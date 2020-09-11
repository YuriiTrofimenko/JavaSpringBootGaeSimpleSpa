package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces;

//import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.ProductFilterModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;
//import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductSearchModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductSearchModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;

public interface IProductService {
    ResponseModel create(ProductModel productModel, Long userId) throws Exception;
    ResponseModel update(ProductModel productModel) throws IllegalAccessException, InstantiationException;
    ResponseModel getAll();
    ResponseModel delete(Long id) throws IllegalAccessException, InstantiationException;
    ResponseModel search(ProductSearchModel searchModel);
    ResponseModel getProductsPriceBounds();
}
