package org.example.Mappers;

import java.util.Date;

public class ArticleDTO {
    private int id;
    private String title;
    private String documentData;
    private int author;
    private String comment;
    private Date submissionDate; // Keep the field name consistent

    // Default constructor
    public ArticleDTO() {}

    // Constructor for initializing with title, documentData, author, comment, and submissionDate
    public ArticleDTO(String title, String documentData, int author, String comment, Date submissionDate) {
        this.title = title;
        this.documentData = documentData;
        this.author = author;
        this.comment = comment;
        this.submissionDate = submissionDate;
    }

    // Constructor for initializing with id and other fields
    public ArticleDTO(int id, String title, String documentData, int author, String comment, Date submissionDate) {
        this.id = id;
        this.title = title;
        this.documentData = documentData;
        this.author = author;
        this.comment = comment;
        this.submissionDate = submissionDate;
    }

    // Getters and Setters
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

    public String getDocumentData() {
        return documentData;
    }

    public void setDocumentData(String documentData) {
        this.documentData = documentData;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // Getter and Setter for submissionDate
    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }
}
