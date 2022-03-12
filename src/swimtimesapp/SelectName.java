/*
Frame to select a name to look at times
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class SelectName extends JFrame implements ActionListener
{

    public static final Color SELECT_COLOR = new Color(255, 133, 46);
    //declaring variables
    private JLabel selectLabel;
    private JLabel descriptionLabel;
    private Box buttonBox;
    private JPanel buttonPanel;
    private JPanel northPanel;
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
    private JScrollPane scrollTable;
    private ArrayList<String> names = new ArrayList<>(10);
    ArrayList<ArrayList<String>> dataList;
    String fullName;

    public SelectName()
    {
        //constructing frame
        super("Select Name");
        this.setBounds(200, 200, 600, 500);
        this.getContentPane().setBackground(SELECT_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        JavaDatabase objDb = new JavaDatabase(dbName);
        dataList = objDb.getData(tableName, columnNames);
        for (int i = 0; i < dataList.size(); i++)
        {
            fullName = dataList.get(i).get(0) + dataList.get(i).get(1);
            names.add(fullName);
        }

        //construct labels 
        this.selectLabel = new JLabel("<html><center>Select a Name</center></html>", SwingConstants.CENTER);
        this.selectLabel.setForeground(Color.BLACK);
        this.selectLabel.setFont(Welcome.BIG_FONT);
        this.descriptionLabel = new JLabel("<html><center> List of Inserted Swimmers: <br> *Must have inserted a swimmer for a name to show up</center></html>", SwingConstants.CENTER);
        this.descriptionLabel.setForeground(Color.BLACK);
        this.descriptionLabel.setFont(Welcome.SMALL_FONT);
        // construct buttons
        //constructing box
        this.buttonBox = Box.createVerticalBox();
        this.buttonBox.add(new JLabel(""));
        // contructing panel
        this.northPanel = new JPanel(new BorderLayout());
        this.northPanel.setBackground(SELECT_COLOR);
        this.northPanel.add(selectLabel, BorderLayout.NORTH);
        this.northPanel.add(descriptionLabel, BorderLayout.SOUTH);
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(SELECT_COLOR);
        this.buttonPanel.add(buttonBox, CENTER_ALIGNMENT);
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(buttonPanel);

        for (int i = 0; i < names.size(); i++)
        {
            JButton temp = new JButton(names.get(i));
            temp.addActionListener(this);
            this.buttonBox.add(temp);

        }
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
        helpMenu.add(helpItem);

        mainBar.add(navigationMenu);
        mainBar.add(helpMenu);

        this.setJMenuBar(mainBar);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(scrollTable, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        SelectName selectObj = new SelectName();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        //loop to look through all the names 
        for (int i = 0; i < names.size(); i++)
        {
            // if one of the buttons with one of the swimmers names pressed 
            if (command.equals(names.get(i)))
            {
                //open specific name frame
                this.dispose();
                // specific swimmer table information
                String dbName = "SwimTeam";
                String tableName = names.get(i);
                String[] columnNames =
                {
                    "Race", "BestTime", "GoalTime", "PreviousTime"
                };
                NameTimes timeObj = new NameTimes(dbName, tableName, columnNames);
            }
        }
        if (myComponent == helpItem)
        {
            //help message to pass on to the help frame
            String message = " This page shows buttons of swimmers. You can select"
                    + " a swimmer by pressing the button with their name on it to view their times. You must insert"
                    + " a swimmer on the Swimmers page for a button to appear on the screen. To go back, use the navigation tab at the top. </html>";
            Help helpObj = new Help(message, "Select Name");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            //swimmers data table information
            this.dispose();
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
            // all splits table data table information
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
