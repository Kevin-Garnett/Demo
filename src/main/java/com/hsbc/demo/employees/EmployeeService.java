package com.hsbc.demo.employees;

import com.hsbc.demo.employees.Employee;
import com.hsbc.demo.employees.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeService {

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Mono<Employee> getOneEmployee(String firstName){
        Employee e = new Employee();
        e.setFirstName(firstName);
        Example<Employee> example = Example.of(e); // default matcher required to match the stored records exactly

        //Also can be implemented as below:
        Mono<Employee> singleEmployee = reactiveMongoOperations.findOne(
                new Query(Criteria.byExample(example)), Employee.class);

        return employeeRepository.findOne(example);
    }

    public Flux<Employee> getAllEmployees(){
        Employee e = new Employee();
        e.setLastName("kevin");  //Lowercase lastName

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withIncludeNullValues();

        Example<Employee> example = Example.of(e, matcher); // customized matcher

        //Also can be implemented as below:
        Flux<Employee> multipleEmployees = reactiveMongoOperations.find(
                new Query(Criteria.byExample(example)), Employee.class);

        return employeeRepository.findAll(example);
    }
}
