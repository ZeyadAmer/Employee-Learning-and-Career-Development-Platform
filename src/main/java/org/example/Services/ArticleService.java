package org.example.Services;

import org.example.Entities.Article;
import org.example.Mappers.ArticleDTO;
import org.example.Mappers.ArticleMapper;
import org.example.Repositories.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }
    public List<ArticleDTO> getAllArticlesApproved(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findAllTitleApproved(pageable);
        return articleMapper.articlesToArticleDTOs(articlePage.getContent());
    }
    public List<ArticleDTO> getAllArticlesPending(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findAllTitlePending(pageable);
        return articleMapper.articlesToArticleDTOs(articlePage.getContent());
    }
    public void createArticle(ArticleDTO articleDTO) {
        Article article = articleMapper.articleDTOToArticle(articleDTO);
        articleRepository.save(article);
    }
    public void approveArticle(int id) {
        Optional<Article> article = articleRepository.findById(id);
        article.get().setApprovalStatus(Article.ApprovalStatus.APPROVED);
        articleRepository.save(article.get());

    }

    public void rejectArticle(int id) {
        Optional<Article> article = articleRepository.findById(id);
        article.get().setApprovalStatus(Article.ApprovalStatus.REJECTED);
        articleRepository.save(article.get());
    }

}
