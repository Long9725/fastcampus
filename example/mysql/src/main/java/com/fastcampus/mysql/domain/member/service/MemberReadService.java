package com.fastcampus.mysql.domain.member.service;

import com.fastcampus.mysql.domain.member.dto.MemberDto;
import com.fastcampus.mysql.domain.member.dto.MemberNicknameHistoryDto;
import com.fastcampus.mysql.domain.member.entity.Member;
import com.fastcampus.mysql.domain.member.entity.MemberNicknameHistory;
import com.fastcampus.mysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.fastcampus.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;

    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto getMember(Long id) {
        return toDto(memberRepository.findById(id).orElseThrow());
    }

    public List<MemberDto> getMembers(List<Long> ids) {
        List<Member> members = memberRepository.findAllByIdIn(ids);
        return members.stream().map(this::toDto).toList();
    }
    public List<MemberNicknameHistoryDto> getNicknameHistories(long memberId) {
        return memberNicknameHistoryRepository.findAllByMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }

    private MemberNicknameHistoryDto toDto(MemberNicknameHistory history) {
        return new MemberNicknameHistoryDto(history.getId(), history.getMemberId(), history.getNickname(), history.getCreatedAt());
    }
}

