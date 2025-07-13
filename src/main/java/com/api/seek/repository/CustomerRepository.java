package com.api.seek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.seek.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
	
	@Query("SELECT AVG(c.age) FROM CustomerModel c WHERE c.age IS NOT NULL")
	Double averageAge();
	
	@Query("SELECT STDDEV_POP(c.age) FROM CustomerModel c WHERE c.age IS NOT NULL")
	Double calculateDeviation();

}
