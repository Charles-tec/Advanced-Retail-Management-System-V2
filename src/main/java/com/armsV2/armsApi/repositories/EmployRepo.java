package com.armsV2.armsApi.repositories;


import com.armsV2.armsApi.dto.EmployeeDto;
import com.armsV2.armsApi.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployRepo extends JpaRepository<Employee, Integer> {

   //find user by username
   Employee findByUsername(String username);

   Employee findBySupervisorCode(String supervisor);

   Employee findEmployeeById(long Id);



}

