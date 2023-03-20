package me.dorie.blog.search.trend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

@Table(
        name = "t_trend_log",
        indexes = {
                @Index(name = "t_trend_log_idx_created_at", columnList = "createdAt"),
                @Index(name = "t_trend_log_idx_keyword_desc", columnList = "keyword")
        }
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrendLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String keyword;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    private TrendLog(String keyword) {
        this.keyword = keyword;
    }

    public static TrendLog from(String keyword) {
        return new TrendLog(keyword);
    }
}
