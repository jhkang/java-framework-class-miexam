package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jhkang on 4/22/16.
 */
@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(new JejuConnectionMaker());
    }
}
