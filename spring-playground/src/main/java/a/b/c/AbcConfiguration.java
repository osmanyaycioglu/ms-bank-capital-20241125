package a.b.c;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbcConfiguration {

    @Bean
    public AbcBean abcBean(){
        return new AbcBean();
    }

}
