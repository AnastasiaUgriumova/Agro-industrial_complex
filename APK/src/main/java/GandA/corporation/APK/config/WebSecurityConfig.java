package GandA.corporation.APK.config;

import GandA.corporation.APK.security.MySimpleUrlAuthenticationSuccessHandler;
import GandA.corporation.APK.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/").anonymous()
                .antMatchers("/index").hasAnyAuthority( "CREATEREDITOR")
                .antMatchers("/registration").anonymous()
                .antMatchers( "/Land","/newLand","/saveLand","/editLand/{id}","/editLandSave/{id}","/deleteLand/{id}" ).hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/Employee","/newEmployee","/saveEmployee","/editEmployee/{id}","/editEmployeeSave/{id}","/deleteEmployee/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/PlannedProduction","/newPlannedProduction","/savePlannedProduction","/editPlannedProduction/{id}","/editPlannedProductionSave/{id}","/deletePlannedProduction/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/Product","/newProduct","/saveProduct","/editProduct/{id}","/editProductSave/{id}","/deleteProduct/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/Sale","/newSale","/saveSale","/editSale/{id}","/editSaleSave/{id}","/deleteSale/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/Procurement","/newProcurement","/saveProcurement","/editProcurement/{id}","/editProcurementSave/{id}","/deleteProcurement/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/Expenses","/newExpenses","/saveExpenses","/editExpenses/{id}","/editExpensesSave/{id}","/deleteExpenses/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/ProductionCosts","/newProductionCosts","/saveProductionCosts","/editProductionCosts/{id}","/editProductionCostsSave/{id}","/deleteProductionCosts/{id}").hasAnyAuthority("CREATEREDITOR")
                .antMatchers("/manager","/newCompany","/saveCompany","/editCompany/{id}","/editCompanySave{id}","/addUserToCompany/{id}",
                        "/addUserToCompanySave{idCompany}","/deleteUserToCompany/{id}","/deleteSelectUserToCompany","/deleteCompany/{id}").hasAuthority("MANAGER")
                .antMatchers("/admin","/editUser/{id}","/editUserSave{Userid}","/editUserPassword/{id}","/editUserPasswordSave{Userid}","/deleteUser/{id}").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/login?error=true")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")
        ;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(

                // статика
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/images/**"
        );
    }
}
