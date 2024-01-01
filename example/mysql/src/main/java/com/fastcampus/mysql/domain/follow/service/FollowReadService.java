package com.fastcampus.mysql.domain.follow.service;

import com.fastcampus.mysql.domain.follow.entity.Follow;
import com.fastcampus.mysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    final private FollowRepository followRepository;

    public List<Follow> getFollowing(Long memberId) {
        return followRepository.findAllByFromMemberId(memberId);
    }
}
