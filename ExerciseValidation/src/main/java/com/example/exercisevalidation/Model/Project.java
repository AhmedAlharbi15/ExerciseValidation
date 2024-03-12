package com.example.exercisevalidation.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "Should be placed ID")
    @Size(min = 2)
    private String id;
    @NotEmpty(message = "Should be placed title")
    @Size(min = 8)
    private String title;
    @NotEmpty(message ="Should be placed description")
    @Size(min = 15)
    private String description;
    @NotEmpty(message = "Should be placed status")
    private boolean status;
    @NotEmpty(message = "Should be placed companyName")
    @Size(min = 6)
    private String companyName;
}
