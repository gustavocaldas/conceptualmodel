package com.gcaldas.conceptualmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcaldas.conceptualmodel.domain.PurcharseOrder;

@Repository
public interface PurcharseOrderRepository extends JpaRepository<PurcharseOrder, Integer> {

}
