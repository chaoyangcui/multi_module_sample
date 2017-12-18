package com.sssarm.controller;

import com.sssarm.entity.Company;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/14 13:05
 * Desc    Setting | Editor | File and Code Templates
 */
@Controller
@RequestMapping("/api/v1")
public class WechatAuthorizeController {

    @RequestMapping(value = "wechat.authorize", method = RequestMethod.GET)
    public ModelAndView authorize(
            HttpServletRequest request,
            @RequestParam(required = false) String code) {
        String url = request.getRequestURI();
        int authorizeType = 0;
        if (!StringUtils.isEmpty(code)) {
            url = url + "?code=" + code;
            authorizeType = 1;
        }

        switch (authorizeType) {
            case AuthorizeType.BASE:
                System.out.println("BASE");
                break;
            case AuthorizeType.USERINFO:
                System.out.println("USERINFO");
                break;
            default:
                System.out.println("BASE");
        }

        return new ModelAndView("redirect:" + url.replace("wechat.authorize", "wechat.redirect"));
    }

    @RequestMapping(value = "wechat.redirect", method = RequestMethod.GET)
    @ResponseBody
    public Company redirect(
            @RequestParam(required = false) String code) {
        return Company.builder()
                .address("天钥桥路333弄")
                .industry(code)
                .number(2703)
                .build();
    }

    enum AuthorizeType {
        ;
        public static final int USERINFO = 1;
        public static final int BASE = 0;
    }
}
