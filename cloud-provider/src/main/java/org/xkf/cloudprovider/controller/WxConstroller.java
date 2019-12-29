/**
 * FileName: WxConstroller
 * Author:   13235
 * Date:     2019/10/25 23:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.xkf.cloudprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author 13235
 * @create 2019/10/25
 * @since 1.0.0
 */
@RequestMapping("/wx")
@RestController
@Slf4j
public class WxConstroller {

    public static final String APP_ID = "wx159067bdddf98d81";
    public static final String APP_SCRET = "93bec84be73189dcc3b2299277ad50ab";
    public static final String TOKEN = "wxtest";


    @GetMapping("/checkUser")
    public boolean testUser(HttpServletRequest request, HttpServletResponse response) throws
            UnsupportedEncodingException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String[] strings = new String[]{signature, timestamp, nonce};
        StringBuilder sb = new StringBuilder();
        int len = strings.length;
        for (int i = 0; i < len; i++) {
            sb.append(strings[i]);
        }
        String sign = DigestUtils.md5DigestAsHex(sb.toString().getBytes("utf-8"));
        if (Objects.equals(signature, sign)) {
            return true;
        }
        return false;
    }


}
