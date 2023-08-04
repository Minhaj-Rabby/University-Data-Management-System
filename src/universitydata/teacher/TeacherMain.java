package universitydata.teacher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import universitydata.admin.AdminProfilePanel;
import universitydata.common.DataBaseConnection;
import universitydata.common.HomePanel;
import universitydata.common.SearchPanel;
import universitydata.common.TimeUtil;
import universitydata.login.LoginPageFrame;
import universitydata.student.AttandanceReportPanel;
import universitydata.student.EnterMarksPanel;
import universitydata.student.MarkAttandancePanel;
import universitydata.student.MarkSheetPanel;
import universitydata.student.MarkSheetReportPanel;
import universitydata.student.StudentPanel;
import universitydata.student.ViewStudentPanel;
import universitydata.subject.AssignSubjectPanel;
import universitydata.subject.SubjectPanel;
/*
 * Title : TeacherMain.java
 * Purpose : Main teacher frame
 */
@SuppressWarnings("serial")
public class TeacherMain extends JFrame  implements ActionListener
{

	public JPanel contentPane;
	private JLabel teachernamelabel;
	private JLabel teacherprofilepiclabel;
	private JPanel profilepanel;
	private JButton homebutton;
	private JButton studentsbutton;
	private JButton subjectbutton;
	private JButton faculitiesbutton;
	private JButton entermarksbutton;
	private JButton assignedsubjectbutton;
	private JButton markattandancebutton;
	private JButton marksheetreportbutton;
	private JButton attandancereportbutton;
	private JButton searchbutton;
	private JButton logoutbutton;
	private JButton exitbutton;
	private Color buttonbcolor=Color.DARK_GRAY;
	private Color buttonfcolor=Color.LIGHT_GRAY;
	private Font buttonfont=new Font("Tw Cen MT", Font.PLAIN, 20);
	public SubjectPanel subjectpanel;
	public HomePanel homepanel;
	public StudentPanel studentpanel;
	public ViewStudentPanel viewstudentpanel;
	public MarkSheetPanel marksheetpanel;
	public JScrollPane marksheetpanelscroll;
	public ViewTeacherPanel viewteacherpanel;
	public AssignSubjectPanel assignsubjectpanel;
	public EnterMarksPanel entermarkspanel;
	public JScrollPane entermarkspanelscroll;
	private MarkAttandancePanel markattandancepanel;
	private JScrollPane markattandancepanelscroll;
	public AttandanceReportPanel attandancereportpanel;
	public JScrollPane attandancereportpanelscroll;
	public TeacherPanel teacherpanel;
	public AdminProfilePanel adminprofilepanel;
	public SearchPanel searchpanel;
	public int panely=0,panelx=250;
	private JButton btn;
	private JButton myprofilebutton;
	private String lastlogin;
	public Teacher f;
	private int row=63;
	public Socket socket;
	private Timer timer;
	public MarkSheetReportPanel marksheetreportpanel;
	public JScrollPane marksheetreportpanelscroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					if(DataBaseConnection.checkconnection())
					{
						
						Teacher f=new TeacherData().getTeacherInfo(1);
						TeacherMain frame = new TeacherMain(f);
					    frame.setVisible(true);
					    
					}
					else
					{
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						JOptionPane.showMessageDialog(null, "You Are Not Connected To DataBase","Error",JOptionPane.ERROR_MESSAGE);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeacherMain(Teacher f) {
		this.f=f;
		ActionListener setActive=new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new TeacherData().setActiveStatus(true, f.getTeacherId());
			}
			
		};
		timer=new Timer(1000,setActive);
		timer.start();
		Color bgColor =new Color(32,178,170);
		Color frColor=Color.white;
		UIManager.put("ComboBoxUI", "com.sun.java.swing.plaf.windows.WindowsComboBoxUI");
	    UIManager.put("ComboBox.selectionBackground", new ColorUIResource(bgColor));
	    UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
	    UIManager.put("ComboBox.foreground", new ColorUIResource(Color.DARK_GRAY));
	    UIManager.put("ComboBox.selectionForeground", new ColorUIResource(frColor));
	    UIManager.put("ScrollBarUI", "com.sun.java.swing.plaf.windows.WindowsScrollBarUI");
	  
