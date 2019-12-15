package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 프로젝트의 메인 클래스
 * @SpringBootApplication 으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
 * 특히 @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
 **/

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    // 스프링부트의 내장 was를 실행한다.
    SpringApplication.run(Application.class, args);
  }
}
