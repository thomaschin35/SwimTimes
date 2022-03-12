/*
Delete frame for times
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

public class DeleteTimes extends JFrame implements ActionListener
{

    private JLabel deleteTitle;
    private JLabel raceLabel;
    private JLabel whoLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;

    private JTextField raceField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton deleteButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel racePanel;

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

    public DeleteTimes(AllTimes pFrame)
    {
        //creating frame
        super("Delete Times");
        this.setBounds(200, 200, 630, 280);
        this.getContentPane().setBackground(DeleteSwimmer.DELETE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gFrame = pFrame;
        //creating labels
        this.deleteTitle = new JLabel("Delete Times", SwingConstants.CENTER);
        this.deleteTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex.100 Free)");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ", SwingConstants.CENTER);
        this.whoLabel.setFont(UpdateSwimmer.SUB_FONT);
        this.firstNameLabel = new JLabel("First Name ", SwingConstants.CENTER);
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name ", SwingConstants.CENTER);
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);

        //constructing textfields
        this.raceField = new JTextField(15);
        this.firstNameField = new JTextField(10);
        this.lastNameField = new JTextField(10);

        //constructing buttons 
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);

        //constructing panels and boxes
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(DeleteSwimmer.DELETE_COLOR);
        this.buttonPanel.add(deleteButton);
        this.buttonPanel.add(doneButton);
        this.racePanel = new JPanel();
        this.racePanel.setBackground(DeleteSwimmer.DELETE_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.firstPanel = new JPanel();
        this.firstPanel.add(firstNameLabel);
        this.firstPanel.add(firstNameField);
        this.firstPanel.setBackground(DeleteSwimmer.DELETE_COLOR);
        this.lastPanel = new JPanel();
        this.lastPanel.add(lastNameLabel);
        this.lastPanel.add(lastNameField);
        this.lastPanel.setBackground(DeleteSwimmer.LAST_COLOR);

        this.westBox = Box.createVerticalBox();
        this.westBox.add(whoLabel);
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        this.eastBox = Box.createVerticalBox();
        this.eastBox.add(racePanel);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(DeleteSwimmer.DELETE_COLOR);
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
        this.add(deleteTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //All times data information
        String dbName = "SwimTeam";
        String tableName = "AllTimes";
        String[] columnNames =
        {
            "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
        };
        AllTimes allObj1 = new AllTimes(dbName, tableName, columnNames);
        new DeleteTimes(allObj1);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Delete"))
        {
            try
            {
                // deleting a time for a race for a swimmer
                //using the AllTimes table information
                String dbName = "SwimTeam";
                String tableName = "AllTimes";
                String[] columnNames =
                {
                    "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                //SQL Statement to help delete from database
                String dbQuery = "DELETE FROM AllTimes WHERE FirstName=? AND LastName=? AND Race=?";
                //set Database connection
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                //variables used to delete time from table
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String race = raceField.getText();

                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setString(1, firstName);
                    ps.setString(2, lastName);
                    ps.setString(3, race);
                    //execute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    //open up updated table with data deleted
                    gFrame = new AllTimes(dbName, tableName, columnNames);
                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting Data");
                }
                // deleting a time from a specific swimmers table
                String swimmerTable = firstName + lastName;
                String[] swimmerColumnNames =
                {
                    "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                //SQL statement to help delete from specific swimmer table
                String dbQuery2 = "DELETE FROM " + swimmerTable + " WHERE Race=?";

                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

                    ps.setString(1, race);
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
                // if input is wrong,open up the warning frame
                new Warning("Double Check your input and click on help");
            }
            this.raceField.setText("");
        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message
            String message = "Enter in the first and last name of the swimmer and then "
                    + "enter in the race you want to delete. "
                    + " Finally, press the delete button to delete the time from the database. This will delete"
                    + "the time from the specific swimmers time table as well.";
            // 
            Help helpObj3 = new Help(message, "Delete Times");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            //passing table information to swimmers class
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            //exit program
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            // open select frame 
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            //navigating to see all swimmers times
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            //passing table information to All Times 
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == splitsItem)
        {
            //opening up all splits frame
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            //passing all splits table information
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }

    }
}
