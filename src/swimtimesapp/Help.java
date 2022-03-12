/*
Help Frame
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Help extends JFrame implements ActionListener
{

    //declaring final variables
    public static final Color HELP_COLOR = new Color(0, 171, 205);
    public static final Color DESCRIP_COLOR = new Color(101, 200, 168);
    public final URL HELP_PATH = getClass().getResource("help.jpg");
    private final ImageIcon HELP_IMAGE = new ImageIcon(
            new ImageIcon(HELP_PATH).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    //declaring variables
    private JLabel helpTitle;
    private JLabel descriptionLabel;
    private JLabel howLabel;
    private JLabel imageLabel;
    private JButton doneButton;
    private JPanel buttonPanel;
    private JPanel centerPanel;
    private JPanel descriptionPanel;
    private JPanel mainPanel;
    private Box westBox;

    public Help(String description, String className)
    {
        //creating frame
        super("Help");
        this.setBounds(300, 200, 400, 600);
        this.getContentPane().setBackground(HELP_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //creating labels
        this.helpTitle = new JLabel("Help - " + className, SwingConstants.CENTER);
        this.helpTitle.setForeground(Color.BLACK);
        this.helpTitle.setFont(Welcome.BIG_FONT);
        this.howLabel = new JLabel("How to use the app: ");
        this.howLabel.setForeground(Color.BLACK);
        this.howLabel.setFont(new Font("Charter", Font.BOLD, 24));
        this.descriptionLabel = new JLabel("<html>" + description + " </html>", SwingConstants.CENTER);
        this.descriptionLabel.setForeground(Color.BLACK);
        this.descriptionLabel.setFont(Welcome.SMALL_FONT);
        this.imageLabel = new JLabel(HELP_IMAGE);

        //creating buttons
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(HELP_COLOR);
        this.buttonPanel.add(doneButton);

        this.descriptionPanel = new JPanel(new BorderLayout());
        this.descriptionPanel.setBackground(DESCRIP_COLOR);
        this.descriptionPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        this.descriptionPanel.add(howLabel, BorderLayout.NORTH);
        this.descriptionPanel.add(descriptionLabel, BorderLayout.CENTER);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(HELP_COLOR);
        this.centerPanel.add(descriptionPanel, BorderLayout.CENTER);
        this.centerPanel.add(imageLabel, BorderLayout.NORTH);

        this.add(helpTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        // main message for the welcome page
        String description = "This app stores all the times of swimmers in a database."
                + " You can insert, delete, or update this data, as well as calculate the percentage of improvement"
                + " between two swims of a swimmer. This app also displays all inputted splits for the races. If you want"
                + " more help for each specific frame, make sure to go to the bar at the top and click 'Open Help Frame'";
        String className = "Welcome";
        new Help(description, className);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("Done"))
        {
            this.dispose();
        }

    }
}
