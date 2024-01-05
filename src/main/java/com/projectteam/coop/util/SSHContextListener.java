//package com.projectteam.coop.util;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//@WebListener
//public class SSHContextListener implements ServletContextListener {
//
//    private SSHConnection conexionssh;
//
//    public SSHContextListener() {
//        super();
//    }
//
//    //앱이 시작되면 SSH 연결 생성.
//    public void contextInitialized(ServletContextEvent arg0) {
////        System.out.println("Context initialized ... !");
////        ClassLoader classLoader = getClass().getClassLoader();
////        Properties sshConfig = new Properties();
////
////        try (InputStream is =  classLoader.getResourceAsStream("props/system/dev.properties")) {
////            sshConfig.load(is);
////            conexionssh = new SSHConnection(sshConfig);
////
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//
//    //앱이 종료되면 SSH 연결 종료.
//    public void contextDestroyed(ServletContextEvent arg0) {
//        System.out.println("Context destroyed ... !");
//        conexionssh.closeSSH(); // disconnect
//    }
//
//}