package com.vit.dao.hibernate;

import com.vit.dao.TestDao;
import com.vit.model.Test;
import org.springframework.stereotype.Repository;

/**
 * Created by kashishsinghal on 13/03/16.
 */
@Repository("testDao")
public class TestDaoHibernate extends GenericDaoHibernate<Test,String> implements TestDao {
    public TestDaoHibernate() {
        super(Test.class);
    }
}
