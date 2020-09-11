package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces;

import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.SubscriptionModel;

public interface ISubscriptionService {
    ResponseModel create(SubscriptionModel subscriptionModel);
    ResponseModel getByCategoryId(Long categoryId) throws Exception;
    ResponseModel delete(Long categoryId, Long subscriberId) throws Exception;
}
