package universitydata.student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import universitydata.admin.AdminMain;
import universitydata.teacher.TeacherMain;
import net.proteanit.sql.DbUtils;

/*
 * Title : StudentPanel.java
 * Purpose : Displaying all student details in table/photo view
 */

@SuppressWarnings("serial")
public class PenddingStudentPanel extends JPanel implements ActionListener
{
	public JTable table;
	public AdminMain am;
	public TeacherMain fm;
	public StudentMain sm;
	public JScrollPane tableviewscroll;
	public JScrollPane photoviewscrollpane;
	private JSpinner maxphotospinner;
	private JLabel maxphotolabel;
	private int maxphoto=5;
	private String condition=" ";
	private JLabel studentslabel;
	private JPanel panel;
	/**
	 * Create the panel.
	 */
	public PenddingStudentPanel(AdminMain am)
	{
		this();
		this.am=am;
	}

	private PenddingStudentPanel() 
	{
		this.setName("Pendding Student Panel");
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);
		
		tableviewscroll = new JScrollPane();
		tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableviewscroll.setBounds(10, 194, 1096, 500);
		for(Component c : tableviewscroll.getComponents())
		{
			c.setBackground(Color.white);
		}
		add(tableviewscroll);
		
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.getTableHeader().setBackground(new Color(32,178,170));
		table.getTableHeader().setForeground(Color.white);
		table.setSelectionBackground(new Color(240, 255, 255));
		table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		table.setFont(new Font("Segoe UI",Font.PLAIN,20));
		table.getTableHeader().setPreferredSize(new Dimension(50,40));
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setRowHeight(40);
		createtablemodel();
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);	
		tableviewscroll.setViewportView(table);
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1&&e.getButton()==MouseEvent.BUTTON1)
				{
					
				JTable t=(JTable) e.getSource();
				int row=t.getSelectedRow();
				String departmentcode=table.getValueAt(row,0)+"";
				String  strsem=table.getValueAt(row, 4)+"";
				int sem=Integer.parseInt(strsem.substring(strsem.indexOf('-')+1));
				String strroll=table.getValueAt(row, 1)+"";
				long rollnumber=Long.parseLong(strroll);
				Student s=new StudentData().getPenddingStudentDetails(departmentcode,sem,rollnumber);
				
					if(am!=null)
					{
						am.viewstudentpanel=new ViewStudentPanel(s,am,am.studentpanel2);
						am.viewstudentpanel.setVisible(true);
						am.studentpanel2.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx,0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);	
						am.contentPane.add(am.viewstudentpanel);
					}
				
				}
				
			}
		});
		
		panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		
		studentslabel = new JLabel("All Pendding Students");
		studentslabel.setIcon(null);
		studentslabel.setBounds(10, 65, 352, 44);
		panel.add(studentslabel);
		studentslabel.setBackground(new Color(32, 178, 170));
		studentslabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentslabel.setForeground(Color.WHITE);
		studentslabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		studentslabel.setOpaque(true);
		  maxphotospinner = new JSpinner();
		  
		  maxphotospinner.setModel(new SpinnerNumberModel(maxphoto, 1, 12, 1));
		  maxphotospinner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		  maxphotospinner.setBounds(1009, 98, 76, 30);
		  maxphotospinner.setVisible(false);
		  JComponent c=maxphotospinner.getEditor();
		  JFormattedTextField tf=(JFormattedTextField)c.getComponent(0);
		  tf.setFocusable(false);
		  DefaultFormatter f=(DefaultFormatter) tf.getFormatter();
		  f.setCommitsOnValidEdit(true);
		  maxphotospinner.addChangeListener(new ChangeListener()
				  {

					@Override
					public void stateChanged(ChangeEvent arg0) {
						// TODO Auto-generated method stub
						maxphoto=(int) maxphotospinner.getValue();
						
						createphotopanel();
					}
			  
			  
		  });
		  
		  panel.add(maxphotospinner);
		  
		  maxphotolabel = new JLabel("Max Photos in Row");
		  maxphotolabel.setHorizontalAlignment(SwingConstants.RIGHT);
		  maxphotolabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		  maxphotolabel.setForeground(new Color(255, 255, 255));
		  maxphotolabel.setBounds(797, 98, 193, 30);
		  maxphotolabel.setVisible(false);
		  panel.add(maxphotolabel);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		createtablemodel();
		tableviewscroll.setVisible(true);
		if(photoviewscrollpane!=null && photoviewscrollpane.isVisible())
		{
			maxphotolabel.setVisible(true);
			maxphotospinner.setVisible(true);
		}
		else
		{
			maxphotolabel.setVisible(false);
			maxphotospinner.setVisible(false);
		}
	}
	public void createphotopanel()
	{
		if(this.photoviewscrollpane!=null)
		{
			this.photoviewscrollpane.setVisible(false);
		}
		this.tableviewscroll.setVisible(false);
		this.photoviewscrollpane.getVerticalScrollBar().setUnitIncrement(16);
		this.photoviewscrollpane.setBounds(0, 189, 1105, 500);
		this.photoviewscrollpane.setVisible(true);
		this.add(photoviewscrollpane);
		this.photoviewscrollpane.setBorder(new EmptyBorder(0,0,0,0));
	}
	public void createtablemodel()
	{

		ResultSet rs=new StudentData().getPenddingStudentinfo(condition);
		if(rs!=null)
		{
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(0).setHeaderValue("Department Code");
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(250);
		table.getColumnModel().getColumn(3).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setHeaderValue("Department Name");
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
}
