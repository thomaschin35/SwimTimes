/*
Frame that shows a table of swimmers
allows insert/delete/update

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

public class Swimmers extends JFrame implements ActionListener
{

    //declaring variables
    private JLabel swimmerNameLabel;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton returnButton;
    private JButton timesButton;
    private JButton selectButton;
    private JPanel buttonPanel;
    private JPanel navPanel;
    private JPanel southPanel;

    private JMenuBar mainBar;
    private JMenu helpMenu;
    private JMenuItem helpItem;
    private JMenu navigationMenu;
    private JMenuItem homeItem;
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

    public Swimmers(String dbName, String tableName,
            String[] tableHeaders)
    {
        //constructing frame
        super("Swimmers");
        this.setBounds(200, 200, 700, 500);
        this.getContentPane().setBackground(Welcome.MAIN_COLOR);
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
        dbTable.setBackground(SelectName.SELECT_COLOR);
        dbTable.setFont(Welcome.SMALL_FONT);
        //format header
        header = dbTable.getTableHeader();
        header.setBackground(AllTimes.HEADER_BACKGROUND);
        header.setForeground(AllTimes.HEADER_FORE);
        header.setFont(UpdateSwimmer.SUB_FONT);
        //formate rows
        dbTable.setRowHeight(25);
        //format columns
        column = dbTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(40);
        column = dbTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(20);
        column = dbTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(10);
        column = dbTable.getColumnModel().getColumn(3);
        column.setPreferredWidth(40);
        //constructing scroll pane
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        dbTable.setFillsViewportHeight(true);

        //constructing Labels
        this.swimmerNameLabel = new JLabel("Names of Swimmers", SwingConstants.CENTER);
        this.swimmerNameLabel.setForeground(Color.BLACK);
        this.swimmerNameLabel.setFont(Welcome.BIG_FONT);
        //constructin buttons
        this.insertButton = new JButton("Insert");
        this.insertButton.addActionListener(this);
        this.deleteButton = new JButton("Delete");
        this.deleteButton.addActionListener(this);
        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);
        this.timesButton = new JButton("All Swimmer Times");
        this.timesButton.addActionListener(this);
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);
        this.selectButton = new JButton("Specific Swimmer Times");
        this.selectButton.addActionListener(this);

        // constructing panels
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(Welcome.MAIN_COLOR);
        this.buttonPanel.add(insertButton);
        this.buttonPanel.add(deleteButton);
        this.buttonPanel.add(updateButton);
        this.navPanel = new JPanel();
        this.navPanel.setBackground(Welcome.MAIN_COLOR);
        this.navPanel.add(returnButton);
        this.navPanel.add(timesButton);
        this.navPanel.add(selectButton);
        this.southPanel = new JPanel(new BorderLayout());
        this.southPanel.add(buttonPanel, BorderLayout.NORTH);
        this.southPanel.add(navPanel, BorderLayout.SOUTH);
        //menu
        this.mainBar = new JMenuBar();
        this.mainBar.setBackground(SelectName.SELECT_COLOR);
        this.helpMenu = new JMenu("Help");
        this.helpItem = new JMenuItem("Open Help Frame");
        this.helpItem.addActionListener(this);
        this.navigationMenu = new JMenu("Navigation");
        this.homeItem = new JMenuItem("Home");
        this.homeItem.addActionListener(this);
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
        navigationMenu.add(timesItem);
        navigationMenu.add(splitsItem);
        navigationMenu.add(selectItem);

        helpMenu.add(helpItem);

        mainBar.add(navigationMenu);
        mainBar.add(helpMenu);

        this.setJMenuBar(mainBar);
        this.add(swimmerNameLabel, BorderLayout.NORTH);
        this.add(scrollTable, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
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
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Insert"))
        {
            //open insert frame
            InsertSwimmer insertObj = new InsertSwimmer(this);
        }
        if (command.equals("Delete"))
        {
            //open delete frame to delete
            DeleteSwimmer deleteObj = new DeleteSwimmer(this);
        }
        if (command.equals("Update"))
        {
            //open update frame to update
            UpdateSwimmer updateObj = new UpdateSwimmer(this);
        }
        if (command.equals("All Swimmer Times"))
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
        if (command.equals("Return"))
        {
            this.dispose();
            new Welcome();
        }
        if (command.equals("Specific Swimmer Times"))
        {
            this.dispose();
            //open select name frame
            SelectName selectObj = new SelectName();
        }
        if (myComponent == helpItem)
        {
            //help message to pass on to help frame
            String message = " This page shows a table of swimmers which can be updated."
                    + " and changed by pressing the 'insert' button to insert, 'delete' button to delete, and 'update' button"
                    + " to update each swimmers data, like age, name, and group. To view and update a swimmers times, press the 'Specific Swimmer Times' button"
                    + " which will open a page to select a swimmer ";
            Help helpObj = new Help(message, "Swimmers");
        }
        if (myComponent == homeItem)
        {
            this.dispose();
            Welcome welObj = new Welcome();
        }
        if (myComponent == exitItem)
        {
            System.exit(0);
        }
        if (myComponent == selectItem)
        {
            this.dispose();
            SelectName selectObj1 = new SelectName();
        }
        if (myComponent == timesItem)
        {
            this.dispose();
            // all times data table information
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
            //All splits datatable information
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
