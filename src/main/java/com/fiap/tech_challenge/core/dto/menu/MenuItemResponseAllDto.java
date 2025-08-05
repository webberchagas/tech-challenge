package com.fiap.tech_challenge.core.dto.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response body containing menu item information")
public class MenuItemResponseAllDto {

    @Schema(description = "Unique identifier of the menu item", example = "mi123abc456")
    private String menuItemId;

    @Schema(description = "Name of the menu item", example = "Pizza Pepperoni")
    private String name;

    @Schema(description = "Description of the menu item", example = "Pizza clássica com molho de tomate e pepperoni")
    private String description;

    @Schema(description = "Price of the menu item", example = "49.90")
    private BigDecimal price;

    @Schema(description = "Indicates if the item is available for in-store purchase only", example = "true")
    private Boolean availableInStoreOnly;

    @Schema(description = "Path to the photo of the menu item", example = "/images/menu/mi123abc456.jpg")
    private String photoPath;
}
