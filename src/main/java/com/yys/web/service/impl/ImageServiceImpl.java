package com.yys.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yys.web.entity.Image;
import com.yys.web.service.ImageService;
import com.yys.web.mapper.ImageMapper;
import org.springframework.stereotype.Service;

/**
* @author 28142
* @description 针对表【image(图片表)】的数据库操作Service实现
* @createDate 2025-08-27 15:41:14
*/
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

}




