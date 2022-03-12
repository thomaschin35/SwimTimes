/*
Delete frame for split
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class DeleteSplit extends JFrame implements ActionListener
{

    // delcaring variables
    private JLabel deleteTitle;
    private JLabel raceLabel;
    private JLabel distanceLabel;
    private JLabel whereLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel whoLabel;
    private JTextField raceField;
    private JTextField distanceField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton deleteButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel racePanel;
    private JPanel distancePanel;
    private JPanel northPanel;
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
    private JMenuItem timesItem; //
    private JMenuItem splitsItem; //

    AllSplits gFrame;

    public DeleteSplit(AllSplits pFrame)
    {
        //creating frame
        super("Delete Split");
        this.setBounds(200, 200, 510, 300);
        this.getContentPane().setBackground(DeleteSwimmer.DELETE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gFrame = pFrame;
        //creating labels
        this.deleteTitle = new JLabel("Delete Split", SwingConstants.CENTER);
        this.deleteTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex.100 Free)");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.distanceLabel = new JLabel("Distance (Ex. 50)");
        this.distanceLabel.setFont(Welcome.SMALL_FONT);
        this.whereLabel = new JLabel("Delete Split where: ");
        this.whereLabel.setFont(UpdateSwimmer.SUB_FONT);
        this.firstNameLabel = new JLabel("First Name ");
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name ");
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ");
        this.whoLabel.setFont(UpdateSwimmer.SUB_FONT);
        //constructing textfields
        this.raceField = new JTextField(15);
        this.distanceField = new JTextField(15);
        this.firstNameField = new JTextField(7);
        this.lastNameField = new JTextField(7);
        //constructing buttons 
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.racePanel = new JPanel();
        this.racePanel.setBackground(DeleteSwimmer.DELETE_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.distancePanel = new JPanel();
        this.distancePanel.add(distanceLabel);
        this.distancePanel.add(distanceField);
        this.distancePanel.setBackground(DeleteSwimmer.LAST_COLOR);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(whereLabel);
        this.westBox.add(racePanel);
        this.westBox.add(distancePanel);
        this.buttonBox = Box.createVerticalBox();
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(deleteButton);
        this.buttonBox.add(doneButton);
        this.buttonBox.add(new JLabel(" "));
        this.northPanel = new JPanel();
        this.northPanel.setBackground(DeleteSwimmer.DELETE_COLOR);
        this.northPanel.add(whoLabel);
        this.northPanel.add(firstNameLabel);
        this.northPanel.add(firstNameField);
        this.northPanel.add(lastNameLabel);
        this.northPanel.add(lastNameField);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(DeleteSwimmer.DELETE_COLOR);
        this.centerPanel.add(buttonBox, BorderLayout.EAST);
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
        this.add(deleteTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
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
        new DeleteSplit(splitsObj);
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
                //deleting data from AllSplit table
                String dbName = "SwimTeam";
                String tableName = "AllSplits";
                String[] columnNames =
                {
                    "Name", "Race", "Distance", "Split"
                };
                // SQL code to help delete from database
                String dbQuery = "DELETE FROM AllSplits WHERE Name=? AND Race=? AND Distance=?";
                //accessing database
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                //variables to use in the SQL
                String name = firstNameField.getText() + " " + lastNameField.getText();
                String race = raceField.getText();
                int distance = Integer.parseInt(distanceField.getText());
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setString(1, name);
                    ps.setString(2, race);
                    ps.setInt(3, distance);
                    //execute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new AllSplits(dbName, tableName, columnNames);
                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting Data");
                }
                //now deleting from the specific swimmers table
                String swimmerTable = firstNameField.getText() + lastNameField.getText() + "Splits";
                String[] swimmerColumnNames =
                {
                    "Race", "Distance", "Split",
                };
                //SQL statement to delete from the specific swimmer table
                String dbQuery2 = "DELETE FROM " + swimmerTable + " WHERE Race=? AND Distance=?";
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

                    ps.setString(1, race);
                    ps.setInt(2, distance);
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
                // if user enter something wrong, warning frame will pop up
                new Warning("Check your input, click on help for more info");
            }
            this.distanceField.setText("");
        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message 
            String message = "To delete a split from the database, first enter in the first and last name of the swimmer. Then enter the"
                    + " race(Ex. 100 Free) and distance(Ex. 50) and press delete. This will delete this speicific split in all tables for this swimmer";
            //open helpFrame 
            Help helpObj2 = new Help(message, "Delete Split");
        }
        if (myComponent == homeItem)
        {
            //navigate back to the home page
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            //navigate to the swimmers page
            //using swimmers table information
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            //exit program
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            //open up the select Name frame
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            // navigate to see all Swimmers times
            // using the AllTimes datatable information
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
            //Navigate to see all swimmers splits
            //using the Allsplits data table information
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }
    }
}
