/*
Frame to calculate the percentage improvement
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ImprovementDisplay extends JFrame implements ActionListener
{

    //declaring finals
    public static final Color IMPROVE_COLOR = new Color(0, 149, 191);
    public static final Color RACE_COLOR = new Color(0, 171, 205);
    public static final Color PREVIOUS_COLOR = new Color(0, 134, 172);
    public final URL IMPROVE_PATH = getClass().getResource("percentageChange.png");
    private final ImageIcon IMPROVE_IMAGE = new ImageIcon(
            new ImageIcon(IMPROVE_PATH).getImage().getScaledInstance(263, 289, Image.SCALE_DEFAULT));
    //declaring private variables
    private JLabel titleLabel;
    private JLabel timeLabel;
    private JTextField timeField;
    private JLabel raceLabel;
    private JTextField raceField;
    private JLabel previousLabel;
    private JTextField previousField;
    private JLabel answerLabel;
    private JPanel answerPanel;
    private JLabel imageLabel;

    private JButton calculateButton;
    private JButton doneButton;
    private JButton tableButton;

    private JPanel centerPanel;
    private JPanel racePanel;
    private JPanel timePanel;
    private JPanel previousPanel;
    private JPanel buttonPanel;
    private JPanel responsePanel;
    private Box westBox;
    private Box answerBox;
    private Box spaceBox;

    private JMenuBar mainBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenu navigationMenu;
    private JMenuItem homeItem;
    private JMenuItem swimmerItem;
    private JMenuItem selectItem;
    private JMenuItem exitItem;
    private JMenuItem timesItem; //
    private JMenuItem splitsItem;
    String name;

    public ImprovementDisplay(String name)
    {
        //creating frame
        super("Percentage Improvement");
        this.setBounds(200, 200, 700, 500);
        this.getContentPane().setBackground(IMPROVE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.name = name;
        //constructing labels
        this.titleLabel = new JLabel("<html><center>Calculating Percentage Improvement</center></html>", SwingConstants.CENTER);
        this.titleLabel.setForeground(Color.BLACK);
        this.titleLabel.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex. 200 Free): ");
        this.raceLabel.setForeground(Color.BLACK);
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.timeLabel = new JLabel("Current Time (Ex. 1:34.43): ");
        this.timeLabel.setForeground(Color.BLACK);
        this.timeLabel.setFont(Welcome.SMALL_FONT);
        this.previousLabel = new JLabel("Previous Time (Ex.1:35.64): ");
        this.previousLabel.setForeground(Color.BLACK);
        this.previousLabel.setFont(Welcome.SMALL_FONT);

        this.imageLabel = new JLabel(IMPROVE_IMAGE);
        //Constructing textFields
        this.timeField = new JTextField(10);
        this.raceField = new JTextField(10);
        this.previousField = new JTextField(10);
        //creating buttons
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        this.tableButton = new JButton("Improvement Table");
        this.tableButton.addActionListener(this);
        //creating panels and boxes
        this.racePanel = new JPanel();
        this.racePanel.setBackground(RACE_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.timePanel = new JPanel();
        this.timePanel.setBackground(IMPROVE_COLOR);
        this.timePanel.add(timeLabel);
        this.timePanel.add(timeField);
        this.previousPanel = new JPanel();
        this.previousPanel.setBackground(PREVIOUS_COLOR);
        this.previousPanel.add(previousLabel);
        this.previousPanel.add(previousField);

        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(IMPROVE_COLOR);
        this.buttonPanel.add(calculateButton);
        this.buttonPanel.add(tableButton);
        this.buttonPanel.add(doneButton);
        this.responsePanel = new JPanel();
        this.responsePanel.setBackground(InsertSwimmer.INSERT_COLOR);

        this.spaceBox = Box.createVerticalBox();
        this.spaceBox.add(new JLabel(" "));
        this.spaceBox.add(new JLabel(" "));
        this.westBox = Box.createVerticalBox();
        this.westBox.add(racePanel);
        this.westBox.add(timePanel);
        this.westBox.add(previousPanel);
        this.answerBox = Box.createVerticalBox();
        this.answerBox.add(new JLabel(" "));
        this.answerBox.add(responsePanel);

        this.answerPanel = new JPanel(new BorderLayout());
        this.answerPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 4));
        this.answerPanel.setBackground(InsertSwimmer.INSERT_COLOR);
        this.answerPanel.add(answerBox, BorderLayout.CENTER);
        this.answerPanel.add(spaceBox, BorderLayout.SOUTH);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(IMPROVE_COLOR);
        this.centerPanel.add(westBox, BorderLayout.WEST);
        this.centerPanel.add(answerPanel, BorderLayout.SOUTH);
        this.centerPanel.add(imageLabel, BorderLayout.EAST);
        //menu
        this.mainBar = new JMenuBar();
        this.helpMenu = new JMenu("Help");
        this.helpItem = new JMenuItem("Open Help Frame");
        this.helpItem.addActionListener(this);
        this.navigationMenu = new JMenu("Navigation");
        this.homeItem = new JMenuItem("Home");
        this.homeItem.addActionListener(this);
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
        navigationMenu.add(homeItem);
        navigationMenu.add(swimmerItem);
        navigationMenu.add(timesItem);
        navigationMenu.add(splitsItem);
        navigationMenu.add(selectItem);
        helpMenu.add(helpItem);

        mainBar.add(navigationMenu);
        mainBar.add(helpMenu);

        this.setJMenuBar(mainBar);
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //swimmers datatable information
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        //getting the first name
        //won't work if a swimmer has not been inserted into database
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        String name = data.get(0).get(0) + data.get(0).get(1);
        new ImprovementDisplay(name);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Calculate"))
        {
            try
            {
                this.responsePanel.removeAll();
                //assigning values to variables for calculation
                String currentTime = timeField.getText();
                String previousTime = previousField.getText();
                String race = raceField.getText();
                //creating an object from  calculating the percentage improvement class and passing the variables
                CalculatePercentageImprovement calcObj = new CalculatePercentageImprovement(currentTime, previousTime);
                String answer = calcObj.getPercentage(); //getting the answer from the class
                //if the user enters in the times in wrong split
                if (answer.equals("NaN%"))
                {
                    new Warning("<html> Make sure to enter the time in this format [minute:second.milisecond] </html>");
                }
                else
                {
                    //creating answer label to display answer
                    this.answerLabel = new JLabel("<html><center>Current Time: " + currentTime + "   Previous Time: " + previousTime + "<br> Percentage of Improvement: " + answer + "</center></html>");
                    this.answerLabel.setForeground(Color.BLACK);
                    this.answerLabel.setFont(Welcome.SMALL_FONT);
                    //resetting all fields
                    this.raceField.setText("");
                    this.previousField.setText("");
                    this.timeField.setText("");
                    this.responsePanel.add(answerLabel); // add answer to the display
                    this.responsePanel.updateUI();
                    //specific swimmer database improvement
                    String dbName = "SwimTeam";
                    String swimmerTable = name + "Improvement";
                    String[] swimmerColumns =
                    {
                        "Race", "CurrentTime", "PreviousTime", "Improvement"
                    };
                    //SQL statement to insert into swimmers table
                    String dbQuery = "INSERT INTO " + swimmerTable + " VALUES(?,?,?,?)";
                    // connect to Db
                    JavaDatabase objDb = new JavaDatabase(dbName);
                    Connection myDbConn = objDb.getDbConn();

                    try
                    {
                        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
                        ps.setString(1, race);
                        ps.setString(2, currentTime);
                        ps.setString(3, previousTime);
                        ps.setString(4, answer);
                        //execute Query
                        ps.executeUpdate();
                    }
                    catch (SQLException se)
                    {
                        System.out.println("Error inserting data");
                    }
                }
            }
            catch (NumberFormatException nfe)
            {
                new Warning("<html> Make sure you enter in the time correctly with numbers </html> ");
            }
        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (command.equals("Improvement Table"))
        {
            //improveement table information
            String dbName = "SwimTeam";
            String swimmerTable = name + "Improvement";
            String[] swimmerColumns =
            {
                "Race", "CurrentTime", "PreviousTime", "Improvement"
            };
            //open up specific swimmer improvement table
            ImprovementTable improveObj = new ImprovementTable(dbName, swimmerTable, swimmerColumns, name);
            this.dispose();

        }
        if (myComponent == helpItem)
        {
            //help message 
            String message = "To calculate the percentage improvement between"
                    + "two races, enter in the specific race a split for a race by pressing the corresponding buttons. To calculate the split"
                    + " difference press the 'Split Difference' button. The program takes the difference between both times and"
                    + " divides the result by the previous time. Then it multiplies the result by 100 to get the percentage of improvement";
            //open help frame 
            Help helpObj = new Help(message, "Percentage Improvement");
        }
        if (myComponent == homeItem)
        {

            this.dispose();
            Welcome welcomeObj2 = new Welcome();
            //open welcome frame

        }
        if (myComponent == swimmerItem)
        {
            //open swimmer frame
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            //passing swimmer table information
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            //exiting program
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            //opening select name frame
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            this.dispose();
            //opening all times frame using All times table information
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == splitsItem)
        {
            //open al swimmer splits frame 
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }
    }
}
