/*
Frame that contains all swimmers times
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

public class AllTimes extends JFrame implements ActionListener
{

    //declaring variables
    public static final Color TIMES_COLOR = new Color(255, 150, 20);
    public static final Color TABLE_BACKGROUND = new Color(247, 189, 73);
    public static final Color HEADER_BACKGROUND = new Color(231, 89, 0);
    public static final Color HEADER_FORE = new Color(0, 53, 79);
    private JLabel nameTitle;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton returnButton;
    private JButton splitsButton;
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
    private JMenuItem splitsItem; //

    private ArrayList<ArrayList<String>> dataList;
    private Object[][] data;
    private JTable dbTable;
    private JScrollPane scrollTable;
    private JTableHeader header;
    private TableColumn column;

    public AllTimes(String dbName, String tableName, String[] tableHeaders)
    {
        //constructing frame
        super("Times");
        this.setBounds(200, 200, 900, 500);
        this.getContentPane().setBackground(TIMES_COLOR);
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
        dbTable.setBackground(TABLE_BACKGROUND);
        dbTable.setFont(Welcome.SMALL_FONT);
        //format header
        header = dbTable.getTableHeader();
        header.setBackground(HEADER_BACKGROUND);
        header.setForeground(HEADER_FORE);
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
        column = dbTable.getColumnModel().getColumn(4);
        column.setPreferredWidth(30);
        column = dbTable.getColumnModel().getColumn(5);
        column.setPreferredWidth(40);
        //constructing scroll Pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);
        //constructing Labels
        this.nameTitle = new JLabel("<html><center>All Swimmers Best Times / Goal Times / Previous Times </center></html>", SwingConstants.CENTER);
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
        this.splitsButton = new JButton("Splits");
        this.splitsButton.addActionListener(this);
        // constructing panels
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(TIMES_COLOR);
        this.buttonPanel.add(insertButton);
        this.buttonPanel.add(deleteButton);
        this.buttonPanel.add(updateButton);
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

        navigationMenu.add(exitItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(swimmerItem);
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
        //All times table information
        String dbName = "SwimTeam";
        String tableName = "AllTimes";
        String[] columnNames =
        {
            "FirstName", "LastName", "Race", "BestTime", "GoalTime", "PreviousTime"
        };
        AllTimes timesObj = new AllTimes(dbName, tableName, columnNames);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Insert"))
        {
            //open up insert frame
            InsertTimes insertObj1 = new InsertTimes(this);
        }
        if (command.equals("Delete"))
        {
            //open up delete frame
            DeleteTimes deleteObj1 = new DeleteTimes(this);
        }
        if (command.equals("Update"))
        {
            //open update frame
            UpdateTimes updateObj1 = new UpdateTimes(this);
        }
        if (command.equals("Splits"))
        {
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "AllSplits";
            String[] columnNames =
            {
                "Name", "Race", "Distance", "Split"
            };
            //open up splits frame using the table information
            AllSplits splitsObj = new AllSplits(dbName, tableName, columnNames);
        }
        if (command.equals("Return"))
        {
            //return to all swimmers page, displaying table of swimmers
            this.dispose();
            String dbName = "SwimTeam";
            String tableName = "Swimmers";
            String[] columnNames =
            {
                "FirstName", "LastName", "SwimmerAge", "SwimGroup"
            };
            Swimmers swimObj = new Swimmers(dbName, tableName, columnNames);
        }

        if (myComponent == helpItem)
        {
            //help message
            String message = "This page contains all swimmers best, goal and previous"
                    + " times. You can insert/delete/update with their corresponding buttons. To see the all swimmers splits,  press the 'splits' button.";
            // open help frame
            Help helpObj = new Help(message, "Times");
        }
        if (myComponent == homeItem)
        {
            // naviage back to home page
            this.dispose();
            Welcome welcomeObj2 = new Welcome();
        }
        if (myComponent == swimmerItem)
        {
            // open up swimmer frame using database information
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
            //exit program completely
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            //open select frame with name buttons
            this.dispose();
            SelectName selectObj2 = new SelectName();
        }
        if (myComponent == splitsItem)
        {
            this.dispose();
            //navigate to see all swimmers splits
            //using the AllSplits datatable
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
