package com.solution.userSystem.repository;

import com.solution.userSystem.Model.Address;
import com.solution.userSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
