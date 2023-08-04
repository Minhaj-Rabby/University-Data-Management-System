package universitydata.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import universitydata.department.DepartmentData;
import universitydata.student.Student;
import universitydata.student.StudentData;
import universitydata.student.StudentPanel;
import universitydata.student.ViewStudentPanel;
import universitydata.teacher.Teacher;
import universitydata.teacher.TeacherData;
import universitydata.teacher.TeacherPanel;
import universitydata.teacher.ViewTeacherPanel;


/*
 * Title : PhotoViewPanel.java
 * Purpose : To displaying students and teachers image
 */
@SuppressWarnings("serial")
public class PhotoViewPanel extends JPanel {
	int xpos[];
	JPanel panel[][];
	JLabel profilepiclabel[][];
	JLabel namelabel[][];
	JLabel degreelabel[][];
	int totalteachers=-1;
	int totalstudents=-1;
	int maxphotosinrow=3;
	int incrementx=0;
	int incrementy=0;
	TeacherPanel fp;
	StudentPanel sp;
	/**
	 * Create the panel.
	 * 
	 */
	 
	@Override
	public Dimension getPreferredSize()
	{
		int n=0;
		if(totalteachers!=-1)
		{
		  n=totalteachers;
		}
		if(totalstudents!=-1)
		{
			n=totalstudents;
		}
		 int row=n%maxphotosinrow==0?n/maxphotosinrow:(n/maxphotosinrow)+1;
		 if(row==1)
		 {
			 return new Dimension(xpos[maxphotosinrow-1]+xpos[1]-xpos[0],incrementy+20);
		 }
		 
	    return new Dimension( 1116,row*(incrementy));
	}
	public PhotoViewPanel(TeacherPanel teacherPanel,int maxphoto) {
		
		this.maxphotosinrow=maxphoto;
		this.setFocusable(true);
		this.fp=teacherPanel;
		xpos=new int[maxphotosinrow];
		incrementx=(4*270)/maxphotosinrow;
		int start=20;
		
		for(int i=0; i<maxphotosinrow; i++)
		{
			xpos[i]=start;
			start+=(incrementx);
		}
		 incrementy=incrementx+50;
		totalteachers=fp.table.getRowCount();
		setBackground(Color.WHITE);
		
		
		  this.setBounds(0, 189, 1116, 1000);
		  setLayout(null);
		  
		  int n=totalteachers;
		  int row=n%maxphotosinrow==0?n/maxphotosinrow:(n/maxphotosinrow)+1;
		  int lastcolumn=n%maxphotosinrow==0?maxphotosinrow:n%maxphotosinrow;
	
		  panel=new JPanel[row][maxphotosinrow];
		  profilepiclabel=new JLabel[row][maxphotosinrow];
		  namelabel=new JLabel[row][maxphotosinrow];
		  degreelabel=new JLabel[row][maxphotosinrow];
		  int column=10;
		  int index=1;
		  for(int i=0; i<row; i++)
		  {
			  int totalcolumn=maxphotosinrow;
			  if(i==(row-1))
			  {
				  totalcolumn=lastcolumn;
			  }
			  for(int j=0; j<totalcolumn; j++)
			  {
					int fid=Integer.parseInt(fp.table.getValueAt(index-1, 0)+"");
					Teacher f=new TeacherData().getTeacherInfobyId(fid);
				  panel[i][j]=new JPanel();
				  panel[i][j].setBackground(Color.WHITE);
				  panel[i][j].setBounds(xpos[j], column, incrementx-20, incrementy-10);
				  panel[i][j].setVisible(true);
				  panel[i][j].setToolTipText(f.getTeacherName());
				  add(panel[i][j]);
				  panel[i][j].setLayout(null);
				  panel[i][j].setName(f.getTeacherId()+"");
				  panel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				  panel[i][j].addMouseListener(new MouseAdapter()
				  {
					  @Override
			  			public void mousePressed(MouseEvent e)
			  			{
						  	if(e.getButton()==MouseEvent.BUTTON1)
						  	{
			  					
			  					Teacher f=new TeacherData().getTeacherInfobyId(Integer.parseInt(e.getComponent().getName()));
								if(fp.am!=null)
								{
									fp.am.viewteacherpanel = new ViewTeacherPanel(f, fp.am, fp);
									fp.am.viewteacherpanel.setVisible(true);
									fp.am.teacherpanel.setVisible(false);
									fp.am.viewteacherpanel.setLocation(fp.am.panelx, fp.am.panely);
									fp.am.viewteacherpanel.setVisible(true);
									fp.am.viewteacherpanel.setFocusable(true);
									fp.am.contentPane.add(fp.am.viewteacherpanel);
								}
								else if(fp.fm!=null)
								{
									fp.fm.viewteacherpanel = new ViewTeacherPanel(f, fp.fm, fp);
									fp.fm.viewteacherpanel.setVisible(true);
									fp.fm.teacherpanel.setVisible(false);
									fp.fm.viewteacherpanel.setLocation(fp.fm.panelx, fp.fm.panely);
									fp.fm.viewteacherpanel.setVisible(true);
									fp.fm.viewteacherpanel.setFocusable(true);
									fp.fm.contentPane.add(fp.fm.viewteacherpanel);
								}
								else if(fp.sm!=null)
								{
									fp.sm.viewteacherpanel = new ViewTeacherPanel(f, fp.sm, fp);
									fp.sm.viewteacherpanel.setVisible(true);
									fp.sm.teacherpanel.setVisible(false);
									fp.sm.viewteacherpanel.setLocation(fp.sm.panelx, fp.sm.panely);
									fp.sm.viewteacherpanel.setVisible(true);
									fp.sm.viewteacherpanel.setFocusable(true);
									fp.sm.contentPane.add(fp.sm.viewteacherpanel);
								}
			  				}
			  			}
				  }
				  );
				
				  
				  profilepiclabel[i][j]=new JLabel();
				  profilepiclabel[i][j].setBounds(0, 0, panel[i][j].getWidth()-10,panel[i][j].getHeight()-60);
				  profilepiclabel[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
				  profilepiclabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  profilepiclabel[i][j].setText("image");
				  profilepiclabel[i][j].setIcon(new ImageIcon(f.getProfilePic(profilepiclabel[i][j].getWidth()+((maxphotosinrow*10)/4+1),profilepiclabel[i][j].getHeight())));
				  
				
				  panel[i][j].add(profilepiclabel[i][j]);

				  namelabel[i][j]=new JLabel();
				  namelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  namelabel[i][j].setText(f.getTeacherName());
				  namelabel[i][j].setFont(new Font("Tahoma", Font.BOLD, changeNameFont()));
				  namelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+5,panel[i][j].getWidth(), 22);
				  panel[i][j].add(namelabel[i][j]);
				  degreelabel[i][j]=new JLabel();
				  degreelabel[i][j].setVerticalAlignment(SwingConstants.TOP);
				  if(f.getPosition().equals("Not Assigned"))
				  {
				  degreelabel[i][j].setText("");
				  }
				  else
				  {
					  degreelabel[i][j].setText(f.getPosition());
				  }
				  degreelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  degreelabel[i][j].setFont(new Font("Tahoma", Font.PLAIN, changeDegreeFont()));
				  degreelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+25,panel[i][j].getWidth(), 22);
				  panel[i][j].add(degreelabel[i][j]);
				  index++;
				  if(index>totalteachers)
				  {
					  break;
				  }
			  }
			  column+=incrementy;
		  }

	}
	public PhotoViewPanel(StudentPanel sp,int maxphoto)
	{
		this.maxphotosinrow=maxphoto;
		this.sp=sp;
		xpos=new int[maxphotosinrow];
		 incrementx=(4*270)/maxphotosinrow;
		int start=20;
		
		for(int i=0; i<maxphotosinrow; i++)
		{
			xpos[i]=start;
			start+=(incrementx);
		}
		incrementy=incrementx+50;
		totalstudents=sp.table.getRowCount();
		setBackground(Color.WHITE);
		
		
		  this.setBounds(0, 189, 1116, 1000);
		  setLayout(null);
		  
		  int n=totalstudents;
		  int row=n%maxphotosinrow==0?n/maxphotosinrow:(n/maxphotosinrow)+1;
		  int lastcolumn=n%maxphotosinrow==0?maxphotosinrow:n%maxphotosinrow;
	
		  panel=new JPanel[row][maxphotosinrow];
		  profilepiclabel=new JLabel[row][maxphotosinrow];
		  namelabel=new JLabel[row][maxphotosinrow];
		  degreelabel=new JLabel[row][maxphotosinrow];
		  int column=10;
		  int index=1;
		  for(int i=0; i<row; i++)
		  {
			  int totalcolumn=maxphotosinrow;
			  if(i==(row-1))
			  {
				  totalcolumn=lastcolumn;
			  }
			  for(int j=0; j<totalcolumn; j++)
			  {
				  
					String departmentcode=sp.table.getValueAt(index-1,0)+"";
					String  strsem=sp.table.getValueAt(index-1, 4)+"";
					int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
					String strroll=sp.table.getValueAt(index-1, 1)+"";
					long rollnumber=Long.parseLong(strroll);
					Student s=new StudentData().getStudentDetails(departmentcode,sem,rollnumber);
				  panel[i][j]=new JPanel();
				  panel[i][j].setBackground(Color.WHITE);
				  panel[i][j].setBounds(xpos[j], column, incrementx-20, incrementy-10);
				  panel[i][j].setVisible(true);
				  panel[i][j].setToolTipText(s.getFullName());
				  add(panel[i][j]);
				  panel[i][j].setName(s.getSrNo()+"");
				  panel[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
				  panel[i][j].addMouseListener(new MouseAdapter()
						  {
					  			@Override
					  			public void mousePressed(MouseEvent e)
					  			{
					  				if (e.getButton() == MouseEvent.BUTTON1)
					  				{
					  					Student s=new StudentData().getStudentDetails(Integer.parseInt(e.getComponent().getName()));
					  					
					  					if(sp.am!=null)
					  					{
						  					sp.am.viewstudentpanel=new ViewStudentPanel(s,sp.am,sp);
						  					sp.am.viewstudentpanel.setVisible(true);
						  					sp.am.studentpanel.setVisible(false);
						  					sp.am.viewstudentpanel.setLocation(sp.am.panelx,sp.am.panely);
						  					sp.am.viewstudentpanel.setVisible(true);
						  					sp.am.viewstudentpanel.setFocusable(true);
						  					sp.am.contentPane.add(sp.am.viewstudentpanel);
					  					}
					  					else  if(sp.fm!=null)
					  					{
					  						sp.fm.viewstudentpanel=new ViewStudentPanel(s,sp.fm,sp);
						  					sp.fm.viewstudentpanel.setVisible(true);
						  					sp.fm.studentpanel.setVisible(false);
						  					sp.fm.viewstudentpanel.setLocation(sp.fm.panelx,sp.fm.panely);
						  					sp.fm.viewstudentpanel.setVisible(true);
						  					sp.fm.viewstudentpanel.setFocusable(true);
						  					sp.fm.contentPane.add(sp.fm.viewstudentpanel);
					  					}
					  					else if(sp.sm!=null)
					  					{
					  						sp.sm.viewstudentpanel=new ViewStudentPanel(s,sp.sm,sp);
						  					sp.sm.viewstudentpanel.setVisible(true);
						  					sp.sm.studentpanel.setVisible(false);
						  					sp.sm.viewstudentpanel.setLocation(sp.sm.panelx,sp.sm.panely);
						  					sp.sm.viewstudentpanel.setVisible(true);
						  					sp.sm.viewstudentpanel.setFocusable(true);
						  					sp.sm.contentPane.add(sp.sm.viewstudentpanel);
					  					}
					  					
					  				}
					  			}
						  }
						  );
				  panel[i][j].setLayout(null);
				
				  
				  profilepiclabel[i][j]=new JLabel();
				  profilepiclabel[i][j].setBounds(0, 0, panel[i][j].getWidth(),panel[i][j].getHeight()-60);
				  profilepiclabel[i][j].setBorder(new LineBorder(Color.LIGHT_GRAY));
				  profilepiclabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  profilepiclabel[i][j].setText("image");
				  profilepiclabel[i][j].setIcon(new ImageIcon(s.getProfilePic(profilepiclabel[i][j].getWidth()+((maxphotosinrow*10)/4+1),profilepiclabel[i][j].getHeight())));
				  
				
				  panel[i][j].add(profilepiclabel[i][j]);

				  namelabel[i][j]=new JLabel();
				  namelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  namelabel[i][j].setText(s.getFullName());
				
				  namelabel[i][j].setFont(new Font("Tahoma", Font.BOLD, changeNameFont()));
				  namelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+3,panel[i][j].getWidth(), 22);
				  panel[i][j].add(namelabel[i][j]);
				  degreelabel[i][j]=new JLabel();
				  degreelabel[i][j].setVerticalAlignment(SwingConstants.TOP);
				  degreelabel[i][j].setText(new DepartmentData().getsemoryear(s.getDepartmentCode())+"-"+s.getSemorYear()+" "+" ("+s.getDepartmentCode()+")");
				  degreelabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				  degreelabel[i][j].setFont(new Font("Tahoma", Font.PLAIN, changeDegreeFont()));
				  degreelabel[i][j].setBounds(0, profilepiclabel[i][j].getHeight()+25,panel[i][j].getWidth(), 22);
				  panel[i][j].add(degreelabel[i][j]);
				  index++;
				  if(index>totalstudents)
				  {
					  break;
				  }
			  }
			  column+=incrementy;
		  }
		  

	}
	public int changeNameFont()
	  {
		return maxphotosinrow<4?22:maxphotosinrow<8?17:maxphotosinrow<12?13:10;
	  }
	public int changeDegreeFont()
	  {
		return maxphotosinrow<4?18:maxphotosinrow<8?14:maxphotosinrow<12?13:10;
	  }
	
	


}
