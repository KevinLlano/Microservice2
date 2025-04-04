package com.microservicetry.department_service.controller;

import com.microservicetry.department_service.client.EmployeeClient;

import com.microservicetry.department_service.model.Department;
import com.microservicetry.department_service.repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    // Handle GET request to /department/with-employees
    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        // Log the request
        LOGGER.info("Department find");

        // Get all departments from the repository
        List<Department> departments = repository.findAll();

        // For each department, fetch and set its employees using the employee service
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));

        // Return the list of departments with employees
        return departments;
    }

}
