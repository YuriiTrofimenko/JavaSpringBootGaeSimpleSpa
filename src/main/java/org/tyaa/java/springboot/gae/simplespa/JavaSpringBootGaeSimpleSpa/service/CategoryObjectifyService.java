package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.CategoryObjectifyDao;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.ICategoryService;

@Service
public class CategoryObjectifyService implements ICategoryService {

    @Autowired
    private CategoryObjectifyDao categoryObjectifyDao;

    @Override
    public ResponseModel create(CategoryModel categoryModel) {
        categoryObjectifyDao.create(categoryModel);
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Category %s Created", categoryModel.getName()))
                .build();
    }

    @Override
    public ResponseModel update(CategoryModel categoryModel) {
        categoryObjectifyDao.update(categoryModel);
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Category %s Updated", categoryModel.getName()))
                .build();
    }

    @Override
    public ResponseModel getAll() {
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(categoryObjectifyDao.read())
                .build();
    }

    @Override
    public ResponseModel delete(Long id) throws IllegalAccessException, InstantiationException {
        CategoryModel categoryModel = categoryObjectifyDao.read(id);
        if (categoryModel != null){
            categoryObjectifyDao.delete(categoryModel.getId());
            return ResponseModel.builder()
                    .status(ResponseModel.SUCCESS_STATUS)
                    .message(String.format("Category #%s Deleted", categoryModel.getName()))
                    .build();
        } else {
            return ResponseModel.builder()
                    .status(ResponseModel.FAIL_STATUS)
                    .message(String.format("Category #%d Not Found", id))
                    .build();
        }
    }
}
