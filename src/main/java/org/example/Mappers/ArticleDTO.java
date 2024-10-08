package org.example.Mappers;

import java.util.Date;

public class ArticleDTO {
    private int id;
    private String title;
    private String content;
    private String author;
    private String comment;
    private Date date;
    public ArticleDTO() {}
    public ArticleDTO(int id, String title, String content, String author, String comment, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.comment = comment;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
