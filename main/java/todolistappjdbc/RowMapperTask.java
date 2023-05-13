package todolistappjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RowMapperTask implements RowMapper<Task>{

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Task temp = new Task();
		temp.setTask(rs.getString(1));
		temp.setTask_details(rs.getString(2));
		temp.setStart_date(rs.getDate(3));
		temp.setStart_time(rs.getTime(4));
		temp.setEnd_date(rs.getDate(5));
		temp.setEnd_time(rs.getTime(6));
		return temp;
	}

}
