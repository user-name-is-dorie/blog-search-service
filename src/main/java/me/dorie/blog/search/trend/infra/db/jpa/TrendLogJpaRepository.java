package me.dorie.blog.search.trend.infra.db.jpa;

import org.springframework.data.repository.CrudRepository;

public interface TrendLogJpaRepository extends CrudRepository<TrendLogEntity, Long>, TrendLogJpaRepositoryCustom {

}
