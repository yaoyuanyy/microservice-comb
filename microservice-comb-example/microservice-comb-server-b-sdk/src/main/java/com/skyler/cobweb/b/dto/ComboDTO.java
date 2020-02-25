package com.skyler.cobweb.b.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComboDTO {
    private Long id;

    private String comboName;

    private Byte state;

    private String brandName;

}