package db;

import java.sql.*;

public class JDBCConnector {
    private final String host = "localhost";
    private final String port = "3306";
    private final String user = "root";
    private final String password = "abdelnaby";
    private final String dbName = "QAdbt";
    private final String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;


    public ResultSet connect(){
        Connection connection;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM EmployeeInfo where location Like 'egypt, cairo'");
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("name"));
//                System.out.println(resultSet.getString("location"));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return resultSet;

    }
}
