package me.dorie.blog.search.blog.infra.api.kakao;

import me.dorie.blog.search.blog.domain.Blog;
import me.dorie.blog.search.blog.domain.BlogSearchCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface KakaoApiDtoMapper {
    KakaoApiDto.BlogSearchRequest toRequest(BlogSearchCriteria criteria);

    List<Blog> toBlogs(List<KakaoApiDto.BlogSearchResponse.Document> documents);

    Blog toBlog(KakaoApiDto.BlogSearchResponse.Document document);
}
