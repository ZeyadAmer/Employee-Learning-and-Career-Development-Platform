package org.example.Classes;

import javax.persistence.*;

@Entity
@Table(
        name = "comment"
)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentText;

    @ManyToOne
    @JoinColumn(name = "sumbitted_career_package_id")
    private SubmittedCareerPackage submittedCareerPackage;

    public Comment(){}

    public Comment(String commentText, SubmittedCareerPackage submittedCareerPackage) {
        this.commentText = commentText;
        this.submittedCareerPackage = submittedCareerPackage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public SubmittedCareerPackage getSubmittedCareerPackage() {
        return submittedCareerPackage;
    }

    public void setSubmittedCareerPackage(SubmittedCareerPackage submittedCareerPackage) {
        this.submittedCareerPackage = submittedCareerPackage;
    }
}
