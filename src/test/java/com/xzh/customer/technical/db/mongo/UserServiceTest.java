package com.xzh.customer.technical.db.mongo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private MongoUserService mongoUserService;

    @Test
    public void find_all_test() {
        List<MongoUser> all = mongoUserService.findAll();
        System.out.println(all);
    }


    @Test
    public void save_test() {

        MongoUser user = new MongoUser();
        user.setId(1L);
        user.setName("user001");
        user.setAge(12);
        user.setPassword("password");
        MongoUser save = mongoUserService.save(user);
        System.out.println(save);
    }

}