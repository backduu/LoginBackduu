package com.example.helloauthlogin.config;

import com.example.helloauthlogin.DAO.UserDao;
import com.example.helloauthlogin.DTO.UserRegisterDTO;
import com.example.helloauthlogin.exception.LoginBackduuException;
import com.example.helloauthlogin.service.UserService;
import com.example.helloauthlogin.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable) // basic authentication filter 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/logout", "/error", "/signup").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 정적 자원에 대해서 인증을 하지 않도록 허가
                        .requestMatchers("/static/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/doLogin")
                        .defaultSuccessUrl("/")
                        .failureForwardUrl("/login")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(form->form
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                )
                // spring security6 부턴 람다 스타일로 authenticationManager 설정
                .authenticationProvider(authenticationProvider(userDetailsService, passwordEncoder));
        ;
        return http.build();
    }

    /*
    옛날이었으면 먼저 PasswordEncoder를 주입받고 아래와 같이 AuthenticationManager를 설정해서 커스텀 UserDetailsService와 PasswordEncoder를 연결하지만
    6 버전 이후부터는 deprecated된 아래 방식을 안쓰고 람다 스타일 AuthenticationManager 을 등록하고 authenticationProvider
    를 구현하여 내부적으로 UserDetailsService와 PasswordEncoder를 연결해준다.
         @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
                throws Exception {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService())
                    .passwordEncoder(passwordEncoder)
                    .and()
                    .build();
         }

    * */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }


    /*
        UserDetailsService 구현
     */
    @Bean
    public UserDetailsService userDetailsService() {

        return userId -> {
            UserRegisterDTO user = userService.findByUserId(userId);
            if(user == null){
                throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
                //throw new LoginBackduuException(ErrorCode.NO_DEVELOPER);
            }
            return user;
        };
    }

    /*
        passwordEncoder 구현
        : 해당 인터페이스는 사용자가 로그인 할 때 입력한 비밀번호와 db에 저장된 비밀번호를 비교할 때 쓴다.

        ... String encodedPassword = passwordEncoder.encode(plainTextPassword); 로 나중에 쓰인다.

        : Springboot는 기본적으로 DelegatingPasswordEncoder를 자동 설정해주지만, 명시적으로 userDetailsService나
          커스텀 인증 로직을 만들면 자동 설정이 무력화 된다.
          - 즉, encode()나 matches() 호출 시 NoSuchBeanDefinitionException 같은 오류가 날 수 있다.

        : 근데 이렇게 설정 파일 안에 정의한다면 한 번에 모아서 관리할 수 있고 직관적이지만 나중에 설정 클래스가 점점 커지는 단점이 있다.
        : PasswordEncoderConfig라고 만들어준 것은 관심사의 분리를 통해 유지보수가 쉽고 만일 큰 프로젝트일 경우 해당 방법이 다른 곳에서 의존성 주입을 쉽게
        받아 사용가능하기에 좋다!
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
