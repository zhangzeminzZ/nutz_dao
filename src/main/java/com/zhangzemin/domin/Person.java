package com.zhangzemin.domin;

import org.nutz.dao.entity.annotation.*;

import java.util.Date;

/**
 * 实体类Person
 */

@Table("t_person")
public class Person {
    @Name
    @Prev(els=@EL("uuid(32)"))
    private String id;
    @Column
    private String name;
    @Column
    private Integer age;
    @Column
    private String gender;
    @Column
    private String email;
    @Column
    private Date createDate;
//    @Column(hump=true)


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
