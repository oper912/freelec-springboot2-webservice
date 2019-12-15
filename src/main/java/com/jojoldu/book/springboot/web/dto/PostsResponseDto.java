package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

  private Long id;
  private String title;
  private String content;
  private String author;

  // 생성자를 유동적으로 사용하기 위해서 entity를 파라미터로 받아서 사용
  public PostsResponseDto(Posts entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }
}