		this.setResizable(false);
		setTitle("University Data Management");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setBounds(-2,0,1370,733);
		createpanel();
		JPanel sidebarpanel = new JPanel();
		sidebarpanel.setBorder(new MatteBorder(0, 0, 0, 2, (Color) new Color(64, 64, 64)));
		sidebarpanel.setBackground(Color.DARK_GRAY);
		sidebarpanel.setBounds(5, 11, 240, 706);
		contentPane.add(sidebarpanel);
		sidebarpanel.setLayout(null);
		
		 profilepanel = new JPanel();
		 profilepanel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		profilepanel.setBackground(Color.DARK_GRAY);
		profilepanel.setBounds(0, 0, 240, 61);
		sidebarpanel.add(profilepanel);
		profilepanel.setLayout(null);
		
		teachernamelabel = new JLabel();
		teachernamelabel.setForeground(Color.WHITE);
		teachernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		teachernamelabel.setFont(new Font("Tw Cen MT", Font.BOLD, 25));
		teachernamelabel.setBackground(Color.DARK_GRAY);
		teachernamelabel.setOpaque(true);
		teachernamelabel.setBounds(65, 5, 171, 36);
		profilepanel.add(teachernamelabel);
		
		teacherprofilepiclabel = new JLabel();
		teacherprofilepiclabel.setBounds(5,0, 50, 50);
		profilepanel.add(teacherprofilepiclabel);
		teacherprofilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
		teacherprofilepiclabel.setBackground(Color.DARK_GRAY);
		
		teacherprofilepiclabel.setBorder(new LineBorder(Color.black,0));
		teacherprofilepiclabel.setOpaque(true);
		
	
		
		
		homebutton = createButton("Home");
		sidebarpanel.add(homebutton);
		btn=homebutton;
		
		studentsbutton = createButton("Students");
		sidebarpanel.add(studentsbutton);
		
		subjectbutton = createButton("Subjects");
		sidebarpanel.add(subjectbutton);
		
		
		assignedsubjectbutton =createButton("Assigned Subject","Assign Subject");
		sidebarpanel.add(assignedsubjectbutton);
		
		entermarksbutton = createButton("Enter Marks");
		sidebarpanel.add(entermarksbutton);
		
		marksheetreportbutton=createButton("Marksheet Report");
		sidebarpanel.add(marksheetreportbutton);
		
		markattandancebutton = createButton("Mark Attandance");
		sidebarpanel.add(markattandancebutton);
		
		attandancereportbutton = createButton("Attandance Report");
		sidebarpanel.add(attandancereportbutton);
		
		
		myprofilebutton = createButton("My Profile","Profile");
		sidebarpanel.add(myprofilebutton);
		
		
		logoutbutton = createButton("logout");
		sidebarpanel.add(logoutbutton);

		exitbutton =createButton("Exit");
		sidebarpanel.add(exitbutton);
		
		activeButton(homebutton);
		homepanel.setVisible(true);
		
		
		
		this.setTeacherDetails();
		lastlogin=f.getLastLogin();
		homepanel.setLastLogin(lastlogin);
		f.setLastLogin(TimeUtil.getCurrentTime());
		f.setActiveStatus(true);
		new TeacherData().updateTeacherData(f, f);
		
	
		
