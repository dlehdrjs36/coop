package com.projectteam.coop.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SSHContextListener implements ServletContextListener {

    private SSHConnection conexionssh;

    public SSHContextListener() {
        super();
    }

    //앱이 시작되면 SSH 연결 생성.
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Context initialized ... !");
        try {
            conexionssh = new SSHConnection();
        } catch (Throwable e) {
            e.printStackTrace(); // error connecting SSH server
        }
    }

    //앱이 종료되면 SSH 연결 종료.
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Context destroyed ... !");
        conexionssh.closeSSH(); // disconnect
    }

}