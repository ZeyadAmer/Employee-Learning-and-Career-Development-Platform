package Article;

import org.example.Configurations.JwtUtil;
import org.example.Controllers.ArticleController;
import org.example.Mappers.ArticleDTO;
import org.example.Services.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private ArticleController articleController;



    @Test
    public void testGetAllArticles() {
        ArticleDTO articleDTO = new ArticleDTO(1, "Test Article", "Document Data", 100, "Comment", null);
        List<ArticleDTO> articles = Arrays.asList(articleDTO);

        when(articleService.getAllArticlesApproved(0, 5)).thenReturn(articles);

        ResponseEntity<List<ArticleDTO>> response = articleController.getAllArticles(0, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Test Article", response.getBody().get(0).getTitle());
        verify(articleService, times(1)).getAllArticlesApproved(0, 5);
    }

    @Test
    public void testGetAllArticlesPending() {
        ArticleDTO articleDTO = new ArticleDTO(1, "Pending Article", "Document Data", 100, "Comment", null);
        List<ArticleDTO> articles = Arrays.asList(articleDTO);

        when(articleService.getAllArticlesPending(0, 5)).thenReturn(articles);

        ResponseEntity<List<ArticleDTO>> response = articleController.getAllArticlesPending(0, 5);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Pending Article", response.getBody().get(0).getTitle());
        verify(articleService, times(1)).getAllArticlesPending(0, 5);
    }

    @Test
    public void testCreateArticle() {
        ArticleDTO articleDTO = new ArticleDTO();
        String authorizationHeader = "Bearer token";
        when(request.getHeader("Authorization")).thenReturn(authorizationHeader);
        when(jwtUtil.extractUserId("token")).thenReturn(1);

        ResponseEntity<String> response = articleController.createArticle(articleDTO, request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"response\":\"article created\"}", response.getBody());
        verify(articleService, times(1)).createArticle(articleDTO);
    }


    @Test
    public void testApproveArticle() {
        int articleId = 1;

        ResponseEntity<String> response = articleController.approveArticle(articleId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"response\":\"article approved\"}", response.getBody());
        verify(articleService, times(1)).approveArticle(articleId);
    }

    @Test
    public void testRejectArticle() {
        int articleId = 1;

        ResponseEntity<String> response = articleController.rejectArticle(articleId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("{\"response\":\"article rejected\"}", response.getBody());
        verify(articleService, times(1)).rejectArticle(articleId);
    }
}
