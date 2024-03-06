package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utilities.OwnException;
import utilities.User;


public class DBConnector implements Connector{
	private String url = "jdbc:mysql://localhost:3306/UBA_ACCET";
	private String userName = "root";
	private String password = "";
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,userName,password);
	}
	
	public User getUserDetails(String userName) throws OwnException {
		try {
			String query = "select * from USER_DETAILS WHERE USER_NAME = ?";
			User user = new User();
			
			try(Connection connection = getConnection()){
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, userName);
				try(ResultSet resultSet = statement.executeQuery()){
					
					if(resultSet.next()) {
						user.setUserName(resultSet.getString(1));
						user.setPassWord(resultSet.getString(2));
						user.setName(resultSet.getString(3));
					}
				}
				return user;
			}
			}catch(SQLException e) {
				throw new OwnException(e.getMessage());
			}
		}
	
	public void addUser(User user) throws OwnException {
		String query = "insert into USER_DETAILS VALUES(?,?,?) ";
		try {
			
			try(Connection connection = getConnection()){
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, user.getUserName());
				statement.setString(2, user.getPassWord());
				statement.setString(3, user.getName());
				statement.execute();
			
				}
			}catch(SQLException e) {
				throw new OwnException(e.getMessage());
			}
	}

}

