package com.projectteam.coop.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SecurityUtilTest {
    @Test
    public void sha256() {
        SecurityUtil securityUtil = new SecurityUtil();
        String password = "test123456";

        String encryptWithSalt = securityUtil.encryptSHA256(password, SecurityUtil.getSalt());
        String encryptWithoutSalt = securityUtil.encryptSHA256(password);
        String encryptWithSalt2 = securityUtil.encryptSHA256(password, SecurityUtil.getSalt());
        String encryptWithoutSalt2 = securityUtil.encryptSHA256(password);

        assertNotEquals(SecurityUtil.getSalt(), SecurityUtil.getSalt());
        assertNotEquals(encryptWithSalt, encryptWithSalt2, () -> "동일한 비밀번호라도 다른 해시 값을 가져야 합니다.");
        assertEquals(encryptWithoutSalt, encryptWithoutSalt2, () -> "동일한 비밀번호는 동일한 해시 값을 가져야 합니다.");
    }

}