package com.eldorado.employeeservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private LocalDate date;

    private Set<Integer> hours;

    public boolean addAppointment(int hour){
        if(!this.isWorkingHours(hour))
            throw new InvalidParameterException("Invalid working hour.");
        if(!this.hours.contains(hour)) {
            this.hours.add(hour);
            return true;
        }
        return false;
    }

    private boolean isHourAvailable(int hour){
        if(!this.isWorkingHours(hour))
            throw new InvalidParameterException("Invalid working hour.");
        return this.hours.contains(hour);
    }

    private List<Integer> getAvailableHours(){
        var workingHours = Arrays.asList(8, 9, 10, 11, 14, 15, 16, 17);
        workingHours.removeAll(this.hours);
        return workingHours;
    }
    private boolean isWorkingHours(int hour){
        var morning = hour >= 8 && hour < 12;
        var afternoon = hour >= 14 && hour < 18;
        return morning || afternoon;
    }
}
