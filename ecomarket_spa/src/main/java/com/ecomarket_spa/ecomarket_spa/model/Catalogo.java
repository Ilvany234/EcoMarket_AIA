package com.ecomarket_spa.ecomarket_spa.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad representando un producto del catálogo")
public class Catalogo {


    @Schema(description = "ID del producto", example = "1")
    private Long id;


    @Schema(description = "Nombre del producto", example = "Detergente ecológico")
    private String nombre;


    @Schema(description = "Precio del producto", example = "4590")
    private Double precio;


    @Schema(description = "Cantidad disponible en stock", example = "100")
    private Integer stock;
}
