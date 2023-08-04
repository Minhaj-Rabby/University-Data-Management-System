package universitydata.teacher;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import universitydata.common.DataBaseConnection;

public class TeacherData 
{
	static Connection con=DataBaseConnection.getConnection();
	public static void closeConnection()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getTotalTeacher()
	{
		int total=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from teachers");
			rs.next();
			total=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return total;
	}
	public int getTotalTeacher(String departmentcode,int sem)
	{
		int total=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from teachers where departmentcode='"+departmentcode+"' and semoryear="+sem);
			rs.next();
			total=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return total;
	}
	public int getTeacher(String departmentcode,int sem)
	{
		int f=0;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from teachers where departmentcode='"+departmentcode+"' and semoryear="+sem);
			rs.next();
			f=rs.getInt(1);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}
	public int createTeacherID()
	{
		int id=1;
		try
		{
            String query="SELECT teacherid FROM (SELECT teacherid FROM newteachers WHERE teacherid = (SELECT MAX(teacherid) FROM newteachers) UNION SELECT teacherid FROM teachers WHERE teacherid = (SELECT MAX(teacherid) FROM teachers)) AS M ORDER BY teacherid DESC LIMIT 1;";
			java.sql.Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			id=id+rs.getInt(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return id;
		
	}
	public ResultSet getTeacherInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select teacherid as 'Teacher ID',teachername as 'Teacher Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from teachers  "+condition+" order by teacherid";
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public ResultSet getPenddingTeacherInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select teacherid as 'Teacher ID',teachername as 'Teacher Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from newteachers  "+condition+" order by teacherid ;";
			
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public ResultSet searchTeacher(String query)
	{
		ResultSet rs=null;
		query+=" order by teacherid";
		try
		{
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public boolean isActive(String teacherid)
	{
		try
		{
			String query="select activestatus from teachers where teacherid="+teacherid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getBoolean(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return false;
	}
	public String getTeacherName(String teacherid)
	{
		try
		{
			String query="select teachername from teachers where teacherid="+teacherid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return "";
	}
	public int addTeacherData(Teacher t)
	{
		int result=0;
		String query="insert into teachers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, t.getTeacherId());
			pr.setString(2,t.getTeacherName());
			pr.setString(3,t.getState());
			pr.setString(4,t.getCity());
			pr.setString(5,t.getEmailId());
			pr.setString(6,t.getContactNumber());
			pr.setString(7,t.getQualification());
			pr.setString(8,t.getExperience());
			pr.setString(9,t.getBirthDate());
			pr.setString(10,t.getGender());
			pr.setBytes(11,t.getProfilePicInBytes());
			pr.setString(12, "Not Assigned");
			pr.setInt(13, 0);
			pr.setString(14, "Not Assigned");
			pr.setString(15, "Not Assigned");
			pr.setInt(16, 0);
			pr.setString(17, null);
			pr.setString(18, t.getBirthDate());
			pr.setBoolean(19, t.getActiveStatus());
			pr.setString(20, t.generateJoinedDate());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public int deletePendingTeacherData(int tid)
	{
		int result=0;
		try
		{
			String query="DELETE FROM newteachers WHERE newteachers.teacherid="+tid+";";
			Statement st=con.createStatement();
			result=st.executeUpdate(query);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public int addPandingTeacherData(Teacher t)
	{
		int result=0;
		String query="insert into newteachers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, t.getTeacherId());
			pr.setString(2,t.getTeacherName());
			pr.setString(3,t.getState());
			pr.setString(4,t.getCity());
			pr.setString(5,t.getEmailId());
			pr.setString(6,t.getContactNumber());
			pr.setString(7,t.getQualification());
			pr.setString(8,t.getExperience());
			pr.setString(9,t.getBirthDate());
			pr.setString(10,t.getGender());
			pr.setBytes(11,t.getProfilePicInBytes());
			pr.setString(12, "Not Assigned");
			pr.setInt(13, 0);
			pr.setString(14, "Not Assigned");
			pr.setString(15, "Not Assigned");
			pr.setInt(16, 0);
			pr.setString(17, null);
			pr.setString(18, t.getBirthDate());
			pr.setBoolean(19, t.getActiveStatus());
			pr.setString(20, t.generateJoinedDate());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public int updateTeacherData(Teacher fold,Teacher t)
	{
		int result=0;
		String query="update teachers set teacherid=? , teachername=? ,state=? , city=? , emailid=? , contactnumber=? , qualification=? , experience=? , birthdate=? , gender=? , profilepic=?,lastlogin=?,activestatus=? where teacherid="+fold.getTeacherId();
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			pr.setInt(1, t.getTeacherId());
			pr.setString(2,t.getTeacherName());
			pr.setString(3,t.getState());
			pr.setString(4,t.getCity());
			pr.setString(5,t.getEmailId());
			pr.setString(6,t.getContactNumber());
			pr.setString(7,t.getQualification());
			pr.setString(8,t.getExperience());
			pr.setString(9,t.getBirthDate());
			pr.setString(10,t.getGender());
			pr.setBytes(11,t.getProfilePicInBytes());
			pr.setString(12, t.getLastLogin());
			pr.setBoolean(13, t.getActiveStatus());
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public Teacher getTeacherInfo(int row)
	{
		Teacher t=new Teacher();
		String query="select * from teachers where sr_no="+row;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			t.setTeacherId(rs.getInt(1));
			t.setTeacherName(rs.getString(2));
			t.setState(rs.getString(3));
			t.setCity(rs.getString(4));
			t.setEmailId(rs.getString(5));
			t.setContactNumber(rs.getString(6));
			t.setQualification(rs.getString(7));
			t.setExperience(rs.getString(8));
			t.setBirthDate(rs.getString(9));
			t.setGender(rs.getString(10));
			t.setProfilePic(rs.getBytes(11));
			t.setDepartmentCode(rs.getString(12));
			t.setSemorYear(rs.getInt(13));
			t.setSubject(rs.getString(14));
			t.setPosition(rs.getString(15));
			t.setLastLogin(rs.getString(17));
			t.setPassword(rs.getString(18));
			t.setActiveStatus(rs.getBoolean(19));
			t.setJoinedDate(rs.getString(20));
			st.close();
		
			return t;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return t;
	}
	
	public Teacher getPendingTeacherInfo(int tid)
	{
		Teacher t=new Teacher();
		String query="select * from newteachers where teacherid="+tid+";";
		
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			t.setTeacherId(rs.getInt(1));
			t.setTeacherName(rs.getString(2));
			t.setState(rs.getString(3));
			t.setCity(rs.getString(4));
			t.setEmailId(rs.getString(5));
			t.setContactNumber(rs.getString(6));
			t.setQualification(rs.getString(7));
			t.setExperience(rs.getString(8));
			t.setBirthDate(rs.getString(9));
			t.setGender(rs.getString(10));
			t.setProfilePic(rs.getBytes(11));
			t.setDepartmentCode(rs.getString(12));
			t.setSemorYear(rs.getInt(13));
			t.setSubject(rs.getString(14));
			t.setPosition(rs.getString(15));
			t.setLastLogin(rs.getString(17));
			t.setPassword(rs.getString(18));
			t.setActiveStatus(rs.getBoolean(19));
			t.setJoinedDate(rs.getString(20));
			st.close();
		
			return t;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return t;
	}
	public Teacher getTeacherInfobyId(int teacherid)
	{
		Teacher t=new Teacher();
		String query="select * from teachers where teacherid="+teacherid;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			t.setTeacherId(rs.getInt(1));
			t.setTeacherName(rs.getString(2));
			t.setState(rs.getString(3));
			t.setCity(rs.getString(4));
			t.setEmailId(rs.getString(5));
			t.setContactNumber(rs.getString(6));
			t.setQualification(rs.getString(7));
			t.setExperience(rs.getString(8));
			t.setBirthDate(rs.getString(9));
			t.setGender(rs.getString(10));
			t.setProfilePic(rs.getBytes(11));
			t.setDepartmentCode(rs.getString(12));
			t.setSemorYear(rs.getInt(13));
			t.setSubject(rs.getString(14));
			t.setPosition(rs.getString(15));
			t.setLastLogin(rs.getString(17));
			t.setPassword(rs.getString(18));
			t.setActiveStatus(rs.getBoolean(19));
			t.setJoinedDate(rs.getString(20));
			
			st.close();
			return t;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return t;
	}
	public ArrayList<Teacher> getTotalTeacher(String condition)
	{
		ArrayList<Teacher> list=new ArrayList<Teacher>();
		
		String query="select * from teachers"+condition+" order by teacherid asc";
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				Teacher t=new Teacher();
				t.setTeacherId(rs.getInt(1));
				t.setTeacherName(rs.getString(2));
				t.setState(rs.getString(3));
				t.setCity(rs.getString(4));
				t.setEmailId(rs.getString(5));
				t.setContactNumber(rs.getString(6));
				t.setQualification(rs.getString(7));
				t.setExperience(rs.getString(8));
				t.setBirthDate(rs.getString(9));
				t.setGender(rs.getString(10));
				t.setProfilePic(rs.getBytes(11));
				t.setDepartmentCode(rs.getString(12));
				t.setSemorYear(rs.getInt(13));
				t.setSubject(rs.getString(14));
				t.setPosition(rs.getString(15));
				t.setLastLogin(rs.getString(17));
				t.setPassword(rs.getString(18));
				t.setActiveStatus(rs.getBoolean(19));
				t.setJoinedDate(rs.getString(20));
				list.add(t);
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	
	public Teacher getTeacherInfobyUserId(String teacherid)
	{
		Teacher t=new Teacher();
		teacherid=teacherid.replaceAll("\\s", "");
		String query="select * from teachers where teacherid="+teacherid;
		try
		{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			t.setTeacherId(rs.getInt(1));
			t.setTeacherName(rs.getString(2));
			t.setState(rs.getString(3));
			t.setCity(rs.getString(4));
			t.setEmailId(rs.getString(5));
			t.setContactNumber(rs.getString(6));
			t.setQualification(rs.getString(7));
			t.setExperience(rs.getString(8));
			t.setBirthDate(rs.getString(9));
			t.setGender(rs.getString(10));
			t.setProfilePic(rs.getBytes(11));
			t.setDepartmentCode(rs.getString(12));
			t.setSemorYear(rs.getInt(13));
			t.setSubject(rs.getString(14));
			t.setPosition(rs.getString(15));
			t.setLastLogin(rs.getString(17));
			t.setPassword(rs.getString(18));
			t.setActiveStatus(rs.getBoolean(19));
			t.setJoinedDate(rs.getString(20));
			st.close();
			return t;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return t;
	}
	
	public int assignSubject(Teacher fold,Teacher t)
	{
		int result=0;
		try
		{
			
			String query="update teachers set departmentcode='"+t.getDepartmentCode()+"',semoryear="+t.getSemorYear()+",subject='"+t.getSubject()+"',position='"+t.getPosition()+"' where teacherid="+t.getTeacherId();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
		exp.printStackTrace();	
		}
		return result;
	}
	public int deleteNotificationHistory(Teacher t)
	{
		int result=0;
		String query="delete from notification where userid='"+t.getTeacherId()+"'";
		try
		{
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
		
	}
	public ResultSet getTeacherSubjectInfo(String condition)
	{
		ResultSet rs=null;
		try
		{
			String query="select teacherid as 'Teacher ID',teachername as 'Teacher Name',departmentcode as 'Class',semoryear as 'Sem/Year',subject as 'Subject',position as 'Position' from teachers "+condition+" order by teacherid asc";
			java.sql.Statement st=con.createStatement();
			rs=st.executeQuery(query);
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return rs;
	}
	public boolean checkPassword(String teacherid,String password)
	{
		boolean result=false;
		int tid=Integer.parseInt(teacherid);
		try
		{
			
//			if(teacherid.isEmpty()||teacherid.equalsIgnoreCase(" Enter teacher user-id"))
//			{
//				JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password","Error",JOptionPane.ERROR_MESSAGE);
//				result=false;
//			}
				String query="select count(*) from teachers where teacherid="+tid+" and password='"+password+"'";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				rs.next();
				if(rs.getInt(1)>0)
				{
					result=true;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password","Error",JOptionPane.ERROR_MESSAGE);
				}
			
			
		}
		catch(Exception exp)
		{
			
			exp.printStackTrace();
		}
		return result;
	}
	public boolean checkApproval(String tid,String pd)
	{
		boolean result=false;
		try
		{
			
			if(tid.isEmpty()||tid.equalsIgnoreCase(" Enter teacher user-id"))
			{
				JOptionPane.showMessageDialog(null, "Please Enter Both Id and Password","Error",JOptionPane.ERROR_MESSAGE);
				result=false;
			}
			else
			{
				String query="select count(*) from universitydata.newteachers where newteachers.teacherid='"+tid+"' and newteachers.password='"+pd+"';";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(query);
				rs.next();
				if(rs.getInt(1)>0)
				{
					result=true;
				}
				
			}
			
			
		}
		catch(Exception exp)
		{
			
			exp.printStackTrace();
		}
		return result;
	}
	
	public void setActiveStatus(boolean activestatus,int teacherid)
	{
		try
		{
			String query="update teachers set activestatus="+activestatus+" where teacherid="+teacherid;
			PreparedStatement pr=con.prepareStatement(query);
			pr.executeUpdate();
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
	public String getLastLogin(String userid)
	{
		try
		{
			String query="select lastlogin from teachers where teacherid="+userid+"";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return null;
	}
	public Image getProfilePic(String userid)
	{
		Image image=null;
		try
		{
			String query="select profilepic from teachers where teacherid="+userid;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			byte[] imagedata=rs.getBytes(1);
			image=Toolkit.getDefaultToolkit().createImage(imagedata);
			rs.close();
			st.close();
		}
		catch(Exception exp)
		{
		exp.printStackTrace();	
		}
		return image;
	}
	public int changePassword(String userid,String password)
	{
		try
		{
			String query="update teachers set password='"+password+"' where teacherid="+userid;
			PreparedStatement pr=con.prepareStatement(query);
			return pr.executeUpdate();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return 0;
	}
	
}

