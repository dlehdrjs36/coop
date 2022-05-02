package com.projectteam.coop.domain;

public enum ProductType {
    BACKGROUND("배경"), ICON("아이콘");

    private String description;

    ProductType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
