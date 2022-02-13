package ubb.soa.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@SpringBootApplication
class AppApplication

fun main(args: Array<String>) {
    runApplication<AppApplication>(*args)
}

@Configuration
class SecurityConfiguration: WebSecurityConfigurerAdapter(false) {

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests().anyRequest().permitAll()
    }
}
