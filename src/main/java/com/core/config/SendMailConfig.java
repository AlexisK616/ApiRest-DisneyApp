package com.core.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendMailConfig {
    
    @Value("${app.sendgrid.key}")
    private String API_KEY;
    
    @Bean
    public SendGrid getSendGrid(){
        return new SendGrid(API_KEY);
    }
}
