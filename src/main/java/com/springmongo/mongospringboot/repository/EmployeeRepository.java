package com.springmongo.mongospringboot.repository;

import com.springmongo.mongospringboot.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Integer> {
}
