package com.projectteam.coop.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@WebListener
@Slf4j
public class SSHContextListener implements ServletContextListener {

    private SSHConnection conexionssh;

    public SSHContextListener() {
        super();
    }

    //앱이 시작되면 SSH 연결 생성.
    public void contextInitialized(ServletContextEvent arg0) {
        log.info("Context initialized ... !");
        Yaml yaml = new Yaml();

        try (InputStream is =  new FileInputStream("../properties/coop_server.yml")) {
            Map<String, Object> load = yaml.load(is);
            Map<String, Object> sshMap = (Map<String, Object>) load.getOrDefault("ssh", null);
            conexionssh = new SSHConnection(sshMap);

        } catch (FileNotFoundException e) {
            log.error("{errorMsg}", e);
        } catch (IOException e) {
            log.error("{errorMsg}", e);
        }
    }

    //앱이 종료되면 SSH 연결 종료.
    public void contextDestroyed(ServletContextEvent arg0) {
        log.info("Context destroyed ... !");
        conexionssh.closeSSH(); // disconnect
    }

}