### SpringBoot 学习笔记
#### SpringBoot的优点
- 使用SpringBoot轻松地创建独立的、生产级的、基于 Spring 且能直接运行的应用程序。这意味着只需极少的配置，就可以快速获得一个正常运行的Spring应用程序。这些极少的配置采用了注释的形式，所以没有XML
- 依赖管理很方便
- 使部署变简单

#### YAML配置
[官网](http://www.yaml.org)
[中文参考](http://www.ruanyifeng.com/blog/2016/07/yaml.html)

#### 配置方式选择
配置文件yml和properties都支持配置；
- 如果说，我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value；
- 如果说，我们专门编写了一个javaBean来和配置文件进行映射，我们就直接使用@ConfigurationProperties；

<table>
  <tr>
    <th></th>
    <th>@ConfigurationProperties</th>
    <th>@Value</th>
  </tr>
  <tr>
    <td>功能</td>
    <td>批量注入配置文件中的属性</td>
    <td>一个个指定</td>
  </tr>
  <tr>
    <td>松散绑定（松散语法）</td>
    <td>支持</td>
    <td>不支持</td>
  </tr>
  <tr>
    <td>SpEL</td>
    <td>不支持</td>
    <td>支持</td>
  </tr>
  <tr>
    <td>JSR303数据校验</td>
    <td>支持</td>
    <td>不支持</td>
  </tr>
  <tr>
      <td>复杂类型封装</td>
      <td>支持</td>
      <td>不支持</td>
  </tr>
</table>

#### @PropertySource @ImportResource @Bean
- @PropertySource：加载指定的配置文件
- @ImportResource：导入Spring的配置文件，让配置文件里面的内容生效；
  想让Spring的配置文件生效，可将@ImportResource标注在一个配置类上
- @Bean 通过注解的方式将bean纳入spring容器管理,可以不再编写xml文件
```java
//@ImportResource(locations = {"classpath*:spring/config/local/appcontext-bean.xml"})
@Configuration
public class Config {

    @Bean
    public UserService userService(){
        return new UserService();
    }
}
```

#### 日志框架使用log4j2
Spring Boot在所有内部日志中使用Commons Logging，如果需要使用log4j2,需要修改相应依赖
```pom
    //去除spring-boot-starter-logging
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
      </exclusions>
    </dependency>


    //引入spring-boot-starter-log4j2
    <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
```