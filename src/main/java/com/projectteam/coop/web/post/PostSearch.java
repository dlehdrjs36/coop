package com.projectteam.coop.web.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearch {

    private String searchValue;
    private PostSearchType searchType;

}
