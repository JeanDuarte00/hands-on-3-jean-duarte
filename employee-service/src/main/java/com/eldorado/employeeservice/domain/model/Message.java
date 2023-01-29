package com.eldorado.employeeservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {

    String to;
    String subject;
    @JsonIgnore
    String password;
    String message;

    @Override
    public String toString() {
        return "MessageDto{" +
                "to='" + to + '\'' +
                ", subject='" + subject +
                '}';
    }
}
