package jdbc_user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserCRUD {

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root");
		return connection;
	}
	
	public int signUpUser(User user) throws Exception
	{
		String sql = "insert into user(id,name,phone,email,password)values(?,?,?,?,?)";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    preparedStatement.setInt(1, user.getId());
	    preparedStatement.setString(2, user.getName());
	    preparedStatement.setLong(3, user.getPhone());
	    preparedStatement.setString(4, user.getEmail());
	    preparedStatement.setString(5, user.getPassword());
	    
	    int result = preparedStatement.executeUpdate();
	    connection.close();
	    return result;
	}
	
	public String loginUser(String email) throws Exception {
		String sql = "select password from user where email=?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet  = preparedStatement.executeQuery();
		String password = null;
		while(resultSet.next()) {
			password = resultSet.getString("password");
		}
		connection.close();
		return password;
	}
	
	public String getPassword(String email) throws Exception {
		String sql = "select password from user where email=?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet  = preparedStatement.executeQuery();
		String password = null;
		while(resultSet.next()) {
			password = resultSet.getString("password");
		}
		connection.close();
		return password;
	}
	
	public User displayPasswords(String email) throws Exception {
		Connection connection = getConnection();
		String sql = "select * from user where email=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = null;
		while(resultSet.next()) {
			String instagram = resultSet.getString("instagram");
			String facebook = resultSet.getString("facebook");
			String twitter = resultSet.getString("twitter");
			String snapchat = resultSet.getString("snapchat");
			user = new User();
			
			user.setInstagram(instagram);
			user.setFacebook(facebook);
			user.setTwitter(twitter);
			user.setSnapchat(snapchat);
		}
		 connection.close();
		 return user;
	}
	
	public int updatePasswords(User user, String email) throws Exception {
		String sql = "update user set facebook = ?, instagram=?, twitter=?, snapchat=? where email=?";
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    preparedStatement.setString(1, user.getFacebook());
	    preparedStatement.setString(2, user.getInstagram());
	    preparedStatement.setString(3, user.getTwitter());
	    preparedStatement.setString(4, user.getSnapchat());
	    preparedStatement.setString(5, email);
	    
	    int result = preparedStatement.executeUpdate();
	    connection.close();
	    return result;
	}
}
