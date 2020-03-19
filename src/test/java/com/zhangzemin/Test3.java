package com.zhangzemin;

import com.zhangzemin.domin.Person;
import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * 分页查询
 * @author zhangzemin
 * @date 2020/2/21 10:03
 */
public class Test3 {
    private Dao dao = new NutDao(DButils.getDatabaseConnect());
    @Test
    public void test1(){
        Pager pager = dao.createPager(1,5);
        List<Person> list = dao.query(Person.class, null, pager);
        pager.setRecordCount(dao.count(Person.class));
        QueryResult queryResult = new QueryResult(list,pager);
        List<Person> list1 = (List<Person>) queryResult.getList();
        for(Person p : list1){
            System.err.println(p);
        }
    }
}
