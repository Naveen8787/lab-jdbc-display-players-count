
package dao;
import model.Skill;
import utility.ConnectionManager;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class SkillDAO{

	public TreeMap<String,Integer> skillCount() throws SQLException, Exception{
		TreeMap<String,Integer> treeMap = new TreeMap<String,Integer>();
		
		int count=0;
		Skill skill=null;
		Connection con=ConnectionManager.getConnection();
		Statement st=con.createStatement();
		String sql = "select name, count(name) from skillnames group by name";
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next()) {
			skill=new Skill();
		    skill.setSkillName(rs.getString("name"));
		    count = rs.getInt("count(name)");
		    treeMap.put(skill.getSkillName(),count);
		}
		rs.close();
		st.close();
		con.close();
		return treeMap;
	}
}
