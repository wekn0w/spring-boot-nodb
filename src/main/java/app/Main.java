package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "app")//todo разобраться в неободимости указывать scan(чтобы конфиг видел здесь)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
