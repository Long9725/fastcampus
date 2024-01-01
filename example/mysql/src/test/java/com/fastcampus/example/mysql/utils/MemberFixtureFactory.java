package com.fastcampus.example.mysql.utils;

import com.fastcampus.mysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {
    static  public Member create() {
        EasyRandomParameters params = new EasyRandomParameters();
        return new EasyRandom(params).nextObject(Member.class);
    }
    static public Member create(Long seed) {
        EasyRandomParameters params = new EasyRandomParameters()
                .seed(seed);
        return new EasyRandom(params).nextObject(Member.class);
    }
}
