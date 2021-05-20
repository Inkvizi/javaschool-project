package github.marinets.atm.server;

import github.marinets.atm.common.ModelMapperConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ModelMapperConfiguration.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}