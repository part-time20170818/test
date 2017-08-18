package com.wg.service;

import com.wg.domain.User;
import org.springframework.stereotype.Service;
import com.wg.dao.UserDao;

@Service  
public class UserServiceImpl implements UserService {

	//@Autowired
    private UserDao userDao;  
  
    public User selectUserById(Integer userId) {
        return userDao.selectUserById(userId);  
          
    }  
}
