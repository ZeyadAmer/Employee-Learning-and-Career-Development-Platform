package org.example.Mappers;

public class CareerPackageTemplateDTO {

    private int id;
    private String title;
    private byte[] careerPackageTemplate;

    public CareerPackageTemplateDTO(){}

    public CareerPackageTemplateDTO(String title, byte[] careerPackageTemplate) {
        this.title = title;
        this.careerPackageTemplate = careerPackageTemplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getCareerPackageTemplate() {
        return careerPackageTemplate;
    }

    public void setCareerPackageTemplate(byte[] careerPackageTemplate) {
        this.careerPackageTemplate = careerPackageTemplate;
    }
}
