package com.sssarm.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cuiguiyang on 2017/5/14 19:37.
 * Desc:
 */
@Controller
@RequestMapping("/")
public class SecretController extends BaseController {

    @RequestMapping(value = "secret", method = RequestMethod.GET)
    @PreAuthorize("authenticated and hasPermission('secret')")
    public String home() {
        return "secret";
    }

}
