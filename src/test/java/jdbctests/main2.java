package jdbctests;

import java.sql.*;

public class main2 {
    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@54.91.219.9:1521:xe";
        String dbUsername="hr";
        String dbPassword="hr";

        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement= connection.createStatement();
        //run query and get the result in resulset object
        ResultSet resultSet=statement.executeQuery("Select * from departments");

        while (resultSet.next()){
          //  System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));
            System.out.println(resultSet.getString(1)+"-"+resultSet.getString(2)+"-"+resultSet.getString(3)+"-"+resultSet.getString(4));
        }



    }

}
