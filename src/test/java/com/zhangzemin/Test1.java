package com.zhangzemin;


import com.zhangzemin.domin.Person;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * dao接口的基本操作
 * @author zhangzemin
 * @date 2020/2/19 19:04
 */
public class Test1 {
    private Dao dao = new NutDao(DButils.getDatabaseConnect());
    @org.junit.Test
    public void test(){
        //创建表
        dao.create(Person.class,false);// false的含义是,如果表已经存在,就不要删除重建了.
//---------------------------------------------------------------------------------------------
        /*插入
        Insert
        一条 SQL 插入一条记录或者多条记录*/

        //插入一条
        /*Person person = new Person();
        person.setName("zhangzemin");
        person.setGender("男");
        person.setEmail("longzhenghe@163.com");
        person.setCreateDate(new Date());
        dao.insert(person);*/

        //插入多条
        /*Person person1 = new Person();
        person1.setName("zhangzemin");
        person1.setGender("男");
        person1.setEmail("zzm1@163.com");
        person1.setCreateDate(new Date());
        Person person2 = new Person();
        person2.setName("zhangzemin");
        person2.setGender("男");
        person2.setEmail("zzm2@163.com");
        person2.setCreateDate(new Date());
        Person[] person = {person1,person2};
        dao.insert(person);*/


        /*获取
        Fetch
        一条 SQL 获取一条记录*/
        //Person p = dao.fetch(Person.class,1);//根据id查询(如果你的实体声明了 @Id 字段, 数值型主键)
        //Person p = dao.fetch(Person.class,"民酱");//根据name查询(如果你的实体声明了 @Name 字段, 字符型主键,或者带唯一性索引的字段)


        /*删表
        Drop
        根据实体/表名称进行删表*/
        //dao.drop(Person.class);//全部删掉哦,没条件的,慎用!!



        /*更新
        Update
        一条 SQL 更新一条或者多条记录*/

        //更新一条
        /*Person p = dao.fetch(Person.class,3);
        p.setGender("女");
        dao.update(p,"^gender$");*/

        //更新多条
        /*List<Person> list = new ArrayList<Person>();
        Person p1 = dao.fetch(Person.class,3);
        p1.setGender("男");
        list.add(p1);
        Person p2 = dao.fetch(Person.class,4);
        p2.setGender("女");
        list.add(p2);
        dao.update(list,"^gender$");*/


        /*删除
        Delete
        一条 SQL 删除一条记录*/

        //直接删对象
        /*Person p = dao.fetch(Person.class,3);// Pet必须带@Id/@Name/@Pk中的一种或多种
        dao.delete(p);*/


        //根据名称删除 （如果你的实体声明了 @Name 字段）. 批量删除请用clear
        //dao.delete(Person.class,"奚酱");


        //根据 ID 删除 （如果你的实体声明了 @Id 字段）
        //dao.delete(Person.class,4);



        //直接删列表. 如果要按条件删,用dao.clear
        /*List<Person> list = new ArrayList<Person>();
        Person p1 = dao.fetch(Person.class,6);
        Person p2 = dao.fetch(Person.class,7);
        list.add(p1);
        list.add(p2);
        dao.delete(list);*/


        /*查询
        Query
        一条 SQL 根据条件获取多条记录*/

        //查询全部记录
        /*List<Person> list = dao.query(Person.class,null);
        for(Person p : list){
            System.err.println(p);
        }*/

        //按条件查询
        /*List<Person> list = dao.query(Person.class,Cnd.where("email","like","zzm1%"));
        Cnd.where("email","like","zzm1%");
        for(Person p:list){
            System.err.println(p);
        }*/

        //分页查询
        /*List<Person> list = dao.query(Person.class,Cnd.where("name","=","zhangzemin"),dao.createPager(1,1));
        for(Person p : list){
            System.err.println(p);
        }*/

        /*清除
        Clear
        一条 SQL 根据条件删除多条记录*/

        //清楚所有记录
        //dao.clear(Person.class); //还是那句,慎用



        //按条件清除
        //dao.clear(Person.class,Cnd.where("id", ">=", 8));


        //集合操作(func)

        //整数类型. 例如调用sum
        int func = dao.func(Person.class, "sum", "id");
        System.out.println(func);

        //其他类型
        dao.func2(Person.class, "min", "id");


    }
}
