package universitydata.department;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import universitydata.common.HintTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


/*
 * Title : AddDepartmentDialog.java
 * Purpose : Dialog for adding new department
 */

@SuppressWarnings("serial")
public class AddDepartmentDialog extends JDialog implements ActionListener
{

	private JTextField departmentcodefield;
	private JTextField departmentnamefield;
	private JTextField totalsemoryearfield;
	private JComboBox<String> semoryearcombo;
	private JLabel lblError;
	private DepartmentPanel departmentpanel;

	public static void main(String[] args) {
		try {
			AddDepartmentDialog dialog = new AddDepartmentDialog();
			
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddDepartmentDialog(DepartmentPanel departmentpanel)
	{
		this();
		this.departmentpanel=departmentpanel;
	}
	public AddDepartmentDialog() {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 476, 452);
		getContentPane().setLayout(null);
		
		JLabel lblAddNewDepartment = new JLabel("Add New Department");
		lblAddNewDepartment.setForeground(new Color(255, 255, 255));
		lblAddNewDepartment.setBackground(new Color(32, 178, 170));
		lblAddNewDepartment.setOpaque(true);
		lblAddNewDepartment.setFont(new Font("Arial", Font.BOLD, 23));
		lblAddNewDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewDepartment.setBounds(0, 0, 473, 55);
		getContentPane().add(lblAddNewDepartment);
		
		JLabel lblDepartmentCode = new JLabel("Department Code ");
		lblDepartmentCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblDepartmentCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblDepartmentCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentCode.setBounds(10, 79, 159, 24);
		lblDepartmentCode.setFocusable(true);
		getContentPane().add(lblDepartmentCode);
		
		JLabel lblDepartmentName = new JLabel("Department Name ");
		lblDepartmentName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblDepartmentName.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblDepartmentName.setBounds(10, 147, 159, 24);
		getContentPane().add(lblDepartmentName);
		
		JLabel lblSemyear = new JLabel("Sem/Year");
		lblSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblSemyear.setBounds(10, 218, 139, 24);
		getContentPane().add(lblSemyear);
		
		departmentcodefield = new HintTextField("");
		departmentcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		departmentcodefield.setBounds(179, 72, 270, 40);
		getContentPane().add(departmentcodefield);
		departmentcodefield.setColumns(10);
		
		departmentnamefield = new HintTextField("");
		departmentnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		departmentnamefield.setColumns(10);
		departmentnamefield.setBounds(179, 141, 272, 40);
		getContentPane().add(departmentnamefield);
		
		totalsemoryearfield = new HintTextField("");
		totalsemoryearfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		totalsemoryearfield.setColumns(10);
		totalsemoryearfield.setBounds(179, 278, 270, 40);
		getContentPane().add(totalsemoryearfield);
		
		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] {"---Select Sem/Year---", "sem", "year"}));
		semoryearcombo.setBounds(179, 210, 272, 40);
		getContentPane().add(semoryearcombo);
		
		JLabel lblTotalSemyear = new JLabel("Total Sem/Year");
		lblTotalSemyear.setHorizontalAlignment(SwingConstants.LEFT);
		lblTotalSemyear.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblTotalSemyear.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblTotalSemyear.setBounds(10, 284, 139, 24);
		getContentPane().add(lblTotalSemyear);
		
		JButton adddepartmentbutton = new JButton("Add Department");
		adddepartmentbutton.setBackground(new Color(32, 178, 170));
		adddepartmentbutton.setForeground(new Color(255, 255, 255));
		adddepartmentbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		adddepartmentbutton.setBounds(290, 373, 159, 37);
		adddepartmentbutton.addActionListener(this);
		getContentPane().add(adddepartmentbutton);
		
		lblError=new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(157,115,355,21);
		getContentPane().add(lblError);
		
		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 346, 470, 14);
		getContentPane().add(label);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setText("This is required question !");
		String departmentname=departmentnamefield.getText();
		String departmentcode=departmentcodefield.getText();
		String semoryear=(String) semoryearcombo.getSelectedItem();
		String strtotalsemoryear=totalsemoryearfield.getText();
	 	if(departmentcode.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(departmentcodefield.getX(), departmentcodefield.getY()+departmentcodefield.getHeight(), lblError.getWidth(), 21);
			departmentcodefield.setFocusable(true);
		}
	
		else if(departmentname.isEmpty())
		{
			lblError.setVisible(true);
			lblError.setBounds(departmentnamefield.getX(), departmentnamefield.getY()+departmentnamefield.getHeight(), lblError.getWidth(), 21);
			departmentnamefield.setFocusable(true);
		}
		else if(semoryearcombo.getSelectedIndex()==0)
		{
			lblError.setVisible(true);
			lblError.setBounds(semoryearcombo.getX(), semoryearcombo.getY()+semoryearcombo.getHeight(),  lblError.getWidth(), 21);
			
		}
		else if(strtotalsemoryear.isEmpty())
		{
			
			lblError.setVisible(true);
			lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(),  lblError.getWidth(), 21);
			totalsemoryearfield.setFocusable(true);
		}
		
		else
		{
			 
				try
				{
					int totalsemoryear=Integer.parseInt(strtotalsemoryear);
					if(new DepartmentData().isDepartmentCodeExist(departmentcode.toUpperCase()))
					{
						lblError.setVisible(true);
						lblError.setBounds(departmentcodefield.getX(), departmentcodefield.getY()+departmentcodefield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Department code already exist !");
					}
					else if(new DepartmentData().isDepartmentNameExist(departmentname))
					{
						lblError.setVisible(true);
						lblError.setBounds(departmentnamefield.getX(), departmentnamefield.getY()+departmentnamefield.getHeight(), lblError.getWidth(), 21);
						departmentnamefield.setFocusable(true);
						lblError.setText("Department name already exist !");
					}
					else if(totalsemoryear<1)
					{
						lblError.setVisible(true);
						lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(),  lblError.getWidth(), 21);
						lblError.setText("Minimun 1 sem/year required !");
					}
					else
					{
						DepartmentData c=new DepartmentData();
						int result=c.addDepartment(departmentcode, departmentname, semoryear, totalsemoryear);
						if(result>0)
						{
							
							if(departmentpanel!=null)
							{
							departmentpanel.updatetableData();
							}
							this.dispose();
						}
						
					}
				}
				catch(NumberFormatException nexp)
				{
					lblError.setVisible(true);
					lblError.setBounds(totalsemoryearfield.getX(), totalsemoryearfield.getY()+totalsemoryearfield.getHeight(), lblError.getWidth(), 21);
					lblError.setText("Characters are not allowed !");
				}
				
		
		}
		
	}
	
}
