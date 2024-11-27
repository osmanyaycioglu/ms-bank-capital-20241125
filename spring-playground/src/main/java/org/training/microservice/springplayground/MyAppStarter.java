package org.training.microservice.springplayground;

import a.b.c.AbcBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyAppStarter implements ApplicationRunner {
    @Autowired
    @Qualifier("helloImpl")
    private  IHello hello;
    @Autowired
    @Qualifier("prof-hello")
    private  IHello profHello;

    @Autowired
    private AbcBean abcBean;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        System.out.println("Hello str : " + hello.hello());
        System.out.println("Prof Hello str : " + profHello.hello());
        abcBean.saySomething();
    }
}
