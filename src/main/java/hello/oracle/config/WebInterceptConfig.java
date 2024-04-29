package hello.oracle.config;

import hello.oracle.intercept.RequiredLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebInterceptConfig implements WebMvcConfigurer {

  private final RequiredLoginInterceptor requiredLoginInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    // 로그인이 필요한 접근을 막는 인터셉터
    registry.addInterceptor(requiredLoginInterceptor)
            .addPathPatterns("/user/mypage.do")
            .addPathPatterns("/user/modifyUser.form")
            .addPathPatterns("/plan/write.form");

  }

}