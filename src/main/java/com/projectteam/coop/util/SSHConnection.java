package com.projectteam.coop.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnection {

    private final static String S_PATH_FILE_PRIVATE_KEY = "";
    private final static String S_PATH_FILE_KNOWN_HOSTS = "";
    private final static String S_PASS_PHRASE = "";
    private final static String BASTION_SERVER = "";
    private final static int BASTION_PORT = 0; // 해당 서버에서 사용할 수 있는 포트 아무거나 입력
    private final static String SSH_USER = "";
    private final static String SSH_REMOTE_SERVER = "";
    private final static int SSH_REMOTE_PORT = 22;
    private final static String MYSQL_REMOTE_SERVER = "";
    private final static int MYSQL_REMOTE_PORT = 0;
    private Session session; //represents each ssh session
    private Channel channel;

    public SSHConnection() {
        JSch jsch;
        jsch = new JSch();
        try {
            jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
            jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());

            session = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
            session.connect(); //ssh connection established!
    /*
            Channel channel = session.openChannel("exec");  //채널접속
            ChannelExec channelExec = (ChannelExec) channel; //명령 전송 채널사용
            channelExec.setPty(true);
            channelExec.setCommand("ls -al >> 1.txt");
            channelExec.connect();
    */
            //리눅스 SSH 터널링
            //by security policy, you must connect through a fowarded port
            session.setPortForwardingL(BASTION_SERVER, BASTION_PORT, MYSQL_REMOTE_SERVER, MYSQL_REMOTE_PORT);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void closeSSH() {
        if(session != null) {
            session.disconnect();
        }
        if(channel != null) {
            channel.disconnect();
        }
    }

}
