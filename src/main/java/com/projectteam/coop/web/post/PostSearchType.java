package com.projectteam.coop.web.post;

public enum PostSearchType {
    TITLE("제목"), CONTENT("내용"), TITLE_CONTENT("제목+내용"), CREATE_MEMBER("작성자");

    String description;

    PostSearchType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
