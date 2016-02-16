package dao;

import domain.User;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Usage: DataAccessObject
 * Description: UserDao
 * Created by Administrator on 2016/2/14.
 */

// UserDao 用户 数据访问对象
@Repository
public class UserDao extends BaseDao{
	//language=MYSQL
	private static final String INSERT =
			"INSERT INTO user(user_name,pass_word) values(?,?)";

	//注册用户
	public User insert( User user ){
		//security check
		if( user==null || user.getUserName()==null || user.getPassWord()==null ){
			return null;
		}
		//data inject
		Object[] data = new Object[]{user.getUserName(),user.getPassWord()};
		//get the return value of id
		int id = jdbcTemplate.update( INSERT , data );
		//cast to long
		user.setId((long) id);
		//return the user with id
		return user;
	}

	//language=MYSQL
	private static final String CHECK_EXIST = "select id,user_name,pass_word from user where user_name=?";
	//根据帐户查询用户是否存在 存在返回true
	public boolean checkExist(String userName){
		if(userName==null){
			return false;
		}
		Object[] condition = new Object[]{userName};
		final User user = new User();
		jdbcTemplate.query(CHECK_EXIST, condition, rs -> {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassWord(rs.getString("pass_word"));
		});
		return user.getId() != null;
	}

	//language=MYSQL
	private static final String GET = "select id,user_name,pass_word from user where user_name=? and pass_word=?";
	//根据帐户密码查询是否存在 存在返回user对象
	public User get(String userName,String passWord){
		if (userName==null||passWord==null){
			return null;
		}
		Object[] condition = new Object[]{userName,passWord};
		final User user = new User();
		jdbcTemplate.query(GET, condition, rs -> {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassWord(rs.getString("pass_word"));
		});
		return user;
	}
}
