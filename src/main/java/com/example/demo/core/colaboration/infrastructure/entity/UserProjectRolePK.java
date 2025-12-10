package com.example.demo.core.colaboration.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter

public class UserProjectRolePK implements Serializable {
    private Long user;
    private Long project;
    private Long projectRole;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserProjectRolePK that = (UserProjectRolePK) o;
        return Objects.equals(user, that.user) && Objects.equals(project, that.project) && Objects.equals(projectRole, that.projectRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, project, projectRole);
    }
}
