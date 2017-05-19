package com.sssarm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sssarm.entity.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.sssarm.entity.Util.OBJECT_MAPPER;

/**
 * @Author Eric
 * @Date 2017/5/9 18:07
 * @Desc
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("name", "Eric.Cui");
        return "home";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public String json() {
        Company company = Company.builder().ifPublic(true).address("address").aDouble(111.111D).build();
        System.out.println(company.toString());
        String result = "";
        try {
            result = OBJECT_MAPPER.writeValueAsString(company);
            System.out.println(OBJECT_MAPPER.writeValueAsString(company));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
