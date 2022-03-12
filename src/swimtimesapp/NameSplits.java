/*
Frame for a specific swimmers splits
 */
package swimtimesapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
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

public class NameSplits extends JFrame implements ActionListener
{

    //declaring variables
    public static final Color SPLITS_COLOR = new Color(239, 204, 0);
    private JLabel nameTitle;
    private JLabel helpLabel;
    private JButton returnButton;
    private JButton splitDiffButton;
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
    String name;

    private ArrayList<ArrayList<String>> dataList;
    private Object[][] data;
    private JTable dbTable;
    private JScrollPane scrollTable;
    private JTableHeader header;
    private TableColumn column;

    public NameSplits(String dbName, String tableName,
            String[] tableHeaders, String name)
    {
        //constructing frame
        super(tableName);
        this.setBounds(200, 200, 800, 500);
        this.getContentPane().setBackground(SPLITS_COLOR);
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
        //constructing scroll Pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //constructing Labels
        this.nameTitle = new JLabel("<html><center>" + name + " Splits </center></html>", SwingConstants.CENTER);
        this.nameTitle.setForeground(Color.BLACK);
        this.nameTitle.setFont(Welcome.BIG_FONT);
        this.helpLabel = new JLabel("<html><center> If you want to insert/delete/update data for this swimmer, you need to do it at the all split page. </center></html>");
        this.helpLabel.setForeground(Color.BLACK);
        this.helpLabel.setFont(Welcome.SMALL_FONT);
        //constructing buttons

        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.splitDiffButton = new JButton("Calculate Split Difference");
        this.splitDiffButton.addActionListener(this);

        // constructing panel
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(SPLITS_COLOR);
        this.buttonPanel.add(splitDiffButton);
        this.buttonPanel.add(returnButton);
        this.northPanel = new JPanel(new BorderLayout());
        this.northPanel.setBackground(SPLITS_COLOR);
        this.northPanel.add(nameTitle, BorderLayout.NORTH);
        this.northPanel.add(helpLabel, BorderLayout.SOUTH);
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
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        //main class only works if there was a swimmer inserted
        //getting the first swimmer in database
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        //getting data from swimmers to use in main class 
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        //specific swimmers splits table information
        String name = data.get(0).get(0) + data.get(0).get(1);
        String swimmerTable = name + "Splits";
        String[] swimmerColumns =
        {
            "Race", "Distance", "Split"
        };
        NameSplits splitsObj = new NameSplits(dbName, swimmerTable, swimmerColumns, name);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Calculate Split Difference"))
        {
            //opens up split difference page
            SplitDifference splitObj1 = new SplitDifference(name);
        }
        if (command.equals("Return"))
        {
            this.dispose();
            //returns back to the specific swimmers times
            String dbName = "SwimTeam";
            String tableName = name;
            String[] columnNames =
            {
                "Race", "BestTime", "GoalTime", "PreviousTime"
            };
            NameTimes timesObj = new NameTimes(dbName, tableName, columnNames);
        }
        if (myComponent == helpItem)
        {
            //help message to pass on to help frame
            String message = "This page displays the 50 yard splits for each"
                    + " race. To calculate the split"
                    + " difference press the 'Split Difference' button. ";
            Help helpObj = new Help(message, "RaceSplits");
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
            //open swimmers 
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
            // All splits data table information
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
