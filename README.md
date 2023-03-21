## 블로그 검색 서비스

### 빌드 결과물

빌드 결과물 다운로드(조금 시간이 걸릴 수 있습니다.) -> [blog-search-service-1.0.jar](https://drive.google.com/file/d/1unbz84AnrODWq-K3RCyjRzG1fE13ypn6/view?usp=sharing)

```Shell
java -jar blog-search-service-1.0.jar 
```

### API 명세

- 스웨거를 통해서 "블로그 검색 서비스"의 API 명세하였습니다.
- 빌드된 결과물(jar) 를 실행한 후 http://localhost:8080/swagger-ui/index.html#/ 로 접속하시면 됩니다.

### 패키지 구성

1. 크게 도메인 별로 blog, trend 로 패키지 분리하였고 프로젝트 내의 설정 정보를 담을 수 있는 config 패키지로 구성
   - blog
   - trend
   - config

2. 도메인 내에서는 레이어 별로 패키지를 구성
   - ui : 앱의 요청하려는 클라이언트와 소통하는 
   - application : 앱 내의 유즈케이스 및 다른 도메인과의 소통 및 응용하는 레이어
   - domain : POJO 형태로 앱에서 다루는 관심사 및 인터페이스 정의하는 레이어
   - infra : 도메인의 레이어의 인터페이스를 각각 BoundedContext 에 맞게 저수준 기술 구현하는 레이어
      - Service, Reader, Writer : 도메인 레이어의 Interface 를 바탕으로 BoundedContext 에 맞는 세부 기술 구현 (ex. Redis, Feign, JPA)  
      - Translator : 외부와 통신할 경우 내부 도메인의 형태로 번역해주는 역할

### 용어 사전

| 한글    | 영어      | 비고                                       |
|-------|---------|:-----------------------------------------|
| 인기검색어 | trend   | 구글에선 인기 검색어를 trend 로 표기하여 차용             |
| 검색어   | keyword | DB 의 질의문 query 와 헷갈릴 수 있어서 keyword 로 네이밍 |
|       |         |                                          |

### 사용한 라이브러리

- swagger: 스웨거를 통한 API 명세
- lombok, mapstruct : 반복되는 코드를 줄어들어 편리 및 실수를 방지할 수 있음
- openfeign : 외부 API 호출을 위함, 인터페이스와 어노테이션 기반으로 작성할 코드가 줄어들어 편리
- resilience4j : 장애 대응을 처리하기 위함, 스프링에서 권장하는 프로젝트
- redis : 인기 검색어 관련 요구사항을 sortedSet 사용하여 빠르게 처리하기 위함,
   - 인기검색어를 이미 검색횟수(score) 기준으로 정렬되어 있어 별도 정렬하는 로직을 작성하거나 계산하지 않아도 되는 이점이 있음
- querydsl : JPAQuery 를 컴파일 시점에 파악이 가능하며 동적인 쿼리 작성이 편리

