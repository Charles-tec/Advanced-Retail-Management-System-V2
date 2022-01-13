package com.armsV2.armsApi.repositories;


import com.armsV2.armsApi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        Optional<Employee> findByUsername(String username);

        Boolean existsByUsername(String username);


}

