package org.example.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "employee_career_package"
)
public class EmployeeCareerPackage {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private int employeeId; // from userDB
    @Lob // for large amount of data
    private byte[] careerPackage;
    @OneToOne(mappedBy = "employeeCareerPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private SubmittedCareerPackage submittedCareerPackage;
    private String careerPackageName;
    private Date date;
    @ManyToOne
    @JoinColumn( name = "career_package_template")
    private CareerPackageTemplate careerPackageTemplate;

    public EmployeeCareerPackage(){}

    public EmployeeCareerPackage(int employeeId, byte[] careerPackage, SubmittedCareerPackage submittedCareerPackage, String careerPackageName, Date date, CareerPackageTemplate careerPackageTemplate) {
        this.employeeId = employeeId;
        this.careerPackage = careerPackage;
        this.submittedCareerPackage = submittedCareerPackage;
        this.careerPackageName = careerPackageName;
        this.date = date;
        this.careerPackageTemplate = careerPackageTemplate;
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

    public SubmittedCareerPackage getSubmittedCareerPackage() {
        return submittedCareerPackage;
    }

    public void setSubmittedCareerPackage(SubmittedCareerPackage submittedCareerPackage) {
        this.submittedCareerPackage = submittedCareerPackage;
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

    public CareerPackageTemplate getCareerPackageTemplate() {
        return careerPackageTemplate;
    }

    public void setCareerPackageTemplate(CareerPackageTemplate careerPackageTemplate) {
        this.careerPackageTemplate = careerPackageTemplate;
    }
}
