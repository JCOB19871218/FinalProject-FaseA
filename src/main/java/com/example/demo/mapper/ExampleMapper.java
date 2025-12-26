package com.example.demo.mapper;

import com.example.demo.dto.example.ExampleRequestDto;
import com.example.demo.dto.example.ExampleResponseDto;
import com.example.demo.entity.example.Example;

import java.util.ArrayList;
import java.util.List;

public class ExampleMapper {

    public static Example toEntity(ExampleRequestDto dto) {
        if (dto == null) return null;

        return Example.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .build();
    }

    public static ExampleResponseDto toDto(Example example) {
        if (example == null) return null;

        ExampleResponseDto dto = new ExampleResponseDto();
        dto.setId(example.getId());
        dto.setTitle(example.getTitle());
        dto.setDescription(example.getDescription());
        dto.setDuration(example.getDuration());
        dto.setProfessor(example.getProfessor());

        return dto;
    }

    public static List<ExampleResponseDto> toDtoList(List<Example> examples) {
        List<ExampleResponseDto> dtoList = new ArrayList<>();
        for (Example example : examples) {
            dtoList.add(toDto(example));
        }
        return dtoList;
    }
}
