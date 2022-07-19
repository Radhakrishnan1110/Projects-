import java.util.*;
import java.io.FileWriter;  
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.UUID;
import java.sql.*;  

public class Main
{
    static final String DB_URL = "jdbc:mysql://localhost/powermanagement";
    static final String USER = "root";
    static final String PASS = "admin";
    Scanner sc=new Scanner(System.in);
    ArrayList<admin> adminobj = new ArrayList<admin>();
    void adminLogin(){
        System.out.println("Enter id,password to Login");
        int aid=sc.nextInt();
        String apass=sc.next();
        int f=0;
        admin curradmin = null;
        for(admin a:adminobj)
        {
            if(a.id==(aid) && a.password.equals(apass))
            {
                curradmin=a;
                f++;
                break;
            }
        }
        if(f==0)
        {
            System.out.println("Invalid Login or Password");
        }
        else
        {
            int a=0;
            System.out.println("Logined Successfully...\nWelcome "+curradmin.name);
            while(a==0)
            {
            System.out.println("\n1)CustomerTable Summary\n2)Customer Report \n3)Apply Filter to Database\n4)Login Table\n5)Tariff Table\n6)Modify Tariff details\n7)Logout");
            int choiceafterlogin=sc.nextInt();
            switch(choiceafterlogin)
            {
                case 1:
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    Statement s = con.createStatement();
                    System.out.println("Customer Table list are....");
                    System.out.println("----------------------------------------------------");
                    ResultSet rs = s.executeQuery("select * from customer");  
                    if (rs != null) // if rs == null, then there is no record in ResultSet to show  
                    while (rs.next()) // By this line we will step through our data row-by-row  
                    {  
                        System.out.println("CustomerID :" +rs.getString(1) + " Name: " + rs.getString(2)  +" Meterno: "+rs.getString(3)+" PaymentMode: "+rs.getString(4)+" BillType: "+rs.getString(5)+" ConsumedUnits: "+rs.getString(6)+" Amount: "+rs.getString(7));
                        System.out.println("----------------------------------------------------");
                    }
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                } 
                break;
                case 2:System.out.println("Generate Customer Report");
                int searchid = sc.nextInt();
                int n=0;
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    Statement s = con.createStatement();
                    ResultSet rs = s.executeQuery("select * from userdetails");  
                    if (rs != null) // if rs == null, then there is no record in ResultSet to show 
                    System.out.println("-------------------USERID FOUND----------------------------------"); 
                    while (rs.next()) // By this line we will step through our data row-by-row  
                    {  
                        if(rs.getInt(1)==searchid){ 
                            n++;
                            System.out.println("Customerid: "+ rs.getInt(1)+" Customername: "+rs.getString(2)+" Meterno: "+rs.getInt(3)+" Billtype: "+rs.getString(5)+" Payment type: "+rs.getString(4)+" Amount: "+rs.getFloat(7)+" Consumednit: "+rs.getFloat(6)+" Payment Done By: "+rs.getString(8)+" Date and Time: "+rs.getTimestamp(9));
                            FileWriter fw=new FileWriter("C:\\Users\\raman\\OneDrive\\Desktop\\Bill_calculation\\customerreport.txt");    
                            fw.write("Details of the Customer with the user id: "+searchid+"\n");
                            fw.write("\n");
                            fw.write("Customerid: "+ rs.getInt(1)+"\n"+"Customername: "+rs.getString(2)+"\n"+"Meterno: "+rs.getInt(3)+" \n"+"Billtype: "+rs.getString(5)+" \n"+"Payment type: "+rs.getString(4)+" \n"+"Amount: "+rs.getFloat(7)+"\n"+"Energy Consumed: "+rs.getFloat(6)+"\n"+"Date and Time of Payment: "+rs.getTimestamp(9)+"\nPaid By: "+rs.getString(8));
                            fw.close();   
                        }
                    }
                    System.out.println("-----------------------------------------------------"); 
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                if(n==0){
                    System.out.println("UseriD not found.Enter correct userID");
                }
                break;
                case 3:System.out.println("Select Filter to apply to the database ");
                System.out.println("\n1)BillType\n2)Location \n3)Mode of Payment\n4)By amount\n5)By units\n6)Tariff");
                int choice=sc.nextInt();
                switch(choice){
                    case 1:System.out.println("Enter Billtype to search");
                    String searchbilltype = sc.next();
                    int m=0;
                    try {  
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                       // String sql = "CREATE DATABASE STUDENTS1";
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("select * from customer");  
                        if (rs != null) // if rs == null, then there is no record in ResultSet to show 
                        System.out.println("-------------------Bill type "+searchbilltype+"----------------------------------"); 
                        while (rs.next()) // By this line we will step through our data row-by-row  
                        {  
                            if(rs.getString(5).equals(searchbilltype)){ 
                                System.out.println("Customerid: "+ rs.getInt(1)+" Customername: "+rs.getString(2)+" Meterno: "+rs.getInt(3)+" Billtype: "+rs.getString(5)+" Payment type: "+rs.getString(4)+" Amount: "+rs.getFloat(7)+" Consumednit: "+rs.getFloat(6));
                                m++;
                            }
                        }
                        System.out.println("-----------------------------------------------------"); 
                        s.close(); // close the Statement to let the database know we're done with it  
                        con.close(); 
                    } catch (SQLException err) {  
                        System.out.println("ERROR: " + err);  
                    } catch (Exception err) {  
                        System.out.println("ERROR: " + err);  
                    }
                    if(m==0){
                        System.out.println("No customer of the Billtype specified");
                    }
                    break;
                    case 2:System.out.println("Enter Location to search");
                    String Location = sc.next();
                    m=0;
                    try {  
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                       // String sql = "CREATE DATABASE STUDENTS1";
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("select * from logindetails");  
                        if (rs != null) // if rs == null, then there is no record in ResultSet to show 
                        System.out.println("-------------------Payment of type "+Location+"----------------------------------"); 
                        while (rs.next()) // By this line we will step through our data row-by-row  
                        {  
                            if(rs.getString(5).equals(Location)){ 
                                System.out.println("Customerid: "+ rs.getInt(1)+" Customername: "+rs.getString(2)+" Meterno: "+rs.getInt(4)+" Location: "+rs.getString(5));
                                m++;
                            }
                        }
                        System.out.println("-----------------------------------------------------"); 
                        s.close(); // close the Statement to let the database know we're done with it  
                        con.close(); 
                    } catch (SQLException err) {  
                        System.out.println("ERROR: " + err);  
                    } catch (Exception err) {  
                        System.out.println("ERROR: " + err);  
                    }
                    if(m==0){
                        System.out.println("No customer at this specified location");
                    }
                    break;
                    case 3:System.out.println("Enter Paymode to search");
                    String paymenttype = sc.next();
                    m=0;
                    try {  
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                       // String sql = "CREATE DATABASE STUDENTS1";
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("select * from customer");  
                        if (rs != null) // if rs == null, then there is no record in ResultSet to show 
                        System.out.println("-------------------Payment of type "+paymenttype+"----------------------------------"); 
                        while (rs.next()) // By this line we will step through our data row-by-row  
                        {  
                            if(rs.getString(4).equals(paymenttype)){ 
                                System.out.println("Customerid: "+ rs.getInt(1)+" Customername: "+rs.getString(2)+" Meterno: "+rs.getInt(3)+" Billtype: "+rs.getString(5)+" Payment type: "+rs.getString(4)+" Amount: "+rs.getFloat(7)+" Consumednit: "+rs.getFloat(6));
                                m++;
                            }
                        }
                        System.out.println("-----------------------------------------------------"); 
                        s.close(); // close the Statement to let the database know we're done with it  
                        con.close(); 
                    } catch (SQLException err) {  
                        System.out.println("ERROR: " + err);  
                    } catch (Exception err) {  
                        System.out.println("ERROR: " + err);  
                    }
                    if(m==0){
                        System.out.println("No customer of this payment mode specified");
                    }
                    break;
                    case 4:System.out.println("Enter amount");
                    Float threshold = sc.nextFloat();
                    m=0;
                    try {  
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                       // String sql = "CREATE DATABASE STUDENTS1";
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("select * from customer");  
                        if (rs != null) // if rs == null, then there is no record in ResultSet to show 
                        System.out.println("-------------------Amount greater than "+threshold +"----------------------------------"); 
                        while (rs.next()) // By this line we will step through our data row-by-row  
                        {  
                            if(rs.getFloat(7)>=threshold){ 
                                System.out.println("Customerid: "+ rs.getInt(1)+" Customername: "+rs.getString(2)+" Meterno: "+rs.getInt(3)+" Billtype: "+rs.getString(5)+" Payment type: "+rs.getString(4)+" Amount: "+rs.getFloat(7)+" Consumednit: "+rs.getFloat(6));
                                m++;
                            }
                        }
                        System.out.println("-----------------------------------------------------"); 
                        s.close(); // close the Statement to let the database know we're done with it  
                        con.close(); 
                    } catch (SQLException err) {  
                        System.out.println("ERROR: " + err);  
                    } catch (Exception err) {  
                        System.out.println("ERROR: " + err);  
                    }
                    if(m==0){
                        System.out.println("No payment is above the specified threshold");
                    }
                    break;
                    case 5:System.out.println("Enter unit");
                    threshold = sc.nextFloat();
                    m=0;
                    try {  
                        Class.forName("com.mysql.cj.jdbc.Driver");  
                        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                       // String sql = "CREATE DATABASE STUDENTS1";
                        Statement s = con.createStatement();
                        ResultSet rs = s.executeQuery("select * from customer");  
                        if (rs != null) // if rs == null, then there is no record in ResultSet to show 
                        System.out.println("-------------------Usage greater than "+threshold +"----------------------------------"); 
                        while (rs.next()) // By this line we will step through our data row-by-row  
                        {  
                            if(rs.getFloat(6)>=threshold){ 
                                System.out.println("Customerid: "+ rs.getInt(1)+" Customername: "+rs.getString(2)+" Meterno: "+rs.getInt(3)+" Billtype: "+rs.getString(5)+" Payment type: "+rs.getString(4)+" Amount: "+rs.getFloat(7)+" Consumednit: "+rs.getFloat(6));
                                m++;
                            }
                        }
                        System.out.println("-----------------------------------------------------"); 
                        s.close(); // close the Statement to let the database know we're done with it  
                        con.close(); 
                    } catch (SQLException err) {  
                        System.out.println("ERROR: " + err);  
                    } catch (Exception err) {  
                        System.out.println("ERROR: " + err);  
                    }
                    if(m==0){
                        System.out.println("No Customer has used above the specified threshold");
                    }
                    break;
                }
                break;
                case 4:
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    Statement s = con.createStatement();
                    System.out.println("Login  Table list are....");
                    System.out.println("----------------------------------------------------");
                    ResultSet rs = s.executeQuery("select * from logindetails");  
                    if (rs != null) // if rs == null, then there is no record in ResultSet to show  
                    while (rs.next()) // By this line we will step through our data row-by-row  
                    {  
                        System.out.println("CustomerID :" +rs.getString(1) + " Name: " + rs.getString(2)  +" Password: "+rs.getString(3)+" Meterno: "+rs.getString(4)+" Location: "+rs.getString(5));
                        System.out.println("----------------------------------------------------");
                    }
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                break;
                case 5:
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    Statement s = con.createStatement();
                    System.out.println("Traiff Plan details are....");
                    System.out.println("----------------------------------------------------");
                    ResultSet rs = s.executeQuery("select * from tariff");  
                    if (rs != null) // if rs == null, then there is no record in ResultSet to show  
                    while (rs.next()) // By this line we will step through our data row-by-row  
                    {  
                        System.out.println("UnitRange :" +rs.getString(1) + " Domestic: " + rs.getString(2)  +" Industrial: "+rs.getString(3)+" Commercial: "+rs.getString(4));
                        System.out.println("----------------------------------------------------");
                    }
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                break;
                case 6:
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                   System.out.println("Enter the new rupees for respective category in the format d/c/k");
                    float d = sc.nextFloat();
                    float c = sc.nextFloat();
                    float i = sc.nextFloat(); 
                    String sql = "update tariff set Domestic =?,Commercial=?,Industrial=? WHERE UnitRange=?";
                    Statement s = con.createStatement();
                    PreparedStatement p = con.prepareStatement(sql);
                    p.setFloat(1,d);
                    p.setFloat(2, c);
                    p.setFloat(3, i);
                    p.setString(4,"0-100");
                    // 3. Execute SQL query
                    p.executeUpdate();
                    System.out.println("Enter the new rupees for respective category in the format d/c/k");
                    d = sc.nextFloat();
                    c = sc.nextFloat();
                    i = sc.nextFloat(); 
                    String sql1 = "update tariff set Domestic =?,Commercial=?,Industrial=? WHERE UnitRange=?";
                    PreparedStatement p1 = con.prepareStatement(sql1);
                    p1.setFloat(1,d);
                    p1.setFloat(2, c);
                    p1.setFloat(3, i);
                    p1.setString(4,"100-200");
                    // 3. Execute SQL query
                    p1.executeUpdate();
                    System.out.println("Enter the new rupees for respective category in the format d/c/k");
                    d = sc.nextFloat();
                    c = sc.nextFloat();
                    i = sc.nextFloat(); 
                    String sql2 = "update tariff set Domestic =?,Commercial=?,Industrial=? WHERE UnitRange=?";
                    PreparedStatement p2 = con.prepareStatement(sql2);
                    p2.setFloat(1,d);
                    p2.setFloat(2, c);
                    p2.setFloat(3, i);
                    p2.setString(4,"200-500");
                    p2.executeUpdate();
                    System.out.println("Enter the new rupees for respective category in the format d/c/k");
                    d = sc.nextFloat();
                    c = sc.nextFloat();
                    i = sc.nextFloat(); 
                    String sql3 = "update tariff set Domestic =?,Commercial=?,Industrial=? WHERE UnitRange=?";
                    PreparedStatement p3 = con.prepareStatement(sql3);
                    p3.setFloat(1,d);
                    p3.setFloat(2, c);
                    p3.setFloat(3, i);
                    p3.setString(4,">500");
                    // 3. Execute SQL query
                    p3.executeUpdate();

                    //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                    //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                break;
                case 7:System.out.println("You have successfully loggedout of admin page...come back again!!!");
                a++;
                break;

            }
            }
        }
    }
    static String usingRandomUUID(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");
    }
    void billPaymentMode(int id,String name,int meter_no,Double amount,String billtype,long units){
        System.out.println("Enter Payment Mode");
        System.out.println("\n1)Online\n2)Offline\n");
        int choiceafterlogin = sc.nextInt();
        switch(choiceafterlogin)
        {
            case 1:System.out.println("-----------Welcome to Online Payment!----------");
            System.out.println("Amount to be paid:"+ amount);
            System.out.println("Bill Paid Successfully");
            java.util.Date date = new java.util.Date();
            String randomstr = usingRandomUUID();
            //paymentDetailsobj.add(new PaymentDetails(id,randomstr,date,"Self"));
            //customerTablesobj.add(new CustomerTable(id,name,meter_no,"Online ",billtype,units,amount,randomstr,date,"Self"));
            try{    
                FileWriter fw=new FileWriter("C:\\Users\\raman\\OneDrive\\Desktop\\Bill_calculation\\Billdetails.txt");    
                fw.write("                BILL PAID SUCCESSFULLY          \n");
                fw.write("Paid date and Time:"+ date+"\n");
                fw.write("Order Id:"+ randomstr+"\n");
                fw.write("Customerid: "+ id +"\nCustomername: "+name+"\n"+"Meterno: "+meter_no+"\n"+"Payment Mode: "+"Online"+" \n"+"Unit Consumed :"+ units+"Wh"+"\nAmount: "+amount+"\n"+"Paid by:"+"Self");
                fw.close();    
               }catch(Exception e){System.out.println(e);}    
               System.out.println("Successfully Biil report generated and your Oderid is"+randomstr);
               try {  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
               // String sql = "CREATE DATABASE STUDENTS1";
                String sql = "update UserDetails set Name =?,MeterNo=?,PaymentMode=?,BillType=?,ConsumedUnits=?,Amount=?,PaymentDoneBy=?,DateandTime=? WHERE CustomerID=?";
                //String sql = "insert into Customer" +"(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumedUnits,Amount)"+ "values(?,?,?,?,?,?,?,?,?)";
                Statement s = con.createStatement();
                PreparedStatement p = con.prepareStatement(sql);
                p.setInt(9,id);
                p.setString(1, name);
                p.setInt(2, meter_no);
                p.setString(3, "Online");
                p.setString(4, billtype);
                p.setFloat(5, units);
                float fi = amount.floatValue();
                p.setFloat(6,fi);
                p.setString(7, "Self");
                p.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                // 3. Execute SQL query
                p.executeUpdate();
                //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                s.close(); // close the Statement to let the database know we're done with it  
                con.close(); 
            } catch (SQLException err) {  
                System.out.println("ERROR: " + err);  
            } catch (Exception err) {  
                System.out.println("ERROR: " + err);  
            }
            break;
            case 2:System.out.println("-----------Welcome to Offline Payment!----------");
            System.out.println("Amount to be paid:"+ amount);
            System.out.println("Bill Paid Successfully");
            java.util.Date date1 = new java.util.Date();
            String randomstr1 = usingRandomUUID();
            try {  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
               // String sql = "CREATE DATABASE STUDENTS1";
               
                String sql = "update UserDetails set Name =?,MeterNo=?,PaymentMode=?,BillType=?,ConsumedUnits=?,Amount=?,PaymentDoneBy=?,DateandTime=? WHERE CustomerID=?";
                //String sql = "insert into Customer" +"(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumedUnits,Amount)"+ "values(?,?,?,?,?,?,?,?,?)";
                Statement s = con.createStatement();
                PreparedStatement p = con.prepareStatement(sql);
                p.setInt(9,id);
                p.setString(1,name);
                p.setInt(2, meter_no);
                p.setString(3, "Offline");
                p.setString(4, billtype);
                p.setFloat(5, units);
                float fi = amount.floatValue();
                p.setFloat(6,fi);
                p.setString(7, "xyz");
                p.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
                // 3. Execute SQL query
                p.execute();
                //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                s.close(); // close the Statement to let the database know we're done with it  
                con.close(); 
            } catch (SQLException err) {  
                System.out.println("ERROR: " + err);  
            } catch (Exception err) {  
                System.out.println("ERROR: " + err);  
            }
            try{    
                FileWriter fw=new FileWriter("C:\\Users\\raman\\OneDrive\\Desktop\\Bill_calculation\\Billdetails.txt");    
                fw.write("                BILL PAID SUCCESSFULLY        \n");
                fw.write("Paid date and Time:"+ date1+"\n");
                fw.write("Customerid: "+ id +"\nCustomername: "+name+"\n"+"Meterno: "+meter_no+" \n"+"Billtype: "+billtype+" \n"+"Payment Mode: "+"Offline"+" \n"+"Unit Consumed :"+ units +"Wh"+"\nAmount: "+amount+"\n"+"Paid by:"+"xyz");
                fw.close();    
               }catch(Exception e){System.out.println(e);}    
               System.out.println("Successfully Biil report generated and your OrderID is "+randomstr1);
               System.out.println("Paid By Mr.XYZ contact for further details  ");
            break;
        }
    }

    void SignUp()
    {
        System.out.println("---------NEW USER REGISTRATION----------");
        System.out.println("Enter id,name,password,door_no,location");
        int id=sc.nextInt();
        String name=sc.next();
        String pass=sc.next();
        int meter_no=sc.nextInt();
        String location = sc.next();
        int f=0;
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
           // String sql = "CREATE DATABASE STUDENTS1";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from logindetails");  
            if (rs != null) // if rs == null, then there is no record in ResultSet to show  
            while (rs.next()) // By this line we will step through our data row-by-row  
            {  
                if(rs.getInt(1)==id){
                    f++;
                    break;
                }
            }
            s.close(); // close the Statement to let the database know we're done with it  
            con.close(); 
        } catch (SQLException err) {  
            System.out.println("ERROR: " + err);  
        } catch (Exception err) {  
            System.out.println("ERROR: " + err);  
        }
        if(f==0)
        {
            try {  
                Class.forName("com.mysql.cj.jdbc.Driver");  
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
               // String sql = "CREATE DATABASE STUDENTS1";
                //String sql = "insert into logindetails" +"(CustomerID,Name,,Password,MeterNo,Location)"+ "values(?,?,?,?,?)";
                Statement s = con.createStatement();
                String sql1 = "insert customer set CustomerID=?,Name=?,MeterNo=?,BillType=?,ConsumedUnits=?,Amount=?";
                PreparedStatement k = con.prepareStatement(sql1);
                k.setInt(1,id);
                k.setString(2, name);
                k.setInt(3,meter_no);
                k.setString(4,"New user");
                k.setFloat(5, 0);
                k.setFloat(6, 0);
                // 3. Execute SQL query
                k.executeUpdate();
                String sql = "insert logindetails set CustomerID=?,Name=?,Password=?,MeterNo=?,Location=?";
                //s.execute("insert into customer values(i, 'eee', 29, 'Offline', 'Domestic', 900,500)");
                PreparedStatement p = con.prepareStatement(sql);
                p.setInt(1,id);
                p.setString(2, name);
                p.setString(3,pass);
                p.setInt(4, meter_no);
                p.setString(5, location);
                // 3. Execute SQL query
                p.executeUpdate();
                //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                s.close(); // close the Statement to let the database know we're done with it  
                con.close(); 
            } catch (SQLException err) {  
                System.out.println("ERROR: " + err);  
            } catch (Exception err) {  
                System.out.println("ERROR: " + err);  
            }
            System.out.println("--------- USER REGISTRATED SUCCESSFULLY----------");
        }
        else
        {
            System.out.println("User id already exist");
        }
    }
    void customerLogin()
    {
        System.out.println("Enter UserId and password to Login");
        int id=sc.nextInt();
        String pass=sc.next();
        int f=0;
        String name ="null";
        String location = "null";
        int meter_no = 0;
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
           // String sql = "CREATE DATABASE STUDENTS1";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from logindetails");  
            if (rs != null) // if rs == null, then there is no record in ResultSet to show  
            while (rs.next()) // By this line we will step through our data row-by-row  
            {  
                if(rs.getInt(1)==id && rs.getString(3).equals(pass)){
                    f++;
                    name = rs.getString(2);
                    meter_no = rs.getInt(4);
                    location = rs.getString(5);
                    break;
                }
            }
            s.close(); // close the Statement to let the database know we're done with it  
            con.close(); 
        } catch (SQLException err) {  
            System.out.println("ERROR: " + err);  
        } catch (Exception err) {  
            System.out.println("ERROR: " + err);  
        }
        if(f==0)
        {
            System.out.println("Invalid Login or Password");
        }
        else
        {
            int t=0;
            System.out.println("Logined Successfully...\nWelcome "+ name);
            while(t==0)
            {
            System.out.println("\n1)DomesticBill\n2)CommercialBill\n3)IndustrialBill\n4)Logout");
            int choiceafterlogin=sc.nextInt();
            String billtype="null";
            switch(choiceafterlogin)
            {
                case 1:System.out.println("Domestic EB Bill Calculation");
                System.out.println("Enter consumed power units");
                billtype = "Domestic";
                long units = sc.nextLong();
                double billpay = 0;
                if(units<100){
		            billpay = 0;
                }
	            else if(units>=100 && units<200){
		            billpay = (units-100)*1.5;
                }
	            else if(units>200 && units <500){
		            billpay = 100*1.5 +(units-200)*3;
                }else{
                    billpay = 100*1.5 + 300*3 +(units-500)*5.5;
                }
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    String sql = "update Customer set Name =?,MeterNo=?,PaymentMode=?,BillType=?,ConsumedUnits=?,Amount=? WHERE CustomerID=?";
                    //String sql = "insert into Customer" +"(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumedUnits,Amount)"+ "values(?,?,?,?,?,?,?)";
                    Statement s = con.createStatement();
                    PreparedStatement p = con.prepareStatement(sql);
                    p.setInt(7, id);
                    p.setString(1, name);
                    p.setInt(2, meter_no);
                    p.setString(3, "Online");
                    p.setString(4, "Domestic");
                    p.setFloat(5, units);
                    float fi = (float)billpay;
                    p.setFloat(6,fi);
                    // 3. Execute SQL query
                    p.executeUpdate();
                    //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                    //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                billPaymentMode(id,name,meter_no,billpay,billtype,units);
                System.out.println("-------------------------BILL GENERATED-------------------------------");
                System.out.println("Customerid: "+ id+"\n"+"Customername: "+name+"\n"+"Doorno: "+meter_no+" \n"+"Billtype: "+"Domestic"+" \n"+"Payment type: "+"Online"+" \n"+"Amount Paid: "+billpay+"\n "+"Usage: "+units);
                System.out.println("-----------------------THANK YOU! HAVE A GOOD DAY------------------------------");
                break;
                case 2:System.out.println("Commercial EB Bill Calculation");
                System.out.println("Enter consumed power units");
                billtype = "Commercial";
                units = sc.nextLong();
                //billpay = 0;
                if(units<100){
		            billpay = 100*3.5;
                }
	            else if(units>=100 && units<200){
		            billpay = 100*3.5 + (units-100)*4.5;
                }
	            else if(units>=200 && units <500){
		            billpay = 100*3.5 +100*4.5+(units-200)*5.5;
                }else{
                    billpay = 100*3.5 + 100*4.5 +300*5.5 +(units-500)*7;
                }
                billPaymentMode(id,name,meter_no,billpay,billtype,units);
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    String sql = "update Customer set Name =?,MeterNo=?,PaymentMode=?,BillType=?,ConsumedUnits=?,Amount=? WHERE CustomerID=?";
                    //String sql = "insert into Customer" +"(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumedUnits,Amount)"+ "values(?,?,?,?,?,?,?)";
                    Statement s = con.createStatement();
                    PreparedStatement p = con.prepareStatement(sql);
                    p.setInt(7, id);
                    p.setString(1, name);
                    p.setInt(2, meter_no);
                    p.setString(3, "Online");
                    p.setString(4, "Commercial");
                    p.setFloat(5, units);
                    float fi = (float)billpay;
                    p.setFloat(6,fi);
                    // 3. Execute SQL query
                    p.executeUpdate();
                    //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                    //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                System.out.println("-------------------------BILL GENERATED-------------------------------");
                System.out.println("Customerid: "+ id+"\n"+"Customername: "+name+"\n"+"Doorno: "+meter_no+" \n"+"Billtype: "+"Commercial"+" \n"+"Payment type: "+"Online"+" \n"+"Amount Paid: "+billpay+"\n "+"Usage: "+units);
                System.out.println("-----------------------THANK YOU! HAVE A GOOD DAY------------------------------");
                break;
                case 3:System.out.println("Industrial EB Bill Calculation");
                System.out.println("Enter consumed power units");
                units = sc.nextLong();
                billtype = "Industrial";
                if(units<100){
		            billpay = 100*8;
                }
	            else if(units>=100 && units<200){
		            billpay = 100*8 + (units-100)*10;
                }
	            else if(units>=200 && units <500){
		            billpay = 100*8 +100*10+(units-200)*12;
                }else{
                    billpay = 100*8 + 100*10 +300*12 +(units-500)*15;
                }
                billPaymentMode(id,name,meter_no,billpay,billtype,units);
                try {  
                    Class.forName("com.mysql.cj.jdbc.Driver");  
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                   // String sql = "CREATE DATABASE STUDENTS1";
                    String sql = "update Customer set Name =?,MeterNo=?,PaymentMode=?,BillType=?,ConsumedUnits=?,Amount=? WHERE CustomerID=?";
                    //String sql = "insert into Customer" +"(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumedUnits,Amount)"+ "values(?,?,?,?,?,?,?)";
                    Statement s = con.createStatement();
                    PreparedStatement p = con.prepareStatement(sql);
                    p.setInt(7, id);
                    p.setString(1, name);
                    p.setInt(2, meter_no);
                    p.setString(3, "Offine");
                    p.setString(4, "Industrial");
                    p.setFloat(5, units);
                    float fi = (float)billpay;
                    p.setFloat(6,fi);
                    // 3. Execute SQL query
                    p.executeUpdate();
                    //s.executeUpdate("insert into customer(CustomerID,Name,MeterNo,PaymentMode,BillType,ConsumerUnits,Amount) values("+id+",'"+h.name+"',"+h.door_no+",'"+"Domestic"+",'"+"Online"+",'"+units+",'"+billpay+")");
                    //s.execute("insert into Logindetails values(id,name,pass,meter_no,location)");
                    s.close(); // close the Statement to let the database know we're done with it  
                    con.close(); 
                } catch (SQLException err) {  
                    System.out.println("ERROR: " + err);  
                } catch (Exception err) {  
                    System.out.println("ERROR: " + err);  
                }
                System.out.println("-------------------------BILL GENERATED-------------------------------");
                System.out.println("Customerid: "+ id+"\n"+"Customername: "+name+"\n"+"Doorno: "+meter_no+" \n"+"Billtype: "+"Industrial"+" \n"+"Payment type: "+"Offline"+" \n"+"Amount Paid: "+billpay+"\n "+"Usage: "+units);
                System.out.println("-----------------------THANK YOU! HAVE A GOOD DAY------------------------------");
                break;
                case 4:System.out.println("You have successfully loggedout...come back again!!!");
                       t++;
                       break;
            }
        }
    }
}
    void Login()
    {
        System.out.println("\n1)Admin Login\n2)Customer Login\n3)Quit");
        int choiceafterlogin=sc.nextInt();
        switch(choiceafterlogin)
        {
            case 1:adminLogin();
                   break;
            case 2:customerLogin();
                   break;
            case 3:System.exit(0);
        }
    }
    public void createObj()
    {
            adminobj.add(new admin(1,"admin","admin"));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        Main m=new Main();
        m.createObj();
        while(true)
        {
        System.out.println("Welcome! to Power Management System");
        System.out.println("1)Sign-up\n2)Login\n3)Stop Program");
        choice=sc.nextInt();
        switch(choice)
        {
            case 1:m.SignUp();
                   break;
            case 2:m.Login();
                   break;
            case 3:System.exit(0);
        }
       }
    }
}