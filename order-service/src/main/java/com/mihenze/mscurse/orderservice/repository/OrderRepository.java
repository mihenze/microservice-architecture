package com.mihenze.mscurse.orderservice.repository;

import com.mihenze.mscurse.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "from Order ord left join fetch ord.items where ord.id=:id")
    Optional<Order> findByIdFetch(Long id);

    @Query(value = "from Order ord left join fetch ord.items order by ord.id")
    List<Order> findAllFetch();

    boolean existsByUid(String uid);
}
