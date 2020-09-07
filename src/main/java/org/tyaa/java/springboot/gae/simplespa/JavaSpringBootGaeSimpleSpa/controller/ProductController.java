package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductSearchModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping("/products")
    public ResponseEntity<ResponseModel> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseModel> create(@RequestBody ProductModel product) throws InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(service.create(product), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/product/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @RequestBody ProductModel product) throws InstantiationException, IllegalAccessException {
        product.setId(id);
        return new ResponseEntity<>(service.update(product), HttpStatus.OK);
    }

    // поиск списка товаров согласно query dsl-запроса из http-параметра search
    // и сортировка по значению поля orderBy в направлении sortingDirection,
    // заданным как часть начальной строки с произвольно выбранными разделителями:
    // "::" - между парами ключ-значение,
    // ":" - между каждым ключом и его значением
    @GetMapping("/products/filtered::orderBy:{orderBy}::sortingDirection:{sortingDirection}")
    public ResponseEntity<ResponseModel> search(
        @RequestParam(value = "search") String searchString,
        @PathVariable String orderBy,
        @PathVariable ProductSearchModel.Order sortingDirection
    ) {
        return new ResponseEntity<>(
            service.search(
                new ProductSearchModel(searchString, orderBy, sortingDirection)
            ),
            HttpStatus.OK
        );
    }
    //
    @GetMapping("/products/price-bounds")
    public ResponseEntity<ResponseModel> getProductsPriceBounds() {
        return new ResponseEntity<>(
                service.getProductsPriceBounds(),
                HttpStatus.OK
        );
    }
}
