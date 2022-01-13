package com.armsV2.armsApi.repositories;


import com.armsV2.armsApi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        Employee findByUserName(String username);
}

