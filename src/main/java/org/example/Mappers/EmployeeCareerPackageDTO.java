package org.example.Mappers;

public class EmployeeCareerPackageDTO {

    private int id;
    private int employeeId;
    private byte[] careerPackage;

    public EmployeeCareerPackageDTO(){}

    public EmployeeCareerPackageDTO(int employeeId, byte[] careerPackage) {
        this.employeeId = employeeId;
        this.careerPackage = careerPackage;
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
}
