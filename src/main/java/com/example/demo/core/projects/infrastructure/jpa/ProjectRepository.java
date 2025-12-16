package com.example.demo.core.projects.infrastructure.jpa;

import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.core.users.infrastructure.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    
    @Query("""
         SELECT key FROM Project p
         where p.key = :key
    """)
    Optional<String> findKey(@Param("key") String key);


    @Query("""
        SELECT DISTINCT u FROM User u 
        INNER JOIN  UserProjectRole usr on usr.user.id = u.id
        WHERE usr.user.id = :userId AND usr.project.id = :projectId  
    """)
    List<User> findAllUsersByProject(@Param("authorId") Long userId,
                                     @Param("projectId") Long projectId);


    @Query("""
        SELECT p from Project p
        INNER JOIN  UserProjectRole upr on upr.project.id = p.id
        where upr.user.id = :userId
    """)
    Page<Project> findAllByUsersAndRoles_UserId(@Param("userId") Long userId,
                                                Pageable pageable);


    @Query("""
       SELECT p FROM Project p
       INNER JOIN  UserProjectRole upr ON upr.project.id = p.id
       where upr.user.id = :userId AND p.name = :name 
    """)
    Optional<Project> findProjectByName(@Param("authorId") Long userId,
                                        @Param("name") String name);


    @Query("""
       SELECT p FROM Project p
       INNER JOIN  UserProjectRole upr ON upr.project.id = p.id
       where upr.user.id = :authorId AND p.key = :key
    """)
    Optional<Project> findProjectByKey(@Param("authorId") Long authorId,
                                       @Param("key") String key);


    @Query("""
       SELECT p FROM Project p
       INNER JOIN UserProjectRole upr on upr.project.id = p.id
       where upr.user.id = :authorId AND p.id = :projectId 
    """)
    Optional<Project> findProjectById(@Param("authorId") Long authorId,
                                      @Param("projectId") Long projectId
    );
}
