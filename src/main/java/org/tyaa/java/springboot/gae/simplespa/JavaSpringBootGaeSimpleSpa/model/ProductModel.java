package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "Products")
public class ProductModel {
    @Id
    private Long id;
    @Index
    private String title;
    private String description;
    @Index
    @JsonIgnore
    private BigDecimal price;
    @Index
    private Integer quantity;
    private String image;
    @Index
    private Long categoryId;
    @Ignore
    private CategoryModel category;
    @Ignore
    private Double priceDouble;
}
