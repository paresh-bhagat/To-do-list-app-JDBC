package todolistappjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RowMapperUserInfo implements RowMapper<UserInfo>{

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserInfo temp = new UserInfo();
		temp.setUser_id(rs.getString(1));
		temp.setUser_password(rs.getString(2));
		return temp;
	}

}
