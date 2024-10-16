package org.example.Mappers;

import java.util.Date;

public class EmployeeCareerPackageDTO {

    private int id;
    private int employeeId;
    private byte[] careerPackage;
    private String careerPackageName;
    private Date date;
    private CareerPackageTemplateDTO careerPackageTemplateDTO;

    public EmployeeCareerPackageDTO(){}

    public EmployeeCareerPackageDTO(int employeeId, byte[] careerPackage, String careerPackageName, Date date, CareerPackageTemplateDTO careerPackageTemplateDTO) {
        this.employeeId = employeeId;
        this.careerPackage = careerPackage;
        this.careerPackageName = careerPackageName;
        this.date = date;
        this.careerPackageTemplateDTO = careerPackageTemplateDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public byte[] getCareerPackage() {
        return careerPackage;
    }

    public void setCareerPackage(byte[] careerPackage) {
        this.careerPackage = careerPackage;
    }

    public String getCareerPackageName() {
        return careerPackageName;
    }

    public void setCareerPackageName(String careerPackageName) {
        this.careerPackageName = careerPackageName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CareerPackageTemplateDTO getCareerPackageTemplateDTO() {
        return careerPackageTemplateDTO;
    }

    public void setCareerPackageTemplateDTO(CareerPackageTemplateDTO careerPackageTemplateDTO) {
        this.careerPackageTemplateDTO = careerPackageTemplateDTO;
    }
}
