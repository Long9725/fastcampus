package com.fastcampus.mysql.domain.post.service;

import com.fastcampus.mysql.domain.post.dto.PostCommand;
import com.fastcampus.mysql.domain.post.entity.Post;
import com.fastcampus.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    final private PostRepository postRepository;

    public  Long create (PostCommand command) {
        Post post = Post
                .builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();
        return postRepository.save(post).getId();
    }
}
