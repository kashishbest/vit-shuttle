package com.vit.service.impl;

import com.vit.model.Test;
import com.vit.service.TestManager;
import com.vit.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;

/**
 * Created by kashishsinghal on 13/03/16.
 */
@Service("testManager")
@WebService(serviceName = "testService", endpointInterface = "com.vit.service.TestService")
@Transactional
public class TestManagerImpl extends GenericManagerImpl<Test,String> implements TestManager,TestService {
    @Override
    public String print() {
        System.out.println("Yay");
        return "Hello World";
    }
}
