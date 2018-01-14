package com.sssarm.controller;

import com.sssarm.util.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/5 14:44
 * Desc    Setting | Editor | File and Code Templates
 */
@Controller
@RequestMapping("/code")
public class JPEGController {

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public void getCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            BufferedImage image = new BufferedImage(68, 22, BufferedImage.TYPE_INT_BGR);
            Graphics graphics = image.getGraphics();
            Color c = new Color(170, 165, 126);
            graphics.setColor(c);
            graphics.fillRect(0, 0, 68, 22);
            // 验证码字符集合
            char[] ch = getCharacters(LanguageType.EN);
            Random r = new Random();
            int len = ch.length;
            int index;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; ++i) {
                index = r.nextInt(len);
                // 设置验证码字符随机颜色
                graphics.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
                // 画出对应随机的验证码字符
                graphics.drawString(ch[index] + "", (i * 15) + 3, 18);
                sb.append(ch[index]);
            }
            // 把验证码字符串放入Session
            request.getSession().setAttribute("piccode", sb.toString());
            response.setContentType("image/jpeg");
            // 在HttpServletResponse中写入验证码图片信息
            ImageIO.write(image, "JPG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符集
     * @return char[]
     */
    private char[] getCharacters(LanguageType languageType) {
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        switch (languageType) {
            case ZH_CN:
                ch = "江畔何人初见月江月何年初照人".toCharArray();
                break;
            default:
        }
        return ch;
    }

    @RequestMapping(value = "get2", method = RequestMethod.GET)
    public void codeGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        new RandomValidateCode().getRandcode(request, response);
    }

    /**
     * 字符类型:英文/中文
     */
    enum LanguageType {
        EN, ZH_CN
    }

}
