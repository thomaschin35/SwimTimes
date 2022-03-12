package swimtimesapp;

import java.util.ArrayList;
//calculation class for split difference

public class CalculateSplitDiff
{

    //variables that are encapsulated
    private double distance1;
    private double distance2;
    private String name;
    private String race;
    private double difference;

    //main constructor
    public CalculateSplitDiff(int distance1, int distance2, String race, String name)
    {
        setDistance1(distance1, race, name);
        setDistance2(distance2, race, name);
        setRace(race);
        setDifference();
    }

    public double getDistance1()
    {
        return distance1;
    }

    public void setDistance1(int distance1, String race, String name)
    {
        // first getting the split for the corresponding distance then assigning to distance1
        this.distance1 = getDistanceTime(distance1, race, name);
    }

    public double getDistance2()
    {
        return distance2;
    }

    public void setDistance2(int distance2, String race, String name)
    {
        // first getting the split for the corresponding distance then assigning to distance2
        this.distance2 = getDistanceTime(distance2, race, name);
    }

    public String getRace()
    {
        return race;
    }

    public void setRace(String race)
    {
        this.race = race;
    }

    public double getDifference()
    {
        return difference;
    }

    public void setDifference()
    {
        this.difference = distance2 - distance1;
        //rounding to 3 decimal places
        difference = difference * 1000;
        difference = Math.round(difference);
        difference = difference / 1000;

    }

    @Override
    public String toString()
    {
        return "Race: " + this.race + " - Split Difference between "
                + this.distance1 + " and " + this.distance2 + " is " + this.difference;
    }

    public static void main(String[] args)
    {
        String dbName = "SwimTeam";
        String tableName = "Swimmers";
        String[] columnNames =
        {
            "FirstName", "LastName", "SwimmerAge", "SwimGroup"
        };
        ArrayList<ArrayList<String>> data;
        JavaDatabase dbObj = new JavaDatabase(dbName);
        data = dbObj.getData(tableName, columnNames);
        // main class shows first (first name and last name) inputted into swimmerss table
        String name = data.get(0).get(0) + data.get(0).get(1);
        CalculateSplitDiff calcSplitObj = new CalculateSplitDiff(50, 100, "100 Free", name);
        System.out.println(calcSplitObj);
    }

    //method to get time from database according to specific distance
    public double getDistanceTime(int distance, String race, String name)
    {
        double distanceValue = 0;
        //specific swimmers table splits
        String dbName = "SwimTeam";
        String swimmerTable = name + "Splits";
        String[] swimmerColumns =
        {
            "Race", "Distance", "Split"
        };
        //create database connection with specific swimmers splits
        JavaDatabase dbObj = new JavaDatabase(dbName);
        ArrayList<ArrayList<String>> dataList = dbObj.getData(swimmerTable, swimmerColumns);
        //looking through the data in the specific swimmers table
        for (int i = 0; i < dataList.size(); i++)
        {
            //if the race column in row i equals the race inputted
            if (dataList.get(i).get(0).equals(race))
            {
                //if the distance column in row i equals the distance inputted
                if (Integer.parseInt(dataList.get(i).get(1)) == distance)
                {
                    //assign the time value to distance 1 Value
                    distanceValue = Double.parseDouble(dataList.get(i).get(2));
                }
            }
        }
        return distanceValue;
    }
}
