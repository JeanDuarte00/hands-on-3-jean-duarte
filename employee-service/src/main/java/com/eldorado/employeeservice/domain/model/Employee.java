package com.eldorado.employeeservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

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

    @Nullable
    @JsonIgnore
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
