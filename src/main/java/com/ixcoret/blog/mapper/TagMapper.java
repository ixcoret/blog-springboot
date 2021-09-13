package com.ixcoret.blog.mapper;

import com.ixcoret.blog.entity.Tag;
import com.ixcoret.blog.vo.TagBackVO;
import com.ixcoret.blog.vo.TagOptionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/6/30 10:11
 */
@Repository
public interface TagMapper {
    List<TagOptionVO> listTagOptions();

    void saveBatch(List<Tag> tags);

    Integer countTags();

    List<TagBackVO> listBackTags(int index, int pageSize);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    Tag selectByName(String name);

    void update(Tag tag);

    void save(Tag tag);

    List<Tag> listTagsInTagNameList(List<String> tagNameList);
}
