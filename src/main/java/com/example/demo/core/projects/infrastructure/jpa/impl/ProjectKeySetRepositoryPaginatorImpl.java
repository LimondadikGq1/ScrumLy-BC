package com.example.demo.core.projects.infrastructure.jpa.impl;

import com.example.demo.core.colaboration.infrastructure.entity.UserProjectRole;
import com.example.demo.core.projects.infrastructure.entity.Project;
import com.example.demo.global.pagination.Paginator;
import com.example.demo.global.pagination.Sorter;
import com.example.demo.global.pagination.impl.SortOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Slf4j
@Repository
public class ProjectKeySetRepositoryPaginatorImpl implements Paginator<Project> {

    private final EntityManager em;

    private final CriteriaBuilder builder;

    private final CriteriaQuery<Project> critQuery;

    private final Root<Project> root;

    @Autowired
    public ProjectKeySetRepositoryPaginatorImpl(
            EntityManager em) {
        this.em = em;
        builder = em.getCriteriaBuilder();
        this.critQuery = builder.createQuery(Project.class);
        this.root = critQuery.from(Project.class);
    }

    @Override
    public List<Project> getFirstPage(Long userId, int limit,
                                      Sorter sortBy,
                                      SortOrder order)
    {
        Join<Project, UserProjectRole> usersAndRoles = root.join("usersAndRoles", JoinType.INNER);
        Predicate predicate = builder.equal(usersAndRoles.get("user").get("id"), userId);
        Order orderBy  = getOrder(order, sortBy,root);
        critQuery.select(root).where(predicate).orderBy(orderBy);
        return getExecutionList(limit);
    }

    @Override
    public List<Project> getNextPage(Long userId,
                                     int limit,
                                     Object cursor,
                                     Sorter sortBy,
                                     SortOrder order)
    {
        Integer  custedCursor =Integer.valueOf(custCursor(String.class,cursor));
        Join<Project, UserProjectRole> usersAndRoles = root.join("usersAndRoles", JoinType.INNER);
        Order orderBy  = getOrder(order, sortBy,root);
        Predicate predicate = builder.and(
                builder.equal(usersAndRoles.get("user").get("id"), userId),
                builder.greaterThan(usersAndRoles.get("project").get("id"), custedCursor)
        );
        critQuery.select(root).where(predicate).orderBy(orderBy);
        return getExecutionList(limit);
    }

    @Override
    public <V> V custCursor(Class<V> type, Object cursor) {
        return (V) cursor;

    }

    private Order getOrder(SortOrder sortOrder, Sorter sortBy,Root<Project> root){
        Order order = null;
        switch (sortOrder){
            case  SortOrder.ASC ->  order = builder.asc(root.get(sortBy.getSort()));
            case  SortOrder.DESC -> order = builder.desc(root.get(sortBy.getSort()));
        }
        return order;
    }

    private List<Project> getExecutionList(Integer limit){
        TypedQuery<Project> query = em.createQuery(critQuery);
        List<Project> projects = query.setMaxResults(limit).getResultList();
        return projects;
    }
}
