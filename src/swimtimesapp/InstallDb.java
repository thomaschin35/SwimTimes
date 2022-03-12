/*
Class to install database and tables
 */
package swimtimesapp;

public class InstallDb
{

    public static void main(String[] args)
    {
        String dbName = "SwimTeam";
        //creating an object of Db class
        JavaDatabase objDb = new JavaDatabase();
        //creating a new database
        objDb.createDbConn(dbName);

        //creating new tables
        String swimmerTable = "CREATE TABLE Swimmers (FirstName varchar(50),LastName varchar(50),SwimmerAge int,SwimGroup varchar(50))";
        objDb.createTable(swimmerTable, dbName);
        String timesTable = "CREATE TABLE AllTimes (FirstName varchar(50),LastName varchar(50), Race varchar(50), BestTime varchar(50), GoalTime varchar(50), PreviousTime varchar(50))";
        objDb.createTable(timesTable, dbName);
        String splitsTable = "CREATE TABLE AllSplits (Name varchar(50), Race varchar(50), Distance int, Split varchar(50))";
        objDb.createTable(splitsTable, dbName);

    }

}
