package com.company.chatapp.users.dao;
import java.sql.*;

import com.company.chatapp.users.dto.UserDTO;
import com.company.chatapp.utils.Encryption;

//DB User CRUD
public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String SQL = "select userid from users where userid=? and password=?";
		try {
			connection = CommonDAO.createConnection();
			pstmt = connection.prepareStatement(SQL);
			pstmt.setString(1,  userDTO.getUserid());
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,  encryptedPwd);
			rs = pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(rs != null)rs.close();
			if(pstmt != null)pstmt.close();
			if(connection != null)connection.close();
		}
	}
	public int add(UserDTO userDTO) throws ClassNotFoundException, Exception{
		Connection connection = null;
		Statement stmt = null;
		try {
		connection = CommonDAO.createConnection();
		stmt = connection.createStatement();
		int record = stmt.executeUpdate("insert into users (userid, password) values ('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
		return record;
		}
		finally {
			if(stmt != null)stmt.close();
			if(connection != null)connection.close();
		}
	}
}
