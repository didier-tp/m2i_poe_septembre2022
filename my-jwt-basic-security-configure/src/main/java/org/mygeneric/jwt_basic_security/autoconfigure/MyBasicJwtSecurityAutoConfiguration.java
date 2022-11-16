package org.mygeneric.jwt_basic_security.autoconfigure;

import org.mygeneric.jwt_basic_security.config.WebSecurityRecentConfig;
import org.mygeneric.jwt_basic_security.config.WithoutSecurityRecentConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/*
NB: cette classe est référencée dans le fichier
META-INF/spring.factories (de src/main/resources)
org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.mygeneric.jwt_basic_security.autoconfigure.MyBasicJwtSecurityAutoConfiguration
(ou bien = AutoConfig1,...,AutoConfigN
selon les spécifications suivantes:
https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-developing-auto-configuration.html
*/
@Configuration
@Import({ WebSecurityRecentConfig.class , WithoutSecurityRecentConfig.class})
@ComponentScan({"org.mygeneric.util" , "org.mygeneric.rest"})
public class MyBasicJwtSecurityAutoConfiguration {

}
