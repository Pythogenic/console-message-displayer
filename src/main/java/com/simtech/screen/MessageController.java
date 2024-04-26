package com.simtech.screen;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@RestController
public class MessageController {

    @Value("${server.port:8080}")
    private String serverPort;

    @PostConstruct
    public void init() {
        String message = String.format("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "Connect via http://%s:%s\n\n\n", getIpAddress(), serverPort);
        System.out.println(message);
    }

    @PostMapping("/submitMessage")
    public RedirectView handleMessage(@RequestParam("message") String message) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + message);

        return new RedirectView("/");
    }

    private String getIpAddress() {
        Enumeration e = null;
        try {
            e = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException ex) {
            return "localhost";
        }
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                String ipAddress = i.getHostAddress();
                if (isNetworkIp(ipAddress)) {
                    return ipAddress;
                }
            }
        }
        return "localhost";
    }

    private static boolean isNetworkIp(String ipAddress) {
        return ipAddress.startsWith("10.") || ipAddress.startsWith("192.") || ipAddress.startsWith("172.");
    }

}
