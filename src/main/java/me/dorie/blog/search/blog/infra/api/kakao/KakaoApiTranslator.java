package me.dorie.blog.search.blog.infra.api.kakao;

import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface KakaoApiTranslator {

    @Mapping(target = "query", source = "keyword")
    KakaoApiDto.BlogSearchRequest translateToRequest(BlogSearchCriteria criteria);

    List<Blog> translateToBlogs(List<KakaoApiDto.BlogSearchResponse.Document> documents);
}
