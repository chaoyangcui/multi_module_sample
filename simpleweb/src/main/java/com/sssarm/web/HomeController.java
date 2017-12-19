package com.sssarm.web;

import com.sssarm.domain.Blog;
import com.sssarm.mapper.BlogMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cuiguiyang on 2017/5/14 19:37.
 * Desc:
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @Autowired
    BlogMapper blogMapper;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    @PreAuthorize("authenticated and hasPermission('secret')")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "blog/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Blog getBlog(@PathVariable int id) {
        Blog blog = blogMapper.findById(id);
        System.out.println(null == blog);
        return blog;
    }

}
