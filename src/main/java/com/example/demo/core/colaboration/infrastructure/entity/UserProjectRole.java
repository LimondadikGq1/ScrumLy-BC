package com.example.demo.core.colaboration.infrastructure.entity;

import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.projects.infrastructure.entity.ProjectRole;
import com.example.demo.core.users.infrastructure.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@Entity
@Table(name = "users_projects_roles")
@IdClass(UserProjectRolePK.class)
public class UserProjectRole {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Id
    @ManyToOne
    @JoinColumn(name = "project_role_template_id")
    private ProjectRole projectRole;
}
