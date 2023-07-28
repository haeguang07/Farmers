package com.yedam.app.farm.service;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import oracle.jdbc.OracleConnection;

public class OracleArrayHandler implements TypeHandler<Object> {
	
	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		OracleConnection conn = ps.getConnection().unwrap(OracleConnection.class);
		Array reportsArray = (Array)conn.createOracleArray("STRINGARRAY", ((List<String>) parameter).toArray());
		ps.setArray(i, reportsArray);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		return null;
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}
	
}