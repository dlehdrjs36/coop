package com.projectteam.coop.web.menu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;

@Configuration
public class ChampionJsonDataReader {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final Resource resource = resourceLoader.getResource("classpath:/static/json/tft/");
    private final ObjectMapper mapper = new ObjectMapper();
    private JsonNode championListJsonObject = null;

    public JsonNode setChampionListJsonObject(){
        // JSON 파일 읽기
        try {
            championListJsonObject = mapper.readTree(new File(resource.getURL().getPath()+"championList.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return championListJsonObject;
    }

    public JsonNode getChampionListJsonObject() {
        if (championListJsonObject == null) {
            championListJsonObject = setChampionListJsonObject();
        }
        return championListJsonObject;
    }
}