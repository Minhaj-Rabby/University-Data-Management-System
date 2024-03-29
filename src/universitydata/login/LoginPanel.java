package universitydata.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import universitydata.admin.AdminData;
import universitydata.admin.AdminMain;
import universitydata.common.HintPasswordField;
import universitydata.common.HintTextField;
import universitydata.common.UserData;
import universitydata.student.AddNewStudentDialog;
import universitydata.student.Student;
import universitydata.student.StudentData;
import universitydata.student.StudentMain;
import universitydata.teacher.AddNewTeacherDialog;
import universitydata.teacher.Teacher;
import universitydata.teacher.TeacherData;
import universitydata.teacher.TeacherMain;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener
{

	public HintTextField useridfield;
	public JPasswordField passwordfield;
	public JButton loginbutton;
	String loginprofile;
	private LoginPageFrame loginpageframe;
	private final JButton btnCreateNewUser = new JButton("Register");

	/**
	 * Create the panel.
	 */
	public LoginPanel(String loginprofile,ImageIcon imageicon,LoginPageFrame lpf) {
		
		this.loginprofile=loginprofile;
		this.loginpageframe=lpf;
		setBorder(new LineBorder(new Color(192, 192, 192)));
		setBackground(new Color(0, 0, 0,80));
		setBounds(490, 206, 420, 416);
		setLayout(null);
		
		JLabel lblPassword = new JLabel("");
		lblPassword.setOpaque(true);
		lblPassword.setBackground(new Color(32, 178, 170));
		lblPassword.setIcon(new ImageIcon(".\\assets\\password1.png"));
		lblPassword.setBounds(20, 257, 60, 44);
		add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPassword.setBorder(new LineBorder(new Color(192, 192, 192)));
		
		useridfield = new HintTextField("Userid");
		useridfield.setBorder(new EmptyBorder(0,3,0,0));
		useridfield.setToolTipText("User Id");
		useridfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		useridfield.setBounds(80, 188, 323, 44);
		useridfield.setForeground(Color.DARK_GRAY);
		add(useridfield);
		useridfield.setColumns(10);
		
		JLabel lblEmailId = new JLabel("");
		lblEmailId.setOpaque(true);
		lblEmailId.setFocusable(true);
		lblEmailId.setBackground(new Color(32, 178, 170));
		lblEmailId.setIcon(new ImageIcon(".\\assets\\userid.png"));
		lblEmailId.setBounds(20, 188, 60, 44);
		add(lblEmailId);
		lblEmailId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		loginbutton = new JButton("Login");
		
	
		loginbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		loginbutton.setForeground(new Color(255, 255, 255));
		loginbutton.addActionListener(this);
		loginbutton.setBackground(new Color(32, 178, 170));
		loginbutton.setBounds(20, 328, 175, 44);
		loginbutton.setFocusable(false);
		loginbutton.setBorderPainted(false);
		add(loginbutton);
		
		JLabel lblStudentLogin = new JLabel(loginprofile+" Login");
		lblStudentLogin.setForeground(new Color(255, 255, 255));
		lblStudentLogin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblStudentLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentLogin.setBounds(10, 121, 420, 38);
		add(lblStudentLogin);
		
		JLabel userprofilelabel = new JLabel();
		userprofilelabel.setIcon(imageicon);
		userprofilelabel.setBounds(169, 28, 100, 98);
		add(userprofilelabel);
		
		passwordfield = new HintPasswordField("Password");
		passwordfield.setBorder(useridfield.getBorder());
		passwordfield.setToolTipText("Password");
		passwordfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		passwordfield.setBounds(80, 257, 261, 44);
		add(passwordfield);
		
		JButton showandhidebutton = new JButton("show");
		   showandhidebutton.setForeground(new Color(255, 255, 255));
		showandhidebutton.setBounds(341, 257, 62, 44);
		  showandhidebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		    showandhidebutton.setFocusable(false);
		    showandhidebutton.setFocusPainted(false);
		    showandhidebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		    showandhidebutton.setBackground(new Color(32, 178, 170));
		    showandhidebutton.setBorderPainted(false);
		    showandhidebutton.addActionListener(e->
		    {
		    	if(showandhidebutton.getText().equals("show"))
		    	{
		    		passwordfield.setEchoChar('\u0000');
		    		showandhidebutton.setText("hide");
		    	}
		    	else
		    	{
		    		passwordfield.setEchoChar((Character)UIManager.get("PasswordField.echoChar"));
		    		showandhidebutton.setText("show");
		    	}
		    });
		add(showandhidebutton);
		
		//Create New user
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				 if(loginprofile.equals("Teacher"))
				{
					AddNewTeacherDialog afd=new AddNewTeacherDialog();
					afd.setLocationRelativeTo(null);
					afd.setVisible(true);
				}
				else if(loginprofile.equals("Student"))
				{
					AddNewStudentDialog sd=new AddNewStudentDialog();
					sd.setLocationRelativeTo(null);
					sd.setVisible(true);
					
				}
			}
		});
		
		
		
		btnCreateNewUser.setBounds(216, 328, 187, 44);
		add(btnCreateNewUser);
		btnCreateNewUser.setForeground(Color.WHITE);
		btnCreateNewUser.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnCreateNewUser.setFocusable(false);
		btnCreateNewUser.setBorderPainted(false);
		btnCreateNewUser.setBackground(new Color(32, 178, 170));
		if(loginprofile.equals("Admin"))
		{
			btnCreateNewUser.setVisible(false);
			loginbutton.setBounds(20, 328, 383, 44);
			
		}

	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
		if(loginprofile.equals("Admin"))
		{
			//btnCreateNewUser.setVisible(true);
			boolean result=new AdminData().checkPassword(useridfield.getText(), passwordfield.getText());
			if(result==true)
			{
				
				AdminMain am=new AdminMain();
				am.setVisible(true);
				am.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				loginpageframe.dispose();	
				
			}
			
		}
		else if(loginprofile.equals("Teacher"))
		{	
			boolean res=new TeacherData().checkApproval(useridfield.getText(), passwordfield.getText());
			
			if(res==true)
			{
				JOptionPane.showMessageDialog(null, "Wait for admin Approval","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			
			else
			{
				boolean result =new TeacherData().checkPassword(useridfield.getText(), passwordfield.getText());
				System.out.println(result);
				if(result==true) 
				{
				Teacher f=new TeacherData().getTeacherInfobyUserId(useridfield.getText());
				TeacherMain fm=new TeacherMain(f);
				fm.setVisible(true);
				fm.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				loginpageframe.dispose();
				if(!f.getDepartmentCode().equals("Not Assigned"))
				{
				
					new UserData().addTeacherLoginTime(f);
				}
				}
				
					
				else 
				{
					JOptionPane.showMessageDialog(null," No user Found","Login Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(loginprofile.equals("Student"))
		{
			boolean res=new StudentData().checkApproval(useridfield.getText(), passwordfield.getText());
			
			if(res==true)
			{
				JOptionPane.showMessageDialog(null, "Wait for admin Approval","Error",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				boolean result=new StudentData().checkPassword(useridfield.getText(), passwordfield.getText());	
				if(res!=true && result!=true)
				{
					JOptionPane.showMessageDialog(null," No user Found","Login Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				Student s=new StudentData().getStudentDetailsByUserId(useridfield.getText());
				new UserData().addStudentLoginTime(s);
				StudentMain sm=new StudentMain(s);
				sm.setVisible(true);
				sm.setLocationRelativeTo(null);
				loginpageframe.timer.stop();
				loginpageframe.imagetimer.stop();
				loginpageframe.dispose();
				}
			}
				
					
			

				
		}
	}
}
