/*
Frame to calculate the split difference
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SplitDifference extends JFrame implements ActionListener
{

    // declaring final variables
    public static final Color DIFF_COLOR = new Color(63, 171, 148);
    public static final Color LAYER_1 = new Color(128, 197, 192);
    public static final Color LAYER_2 = new Color(108, 189, 170);
    public static final Color ANSWER_BACK = new Color(58, 154, 133);
    public final URL SPLIT_PATH = getClass().getResource("SplitPhoto.png");
    private final ImageIcon SPLIT_IMAGE = new ImageIcon(
            new ImageIcon(SPLIT_PATH).getImage().getScaledInstance(350, 340, Image.SCALE_DEFAULT));

    //declaring variables
    private JLabel titleLabel;
    private JLabel raceLabel;
    private JTextField raceField;
    private JLabel distance2Label;
    private JTextField distance2Field;
    private JLabel distance1Label;
    private JTextField distance1Field;
    private JLabel answerLabel;
    private JPanel answerPanel;
    private JLabel imageLabel;
    private JLabel descriptionLabel;

    private JButton calculateButton;
    private JButton doneButton;
    private JButton tableButton;
    private JPanel racePanel;
    private JPanel centerPanel;
    private JPanel distance1Panel;
    private JPanel distance2Panel;
    private JPanel buttonPanel;
    private JPanel responsePanel;
    private JPanel northPanel;
    private Box westBox;
    private Box answerBox;

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
    String name;

    public SplitDifference(String name)
    {
        //creating frame
        super("Split Difference");
        this.setBounds(200, 200, 730, 400);
        this.getContentPane().setBackground(DIFF_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.name = name;
        //constructing labels
        this.titleLabel = new JLabel("Calculating the Split Difference", SwingConstants.CENTER);
        this.titleLabel.setForeground(Color.BLACK);
        this.titleLabel.setFont(Welcome.BIG_FONT);
        this.descriptionLabel = new JLabel("<html><center>To find the split difference, you must put in some data "
                + "for the swimmer in the all splits page </center></html>", SwingConstants.CENTER);
        this.descriptionLabel.setForeground(Color.BLACK);
        this.descriptionLabel.setFont(Welcome.SMALL_FONT);
        this.raceLabel = new JLabel("Race (Ex. 100 Free): ");
        this.raceLabel.setForeground(Color.BLACK);
        this.raceLabel.setFont(Welcome.SMALL_FONT);
        this.distance1Label = new JLabel("Distance 1 (Ex. 50): ");
        this.distance1Label.setForeground(Color.BLACK);
        this.distance1Label.setFont(Welcome.SMALL_FONT);
        this.distance2Label = new JLabel("Distance 2 (Ex. 100): ");
        this.distance2Label.setForeground(Color.BLACK);
        this.distance2Label.setFont(Welcome.SMALL_FONT);

        this.imageLabel = new JLabel(SPLIT_IMAGE);
        //Constructing textFields
        this.distance2Field = new JTextField(10);
        this.distance1Field = new JTextField(12);
        this.raceField = new JTextField(13);
        //creating buttons
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);
        this.doneButton = new JButton("Done");
        this.doneButton.addActionListener(this);
        this.tableButton = new JButton("Split Difference Table");
        this.tableButton.addActionListener(this);
        //creating panels and boxes
        this.racePanel = new JPanel();
        this.racePanel.setBackground(LAYER_1);
        this.racePanel.add(raceLabel);
        this.racePanel.add(raceField);
        this.distance1Panel = new JPanel();
        this.distance1Panel.setBackground(LAYER_2);
        this.distance1Panel.add(distance1Label);
        this.distance1Panel.add(distance1Field);
        this.distance2Panel = new JPanel();
        this.distance2Panel.setBackground(DIFF_COLOR);
        this.distance2Panel.add(distance2Label);
        this.distance2Panel.add(distance2Field);

        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(DIFF_COLOR);
        this.buttonPanel.add(tableButton);
        this.buttonPanel.add(calculateButton);
        this.buttonPanel.add(doneButton);
        this.northPanel = new JPanel(new BorderLayout());
        this.northPanel.setBackground(DIFF_COLOR);
        this.northPanel.add(titleLabel, BorderLayout.NORTH);
        this.northPanel.add(descriptionLabel, BorderLayout.SOUTH);
        this.responsePanel = new JPanel();
        this.responsePanel.setBackground(ANSWER_BACK);
        this.responsePanel.add(new JLabel(""));
        this.answerBox = Box.createVerticalBox();
        this.answerBox.add(new JLabel(" "));
        this.answerBox.add(responsePanel);
        this.answerPanel = new JPanel();
        this.answerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 96, 126), 3));
        this.answerPanel.setBackground(ANSWER_BACK);
        this.answerPanel.add(answerBox);
        this.westBox = Box.createVerticalBox();
        this.westBox.add(racePanel);
        this.westBox.add(distance1Panel);
        this.westBox.add(distance2Panel);
        this.westBox.add(answerPanel);

        this.centerPanel = new JPanel(new BorderLayout());
        this.centerPanel.setBackground(DIFF_COLOR);
        this.centerPanel.add(westBox, BorderLayout.WEST);
        this.centerPanel.add(imageLabel, BorderLayout.EAST);
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
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //swimmemrs table information
        //main class only works if there was a swimmer inserted
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        //getting data from swimmers to use in main class 
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        String name = data.get(0).get(0) + data.get(0).get(1);
        //specific swimmers split table
        String swimmerTable = name + "Splits";
        String[] swimmerColumns =
        {
            "Race", "Distance", "Split"
        };
        new SplitDifference(name);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Calculate"))
        {
            try
            {
                // variables used to pass to calculating difference class
                this.responsePanel.removeAll();
                int distance1 = Integer.parseInt(distance1Field.getText());
                int distance2 = Integer.parseInt(distance2Field.getText());
                String race = raceField.getText();
                //creating a calculate split diff class
                CalculateSplitDiff calSplitObj = new CalculateSplitDiff(distance1, distance2, race, name);
                //get split difference and assign to this variable
                double splitDifference = calSplitObj.getDifference();
                this.answerLabel = new JLabel("<html><center>Race: " + race + "<br> Distances:  "
                        + distance1 + " , " + distance2 + " <br> Split Difference: " + splitDifference + " </center></html>", SwingConstants.CENTER);
                this.answerLabel.setForeground(Color.BLACK);
                this.answerLabel.setFont(Welcome.SMALL_FONT);
                //resetting text
                this.distance1Field.setText("");
                this.distance2Field.setText("");
                this.responsePanel.add(answerLabel);
                //updating the GUI
                this.responsePanel.updateUI();
                //inserting into split differneces table
                String dbName = "SwimTeam";
                String swimmerTable = name + "SplitDifferences";
                String[] swimmerColumns =
                {
                    "Race", "Distances", "Difference"
                };
                //SQL statement to insert into the swimmers split difference table
                String dbQuery = "INSERT INTO " + swimmerTable + " VALUES(?,?,?)";
                // connect to Db
                JavaDatabase objDb = new JavaDatabase(dbName);
                Connection myDbConn = objDb.getDbConn();
                String distances = distance1 + " - " + distance2;
                try
                {
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
                    ps.setString(1, race);
                    ps.setString(2, distances);
                    ps.setDouble(3, splitDifference);
                    //execute Query
                    ps.executeUpdate();

                }
                catch (SQLException se)
                {
                    System.out.println("Error Inserting DATA");
                }
            }
            catch (NumberFormatException nfe)
            {
                new Warning("Enter in the numbers and enter in the times in the right format");
            }

        }
        if (command.equals("Done"))
        {
            this.dispose();
        }
        if (command.equals("Split Difference Table"))
        {
            this.dispose();
            //specific swimmer split difference table information
            String dbName = "SwimTeam";
            String swimmerTable = name + "SplitDifferences";
            String[] swimmerColumns =
            {
                "Race", "Distances", "Difference"
            };
            SplitDiffTable diffTableObj = new SplitDiffTable(dbName, swimmerTable, swimmerColumns, name);
        }
        if (myComponent == helpItem)
        {
            //help message to pass on to the help frame
            String message = " This page will calculate the difference between"
                    + "two 50m splits. Enter in the race and two distances like 50m and 100m and press calculate to get the"
                    + " split difference. Make sure to enter in only numbers for the distances or else you will produce an error.";
            Help helpObj = new Help(message, "Split Difference");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj3 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            this.dispose();
            // swimmers data table information
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            // open swimmers frame
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            SelectName selectObj3 = new SelectName();
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
            //open all times frame
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == splitsItem)
        {
            this.dispose();
            //all splits data table information
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
