package org.example.Mappers;

import org.example.Entities.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO articleToArticleDTO(Article article);
    Article articleDTOToArticle(ArticleDTO articleDTO);
    List<ArticleDTO> articlesToArticleDTOs(List<Article> articles);
    List<Article> articleDTOsToArticles(List<ArticleDTO> articleDTOs);
}
