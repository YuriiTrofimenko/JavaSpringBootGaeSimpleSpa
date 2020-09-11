package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.CategoryObjectifyDao;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.SubscriptionObjectifyDao;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.SubscriptionModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.ICategoryService;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.ISubscriptionService;

@Service
public class SubscriptionObjectifyService implements ISubscriptionService {

    @Autowired
    private SubscriptionObjectifyDao subscriptionDao;

    @Override
    public ResponseModel create(SubscriptionModel subscriptionModel) {
        subscriptionDao.create(subscriptionModel);
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Subscription Created"))
                .build();
    }

    @Override
    public ResponseModel getByCategoryId(Long categoryId) throws Exception {
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(subscriptionDao.getSubscriberListByCategoryId(categoryId))
                .build();
    }

    @Override
    public ResponseModel delete(Long categoryId, Long subscriberId) throws Exception {
        SubscriptionModel subscriptionModel =
                subscriptionDao
                        .findSubscriberByCategoryIdAndSubscriberId(categoryId, subscriberId);
        if (subscriptionModel != null) {
            subscriptionDao.delete(subscriptionModel.getId());
            return ResponseModel.builder()
                    .status(ResponseModel.SUCCESS_STATUS)
                    .message(String.format("Subscription #%d Deleted", subscriptionModel.getId()))
                    .build();
        } else {
            return ResponseModel.builder()
                    .status(ResponseModel.FAIL_STATUS)
                    .message(String.format("Subscription #%d Not Found", subscriptionModel.getId()))
                    .build();
        }
    }
}
