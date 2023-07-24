package com.yedam.app.user.service;

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
import com.yedam.app.user.vo.MemberVO;

import oracle.jdbc.OracleConnection;

public class OracleArrayMemHandler implements TypeHandler<Object> {

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
		List<MemberVO> members = (List<MemberVO>)parameter;
	    Object[] filetype = new Object[3]; 
	    Struct[] array = new Struct[members.size()];

	    int arrayIndex = 0;
	    for (MemberVO mem : members) {
	    	filetype[0] = mem.getMemNo();
	    	filetype[1] = mem.getMemGrd();
	    	filetype[2] = mem.getStts();
	    	array[arrayIndex++] = conn.createStruct("MEMBER_STTS", filetype);
	    }		
		Array filearray = (Array)conn.createOracleArray("MEMSTTSARRAY", (Struct[]) array);		
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