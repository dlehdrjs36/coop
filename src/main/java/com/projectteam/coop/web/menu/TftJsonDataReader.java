package com.projectteam.coop.web.menu;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 전략적팀전투(TFT)와 관련된 데이터를 가진 Json File을 읽어서 Json Object를 생성
 */
public interface TftJsonDataReader {
    JsonNode getTftJsonData();
}
