package org.example.Controllers;

import org.example.Configurations.JwtUtil;
import org.example.Entities.Article;
import org.example.Mappers.ArticleDTO;
import org.example.Services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/blogs")
public class ArticleController {
    private ArticleService articleService;
    private JwtUtil jwtUtil;
    public ArticleController(ArticleService articleService, JwtUtil jwtUtil) {
        this.articleService = articleService;
        this.jwtUtil = jwtUtil;
    }
    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getAllArticles(
            @RequestParam(defaultValue = "0") int page, // Default page 0
            @RequestParam(defaultValue = "5") int size  // Default page size 5
    ) {
        List<ArticleDTO> articles = articleService.getAllArticlesApproved(page, size);
        return ResponseEntity.ok(articles);
    }
    @GetMapping("/pending")
    public ResponseEntity<List<ArticleDTO>> getAllArticlesPending(
            @RequestParam(defaultValue = "0") int page, // Default page 0
            @RequestParam(defaultValue = "5") int size  // Default page size 5
    ) {
        List<ArticleDTO> articles = articleService.getAllArticlesPending(page, size);
        return ResponseEntity.ok(articles);
    }
    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody ArticleDTO articleDTO, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.substring(7);
        int id = jwtUtil.extractUserId(token);
        articleDTO.setAuthor(id);
        articleService.createArticle(articleDTO);
        return ResponseEntity.ok("{\"response\":\"" + "article created" + "\"}");
    }
    @PutMapping("approve")
    public ResponseEntity<String> approveArticle(@RequestBody int id) {
        articleService.approveArticle(id);
        return ResponseEntity.ok("{\"response\":\"" + "article approved" + "\"}");
    }
    @PutMapping("reject")
    public ResponseEntity<String> rejectArticle(@RequestBody int id) {
        articleService.rejectArticle(id);
        return ResponseEntity.ok("{\"response\":\"" + "article rejected" + "\"}");
    }
}
