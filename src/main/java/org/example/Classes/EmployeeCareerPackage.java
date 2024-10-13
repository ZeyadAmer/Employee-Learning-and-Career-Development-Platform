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
    //@Lob
    private byte[] careerPackage;

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
}
