import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static LoginFrame frame = new LoginFrame();
    static Connection connection;
    public static void register( RegisterPage register_panel ) throws SQLException {

        PreparedStatement st;
        ResultSet rset;
        String query = "select * from to_do_list_app.user_info where user_id=?;";
        st = connection.prepareStatement(query);
        st.setString(1,register_panel.textbox_newus.getText());
        rset = st.executeQuery();

        if (rset.next() )
            register_panel.text_takenu.setVisible(true);
        else
        {
            String PasswordTyped = new String(register_panel.textbox_newpwd.getPassword());
            // insert row
            query = "INSERT INTO to_do_list_app.user_info VALUES (?,?);";
            st = connection.prepareStatement(query);
            st.setString(1,register_panel.textbox_newus.getText());
            st.setString(2,PasswordTyped);
            st.executeUpdate();

            // create table for task

            query = "CREATE TABLE to_do_list_app.? " +
                    "(task VARCHAR(25) not NULL, " +
                    " details VARCHAR(100), " +
                    " start_date VARCHAR(20), " +
                    " start_time VARCHAR(20), " +
                    " end_date VARCHAR(20), " +
                    " end_time VARCHAR(20), " +
                    " PRIMARY KEY ( task ))";

            st = connection.prepareStatement(query);
            st.setString(1,register_panel.textbox_newus.getText() + "_task");
            st.executeUpdate();

            register_panel.text_registered.setVisible(true);
        }

    }
    public static void register_page()
    {
        frame.panel1.setVisible(false);
        frame.panel2.setVisible(false);
        RegisterPage register_panel = new RegisterPage();

        frame.add(register_panel);

        register_panel.button_newregister.addActionListener( e -> { register_panel.text_takenu.setVisible(false); register_panel.text_registered.setVisible(false);
            try {
                register(register_panel);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } );

        register_panel.button_loginmenu.addActionListener( e -> { register_panel.setVisible(false); frame.panel1.setVisible(true); frame.panel2.setVisible(true);} );
    }
    public static void add_edit_task(String usr_name, String task) throws SQLException {
        TaskEdit panel_taskedit;
        if ( task.equals("0") )
        {
            panel_taskedit = new TaskEdit( "Enter task name","Enter task details" ,"00-00-0000","00-00","00-00-0000","00-00");
            frame.add(panel_taskedit);
            panel_taskedit.text_tasksaved.setVisible(false);
            panel_taskedit.text_taskdeleted.setVisible(false);
            panel_taskedit.button_deletetask.setVisible(false);

            panel_taskedit.button_save.addActionListener( e -> { panel_taskedit.text_tasksaved.setVisible(true);

                PreparedStatement st;
                String query = "INSERT INTO to_do_list_app.? VALUES (?,?,?,?,?);";
                try {
                    st = connection.prepareStatement(query);
                    st.setString(1,usr_name+"_task");
                    st.setString(2,panel_taskedit.textbox_taskdetails.getText());
                    st.setString(3,panel_taskedit.textbox_startdate.getText());
                    st.setString(4,panel_taskedit.textbox_starttime.getText());
                    st.setString(5,panel_taskedit.textbox_enddate.getText());
                    st.setString(6,panel_taskedit.textbox_endtime.getText());
                    st.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                panel_taskedit.setVisible(false); task_page(usr_name);} );
        }

        else
        {
            PreparedStatement st;
            ResultSet rset;
            String query = "select * from to_do_list_app.? where task=?;";
            try{
                st = connection.prepareStatement(query);
                st.setString(1,usr_name);
                st.setString(2,task);
                rset = st.executeQuery();
                rset.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            panel_taskedit = new TaskEdit( task,rset.getString("details") ,rset.getString("start_date"),rset.getString("start_time"),rset.getString("end_date"),rset.getString("end_time"));
            frame.add(panel_taskedit);
            panel_taskedit.text_tasksaved.setVisible(false);
            panel_taskedit.text_taskdeleted.setVisible(false);
            panel_taskedit.button_deletetask.setVisible(true);
            PreparedStatement st2 = connection.prepareStatement("update to_do_list_app.? SET task = ?, details = ?, start_date = ?, start_time = ?, end_date = ?, end_time = ? WHERE task = ?;");

            panel_taskedit.button_save.addActionListener(e -> { panel_taskedit.text_tasksaved.setVisible(true);
                try {
                    st2.setString(1,usr_name+"_task");
                    st2.setString(2,panel_taskedit.textbox_taskname.getText());
                    st2.setString(3,panel_taskedit.textbox_taskdetails.getText());
                    st2.setString(4,panel_taskedit.textbox_startdate.getText());
                    st2.setString(5,panel_taskedit.textbox_starttime.getText());
                    st2.setString(6,panel_taskedit.textbox_enddate.getText());
                    st2.setString(7,panel_taskedit.textbox_endtime.getText());
                    st2.setString(8,task);
                    st2.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                panel_taskedit.setVisible(false);
                task_page(usr_name);} );

            panel_taskedit.button_deletetask.addActionListener( e -> { panel_taskedit.text_taskdeleted.setVisible(true);

                PreparedStatement st3;
                try {
                    st3 = connection.prepareStatement("DELETE FROM to_do_list_app.? WHERE task=?;");
                    st3.setString(1,usr_name+"_task");
                    st3.setString(2,task);
                    st3.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                panel_taskedit.setVisible(false);
                task_page(usr_name);} );
        }

        panel_taskedit.button_backmenu.addActionListener( e -> { panel_taskedit.setVisible(false); task_page(usr_name);} );
    }

    public static void task_page( String usr_name)
    {
        TaskPage task_panel = new TaskPage(usr_name);
        TaskView taskview_panel = new TaskView();

        try {

            PreparedStatement st;
            ResultSet rset;
            String query = "select * from to_do_list_app.?;";
            st = connection.prepareStatement(query);
            st.setString(1,usr_name+"_task");
            rset = st.executeQuery();
            rset.next();

            if( rset.next() )
            {
                task_panel.button_addtask.setText("Add new task");
                do
                {
                    String temp_s = rset.getString("task");
                    TaskButton button_t = new TaskButton(temp_s);
                    button_t.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                        try {
                            add_edit_task(usr_name,temp_s);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } );
                    // Printing keys
                    taskview_panel.add(button_t);

                }while(rset.next());
            }
            else
            {
                task_panel.button_addtask.setText("Create your first task");
            }

            query = "DELETE FROM to_do_list_app.user_info WHERE user_id=?;";
            st = connection.prepareStatement(query);
            st.setString(1,usr_name);
            String query2 = "DROP TABLE to_do_list_app.?";
            PreparedStatement stl = connection.prepareStatement(query2);
            stl.setString(1,usr_name+"_task");

            PreparedStatement finalSt = st;
            task_panel.button_delact.addActionListener(e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                try {
                    finalSt.executeUpdate();
                    stl.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                frame.panel1.setVisible(true); frame.panel2.setVisible(true);});


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        frame.add(task_panel);
        frame.add(taskview_panel);
        task_panel.button_addtask.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
            try {
                add_edit_task(usr_name,"0");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } );

        task_panel.button_changepswd.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
            try {
                change_password(usr_name);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } );

        task_panel.button_loginmenu.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); frame.panel1.setVisible(true); frame.panel2.setVisible(true);} );
    }
    public static void task_page()
    {
        frame.text_wup.setVisible(false);

        try {
            String usr_name =  frame.textbox_us.getText();
            PreparedStatement st;
            ResultSet rset;
            String query = "select * from to_do_list_app.user_info where user_id=?;";
            st = connection.prepareStatement(query);
            st.setString(1,usr_name);
            rset = st.executeQuery();

            if ( rset.next() )
            {
                String PasswordTyped = new String(frame.textbox_pwd.getPassword());

                query = "select user_password from to_do_list_app.user_info where user_id=?;";
                st = connection.prepareStatement(query);
                st.setString(1,usr_name);
                rset = st.executeQuery();
                rset.next();
                String password_actual = rset.getString("user_password");

                if ( password_actual.equals(PasswordTyped) )
                {
                    frame.panel1.setVisible(false);
                    frame.panel2.setVisible(false);
                    TaskPage task_panel = new TaskPage(usr_name);
                    TaskView taskview_panel = new TaskView();
                    task_panel.button_addtask.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                        try {
                            add_edit_task(usr_name,"0");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } );

                    query = "select * from to_do_list_app.?;";
                    st = connection.prepareStatement(query);
                    st.setString(1,usr_name+"_task");
                    rset = st.executeQuery();
                    rset.next();

                    if(rset.next())
                    {
                        task_panel.button_addtask.setText("Add new task");
                        do
                        {
                            String temp_s = rset.getString("task");
                            TaskButton button_t = new TaskButton(temp_s);
                            button_t.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                                try {
                                    add_edit_task( usr_name, temp_s );
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } );
                            // Printing keys
                            taskview_panel.add(button_t);

                        }while(rset.next());
                    }
                    else
                    {
                        task_panel.button_addtask.setText("Create your first task");
                    }

                    frame.add(task_panel);
                    frame.add(taskview_panel);

                    query = "DELETE FROM to_do_list_app.user_info WHERE user_id=?;";
                    st = connection.prepareStatement(query);
                    st.setString(1,usr_name);
                    String query2 = "DROP TABLE to_do_list_app.?";
                    PreparedStatement stl = connection.prepareStatement(query2);
                    stl.setString(1,usr_name+"_task");

                    PreparedStatement finalSt = st;
                    task_panel.button_delact.addActionListener(e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                        try {
                            finalSt.executeUpdate();
                            stl.executeUpdate();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.panel1.setVisible(true); frame.panel2.setVisible(true);});

                    task_panel.button_changepswd.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                        try {
                            change_password(usr_name);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } );

                    task_panel.button_loginmenu.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); frame.panel1.setVisible(true); frame.panel2.setVisible(true);} );


                }
                else
                    frame.text_wup.setVisible(true);
            }
            else
                frame.text_wup.setVisible(true);

            frame.textbox_us.setText("");
            frame.textbox_pwd.setText("");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void change_password(String usr_name) throws SQLException {
        ChangePassword change_panel = new ChangePassword();
        change_panel.text_changed.setVisible(false);
        frame.add(change_panel);

        PreparedStatement st;
        String query = "update to_do_list_app.user_info set user_password=? where user_id=?;";
        st = connection.prepareStatement(query);

        change_panel.button_changepassword.addActionListener(
                e -> { String PasswordTyped = new String(change_panel.textbox_newpwd.getPassword());
                    try {
                        st.setString(1,PasswordTyped);
                        st.setString(2,usr_name);
                        st.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    change_panel.text_changed.setVisible(true);}
        );
        change_panel.button_back.addActionListener( e -> { change_panel.setVisible(false); task_page(usr_name);} );
    }
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/to_do_list_app","root","GMb123!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        frame.button_login.addActionListener( e -> task_page() );
        frame.button_register.addActionListener( e -> register_page() );
    }
}
