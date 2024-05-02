package com.projectteam.coop.domain.purchaselist.enums;

public enum PurchaseListStatus {
    ORDER("주문"), APPLY("적용"), UNAPPLY("미적용");

    private String description;

    PurchaseListStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
