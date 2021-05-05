package com.incs83.hrm.repository;

import com.incs83.hrm.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
