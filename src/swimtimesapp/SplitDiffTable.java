/*
This frame displays a table of Split diff for each race
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

public class SplitDiffTable extends JFrame implements ActionListener
{

    //declaring variables
    private JLabel nameTitle;
    private JButton returnButton;
    private JButton deleteButton;
    private JPanel buttonPanel;
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

    public SplitDiffTable(String dbName, String tableName,
            String[] tableHeaders, String name)
    {
        //constructing frame
        super(tableName);
        this.setBounds(300, 300, 500, 500);
        this.getContentPane().setBackground(SplitDifference.DIFF_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.name = name;
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
        dbTable.setBackground(SplitDifference.LAYER_2);
        dbTable.setFont(Welcome.SMALL_FONT);
        //format header
        header = dbTable.getTableHeader();
        header.setBackground(SplitDifference.LAYER_1);
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
        //constructing scroll Pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //constructing Labels
        this.nameTitle = new JLabel("<html><center>" + tableName + "</center></html>", SwingConstants.CENTER);
        this.nameTitle.setForeground(Color.BLACK);
        this.nameTitle.setFont(Welcome.BIG_FONT);
        //constructing buttons
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        // constructing panels
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(ImprovementDisplay.RACE_COLOR);
        this.buttonPanel.add(deleteButton);
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
        this.add(nameTitle, BorderLayout.NORTH);
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
        //getting the first swimmer in the database
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        String name = data.get(0).get(0) + data.get(0).get(1);
        String swimmerTable = name + "SplitDifference";
        String[] swimmerColumns =
        {
            "Race", "Distances", "Difference"
        };
        SplitDiffTable diffObj = new SplitDiffTable(dbName, swimmerTable, swimmerColumns, name);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Delete"))
        {
            //opens up delete difference display
            new DeleteDifference(this, name);
        }
        if (command.equals("Return"))
        {
            this.dispose();
            new SplitDifference(name);
        }
        if (myComponent == helpItem)
        {
            // help message
            String message = "This frame displays the results calculated from the Split Difference display. It displays the race"
                    + ", distances, and split difference for those distances. If you want to insert into this table, you must calculate the split difference"
                    + "in the previous frame. You can delete a certain row of data by pressing the delete button to open up a delete frame. ";
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
