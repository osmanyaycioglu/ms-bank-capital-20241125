package org.training.microservice.springplayground;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Controller - MyController MyRestController
// @Repository -
// @Service -

@Configuration
public class MyConfigBeanConfiguration {

    @Bean
    public MyFirstBean method() {
        MyJavaObject myJavaObjectLoc = new MyJavaObject();
        MyFirstBean  myFirstBeanLoc  = new MyFirstBean(myJavaObjectLoc);

        return myFirstBeanLoc;
    }

    @Bean("myotherbean")
    public MyFirstBean xyz() {
        MyJavaObject myJavaObjectLoc = new MyJavaObject();
        MyFirstBean  myFirstBeanLoc  = new MyFirstBean(myJavaObjectLoc);
        return myFirstBeanLoc;
    }

}
