package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.annotations.Expose;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.utils.BigDecimalDeserializer;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.utils.MoneySerializer;

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
    @Expose
    private String title;
    @Expose
    private String description;
    @Index
    @Expose
    private Double price;
    @Index
    @Expose
    private Integer quantity;
    private String image;
    @Index
    @Expose
    private Long categoryId;
    @Ignore
    private CategoryModel category;
}
