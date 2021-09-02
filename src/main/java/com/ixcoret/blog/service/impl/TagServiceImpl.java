package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.mapper.TagMapper;
import com.ixcoret.blog.pojo.entity.Tag;
import com.ixcoret.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/5 0:12
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> list() {
        return tagMapper.list();
    }
}
