package org.example.Entity;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Product {

    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private BigDecimal price;


}
