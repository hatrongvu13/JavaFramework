package com.hitex.evn.constant;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ApplicationCode {
    private static final String LANGUAGE_DEFAULT = "vn";
    private static final ApplicationCode instance = new ApplicationCode();
    private static final Map<Integer, String> msg = new HashMap<>();

    private static final String ISO_8859 = "ISO-8859-1";
    private static final String UTF8 = "UTF-8";
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;


    static {
        msg.put(SUCCESS, "Thành Công");
        msg.put(ERROR, "Lỗi");

    }

    public static ApplicationCode getInstance() {
        return instance;
    }


    public static String getProperty(int code, String language) {
        return msg.get(code);

    }

    public static String getMessage(int code) {
        return getMsg(code, LANGUAGE_DEFAULT);
    }

//    public static String getMessage(int code, String language) {
//        if (StringUtils.isEmpty(language)) {
//            language = LANGUAGE_DEFAULT;
//        }
//
//        return getMsg(code, language);
//    }


    private static String getMsg(int code, String language) {
        if (msg.containsKey(code)) {
            String message = getProperty(code, language);
            try {
                String msg;
                if (code == 0) {
                    msg = new String(message.getBytes(UTF8), StandardCharsets.UTF_8);
                } else {
                    msg = "[ERR_" + code + "] " + new String(message.getBytes(UTF8), StandardCharsets.UTF_8);
                }
                return msg;
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }

        return "";
    }
}