	        this.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(final WindowEvent windowenent) {
	            	openPanel(exitbutton);
	            }
	        });
	        
	        
		
	}
	public void createpanel()
	{
		
		homepanel=new HomePanel(f);
		homepanel.setLocation(panelx,panely);
		homepanel.setVisible(true);
		homepanel.setFocusable(true);
		contentPane.add(homepanel);
		
	}
	
	public void activeButton(JButton button)
	{
		btn.setBackground(buttonbcolor);
		btn.setForeground(buttonfcolor);
		btn.setFont(buttonfont);
		btn.setDisabledIcon(new ImageIcon(""));
		btn.setIcon(new ImageIcon("./assets/"+btn.getName()+"dac.png"));
		btn=button;
		btn.setForeground(Color.white);
		btn.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
		btn.setIcon(new ImageIcon("./assets/"+btn.getName()+"ac.png"));
		disablepanel();
	}
	
	public JButton createButton(String text,String name)
	{
		JButton button=createButton(text);
		button.setName(name);
		button.setIcon(new ImageIcon("./assets/"+button.getName()+"dac.png"));
		return button;
	}
	public JButton createButton(String text)
	{
		JButton button=new JButton();
		button.setForeground(buttonfcolor);
		button.setFont(buttonfont);
		button.setBackground(buttonbcolor);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setFocusable(false);
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(new EmptyBorder(0,0,0,0));
		button.setText(text);
		button.setName(text);
		button.setIcon(new ImageIcon("./assets/"+button.getName()+"dac.png"));
		button.addActionListener(this);
		button.setIconTextGap(10);
		button.setLocation(0, row);
		button.setSize(234, 40);
		row+=39;
		return button;
	}
	public void disablepanel()
	{
		if(homepanel!=null && homepanel.isVisible())
		{
			homepanel.setVisible(false);
		}
		else if(subjectpanel!=null&&subjectpanel.isVisible())
		{
			subjectpanel.setVisible(false);
		}
		else if(studentpanel!=null&&studentpanel.isVisible())
		{
			studentpanel.setVisible(false);
		}
		else if(viewstudentpanel!=null && viewstudentpanel.isVisible())
		{
		
			viewstudentpanel.setVisible(false);
		}
		else if(teacherpanel!=null && teacherpanel.isVisible())
		{
			teacherpanel.setVisible(false);
		}
		else if(viewteacherpanel!=null&&viewteacherpanel.isVisible())
		{
			viewteacherpanel.setVisible(false);
		}
		else if(assignsubjectpanel!=null &&assignsubjectpanel.isVisible())
		{
			assignsubjectpanel.setVisible(false);
		}
		else if(entermarkspanelscroll!=null && entermarkspanelscroll.isVisible())
		{
			entermarkspanelscroll.setVisible(false);
		}
		else if(marksheetpanelscroll!=null&& marksheetpanelscroll.isVisible())
		{
			marksheetpanelscroll.setVisible(false);
		}
		else if(markattandancepanelscroll!=null && markattandancepanelscroll.isVisible())
		{
			markattandancepanelscroll.setVisible(false);
		}		
		else if(marksheetreportpanelscroll!=null && marksheetreportpanelscroll.isVisible())
		{
			marksheetreportpanelscroll.setVisible(false);
		}		
		else if(attandancereportpanelscroll!=null && attandancereportpanelscroll.isVisible())
		{
			attandancereportpanelscroll.setVisible(false);
		}
		else if(adminprofilepanel!=null && adminprofilepanel.isVisible())
		{
			adminprofilepanel.setVisible(false);
		}
		else if(searchpanel!=null && searchpanel.isVisible())
		{
			searchpanel.setVisible(false);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.openPanel(e.getSource());
	}
	public void openPanel(Object source) 
	{
		if(source==homebutton)
		{
			activeButton(homebutton);
			homepanel=new HomePanel(f);
			homepanel.setLocation(panelx, panely);
			homepanel.setFocusable(true);
			contentPane.add(homepanel);
			homepanel.setLastLogin(lastlogin);
			homepanel.setVisible(true);
		
		}
		
		else if(source==subjectbutton)
		{
			activeButton(subjectbutton);
			subjectpanel=new SubjectPanel(this);
			subjectpanel.setLocation(panelx, panely);
			subjectpanel.setFocusable(true);
			contentPane.add(subjectpanel);
			subjectpanel.setVisible(true);
		}
		else if(source==studentsbutton)
		{
			activeButton(studentsbutton);
			studentpanel=new StudentPanel(this);
			studentpanel.setLocation(panelx,panely);
			studentpanel.setVisible(true);
			studentpanel.setFocusable(true);
			contentPane.add(studentpanel);
		}
		else if(source==faculitiesbutton)
		{
			activeButton(faculitiesbutton);
			teacherpanel=new TeacherPanel(this);
			teacherpanel.setLocation(panelx,panely);
			teacherpanel.setVisible(true);
			teacherpanel.setFocusable(true);
			contentPane.add(teacherpanel);
			
		}
		else if(source==assignedsubjectbutton)
		{
			activeButton(assignedsubjectbutton);
			assignsubjectpanel=new AssignSubjectPanel(this);
			assignsubjectpanel.setLocation(panelx,panely);
			assignsubjectpanel.setVisible(true);
			assignsubjectpanel.setFocusable(true);
			contentPane.add(assignsubjectpanel);
			
		}
		else if(source==entermarksbutton)
		{
			
			activeButton(entermarksbutton);
			entermarkspanel=new EnterMarksPanel(this);
			entermarkspanel.setVisible(true);
			entermarkspanelscroll=new JScrollPane(entermarkspanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			entermarkspanelscroll.setBounds(panelx,panely,1116,705);
			entermarkspanelscroll.setVisible(true);
			entermarkspanelscroll.getVerticalScrollBar().setUnitIncrement(16);
			contentPane.add(entermarkspanelscroll);
			for(Component c:entermarkspanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
			
		}
		else if(source==marksheetreportbutton)
		{
			activeButton(marksheetreportbutton);
			marksheetreportpanel=new MarkSheetReportPanel(this);
			marksheetreportpanel.setVisible(true);
			marksheetreportpanelscroll=new JScrollPane(marksheetreportpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			marksheetreportpanelscroll.setBounds(panelx, panely, 1116, 705);
			marksheetreportpanelscroll.setVisible(true);
			marksheetreportpanelscroll.setName("Marksheet Report Panel Scroll");
			marksheetreportpanelscroll.getVerticalScrollBar().setUnitIncrement(80);
			contentPane.add(marksheetreportpanelscroll);
			for(Component c:marksheetreportpanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
		}
		else if(source==markattandancebutton)
		{
			activeButton(markattandancebutton);
			markattandancepanel=new MarkAttandancePanel(this);
			markattandancepanel.setVisible(true);
			markattandancepanelscroll=new JScrollPane(markattandancepanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			markattandancepanelscroll.setBounds(panelx, panely, 1116, 705);
			markattandancepanelscroll.setVisible(true);
			markattandancepanelscroll.getVerticalScrollBar().setUnitIncrement(16);
			contentPane.add(markattandancepanelscroll);
			for(Component c:markattandancepanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
		}
		else if(source==attandancereportbutton)
		{
			activeButton(attandancereportbutton);
			attandancereportpanel=new AttandanceReportPanel(this);
			attandancereportpanel.setVisible(true);
			attandancereportpanelscroll=new JScrollPane(attandancereportpanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			attandancereportpanelscroll.setBounds(panelx, panely, 1116, 705);
			attandancereportpanelscroll.setVisible(true);
			attandancereportpanelscroll.setName("Attadance Report Panel Scroll");
			attandancereportpanelscroll.getVerticalScrollBar().setUnitIncrement(16);
			contentPane.add(attandancereportpanelscroll);
			for(Component c:attandancereportpanelscroll.getComponents())
			{
				c.setBackground(Color.white);
			}
		}

		else if(source==searchbutton)
		{
			activeButton(searchbutton);
			searchpanel=new SearchPanel(this);
			searchpanel.setLocation(this.panelx, this.panely);
			searchpanel.setVisible(true);
			contentPane.add(searchpanel);
				
		}
	
		else if(source==myprofilebutton)
		{
			activeButton(myprofilebutton);
			viewteacherpanel=new ViewTeacherPanel(f,this);
			viewteacherpanel.setLocation(panelx,panely);
			viewteacherpanel.setVisible(true);
			viewteacherpanel.setFocusable(true);
			contentPane.add(viewteacherpanel);		
			}
		else if(source==exitbutton)
		{
			int result=JOptionPane.showConfirmDialog(null,"Do you want to exit this application ?","Exit",JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION)
			{
				f.setActiveStatus(false);
				timer.stop();
				new TeacherData().setActiveStatus(false, f.getTeacherId());
				disablepanel();
				System.exit(0);
			}
		}
		else if(source==logoutbutton)
		{
			int result=JOptionPane.showConfirmDialog(null,"Do you want to logout this application ?","Logout",JOptionPane.INFORMATION_MESSAGE);
			if(result==JOptionPane.YES_OPTION)
			{
				f.setActiveStatus(false);
				timer.stop();
				new TeacherData().setActiveStatus(false, f.getTeacherId());
				LoginPageFrame loginpageframe=new LoginPageFrame();
				loginpageframe.setVisible(true);
				loginpageframe.setLocationRelativeTo(null);
				disablepanel();
				this.dispose();
			}
		}
		
	}
	
	
	public void setTeacherDetails()
	{
		teacherprofilepiclabel.setIcon(new ImageIcon(f.getRoundedProfilePic(50, 50, 50)));
		teachernamelabel.setText(f.getTeacherName());
	}
	
}
