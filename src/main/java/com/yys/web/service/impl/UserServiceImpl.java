package com.yys.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yys.web.entity.User;
import com.yys.web.service.UserService;
import com.yys.web.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 28142
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-08-26 17:01:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}




