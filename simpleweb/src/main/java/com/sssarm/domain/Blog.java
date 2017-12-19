package com.sssarm.domain;

import java.util.ArrayList;

/**
 * Created by cuiguiyang on 2017/5/29 10:21.
 * Desc:
 */
public class Blog {
    private int id;
    private String title;
    private Author author;
    private ArrayList<Post> posts;

    public Blog(Integer id) {
        this.id = id;
    }
}
