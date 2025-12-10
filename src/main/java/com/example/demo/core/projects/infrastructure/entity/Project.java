package com.example.demo.core.projects.infrastructure.entity;

import com.example.demo.core.colaboration.infrastructure.entity.UserProjectRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "key")
    private String key;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinTable(name = "project_roles",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "project_role_template_id")
    )
    private Set<ProjectRole> projectRoles = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Set<UserProjectRole> usersAndRoles = new HashSet<>();

    @Builder
    public Project(String name, String key, String description) {
        this.name = name;
        this.key = key;
        this.description = description;
    }
}
