package com.demo.ConsumerLoan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.ConsumerLoan.entity.Collateral;

@Repository
public interface CollateralRepository extends CrudRepository<Collateral, String> {

}
