package com.hsbc.demo.repository;

import com.hsbc.demo.bean.Employee;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String>, ReactiveQueryByExampleExecutor<Employee> {

}
