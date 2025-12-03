package com.empleados.patrones_diseno.config;

import com.empleados.patrones_diseno.domain.observer.EmailNotificationObserver;
import com.empleados.patrones_diseno.domain.observer.LoggingObserver;
import com.empleados.patrones_diseno.domain.observer.SalarySubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public SalarySubject salarySubject(
            EmailNotificationObserver emailObserver,
            LoggingObserver loggingObserver) {
        
        SalarySubject subject = new SalarySubject();
        subject.addObserver(emailObserver);
        subject.addObserver(loggingObserver);
        return subject;
    }
}