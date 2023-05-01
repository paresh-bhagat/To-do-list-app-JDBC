
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// it will handle jdbc and all the queries to our database
public class DatabaseApi{

    private final Connection connection;

    DatabaseApi(String mysql_username,String mysql_password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String mysql_url = "jdbc:mysql://localhost:3306/?" + "user=" + mysql_username + "&password=" + mysql_password;
        this.connection = DriverManager.getConnection( mysql_url);
    }

    // check if database exits or create new
    public void CreateDatabase() throws SQLException {
        Statement st = this.connection.createStatement();
        String query = "CREATE DATABASE IF NOT EXISTS to_do_list_app;";
        st.executeUpdate(query);
    }

    // connect to database
    public void ConnectDatabase() throws SQLException {

        Statement st = this.connection.createStatement();
        String query = "USE to_do_list_app;";
        st.executeUpdate(query);
    }

    // check if table user_info exists else create one
    public void CreateTable() throws SQLException {
        Statement st = this.connection.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS to_do_list_app.user_info( user_id varchar(20), user_password varchar(20), " +
                "PRIMARY KEY (user_id));";
        st.executeUpdate(query);
    }

    // while registering check if user id really exist
    public int check_userid(String userid) throws SQLException {
        PreparedStatement st;
        ResultSet rset;
        String query = "SELECT * FROM to_do_list_app.user_info WHERE user_id=?;";
        st = this.connection.prepareStatement(query);
        st.setString(1,userid);
        rset = st.executeQuery();

        if (rset.next() )
            return 1;
        return 0;
    }

    // create new user in database
    public void create_new_task_table(String userid) throws SQLException {
        Statement st = this.connection.createStatement();
        // create table for task for user

        String query = "CREATE TABLE to_do_list_app." + userid + "_task(" +
                "task VARCHAR(25) not NULL, " +
                " details VARCHAR(100), " +
                " start_date VARCHAR(20), " +
                " start_time VARCHAR(20), " +
                " end_date VARCHAR(20), " +
                " end_time VARCHAR(20), " +
                " PRIMARY KEY ( task ))";
        st.executeUpdate(query);
    }
    public void add_new_account(String userid, String password) throws SQLException {
        PreparedStatement st;
        String query = "INSERT INTO to_do_list_app.user_info VALUES (?,?);";
        st = this.connection.prepareStatement(query);
        st.setString(1,userid);
        st.setString(2,password);
        st.executeUpdate();

        create_new_task_table(userid);
    }

    // query to change password

    public void change_password(String PasswordTyped,String usr_name) throws SQLException {
        PreparedStatement st;
        String query = "UPDATE to_do_list_app.user_info set user_password=? WHERE user_id=?;";
        st = this.connection.prepareStatement(query);
        st.setString(1,PasswordTyped);
        st.setString(2,usr_name);
        st.executeUpdate();
    }

    // get user_password from user id
    public String get_userpassword(String usr_name) throws SQLException {
        PreparedStatement st;
        ResultSet rset;
        String query = "SELECT user_password from to_do_list_app.user_info WHERE user_id=?;";
        st = this.connection.prepareStatement(query);
        st.setString(1,usr_name);
        rset = st.executeQuery();
        rset.next();
        return rset.getString("user_password");
    }

    //delete user row form table
    public void remove_user_userinfo(String usr_name) throws SQLException {
        PreparedStatement st;
        String query = "DELETE FROM to_do_list_app.user_info WHERE user_id=?;";
        st = this.connection.prepareStatement(query);
        st.setString(1,usr_name);
        st.executeUpdate();
    }

    // delete user task table

    public void drop_table_user(String usr_name) throws SQLException {
        Statement st = this.connection.createStatement();
        String query = "DROP TABLE to_do_list_app." + usr_name + "_task;" ;
        st.executeUpdate(query);
    }

    // get all tasks of a user
    public List<String> get_all_task(String usr_name) throws SQLException {
        Statement st = this.connection.createStatement();
        ResultSet rset;
        String query = "SELECT * from to_do_list_app." + usr_name + "_task;";
        rset = st.executeQuery(query);
        List<String> tasks = new ArrayList<>();

        while(rset.next())
        {
            tasks.add(rset.getString("task"));
        }
        return tasks;
    }

    // add task of a user

    public void add_task(String usr_name, String taskname, String taskdetails,String startdate,String starttime,
                         String enddate,String endtime) throws SQLException {
        PreparedStatement st;
        String query = "INSERT INTO to_do_list_app." + usr_name + "_task" + " VALUES (?,?,?,?,?,?);";

        st = this.connection.prepareStatement(query);
        st.setString(1,taskname);
        st.setString(2,taskdetails);
        st.setString(3,startdate);
        st.setString(4,starttime);
        st.setString(5,enddate);
        st.setString(6,endtime);
        st.executeUpdate();

    }

    // get all details of a task

    public List<String> get_task_details(String usr_name, String task) throws SQLException {
        PreparedStatement st;
        ResultSet rset;
        String query = "SELECT * from to_do_list_app." + usr_name + "_task" + " WHERE task=?;";
        st = connection.prepareStatement(query);
        st.setString(1,task);
        rset = st.executeQuery();
        rset.next();
        List<String> task_info = new ArrayList<>();

        task_info.add(rset.getString("details"));
        task_info.add(rset.getString("start_date"));
        task_info.add(rset.getString("start_time"));
        task_info.add(rset.getString("end_date"));
        task_info.add(rset.getString("end_time"));

        return task_info;
    }

    //update a task

    public void update_task(String usr_name, String taskname, String taskdetails,String startdate,String starttime,
                            String enddate,String endtime, String task) throws SQLException {

        PreparedStatement st;

        String query = "UPDATE to_do_list_app." + usr_name + "_task" + " SET task = ?, " +
                "details = ?, start_date = ?, start_time = ?, end_date = ?, end_time = ? WHERE task = ?;";
        st = this.connection.prepareStatement(query);
        st.setString(1,taskname);
        st.setString(2,taskdetails);
        st.setString(3,startdate);
        st.setString(4,starttime);
        st.setString(5,enddate);
        st.setString(6,endtime);
        st.setString(7,task);
        st.executeUpdate();

    }

    //delete a task

    public void delete_task(String usr_name,String task) throws SQLException {
        PreparedStatement st;
        String query = "DELETE FROM to_do_list_app." + usr_name + "_task" + " WHERE task=?;";
        st = connection.prepareStatement(query);
        st.setString(1,task);
        st.executeUpdate();
    }
    // check if task exist
    public int check_task_exist(String usr_name,String task) throws SQLException {
        PreparedStatement st;
        ResultSet rset;
        String query = "SELECT * FROM to_do_list_app." + usr_name + "_task" + " WHERE task=?;";
        st = connection.prepareStatement(query);
        st.setString(1,task);
        rset = st.executeQuery();

        if(rset.next())
            return 1;
        return 0;
    }

}
