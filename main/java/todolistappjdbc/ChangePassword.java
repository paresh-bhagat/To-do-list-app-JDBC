package todolistappjdbc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
@SuppressWarnings("serial")
public class ChangePassword extends JPanel {

    JButton button_changepassword = new JButton();
    JButton button_back = new JButton();
    JPasswordField textbox_newpwd = new JPasswordField();

    JLabel text_changed = new JLabel();

    ChangePassword() throws IOException{

        // text new password
        JLabel text_newpwd = new JLabel();
        text_newpwd.setText("Enter new Password");
        text_newpwd.setHorizontalAlignment(JLabel.CENTER);
        text_newpwd.setBounds(390,150,300,80);
        text_newpwd.setForeground(new Color(47, 43, 44));
        text_newpwd.setFont(new Font("Futura",Font.PLAIN,25));

        // text-box password
        textbox_newpwd.setBorder(null);
        textbox_newpwd.setBounds(415,230,250,25);

        // change password button
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("check.png"); 
        ImageIcon img_ok = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_changepassword.setBounds(510, 280, 40,40);
        button_changepassword.setBorder(null);
        button_changepassword.setFocusable(false);
        button_changepassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_changepassword.setBackground(new Color(236, 241, 240, 255));
        button_changepassword.setIcon(img_ok);

        // registered

        text_changed.setText("Password Successfully Changed (*_*)");
        text_changed.setHorizontalAlignment(JLabel.CENTER);
        text_changed.setBounds(340,280,400,40);
        text_changed.setForeground(new Color(106, 210, 38));
        text_changed.setFont(new Font("Futura",Font.PLAIN,15));
        text_changed.setVisible(false);

        // back menu
        stream = this.getClass().getClassLoader().getResourceAsStream("backbutton.png");
        ImageIcon img_back = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_back.setBounds(100, 80, 40,40);
        button_back.setBorder(null);
        button_back.setFocusable(false);
        button_back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_back.setBackground(new Color(236, 241, 240, 255));
        button_back.setIcon(img_back);

        this.setBackground(new Color(236, 241, 240, 255));
        this.setBounds(0,0,1080,720);
        this.setLayout(null);
        this.add(text_newpwd);
        this.add(textbox_newpwd);
        this.add(button_changepassword);
        this.add(text_changed);
        this.add(button_back);

    }
}
