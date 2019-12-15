package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsSaveRequestDto requestDto) {
    Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id:" + id));
    posts.update(requestDto.getTitle(), requestDto.getContent());
    return id;
  }

  public PostsResponseDto findById(Long id) {
    Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id:" + id));
    return new PostsResponseDto(posts);
  }

  /**
   * 트랙재션 범위는 유지하되 조회 기능만 남겨두어 조회 속도가 개선되기 때문에
   * 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천.
   */
  @Transactional(readOnly = true)
  public List<PostsListResponseDto> findAllDesc() {
    return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
  }

  @Transactional
  public void delete(Long id) {
   Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id:" + id));
   postsRepository.delete(posts);
  }
}
