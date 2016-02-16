package dao;

import com.sun.jndi.toolkit.dir.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Usage: AutoInject Source
 * Description: AutoInject Source
 * Created by Administrator on 2016/2/14.
 */
public abstract class BaseDao {


	protected JdbcTemplate jdbcTemplate;

	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	protected static final Object[] EMPTY_PAPARMS = new Object[]{};

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getPageSQL(int pageId, int size, String sql) {
		int nStartRow = 0;
		if (pageId > 1) {
			nStartRow = (pageId - 1) * size;
		}
		return sql + " LIMIT " + nStartRow + "," + size;
	}

}
