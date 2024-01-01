package com.fastcampus.mysql.domain.follow.repository;

import com.fastcampus.mysql.domain.follow.entity.Follow;
import com.fastcampus.mysql.domain.member.entity.Member;
import com.fastcampus.mysql.domain.member.entity.MemberNicknameHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class FollowRepository {
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    static final private String TABLE = "Follow";

    static final RowMapper<Follow> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Follow
            .builder()
            .id(resultSet.getLong("id"))
            .fromMemberId(resultSet.getLong("fromMemberId"))
            .toMemberId(resultSet.getLong("toMemberId"))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .build();

    public List<Follow> findAllByFromMemberId(Long memberId) {
        String sql = String.format("SELECT * FROM %s WHERE fromMemberId = :fromMemberId", TABLE);
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("fromMemberId", memberId);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }


    public Optional<Follow> findById(Long id) {
        String sql = String.format("SELECT * FROM %s WHERE id = :id", TABLE);
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        RowMapper<Follow> rowMapper = (ResultSet resultSet, int rowNum) -> Follow
                .builder()
                .id(resultSet.getLong("id"))
                .fromMemberId(resultSet.getLong("fromMemberId"))
                .toMemberId(resultSet.getLong("toMemberId"))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(sql, params, rowMapper));
    }

    public Follow save(Follow follow) {
        if (follow.getId() == null) {
            return insert(follow);
        }
        throw new UnsupportedOperationException("Follow는 갱신을 지원하지 않습니다.");
    }

    private Follow insert(Follow follow) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(follow);
        Number id = simpleJdbcInsert.executeAndReturnKey(params);
        return Follow.builder()
                .id(id.longValue())
                .fromMemberId(follow.getFromMemberId())
                .toMemberId(follow.getToMemberId())
                .createdAt(follow.getCreatedAt())
                .build();
    }
}
