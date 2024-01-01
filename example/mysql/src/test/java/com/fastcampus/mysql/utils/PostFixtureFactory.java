package com.fastcampus.mysql.utils;

import com.fastcampus.mysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.function.Predicate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {

    static public EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        Predicate<Field> idPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));
        Predicate<Field> memberIdField = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));
        EasyRandomParameters params = new EasyRandomParameters()
                .excludeField(idPredicate)
                .dateRange(firstDate, lastDate)
                .randomize(memberIdField, () -> memberId);

        return new EasyRandom(params);
    }
}
