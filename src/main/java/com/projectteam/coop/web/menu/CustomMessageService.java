package com.projectteam.coop.web.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomMessageService {

    @Autowired
    MessageService messageService;

    @Autowired
    Tft10JsonDataReader tft10JsonDataReader;

    public boolean sendMyMessage() throws JSONException {
        DefaultMessageDto myMsg = new DefaultMessageDto();
        myMsg.setBtnTitle("자세히보기");
        myMsg.setWebUrl("https://m.booking.naver.com/booking/10/bizes/523085/items/4065350");
        myMsg.setMobileUrl("https://m.booking.naver.com/booking/10/bizes/523085/items/4065350");
        myMsg.setObjType("text");

        List<String> maimai = tft10JsonDataReader.maimai();
        myMsg.setText("[maimai 오전 예약가능 목록]\n" + maimai.toString());
        String accessToken = AuthService.authToken;

        return messageService.sendMessage(accessToken, myMsg);
    }
}
