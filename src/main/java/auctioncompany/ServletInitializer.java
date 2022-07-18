<<<<<<<< HEAD:src/main/java/auctioncompany/ServletInitializer.java
package auctioncompany;
========
package com.auctioncompany;
>>>>>>>> fa73735 (创建项目):src/main/java/com/auctioncompany/ServletInitializer.java

import com.auctioncompany.AuctionCompanyApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuctionCompanyApplication.class);
    }

}
