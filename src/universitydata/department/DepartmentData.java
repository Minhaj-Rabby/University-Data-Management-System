package universitydata.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import universitydata.common.DataBaseConnection;

public class DepartmentData
{
	
	static Connection con=DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException
	{
		con.close();
	}
	public int addDepartment(String Departmentcode,String Departmentname,String semoryear,int totalyearorsem)
	{
		int result=0;
		try 
		{
		String query="insert into departments values(?,?,?,?,?)";
		PreparedStatement pr=con.prepareStatement(query);
		pr.setInt(1,0);
		pr.setString(2, Departmentcode.toUpperCase());
		pr.setString(3, Departmentname);
		pr.setString(4, semoryear);
		pr.setInt(5, totalyearorsem);
		result=pr.executeUpdate();
			
			pr.close();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return result;
	}
	
	public  ResultSet getDepartmentinfo()
	{
	
		ResultSet st=null;
		try
		{
			String query="select c.sr_no as 'Index no.',c.departmentcode as 'Department Code' ,c.departmentname as 'Department Name',(select count(*) from subject where subject.departmentcode=c.departmentcode) as 'Subjects' ,(select count(*) from students where students.departmentcode=c.departmentcode) as 'Students',concat(c.totalsemoryear,' ',c.semoryear) as 'Total Sem/Year' from departments c;";
			PreparedStatement pr=con.prepareStatement(query);
			
			st=pr.executeQuery();
			return st;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return st;
	}
	public int getTotalDepartment()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select * from departments");
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
		
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	
	
	
	public String[] getDepartmentName()
	{
				String Departmentname[];
				int i=0;
				Departmentname=new String[getTotalDepartment()+1];
				Departmentname[i++]="---Select Department---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select departmentname from departments");
					
					
					while(st.next())
					{
						Departmentname[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return Departmentname;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return Departmentname;
		
	}
	public int getRollTotalDepartment()
	{
		int totalrow=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery("select departmentname from departments where departmentcode Not IN(select distinct departmentcode from rollgenerator)");             
			while(st.next())
			{
				totalrow++;
			}
			pr.close();
			st.close();
			return totalrow;
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalrow;
	}
	public String[] getRollDepartmentName()
	{
				String Departmentname[];
				int i=0;
				Departmentname=new String[getRollTotalDepartment()+1];
				Departmentname[i++]="---select---";
		
				try
				{
					Statement pr=con.createStatement();
					ResultSet st=pr.executeQuery("select departmentname from departments where departmentcode NOT IN(select distinct departmentcode from rollgenerator)");
					
					
					while(st.next())
					{
						Departmentname[i++]=st.getString(1);
					}
					pr.close();
					st.close();
					return Departmentname;
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
		return Departmentname;
		
	}
	public String[] getSemorYear(String Departmentname)
	{
		String query="select semoryear, totalsemoryear from departments where departmentname='"+Departmentname+"'";
		String totalsem[]=new String[1];
		totalsem[0]="---Select Sem/Year---";
		if(!Departmentname.contains("--select--"))
		{
			try
			{
				Statement pr=con.createStatement();
				ResultSet st=pr.executeQuery(query);
				st.next();
				String semoryear=st.getString(1);
				int totalsemoryear=st.getInt(2);	
				
				totalsem=new String[totalsemoryear+1];
				if(semoryear.contains("sem"))
				{
					semoryear="Semester";
				}
				else
				{
					semoryear="Year";
				}
				totalsem[0]="---Select "+semoryear+"---";
				for(int i=1; i<=totalsemoryear; i++)
				{
					totalsem[i]=semoryear+" "+i;
				}
				pr.close();
				st.close();
				return totalsem;
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
		}
		return totalsem;
		
	}
	public String[] getDepartmentcode()
	{
		String Departmentcode[]=new String[this.getTotalDepartment()];
		String query="select departmentcode from departments";
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			int i=0;
			while(st.next())
			{
				Departmentcode[i++]=st.getString(1);
			}
			pr.close();
			st.close();
			
			return Departmentcode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return Departmentcode;
		
		
	}
	public String getDepartmentcode(String Departmentname)
	{
		String query="select departmentcode from departments where departmentname='"+Departmentname+"'";
		String Departmentcode=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				Departmentcode=st.getString(1);
			
				pr.close();
				st.close();
			return Departmentcode;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return Departmentcode;
	}
	public String getsemoryear(String Departmentcode)
	{
		String query="select semoryear from departments where departmentcode='"+Departmentcode+"'";
		String semoryear=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				semoryear=st.getString(1);
			
				pr.close();
				st.close();
			return semoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return semoryear;
	}
	public String getDepartmentname(String Departmentcode)
	{
		String query="select departmentname from departments where departmentcode='"+Departmentcode+"'";
		String Departmentname=null;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
	
				st.next();
				Departmentname=st.getString(1);
			
				pr.close();
				st.close();
			return Departmentname;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return Departmentname;
	}
	public int getTotalsemoryear(String Departmentname)
	{
		String query="select totalsemoryear from departments where departmentname='"+Departmentname+"'";
		int totalsemoryear=0;
		try
		{
			Statement pr=con.createStatement();
			ResultSet st=pr.executeQuery(query);
			while(st.next())
			{
			totalsemoryear=st.getInt(1);
			}
			pr.close();
			st.close();
			
			return totalsemoryear;
			
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return totalsemoryear;
	}
	public boolean isDepartmentCodeExist(String Departmentcode)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from departments where departmentcode='"+Departmentcode+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isDepartmentNameExist(String Departmentname)
	{
		try
		{
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) from departments where departmentname='"+Departmentname+"'");
			rs.next();
			if(rs.getInt(1)>0)
			{
				return true;
			}
			rs.close();
			st.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public boolean isDeclared(String Departmentcode,int semoryear)
	{
		boolean isdeclared=false;
		try
		{
			String query="select isdeclared from result where departmentcode='"+Departmentcode+"' and semoryear="+semoryear;
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
			isdeclared=rs.getBoolean(1);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return isdeclared;
	}
	public ArrayList<Department> getDepartmentsForDeclareResult(String Departmentname)
	{
		ArrayList<Department> list=new ArrayList<Department>();
		try
		{
			String query="select departmentname,departmentcode,totalsemoryear from departments where departmentname='"+Departmentname+"'";
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				int totalsem=rs.getInt(3);
				for(int i=0; i<totalsem; i++)
				{
					Department Department=new Department();
					Department.setDepartmentName(rs.getString(1));
					Department.setDepartmentCode(rs.getString(2));
					Department.setSemorYear((i+1));
					Department.setIsDeclared(isDeclared(rs.getString(2),(i+1)));
					list.add(Department);
				}
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		return list;
	}
	public int updateResult(Department c)
	{
		int result=0;
		try
		{
			String query="update result set isdeclared="+c.getIsDeclared()+" where departmentcode='"+c.getDepartmentCode()+"' and semoryear="+c.getSemorYear();
			PreparedStatement pr=con.prepareStatement(query);
			result=pr.executeUpdate();
		}
		catch(Exception exp) 
		{
			exp.printStackTrace();
		}
		return result;
	}
	public void declareResult(Department c)
	{
		try
		{		
				if(updateResult(c)==0)
				{
				String query="insert into result values(?,?,?)";
				PreparedStatement pr=con.prepareStatement(query);
				pr.setString(1,c.getDepartmentCode());
				pr.setInt(2, c.getSemorYear());
				pr.setBoolean(3, c.getIsDeclared());
				pr.executeUpdate();
				}
				
				
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		
	}
	
}

