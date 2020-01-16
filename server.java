import java.net.*;  
import java.io.*;  
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
class server{  
    public static void main(String args[])throws Exception{  
        ServerSocket ss=new ServerSocket(3333);  
        Socket s=ss.accept();  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        LocalDate date = null;
        LocalTime time = null;
        String str="",str2="";  
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");;
        //dout.writeUTF("Success");
        while(!str.equals("stop")){  
            str=din.readUTF();  
            System.out.println("client says: "+str);  
            //str2=br.readLine();
            if(str.equals("Hello")){
                str2 = "welcome";
                dout.writeUTF(str2);  
            }
            else if( str.equals("Time")){
                 time = LocalTime.parse(LocalTime.now().toString());
                 str2 = timeFormatter.format(time);
                dout.writeUTF(str2);
            }
            else if (str.equals("Date")){
                date = LocalDate.parse(LocalDate.now().toString());
                str2 = dateFormatter.format(date);
                dout.writeUTF(str2);
            }
            else if (str.equals("DateTime")){
                time = LocalTime.parse(LocalTime.now().toString());
                date = LocalDate.parse(LocalDate.now().toString());
                str2 = dateFormatter.format(date) + "\n" + timeFormatter.format(time);
                dout.writeUTF(str2);
            }  
            
            dout.flush();  
        }  
        din.close();  
        s.close();  
        ss.close();  
    }
}  