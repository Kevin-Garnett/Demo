package com.hsbc.demo.config;

import com.hsbc.demo.bean.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.UUID;

@Configuration
public class InitEmployeeData {

    private static Logger logger = LoggerFactory.getLogger(InitEmployeeData.class);

    // Remember should not have 2 same function name = init() here, otherwise spring boot will only run 1 of them
    // So here should not name init(), otherwise will have the same function name as LoadDatabase.init()
    @Bean
    CommandLineRunner initEmployee(MongoOperations operations) {
        return args -> {
            operations.dropCollection(Employee.class);

            Employee e1 = new Employee();
            e1.setId(UUID.randomUUID().toString());
            e1.setFirstName("Kevin");
            e1.setLastName("Huang");
            e1.setRole("Admin");
            operations.insert(e1);

            Employee e2 = new Employee();
            e2.setId(UUID.randomUUID().toString());
            e2.setFirstName("Sherry");
            e2.setLastName("Ou");
            e2.setRole("User");
            operations.insert(e2);

            operations.findAll(Employee.class).forEach(employee -> {
                logger.info(employee.toString());
            });
        };
    }
}
