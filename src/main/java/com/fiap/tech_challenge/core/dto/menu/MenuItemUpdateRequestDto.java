package com.fiap.tech_challenge.core.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request body for creating a new menu item")
public class MenuItemUpdateRequestDto {

    @NotEmpty
    @Schema(description = "Name of the menu item", example = "Pizza Pepperoni")
    private String name;

    @Schema(description = "Description of the menu item", example = "Pizza clássica com molho de tomate e pepperoni")
    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Schema(description = "Price of the menu item", example = "49.90")
    private BigDecimal price;

    @Schema(description = "Indicates if the item is available for in-store purchase only", example = "true")
    private Boolean availableInStoreOnly;

    @Schema(description = "Path to the photo of the menu item", example = "/images/menu/mi123abc456.jpg")
    private String photoPath;

}
