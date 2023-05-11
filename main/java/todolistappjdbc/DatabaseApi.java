package todolistappjdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

// it will handle jdbc and all the queries to our database
public class DatabaseApi{

    private JdbcTemplate jdbctemplate;

    public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	// check if table user_info exists else create one
	
    public void CreateTable() {
        String query = "CREATE TABLE IF NOT EXISTS to_do_list_app.user_info( user_id varchar(20)," + 
        				" user_password varchar(20), " + "PRIMARY KEY (user_id));";
        this.jdbctemplate.update(query);
    }

    // while registering check if user id really exist
    
    public int check_userid(String userid) {
        String query = "SELECT * FROM to_do_list_app.user_info WHERE user_id=?;";
        
        RowMapper<UserInfo> rowMapper = new RowMapperUserInfo();
        
        UserInfo temp = this.jdbctemplate.queryForObject( query ,rowMapper , userid);
        
        if (temp!=null )
            return 1;
        return 0;
    }

    // create new user in database
    
    public void create_new_task_table(String userid) {
       
        // create table for task for user

        String query = "CREATE TABLE to_do_list_app." + userid + "_task(" +
                "task VARCHAR(25) not NULL, " +
                " details VARCHAR(100), " +
                " start_date VARCHAR(20), " +
                " start_time VARCHAR(20), " +
                " end_date VARCHAR(20), " +
                " end_time VARCHAR(20), " +
                " PRIMARY KEY ( task ))";
        this.jdbctemplate.update(query);
    }
    
    public void add_new_account(UserInfo temp) {
        
        String query = "INSERT INTO to_do_list_app.user_info VALUES (?,?);";
        this.jdbctemplate.update(query,temp.getUser_id(),temp.getUser_password());
        create_new_task_table(temp.getUser_id());
    }

    // query to change password

    public void change_password(UserInfo temp) {
    	
        String query = "UPDATE to_do_list_app.user_info set user_password=? WHERE user_id=?;";
        this.jdbctemplate.update(query,temp.getUser_password(),temp.getUser_id());
    }

    // get user_password from user id
    
    public String get_userpassword(String userid) throws SQLException {
        
        String query = "SELECT * from to_do_list_app.user_info WHERE user_id=?;";
        RowMapper<UserInfo> rowMapper = new RowMapperUserInfo();
        UserInfo temp = this.jdbctemplate.queryForObject( query ,rowMapper , userid);
        return temp.getUser_password();
    }

    //delete user row form table
    
    public void remove_user_userinfo(String usr_name) {
        
        String query = "DELETE FROM to_do_list_app.user_info WHERE user_id=?;";
        this.jdbctemplate.update(query,usr_name);
    }

    // delete user task table

    public void drop_table_user(String usr_name) {

        String query = "DROP TABLE to_do_list_app." + usr_name + "_task;" ;
        this.jdbctemplate.update(query,usr_name);
    }

    // get all tasks of a user
    
    public List<String> get_all_task(String usr_name) throws SQLException {
       
        String query = "SELECT * from to_do_list_app." + usr_name + "_task;";
        
        List<String> tasks = new ArrayList<>();
        
        RowMapper<Task> rowMapper = new RowMapperTask();
        List<Task> temp = this.jdbctemplate.query( query ,rowMapper);
        
        for(Task t:temp)
            tasks.add(t.getTask());
        
        return tasks;
    }

    // add task of a user

    public void add_task(String usr_name, String taskname, String taskdetails,String startdate,String starttime,
                         String enddate,String endtime) {
       
        String query = "INSERT INTO to_do_list_app." + usr_name + "_task" + " VALUES (?,?,?,?,?,?);";
        this.jdbctemplate.update(query,taskname,taskdetails,startdate,starttime,enddate,endtime);
    }

    // get all details of a task

    public Task get_task_details(String usr_name, String task) {
        
        String query = "SELECT * from to_do_list_app." + usr_name + "_task" + " WHERE task=?;";
        
        RowMapper<Task> rowMapper = new RowMapperTask();
        Task temp = this.jdbctemplate.queryForObject( query ,rowMapper , task);
        
        return temp;
    }

    //update a task

    public void update_task(String usr_name, String taskname, String taskdetails,String startdate,String starttime,
                            String enddate,String endtime, String task) {

        String query = "UPDATE to_do_list_app." + usr_name + "_task" + " SET task = ?, " +
                "details = ?, start_date = ?, start_time = ?, end_date = ?, end_time = ? WHERE task = ?;";
        this.jdbctemplate.update(query,taskname,taskdetails,startdate,starttime,enddate,endtime,task);
    }

    //delete a task

    public void delete_task(String usr_name,String task) {
        
        String query = "DELETE FROM to_do_list_app." + usr_name + "_task" + " WHERE task=?;";
        this.jdbctemplate.update(query,task);
    }
    
    // check if task exist
    
    public int check_task_exist(String usr_name,String task) {
        
        String query = "SELECT * FROM to_do_list_app." + usr_name + "_task" + " WHERE task=?;";
        
        RowMapper<Task> rowMapper = new RowMapperTask();
        Task temp = this.jdbctemplate.queryForObject( query ,rowMapper , task);
        
        if(temp!=null)
            return 1;
        return 0;
    }

}
