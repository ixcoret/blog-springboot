package com.ixcoret.blog.service.impl;

import com.ixcoret.blog.api.Page;
import com.ixcoret.blog.dto.Condition;
import com.ixcoret.blog.dto.TagDTO;
import com.ixcoret.blog.entity.Tag;
import com.ixcoret.blog.enums.ResultCodeEnum;
import com.ixcoret.blog.exception.BusinessException;
import com.ixcoret.blog.mapper.TagMapper;
import com.ixcoret.blog.service.TagService;
import com.ixcoret.blog.util.PageUtil;
import com.ixcoret.blog.vo.TagBackVO;
import com.ixcoret.blog.vo.TagOptionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<TagOptionVO> listTagOptions() {
        return tagMapper.listTagOptions();
    }

    @Override
    public Page<TagBackVO> listBackTags(Condition condition) {
        Integer total = tagMapper.countTags();
        if (total == 0) {
            return new Page<>();
        }
        Page<TagBackVO> page = new Page<>();
        page.setTotal(total);
        int index = PageUtil.startIndex(condition.getPageNum(), condition.getPageSize());
        List<TagBackVO> list = tagMapper.listBackTags(index, condition.getPageSize());
        page.setList(list);
        return page;
    }

    @Override
    public void deleteById(Integer id) {
        tagMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        tagMapper.deleteBatch(ids);
    }

    @Override
    public void update(TagDTO tagDTO) {
        Tag tag = tagMapper.selectByName(tagDTO.getTagName());
        if (tag != null) {
            throw new BusinessException(ResultCodeEnum.DATA_DUPLICATE.getCode(), "标签名已存在");
        }
        tag = new Tag();
        BeanUtils.copyProperties(tagDTO, tag);
        tagMapper.update(tag);
    }

    @Override
    public void save(TagDTO tagDTO) {
        Tag tag = tagMapper.selectByName(tagDTO.getTagName());
        if (tag != null) {
            throw new BusinessException(ResultCodeEnum.DATA_DUPLICATE.getCode(), "标签名已存在");
        }
        tag = new Tag();
        tag.setTagName(tagDTO.getTagName());
        tag.setCreateTime(LocalDateTime.now());
        tagMapper.save(tag);
    }
}
