package org.example.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "department"
)
public class Department {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private UUID id;
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Title> titles;

    public Department() {}
    public Department(String name, List<Title> titles) {
        this.name = name;
        this.titles = titles;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Title> getTitles() {
        return titles;
    }
    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

}
