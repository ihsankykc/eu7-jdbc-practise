package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {
    String dbUrl="jdbc:oracle:thin:@54.91.219.9:1521:xe";
    String dbUsername="hr";
    String dbPassword="hr";

    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet=statement.executeQuery("Select * from departments");

//        while (resultSet.next()){
//            System.out.println(resultSet.getString(1));
//        }

        //How to know how many rows we have for the query
        //go to last row
        resultSet.last();
        //get te row count
        int rowCount=resultSet.getRow();
        System.out.println(rowCount);

        resultSet.beforeFirst();

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet=statement.executeQuery("Select * from departments");

        //get the database related metaData
        DatabaseMetaData dbMetaData= connection.getMetaData();
        System.out.println("User ="+dbMetaData.getUserName());
        System.out.println("DB Product name ="+dbMetaData.getDatabaseProductVersion());
        System.out.println("DB Product version ="+dbMetaData.getDriverName());
        System.out.println("Driver Version ="+dbMetaData.getDriverVersion());

        //get the resultset related metaData
        ResultSetMetaData rs=resultSet.getMetaData();

        //How many columns we have

        int colCount= rs.getColumnCount();
        System.out.println("colCount = " + colCount);

        //get columnNames

        for (int i = 1; i <= rs.getColumnCount(); i++) {

            System.out.println(rs.getColumnName(i));

        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
