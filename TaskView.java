import javax.swing.*;
import java.awt.*;
public class TaskView extends JPanel{

    TaskView()
    {
        this.setBackground(new Color(236, 241, 240, 255));
        this.setBounds(140,0,940,720);
        this.setLayout(new FlowLayout( FlowLayout.CENTER, 40, 40));
    }
}
