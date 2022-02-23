package com.projectteam.coop.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class SSHConnection {

    private final String S_PATH_FILE_PRIVATE_KEY;
    private final String S_PATH_FILE_KNOWN_HOSTS;
    private final String S_PASS_PHRASE;
    private final String SSH_USER;
    private final String SSH_REMOTE_SERVER;
    private final int SSH_REMOTE_PORT;
    private final String BASTION_SERVER;
    private final int BASTION_PORT; // 해당 서버에서 사용할 수 있는 포트 아무거나 입력
    private final String MYSQL_REMOTE_SERVER;
    private final int MYSQL_REMOTE_PORT;
    private Session session; //represents each ssh session
    private Channel channel;

    public SSHConnection(Properties properties) {
        this.S_PATH_FILE_PRIVATE_KEY = properties.getProperty("ssh.privateKeyPath");
        this.S_PATH_FILE_KNOWN_HOSTS = properties.getProperty("ssh.knownHostsPath");
        this.S_PASS_PHRASE = properties.getProperty("ssh.privateKeyPassword");
        this.SSH_USER = properties.getProperty("ssh.user");
        this.SSH_REMOTE_SERVER = properties.getProperty("ssh.remoteServer");
        this.SSH_REMOTE_PORT = Integer.parseInt(properties.getProperty("ssh.remotePort"));
        //SSH tunnel
        this.BASTION_SERVER = properties.getProperty("ssh.bastionServer");
        this.BASTION_PORT = Integer.parseInt(properties.getProperty("ssh.bastionPort"));
        this.MYSQL_REMOTE_SERVER = properties.getProperty("ssh.mysqlRemoteServer");
        this.MYSQL_REMOTE_PORT = Integer.parseInt(properties.getProperty("ssh.mysqlRemotePort"));

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
