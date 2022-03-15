package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@54.91.219.9:1521:xe";
        String dbUsername="hr";
        String dbPassword="hr";

        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement= connection.createStatement();
        //run query and get the result in resulset object
        ResultSet resultSet=statement.executeQuery("Select * from regions");

        //move pointer to the first row
        resultSet.next();

        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(2));

        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));

        resultSet.next();

        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(2));

        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString("region_name"));



        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
