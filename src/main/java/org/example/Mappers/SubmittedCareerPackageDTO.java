package org.example.Mappers;

import org.example.Classes.EmployeeCareerPackage;
import org.example.Enums.CareerPackageStatus;


public class SubmittedCareerPackageDTO {

    private int id;
    private int employeeId; // from user DB
    private EmployeeCareerPackageDTO employeeCareerPackage; // fk one to one
    private int managerId; // from user DB
    private CareerPackageStatus careerPackageStatus;

    public SubmittedCareerPackageDTO(int employeeId, EmployeeCareerPackageDTO employeeCareerPackage, int managerId, CareerPackageStatus careerPackageStatus) {
        this.employeeId = employeeId;
        this.employeeCareerPackage = employeeCareerPackage;
        this.managerId = managerId;
        this.careerPackageStatus = careerPackageStatus;
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

    public EmployeeCareerPackageDTO getEmployeeCareerPackage() {
        return employeeCareerPackage;
    }

    public void setEmployeeCareerPackage(EmployeeCareerPackageDTO employeeCareerPackage) {
        this.employeeCareerPackage = employeeCareerPackage;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public CareerPackageStatus getCareerPackageStatus() {
        return careerPackageStatus;
    }

    public void setCareerPackageStatus(CareerPackageStatus careerPackageStatus) {
        this.careerPackageStatus = careerPackageStatus;
    }
}
