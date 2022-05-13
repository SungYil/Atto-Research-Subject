package com.atto.AttoSubject.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validate {
    public boolean validateHostIp(String ip){
        Pattern ipPattern=Pattern.compile("^(([01]?[0-9]?[0-9]|2([0-4][0-9]|5[0-5]))\\.){3}([01]?[0-9]?[0-9]|2([0-4][0-9]|5[0-5]))$");
        return ipPattern.matcher(ip).matches();
    }
}
