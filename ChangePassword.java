import javax.swing.*;
import java.awt.*;
public class ChangePassword extends JPanel {

    JButton button_changepassword = new JButton();
    JButton button_back = new JButton();
    JPasswordField textbox_newpwd = new JPasswordField();

    JLabel text_changed = new JLabel();

    ChangePassword(){

        // text new password
        JLabel text_newpwd = new JLabel();
        text_newpwd.setText("Enter new Password");
        text_newpwd.setBounds(410,150,300,80);
        text_newpwd.setForeground(new Color(47, 43, 44));
        text_newpwd.setFont(new Font("Futura",Font.PLAIN,25));

        // text-box password
        textbox_newpwd.setBorder(null);
        textbox_newpwd.setBounds(400,230,250,25);

        // change password button
        ImageIcon img_ok = new ImageIcon( new ImageIcon("./images/check.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_changepassword.setBounds(520, 280, 40,40);
        button_changepassword.setBorder(null);
        button_changepassword.setFocusable(false);
        button_changepassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_changepassword.setBackground(new Color(241, 208, 214));
        button_changepassword.setIcon(img_ok);

        // registered

        text_changed.setText("Password Succesfully Changed");
        text_changed.setBounds(410,320,230,40);
        text_changed.setForeground(new Color(106, 210, 38));
        text_changed.setFont(new Font("Futura",Font.ITALIC,15));
        text_changed.setVisible(false);

        // back menu
        ImageIcon img_back = new ImageIcon( new ImageIcon("./images/backbutton.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button_back.setBounds(470, 280, 40,40);
        button_back.setBorder(null);
        button_back.setFocusable(false);
        button_back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button_back.setBackground(new Color(241, 208, 214));
        button_back.setIcon(img_back);

        this.setBackground(new Color(241, 208, 214));
        this.setBounds(0,0,1080,720);
        this.setLayout(null);
        this.add(text_newpwd);
        this.add(textbox_newpwd);
        this.add(button_changepassword);
        this.add(text_changed);
        this.add(button_back);

    }
}
