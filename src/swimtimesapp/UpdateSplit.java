/*
Update Frame for Splits

 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class UpdateSplit extends JFrame implements ActionListener
{

    //declaring variables
    private JLabel updateTitle;
    private JLabel raceLabel;
    private JLabel distanceLabel;
    private JLabel whereLabel;
    private JLabel splitLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel whoLabel;
    private JTextField raceField;
    private JTextField distanceField;
    private JTextField splitField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton updateButton;
    private JButton doneButton;
    private JPanel splitPanel;
    private JPanel centerPanel;
    private JPanel firstPanel;
    private JPanel lastPanel;
    private JPanel buttonPanel;
    private JPanel northPanel;
    private Box eastBox;
    private Box westBox;
    //menu
    private JMenuBar mainBar;
    private JMenu helpMenu;
    private JMenuItem helpItem; // ADD LATER
    private JMenu navigationMenu;
    private JMenuItem homeItem;
    private JMenuItem swimmerItem;
    private JMenuItem selectItem;
    private JMenuItem exitItem;
    private JMenuItem timesItem; //
    private JMenuItem splitsItem; //

    AllSplits gFrame;

    public UpdateSplit(AllSplits pFrame)
    {
        //creating frame
        super("Update Split");
        this.setBounds(200, 200, 680, 300);
        this.getContentPane().setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gFrame = pFrame;
        //creating labels
        this.updateTitle = new JLabel("Update Split", SwingConstants.CENTER);
        this.updateTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex. 100 Free)");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.distanceLabel = new JLabel("Distance (Ex. 50)");
        this.distanceLabel.setFont(Welcome.SMALL_FONT);
        this.whereLabel = new JLabel("Update Split where: ");
        this.whereLabel.setFont(UpdateSwimmer.SUB_FONT);
        this.splitLabel = new JLabel("50 Split (Ex. 23.34)");
        this.splitLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ", SwingConstants.CENTER);
        this.whoLabel.setFont(UpdateSwimmer.SUB_FONT);
        this.firstNameLabel = new JLabel("First Name ", SwingConstants.CENTER);
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name ", SwingConstants.CENTER);
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        //constructing textfields
        this.raceField = new JTextField(10);
        this.distanceField = new JTextField(10);
        this.splitField = new JTextField(10);
        this.firstNameField = new JTextField(10);
        this.lastNameField = new JTextField(10);
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
        this.firstPanel = new JPanel();
        this.firstPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.firstPanel.add(raceLabel);
        this.firstPanel.add(raceField);
        this.lastPanel = new JPanel();
        this.lastPanel.add(distanceLabel);
        this.lastPanel.add(distanceField);
        this.lastPanel.setBackground(UpdateSwimmer.LAST_NAME_COLOR);
        this.splitPanel = new JPanel();
        this.splitPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.splitPanel.add(splitLabel);
        this.splitPanel.add(splitField);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(whereLabel);
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        this.eastBox = Box.createVerticalBox();
        this.eastBox.add(new JLabel(" "));
        this.eastBox.add(splitPanel);
        this.northPanel = new JPanel();
        this.northPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.northPanel.add(whoLabel);
        this.northPanel.add(firstNameLabel);
        this.northPanel.add(firstNameField);
        this.northPanel.add(lastNameLabel);
        this.northPanel.add(lastNameField);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(UpdateSwimmer.UPDATE_COLOR);
        this.centerPanel.add(eastBox, BorderLayout.EAST);
        this.centerPanel.add(westBox, BorderLayout.WEST);
        this.centerPanel.add(northPanel, BorderLayout.NORTH);

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
        //all splits table information
        String dbName = "SwimTeam";
        String tableName = "AllSplits";
        String[] columnNames =
        {
            "Name", "Race", "Distance", "Split"
        };
        AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        new UpdateSplit(splitsObj);
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
                // All splits data table information
                //first update from all splits table
                String dbName = "SwimTeam";
                String tableName = "AllSplits";
                String[] columnNames =
                {
                    "Name", "Race", "Distance", "Split"
                };
                //SQL statemnet to update from all splits table
                String dbQuery = "UPDATE AllSplits SET Split=? WHERE Name=? "
                        + "AND Race=? AND Distance=?";
                // conneect to Db
                // set database connection
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                // variables to update
                String split = splitField.getText();
                String name = firstNameField.getText() + " " + lastNameField.getText();
                String race = raceField.getText();
                int distance = Integer.parseInt(distanceField.getText());
                try
                {
                    //prepare Statment
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setString(1, split);
                    ps.setString(2, name);
                    ps.setString(3, race);
                    ps.setInt(4, distance);
                    //excecute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new AllSplits(dbName, tableName, columnNames);
                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting data");
                }
                //Second, update from the specific swimmers table
                // specific swimmer table information
                String swimmerTable = firstNameField.getText() + lastNameField.getText() + "Splits";
                String[] swimmerColumnNames =
                {
                    "Race", "Distance", "Split",
                };
                //SQL statement to update from specific swimmer
                String dbQuery2 = "UPDATE " + swimmerTable + " SET Split=? WHERE Race=? AND Distance=?";
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

                    ps.setString(1, split);
                    ps.setString(2, race);
                    ps.setInt(3, distance);
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
                //if there is something wrong with input, open up warning
                new Warning("Check your numbers, click on help for more info");
            }
        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //message to pass on to help frame
            String message = " To update a split from the database, first. Enter in the first and last name of the swimmer. Then enter the"
                    + " race(Ex. 100 Free) and distance(Ex. 50) you want to update, and enter in a 50 split"
                    + "(Ex. 26.87). Finally, press the update button.";
            Help helpObj2 = new Help(message, "Update Split");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            this.dispose();
            // swimmers table information
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
            //all times data table information
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            // open all times frame
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == splitsItem)
        {
            this.dispose();
            //all splits table information
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
