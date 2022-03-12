/*
Insert Frame for Swimmer
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class InsertSwimmer extends JFrame implements ActionListener
{

    //declaring final variables
    public static final Color INSERT_COLOR = new Color(242, 215, 83);
    public static final Color LAYER_1_COLOR = new Color(239, 204, 0);
    public static final Color LAYER_2_COLOR = new Color(214, 184, 2);
    public static final Color LAYER_3_COLOR = new Color(191, 163, 4);
    //declaring variables
    private JLabel insertTitle;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel ageLabel;
    private JLabel groupLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ageField;
    private JTextField groupField;
    private JButton insertButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel firstPanel;
    private JPanel lastPanel;
    private JPanel agePanel;
    private JPanel groupPanel;
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
    private ArrayList<ArrayList<String>> dataList;
    private ArrayList<String> names = new ArrayList<>(10);
    String fullName;
    Swimmers gFrame;

    public InsertSwimmer(Swimmers pFrame)
    {
        //creating frame
        super("Insert Swimmer");
        this.setBounds(200, 200, 450, 350);
        this.getContentPane().setBackground(INSERT_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.gFrame = pFrame;
        //creating labels
        this.insertTitle = new JLabel("Insert Swimmer", SwingConstants.CENTER);
        this.insertTitle.setFont(Welcome.BIG_FONT);
        this.firstNameLabel = new JLabel("First Name: ");
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name: ");
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.ageLabel = new JLabel("Age (Ex. 13): ");
        this.ageLabel.setFont(Welcome.SMALL_FONT);
        this.groupLabel = new JLabel("Group (Ex. National): ");
        this.groupLabel.setFont(Welcome.SMALL_FONT);
        //textfields
        this.firstNameField = new JTextField(15);
        this.lastNameField = new JTextField(15);
        this.ageField = new JTextField(5);
        this.groupField = new JTextField(10);
        //constructing buttons 
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.firstPanel = new JPanel();
        this.firstPanel.setBackground(INSERT_COLOR);
        this.firstPanel.add(firstNameLabel);
        this.firstPanel.add(firstNameField);
        this.lastPanel = new JPanel();
        this.lastPanel.add(lastNameLabel);
        this.lastPanel.add(lastNameField);
        this.lastPanel.setBackground(LAYER_1_COLOR);
        this.agePanel = new JPanel();
        this.agePanel.add(ageLabel);
        this.agePanel.add(ageField);
        this.agePanel.setBackground(LAYER_2_COLOR);
        this.groupPanel = new JPanel();
        this.groupPanel.add(groupLabel);
        this.groupPanel.add(groupField);
        this.groupPanel.setBackground(LAYER_3_COLOR);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(firstPanel);
        this.westBox.add(lastPanel);
        this.westBox.add(agePanel);
        this.westBox.add(groupPanel);
        this.buttonBox = Box.createVerticalBox();
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(insertButton);
        this.buttonBox.add(doneButton);
        this.buttonBox.add(new JLabel(" "));
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(INSERT_COLOR);
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
        this.add(insertTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        // database information for swimmers frame
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        new InsertSwimmer(swimObj);
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
                //inserting into swimmers table
                String dbName = "SwimTeam";
                String tableName = "Swimmers";
                String[] columnNames =
                {
                    "FirstName", "LastName", "SwimmerAge", "SwimGroup"
                };
                //SQL statement to insert into swimmers table
                String dbQuery = "INSERT INTO Swimmers VALUES(?,?,?,?)";
                // connect to Db
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                //variables used to insert
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String group = groupField.getText();
                //resetting text fields
                firstNameField.setText("");
                lastNameField.setText("");
                ageField.setText("");
                groupField.setText("");

                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
                    ps.setString(1, firstName);
                    ps.setString(2, lastName);
                    ps.setInt(3, age);
                    ps.setString(4, group);
                    //execute Query
                    ps.executeUpdate();

                    gFrame.dispose();
                    gFrame = new Swimmers(dbName, tableName, columnNames);
                    this.toFront();
                    //creating datalist to look through it and create tables for each swimmer 
                    dataList = objDb.getData(tableName, columnNames);
                    for (int i = 0; i < dataList.size(); i++)
                    {
                        //creating tables for each swimmer as they are inserted
                        fullName = dataList.get(i).get(0) + dataList.get(i).get(1);
                        //creating specific name time table
                        String nameTable = "CREATE TABLE " + fullName + " "
                                + "(Race varchar(50), BestTime varchar(50), GoalTime varchar(50), PreviousTime varchar(50))";
                        objDb.createTable(nameTable, dbName);
                        //creating specific name split table
                        String nameSplitTable = "CREATE TABLE " + fullName + "Splits "
                                + "(Race varchar(50), Distance int, Split varchar(50))";
                        objDb.createTable(nameSplitTable, dbName);
                        //creating specific name improvement table
                        String nameImprovementTable = "CREATE TABLE " + fullName + "Improvement "
                                + "(Race varchar(50), CurrentTime varchar(50), PreviousTime varchar(50), Improvement varchar(50))";
                        objDb.createTable(nameImprovementTable, dbName);
                        String splitDiffTable = "CREATE TABLE " + fullName + "SplitDifferences "
                                + "(Race varchar(50), Distances varchar(50), Difference varchar(50))";
                        objDb.createTable(splitDiffTable, dbName);
                        //adding name to arraylist
                        names.add(fullName);
                    }
                }
                catch (SQLException se)
                {
                    System.out.println("Error inserting Data");
                }
            }
            catch (NumberFormatException nfe)
            {
                // open warning frame if there is an error in input
                new Warning("Make sure you enter numbers for age, click on help for more info");
            }

        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message
            String message = " Enter in the first and last name of the swimmer"
                    + "as well as their age and group and press 'insert' to insert the data into the database Make sure to only use letter for"
                    + " the first and last names, and only use numbers for the age.";
            //pass help message to the help frame
            Help helpObj = new Help(message, "Insert Swimmer");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            //swimming table database information
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
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            //All times table data table information
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
            //all splits table information
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
