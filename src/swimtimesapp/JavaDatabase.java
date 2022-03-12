/*
database class
 */
package swimtimesapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JavaDatabase
{

    private String dbName;
    private Connection dbConn;
    private ArrayList<ArrayList<String>> data;

    public JavaDatabase()
    {
        dbName = "";
        dbConn = null;
        data = null;

    }

    public JavaDatabase(String dbName)
    {
        setDbName(dbName);
        setDbConn();
        data = null;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }

    public Connection getDbConn()
    {
        return dbConn;
    }

    public void setDbConn()
    {
        String connectionURL = "jdbc:derby:" + this.dbName;
        this.dbConn = null;
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbConn = DriverManager.getConnection(connectionURL);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver not Found, Check Library");
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error");
        }
    }

    public ArrayList<ArrayList<String>> getData(String tableName, String[] tableHeaders)
    {
        int columnCount = tableHeaders.length;
        Statement s = null;
        ResultSet rs = null;
        String dbQuery = "SELECT * FROM " + tableName;
        this.data = new ArrayList<>();
        //read the data
        try
        {
            //send the query and recieve data
            s = this.dbConn.createStatement();
            rs = s.executeQuery(dbQuery);

            //read the data using rs and store arraylist in data
            while (rs.next())
            {
                // row object to  hold one row data
                ArrayList<String> row = new ArrayList<>();
                // go through the row and read each cell
                for (int i = 0; i < columnCount; i++)
                {
                    //read cell i
                    String cell = rs.getString(tableHeaders[i]);
                    // add the cell to the row
                    row.add(cell);
                    // row.add(rs.getString(tableHeaders[i]));
                }
                // add row to the data
                this.data.add(row);
            }
        }
        catch (SQLException se)
        {
            System.out.println("SQL Error: Not able to get Data");
        }
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data)
    {
        this.data = data;
    }

    public void createDbConn(String newDbName)
    {
        setDbName(newDbName);
        String connectionURL = "jdbc:derby:" + this.dbName
                + ";create = true";
        this.dbConn = null;
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbConn = DriverManager.getConnection(connectionURL);
            System.out.println("New Database " + this.dbName + " created!");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Driver not Found, Check Library");
        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection Error, Db was not created");
        }

    }

    public void closeDbConn()
    {
        try
        {
            this.dbConn.close();
        }
        catch (Exception er)
        {
            System.out.println("DB Closing Error");
        }
    }

    public void createTable(String newTable, String dbName)
    {
        System.out.println(newTable);
        setDbName(dbName);
        setDbConn();
        Statement s;
        try
        {
            s = this.dbConn.createStatement();
            s.execute(newTable);
            System.out.println("New Table Created");

        }
        catch (SQLException se)
        {
            System.out.println("SQL Connection error, or SYNTAX Error");
        }

    }

    public Object[][] to2dArray(ArrayList<ArrayList<String>> data)
    {
        int columnCount = data.get(0).size();
        Object[][] dataList = new Object[data.size()][columnCount];
        for (int i = 0; i < data.size(); i++)
        {
            ArrayList<String> row = data.get(i);
            for (int j = 0; j < columnCount; j++)
            {
                dataList[i][j] = row.get(j);
            }
        }
        return dataList;
    }

    public static void main(String[] args)
    {
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        String dbQuery = "INSERT INTO Swimmers VALUES(?,?,?,?)";

        // connect to Db
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();

        String firstName = "John";
        String lastName = "Keene";
        int age = 12;
        String group = "National Prep";

        try
        {
            //prepare Statment
            PreparedStatement ps = myDbConn.prepareStatement(dbQuery);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.setString(4, group);
            //excecute Query
            ps.executeUpdate();
            System.out.println("Data Inserted Successfully");
        }
        catch (SQLException se)
        {
            System.out.println("Error inserting data");
        }
        //read the data from the database
        ArrayList<ArrayList<String>> myData
                = objDb.getData(tableName, columnNames);
        System.out.println(myData);

    }

}
