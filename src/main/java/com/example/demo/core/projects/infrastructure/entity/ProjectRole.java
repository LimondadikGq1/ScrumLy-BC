package com.example.demo.core.projects.infrastructure.entity;

import com.example.demo.core.users.infrastructure.entity.Permission;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "project_role_templates")
public class ProjectRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    @JoinTable(name = "project_roles_permissions",
            joinColumns = @JoinColumn(name = "project_role_template_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

    @Builder
    public ProjectRole(String name) {
        this.name = name;
    }
}
