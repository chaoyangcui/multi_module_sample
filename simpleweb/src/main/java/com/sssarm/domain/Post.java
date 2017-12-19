package com.sssarm.domain;

import java.util.ArrayList;

/**
 * Created by cuiguiyang on 2017/5/29 10:22.
 * Desc:
 */
public class Post {
    private int id;
    private String subject;
    private String body;
    private Author author;
    private ArrayList<Tag> tags;
}
