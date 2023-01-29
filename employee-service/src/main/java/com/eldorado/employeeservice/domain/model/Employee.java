package com.eldorado.employeeservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("employee")
public class Employee {

    @Id
    private String document;

    private String name;

    private String email;

    private String password;

    private List<Appointment> appointments;

    @Override
    public String toString() {
        return "Employee{" +
                "document='" + document + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
