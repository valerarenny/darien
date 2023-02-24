package com.test.darien.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String name;
    private Integer goals;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String teamName;
}
