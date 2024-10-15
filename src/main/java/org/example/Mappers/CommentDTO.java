package org.example.Mappers;

import org.example.Classes.SubmittedCareerPackage;

public class CommentDTO {

    private int id;
    private String commentText;
    private SubmittedCareerPackage submittedCareerPackage;

    public CommentDTO(){}

    public CommentDTO(String commentText, SubmittedCareerPackage submittedCareerPackage) {
        this.commentText = commentText;
        this.submittedCareerPackage = submittedCareerPackage;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubmittedCareerPackage getSubmittedCareerPackage() {
        return submittedCareerPackage;
    }

    public void setSubmittedCareerPackage(SubmittedCareerPackage submittedCareerPackage) {
        this.submittedCareerPackage = submittedCareerPackage;
    }
}
