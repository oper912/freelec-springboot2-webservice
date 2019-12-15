package com.jojoldu.book.springboot.domain.posts;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  @After
  public void cleanup() {
    postsRepository.deleteAll();
  }

  @Test
  public void 게시글저장_불러오기() {
    //given
    String title = "테스트 게시글";
    String content = "테스트 본문";

    postsRepository.save(Posts.builder()
                              .title(title)
                              .content(content)
                              .author("shkwon@rsupport.com")
                              .build());

    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }

  @Test
  public void BaseTimeEntity_등록() {
    //given
    LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
    postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

    //when
    List<Posts> postsList = postsRepository.findAll();

    //then
    Posts posts = postsList.get(0);

    log.info(">>>>>>>>>>>>>>>>>> createdDate" + posts.getCreatedDt() + ", modifiedDate = " + posts.getModifiedDt());

    assertThat(posts.getCreatedDt()).isAfter(now);
    assertThat(posts.getModifiedDt()).isAfter(now);
  }
}