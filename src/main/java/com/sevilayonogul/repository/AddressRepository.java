package com.sevilayonogul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sevilayonogul.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long>{

}
