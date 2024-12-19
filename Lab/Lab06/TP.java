import java.util.*;
import java.io.*;
import java.time.*;

class TP
{
    long startTime, endTime;

    public void startTime()
    {
        startTime = System.nanoTime();
    }

    public void endTime()
    {
        endTime = System.nanoTime();
    }

    public double Time()
    {
        return (endTime - startTime) / 1000000;
    }
}