package we.graduationProj.scheduler.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @EnableWebSecurity: 기본적인 웹 보안 활성화
 *
 * 기본적인 Security 설정을 위한 config class
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/h2-console/**"
                        ,"/favicon.ico"
                        ,"/error"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //http 요청에 제한을 설정한다.
                .antMatchers("/api/hello").permitAll() //해당 요청만 인증없이 허가
                .anyRequest().authenticated(); //나머지는 인증이 필요하다
    }
}
