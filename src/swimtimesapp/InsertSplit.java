/*
Insert Frame for Split
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

public class InsertSplit extends JFrame implements ActionListener
{

    //declaring variables
    private JLabel insertTitle;
    private JLabel raceLabel;
    private JLabel distanceLabel;
    private JLabel splitLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel whoLabel;
    private JTextField raceField;
    private JTextField distanceField;
    private JTextField splitField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton insertButton;
    private JButton doneButton;
    private JPanel centerPanel;
    private JPanel racePanel;
    private JPanel distancePanel;
    private JPanel splitPanel;
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

    public InsertSplit(AllSplits pFrame)
    {
        //creating frame
        super("Insert split");
        this.setBounds(200, 200, 520, 350);
        this.getContentPane().setBackground(InsertSwimmer.INSERT_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.gFrame = pFrame;
        //creating labels
        this.insertTitle = new JLabel("Insert Split", SwingConstants.CENTER);
        this.insertTitle.setFont(Welcome.BIG_FONT);
        this.raceLabel = new JLabel("Race (Ex.100 Free)");
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.distanceLabel = new JLabel("Distance (Ex. 50)");
        this.distanceLabel.setFont(Welcome.SMALL_FONT);
        this.splitLabel = new JLabel("50m Split (Ex. 32.44)");
        this.splitLabel.setFont(Welcome.SMALL_FONT);
        this.firstNameLabel = new JLabel("First Name ");
        this.firstNameLabel.setFont(Welcome.SMALL_FONT);
        this.lastNameLabel = new JLabel("Last Name ");
        this.lastNameLabel.setFont(Welcome.SMALL_FONT);
        this.whoLabel = new JLabel("Who: ");
        this.whoLabel.setFont(UpdateSwimmer.SUB_FONT);
        //constructing textfields
        this.raceField = new JTextField(20);
        this.distanceField = new JTextField(20);
        this.splitField = new JTextField(15);
        this.firstNameField = new JTextField(7);
        this.lastNameField = new JTextField(7);

        //constructing buttons 
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        //constructing panels and boxes
        this.racePanel = new JPanel();
        this.racePanel.setBackground(InsertSwimmer.INSERT_COLOR);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.distancePanel = new JPanel();
        this.distancePanel.add(distanceLabel);
        this.distancePanel.add(distanceField);
        this.distancePanel.setBackground(InsertSwimmer.LAYER_1_COLOR);
        this.splitPanel = new JPanel();
        this.splitPanel.add(splitLabel);
        this.splitPanel.add(splitField);
        this.splitPanel.setBackground(InsertSwimmer.LAYER_2_COLOR);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(racePanel);
        this.westBox.add(distancePanel);
        this.westBox.add(splitPanel);
        this.buttonBox = Box.createVerticalBox();
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(new JLabel(" "));
        this.buttonBox.add(insertButton);
        this.buttonBox.add(doneButton);
        this.buttonBox.add(new JLabel(" "));
        this.northPanel = new JPanel();
        this.northPanel.setBackground(InsertSwimmer.INSERT_COLOR);
        this.northPanel.add(whoLabel);
        this.northPanel.add(firstNameLabel);
        this.northPanel.add(firstNameField);
        this.northPanel.add(lastNameLabel);
        this.northPanel.add(lastNameField);
        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(InsertSwimmer.INSERT_COLOR);
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
        this.add(insertTitle, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //data table information
        String dbName = "SwimTeam";
        String tableName = "AllSplits";
        String[] columnNames =
        {
            "Name", "Race", "Distance", "Split"
        };
        AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        new InsertSplit(splitsObj);
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
                // first insert into allsplits table
                String dbName = "SwimTeam";
                String tableName = "AllSplits";
                String[] columnNames =
                {
                    "Name", "Race", "Distance", "Split"
                };
                //SAL statment to insert into all splits table
                String dbQuery = "INSERT INTO AllSplits VALUES(?,?,?,?)";
                // conneect to Db
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                //variables used to insert 
                String name = firstNameField.getText() + " " + lastNameField.getText();
                String race = raceField.getText();
                int distance = Integer.parseInt(distanceField.getText());
                String split = splitField.getText();

                distanceField.setText("");
                splitField.setText("");
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

                    ps.setString(1, name);
                    ps.setString(2, race);
                    ps.setInt(3, distance);
                    ps.setString(4, split);
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
                //inserting into specific swimmer table
                String swimmerTable = firstNameField.getText() + lastNameField.getText() + "Splits";
                String[] swimmerColumnNames =
                {
                    "Race", "Distance", "Split",
                };
                //SQL statement to insert into specific swimmer 
                String dbQuery2 = "INSERT INTO " + swimmerTable + " VALUES(?,?,?)";
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery2);

                    ps.setString(1, race);
                    ps.setInt(2, distance);
                    ps.setString(3, split);
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
                //open warning frame if there is input problem
                new Warning("Check your numbers, click on the help frame on the main bar for more information");
            }

        }
        if (command.equals("Done"))
        {
            //close frame 
            this.dispose();
        }
        if (myComponent == helpItem)
        {
            //help message
            String message = "Enter in the first and last name of the swimmer. Enter in the race (Ex. 100 Free), distance (Ex. 50), and 50m Split(Ex. 27.87) "
                    + "and then press the 'insert' button to insert the split into the database. For the split, make sure to "
                    + " follow this format - minute:second.milisecond";
            //pass help message to Help class
            Help helpObj = new Help(message, "Insert Split");
        }
        if (myComponent == homeItem)
        {
            //opening home frame
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            //opening swimmer frame using swimmer table information
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
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            //open select name frame
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            //open all swimmer times frame
            //using all times table information 
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
            //open all splits frame using all splits table information
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
