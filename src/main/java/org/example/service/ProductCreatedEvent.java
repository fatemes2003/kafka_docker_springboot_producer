package org.example.service;

import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class ProductCreatedEvent {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal price;
}
