package kz.iitu.itse1909r.mukhitdinov;

import kz.iitu.itse1909r.mukhitdinov.core.entity.AppUser;
import kz.iitu.itse1909r.mukhitdinov.core.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"kz.iitu.itse1909r.mukhitdinov.core.entity"})
public class StockManagementSystemApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StockManagementSystemApplication.class, args);
        AppUserRepository userRepository = context.getBean(AppUserRepository.class);
        System.out.println(userRepository.findAll());
        AppUser u = userRepository.findById(4L);
        System.out.println(u);
    }
}
