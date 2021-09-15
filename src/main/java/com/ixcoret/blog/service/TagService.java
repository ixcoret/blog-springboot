package com.ixcoret.blog.service;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.dto.ConditionDTO;
import com.ixcoret.blog.dto.TagDTO;
import com.ixcoret.blog.vo.TagBackVO;
import com.ixcoret.blog.vo.TagOptionVO;

import java.util.List;

/**
 * @author ixcoret
 * @createTime 2021/7/5 0:11
 */
public interface TagService {
    List<TagOptionVO> listTagOptions();

    Page<TagBackVO> listBackTags(ConditionDTO conditionDTO);

    void deleteById(Integer id);

    void deleteBatch(List<Integer> ids);

    void update(TagDTO tagDTO);

    void save(TagDTO tagDTO);
}
