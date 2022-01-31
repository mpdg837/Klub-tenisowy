package com.example.KlubTenisowy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import
org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/", "/index").permitAll()
		.antMatchers("/resources/static", "/static/**", "/webjars/**").permitAll()
		.antMatchers("/nowa_rezerwacja","/error","/main").hasAnyRole("ADMIN","USER")
		.antMatchers("/zamow_pilke","/zamow_rakiete","/biura","/pracownicy","/blad_edytuj_pracownik","/dodaj_wyplate","/edytuj_biuro",
				"/edytuj_klienta_grupowego","/edytuj_klienta_indywidualnego","/edytuj_kort","/edytuj_pilke","/edytuj_pracownik","/edytuj_rakiete",
				"/klienci_grupowi","/klienci_indywidualni","/korty","/nowa_pilka","/nowa_rakieta","/nowe_biuro","/nowy_klient_grupowy",
				"/nowy_klient_indywidualny","/nowy_kort","/nowy_pracownik","/pilki","/rakiety","/rezerwacje","/szczegoly_klienta_grupowego","/szczegoly_klienta_indywidualnego",
				"/szczegoly_pracownik","/szczegoly_wyplata","/usun_biuro","/usun_klienta_grupowego","/usun_klienta_indywidualnego","/usun_kort","/usun_pilke"
				,"/usun_pracownik","/usun_rakiete","/usun_rezerwacje","/usun_wyplaty","/usun_wypozyczenie","/wyplaty","/wypozyczenia").hasRole("ADMIN")
		.antMatchers("/biura_user", "/korty_user", "/pilki_user","/rakiety_user","/rezerwacje_user","/wypozyczenia_user","odmowa_dostepu","/zamow_pilke_user","/zamow_rakiete_user").hasRole("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/index")
		.permitAll();
	}

   
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user")
		.password("user")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN");
}
	@Bean
	public PasswordEncoder getPasswordEncoder() { 
		return NoOpPasswordEncoder.getInstance();
	}
}