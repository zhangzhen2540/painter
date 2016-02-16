package domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Usage: User Pojo
 * Description: User class
 * Created by Administrator on 2016/2/14.
 */
@Entity
public class User {

	/**
	 * 唯一自增主键 id
	 */
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private Long id;

	/**
	 * 帐户
	 */
	@Column(name = "user_name", nullable = false , insertable = true, updatable = false)
	private String userName;

	/**
	 * 密码
	 */
	@Column(name = "pass_word", nullable = false , insertable = true, updatable = true)
	private String passWord;

	/**
	 * Getter 获取对象属性值
	 * Setter 设置对象属性值
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 用于比较用户对象是否相等
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (passWord != null ? !passWord.equals(user.passWord) : user.passWord != null) return false;
		if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;

		return true;
	}

	/**
	 * 用于获取用户对象hashcode编码值
	 */
	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (passWord != null ? passWord.hashCode() : 0);
		return result;
	}

}
