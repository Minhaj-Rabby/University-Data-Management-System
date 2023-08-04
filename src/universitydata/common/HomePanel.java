package universitydata.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import universitydata.admin.Admin;
import universitydata.student.Student;
import universitydata.teacher.Teacher;

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements ActionListener {
	
	private JPanel homeheaderpanel;
	public JLabel lastloginlabel;
	private JLabel timedifflabel;
	private JLabel welcomelabel;
	
	private HomePanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);

		homeheaderpanel = new JPanel();
		homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		homeheaderpanel.setBackground(new Color(32, 178, 170));
		homeheaderpanel.setLayout(null);
		homeheaderpanel.setBounds(10, 0, 1096, 279);
		add(homeheaderpanel);

		welcomelabel = new JLabel("Welcome");
		welcomelabel.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomelabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		welcomelabel.setForeground(Color.WHITE);
		welcomelabel.setBounds(0, 96, 1076, 45);
		homeheaderpanel.add(welcomelabel);

		JLabel lblHome = new JLabel("Home Page");
		lblHome.setIcon(null);
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblHome.setBounds(10, 97, 377, 45);
		homeheaderpanel.add(lblHome);

		lastloginlabel = new JLabel("Last Login : First Login");
		lastloginlabel.setBackground(Color.WHITE);
		lastloginlabel.setForeground(Color.WHITE);
		lastloginlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastloginlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lastloginlabel.setBounds(10, 143, 1066, 30);
		homeheaderpanel.add(lastloginlabel);

		timedifflabel = new JLabel("");
		timedifflabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timedifflabel.setForeground(Color.WHITE);
		timedifflabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		timedifflabel.setBackground(Color.WHITE);
		timedifflabel.setBounds(599, 75, 486, 19);
		homeheaderpanel.add(timedifflabel);

	}

	public HomePanel(Admin a) {
		this();
		welcomelabel.setText("Welcome Adminstrator");

	}

	public HomePanel(Teacher f) {
		this();
		welcomelabel.setText("Welcome " + f.getTeacherName());
		
	}

	public HomePanel(Student s) {
		this();
		welcomelabel.setText("Welcome " +s.getFullName());
	}

	public void setLastLogin(String lastlogin) {
		if (lastlogin == null || lastlogin.isEmpty()) {
			this.lastloginlabel.setText("last login : First Time");
		} else {
			this.lastloginlabel.setText("last login : " + lastlogin);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}
