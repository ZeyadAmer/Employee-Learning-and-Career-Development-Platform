package org.example.Entities;
import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(
        name="title"
)
public class Title {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "deptId", nullable = false)
    private Department department;
    private String name;
    private boolean isManager;
    public Title() {}
    public Title(Department department, String name, boolean isManager) {
        this.department = department;
        this.name = name;
        this.isManager = isManager;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isManager() {
        return isManager;
    }
    public void setManager(boolean manager) {
        isManager = manager;
    }

}
