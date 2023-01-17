import javax.swing.*;
import java.awt.*;
public class RegisterPage extends JPanel{
    JButton button_newregister = new JButton();
    JButton button_loginmenu = new JButton();
    JTextField textbox_newus = new JTextField();
    JPasswordField textbox_newpwd = new JPasswordField();
    JLabel text_takenu = new JLabel();
    JLabel text_registered = new JLabel();
    RegisterPage()
    {

        // Create account
        JLabel text_createaccount = new JLabel();
        text_createaccount.setText("Create Account");
        text_createaccount.setForeground(new Color(47, 43, 44));
        text_createaccount.setFont(new Font("Futura",Font.PLAIN,30));
        text_createaccount.setBounds(420,100, 300,80);

        // text new username
        JLabel text_newun = new JLabel();
        text_newun.setText("Username");
        text_newun.setForeground(new Color(47, 43, 44));
        text_newun.setFont(new Font("Futura",Font.PLAIN,15));
        text_newun.setBounds(400,180, 100,40);

        // text-box new username

        textbox_newus.setBounds(400,230,250, 25);
        textbox_newus.setBorder(null);
        textbox_newus.setForeground(new Color(47, 43, 44));
        textbox_newus.setFont(new Font("Futura",Font.BOLD,13));

        // text new password
        JLabel text_newpwd = new JLabel();
        text_newpwd.setText("Password");
        text_newpwd.setForeground(new Color(47, 43, 44));
        text_newpwd.setFont(new Font("Futura",Font.PLAIN,15));
        text_newpwd.setBounds(400,270, 100,40);

        // text-box password

        textbox_newpwd.setBounds(400,310,250, 25);
        textbox_newpwd.setBorder(null);

        // register button
        ImageIcon img_register = new ImageIcon( new ImageIcon("./images/register.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_newregister.setBounds(540, 360, 40,40);
        button_newregister.setBorder(null);
        button_newregister.setFocusable(false);
        button_newregister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_newregister.setBackground(new Color(241, 208, 214));
        button_newregister.setIcon(img_register);

        //  username already taken

        text_takenu.setText("Username already taken");
        text_takenu.setForeground(new Color(232, 29, 64));
        text_takenu.setFont(new Font("Futura",Font.ITALIC,15));
        text_takenu.setBounds(450,420, 400,40);
        text_takenu.setVisible(false);

        // registered

        text_registered.setText("Succesfully Registered");
        text_registered.setForeground(new Color(106, 210, 38));
        text_registered.setFont(new Font("Futura",Font.ITALIC,15));
        text_registered.setBounds(450,420, 400,40);
        text_registered.setVisible(false);

        // main menu
        ImageIcon img_mainmenu = new ImageIcon( new ImageIcon("./images/backbutton.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_loginmenu.setBounds(460, 360, 40,40);
        button_loginmenu.setBorder(null);
        button_loginmenu.setFocusable(false);
        button_loginmenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_loginmenu.setBackground(new Color(241, 208, 214));
        button_loginmenu.setIcon(img_mainmenu);

        this.setBackground(new Color(241, 208, 214));
        this.setBounds(0,0,1080,720);
        this.setLayout(null);
        this.add(text_createaccount);
        this.add(text_newun);
        this.add(textbox_newus);
        this.add(text_newpwd);
        this.add(textbox_newpwd);
        this.add(button_newregister);
        this.add(button_loginmenu);
        this.add(text_takenu);
        this.add(text_registered);

    }
}
