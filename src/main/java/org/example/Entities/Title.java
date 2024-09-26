package org.example.Entities;
import javax.persistence.*;


@Entity
@Table(
        name="title"
)
public class Title {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;
    @Column(unique = true, nullable = false)
    private String name;
    private boolean isManager;
    public Title() {}
    public Title(Department department, String name, boolean isManager) {
        this.department = department;
        this.name = name;
        this.isManager = isManager;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
