package com.yedam.app.farm.service;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import com.yedam.app.user.vo.AttachVO;

import oracle.jdbc.OracleConnection;

public class OracleArrayStructHandler implements TypeHandler<Object> {

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex);
	}
  
	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,	JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			return;
		OracleConnection conn = ps.getConnection().unwrap(OracleConnection.class); 	
		List<AttachVO> files = (List<AttachVO>)parameter;
	    Object[] filetype = new Object[2]; 
	    Struct[] array = new Struct[files.size()];

	    int arrayIndex = 0;
	    for (AttachVO file : files) {
	    	filetype[0] = file.getAtchFileName();
	    	filetype[1] = file.getUplFileName();
	    	array[arrayIndex++] = conn.createStruct("FILETYPE", filetype);
	    }		
		Array filearray = (Array)conn.createOracleArray("FILEARRAY", (Struct[]) array);		
		ps.setArray(i, filearray);
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {

		String value = "";
		try {
			if (!StringUtils.hasText(rs.getString(columnName))) {
				value = new String(rs.getString(columnName).getBytes("8859_1"), "KSC5601");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

}