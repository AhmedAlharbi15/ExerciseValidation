package com.example.exercisevalidation.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
   @NotEmpty(message = "Should be placed ID")
   @Size(min = 2)
    private String id;
   @NotEmpty(message = "Should be placed description")
   @Size(min = 15)
   private String description;
   @NotEmpty(message = "Should be placed capacity ")
   @Min(value = 25)
   @Pattern(regexp = "[1-20]+")
   private int capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
