/*
Warning frame
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class Warning extends JFrame
{

    //declaring final variables
    public final URL WARNING_PATH = getClass().getResource("warningPhoto.png");
    private final ImageIcon WARNING_IMAGE = new ImageIcon(
            new ImageIcon(WARNING_PATH).getImage().getScaledInstance(750, 400, Image.SCALE_DEFAULT));
    public static final Color RED = new Color(185, 0, 0);

    //declaring variables
    private JLabel messageLabel;
    private JLabel imageLabel;

    public Warning(String message)
    {
        //creating frame
        super("WARNING FRAME");
        this.setBounds(100, 100, 600, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().setBackground(RED);
        this.setLayout(new BorderLayout());

        //creating labels
        this.messageLabel = new JLabel("<html><center>" + message + "</center></html>", SwingConstants.CENTER);
        this.messageLabel.setFont(new Font("Rockwell", Font.BOLD, 40));
        this.imageLabel = new JLabel(WARNING_IMAGE);
        //adding on to frame
        this.add(messageLabel, BorderLayout.NORTH);
        this.add(imageLabel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Warning("Enter a Number");
    }

}
