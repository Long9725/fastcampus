package com.fastcampus.mysql.domain.post.service;

import com.fastcampus.mysql.domain.post.dto.DailyPostCount;
import com.fastcampus.mysql.domain.post.dto.DailyPostCountRequest;
import com.fastcampus.mysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {
    final private PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postRepository.groupByCreatedDate(request);
    }
}
