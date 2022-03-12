/*
This frame is the first frame that the user sees.
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Welcome extends JFrame implements ActionListener
{

    //declaring variables 
    public static final Color MAIN_COLOR = new Color(207, 11, 0);
    public static final Color TITLE_COLOR = new Color(255, 0, 107);
    public static final Font BIG_FONT = new Font("Hoefler Text", Font.PLAIN, 38);
    public static final Font SMALL_FONT = new Font("Charter", Font.PLAIN, 20);
    public final URL WELCOME_PATH = getClass().getResource("WelcomePictureSwim.jpg");
    private final ImageIcon WELCOME_IMAGE = new ImageIcon(
            new ImageIcon(WELCOME_PATH).getImage().getScaledInstance(443, 249, Image.SCALE_DEFAULT));
    private JLabel title;
    private JLabel subtitle;
    private JLabel image;
    //image 
    private JButton swimmerButton;
    private JButton exitButton;
    private Box mainBox;
    private JPanel buttonPanel;
    private JPanel northPanel;
    //menu
    private JMenuBar mainBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenu navigationMenu;
    private JMenuItem swimmerItem;
    private JMenuItem selectItem;
    private JMenuItem exitItem;
    private JMenuItem timesItem;
    private JMenuItem splitsItem;

    public Welcome()
    {
        // constructing frame
        super("Welcome");
        this.setBounds(200, 200, 600, 500);
        this.getContentPane().setBackground(MAIN_COLOR);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //constructing labels 
        this.title = new JLabel("<html><center> Swimmer Times App </center> </html>", SwingConstants.CENTER);
        this.title.setForeground(Color.BLACK);
        this.title.setFont(BIG_FONT);
        this.subtitle = new JLabel("<html> <center>This app helps organize a swim teams swimmers and their times."
                + " It also compares the times of different races using statistics </center> </html>", SwingConstants.CENTER);
        this.subtitle.setForeground(Color.BLACK);
        this.subtitle.setFont(SMALL_FONT);
        this.image = new JLabel(WELCOME_IMAGE, SwingConstants.CENTER);
        //constructing buttons
        this.swimmerButton = new JButton("Swimmers");
        this.swimmerButton.addActionListener(this);
        this.exitButton = new JButton("Exit");
        this.exitButton.addActionListener(this);
        //constructing box and panel
        this.northPanel = new JPanel(new BorderLayout());
        this.northPanel.setBackground(TITLE_COLOR);
        this.northPanel.add(title, BorderLayout.NORTH);
        this.northPanel.add(subtitle, BorderLayout.SOUTH);
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(MAIN_COLOR);
        this.buttonPanel.add(swimmerButton);
        this.buttonPanel.add(exitButton);
        //menu
        this.mainBar = new JMenuBar();
        this.helpMenu = new JMenu("Help");
        this.helpItem = new JMenuItem("Open Help Frame");
        this.helpItem.addActionListener(this);
        this.navigationMenu = new JMenu("Navigation");
        this.swimmerItem = new JMenuItem("Swimmers");
        this.swimmerItem.addActionListener(this);
        this.selectItem = new JMenuItem("Select Swimmers");
        this.selectItem.addActionListener(this);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);
        this.splitsItem = new JMenuItem("All Swimmers Splits");
        this.splitsItem.addActionListener(this);
        this.timesItem = new JMenuItem("All Swimmers Times");
        this.timesItem.addActionListener(this);

        navigationMenu.add(exitItem);
        navigationMenu.add(swimmerItem);
        navigationMenu.add(timesItem);
        navigationMenu.add(splitsItem);
        navigationMenu.add(selectItem);
        helpMenu.add(helpItem);

        mainBar.add(navigationMenu);
        mainBar.add(helpMenu);

        this.setJMenuBar(mainBar);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(image, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Welcome();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Swimmers"))
        {
            this.dispose();
            //accesses the swimmers table
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (command.equals("Exit"))
        {
            //exits the program completely
            System.exit(0);
        }
        if (myComponent == helpItem)
        {
            //help message that will be displayed 
            String message = " This app stores all the times of swimmers in a database."
                    + " You can insert, delete, or update this data, as well as calculate the percentage of improvement"
                    + " between two swims of a swimmer. This app also displays all inputted splits for the races. If you want"
                    + " more help for each specific frame, make sure to go to the bar at the top and click 'Open Help Frame'";;
            Help helpObj = new Help(message, "Welcome");
        }
        if (myComponent == exitItem)
        {
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            //open up the select name frame
            SelectName selectObj = new SelectName();
        }
        if (myComponent == timesItem)
        {
            this.dispose();
            // all times table information
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            //opening up the all times frame with the all times table
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == splitsItem)
        {
            this.dispose();
            //All splits table information
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            // open all splits frame with the AllSplits table information
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }
    }

}
