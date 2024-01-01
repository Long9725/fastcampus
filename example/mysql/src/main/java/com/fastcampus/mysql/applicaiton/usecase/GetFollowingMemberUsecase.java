package com.fastcampus.mysql.applicaiton.usecase;

import com.fastcampus.mysql.domain.follow.entity.Follow;
import com.fastcampus.mysql.domain.follow.service.FollowReadService;
import com.fastcampus.mysql.domain.member.dto.MemberDto;
import com.fastcampus.mysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMemberUsecase {
    final private MemberReadService memberReadService;

    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        List<Follow> followings = followReadService.getFollowing(memberId);
        return memberReadService.getMembers(followings.stream().map(Follow::getFromMemberId).toList());
    }
}
