/*
Delete Frame to delete split difference from database
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

public class DeleteDifference extends JFrame implements ActionListener
{

    //declaring variables
    private JLabel deleteTitle;
    private JLabel raceLabel;
    private JLabel distancesLabel;
    private JLabel whereLabel;
    private JTextField raceField;
    private JTextField distancesField;
    private JButton deleteButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel racePanel;
    private JPanel distancePanel;
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
    String name;
    SplitDiffTable gFrame;

    public DeleteDifference(SplitDiffTable pFrame, String name)
    {
        //creating frame
        super("Delete Difference");
        this.setBounds(400, 200, 520, 200);
        this.getContentPane().setBackground(DeleteSwimmer.DELETE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.name = name;
        this.gFrame = pFrame;
        //creating labels
        this.deleteTitle = new JLabel("Delete Percentage Improvement", SwingConstants.CENTER);
        this.deleteTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex. 100 Free): ");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.distancesLabel = new JLabel("Distance (Ex. 50 - 100)");
        this.distancesLabel.setFont(Welcome.SMALL_FONT);
        this.whereLabel = new JLabel("Delete where: ");
        this.whereLabel.setFont(UpdateSwimmer.SUB_FONT);
        //textfields
        this.raceField = new JTextField(15);
        this.distancesField = new JTextField(10);
        //constructing buttons 
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.racePanel = new JPanel();
        this.racePanel.setBackground(DeleteSwimmer.LAST_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.distancePanel = new JPanel();
        this.distancePanel.add(distancesLabel);
        this.distancePanel.add(distancesField);
        this.distancePanel.setBackground(DeleteSwimmer.LAST_COLOR);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(whereLabel);
        this.westBox.add(racePanel);
        this.westBox.add(distancePanel);
        this.buttonBox = Box.createVerticalBox();
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(deleteButton);
        this.buttonBox.add(doneButton);
        this.buttonBox.add(new JLabel(" "));
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(DeleteSwimmer.DELETE_COLOR);
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
        //swimmers data base info
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        //grabbing the first name in dataList
        //won't work unless a swimmer is inserted
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        String name = data.get(0).get(0) + data.get(0).get(1);
        String swimmerTable = name + "SplitDifferences";
        String[] swimmerColumns =
        {
            "Race", "Distances", "Difference"
        };
        SplitDiffTable diffTableObj = new SplitDiffTable(dbName, swimmerTable, swimmerColumns, name);
        new DeleteDifference(diffTableObj, name);
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
                String race = raceField.getText();
                String distances = distancesField.getText();
                //resetting the text in the textfield
                distancesField.setText("");
                // deleting swimmer data from swimmers table
                String dbName = "SwimTeam";
                String tableName = this.name + "SplitDifferences";
                String[] columnNames =
                {
                    "Race", "Distances", "Difference"
                }; // using swimmers table information
                //SQL statement to delete from database
                String dbQuery = "DELETE FROM " + tableName + " WHERE Race=? AND Distances=?";

                JavaDatabase objDb = new JavaDatabase(dbName);
                //accessing database
                Connection myDbConn = objDb.getDbConn();
                try
                {
                    //prepare Statment
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
                    ps.setString(1, race);
                    ps.setString(2, distances);

                    //excecute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new SplitDiffTable(dbName,
                            tableName, columnNames, name);

                    this.toFront();
                }
                catch (SQLException se)
                {
                    //if database not working, print this
                    System.out.println("Error deleting Data");
                }

            }
            catch (NumberFormatException nfe)
            {
                new Warning("Check your input, make sure you follow the right format");
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
            String message = "To delete, first type in the race (Ex. 100 Free) and the distances (Ex. 50 - 100). This will delete all the data in the row including"
                    + " the split difference. Once you are sure you want to delete that row"
                    + ", click on the delete button. Press the done button when you are done deleting, which will return you back to SplitDiff table.";
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
