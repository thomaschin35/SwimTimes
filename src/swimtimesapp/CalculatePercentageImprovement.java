package swimtimesapp;
//calculation class for percentage improvement

public class CalculatePercentageImprovement
{

    //variables that are passed in (encapsulated)
    private double currentTime;
    private double prevTime;
    //answer
    private String percentage;
    //percentage change value
    private double percValue;

    //main constructor
    public CalculatePercentageImprovement(String current, String previous)
    {
        setCurrentTime(current);
        setPrevTime(previous);
        setPercentage();
    }

    public double getCurrentTime()
    {
        return currentTime;
    }

    public void setCurrentTime(String currentTime)
    {
        this.currentTime = convertToSeconds(currentTime); // converting the string time to double time first
    }

    public double getPrevTime()
    {
        return prevTime;
    }

    public void setPrevTime(String prevTime)
    {
        this.prevTime = convertToSeconds(prevTime);//convert string time to double time first
    }

    public String getPercentage()
    {
        return percentage;
    }

    public void setPercentage()
    {
        //using percentage change formula
        // ((final-initial)/initial) * 100 = percentage change
        percValue = (currentTime - prevTime) / prevTime;
        percValue = percValue * 100;
        //rounding
        percValue = percValue * 1000;
        percValue = Math.round(percValue);
        percValue = percValue / 1000;
        percentage = percValue + "%";
    }

    @Override
    public String toString()
    {
        return "The percentage of improvment/change is: " + percentage;
    }

    public static void main(String[] args)
    {
        //to test
        CalculatePercentageImprovement percImproveObj = new CalculatePercentageImprovement("1.00", "1.89");
        System.out.println(percImproveObj);
    }

    public double convertToSeconds(String time)
    {
        //method to change string time(1:45.34)
        //to seconds(2345.34)
        double totalSec = 0;
        double seconds;
        String secondStr;
        //scanning through 
        //if time is like ex. 12:32.43
        if (time.length() == 8)
        {
            for (int i = 0; i < time.length(); i++)
            {
                if (i != 5 && i != 2) // if the charAt is not at the colon or point (: .)
                {
                    secondStr = time.charAt(i) + "";
                    seconds = Double.parseDouble(secondStr);

                    if (i == 7)
                    {
                        seconds = seconds / 100; //0.01 seconds
                    }
                    else if (i == 6)
                    {
                        seconds = seconds / 10; // 0.1 seconds
                    }
                    else if (i == 4)
                    {
                        seconds = seconds; // 1 second
                    }
                    else if (i == 3)
                    {
                        seconds = seconds * 10; // 1o seconds
                    }
                    else if (i == 1)
                    {
                        seconds = seconds * 60; // 1 minute
                    }
                    else if (i == 0)
                    {
                        seconds = seconds * 600; // 10 minutes
                    }
                    totalSec = totalSec + seconds;
                }
            }
        }
        //If time is like ex. 1:43.45
        if (time.length() == 7)
        {
            //loop to grab the number of seconds for every character
            for (int i = 0; i < time.length(); i++)
            {
                if (i != 4 && i != 1)
                {
                    secondStr = time.charAt(i) + "";
                    seconds = Double.parseDouble(secondStr);
                    if (i == 6)
                    {
                        seconds = seconds / 100; // 0.01 seconds
                    }
                    else if (i == 5)
                    {
                        seconds = seconds / 10; // 0.1 seconds
                    }
                    else if (i == 3)
                    {
                        seconds = seconds; //1 seconds
                    }
                    else if (i == 2)
                    {
                        seconds = seconds * 10; // 10 seconds
                    }
                    else if (i == 0)
                    {
                        seconds = seconds * 60; // 60 seconds
                    }

                    totalSec = totalSec + seconds;
                }
            }
        }
        //(if time is ex. 35.53)
        if (time.length() <= 5)
        {
            totalSec = Double.parseDouble(time);
        }
        return totalSec;
    }

}
