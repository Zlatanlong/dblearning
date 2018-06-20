package dao;

import db.DBConnent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Student;

public class StudentDao {
    /**
     * 向数据库增加一个学生类型
     * @param s 一个学生
     * @throws Exception 
     */
    public void addStudent(Student s) throws Exception{
		Connection conn=DBConnent.getConnection();
		String sql="" +
				"insert into students" +
				"(num,name,sex,age,major,reward)" +			
				"values(" +
				"?,?,?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(1,s.getNum());
		ptmt.setString(2, s.getName());
		ptmt.setString(3, s.getSex());
		ptmt.setInt(4, s.getAge());
		ptmt.setString(5, s.getMajor());
		ptmt.setString(6, s.getReward());//这里可能出问题	
		ptmt.executeUpdate();
	}
	
	public void updateStudent(Student s) throws SQLException{
		Connection conn=DBConnent.getConnection();
		String sql="" +
				" update students " +
				" set name=?,sex=?,age=?,major=?,reward=?" +
				" where num=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		
		ptmt.setString(6,s.getNum());
		ptmt.setString(1, s.getName());
		ptmt.setString(2, s.getSex());
		ptmt.setInt(3, s.getAge());
		ptmt.setString(4, s.getMajor());
		ptmt.setString(5, s.getReward());//这里可能出问题		
		ptmt.execute();
	}
	
	public void delStudent(String num) throws SQLException{
		Connection conn=DBConnent.getConnection();
		String sql="" +
				" delete from students " +
				" where num=? ";
		PreparedStatement ptmt=conn.prepareStatement(sql);
                
		ptmt.setString(1, num);
		ptmt.execute();
	}
        /**
         * 查询全部学生的全部信息
         * @return
         * @throws Exception 
         */
	public List<Student> query() throws Exception{
		List<Student> result=new ArrayList<Student>();
		
		Connection conn=DBConnent.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select num,name,sex,age,major,reward from students");
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		ResultSet rs=ptmt.executeQuery();
		
		Student g=null;
		while(rs.next()){
			g=new Student();
			g.setNum(rs.getString("num"));
			g.setName(rs.getString("name"));
                        g.setSex(rs.getString("sex"));
			g.setAge(rs.getInt("age"));
                        g.setMajor(rs.getString("major"));
			g.setReward(rs.getString("reward"));			
			result.add(g);
		}
		return result;
	}
        /**
         * 按照某些属性查找n条元组
         * @param params 要查询的属性
         * @return
         * @throws Exception 
         */
        public List<Student> query(List<Map<String, Object>> params) throws Exception{
		List<Student> result=new ArrayList<>();
		
		Connection conn=DBConnent.getConnection();
		StringBuilder sb=new StringBuilder();
		sb.append("select * from students where 1=1 ");
		
		if(params!=null&&params.size()>0){
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sb.append(" and  ").append(map.get("name")).append(" ").append(map.get("rela")).append(" ").append(map.get("value")).append(" ");
			}
		}
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());
		
		ResultSet rs=ptmt.executeQuery();
		
		Student g=null;
		while(rs.next()){
			g=new Student();
			g.setNum(rs.getString("num"));
			g.setName(rs.getString("name"));
                        g.setSex(rs.getString("sex"));
			g.setAge(rs.getInt("age"));
                        g.setMajor(rs.getString("major"));
			g.setReward(rs.getString("reward"));			
			result.add(g);
		}
		return result;
	}
        
}
