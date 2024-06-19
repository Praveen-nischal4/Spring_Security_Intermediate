package com.mysecurity.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysecurity.model.SignupDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDAOImpl implements SingupDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveUser(SignupDTO signupDTO) {
		
		String sql = "insert into users values(?,?,?)";
		String sql1 ="insert into authorities values(?,?)";
		
		//jdbcTemplate.update(sql, signupDTO.getUsername(),signupDTO.getPassword(),1);
		//jdbcTemplate.update(sql1, signupDTO.getUsername(),"user");
		
		jdbcTemplate.update(sql, new PreparedStatementSetter() 
		{

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, signupDTO.getPassword());
				ps.setInt(3, signupDTO.getEnabled());
			}
			
		});
		
		jdbcTemplate.update(sql1, new PreparedStatementSetter() 
		{

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, "Coder");
			}
			
		});
		
		
		
		}

}
