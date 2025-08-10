package com.mvc.web.service;

import com.mvc.web.bean.UserFormBean;
import com.mvc.web.constants.Constants;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserFormBean getUser(String name) {
        UserFormBean userFormBean = new UserFormBean();
        userFormBean.setUserId(name);
        userFormBean.setUsername("テストユーザー");
        userFormBean.setEmail("aaa@bbb.com");
        userFormBean.setNowPassword(Constants.BLANK);
        userFormBean.setNewPassword(Constants.BLANK);
        userFormBean.setConfirmPassword(Constants.BLANK);
        return userFormBean;
    }

    public boolean updateUser(UserFormBean userFormBean) {
        System.out.println(userFormBean.toString());
        return true;
    }
}
