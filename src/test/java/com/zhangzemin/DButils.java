package com.zhangzemin;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

public class DButils {
    public static SimpleDataSource getDatabaseConnect(){
        // 创建一个数据源
        SimpleDataSource dataSource = new SimpleDataSource();
        try {
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/nutz?useSSL=false&serverTimezone=GMT%2B8&characterEncoding=utf8&rewriteBatchedStatements=true");//数据库地址
        dataSource.setUsername("root");//数据库帐号
        dataSource.setPassword("root");//数据库密码

        // 创建一个NutDao实例,在真实项目中, NutDao通常由ioc托管, 使用注入的方式获得.
        Dao dao = new NutDao(dataSource);
        return dataSource;
    }
}
