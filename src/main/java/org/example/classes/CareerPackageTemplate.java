package org.example.classes;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "career_package_template"
)
public class CareerPackageTemplate {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Lob
    private byte[] careerPackageTemplate;
    @OneToMany(mappedBy = "careerPackageTemplate", cascade = CascadeType.ALL)
    private List<EmployeeCareerPackage> employeeCareerPackages;


    public CareerPackageTemplate(){}

    public CareerPackageTemplate(String title, byte[] careerPackageTemplate) {
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

    public List<EmployeeCareerPackage> getEmployeeCareerPackages() {
        return employeeCareerPackages;
    }

    public void setEmployeeCareerPackages(List<EmployeeCareerPackage> employeeCareerPackages) {
        this.employeeCareerPackages = employeeCareerPackages;
    }
}
