package ru.test.SpringConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:defaultSpring.properties")
@ComponentScan("ru.test")

public class SpringConfig {
}
