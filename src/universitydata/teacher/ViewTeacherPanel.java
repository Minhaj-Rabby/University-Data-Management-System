package universitydata.teacher;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import universitydata.admin.AdminMain;
import universitydata.common.ChangePasswordDialog;
import universitydata.common.Mail;
import universitydata.department.DepartmentData;
import universitydata.student.StudentMain;
import universitydata.subject.AssignSubjectDialog;

/*
 * Purpose : Displaying all the details of  teacher 
 */

@SuppressWarnings("serial")
public class ViewTeacherPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public JComponent lastpanel;
	private JButton assignsubjectbutton;
	private JButton Approvebutton;
	private JButton backbutton;
	private JButton editdetailsbutton;
	/**
	 * @wbp.parser.constructor
	 */
	private ViewTeacherPanel(Teacher f)
	{
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 188);
		add(panel);
		panel.setLayout(null);
		JLabel lblDisplayingStudentDetails = new JLabel(f.getTeacherName());
		
		lblDisplayingStudentDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDisplayingStudentDetails.setForeground(new Color(255, 255, 255));
		lblDisplayingStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblDisplayingStudentDetails.setBounds(661, 11, 415, 44);
		panel.add(lblDisplayingStudentDetails);
		
		editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setFocusable(false);
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.setBounds(919, 141, 153, 35);
		editdetailsbutton.setVisible(true);
		
		Approvebutton = new JButton("Approve User");
		Approvebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TeacherData().addTeacherData(f);
				new TeacherData().deletePendingTeacherData(f.getTeacherId());
				JOptionPane.showMessageDialog(null,"New Teacher User Created.","Teacher Profile Created",JOptionPane.INFORMATION_MESSAGE);
				try {
					new Mail(f.getEmailId(),f.getTeacherName());
					System.out.println(f.getEmailId());
				} catch (MessagingException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Approvebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		Approvebutton.setFocusable(false);
		Approvebutton.setForeground(new Color(0, 139, 139));
		Approvebutton.setBackground(new Color(255, 255, 255));
		Approvebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		Approvebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Approvebutton.setBounds(919,141, 153, 35);
		Approvebutton.setVisible(false);		
		panel.add(Approvebutton);
		
		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 141, 88, 36);
		panel.add(backbutton);
		
		JLabel lblLastLogin = new JLabel("Last Login : ");
		if(f.getLastLogin()==null||f.getLastLogin().isEmpty())
		{
			lblLastLogin.setText("Last Login : No Login");
		}
		else 
		{
			lblLastLogin.setText("Last Login : "+f.getLastLogin());
		}
		lblLastLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastLogin.setForeground(Color.WHITE);
		lblLastLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLastLogin.setBounds(719, 57, 357, 19);
		panel.add(lblLastLogin);
		
		JLabel lblStudentDetails = new JLabel("Teacher Details");
		lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentDetails.setForeground(Color.WHITE);
		lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentDetails.setBounds(10, 65, 415, 44);
		panel.add(lblStudentDetails);
		
		assignsubjectbutton = new JButton("Assign Subject");
		assignsubjectbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		assignsubjectbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		assignsubjectbutton.setFocusable(false);
		assignsubjectbutton.setForeground(new Color(0, 139, 139));
		assignsubjectbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		assignsubjectbutton.setBackground(Color.WHITE);
		assignsubjectbutton.setBounds(919, 141, 153, 35);
		panel.add(assignsubjectbutton);
		
		JLabel teacheridlbl = new JLabel("Teacher ID   ");
		teacheridlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		teacheridlbl.setBackground(new Color(255, 255, 255));
		teacheridlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		teacheridlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		teacheridlbl.setOpaque(true);
		teacheridlbl.setBounds(309, 66+150, 274, 48);
		add(teacheridlbl);
		
		JLabel teachernamelbl = new JLabel("Teacher Name   ");
		teachernamelbl.setOpaque(true);
		teachernamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		teachernamelbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		teachernamelbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		teachernamelbl.setBackground(Color.WHITE);
		teachernamelbl.setBounds(309, 113+150, 274, 48);
		add(teachernamelbl);
		
		JLabel lblAddress = new JLabel("Address   ");
		lblAddress.setOpaque(true);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setBounds(309, 160+150, 274, 48);
		add(lblAddress);
		
		JLabel lblEmailId = new JLabel("Email ID  ");
		lblEmailId.setOpaque(true);
		lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setBackground(Color.WHITE);
		lblEmailId.setBounds(309, 207+150, 274, 48);
		add(lblEmailId);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth ");
		lblDateOfBirth.setOpaque(true);
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblDateOfBirth.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblDateOfBirth.setBackground(Color.WHITE);
		lblDateOfBirth.setBounds(309, 254+150, 274, 48);
		add(lblDateOfBirth);
		
		JLabel lblContactNumber = new JLabel("Contact Number ");
		lblContactNumber.setOpaque(true);
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblContactNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblContactNumber.setBackground(Color.WHITE);
		lblContactNumber.setBounds(309, 300+150, 274, 48);
		add(lblContactNumber);
		
		JLabel qualificationlbl = new JLabel("Qualification   ");
		qualificationlbl.setOpaque(true);
		qualificationlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		qualificationlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		qualificationlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		qualificationlbl.setBackground(Color.WHITE);
		qualificationlbl.setBounds(20, 359+150, 291, 48);
		add(qualificationlbl);
		
		JLabel departmentlbl = new JLabel("Department    ");
		departmentlbl.setOpaque(true);
		departmentlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		departmentlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		departmentlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		departmentlbl.setBackground(Color.WHITE);
		departmentlbl.setBounds(20, 405+150, 291, 48);
		add(departmentlbl);
		
		JLabel semoryearlbl = new JLabel("Semester/Year    ");
		semoryearlbl.setOpaque(true);
		semoryearlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		semoryearlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		semoryearlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		semoryearlbl.setBackground(Color.WHITE);
		semoryearlbl.setBounds(582, 405+150, 239, 48);
		add(semoryearlbl);
		
		JLabel lblsubject = new JLabel("Subject    ");
		lblsubject.setOpaque(true);
		lblsubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblsubject.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblsubject.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblsubject.setBackground(Color.WHITE);
		lblsubject.setBounds(20, 452+150, 291, 48);
		add(lblsubject);
		
		JLabel positionlbl = new JLabel("Position    ");
		positionlbl.setOpaque(true);
		positionlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		positionlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		positionlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		positionlbl.setBackground(Color.WHITE);
		positionlbl.setBounds(582, 452+150, 239, 48);
		add(positionlbl);
		
		JLabel teacheridlabel = new JLabel("  "+f.getTeacherId());
		teacheridlabel.setOpaque(true);
		teacheridlabel.setHorizontalAlignment(SwingConstants.LEFT);
		teacheridlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		teacheridlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		teacheridlabel.setBackground(Color.WHITE);
		teacheridlabel.setBounds(582, 66+150, 523, 48);
		add(teacheridlabel);
		
		JLabel teachernamelabel = new JLabel("  "+f.getTeacherName());
		teachernamelabel.setOpaque(true);
		teachernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		teachernamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		teachernamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		teachernamelabel.setBackground(Color.WHITE);
		teachernamelabel.setBounds(582, 113+150, 523, 48);
		add(teachernamelabel);
		
		JLabel addresslabel = new JLabel("  "+f.getAddress());
		addresslabel.setOpaque(true);
		addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
		addresslabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		addresslabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		addresslabel.setBackground(Color.WHITE);
		addresslabel.setBounds(582, 160+150, 523, 48);
		add(addresslabel);
		
		JLabel emailidlabel = new JLabel("  "+f.getEmailId());
		emailidlabel.setOpaque(true);
		emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailidlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		emailidlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		emailidlabel.setBackground(Color.WHITE);
		emailidlabel.setBounds(582, 207+150, 523, 48);
		add(emailidlabel);
		
		JLabel dateofbirthlabel = new JLabel("  "+f.getBirthDate());
		dateofbirthlabel.setOpaque(true);
		dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateofbirthlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		dateofbirthlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		dateofbirthlabel.setBackground(Color.WHITE);
		dateofbirthlabel.setBounds(582, 254+150, 523, 48);
		add(dateofbirthlabel);
		
		JLabel contactnumberlabel = new JLabel("  "+f.getContactNumber());
		contactnumberlabel.setOpaque(true);
		contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		contactnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		contactnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		contactnumberlabel.setBackground(Color.WHITE);
		contactnumberlabel.setBounds(582, 300+150, 523, 48);
		add(contactnumberlabel);
		
		JLabel qualificationlabel = new JLabel("  "+f.getQualification());
		qualificationlabel.setOpaque(true);
		qualificationlabel.setHorizontalAlignment(SwingConstants.LEFT);
		qualificationlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		qualificationlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		qualificationlabel.setBackground(Color.WHITE);
		qualificationlabel.setBounds(309, 359+150, 274, 48);
		add(qualificationlabel);
		
		JLabel departmentnamelabel = new JLabel();
		if(f.getDepartmentCode()==null ||f.getDepartmentCode().equals("Not Assigned"))
		{
		departmentnamelabel.setText("  "+f.getDepartmentCode());	
		}
		else
		{
		departmentnamelabel.setText("  "+f.getDepartmentName());	
		}
		
		departmentnamelabel.setOpaque(true);
		departmentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		departmentnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		departmentnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		departmentnamelabel.setBackground(Color.WHITE);
		departmentnamelabel.setBounds(309, 405+150, 274, 48);
		add(departmentnamelabel);
		
		JLabel semoryearlabel = new JLabel();
		if(f.getDepartmentCode()==null || f.getDepartmentCode().equals("Not Assigned") )
		{
			semoryearlabel.setText("  Not Assigned");
		}
		else
		{
			semoryearlabel.setText("  "+new DepartmentData().getsemoryear(f.getDepartmentCode())+"-"+f.getSemorYear()+" "+" ("+f.getDepartmentCode()+")");
		}
		semoryearlabel.setOpaque(true);
		semoryearlabel.setHorizontalAlignment(SwingConstants.LEFT);
		semoryearlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		semoryearlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		semoryearlabel.setBackground(Color.WHITE);
		semoryearlabel.setBounds(820, 405+150, 285, 48);
		add(semoryearlabel);
		
		JLabel subjectlabel = new JLabel("  "+f.getSubject());
		subjectlabel.setOpaque(true);
		subjectlabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		subjectlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		subjectlabel.setBackground(Color.WHITE);
		subjectlabel.setBounds(309, 452+150, 274, 48);
		add(subjectlabel);
		
		JLabel postionlabel = new JLabel("  "+f.getPosition());
		postionlabel.setOpaque(true);
		postionlabel.setHorizontalAlignment(SwingConstants.LEFT);
		postionlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		postionlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		postionlabel.setBackground(Color.WHITE);
		postionlabel.setBounds(820, 452+150, 285, 48);
		add(postionlabel);
		
		JLabel profilepiclabel = new JLabel();
		profilepiclabel.setBounds(20, 66+150, 250, 270);
		add(profilepiclabel);
				profilepiclabel.setIcon(new ImageIcon(f.getProfilePic(250, 270)));
				profilepiclabel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
				profilepiclabel.setOpaque(true);
				profilepiclabel.setBackground(new Color(240, 248, 255));
				profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
				JLabel lblsemoryear = new JLabel("Experience   ");
				lblsemoryear.setOpaque(true);
				lblsemoryear.setHorizontalAlignment(SwingConstants.RIGHT);
				lblsemoryear.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
				lblsemoryear.setBorder(new LineBorder(new Color(192, 192, 192)));
				lblsemoryear.setBackground(Color.WHITE);
				lblsemoryear.setBounds(582, 359+150, 239, 48);
				add(lblsemoryear);
				
				JLabel experiencelabel = new JLabel("  "+f.getExperience());
				experiencelabel.setOpaque(true);
				experiencelabel.setHorizontalAlignment(SwingConstants.LEFT);
				experiencelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
				experiencelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
				experiencelabel.setBackground(Color.WHITE);
				experiencelabel.setBounds(820, 359+150, 285, 48);
				add(experiencelabel);
				
				
	}
	public ViewTeacherPanel(Teacher f, AdminMain am,JComponent lastpanel)
	{
		
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel=lastpanel;
		
		if(lastpanel.getName().equals("Pendding Teacher Panel")){
			editdetailsbutton.setVisible(false);
			assignsubjectbutton.setVisible(false);
			Approvebutton.setVisible(true);
			backbutton.setVisible(false);
			
		}
		JLabel lblJoinedDate = new JLabel("Joined Date    ");
		lblJoinedDate.setOpaque(true);
		lblJoinedDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblJoinedDate.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblJoinedDate.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblJoinedDate.setBackground(Color.WHITE);
		lblJoinedDate.setBounds(20, 649, 291, 48);
		add(lblJoinedDate);
		
		JLabel joineddatelabel = new JLabel("  "+f.getJoinedDate());
		joineddatelabel.setOpaque(true);
		joineddatelabel.setHorizontalAlignment(SwingConstants.LEFT);
		joineddatelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		joineddatelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		joineddatelabel.setBackground(Color.WHITE);
		joineddatelabel.setBounds(309, 649, 274, 48);
		add(joineddatelabel);
		
		JLabel lblPassword = new JLabel("Password    ");
		lblPassword.setOpaque(true);
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblPassword.setBackground(Color.WHITE);
		lblPassword.setBounds(582, 649, 239, 48);
		add(lblPassword);
		
		JLabel passwordlabel = new JLabel("  "+f.getPassword());
		passwordlabel.setOpaque(true);
		passwordlabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		passwordlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		passwordlabel.setBackground(Color.WHITE);
		passwordlabel.setBounds(820, 649, 285, 48);
		add(passwordlabel);
		
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.viewteacherpanel.setVisible(false);
				if(lastpanel.getName().equals("Teacher Panel"))
				{
					editdetailsbutton.setVisible(true);
					if(am.teacherpanel.viewbutton.getText().equals("Photo View"))
					{
						am.teacherpanel.createtablemodel();
					}
					else
					{
						am.teacherpanel.createphotoviewpanel();
					}
					am.teacherpanel.setVisible(true);
				}
				
				else if(lastpanel.getName().equals("Search Panel"))
				{
					am.searchpanel.createtablemodel();
					am.searchpanel.setVisible(true);
				}
				else if(lastpanel.getName().equals("Users Panel"))
				{
					am.userspanel.createtablemodel();
					am.userspanel.setVisible(true);
				}
				else 
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
		
	
		editdetailsbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				AddTeacherDialog ad=new AddTeacherDialog(am,f);
				ad.setLocationRelativeTo(null);
				ad.setVisible(true);
				
			}
	
		}
		
		);
		
		assignsubjectbutton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				AssignSubjectDialog as=new AssignSubjectDialog(f,am);
				as.setLocation(450, 100);
				as.setVisible(true);
				
			}
		});
		
	}
	public ViewTeacherPanel(Teacher f, TeacherMain fm, JComponent lastpanel) 
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel=lastpanel;
		assignsubjectbutton.setVisible(false);
		editdetailsbutton.setVisible(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fm.viewteacherpanel.setVisible(false);
				if(lastpanel.getName().equals("Teacher Panel"))
				{
					if(fm.teacherpanel.viewbutton.getText().equals("Photo View"))
					{
						fm.teacherpanel.createtablemodel();
					}
					else
					{
						fm.teacherpanel.createphotoviewpanel();
					}
					fm.teacherpanel.setVisible(true);
				}
				
				else if(lastpanel.getName().equals("Search Panel"))
				{
					fm.searchpanel.createtablemodel();
					fm.searchpanel.setVisible(true);
				}
				else 
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
	}
	public ViewTeacherPanel(Teacher f, TeacherMain fm) 
	{
		// TODO Auto-generated constructor stub
		this(f);
		assignsubjectbutton.setVisible(false);
		editdetailsbutton.setText("Change Password");
		backbutton.setVisible(false);
		editdetailsbutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
					ChangePasswordDialog cp=new ChangePasswordDialog(f);
					cp.setLocationRelativeTo(null);
					cp.setVisible(true);
				
			}
	
		}
		);

	}
	
	
	public ViewTeacherPanel(Teacher f, StudentMain sm, JComponent lastpanel)
	{
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel=lastpanel;
		assignsubjectbutton.setVisible(false);
		editdetailsbutton.setVisible(false);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sm.viewteacherpanel.setVisible(false);
				if(lastpanel.getName().equals("Teacher Panel"))
				{
					if(sm.teacherpanel.viewbutton.getText().equals("Photo View"))
					{
						sm.teacherpanel.createtablemodel();
					}
					else
					{
						sm.teacherpanel.createphotoviewpanel();
					}
					sm.teacherpanel.setVisible(true);
				}
				
				else if(lastpanel.getName().equals("Search Panel"))
				{
					sm.searchpanel.createtablemodel();
					sm.searchpanel.setVisible(true);
				}
				else 
				{
					lastpanel.setVisible(true);
				}
				
			}
		});
	}
}
