package com.fastcampus.mysql.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

// memberId, createdDate 를 기준으로 복합키 Index를 생성
// createdAt이 있는데 createdDate를 굳이 만드는 이유 => Index를 사용할 때 createdAt은 Index당 범위가 너무 좁음.
// 협업할 때 어떠한 Index를 생성해놨다는 소통을 어떻게 할 수 있을까? Entity class에 주석 or Docs?
@Getter
public class Post {
    final private Long id;

    final private Long memberId;

    final private String contents;

    final private LocalDate createdDate;

    final private LocalDateTime createdAt;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createdDate, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.contents = Objects.requireNonNull(contents);
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
