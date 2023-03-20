package me.dorie.blog.search.trend.infra.db.jpa;

import me.dorie.blog.search.trend.domain.TrendLog;
import org.springframework.data.repository.CrudRepository;

public interface TrendLogJpaRepository extends CrudRepository<TrendLog, Long>, TrendLogJpaRepositoryCustom {

}
