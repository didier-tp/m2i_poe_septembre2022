package tp.appliSpring.core.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration(exclude = { org.joinfaces.autoconfigure.javaxfaces.JsfBeansAutoConfiguration.class})
@ComponentScan(basePackages = { "tp.appliSpring.core.service" })
@EnableJpaRepositories(basePackages = { "tp.appliSpring.core.dao" }) 
@EntityScan(basePackages = { "tp.appliSpring.core.entity"})
public class ConfigSuffisante {

}
