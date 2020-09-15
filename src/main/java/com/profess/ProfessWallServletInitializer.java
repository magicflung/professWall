package com.profess;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author flunggg
 * @date 2020/9/13 13:37
 * @Email: chaste86@163.com
 */
public class ProfessWallServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProfessWallApplication.class);
    }
}
