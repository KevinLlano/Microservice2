package com.microservicetry.department_service.model;

// A record automatically generates methods like constructors, getters,
// toString(), equals(), and hashCode() for the fields (id, departmentId, name, age, position).
public record Employee(Long id, Long departmentId, String name, int age, String position) {
}
