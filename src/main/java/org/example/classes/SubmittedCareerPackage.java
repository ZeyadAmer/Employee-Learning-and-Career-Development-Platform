package org.example.classes;

import org.example.enums.CareerPackageStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "submitted_career_package"
)
public class SubmittedCareerPackage {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private int employeeId; // from user DB
    @OneToOne
    @JoinColumn(name = "employee_career_package_id", unique = true)
    private EmployeeCareerPackage employeeCareerPackage; // fk one to one
    private int managerId; // from user DB
    private CareerPackageStatus careerPackageStatus; // enum
    @OneToMany(mappedBy = "submittedCareerPackage", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public SubmittedCareerPackage(){}

    public SubmittedCareerPackage(int employeeId, EmployeeCareerPackage employeeCareerPackage, int managerId, CareerPackageStatus careerPackageStatus) {
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

    public EmployeeCareerPackage getEmployeeCareerPackage() {
        return employeeCareerPackage;
    }

    public void setEmployeeCareerPackage(EmployeeCareerPackage employeeCareerPackage) {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
