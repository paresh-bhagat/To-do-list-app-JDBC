import javax.swing.*;
import java.awt.*;
public class TaskButton extends JButton{
    TaskButton(String usr_task)
    {
        ImageIcon imtask = new ImageIcon( new ImageIcon("./images/task.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        this.setIconTextGap(5);
        this.setText(usr_task);
        this.setBorder(null);
        this.setFocusable(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.BOTTOM);
        this.setBackground(new Color(245, 241, 243));
        this.setIcon( imtask );
    }
}
