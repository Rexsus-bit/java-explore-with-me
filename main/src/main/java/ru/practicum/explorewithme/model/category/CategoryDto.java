package ru.practicum.explorewithme.model.category;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @Min(1)
    @NotNull
    private Long id;
    @NotBlank
    private String name;
}
