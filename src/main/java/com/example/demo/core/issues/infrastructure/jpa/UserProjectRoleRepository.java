package com.example.demo.core.issues.infrastructure.jpa;

import com.example.demo.core.colaboration.infrastructure.entity.UserProjectRole;
import com.example.demo.core.colaboration.infrastructure.entity.UserProjectRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRoleRepository extends JpaRepository<UserProjectRole, UserProjectRolePK> {

    @Query(value = """
    select usp from User u 
    inner join UserProjectRole usp on usp.user.id = :id
    """)
    List<UserProjectRole> findUserProjectsById(@Param("id")Long id);
}
