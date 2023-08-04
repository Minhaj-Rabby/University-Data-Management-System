package universitydata.department;

public class Department {

	private String departmentcode;
	private int semoryear;
	private boolean isdeclared;
	private String departmentname;
	public void setDepartmentName(String departmentname)
	{
		this.departmentname=departmentname;
	}
	public void setDepartmentCode(String departmentcode)
	{
		this.departmentcode=departmentcode;
	}
	public void setSemorYear(int semoryear)
	{
		this.semoryear=semoryear;
	}
	public void setIsDeclared(boolean isdeclared)
	{
		this.isdeclared=isdeclared;
	}
	public String getDepartmentName()
	{
		return departmentname!=null?departmentname:new DepartmentData().getDepartmentname(departmentcode);
	}
	public String getDepartmentCode()
	{
		return departmentcode;
	}
	public int getSemorYear()
	{
		return semoryear;
	}
	public boolean getIsDeclared()
	{
		return isdeclared;
	}
}
