package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSearchModel {
    public enum Order {ASC, DESC}
    public String searchString;
    public String orderBy;
    public Order sortingDirection;
}
