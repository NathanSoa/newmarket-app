package com.activity.newmarketapp.data.repository.product;

import com.activity.newmarketapp.data.entities.Product;
import com.activity.newmarketapp.data.repository.filter.ProductFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.ObjectUtils;

import java.util.*;

public class ProductRepositoryQueryImpl implements ProductRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Product> findAllFiltered(ProductFilter productFilter){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);

        Predicate[] predicates = buildFilter(productFilter, builder, root);
        criteria.where(predicates);

        return new HashSet<>(entityManager.createQuery(criteria).getResultList());
    }

    private Predicate[] buildFilter(ProductFilter productFilter, CriteriaBuilder builder, Root<Product> root) {
        List<Predicate> predicates = new ArrayList<>();

        if(descriptionIsNotEmpty(productFilter))
            predicates.add(
                    builder.like(
                            builder.lower(root.get("description")),
                            "%" + productFilter.getDescription().toLowerCase() + "%"
                    )
            );

        if(nameIsNotEmpty(productFilter))
            predicates.add(
                    builder.like(
                            builder.lower(root.get("name")),
                            "%" + productFilter.getName().toLowerCase() + "%"
                    )
            );

        if(activeIsNotNull(productFilter))
            predicates.add(
                    builder.equal(root.get("active"), productFilter.getActive())
            );

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private boolean descriptionIsNotEmpty(ProductFilter productFilter) {
        return !ObjectUtils.isEmpty(productFilter.getDescription());
    }

    private boolean nameIsNotEmpty(ProductFilter productFilter) {
        return !ObjectUtils.isEmpty(productFilter.getName());
    }

    private boolean activeIsNotNull(ProductFilter productFilter) {
        return !Objects.isNull(productFilter.getActive());
    }
}
