package com.fastcampus.mysql.applicaiton.controller;

import com.fastcampus.mysql.domain.member.dto.MemberDto;
import com.fastcampus.mysql.domain.member.dto.MemberNicknameHistoryDto;
import com.fastcampus.mysql.domain.member.dto.RegisterMemberCommand;
import com.fastcampus.mysql.domain.member.entity.Member;
import com.fastcampus.mysql.domain.member.service.MemberReadService;
import com.fastcampus.mysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;

    @PostMapping("")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        return memberWriteService.create(command);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PostMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{id}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long id) {
        return memberReadService.getNicknameHistories(id);
    }
}
