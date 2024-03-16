package JDBC.JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
	
	public static void main(String[]args) {
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "Test";
		String dbURL =url+dbName;		
		String username ="root";
		String password ="root";
		
		Connection connection =null;
		
		try {
			//create an object for mysql JDBC driver class		
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver).newInstance();
			
			
			//connect to mysql database		
			 connection = DriverManager.getConnection(dbURL,username,password);
			
			if (!connection.isClosed()) {
				System.out.println("Successfully connect to Test DB");
				
				Statement statement = connection.createStatement();
				
				//Execute the query
				ResultSet resultSet = statement.executeQuery("Select * from Employee where Name ='Siva'");
				
				while(resultSet.next()) {
					System.out.println(resultSet.getString("Name")+"----"+ resultSet.getString("Location")+"---"+ resultSet.getString("Experience"));
					
				}
				
				//Execute prepared statement to retrive the filtere records from the Employee table records
				PreparedStatement preparedStatment = connection.prepareStatement("Select * from Employee where  Name=? and Experience=?");
				preparedStatment.setString(1, "Siva");
				preparedStatment.setInt(2, 12);
				ResultSet resultSet2 = preparedStatment.executeQuery();
				
				while(resultSet.next()){
					System.out.println(resultSet2.getString("Name")+"---"+resultSet2.getInt("Experience")+"--"+resultSet2.getString("Location"));
				}
			}
						

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}finally {
			
			//close databse connection
			
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if (connection.isClosed()) {
					System.out.println("Connect is closed");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			}

}
