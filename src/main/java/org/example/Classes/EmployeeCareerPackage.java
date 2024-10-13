package org.example.Classes;

import javax.persistence.*;

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
    private SubmittedCareerPackage submittedCareerPackage;

    public EmployeeCareerPackage(){}

    public EmployeeCareerPackage(int employeeId, byte[] careerPackage) {
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

    public SubmittedCareerPackage getSubmittedCareerPackage() {
        return submittedCareerPackage;
    }

    public void setSubmittedCareerPackage(SubmittedCareerPackage submittedCareerPackage) {
        this.submittedCareerPackage = submittedCareerPackage;
    }
}
