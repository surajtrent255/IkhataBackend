package com.ishanitech.iaccountingrest.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class HostDetailsUtil {

    @Value("${server.port}")
    int port;

    private static final String HTTP_PREFIX = "http://";

    public String getHostUrl() {
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return HTTP_PREFIX + hostAddress + ":" + port + "/" ;
    }
}
