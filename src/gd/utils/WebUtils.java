package gd.utils;

import atg.servlet.ServletUtil;

/**
 * @author Alex Aksenov on 14/12/2016.
 **/
public class WebUtils {

    private WebUtils() {
    }

    public static String withRespCode(int code, String msg) {
        ServletUtil.getCurrentResponse().setStatus(code);
        return msg;
    }
}
