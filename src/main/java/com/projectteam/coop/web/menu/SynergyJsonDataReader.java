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
public class SynergyJsonDataReader {

    private final ResourceLoader resourceLoader = new DefaultResourceLoader();
    private final Resource resource = resourceLoader.getResource("classpath:/static/json/tft/");
    private final ObjectMapper mapper = new ObjectMapper();
    private JsonNode synergyListJsonObject = null;

    public JsonNode setSynergyListJsonObject(){
        // JSON 파일 읽기
        try {
            synergyListJsonObject = mapper.readTree(new File(resource.getURL().getPath()+"synergyList.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return synergyListJsonObject;
    }

    public JsonNode getSynergyListJsonObject(){
        if (synergyListJsonObject == null) {
            synergyListJsonObject = setSynergyListJsonObject();
        }
        return synergyListJsonObject;
    }
}