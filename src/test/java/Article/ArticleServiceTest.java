package Article;

import org.example.Entities.Article;
import org.example.Mappers.ArticleDTO;
import org.example.Mappers.ArticleMapper;
import org.example.Repositories.ArticleRepository;
import org.example.Services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleService articleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllArticlesApproved() {
        Pageable pageable = PageRequest.of(0, 10);
        Article article = new Article();
        article.setId(1);
        article.setTitle("Test Article");
        Page<Article> articlePage = new PageImpl<>(Arrays.asList(article));

        when(articleRepository.findAllTitleApproved(pageable)).thenReturn(articlePage);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1);
        articleDTO.setTitle("Test Article");
        when(articleMapper.articlesToArticleDTOs(any())).thenReturn(Arrays.asList(articleDTO));

        List<ArticleDTO> result = articleService.getAllArticlesApproved(0, 10);

        assertEquals(1, result.size());
        assertEquals("Test Article", result.get(0).getTitle());
        verify(articleRepository, times(1)).findAllTitleApproved(pageable);
    }

    @Test
    public void testGetAllArticlesPending() {
        Pageable pageable = PageRequest.of(0, 10);
        Article article = new Article();
        article.setId(1);
        article.setTitle("Pending Article");
        Page<Article> articlePage = new PageImpl<>(Arrays.asList(article));

        when(articleRepository.findAllTitlePending(pageable)).thenReturn(articlePage);
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(1);
        articleDTO.setTitle("Pending Article");
        when(articleMapper.articlesToArticleDTOs(any())).thenReturn(Arrays.asList(articleDTO));
        List<ArticleDTO> result = articleService.getAllArticlesPending(0, 10);

        assertEquals(1, result.size());
        assertEquals("Pending Article", result.get(0).getTitle());
        verify(articleRepository, times(1)).findAllTitlePending(pageable);
    }

    @Test
    public void testCreateArticle() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle("New Article");
        Article article = new Article();
        article.setTitle("New Article");

        when(articleMapper.articleDTOToArticle(any(ArticleDTO.class))).thenReturn(article);

        articleService.createArticle(articleDTO);

        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testApproveArticle() {
        Article article = new Article();
        article.setId(1);
        article.setApprovalStatus(Article.ApprovalStatus.PENDING);
        Optional<Article> optionalArticle = Optional.of(article);

        when(articleRepository.findById(anyInt())).thenReturn(optionalArticle);

        articleService.approveArticle(1);

        assertEquals(Article.ApprovalStatus.APPROVED, article.getApprovalStatus());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    public void testRejectArticle() {
        Article article = new Article();
        article.setId(1);
        article.setApprovalStatus(Article.ApprovalStatus.PENDING);
        Optional<Article> optionalArticle = Optional.of(article);

        when(articleRepository.findById(anyInt())).thenReturn(optionalArticle);

        articleService.rejectArticle(1);

        assertEquals(Article.ApprovalStatus.REJECTED, article.getApprovalStatus());
        verify(articleRepository, times(1)).save(article);
    }
}
