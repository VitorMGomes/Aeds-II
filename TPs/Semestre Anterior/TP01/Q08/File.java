import java.io.*;

public class File {
    
    public static void main(String[] args)
    {
        try {
            RandomAccessFile file = new RandomAccessFile("file.txt", "rw"); 
            int rep = MyIO.readInt(); 
            
            for(int i = 0; i < rep; i++) 
            {
                double num = MyIO.readDouble(); 
                file.writeDouble(num); 
            }

            file.close();

            file = new RandomAccessFile("file.txt", "r");

            for(int i = rep - 1; i >= 0 ; i--) 
            {
                file.seek(i * 8);
                double resultD = file.readDouble();
                
                int resultI = (int) resultD ;

                if(resultI == resultD)
                {
                    MyIO.println(resultI);
                }
                else
                {
                    MyIO.println(resultD);
                }
            }

            file.close();
        } catch (IOException e) {}
    }
}