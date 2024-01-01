package com.fastcampus.mysql.applicaiton.usecase;

import com.fastcampus.mysql.domain.follow.service.FollowWriteService;
import com.fastcampus.mysql.domain.member.dto.MemberDto;
import com.fastcampus.mysql.domain.member.entity.Member;
import com.fastcampus.mysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsecase {
    final private MemberReadService memberReadService;

    final private FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        MemberDto fromMember = memberReadService.getMember(fromMemberId);
        MemberDto toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
