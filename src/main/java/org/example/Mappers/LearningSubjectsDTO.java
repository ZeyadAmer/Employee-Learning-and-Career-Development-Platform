package org.example.Mappers;

import org.example.Entities.LearningSubjects;

public class LearningSubjectsDTO {
    private int id;
    private String subject;
    private LearningSubjects.LearningSubjectsType learningSubjectsType;

    public LearningSubjectsDTO(){}

    public LearningSubjectsDTO(String subject, LearningSubjects.LearningSubjectsType learningSubjectsType){
        this.subject = subject;
        this.learningSubjectsType = learningSubjectsType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LearningSubjects.LearningSubjectsType getLearningSubjectsType() {
        return learningSubjectsType;
    }

    public void setLearningSubjectsType(LearningSubjects.LearningSubjectsType learningSubjectsType) {
        this.learningSubjectsType = learningSubjectsType;
    }
}
