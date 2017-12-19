package com.sssarm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * Created by cuiguiyang on 2017/3/11 13:12.
 * Desc:
 *
 */
@RestController
@RequestMapping("/api/")
public class FileExportController extends BaseController {

    public static final String filePath = "/Users/cuiguiyang/workspace/IdeaProjects/multimodule/core/src/main/java/note.txt";

    @Autowired
    Properties properties;

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "/";
        String javaPath = request.getServletContext().getRealPath(fileName);
        byte[] bytes = new byte[1024 * 10];
        System.out.println(javaPath);
        try {
            int read = getClass().getResource("/note.txt").openStream().read(bytes);
            System.out.println(read);

            fileName = properties.getProperty("system.name.en") + TXT_FILE_SUFFIX;
            response.setCharacterEncoding(UTF8);
            response.setHeader(CONTENT_DISPOSITION, String.format("attachment;filename=\"%s\"", URLEncoder.encode(fileName, UTF8)));
//            response.getOutputStream().write(Files.readAllBytes(Paths.get(filePath)));
            response.getOutputStream().write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "simple", method = RequestMethod.GET)
    public void simple(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding(UTF8);
            response.setHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(properties.getProperty("system.name.en"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
