package com.fastcampus.mysql.domain.member;

import com.fastcampus.mysql.utils.MemberFixtureFactory;
import com.fastcampus.mysql.domain.member.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {
    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    public void testChangeName() {
        Member member = MemberFixtureFactory.create();
        String expectedNickname = "pnu";

        member.changeNickname(expectedNickname);

        Assertions.assertEquals(expectedNickname, member.getNickname());
    }

    @DisplayName("회원은 닉네임은 10자를 초과할 수 없다.")
    @Test
    public void testChangeMaxLength() {
        Member member = MemberFixtureFactory.create();
        String overMaxLengthNickname = "pnupnupnupnu";

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLengthNickname)
        );
    }
}
