package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Usage: Picture Pojo
 * Description: Picture class
 * Created by Administrator on 2016/2/14.
 */
@Entity
public class Picture {

	/**
	 * 作品id
	 */
	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private Long id;

	/**
	 * 作者id
	 */
	@Column(name = "author_id", nullable = false , insertable = true, updatable = false)
	private Long authorId;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time", nullable = true , insertable = true, updatable = false)
	private Date createTime;

	/**
	 * 最后修改时间
	 */
	@Column(name = "update_time", nullable = true , insertable = true, updatable = true)
	private Date updateTime;

	/**
	 * 存储路径
	 */
	@Column(name = "path", nullable = false , insertable = true, updatable = true)
	private String path;

	/**
	 * 名称
	 */
	@Column(name = "name", nullable = false , insertable = true, updatable = true)
	private String name;


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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Picture picture = (Picture) o;

		if (authorId != null ? !authorId.equals(picture.authorId) : picture.authorId != null) return false;
		if (createTime != null ? !createTime.equals(picture.createTime) : picture.createTime != null) return false;
		if (id != null ? !id.equals(picture.id) : picture.id != null) return false;
		if (name != null ? !name.equals(picture.name) : picture.name != null) return false;
		if (path != null ? !path.equals(picture.path) : picture.path != null) return false;
		if (updateTime != null ? !updateTime.equals(picture.updateTime) : picture.updateTime != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
		result = 31 * result + (path != null ? path.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
