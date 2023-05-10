package todolistappjdbc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

// task button after the login page

@SuppressWarnings("serial")
public class TaskButton extends JButton{
    TaskButton(String usr_task) throws IOException
    {
    	InputStream stream = this.getClass().getClassLoader().getResourceAsStream("task.png");
        ImageIcon imtask = new ImageIcon( new ImageIcon(ImageIO.read(stream)).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        this.setIconTextGap(5);
        this.setText(usr_task);
        this.setBorder(null);
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.BOTTOM);
        this.setBackground(new Color(236, 241, 240, 255));
        this.setIcon( imtask );
    }
}
