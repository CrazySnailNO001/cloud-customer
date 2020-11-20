package com.xzh.customer.technical.juc.currentLimiting;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-06 10:35
 * @description：
 * @modified By：
 * @version:
 */
public class ResponseUtil {
    public static void addResponse(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert outputStream != null;
            outputStream.flush();
            outputStream.close();
        }
    }
}
