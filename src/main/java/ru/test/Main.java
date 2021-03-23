package ru.test;

import ru.test.SpringConfig.SpringConfig;
import ru.test.ThreadsDownloader.ThreadDownloader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        ThreadDownloader bean = context.getBean(ThreadDownloader.class);
        bean.threadDownloader();
    }
}