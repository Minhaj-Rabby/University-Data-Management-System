package universitydata.teacher;

import universitydata.common.Person;
import universitydata.common.TimeUtil;
import universitydata.department.DepartmentData;

/*
 * Title : Teacher.java
 * Purpose : Binding all the of Teacher
 */
public class Teacher extends  Person
{
	
	private int teacherid;
	private String teachername;
	private String qualification;
	private String experience;
	private String subject;
	private String position;
	private String joineddate;
	
	public void setJoinedDate(String joineddate)
	{
		this.joineddate=joineddate;
	}
	public void setTeacherId(int teacherid)
	{
		this.teacherid=teacherid;
	}
	public void setTeacherName(String teachername)
	{
		this.teachername=teachername;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public void setExperience(String experience)
	{
		this.experience=experience;
	}
	public void setSubject(String subject)
	{
		this.subject=subject;
	}
	public void setPosition(String position)
	{
		this.position=position;
	}
	public String getDepartmentName()
	{
		return new DepartmentData().getDepartmentname(this.getDepartmentCode());
	}
	public String getTeacherName()
	{
		return teachername;
	}
	
	public int getTeacherId()
	{
		return teacherid;
	}
	
	public String getQualification()
	{
		return qualification;
	}
	public String getPosition()
	{
		return position;
	}
	public String getExperience()
	{
		return experience;
	}
	public String getSubject()
	{
		return subject;
	}
	public String getJoinedDate()
	{
		return joineddate;
	}
	public String generateJoinedDate()
	{
		joineddate=TimeUtil.getCurrentTime();
		return joineddate;
	}
	
}