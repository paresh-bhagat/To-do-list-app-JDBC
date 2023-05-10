package todolistappjdbc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

// left menu where options are present for back, change, password, add task.

@SuppressWarnings("serial")
public class TaskPage extends JPanel{
    JButton button_loginmenu = new JButton();
    JButton button_addtask = new JButton();
    JLabel text_wlcusr = new JLabel();
    JButton button_changepswd = new JButton();
    JButton button_delact = new JButton();
    TaskPage(String usr_name) throws IOException
    {

        /* Welcome user */
    	InputStream stream = this.getClass().getClassLoader().getResourceAsStream("user.png");
        ImageIcon img_user = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        text_wlcusr.setText(usr_name);
        text_wlcusr.setForeground(new Color(47, 43, 44));
        text_wlcusr.setFont(new Font("Futura",Font.ITALIC,15));
        text_wlcusr.setIcon(img_user);
        text_wlcusr.setIconTextGap(4);
        text_wlcusr.setHorizontalTextPosition(JButton.CENTER);
        text_wlcusr.setVerticalTextPosition(JButton.BOTTOM);

        // main menu
        stream = this.getClass().getClassLoader().getResourceAsStream("home.png");
        ImageIcon img_mainmenu = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        button_loginmenu.setBorder(null);
        button_loginmenu.setText("Login Page");
        button_loginmenu.setFocusable(false);
        button_loginmenu.setIconTextGap(4);
        button_loginmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_loginmenu.setBackground(new Color(157, 168, 231));
        button_loginmenu.setIcon(img_mainmenu);
        button_loginmenu.setHorizontalTextPosition(JButton.CENTER);
        button_loginmenu.setVerticalTextPosition(JButton.BOTTOM);

        // add task
        stream = this.getClass().getClassLoader().getResourceAsStream("addtask1.png");
        ImageIcon img_addtask = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        button_addtask.setBorder(null);
        button_addtask.setIconTextGap(4);
        button_addtask.setFocusable(false);
        button_addtask.setText("Add new Task");
        button_addtask.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_addtask.setBackground(new Color(157, 168, 231));
        button_addtask.setIcon( img_addtask );
        button_addtask.setHorizontalTextPosition(JButton.CENTER);
        button_addtask.setVerticalTextPosition(JButton.BOTTOM);

        // change password
        stream = this.getClass().getClassLoader().getResourceAsStream("reset-password.png");
        ImageIcon img_change_pswd = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        button_changepswd.setBorder(null);
        button_changepswd.setIconTextGap(4);
        button_changepswd.setText("Change password");
        button_changepswd.setFocusable(false);
        button_changepswd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_changepswd.setBackground(new Color(157, 168, 231));
        button_changepswd.setIcon( img_change_pswd );
        button_changepswd.setHorizontalTextPosition(JButton.CENTER);
        button_changepswd.setVerticalTextPosition(JButton.BOTTOM);

        // delete account
        stream = this.getClass().getClassLoader().getResourceAsStream("delete-user.png");
        ImageIcon img_delac = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        button_delact.setBorder(null);
        button_delact.setIconTextGap(4);
        button_delact.setText("Delete account");
        button_delact.setFocusable(false);
        button_delact.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_delact.setBackground(new Color(157, 168, 231));
        button_delact.setIcon( img_delac );
        button_delact.setHorizontalTextPosition(JButton.CENTER);
        button_delact.setVerticalTextPosition(JButton.BOTTOM);

        this.setBackground(new Color(157, 168, 231));
        this.setBounds(0,0,180,720);
        this.setLayout(new FlowLayout( FlowLayout.CENTER, 30, 30));
        this.add(text_wlcusr);
        this.add(button_loginmenu);
        this.add(button_addtask);
        this.add(button_changepswd);
        this.add(button_delact);

    }
}
