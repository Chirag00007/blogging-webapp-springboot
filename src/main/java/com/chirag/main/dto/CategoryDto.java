package com.chirag.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer cId;
    @NotBlank
    @NotNull
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String cTitle;
    @NotBlank
    @NotNull
    @Size(min = 10, max = 50, message = "Description must be between 10 and 50 characters")
    private String cDescription;


    }
