package com.zhangzemin;

import com.zhangzemin.domin.Person;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.dao.util.cri.Static;
import org.nutz.json.Json;

import java.util.List;

/**
 * 复杂的SQL条件
 *
 * @author zhangzemin
 * @date 2020/2/20 19:04
 */
public class Test2 {

    private Dao dao = new NutDao(DButils.getDatabaseConnect());

    /**
     * 一个友好的工具类 -- Cnd
     */
    @Test
    public void test1() {
        //Cnd.where() 方法
        Condition c = Cnd.where("age", ">", "30").and("gender", "=", "男").desc("id");
        /*List<Person> list = dao.query(Person.class, c);
        for (Person p : list) {
            System.err.println(p);
        }*/
        //这个条件将生成 SQL:WHERE age>30 AND gender = '男' ORDER BY id DESC


        //你也可以嵌套表达式
        SqlExpressionGroup e1 = Cnd.exps("name", "like", "民%").and("age", ">=", "10");
        SqlExpressionGroup e2 = Cnd.exps("name", "like", "民%").and("age", ">=", "20");
        List<Person> list = dao.query(Person.class, Cnd.where(e1).or(e2));
        for (Person p : list) {
            System.err.println(p);
        }
        //这个条件将生成 SQL:WHERE (name LIKE '民%' AND age>='10') OR (name LIKE '民%' AND age>='20')
    }

    /**
     * 直接硬编码(不推荐)
     */
    @Test
    public void test2(){
        //最暴力的方法就是直接输出 WHERE 关键字后面的 SQL 代码了。比如查询一个 Person 对象
        /*List<Person> list = dao.query(Person.class, Cnd.wrap("name LIKE '民%' AND age>20"), null);
        for (Person p : list) {
            System.err.println(p);
        }*/

        //部分暴力,使用Static
        //筛选年龄(age)小于20,现金(cash字段)多于负债(due字段)的XX
        /*List<Person> list = dao.query(Person.class, Cnd.where("age", "<", 40).and(new Static("name like '奚%'")));
        for (Person p : list) {
            System.err.println(p);
        }*/

        List<String> strings = Json.fromJsonAsList(String.class, "[]");
        for(String s : strings){
            System.err.println(s);
        }
    }

    /**
     * 拼装更复杂的sql条件
     */
    @Test
    public void test3(){
        // 创建一个 Criteria 接口实例
        Criteria cri = Cnd.cri();

        // 组装条件
        if(true){
            cri.where().andGT("id",4);
        }
        if(true){
            cri.where().andIn("id",6,7);
        }
        System.err.println(cri.toString());
        List<Person> list = dao.query(Person.class,cri,null);
        for (Person p : list){
            System.err.println(p);
        }
    }
}
