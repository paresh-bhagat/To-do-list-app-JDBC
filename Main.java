import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
    static LoginFrame frame = new LoginFrame();
    static Map<String, String> user_info = new HashMap<>();
    static Map<String,Map<String,String[]>> Task_all = new HashMap<>() ;
    final static String filePath1 = "./files/usrpasswrd.txt";
    final static String filePath2 = "./files/usrtask.txt";

    public static void writeusrpasswrd( Map<String,String> map ){

        File file = new File(filePath1);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            // create new BufferedWriter for the output file
            // iterate map entries
            for (Map.Entry<String, String> entry : map.entrySet()) {
                // put key and value separated by a colon
                bf.write(entry.getKey() + ":" + entry.getValue());
                // new line
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // always close the writer
    }
    public static void writeusrtask( Map<String,Map<String,String[]>> map ){
        File file = new File(filePath2);
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            // create new BufferedWriter for the output file
            // iterate map entries
            for (Map.Entry<String, Map<String,String[]>> entry : map.entrySet()) {
                // put key and value separated by a colon
                Map<String, String[]> innerMap = entry.getValue();
                bf.write(entry.getKey());
                bf.newLine();
                bf.write(Integer.toString(innerMap.size()));
                bf.newLine();
                for (Map.Entry<String, String[] > keyPair : innerMap.entrySet()) {
                    String[] temp = keyPair.getValue();
                    bf.write(keyPair.getKey() + ":" +temp[0] + ":" + temp[1] + ":" +temp[2]+ ":"
                            + temp[3]+ ":" + temp[4]);
                    // new line
                    bf.newLine();
                }

            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readusrpasswrd( Map<String,String> map ){
        BufferedReader br = null;

        try {

            File file = new File(filePath1);

            br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                // split the line by :
                String[] parts = line.split(":");

                // first part is name, second is number
                String name = parts[0].trim();
                String passwrd = parts[1].trim();

                map.put(name, passwrd);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception ignored) {
                }
            }
        }
    }
    public static void readtask( Map<String,Map<String,String[]>> map){

        BufferedReader br = null;

        try {

            File file = new File(filePath2);

            br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                // split the line by :
                String usr_name = line;
                int n =  Integer.parseInt(br.readLine());
                map.put(usr_name,new HashMap<>());

                while(n!=0)
                {
                    line=br.readLine();
                    String[] parts = line.split(":");
                    String[] temp2 = new String[5];
                    // first part is name, second is number
                    String task = parts[0].trim();
                    temp2[0] = parts[1].trim();
                    temp2[1] = parts[2].trim();
                    temp2[2] = parts[3].trim();
                    temp2[3] = parts[4].trim();
                    temp2[4] = parts[5].trim();
                    map.get(usr_name).put(task, temp2);
                    n--;
                }

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {

            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception ignored) {
                }
            }
        }
    }
    public static void register( RegisterPage register_panel )
    {
        if ( user_info.containsKey(register_panel.textbox_newus.getText()) )
            register_panel.text_takenu.setVisible(true);
        else
        {
            String PasswordTyped = new String(register_panel.textbox_newpwd.getPassword());
            user_info.put( register_panel.textbox_newus.getText(), PasswordTyped);
            writeusrpasswrd(user_info);
            register_panel.text_registered.setVisible(true);
        }

    }
    public static void register_page()
    {
        frame.panel1.setVisible(false);
        frame.panel2.setVisible(false);
        RegisterPage register_panel = new RegisterPage();

        frame.add(register_panel);

        register_panel.button_newregister.addActionListener( e -> { register_panel.text_takenu.setVisible(false); register_panel.text_registered.setVisible(false); register(register_panel);} );

        register_panel.button_loginmenu.addActionListener( e -> { register_panel.setVisible(false); frame.panel1.setVisible(true); frame.panel2.setVisible(true);} );
    }
    public static void add_edit_task(String usr_name, String task)
    {
        TaskEdit panel_taskedit;
        if ( task.equals("0") )
        {
            panel_taskedit = new TaskEdit( "Enter task name","Enter task details" ,"00-00-0000","00-00","00-00-0000","00-00");
            frame.add(panel_taskedit);
            panel_taskedit.text_tasksaved.setVisible(false);
            panel_taskedit.text_taskdeleted.setVisible(false);
            panel_taskedit.button_deletetask.setVisible(false);

            panel_taskedit.button_save.addActionListener( e -> { panel_taskedit.text_tasksaved.setVisible(true);
                if( Task_all.containsKey(usr_name)) {
                    String[] temp = new String[5];
                    temp[0]=panel_taskedit.textbox_taskdetails.getText();
                    temp[1]=panel_taskedit.textbox_startdate.getText();
                    temp[2]=panel_taskedit.textbox_starttime.getText();
                    temp[3]=panel_taskedit.textbox_enddate.getText();
                    temp[4]=panel_taskedit.textbox_endtime.getText();
                    Task_all.get(usr_name).put( panel_taskedit.textbox_taskname.getText(), temp);
                    writeusrtask(Task_all);
                }
                else {
                    Map<String,String[]> task_t = new HashMap<>();
                    String[] temp = new String[5];
                    temp[0]=panel_taskedit.textbox_taskdetails.getText();
                    temp[1]=panel_taskedit.textbox_startdate.getText();
                    temp[2]=panel_taskedit.textbox_starttime.getText();
                    temp[3]=panel_taskedit.textbox_enddate.getText();
                    temp[4]=panel_taskedit.textbox_endtime.getText();
                    task_t.put(panel_taskedit.textbox_taskname.getText(),temp);
                    Task_all.put(usr_name,task_t);
                    writeusrtask(Task_all);
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
            panel_taskedit = new TaskEdit( task,(Task_all.get(usr_name)).get(task)[0] ,(Task_all.get(usr_name)).get(task)[1],(Task_all.get(usr_name)).get(task)[2],(Task_all.get(usr_name)).get(task)[3],(Task_all.get(usr_name)).get(task)[4]);
            frame.add(panel_taskedit);
            panel_taskedit.text_tasksaved.setVisible(false);
            panel_taskedit.text_taskdeleted.setVisible(false);
            panel_taskedit.button_deletetask.setVisible(true);

            panel_taskedit.button_save.addActionListener( e -> { panel_taskedit.text_tasksaved.setVisible(true);
                Task_all.get(usr_name).remove(task);
                String[] temp = new String[5];
                temp[0]=panel_taskedit.textbox_taskdetails.getText();
                temp[1]=panel_taskedit.textbox_startdate.getText();
                temp[2]=panel_taskedit.textbox_starttime.getText();
                temp[3]=panel_taskedit.textbox_enddate.getText();
                temp[4]=panel_taskedit.textbox_endtime.getText();
                Task_all.get(usr_name).put( panel_taskedit.textbox_taskname.getText(), temp);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                panel_taskedit.setVisible(false);
                writeusrtask(Task_all);
                task_page(usr_name);} );

            panel_taskedit.button_deletetask.addActionListener( e -> { panel_taskedit.text_taskdeleted.setVisible(true);
                Task_all.get(usr_name).remove(task);
                if (Task_all.get(usr_name).isEmpty()) { Task_all.remove(usr_name);}

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                panel_taskedit.setVisible(false);
                writeusrtask(Task_all);
                task_page(usr_name);} );
        }

        panel_taskedit.button_backmenu.addActionListener( e -> { panel_taskedit.setVisible(false); task_page(usr_name);} );
    }

    public static void task_page( String usr_name)
    {
        TaskPage task_panel = new TaskPage(usr_name);
        TaskView taskview_panel = new TaskView();

        if(Task_all.containsKey(usr_name))
        {
            task_panel.button_addtask.setText("Add new task");
            for ( Map.Entry<String, String[]> me : Task_all.get(usr_name).entrySet())
            {
                TaskButton button_t = new TaskButton(me.getKey());
                button_t.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); add_edit_task(usr_name,me.getKey());} );
                // Printing keys
                taskview_panel.add(button_t);

            }
        }
        else
        {
            task_panel.button_addtask.setText("Create your first task");
        }

        frame.add(task_panel);
        frame.add(taskview_panel);
        task_panel.button_addtask.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); add_edit_task(usr_name,"0");} );

        task_panel.button_delact.addActionListener(e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
            user_info.remove(usr_name);
            Task_all.remove(usr_name);
            writeusrpasswrd(user_info);
            writeusrtask(Task_all);
            frame.panel1.setVisible(true); frame.panel2.setVisible(true);});

        task_panel.button_changepswd.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); change_password(usr_name);} );

        task_panel.button_loginmenu.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); frame.panel1.setVisible(true); frame.panel2.setVisible(true);} );
    }
    public static void task_page()
    {
        frame.text_wup.setVisible(false);

        String usr_name =  frame.textbox_us.getText();

        if ( user_info.containsKey(usr_name) )
        {
            String PasswordTyped = new String(frame.textbox_pwd.getPassword());
            if ( user_info.get(frame.textbox_us.getText()).equals(PasswordTyped) )
            {
                frame.panel1.setVisible(false);
                frame.panel2.setVisible(false);
                TaskPage task_panel = new TaskPage(usr_name);
                TaskView taskview_panel = new TaskView();
                task_panel.button_addtask.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); add_edit_task(usr_name,"0");} );

                if(Task_all.containsKey(usr_name))
                {
                    task_panel.button_addtask.setText("Add new task");
                    for ( Map.Entry<String, String[]> me : Task_all.get(usr_name).entrySet())
                    {
                        TaskButton button_t = new TaskButton(me.getKey());
                        button_t.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); add_edit_task(usr_name,me.getKey());} );
                        // Printing keys
                        taskview_panel.add(button_t);

                    }
                }
                else
                {
                    task_panel.button_addtask.setText("Create your first task");
                }

                frame.add(task_panel);
                frame.add(taskview_panel);

                task_panel.button_delact.addActionListener(e -> { task_panel.setVisible(false); taskview_panel.setVisible(false);
                    user_info.remove(usr_name);
                    Task_all.remove(usr_name);
                    writeusrpasswrd(user_info);
                    writeusrtask(Task_all);
                    frame.panel1.setVisible(true); frame.panel2.setVisible(true);});

                task_panel.button_changepswd.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); change_password(usr_name);} );

                task_panel.button_loginmenu.addActionListener( e -> { task_panel.setVisible(false); taskview_panel.setVisible(false); frame.panel1.setVisible(true); frame.panel2.setVisible(true);} );


            }
            else
                frame.text_wup.setVisible(true);
        }
        else
            frame.text_wup.setVisible(true);
        frame.textbox_us.setText("");
        frame.textbox_pwd.setText("");

    }
    public static void change_password(String usr_name){
        ChangePassword change_panel = new ChangePassword();
        change_panel.text_changed.setVisible(false);
        frame.add(change_panel);
        change_panel.button_changepassword.addActionListener(
                e -> { String PasswordTyped = new String(change_panel.textbox_newpwd.getPassword());
                    user_info.replace(usr_name,PasswordTyped);
                    writeusrpasswrd(user_info);
                    change_panel.text_changed.setVisible(true);}
        );
        change_panel.button_back.addActionListener( e -> { change_panel.setVisible(false); task_page(usr_name);} );
    }
    public static void main(String[] args) {

        readusrpasswrd(user_info);
        readtask(Task_all);

        frame.button_login.addActionListener( e -> task_page() );
        frame.button_register.addActionListener( e -> register_page() );
    }
}
