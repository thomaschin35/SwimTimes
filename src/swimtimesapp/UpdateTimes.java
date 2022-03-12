/*
Update Frame for Times(Best,Goal,Previous)
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class UpdateTimes extends JFrame implements ActionListener
{

    //declaring variables
    private JLabel updateTitle;
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

    private JButton updateButton;
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
    private JMenuItem timesItem; //
    private JMenuItem splitsItem; //

    AllTimes gFrame;

    public UpdateTimes(AllTimes pFrame)
    {
        //creating frame
        super("Update Times");
        this.setBounds(200, 200, 700, 300);
        this.getContentPane().setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gFrame = pFrame;
        //creating labels
        this.updateTitle = new JLabel("Update Times", SwingConstants.CENTER);
        this.updateTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex. 100 Breast) ");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ", SwingConstants.CENTER);
        this.whoLabel.setFont(UpdateSwimmer.SUB_FONT);
        this.firstNameLabel = new JLabel("First Name ", SwingConstants.CENTER);
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name ", SwingConstants.CENTER);
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.bestLabel = new JLabel("Best Time (Ex. 1:34.23)");
        this.bestLabel.setFont(Welcome.SMALL_FONT);
        this.goalLabel = new JLabel("Goal Time (Ex. 1:23.56)");
        this.goalLabel.setFont(Welcome.SMALL_FONT);
        this.previousLabel = new JLabel("Previous Time (Ex. 1:36.49)");
        this.previousLabel.setFont(Welcome.SMALL_FONT);
        //constructing textfields
        this.raceField = new JTextField(15);
        this.firstNameField = new JTextField(10);
        this.lastNameField = new JTextField(10);
        this.bestField = new JTextField(10);
        this.goalField = new JTextField(10);
        this.previousField = new JTextField(10);
        //constructing buttons 
        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.buttonPanel.add(updateButton);
        this.buttonPanel.add(doneButton);
        this.racePanel = new JPanel();
        this.racePanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.bestPanel = new JPanel();
        this.bestPanel.add(bestLabel);
        this.bestPanel.add(bestField);
        this.bestPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.goalPanel = new JPanel();
        this.goalPanel.add(goalLabel);
        this.goalPanel.add(goalField);
        this.goalPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.previousPanel = new JPanel();
        this.previousPanel.add(previousLabel);
        this.previousPanel.add(previousField);
        this.previousPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.firstPanel = new JPanel();
        this.firstPanel.add(firstNameLabel);
        this.firstPanel.add(firstNameField);
        this.firstPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.lastPanel = new JPanel();
        this.lastPanel.add(lastNameLabel);
        this.lastPanel.add(lastNameField);
        this.lastPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(whoLabel);
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        this.eastBox = Box.createVerticalBox();
        this.eastBox.add(racePanel);
        this.eastBox.add(bestPanel);
        this.eastBox.add(goalPanel);
        this.eastBox.add(previousPanel);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
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
        this.add(updateTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //all times table information
        String dbName = "SwimTeam";
        String tableName = "AllTimes";
        String[] columnNames =
        {
            "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
        };
        AllTimes allObj = new AllTimes(dbName, tableName, columnNames);
        new UpdateTimes(allObj);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Update"))
        {
            try
            {
                // first update from all times table
                //all times table information
                String dbName = "SwimTeam";
                String tableName = "AllTimes";
                String[] columnNames =
                {
                    "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                //SQL statement to update from all times table
                String dbQuery = "UPDATE AllTimes SET BestTime=?, GoalTime=?, PreviousTime=?"
                        + " WHERE FirstName=? AND LastName=? AND Race=?";
                //setting database connection
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                //variables used to update
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String race = raceField.getText();
                String bestTime = bestField.getText();
                String goalTime = goalField.getText();
                String previousTime = previousField.getText();

                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setString(1, bestTime);
                    ps.setString(2, goalTime);
                    ps.setString(3, previousTime);
                    ps.setString(4, firstName);
                    ps.setString(5, lastName);
                    ps.setString(6, race);
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
                //Second, update into the specific swimmers table
                //specific swimmers table information
                String swimmerTable = firstName + lastName;
                String[] swimmerColumnNames =
                {
                    "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                //SQL statement to update into specific swimmer table
                String dbQuery2 = "UPDATE " + swimmerTable + " SET BestTime=?, GoalTime=?, PreviousTime=?"
                        + " WHERE Race=?";
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

                    ps.setString(1, bestTime);
                    ps.setString(2, goalTime);
                    ps.setString(3, previousTime);
                    ps.setString(4, race);
                    //execute Query
                    ps.executeUpdate();
                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting Data");
                }
                //resetting text
                this.raceField.setText("");
                this.bestField.setText("");
                this.goalField.setText("");
                this.previousField.setText("");
            }
            catch (NumberFormatException nfe)
            {
                new Warning("Check your input, go to help on the main bar for more information");
            }

        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message to pass to help frame
            String message = " Enter in the first and last name of the swimmer. Enter"
                    + " the race you want to update and then enter in the best/goal/previous times for that race"
                    + "  Finally, press the update button to update the time into the database.";
            Help helpObj3 = new Help(message, "Update Times");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            this.dispose();
            //Swimmers data table information
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            //open swimmers frame
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
            //All Times data table information
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            //open all times frame
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == splitsItem)
        {
            this.dispose();
            //All Splits table information
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            //open all splits frame
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }

    }
}
