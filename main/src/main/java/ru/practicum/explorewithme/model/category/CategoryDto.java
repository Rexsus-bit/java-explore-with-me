package ru.practicum.explorewithme.model.category;

import lombok.*;

import javax.persistence.*;


@Data
public class CategoryDto {

    private Long id;
    private String name;
}
