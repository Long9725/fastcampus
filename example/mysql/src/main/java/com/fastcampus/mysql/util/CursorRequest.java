package com.fastcampus.mysql.util;

public record CursorRequest(
        Long key,
        int size
) {
    public boolean hasKey() {
        return key != null;
    }

    public CursorRequest next(Long key) {
        return new CursorRequest(key, size);
    }
}
