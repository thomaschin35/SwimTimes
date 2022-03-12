/*
This frame shows all swimmers splits
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

public class AllSplits extends JFrame implements ActionListener
{

    //declaring variables
    public static final Color SPLITS_COLOR = new Color(239, 204, 0);
    private JLabel nameTitle;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton returnButton;
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

    private ArrayList<ArrayList<String>> dataList;
    private Object[][] data;
    private JTable dbTable;
    private JScrollPane scrollTable;
    private JTableHeader header;
    private TableColumn column;

    public AllSplits(String dbName, String tableName, String[] tableHeaders)
    {
        //constructing frame
        super("All Splits");
        this.setBounds(200, 200, 800, 500);
        this.getContentPane().setBackground(SPLITS_COLOR);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
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
        this.nameTitle = new JLabel("All Race Splits", SwingConstants.CENTER);
        this.nameTitle.setForeground(Color.BLACK);
        this.nameTitle.setFont(Welcome.BIG_FONT);
        //constructing buttons
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);

        // constructing panel
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(SPLITS_COLOR);
        this.buttonPanel.add(insertButton);
        this.buttonPanel.add(deleteButton);
        this.buttonPanel.add(updateButton);
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
        this.timesItem = new JMenuItem("All Swimmers Times");
        this.timesItem.addActionListener(this);

        navigationMenu.add(exitItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(swimmerItem);
        navigationMenu.add(timesItem);
        navigationMenu.add(selectItem);
        helpMenu.add(helpItem);

        mainBar.add(navigationMenu);
        mainBar.add(helpMenu);
        //adding to the main frame
        this.setJMenuBar(mainBar);
        this.add(nameTitle, BorderLayout.NORTH);
        this.add(scrollTable, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Insert"))
        {
            //opens up insert frame
            InsertSplit insertObj2 = new InsertSplit(this);
        }
        if (command.equals("Delete"))
        {
            //opens up delete frame
            DeleteSplit deleteObj2 = new DeleteSplit(this);
        }
        if (command.equals("Update"))
        {
            //opens up update frame
            UpdateSplit updateObj2 = new UpdateSplit(this);
        }
        if (command.equals("Return"))
        {
            // returns back to AllTimes frame with the corresponding table information
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }
        if (myComponent == helpItem)
        {
            //help message 
            String message = "This page displays the splits for each"
                    + " race. You can insert/delete/update a split for a race by pressing the corresponding buttons.";
            Help helpObj = new Help(message, "RaceSplits");
        }
        if (myComponent == homeItem)
        {
            //navigates back to the welcome page
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            //navigate to the swimmers page using the swimmer table info
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
            //exit the program 
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            // open select Name frame
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            //open up All swimmer times frame, 
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "AllTimes";
            String[] columnNames =
            {
                "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
            };

            AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
        }

    }
}
