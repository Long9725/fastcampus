package com.fastcampus.mysql.domain.member.service;

import com.fastcampus.mysql.domain.member.dto.MemberDto;
import com.fastcampus.mysql.domain.member.dto.RegisterMemberCommand;
import com.fastcampus.mysql.domain.member.entity.Member;
import com.fastcampus.mysql.domain.member.entity.MemberNicknameHistory;
import com.fastcampus.mysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.fastcampus.mysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    public MemberDto create(RegisterMemberCommand command) {
        Member member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        return toDto(memberRepository.save(member));
    }

    public void changeNickname(Long memberId, String nickname) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        Member savedMember = memberRepository.save(member);
        saveMemberNicknameHistory(savedMember);
    }

    private void saveMemberNicknameHistory(Member member) {
        MemberNicknameHistory memberNicknameHistory = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(memberNicknameHistory);
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}
