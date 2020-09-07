package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.CategoryObjectifyService;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryObjectifyService service;

    @GetMapping("/categories")
    public ResponseEntity<ResponseModel> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<ResponseModel> create(@RequestBody CategoryModel category) {
        return new ResponseEntity<>(service.create(category), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/category/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @RequestBody CategoryModel category) {
        category.setId(id);
        return new ResponseEntity<>(service.update(category), HttpStatus.OK);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<ResponseModel> deleteCategory(@PathVariable Long id) throws InstantiationException, IllegalAccessException {
        ResponseModel responseModel = service.delete(id);
        System.out.println(responseModel);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
