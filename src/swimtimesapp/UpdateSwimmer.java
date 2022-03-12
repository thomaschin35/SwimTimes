/*
Update frame for swimmer
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class UpdateSwimmer extends JFrame implements ActionListener
{

    //declaring final variables (colors/fonts)
    public static final Color UPDATE_COLOR = new Color(210, 77, 60);
    public static final Color LAST_NAME_COLOR = new Color(207, 11, 0);
    public static final Color AGE_COLOR = new Color(187, 11, 0);
    public static final Color GROUP_COLOR = new Color(166, 13, 0);
    public static final Font SUB_FONT = new Font("Charter", Font.BOLD, 24);
    //declaring private variables
    private JLabel updateTitle;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel whoLabel;
    private JLabel updateLabel;
    private JLabel ageLabel;
    private JLabel groupLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JTextField groupField;
    private JButton updateButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel firstPanel;
    private JPanel lastPanel;
    private JPanel agePanel;
    private JPanel groupPanel;
    private JPanel buttonPanel;
    private Box updateBox;
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

    Swimmers gFrame;

    public UpdateSwimmer(Swimmers pFrame)
    {
        //creating frame
        super("Update Swimmer");
        this.setBounds(200, 200, 700, 300);
        this.getContentPane().setBackground(UPDATE_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.gFrame = pFrame;
        //creating labels
        this.updateTitle = new JLabel("Update Swimmer", SwingConstants.CENTER);
        this.updateTitle.setFont(Welcome.BIG_FONT);
        this.firstNameLabel = new JLabel("First Name: ");
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name: ");
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ");
        this.whoLabel.setFont(SUB_FONT);
        this.ageLabel = new JLabel("Age (Ex. 34): ");
        this.ageLabel.setFont(Welcome.SMALL_FONT);
        this.groupLabel = new JLabel("Group (Ex. National): ");
        this.groupLabel.setFont(Welcome.SMALL_FONT);
        this.updateLabel = new JLabel("Update Swimmer's Data");
        this.updateLabel.setFont(SUB_FONT);
        //constructing textfields
        this.firstNameField = new JTextField(10);
        this.lastNameField = new JTextField(10);
        this.ageField = new JTextField(5);
        this.groupField = new JTextField(10);
        //constructing buttons 
        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(UPDATE_COLOR);
        this.buttonPanel.add(updateButton);
        this.buttonPanel.add(doneButton);
        this.firstPanel = new JPanel();
        this.firstPanel.setBackground(UPDATE_COLOR);
        this.firstPanel.add(firstNameLabel);
        this.firstPanel.add(firstNameField);
        this.lastPanel = new JPanel();
        this.lastPanel.add(lastNameLabel);
        this.lastPanel.add(lastNameField);
        this.lastPanel.setBackground(LAST_NAME_COLOR);
        this.agePanel = new JPanel();
        this.agePanel.add(ageLabel);
        this.agePanel.add(ageField);
        this.agePanel.setBackground(AGE_COLOR);
        this.groupPanel = new JPanel();
        this.groupPanel.add(groupLabel);
        this.groupPanel.add(groupField);
        this.groupPanel.setBackground(GROUP_COLOR);

        this.westBox = Box.createVerticalBox();
        this.westBox.add(whoLabel);
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        this.updateBox = Box.createVerticalBox();
        this.updateBox.add(updateLabel);
        this.updateBox.add(agePanel);
        this.updateBox.add(groupPanel);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(UPDATE_COLOR);
        this.centerPanel.add(updateBox, BorderLayout.EAST);
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
        //adding to menu
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
        //Swimmers table information
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        new UpdateSwimmer(swimObj);

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
                // first update into swimmers table
                // swimmers table information
                String dbName = "SwimTeam";
                String tableName = "Swimmers";
                String[] columnNames =
                {
                    "FirstName", "LastName", "SwimmerAge", "SwimGroup"
                };
                //SQL information to update from swimmer table
                String dbQuery = "UPDATE Swimmers SET SwimmerAge=?, SwimGroup=?"
                        + " WHERE FirstName=? AND LastName=?";
                // conneect to Db
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                try
                {
                    //prepare Statment
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setInt(1, Integer.parseInt(ageField.getText()));
                    ps.setString(2, groupField.getText());
                    ps.setString(3, firstNameField.getText());
                    ps.setString(4, lastNameField.getText());
                    //excecute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new Swimmers(dbName, tableName, columnNames);
                    this.toFront();
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting data");
                }
                //restting all text fields
                ageField.setText("");
                groupField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");
            }
            catch (NumberFormatException nfe)
            {
                new Warning("Enter in a number for age");
            }
        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message to pass to help frame
            String message = "Enter the first and last name of the swimmer you "
                    + "want to update, then enter in what data to update such as the age and group. Finally, press the update button to update the database."
                    + " Make sure to only use letter for the first and last names, and only use numbers for the age.";
            Help helpObj3 = new Help(message, "Update Swimmer");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            this.dispose();
            //Swimmers table information
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
            //All times data table information
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
            // all splits data table information
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
