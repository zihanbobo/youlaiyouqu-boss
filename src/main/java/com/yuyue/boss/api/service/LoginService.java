package com.yuyue.boss.api.service;

import com.yuyue.boss.api.domain.UserVO;


public interface LoginService {

    UserVO getUser(String loginName, String password);

}
