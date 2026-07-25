package com.projectteam.coop.web.menu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Configuration
public class ChampionTftJsonDataReader implements TftJsonDataReader {

    private final ObjectMapper mapper = new ObjectMapper();
    private static JsonNode championListJsonObject = null;

    @Override
    public JsonNode getTftJsonData() {
        if (championListJsonObject == null) {
            // JSON 파일 읽기
            String resourcePath = "static/json/tft/championList.json";
            try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
                championListJsonObject = mapper.readTree(is);
            } catch (IOException e) {
                log.error("{errorMsg}", e);
            }
            return championListJsonObject;
        }
        return ChampionTftJsonDataReader.championListJsonObject;
    }
}