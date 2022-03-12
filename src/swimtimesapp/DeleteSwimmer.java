/*
Delete Frame to delete a swimmer from database
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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

public class DeleteSwimmer extends JFrame implements ActionListener
{

    //declaring constants
    public static final Color DELETE_COLOR = new Color(253, 161, 61);
    public static final Color LAST_COLOR = new Color(252, 136, 0);
    //declaring variables
    private JLabel deleteTitle;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel whereLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton deleteButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel firstPanel;
    private JPanel lastPanel;
    private Box buttonBox;
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

    Swimmers gFrame;

    public DeleteSwimmer(Swimmers pFrame)
    {
        //creating frame
        super("Delete Swimmer");
        this.setBounds(200, 200, 510, 300);
        this.getContentPane().setBackground(DELETE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.gFrame = pFrame;
        //creating labels
        this.deleteTitle = new JLabel("Delete Swimmer", SwingConstants.CENTER);
        this.deleteTitle.setFont(Welcome.BIG_FONT);
        this.firstNameLabel = new JLabel("First Name: ");
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name: ");
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.whereLabel = new JLabel("Delete swimmer where: ");
        this.whereLabel.setFont(UpdateSwimmer.SUB_FONT);
        //textfields
        this.firstNameField = new JTextField(15);
        this.lastNameField = new JTextField(15);
        //constructing buttons 
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.firstPanel = new JPanel();
        this.firstPanel.setBackground(DELETE_COLOR);
        this.firstPanel.add(firstNameLabel);
        this.firstPanel.add(firstNameField);
        this.lastPanel = new JPanel();
        this.lastPanel.add(lastNameLabel);
        this.lastPanel.add(lastNameField);
        this.lastPanel.setBackground(LAST_COLOR);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(whereLabel);
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        this.buttonBox = Box.createVerticalBox();
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(deleteButton);
        this.buttonBox.add(doneButton);
        this.buttonBox.add(new JLabel(" "));
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(DELETE_COLOR);
        this.centerPanel.add(buttonBox, BorderLayout.EAST);
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
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //swimmers table information
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        new DeleteSwimmer(swimObj);
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
                //variables used to delete from database
                String firstName;
                String lastName;
                firstName = firstNameField.getText();
                lastName = lastNameField.getText();
                String name = firstName + lastName;
                String splitName = firstName + " " + lastName;
                //resetting the text in the textfield
                firstNameField.setText("");
                lastNameField.setText("");
                // first deleting swimmer from swimmers table
                String dbName = "SwimTeam";
                String tableName = "Swimmers";
                String[] columnNames =
                {
                    "FirstName", "LastName", "SwimmerAge", "SwimGroup"
                }; // using swimmers table information
                //SQL statement to delete from database
                String dbQuery = "DELETE FROM Swimmers WHERE FirstName=? AND LastName=? ";
                // deleting the specific swimmer from all tables
                String dbQueryTimes = "DELETE FROM AllTimes WHERE FirstName=? AND LastName=? ";
                String dbQuerySplits = "DELETE FROM AllSplits WHERE Name=? ";
                String deleteTableQuery = "DROP TABLE " + name;
                String deleteSplitQuery = "DROP TABLE " + name + "Splits ";
                String deleteImprovementQuery = "DROP TABLE " + name + "Improvement";
                String deleteDifferenceQuery = "DROP TABLE " + name + "SplitDifferences";
                JavaDatabase objDb = new JavaDatabase(dbName);
                //accessing database
                Connection myDbConn = objDb.getDbConn();

                try
                {
                    //prepare Statment
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
                    ps.setString(1, firstName);
                    ps.setString(2, lastName);

                    //excecute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new Swimmers(dbName,
                            tableName, columnNames);

                    this.toFront();
                }
                catch (SQLException se)
                {
                    //if database not working, print this
                    System.out.println("Error deleting Data");
                }
                try
                {
                    //prepare Statment
                    PreparedStatement ps = myDbConn.prepareStatement(dbQueryTimes);
                    ps.setString(1, firstName);
                    ps.setString(2, lastName);

                    //excecute Query
                    ps.executeUpdate();

                }
                catch (SQLException se)
                {
                    //if database not working, print this
                    System.out.println("Error deleting Data");
                }
                try
                {
                    //prepare Statment
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuerySplits);
                    ps.setString(1, splitName);

                    //excecute Query
                    ps.executeUpdate();

                }
                catch (SQLException se)
                {
                    //if database not working, print this
                    System.out.println("Error deleting  Data");
                }
                //deleting specific swimmer tables
                try
                {
                    //delete specific siwmmer table
                    PreparedStatement ps = myDbConn.prepareStatement(deleteTableQuery);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
                    System.out.println("Error deleting all data in table");
                }
                try
                {
                    //delete specific siwmmer split table
                    PreparedStatement ps = myDbConn.prepareStatement(deleteSplitQuery);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
                    System.out.println("Error deleting all data in table");
                }
                try
                {
                    //delete specific siwmmer Improvement table
                    PreparedStatement ps = myDbConn.prepareStatement(deleteImprovementQuery);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
                    System.out.println("Error deleting all data in table");
                }
                try
                {
                    //delete specific siwmmer difference table
                    PreparedStatement ps = myDbConn.prepareStatement(deleteDifferenceQuery);
                    ps.executeUpdate();
                }
                catch (SQLException se)
                {
                    System.out.println("Error deleting all data in table");
                }
            }
            catch (NumberFormatException nfe)
            {
                new Warning("Enter Numbers");
            }
        }
        if (command.equals("Done"))
        {
            //once done is pressed, the frame will close
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message
            String message = "To delete a swimmer from the database, enter the"
                    + "first and last name and press delete. This will delete all of the specific swimmers data (times/splits) and tables for their times and split."
                    + " Make sure to only use letters for"
                    + " the first and last names";
            // navigate to help frame with specific message
            Help helpObj2 = new Help(message, "Delete Swimmer");
        }
        if (myComponent == homeItem)
        {
            //navigate to the home page
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            //navigate to the swimmer page
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            //using swimmers table information to navigate 
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            //exits program completely
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            //opens up select frame
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            //navigates to see all swimmers times
            //using AllTimes data table information
            this.dispose();
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
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            //Navigates to see all swimmers race splits
            //using the AllSplits table information
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }

    }
}
