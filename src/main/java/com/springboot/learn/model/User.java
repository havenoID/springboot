package com.springboot.learn.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 生成bean,并将配置文件中user配置的每一个字段映射到bean中
 * prefix = "user" 标识从配置文件中取关于'user'的配置
 */
@Component
@ConfigurationProperties(prefix = "user")
public class User {

    //@Value("${user.id}")
    private int id;

    //@Value("${user.name}")
    private String name;

    private List<Car> cars;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
