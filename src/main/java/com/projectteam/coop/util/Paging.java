package com.projectteam.coop.util;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Paging {

    private final static int unit = 10;

    private int startPage = 1;
    private int endPage;
    //페이징 표시 단위 추가필요

    public int calculateTotalPage(int totalCount) {
        this.endPage = (int) Math.ceil((totalCount * 1.0) / unit);
        if (this.endPage == 0) {
            this.endPage = 1;
        }
        return endPage;
    }

    public static int calculateStartOffset(int page) {
        return (page - 1) * unit;
    }

    public static int calculateLastOffset(int page) {
        return calculateStartOffset(page) + unit;
    }

}
