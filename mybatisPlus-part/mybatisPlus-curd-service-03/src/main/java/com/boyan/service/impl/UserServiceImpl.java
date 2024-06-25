package com.boyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boyan.mapper.UserMapper;
import com.boyan.pojo.User;
import com.boyan.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

}
