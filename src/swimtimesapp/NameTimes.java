/*
Displays specific swimmers times
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


public class NameTimes extends JFrame implements ActionListener
{

    //declaring variables
    public static final Color TIMES_COLOR = new Color(255, 150, 20);
    private JLabel nameTitle;
    private JLabel helpLabel;
    private JButton returnButton;
    private JButton PercImproveButton;
    private JButton splitsButton;
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

    private ArrayList<ArrayList<String>> dataList;
    private Object[][] data;
    private JTable dbTable;
    private JScrollPane scrollTable;
    private JTableHeader header;
    private TableColumn column;

    private String name;

    public NameTimes(String dbName, String tableName,
            String[] tableHeaders)
    {
        //constructing frame
        super(tableName);
        this.setBounds(200, 200, 800, 500);
        this.getContentPane().setBackground(TIMES_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        name = tableName;
        //construct Data
        JavaDatabase dbObj = new JavaDatabase(dbName);
        dataList = dbObj.getData(tableName, tableHeaders);
        if (dataList.size() == 0)
        {
            data = new Object[0][0];
        }
        else
        {
            data = dbObj.to2dArray(dataList);
        }
        dbObj.closeDbConn();
        //construct table
        dbTable = new JTable(data, tableHeaders);
        //format table
        dbTable.setGridColor(Color.BLACK);
        dbTable.setBackground(AllTimes.TABLE_BACKGROUND);
        dbTable.setFont(Welcome.SMALL_FONT);
        //format header
        header = dbTable.getTableHeader();
        header.setBackground(AllTimes.HEADER_BACKGROUND);
        header.setForeground(AllTimes.HEADER_FORE);
        header.setFont(UpdateSwimmer.SUB_FONT);
        //format rows
        dbTable.setRowHeight(25);
        //format columns
        column = dbTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(40);
        column = dbTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(30);
        column = dbTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(20);
        column = dbTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(20);
        //constructing scroll Pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //constructing Labels
        this.nameTitle = new JLabel("<html><center>" + tableName + "- Best Times / Goal Times / Previous Times </center></html>", SwingConstants.CENTER);
        this.nameTitle.setForeground(Color.BLACK);
        this.nameTitle.setFont(Welcome.BIG_FONT);
        this.helpLabel = new JLabel("<html><center> If you want to insert/delete/update data for this swimmer, you need to do it at the all times page. </center></html>");
        this.helpLabel.setForeground(Color.BLACK);
        this.helpLabel.setFont(Welcome.SMALL_FONT);
        //constructing buttons
        this.PercImproveButton = new JButton("Percentage Improvement");
        this.PercImproveButton.addActionListener(this);
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.splitsButton = new JButton("Splits");
        this.splitsButton.addActionListener(this);
        // constructing panels
        this.northPanel = new JPanel(new BorderLayout());
        this.northPanel.setBackground(TIMES_COLOR);
        this.northPanel.add(nameTitle, BorderLayout.NORTH);
        this.northPanel.add(helpLabel, BorderLayout.SOUTH);
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(TIMES_COLOR);
        this.buttonPanel.add(PercImproveButton);
        this.buttonPanel.add(splitsButton);
        this.buttonPanel.add(returnButton);

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
        this.add(scrollTable, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        //swimmers table information
        //main class only works if there was a swimmer inserted
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        //getting the first name in the data list
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        String name = data.get(0).get(0) + data.get(0).get(1);
        //main class only works if there was a swimmer inserted
        String swimmerTable = name;
        String[] swimmerColumns =
        {
            "Race", "BestTime", "GoalTime", "PreviousTime"
        };
        NameTimes timesObj = new NameTimes(dbName, swimmerTable, swimmerColumns);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Percentage Improvement"))
        {
            //opening percentage Improvement display
            ImprovementDisplay improveObj2 = new ImprovementDisplay(name);
        }
        if (command.equals("Splits"))
        {
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = name + "Splits";
            String[] columnNames =
            {
                "Race", "Distance", "Split"
            };
            //opens up splits frame using the name(inserted before)
            NameSplits splitsObj = new NameSplits(dbName, tableName, columnNames, name);

        }
        if (command.equals("Return"))
        {
            this.dispose();
            new SelectName();
        }
        if (myComponent == helpItem)
        {
            // help message
            String message = "This page contains a swimmers best, goal and previous"
                    + " times. To edit the data here, you must insert/delete/update in the all times page. To calculate the percentage of improvement between"
                    + " two races,press on the 'Percentage Improvemnt' button. To see the swimmers splits,  press the 'splits' button. ";
            Help helpObj = new Help(message, "Times");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            // navigates back to home
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
            //navigates back to swimmers 
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }
        if (myComponent == exitItem)
        {
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            //open up select frame 
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            this.dispose();
            //All times database table information 
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
            //open all splits
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }
    }
}
