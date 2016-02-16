package service;

import dao.UserDao;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Usage: Service for User
 * Description: UserService
 * Created by Administrator on 2016/2/14.
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	//注册用户
	public User insert( User user ){
		if(user==null||user.getUserName()==null){
			return null;
		}
		boolean isExist = userDao.checkExist(user.getUserName());
		if(isExist){
			//存在返回null
			return null;
		}
		return userDao.insert(user);
	}

	//根据帐户密码查询是否存在 存在返回user对象
	public User login(String userName,String passWord){
		return userDao.get(userName,passWord);
	}
}
