package com.example.demo.dto.example;

import com.example.demo.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExampleRequestDto {


        @NotBlank
        private String title;

        private String description;

        @NotNull
        @Min(1)
        private Integer duration;
 }


