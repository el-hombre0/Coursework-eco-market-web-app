package ru.coursework.eco_market.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.coursework.eco_market.entity.Role;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Для наличия возможнсти редактирования юзеров только у админа
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // Класс при старте приложения настривает систему авторизации
    @Autowired
    private DataSource dataSource; // Чтобы мог входить в БД и искать роли пользователя
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()// Включение авторизации
                    .antMatchers("/", "/registration", "/product*", "/cart", "/api/*").permitAll() // Доступ доступен всем
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())// Шифрование паролей, чтобы не хранились в вном виде
                .usersByUsernameQuery("select username, password, active from user where username=?") // Запрос - система ищет пользователя по его имени
                .authoritiesByUsernameQuery("select u.username, ur.roles from user u inner join user_role ur on u.id = ur.user_id where u.username=?"); // Запрос для получения списка пользователей с их ролями
                // Из таблицы user и присоединенной к ней таблице user_role через поля user.id и id выбираем поля username и и мя роли
    }
    @Override
    public void configure(WebSecurity web) {
        web.ignoring() // Доступ к оформлению страниц без авторизации
                .antMatchers(
                        "/css/**", "/fonts/**",
                        "/images/**");
    }
}
