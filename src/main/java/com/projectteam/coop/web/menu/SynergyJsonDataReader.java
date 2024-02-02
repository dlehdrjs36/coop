package com.projectteam.coop.web.menu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class SynergyJsonDataReader {

    private final ObjectMapper mapper = new ObjectMapper();
    private JsonNode synergyListJsonObject = null;

    public JsonNode setSynergyListJsonObject(){
        // JSON 파일 읽기
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("static/json/tft/synergyList.json")) {
            synergyListJsonObject = mapper.readTree(is);
        } catch (IOException e) {
            log.error("{errorMsg}", e);
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