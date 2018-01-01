package com.macitepos.macitepos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;

@Configuration
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {


    private static final String SQL_LOGIN = "select p.username, p.password, p.status_pengguna as enabled"
            +" from pengguna p where username = ?";

    private static final String SQL_PERMISSION =
//            "select p.username, r.role as authority, ur.id_pengguna"
//            + " from pengguna p join user_role ur on p.id_pengguna = ur.id_pengguna"
//            + " join role r on ur.role_id = r.role_id"
//            + " where p.username = ? ";
            "select p.username, r.role as authority, p.id_pengguna"
                    + " from pengguna p "
                    + " join role r on p.id_pengguna = r.role_id"
                    + " where p.username = ? ";

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Qualifier("dataSource")
    @Autowired DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }
    @Override
    protected void configure(HttpSecurity http)throws Exception{

        http.authorizeRequests().antMatchers("/assets/**").permitAll();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/member").permitAll()
                .antMatchers("/manajer", "/customer","/edit","/m_header","/order","/product","reportPayment","/reportProduct","/user","/itemMapping")
                .hasAnyRole("MANAJER")
                .antMatchers("/kasir", "kasir-product", "/kasir-orders", "/kasir-customer","/c_header","/kasir-report","/kasir-user","/kasir-profile")
                .hasAnyRole("KASIR")
                .antMatchers("w_header", "/warehouse","/suplier","/warehouse-profile")
                .hasAnyRole("WAREHOUSE")
                .antMatchers("/member/create").hasAnyRole("KASIR","MANAJER","WAREHOUSE")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                ;

    }

}
