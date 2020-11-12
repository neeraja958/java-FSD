package com.iiht.evaluation.eloan.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.exception.ELoanException;
import com.iiht.evaluation.eloan.model.Attributes;
import com.iiht.evaluation.eloan.model.User;


public class UserAuth implements IUserAuth{

	private static String GET_USER_QRY=
			"SELECT * FROM adb_users WHERE user_name=?";
	private static String ADD_USER_QRY=
			"INSERT INTO adb_users VALUES(?,?,?)";
	
	@Override
	public User authenticate(String userName,String password) throws ELoanException {
		User user=null;
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(Attributes.GET_ROLE_INFO)){
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();

			user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
			}catch (SQLException e) {
			throw new ELoanException("SQL Exception");
		}
		return user;
	}

	@Override
	public User applyLoan(User user) throws ELoanException {		
		try(Connection con = ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(ADD_USER_QRY)){
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new ELoanException("SQL Exception");
		}
		return user;
	}

}
