package api;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Usage: api for user
 * Description: UserAPI
 * Created by Administrator on 2016/2/14.
 */
@RestController
@RequestMapping(value="user")
public class UserApi {

	@Autowired
	private UserService userService;

	//注册用户
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String,Object> register(@RequestParam(value = "userName", required = true)String userName,
	                                   @RequestParam(value = "passWord", required = true)String passWord
	                               ){
		Map<String,Object> data = new HashMap<String, Object>();
		if(userName==null||userName.trim().equals("")){
			data.put("msg","userName invalid,required not null.");
			data.put("status",1);
			data.put("user",null);
			return data;
		}
		if(passWord==null||passWord.trim().length()<6){
			data.put("msg","Password invalid,length required gt 6.");
			data.put("status",2);
			data.put("user",null);
			return data;
		}
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user = userService.insert(user);
		if(user==null){
			data.put("user",null);
			data.put("status",3);
			data.put("msg","userName required unique.");
			return data;
		}
		data.put("user",user);
		data.put("status",0);
		data.put("msg","success");
		return data;
	}

	//登陆
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String,Object> login(@RequestParam(value = "userName", required = true)String userName,
	                                @RequestParam(value = "passWord", required = true)String passWord
	){
		Map<String,Object> data = new HashMap<String, Object>();
		if(userName==null||userName.trim().equals("")){
			data.put("msg","userName invalid,required not null.");
			data.put("status",1);
			data.put("user",null);
			return data;
		}
		if(passWord==null||passWord.trim().length()<6){
			data.put("msg","Password invalid,length required gt 6.");
			data.put("status",2);
			data.put("user",null);
			return data;
		}
		User user = userService.login(userName,passWord);
		if(user==null||user.getId()==null){
			data.put("user",null);
			data.put("status",3);
			data.put("msg","UserName or PassWord wrong.");
			return data;
		}
		data.put("user",user);
		data.put("status",0);
		data.put("msg","success");
		return data;
	}


}
