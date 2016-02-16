package dao;

import domain.Picture;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Usage: DataAccessObject
 * Description: PictureDao
 * Created by Administrator on 2016/2/14.
 */

// PictureDao 作品 数据访问对象
@Repository
public class PictureDao extends BaseDao{

	//language=MYSQL
	private static final String INSERT =
			"INSERT INTO picture(author_id,create_time,update_time,path,name) values(?,?,?,?,?)";

	//保存图片
	public Picture insert( Picture picture ){
		//security check
		if( picture==null || picture.getAuthorId()==null || picture.getPath()==null ){
			return null;
		}
		//data inject
		Object[] data = new Object[]{picture.getAuthorId(),new Date(),new Date(),picture.getPath(),picture.getName()};
		//get the return value of id
		int id = jdbcTemplate.update( INSERT , data );
		//cast to long
		picture.setId((long) id);
		//return the picture with id
		return picture;
	}

	//language=MYSQL
	private static final String FIND_BY_NAME =
			"select id,author_id,name,create_time,update_time,path from picture where 1=1 ";

	//分页 获取 图片列表
	public List<Picture> findByName(String name,Integer pageId,Integer offset){
		if(pageId==null||pageId<0){
			pageId=0;
		}
		if(offset==null||offset<1){
			offset=20;
		}
		String sql = FIND_BY_NAME;
		if(name!=null){
			sql += " and name like '%" + name + "%' ";
		}
		sql += " LIMIT " + pageId + "," + offset;
		return jdbcTemplate.query(sql,new PictureDao().new PicRowMapper());
	}

	class PicRowMapper implements RowMapper<Picture> {

		@Override
		public Picture mapRow(ResultSet rs, int i) throws SQLException {
			Picture picture = new Picture();
			picture.setName(rs.getString("name"));
			picture.setPath(rs.getString("path"));
			picture.setUpdateTime(rs.getTimestamp("update_time"));
			picture.setCreateTime(rs.getTimestamp("create_time"));
			picture.setAuthorId(rs.getLong("author_id"));
			picture.setId(rs.getLong("id"));
			return picture;
		}

	}

}
