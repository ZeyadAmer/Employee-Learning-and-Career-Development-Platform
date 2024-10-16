package org.example.mapper;

public class CommentDTO {

    private int id;
    private String commentText;
    private SubmittedCareerPackageDTO submittedCareerPackage;

    public CommentDTO(){}

    public CommentDTO(String commentText, SubmittedCareerPackageDTO submittedCareerPackage) {
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

    public SubmittedCareerPackageDTO getSubmittedCareerPackage() {
        return submittedCareerPackage;
    }

    public void setSubmittedCareerPackage(SubmittedCareerPackageDTO submittedCareerPackage) {
        this.submittedCareerPackage = submittedCareerPackage;
    }
}
