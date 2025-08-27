package com.yys.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yys.web.entity.Tag;
import com.yys.web.service.TagService;
import com.yys.web.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 28142
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2025-08-26 17:01:36
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}




