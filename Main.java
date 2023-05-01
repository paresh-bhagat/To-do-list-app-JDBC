import java.sql.SQLException;
import java.util.List;

public class Main {
    static LoginFrame frame = new LoginFrame();

    // action for register button when clicked
    //
    public static void register( RegisterPage register_panel ) throws SQLException, ClassNotFoundException {

        DatabaseApi database = new DatabaseApi();
        int result = database.check_userid(register_panel.textbox_newus.getText());
        // if username is already taken

        if (result == 1 )
            register_panel.text_takenu.setVisible(true);
        else
        {
            // add new user
            String PasswordTyped = new String(register_panel.textbox_newpwd.getPassword());
            database.add_new_account(register_panel.textbox_newus.getText(),PasswordTyped);
            register_panel.button_newregister.setVisible(false);
            register_panel.text_registered.setVisible(true);
        }
    }

    // register user page
    public static void register_page()
    {
        // remove login panel and put register page panel

        frame.panel1.setVisible(false);
        frame.panel2.setVisible(false);
        RegisterPage register_panel = new RegisterPage();

        frame.add(register_panel);

        // set action for register button

        register_panel.button_newregister.addActionListener( e -> { register_panel.text_takenu.setVisible(false);
            register_panel.text_registered.setVisible(false);
            try {
                register(register_panel);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        } );

        register_panel.button_loginmenu.addActionListener( e -> { register_panel.setVisible(false);
            frame.panel1.setVisible(true); frame.panel2.setVisible(true); frame.text_wup.setVisible(false);} );
    }

    // action for add task for button click
    public static void add_task_button(String usr_name,String task,TaskEdit panel_taskedit){

        try {
                DatabaseApi database = new DatabaseApi();

                String new_task = panel_taskedit.textbox_taskname.getText();

                if (database.check_task_exist(usr_name, new_task) == 0) {

                    if(task.equals("0")){
                        database.add_task(usr_name, panel_taskedit.textbox_taskname.getText(),
                                panel_taskedit.textbox_taskdetails.getText(),
                                panel_taskedit.textbox_startdate.getText(),
                                panel_taskedit.textbox_starttime.getText(),
                                panel_taskedit.textbox_enddate.getText(),
                                panel_taskedit.textbox_endtime.getText());

                    }
                    else {
                        database.update_task(usr_name,panel_taskedit.textbox_taskname.getText(),
                                panel_taskedit.textbox_taskdetails.getText(),
                                panel_taskedit.textbox_startdate.getText(),
                                panel_taskedit.textbox_starttime.getText(),
                                panel_taskedit.textbox_enddate.getText(),
                                panel_taskedit.textbox_endtime.getText(),
                                task);
                    }
                    panel_taskedit.text_tasksaved.setVisible(true);
                }
                else {
                    panel_taskedit.text_task_already_exist.setVisible(true);
                }


            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        panel_taskedit.button_deletetask.setVisible(false);
        panel_taskedit.button_save.setVisible(false);

    }

    // open a task for editing or deleting
    public static void add_edit_task(String usr_name, String task) throws SQLException {
        TaskEdit panel_taskedit;

        if (task.equals("0"))
        {
            panel_taskedit = new TaskEdit( "Enter task name","Enter task details" ,
                    "00-00-0000","00-00","00-00-0000","00-00");

        }
        else {

            List<String> task_info;
            try{
                DatabaseApi database = new DatabaseApi();
                task_info = database.get_task_details(usr_name, task);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


            panel_taskedit = new TaskEdit( task,task_info.get(0) ,task_info.get(1),task_info.get(2),
                    task_info.get(3), task_info.get(4));
        }
        frame.add(panel_taskedit);

        panel_taskedit.text_tasksaved.setVisible(false);
        panel_taskedit.text_taskdeleted.setVisible(false);
        panel_taskedit.text_task_already_exist.setVisible(false);
        panel_taskedit.button_deletetask.setVisible(false);

        // add buttons

        panel_taskedit.button_save.addActionListener(e -> add_task_button(usr_name,task,panel_taskedit));

        if (!task.equals("0"))
        {
            // button to delete this task
            panel_taskedit.button_deletetask.setVisible(true);

            panel_taskedit.button_deletetask.addActionListener( e -> {

                try {
                    DatabaseApi database = new DatabaseApi();
                    database.delete_task(usr_name, task);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                panel_taskedit.text_taskdeleted.setVisible(true);
                panel_taskedit.button_deletetask.setVisible(false);
                panel_taskedit.button_save.setVisible(false);} );
        }

        panel_taskedit.button_backmenu.addActionListener( e -> { panel_taskedit.setVisible(false); task_page(usr_name);} );
    }

    // go to task page if credentials are correct or come back to tasks page
    public static void task_page( String usr_name)
    {
        TaskPage task_panel = new TaskPage(usr_name);
        TaskView taskview_panel = new TaskView();

        try {

            // get all tasks of a user
            DatabaseApi database =new DatabaseApi();
            List<String> tasks = database.get_all_task(usr_name);

            // check if user has any task

            if( tasks.size()!=0 )
            {
                // list all the task with buttons with user task table

                for (String temp_s : tasks) {

                    // get all elements of List
                    TaskButton button_t = new TaskButton(temp_s);
                    button_t.addActionListener(e -> {
                        task_panel.setVisible(false);
                        taskview_panel.setVisible(false);
                        try {
                            add_edit_task(usr_name, temp_s);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                    // Printing keys
                    taskview_panel.add(button_t);
                }

            }

            // button for delete user

            task_panel.button_delact.addActionListener(e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                try {
                    database.remove_user_userinfo(usr_name);
                    database.drop_table_user(usr_name);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                frame.panel1.setVisible(true); frame.panel2.setVisible(true);});



            frame.add(task_panel);
            frame.add(taskview_panel);

            // add action listener to add new task

            task_panel.button_addtask.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                try {
                    add_edit_task(usr_name,"0");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } );

            // add action listener for change password

            task_panel.button_changepswd.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                try {
                    change_password(usr_name);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } );

            // add action listener for going back to main menu

            task_panel.button_loginmenu.addActionListener( e -> { task_panel.setVisible(false);
                taskview_panel.setVisible(false); frame.panel1.setVisible(true);
                frame.panel2.setVisible(true);} );


        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // check user credentials and if true open task page
    public static void check_user()
    {
        frame.text_wup.setVisible(false);

        try {
            String usr_name =  frame.textbox_us.getText();

            DatabaseApi database = new DatabaseApi();
            int result = database.check_userid(usr_name);

            // check if user is already present

            if ( result==1 )
            {
                String PasswordTyped = new String(frame.textbox_pwd.getPassword());
                String password_actual = database.get_userpassword(usr_name);

                // check password if equal open up the task page and display the task

                if ( password_actual.equals(PasswordTyped) )
                {
                    frame.panel1.setVisible(false);
                    frame.panel2.setVisible(false);

                    task_page(usr_name);
                }
                else
                    frame.text_wup.setVisible(true);
            }
            else
                frame.text_wup.setVisible(true);

            //set text empty

            frame.textbox_us.setText("");
            frame.textbox_pwd.setText("");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // function to change password of user
    public static void change_password(String usr_name) throws SQLException {

        // create panel for change_password

        ChangePassword change_panel = new ChangePassword();
        change_panel.text_changed.setVisible(false);
        frame.add(change_panel);

        // add action listener for change password button

        change_panel.button_changepassword.addActionListener(
                e -> { String PasswordTyped = new String(change_panel.textbox_newpwd.getPassword());

                    try {
                        DatabaseApi database = new DatabaseApi();
                        database.change_password(PasswordTyped,usr_name);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    change_panel.button_changepassword.setVisible(false);
                    change_panel.text_changed.setVisible(true);}
        );

        // go back to task_page
        change_panel.button_back.addActionListener( e -> { change_panel.setVisible(false); task_page(usr_name);} );
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // connect to to-do-app-list-database

        DatabaseApi database = new DatabaseApi();
        database.CreateDatabase();
        database.ConnectDatabase();
        database.CreateTable();

        // set action for login and register button

        frame.button_login.addActionListener( e -> check_user() );
        frame.button_register.addActionListener( e -> register_page() );
    }
}
