package com.zhangzemin;

import com.zhangzemin.domin.Person;
import org.junit.Test;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * 自定义sql
 * @author zhangzemin
 * @date 2020/2/21 11:20
 */
public class Test4 {
    private Dao dao = new NutDao(DButils.getDatabaseConnect());

    /**
     * 回调的用处
     */
    @Test
    public void test1(){
        Sql sql = Sqls.create("SELECT * FROM t_person WHERE name = @name");
        sql.params().set("name","民酱");
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<Person> list = new LinkedList<Person>();
                while (resultSet.next()){
                    try {
                        Person p = new Person();
//                        p.setId(Integer.parseInt(resultSet.getString("id")));
                        p.setName(resultSet.getString("name"));
                        p.setAge(Integer.parseInt(resultSet.getString("age")));
                        p.setGender(resultSet.getString("gender"));
                        p.setEmail(resultSet.getString("email"));
                        p.setCreateDate(new SimpleDateFormat("yyy-MM-dd").parse(resultSet.getString("createDate")));
                        list.add(p);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                return list;
            }
        });
        dao.execute(sql);
        List<Person> list = sql.getList(Person.class);
        for(Person p : list){
            System.err.println(p);
        }
    }


    /**
     * 获取一个列表的回调
     */
    @Test
    public void test2(){
        Sql sql = Sqls.create("SELECT * FROM t_person");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Person.class));
        dao.execute(sql);
        List<Person> list = sql.getList(Person.class);
        for(Person p : list){
            System.err.println(p);
        }
    }


    /**
     * 批量执行
     */
    @Test
    public void test3(){
        Sql sql = Sqls.create("UPDATE t_person SET name=@name WHERE id=@id");
        sql.params().set("name","小美");
        sql.params().set("id",6);
        sql.addBatch();
        sql.params().set("name","小美");
        sql.params().set("id",7);
        dao.execute(sql);
    }

    /**
     * 加载 SQL 文件
     */
    @Test
    public void test4(){
        ((NutDao)dao).setSqlManager(new FileSqlManager("sqls/person.sql"));
        System.err.println(dao.sqls().count());
        Sql sql = Sqls.create(dao.sqls().get("queryAll"));
        sql.setCondition(Cnd.where("id",">",4));
        sql.setCallback(new SqlCallback() {
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<Person> list = new LinkedList<Person>();
                while(resultSet.next()){
                    try {
                        Person p = new Person();
//                        p.setId(Integer.parseInt(resultSet.getString("id")));
                        p.setName(resultSet.getString("name"));
//                        p.setId(Integer.parseInt(resultSet.getString("id")));
                        p.setName(resultSet.getString("name"));
                        p.setAge(Integer.parseInt(resultSet.getString("age")));
                        p.setGender(resultSet.getString("gender"));
                        p.setEmail(resultSet.getString("email"));
                        p.setCreateDate(new SimpleDateFormat("yyy-MM-dd").parse(resultSet.getString("createDate")));
                        list.add(p);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }
        });
        dao.execute(sql);
        List<Person> list = sql.getList(Person.class);
        for(Person p : list){
            System.err.println(p);
        }
    }


}
