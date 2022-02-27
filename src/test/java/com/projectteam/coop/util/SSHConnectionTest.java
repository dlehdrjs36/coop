package com.projectteam.coop.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class SSHConnectionTest {

    @Test
    @DisplayName("properties 값 읽기 성공")
    void readProperties_Pass() {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        Properties sshConfig = new Properties();
        //when
        InputStream is =  classLoader.getResourceAsStream("props/system/dev.properties");
        //then
        assertDoesNotThrow(() -> sshConfig.load(is));
        assertEquals("3306", sshConfig.getProperty("ssh.bastionPort"));
        assertEquals("opc", sshConfig.getProperty("ssh.user"));
    }

    @Test
    @DisplayName("properties 값 읽기 실패")
    void readProperties_Fail() {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        Properties sshConfig = new Properties();
        //when
        InputStream is =  classLoader.getResourceAsStream("props/system/dev.properties12391239"); //경로 미일치
        //then
        assertThrows(NullPointerException.class, () -> sshConfig.load(is));
    }

    @Test
    @DisplayName("SSH 연결, 터널링 성공")
    void sshConnection_Pass() {
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        Properties sshConfig = new Properties();
        InputStream is =  classLoader.getResourceAsStream("props/system/dev.properties"); //properties 파일 조회
        assertDoesNotThrow(() -> sshConfig.load(is));

        String S_PATH_FILE_PRIVATE_KEY = sshConfig.getProperty("ssh.privateKeyPath");
        String S_PATH_FILE_KNOWN_HOSTS = sshConfig.getProperty("ssh.knownHostsPath");
        String S_PASS_PHRASE = sshConfig.getProperty("ssh.privateKeyPassword");
        String SSH_USER = sshConfig.getProperty("ssh.user");
        String SSH_REMOTE_SERVER = sshConfig.getProperty("ssh.remoteServer");
        String BASTION_SERVER = sshConfig.getProperty("ssh.bastionServer");
        String MYSQL_REMOTE_SERVER = sshConfig.getProperty("ssh.mysqlRemoteServer");
        int SSH_REMOTE_PORT = Integer.parseInt(sshConfig.getProperty("ssh.remotePort"));
        int BASTION_PORT = Integer.parseInt(sshConfig.getProperty("ssh.bastionPort"));
        int MYSQL_REMOTE_PORT = Integer.parseInt(sshConfig.getProperty("ssh.mysqlRemotePort"));

        JSch jsch = new JSch();
        Session session = null;
        int connectionPort = 0;
        //when
        try {
            jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
            jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());
            session = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
            session.connect(); //ssh connection established!
            connectionPort = session.setPortForwardingL(BASTION_SERVER, BASTION_PORT, MYSQL_REMOTE_SERVER, MYSQL_REMOTE_PORT); //SSH 터널링
        } catch (JSchException e) {
            assertDoesNotThrow(() -> e);
        } finally {
            assertNotNull(session);
            session.disconnect();
        }
        //then
        assertEquals(BASTION_PORT, connectionPort);
        assertFalse(session.isConnected());
    }
}