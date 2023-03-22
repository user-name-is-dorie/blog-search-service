package me.dorie.blog.search.trend.infra.db.redis;

import me.dorie.blog.search.trend.infra.db.jpa.TrendLogJpaOperator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 검색 키워드 많은 순으로 조회
 * <p>
 * 1. RDBMS : 검색할 때마다 검색어를 로우 쌓는것
 * - 검색어를 Group By 해서 count 가 높은 순으로 조회함.
 * <p>
 * 2. REDIS : sortedSet : score 기반으로 정렬을 알아서 해줍니담.
 * - 검색될 때마다 해당 키(검색어) score 를 1씩 올려주면 score 가 높은 순으로 간단히 조회할 수 있음
 */
@DisplayName("인기 검색어 속도 테스트")
@SpringBootTest
@Tag("speed-test")
/*
  이 테스트는 시간이 오래걸리기에 태그를 통해 테스트 단계에서 제외할 수 있음
  tasks.named('test') {
      useJUnitPlatform{
          excludeTags 'speed-test'
      }
  }
 */
class TrendRetrieveSpeedTest {

    private static final List<String> SAMPLE_KEYWORDS = List.of("이효리", "김상순", "아이유");
    private static final Random RANDOM = new Random();
    private static final int DATA_SIZE = 100_000;
    @Autowired
    TrendRedisOperator redisOperator;
    @Autowired
    TrendLogJpaOperator jpaOperator;

    private static String getRandomSampleKeyword() {
        final int randomIndex = RANDOM.nextInt(SAMPLE_KEYWORDS.size());
        return SAMPLE_KEYWORDS.get(randomIndex);
    }

    private static void makeDataWithMultiThread(Runnable runnable) throws InterruptedException {
        try (ExecutorService service = Executors.newFixedThreadPool(10)) {
            CountDownLatch latch = new CountDownLatch(DATA_SIZE);
            for (int i = 0; i < DATA_SIZE; i++) {
                service.submit(() -> {
                    runnable.run();
                    latch.countDown();
                });
            }
            latch.await();
        }
    }

    private static void speedCheck(String title, Runnable runnable) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(title);
        runnable.run();
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println("ms : " + stopWatch.getTotalTimeSeconds());
    }

    @Timeout(value = 5)
    @Test
    void 레디스_정렬된_셋을_사용할_경우() throws InterruptedException {
        // given
        makeDataWithMultiThread(() -> {
            final String keyword = getRandomSampleKeyword();
            redisOperator.write(keyword);
        });

        // expect
        speedCheck("레디스 속도 체크", () -> redisOperator.getTrendsByLimit(10));
    }

    @Timeout(value = 5)
    @Test
    void DB_로그_를_사용할_경우() throws InterruptedException {
        // given
        makeDataWithMultiThread(() -> {
            final String keyword = getRandomSampleKeyword();
            jpaOperator.logging(keyword);
        });

        // expect
        speedCheck("JPA DB 속도 체크", () -> jpaOperator.getTrendsByLimit(10));
    }

}
