package com.example.demo.core.users.infrastructure.jpa;

import com.example.demo.core.users.infrastructure.entity.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
                select u from User u 
                join fetch u.role r 
                join fetch r.permissions 
                where u.email = :email
            """)
    Optional<User> findWithRolesAndPermissionsByEmail(
            @NotNull @Param("email") String email
    );

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findById(@NotNull Long id);
}
