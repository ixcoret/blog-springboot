package com.ixcoret.blog.mapper;

import com.ixcoret.blog.pojo.entity.Tag;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/30 10:11
 */
public interface TagMapper {
    List<Tag> list();

    void saveBatch(List<Tag> tags);
}
