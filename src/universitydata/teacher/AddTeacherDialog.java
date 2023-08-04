package universitydata.teacher;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import universitydata.admin.AdminMain;

@SuppressWarnings("serial")
public class AddTeacherDialog extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	private JTextField teacheridfield;
	private JTextField teachernamefield;
	private JTextField statefield;
	private JTextField cityfield;
	private JTextField emailidfield;
	private JTextField contactnumberfield;
	private JTextField qualificationfield;
	private JTextField experiencefield;
	private static AddTeacherDialog dialog;
	private String defaultpicpath="./assets/profilepicicon.jpg";
	private JButton choosefilebutton,addteacherbutton;
	private File file;
	private JLabel filesizenote,filenamelabel,filesize;
	private JLabel profilepiclabel;
	private JLabel Errorlabel;
	private JSpinner birthdatespinner;
	private JComboBox<String> gendercombo;
	TeacherPanel fp;
	private AdminMain am;
	private Teacher teacher;
	private JLabel headerlabel;

	public static void main(String[] args) {
		try {
			dialog = new AddTeacherDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public AddTeacherDialog() {	
		
		super(dialog,"",Dialog.ModalityType.APPLICATION_MODAL);
		
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(350, 20, 711, 680);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		headerlabel = new JLabel("Add New Teacher");
		headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBounds(0, 0, 695, 42);
		getContentPane().add(headerlabel);
		
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		
		JLabel faculityidlabel = new JLabel("Teacher ID");
		faculityidlabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		faculityidlabel.setBounds(21, 53, 134, 29);
		getContentPane().add(faculityidlabel);
		
		teacheridfield = new JTextField(new TeacherData().createTeacherID()+"");
		teacheridfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		teacheridfield.setEditable(false);
		teacheridfield.setBounds(20, 85, 323, 42);
		getContentPane().add(teacheridfield);
		teacheridfield.setColumns(10);
		
		JLabel lblFaculityName = new JLabel("Teacher Name");
		lblFaculityName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFaculityName.setBounds(362, 53, 166, 29);
		getContentPane().add(lblFaculityName);
		
		teachernamefield = new JTextField();
		teachernamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				teachernamefield.setFocusable(true);
			}
		});
		teachernamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					teachernamefield.setFocusable(false);
				}
				
			}
		});
		teachernamefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		teachernamefield.setColumns(10);
		teachernamefield.setBounds(362, 85, 323, 42);
		getContentPane().add(teachernamefield);
		
		JLabel lblState = new JLabel("District");
		lblState.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblState.setBounds(21, 145, 134, 21);
		getContentPane().add(lblState);
		
		statefield = new JTextField();
		statefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		statefield.setColumns(10);
		statefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					statefield.setFocusable(false);
				}
				
			}
		});
		statefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				statefield.setFocusable(true);
			}
		});
		statefield.setBounds(21, 173, 322, 42);
		getContentPane().add(statefield);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCity.setBounds(362, 141, 143, 29);
		getContentPane().add(lblCity);
		
		cityfield = new JTextField();
		cityfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		cityfield.setColumns(10);
		cityfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					cityfield.setFocusable(false);
				}
				
			}
		});
		cityfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cityfield.setFocusable(true);
			}
		});
		cityfield.setBounds(362, 173, 323, 42);
		getContentPane().add(cityfield);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmailId.setBounds(21, 231, 134, 29);
		getContentPane().add(lblEmailId);
		
		emailidfield = new JTextField();
		emailidfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		emailidfield.setColumns(10);
		emailidfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					emailidfield.setFocusable(false);
				}
				
			}
		});
		emailidfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				emailidfield.setFocusable(true);
			}
		});
		emailidfield.setBounds(21, 260, 322, 42);
		getContentPane().add(emailidfield);
		
		JLabel lblPhoneNumber = new JLabel("Contact Number");
		lblPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(362, 226, 185, 29);
		getContentPane().add(lblPhoneNumber);
		
		contactnumberfield = new JTextField();
		contactnumberfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		contactnumberfield.setColumns(10);
		contactnumberfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					contactnumberfield.setFocusable(false);
				}
				
			}
		});
		contactnumberfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contactnumberfield.setFocusable(true);
			}
		});
		contactnumberfield.setBounds(362, 260, 323, 42);
		getContentPane().add(contactnumberfield);
		
		JLabel lblQualification = new JLabel("Qualification");
		lblQualification.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblQualification.setBounds(21, 326, 134, 29);
		getContentPane().add(lblQualification);
		
		qualificationfield = new JTextField();
		qualificationfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		qualificationfield.setColumns(10);
		qualificationfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					qualificationfield.setFocusable(false);
				}
				
			}
		});
		qualificationfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				qualificationfield.setFocusable(true);
			}
		});
		qualificationfield.setBounds(21, 355, 322, 42);
		getContentPane().add(qualificationfield);
		
		JLabel lblExperience = new JLabel("Experience");
		lblExperience.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblExperience.setBounds(362, 326, 134, 29);
		getContentPane().add(lblExperience);
		
		experiencefield = new JTextField();
		experiencefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		experiencefield.setColumns(10);
		experiencefield.setBounds(362, 355, 322, 42);
		experiencefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					experiencefield.setFocusable(false);
				}
				
			}
		});
		experiencefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				experiencefield.setFocusable(true);
			}
		});
		getContentPane().add(experiencefield);
		
		JLabel doblabel = new JLabel("Date of Birth");
		doblabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		doblabel.setBounds(21, 414, 134, 29);
		getContentPane().add(doblabel);
		
		birthdatespinner = new JSpinner();
		birthdatespinner.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		birthdatespinner.setModel(new SpinnerDateModel());
		SimpleDateFormat model=new SimpleDateFormat("dd-MM-yyyy");
		birthdatespinner.setEditor(new JSpinner.DateEditor(birthdatespinner,model.toPattern()));
		birthdatespinner.setForeground(Color.WHITE);
		birthdatespinner.setBounds(21, 442, 322, 42);
		getContentPane().add(birthdatespinner);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblGender.setBounds(362, 414, 134, 29);
		getContentPane().add(lblGender);
		
		gendercombo = new JComboBox<String>();
		gendercombo.setFocusable(false);
		
		gendercombo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		gendercombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Select Gender---", "Male", "Female"}));
		gendercombo.setBackground(Color.WHITE);
		gendercombo.setBounds(362, 442, 323, 42);
		gendercombo.addActionListener(this);
		getContentPane().add(gendercombo);
		
		profilepiclabel = new JLabel("");
		profilepiclabel.setBorder(new LineBorder(Color.GRAY));
		profilepiclabel.setIcon(new ImageIcon(defaultpicpath));
		profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);
		profilepiclabel.setBounds(21, 507, 100, 120);
		getContentPane().add(profilepiclabel);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblPhoto.setBounds(163, 507, 54, 29);
		getContentPane().add(lblPhoto);
		
		filesize = new JLabel("");
		filesize.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		filesize.setBounds(227, 507, 399, 29);
		getContentPane().add(filesize);
		
		choosefilebutton = new JButton("Choose File");
		choosefilebutton.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		choosefilebutton.setForeground(Color.BLACK);
		choosefilebutton.addActionListener(this);
		choosefilebutton.setFocusable(false);
		choosefilebutton.setBackground(new Color(245, 245, 245));
		choosefilebutton.setBounds(161, 547, 114, 35);
		choosefilebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(choosefilebutton);
		
		 filenamelabel = new JLabel("No File Choosen");
		filenamelabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		filenamelabel.setBounds(285, 547, 400, 29);
		getContentPane().add(filenamelabel);
		
		filesizenote = new JLabel("Imagesize < 1024 KB");
		filesizenote.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		filesizenote.setBounds(163, 593, 373, 29);
		getContentPane().add(filesizenote);
		
		addteacherbutton = new JButton("Add Teacher");
		addteacherbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addteacherbutton.setForeground(new Color(255, 255, 255));
		addteacherbutton.setBackground(new Color(32, 178, 170));
		addteacherbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		addteacherbutton.setFocusable(false);
		addteacherbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addteacherbutton.addActionListener(this);
		addteacherbutton.setBounds(530, 587, 155, 37);
		getContentPane().add(addteacherbutton);
		
		Errorlabel = new JLabel("This is required question !");
		Errorlabel.setForeground(new Color(255, 0, 0));
		Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		Errorlabel.setBounds(21, 127, 322, 17);
		Errorlabel.setVisible(false);
		getContentPane().add(Errorlabel);
		
	}
	public AddTeacherDialog(TeacherPanel teacherpanel)
	{
		
		this();
		this.fp=teacherpanel;
	}
	public AddTeacherDialog(AdminMain am,Teacher f)
	{
		this();
		this.teacher=f;
		this.am=am;
		teacheridfield.setText(f.getTeacherId()+"");
		teachernamefield.setText(f.getTeacherName());
		statefield.setText(f.getState());
		cityfield.setText(f.getCity());
		emailidfield.setText(f.getEmailId());
		contactnumberfield.setText(f.getContactNumber());
		birthdatespinner.setValue(f.getBirthDateInDateFormat());
		gendercombo.setSelectedItem(f.getGender()+"");
		experiencefield.setText(f.getExperience());
		qualificationfield.setText(f.getQualification());
		profilepiclabel.setIcon(new ImageIcon(f.getProfilePic(100, 120)));
		headerlabel.setText("Edit Teacher Details");
		addteacherbutton.setText("Update Teacher");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Errorlabel.setVisible(false);
		if(e.getSource()==choosefilebutton)
		{
			FileDialog fd=new FileDialog(this,"Choose a File",FileDialog.LOAD);
			
			fd.setDirectory(".\\Faculities Profile pic\\");
			fd.setFile("*.jpeg;*.jpg;*.png;*.tiff;*.tif;*.gif;");	
			fd.setVisible(true);
			fd.setLocationRelativeTo(this);
			String filename=fd.getFile();
			String path=fd.getDirectory();
			
			if(filename!=null)
			{
 				
				file=new File(path+filename);
				long bytes=file.length();
				if(bytes<10482376)
				{
					try
					{
						 filesizenote.setForeground(new Color(46,139,27));
						filesizenote.setText("Image size < 1024 KB");
						Image image = ImageIO.read(file).getScaledInstance(100, 120, Image.SCALE_SMOOTH);
						profilepiclabel.setIcon(new ImageIcon(image));
						filesize.setText(file.length()/1024+" KB");
						filenamelabel.setText(file.getName());	
					}
					catch(Exception exp)
					{
						file = null;
	            		filenamelabel.setText("No file Choosen");
	            		filesize.setText("");
	            	   filesizenote.setForeground(Color.red);
	            	   filesizenote.setText("Image Not supported");
	            	   exp.printStackTrace();
					}
				}
				else
				{
					file = null;
					filesizenote.setForeground(Color.red);
					filesizenote.setText("Image size greater than 1024 KB");
					filesize.setText("");
					filenamelabel.setText("No File Choosen");
				}
			
			}
		// TODO Auto-generated method stub
		}
		if(e.getSource()==addteacherbutton)
		{
			
			if(teachernamefield.getText().isEmpty())
			{
				showerror(teachernamefield);
			}
			else if(statefield.getText().isEmpty())
			{
				showerror(statefield);
			}
			else if(cityfield.getText().isEmpty())
			{
				showerror(cityfield);
			}
			else if(emailidfield.getText().isEmpty())
			{
				showerror(emailidfield);
			}
			else if(contactnumberfield.getText().isEmpty())
			{
				showerror(contactnumberfield);
			}

			else if(qualificationfield.getText().isEmpty())
			{
				showerror(qualificationfield);
				
			}
			else if(experiencefield.getText().isEmpty())
			{
				showerror(experiencefield);
			}
			
			else if(gendercombo.getSelectedIndex()==0)
			{
				showerror(gendercombo);
			}
			else
			{
				try
				{
					Teacher f=new Teacher();
					f.setTeacherId(Integer.parseInt(teacheridfield.getText()));
					f.setTeacherName(teachernamefield.getText());
					f.setState(statefield.getText());
					f.setCity(cityfield.getText());
					f.setEmailId(emailidfield.getText());
					f.setContactNumber(contactnumberfield.getText());
					f.setExperience(experiencefield.getText());
					f.setQualification(qualificationfield.getText());
					Date date=(Date) birthdatespinner.getValue();
					f.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
					f.setGender(gendercombo.getSelectedItem()+"");
					
					if(file!=null) {
						f.setProfilePic(ImageIO.read(file));
					}
					else if(teacher!=null)
					{
						f.setProfilePic(teacher.getProfilePic());
					}
					else 
					{
					
						file = new File(defaultpicpath);
						f.setProfilePic(ImageIO.read(file));
					}
					int result=0;
					if(fp!=null)
					{
					result=new TeacherData().addTeacherData(f);
					}
					else if(am!=null && teacher!=null)
					{
						result=new TeacherData().updateTeacherData(teacher, f);
					}
				
					if(result>0)
					{
						if(fp!=null)
						{
							if(fp.photoviewscrollpane!=null && fp.photoviewscrollpane.isVisible())
							{
								fp.createtablemodel();
								fp.createphotoviewpanel();
							}
							else
							{
								fp.createtablemodel();
							}
						}
						else if(am!=null && teacher!=null)
						{
							am.viewteacherpanel.setVisible(false);
							am.viewteacherpanel=new ViewTeacherPanel(new TeacherData().getTeacherInfobyId(f.getTeacherId()),am,am.viewteacherpanel.lastpanel);
							am.viewteacherpanel.setVisible(true);
							am.viewteacherpanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewteacherpanel);
					
							
							
						}
						this.dispose();
						
					}
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}
			}
		}
	}

	public void showerror(JComponent tf)
	{
		Errorlabel.setVisible(true);
		Errorlabel.setBounds(tf.getX(), tf.getY()+tf.getHeight()-5, 400,26);
	}
}
