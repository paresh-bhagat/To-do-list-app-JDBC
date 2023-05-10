package todolistappjdbc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

// panel where you edit your task ex. delete, edit etc.

@SuppressWarnings("serial")
public class TaskEdit extends JPanel{
    JTextField textbox_taskname = new JTextField();
    JTextField textbox_taskdetails = new JTextField();
    JButton button_backmenu = new JButton();
    JButton button_save = new JButton();
    JTextField textbox_startdate = new JTextField();
    JTextField textbox_enddate = new JTextField();

    JTextField textbox_starttime = new JTextField();
    JTextField textbox_endtime = new JTextField();
    JButton button_deletetask = new JButton();

    JLabel text_tasksaved = new JLabel();

    JLabel text_task_already_exist = new JLabel();
    JLabel text_taskdeleted = new JLabel();
    TaskEdit(String task, String details, String startdate, String starttime, String enddate, String endtime) throws IOException
    {

        // text task
        JLabel text_Task = new JLabel();
        text_Task.setText("Task");
        text_Task.setForeground(new Color(47, 43, 44));
        text_Task.setFont(new Font("Futura",Font.ITALIC,25));
        text_Task.setBounds(500,80, 100,25);

        // text Details
        JLabel text_details = new JLabel();
        text_details.setText("Details");
        text_details.setForeground(new Color(47, 43, 44));
        text_details.setFont(new Font("Futura",Font.ITALIC,25));
        text_details.setBounds(490,300, 100,25);

        // Start time
        JLabel text_starttime = new JLabel();
        text_starttime.setText("Starting Time");
        text_starttime.setForeground(new Color(47, 43, 44));
        text_starttime.setFont(new Font("Futura",Font.ITALIC,16));
        text_starttime.setBounds(395,210, 150,40);

        // text box start time

        textbox_starttime.setBounds(395,245,100, 20);
        textbox_starttime.setBorder(null);
        textbox_starttime.setText(starttime);
        textbox_starttime.setHorizontalAlignment(JTextField.CENTER);
        textbox_starttime.setForeground(new Color(47, 43, 44));
        textbox_starttime.setFont(new Font("Futura",Font.PLAIN,14));

        // End time
        JLabel text_endtime = new JLabel();
        text_endtime.setText("Ending time");
        text_endtime.setForeground(new Color(47, 43, 44));
        text_endtime.setFont(new Font("Futura",Font.ITALIC,16));
        text_endtime.setBounds(700,210, 100,40);

        // text box end time

        textbox_endtime.setBounds(700,245,100, 20);
        textbox_endtime.setBorder(null);
        textbox_endtime.setText(endtime);
        textbox_endtime.setHorizontalAlignment(JTextField.CENTER);
        textbox_endtime.setForeground(new Color(47, 43, 44));
        textbox_endtime.setFont(new Font("Futura",Font.PLAIN,14));

        // Start date
        JLabel text_startdate = new JLabel();
        text_startdate.setText("Starting Date");
        text_startdate.setForeground(new Color(47, 43, 44));
        text_startdate.setFont(new Font("Futura",Font.ITALIC,16));
        text_startdate.setBounds(250,210, 150,40);

        // text box start date

        textbox_startdate.setBounds(250,245,100, 20);
        textbox_startdate.setBorder(null);
        textbox_startdate.setText(startdate);
        textbox_startdate.setHorizontalAlignment(JTextField.CENTER);
        textbox_startdate.setForeground(new Color(47, 43, 44));
        textbox_startdate.setFont(new Font("Futura",Font.PLAIN,14));

        // End date
        JLabel text_enddate = new JLabel();
        text_enddate.setText("Ending date");
        text_enddate.setForeground(new Color(47, 43, 44));
        text_enddate.setFont(new Font("Futura",Font.ITALIC,16));
        text_enddate.setBounds(570,210, 100,40);

        // text box end date

        textbox_enddate.setBounds(570,245,100, 20);
        textbox_enddate.setBorder(null);
        textbox_enddate.setText(enddate);
        textbox_enddate.setHorizontalAlignment(JTextField.CENTER);
        textbox_enddate.setForeground(new Color(47, 43, 44));
        textbox_enddate.setFont(new Font("Futura",Font.PLAIN,14));

        // text box task name

        textbox_taskname.setBounds(450,120,150, 60);
        textbox_taskname.setBorder(null);
        textbox_taskname.setText(task);
        textbox_taskname.setHorizontalAlignment(JTextField.CENTER);
        textbox_taskname.setForeground(new Color(47, 43, 44));
        textbox_taskname.setFont(new Font("Futura",Font.PLAIN,15));

        // text box task details
        textbox_taskdetails.setBounds(340,350,400, 80);
        textbox_taskdetails.setBorder(null);
        textbox_taskdetails.setText(details);
        textbox_taskdetails.setHorizontalAlignment(JTextField.CENTER);
        textbox_taskdetails.setForeground(new Color(47, 43, 44));
        textbox_taskdetails.setFont(new Font("Futura",Font.PLAIN,15));

        // back button
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("backbutton.png"); 
        ImageIcon img_back = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_backmenu.setBorder(null);
        button_backmenu.setBounds(100, 80, 40,40);
        button_backmenu.setFocusable(false);
        button_backmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_backmenu.setBackground(new Color(157, 168, 231));
        button_backmenu.setIcon(img_back);

        // save button
        stream = this.getClass().getClassLoader().getResourceAsStream("save.png");  
        ImageIcon img_save = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_save.setBorder(null);
        button_save.setFocusable(false);
        button_save.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_save.setBackground(new Color(157, 168, 231));
        button_save.setIcon(img_save);

        // delete button
        stream = this.getClass().getClassLoader().getResourceAsStream("remove.png"); 
        ImageIcon img_delete = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_deletetask.setBounds(550, 530, 40,40);
        button_deletetask.setBorder(null);
        button_deletetask.setFocusable(false);
        button_deletetask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_deletetask.setBackground(new Color(157, 168, 231));
        button_deletetask.setIcon(img_delete);

        if (task.equals("Enter task name")) {
            button_save.setBounds(505, 530, 40,40);
        }
        else {
            button_save.setBounds(475, 530, 40,40);
        }
        // text tasksaved
        text_tasksaved.setText("task saved (*_*)");
        text_tasksaved.setForeground(new Color(17, 14, 14));
        text_tasksaved.setFont(new Font("Futura",Font.PLAIN,15));
        text_tasksaved.setBounds(480,460, 200,40);

        // text task already exist
        text_task_already_exist.setText("task already exist (・・?");
        text_task_already_exist.setForeground(new Color(185, 29, 29));
        text_task_already_exist.setFont(new Font("Futura",Font.PLAIN,15));
        text_task_already_exist.setBounds(460,460, 200,40);


        // text taskdeleted
        text_taskdeleted.setText("task deleted (*_*;");
        text_taskdeleted.setForeground(new Color(17, 14, 14));
        text_taskdeleted.setFont(new Font("Futura",Font.PLAIN,15));
        text_taskdeleted.setBounds(480,460, 150,40);

        this.setBackground(new Color(157, 168, 231));
        this.setBounds(0,0,1080,720);
        this.setLayout(null);
        this.add(text_Task);
        this.add(text_details);
        this.add(text_starttime);
        this.add(text_endtime);
        this.add(textbox_taskname);
        this.add(textbox_taskdetails);
        this.add(button_backmenu);
        this.add(button_save);
        this.add(button_deletetask);
        this.add(text_tasksaved);
        this.add(text_taskdeleted);
        this.add(text_task_already_exist);
        this.add(text_startdate);
        this.add(text_enddate);
        this.add(textbox_startdate);
        this.add(textbox_enddate);
        this.add(textbox_starttime);
        this.add(textbox_endtime);
    }
}
