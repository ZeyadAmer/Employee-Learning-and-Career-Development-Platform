package Article;

import org.example.Entities.Article;
import org.example.Mappers.ArticleDTO;
import org.example.Mappers.ArticleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ArticleMapperTest {

    private ArticleMapper articleMapper;

    @BeforeEach
    public void setUp() {
        articleMapper = Mappers.getMapper(ArticleMapper.class);
    }

    @Test
    public void testArticleToArticleDTO() {
        Article article = new Article();
        article.setId(1);
        article.setTitle("Test Title");
        article.setDocumentData("Test Document");
        article.setAuthor(100);
        article.setComment("Test Comment");
        article.setSubmissionDate(new Date());

        ArticleDTO articleDTO = articleMapper.articleToArticleDTO(article);

        assertEquals(article.getId(), articleDTO.getId());
        assertEquals(article.getTitle(), articleDTO.getTitle());
        assertEquals(article.getDocumentData(), articleDTO.getDocumentData());
        assertEquals(article.getAuthor(), articleDTO.getAuthor());
        assertEquals(article.getComment(), articleDTO.getComment());
        assertEquals(article.getSubmissionDate(), articleDTO.getSubmissionDate());
    }

    @Test
    public void testArticleDTOToArticle() {
        ArticleDTO articleDTO = new ArticleDTO(1, "Test Title", "Test Document", 100, "Test Comment", new Date());

        Article article = articleMapper.articleDTOToArticle(articleDTO);

        assertEquals(articleDTO.getId(), article.getId());
        assertEquals(articleDTO.getTitle(), article.getTitle());
        assertEquals(articleDTO.getDocumentData(), article.getDocumentData());
        assertEquals(articleDTO.getAuthor(), article.getAuthor());
        assertEquals(articleDTO.getComment(), article.getComment());
        assertEquals(articleDTO.getSubmissionDate(), article.getSubmissionDate());
    }

    @Test
    public void testArticlesToArticleDTOs() {
        Article article1 = new Article();
        article1.setId(1);
        article1.setTitle("Title 1");
        Article article2 = new Article();
        article2.setId(2);
        article2.setTitle("Title 2");

        List<Article> articles = Arrays.asList(article1, article2);

        List<ArticleDTO> articleDTOs = articleMapper.articlesToArticleDTOs(articles);

        assertEquals(articles.size(), articleDTOs.size());
        assertEquals(articles.get(0).getId(), articleDTOs.get(0).getId());
        assertEquals(articles.get(1).getId(), articleDTOs.get(1).getId());
    }

    @Test
    public void testArticleDTOsToArticles() {
        ArticleDTO articleDTO1 = new ArticleDTO(1, "Title 1", "Doc 1", 101, "Comment 1", new Date());
        ArticleDTO articleDTO2 = new ArticleDTO(2, "Title 2", "Doc 2", 102, "Comment 2", new Date());

        List<ArticleDTO> articleDTOs = Arrays.asList(articleDTO1, articleDTO2);

        List<Article> articles = articleMapper.articleDTOsToArticles(articleDTOs);

        assertEquals(articleDTOs.size(), articles.size());
        assertEquals(articleDTOs.get(0).getId(), articles.get(0).getId());
        assertEquals(articleDTOs.get(1).getId(), articles.get(1).getId());
    }
}
