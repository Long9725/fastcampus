package com.fastcampus.mysql.applicaiton.controller;

import com.fastcampus.mysql.domain.post.dto.DailyPostCount;
import com.fastcampus.mysql.domain.post.dto.DailyPostCountRequest;
import com.fastcampus.mysql.domain.post.dto.PostCommand;
import com.fastcampus.mysql.domain.post.service.PostReadService;
import com.fastcampus.mysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    final private PostWriteService postWriteService;

    final private PostReadService postReadService;

    @PostMapping("")
    public Long create(PostCommand command) {
        return postWriteService.create(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        return postReadService.getDailyPostCounts(request);
    }
}
