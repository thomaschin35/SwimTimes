/*
Insert frame for time
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class InsertTimes extends JFrame implements ActionListener
{

    // declaring variables
    private JLabel insertTitle;
    private JLabel raceLabel;
    private JLabel whoLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel bestLabel;
    private JLabel goalLabel;
    private JLabel previousLabel;
    private JTextField bestField;
    private JTextField goalField;
    private JTextField previousField;

    private JTextField raceField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton insertButton;
    private JButton doneButton;

    private JPanel centerPanel;
    private JPanel racePanel;
    private JPanel bestPanel;
    private JPanel goalPanel;
    private JPanel previousPanel;
    private JPanel firstPanel;
    private JPanel lastPanel;
    private JPanel buttonPanel;
    private Box eastBox;
    private Box westBox;
    //menu
    private JMenuBar mainBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenu navigationMenu;
    private JMenuItem homeItem;
    private JMenuItem swimmerItem;
    private JMenuItem selectItem;
    private JMenuItem exitItem;
    private JMenuItem timesItem;
    private JMenuItem splitsItem;
    AllTimes gFrame;
    String fullName;

    public InsertTimes(AllTimes pFrame)
    {
        //creating frame
        super("Insert Times");
        this.setBounds(200, 200, 620, 300);
        this.getContentPane().setBackground(InsertSwimmer.INSERT_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gFrame = pFrame;
        //creating labels
        this.insertTitle = new JLabel("Insert Times", SwingConstants.CENTER);
        this.insertTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex. 100 Free)");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ", SwingConstants.CENTER);
        this.whoLabel.setFont(UpdateSwimmer.SUB_FONT);
        this.firstNameLabel = new JLabel("First Name ", SwingConstants.CENTER);
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name ", SwingConstants.CENTER);
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.bestLabel = new JLabel("Best Time (1:23.43)");
        this.bestLabel.setFont(Welcome.SMALL_FONT);
        this.goalLabel = new JLabel("Goal Time (1:19.43");
        this.goalLabel.setFont(Welcome.SMALL_FONT);
        this.previousLabel = new JLabel("Previous Time (1:26.34)");
        this.previousLabel.setFont(Welcome.SMALL_FONT);

        //constructing textfields
        this.raceField = new JTextField(15);
        this.firstNameField = new JTextField(10);
        this.lastNameField = new JTextField(10);
        this.bestField = new JTextField(10);
        this.goalField = new JTextField(10);
        this.previousField = new JTextField(10);
        //constructing buttons 
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(InsertSwimmer.INSERT_COLOR);
        this.buttonPanel.add(insertButton);
        this.buttonPanel.add(doneButton);
        this.racePanel = new JPanel();
        this.racePanel.setBackground(InsertSwimmer.INSERT_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.bestPanel = new JPanel();
        this.bestPanel.add(bestLabel);
        this.bestPanel.add(bestField);
        this.bestPanel.setBackground(InsertSwimmer.LAYER_1_COLOR);
        this.goalPanel = new JPanel();
        this.goalPanel.add(goalLabel);
        this.goalPanel.add(goalField);
        this.goalPanel.setBackground(InsertSwimmer.LAYER_2_COLOR);
        this.previousPanel = new JPanel();
        this.previousPanel.add(previousLabel);
        this.previousPanel.add(previousField);
        this.previousPanel.setBackground(InsertSwimmer.LAYER_3_COLOR);
        this.firstPanel = new JPanel();
        this.firstPanel.add(firstNameLabel);
        this.firstPanel.add(firstNameField);
        this.firstPanel.setBackground(InsertSwimmer.LAYER_1_COLOR);
        this.lastPanel = new JPanel();
        this.lastPanel.add(lastNameLabel);
        this.lastPanel.add(lastNameField);
        this.lastPanel.setBackground(InsertSwimmer.LAYER_2_COLOR);

        this.westBox = Box.createVerticalBox();
        this.westBox.add(whoLabel);
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        //  this.westBox.add(radioBox);
        this.eastBox = Box.createVerticalBox();
        this.eastBox.add(racePanel);
        this.eastBox.add(bestPanel);
        this.eastBox.add(goalPanel);
        this.eastBox.add(previousPanel);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(InsertSwimmer.INSERT_COLOR);
        this.centerPanel.add(eastBox, BorderLayout.EAST);
        this.centerPanel.add(westBox, BorderLayout.WEST);

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
        this.add(insertTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //All times table database information
        String dbName = "SwimTeam";
        String tableName = "AllTimes";
        String[] columnNames =
        {
            "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
        };
        AllTimes allObj = new AllTimes(dbName, tableName, columnNames);
        new InsertTimes(allObj);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();

        if (command.equals("Insert"))
        {
            try
            {
                //inserting data into all times table 
                String dbName = "SwimTeam";
                String tableName = "AllTimes";
                String[] columnNames =
                {
                    "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                //SQL statement to insert
                String dbQuery = "INSERT INTO AllTimes VALUES(?,?,?,?,?,?)";
                //creating database connection
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                // variables to insert
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String race = raceField.getText();
                String bestTime = bestField.getText();
                String goalTime = goalField.getText();
                String previousTime = previousField.getText();

                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setString(1, firstName);
                    ps.setString(2, lastName);
                    ps.setString(3, race);
                    ps.setString(4, bestTime);
                    ps.setString(5, goalTime);
                    ps.setString(6, previousTime);
                    //execute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new AllTimes(dbName, tableName, columnNames);
                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting Data");
                }
                //resetting text
                raceField.setText("");
                bestField.setText("");
                goalField.setText("");
                previousField.setText("");
                //inserting also into specific swimmer table
                String swimmerTable = firstName + lastName;
                String[] swimmerColumnNames =
                {
                    "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                //SQL statement to insert into specific table
                String dbQuery2 = "INSERT INTO " + swimmerTable + " VALUES(?,?,?,?)";
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

                    ps.setString(1, race);
                    ps.setString(2, bestTime);
                    ps.setString(3, goalTime);
                    ps.setString(4, previousTime);
                    //execute Query
                    ps.executeUpdate();

                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting Data");
                }
            }
            catch (NumberFormatException nfe)
            {
                new Warning("Check your input, click on help for more info");
            }

        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message
            String message = " Enter in the first and last name of the swimmer "
                    + "then enter in the race and the best/goal/previous times. Make sure to write the times in this format - Minute:Second.Milisecond"
                    + "  Finally, press the insert button to insert the time into the database.";
            //pass help message to help frame
            Help helpObj3 = new Help(message, "Insert Times");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            this.dispose();
            //swimmers table database information
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            //opening swimmers frame
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }

        if (myComponent == timesItem)
        {
            this.dispose();
            //Alltimes table information
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
            this.dispose();
            //All splits table information
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            //opening all splits table
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }

    }
}
