package universitydata.teacher;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatter;

import universitydata.admin.AdminMain;
import universitydata.student.StudentMain;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class PenddingTeacherPanel extends JPanel implements ActionListener {
	public JTable table;
	public AdminMain am;
	public JPanel tableviewpanel;
	public JScrollPane photoviewscrollpane;
	private JLabel maxphotolabel;
	private JSpinner maxphotospinner;
	int maxphoto=4;
	public StudentMain sm;
	public TeacherMain fm;
	private String condition="";
	private JLabel headinglabel;

	/**
	 * Create the panel.
	 * @param am 
	 */
	public PenddingTeacherPanel(AdminMain am)
	{
		this();
		this.am=am;
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
				{
					JTable t=(JTable) e.getSource();
					int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
					Teacher f=new TeacherData().getPendingTeacherInfo(fid);
				
				am.viewteacherpanel=new ViewTeacherPanel(f,am,am.teacherpanel2);
				am.viewteacherpanel.setVisible(true);
				am.teacherpanel2.setVisible(false);
				am.viewteacherpanel.setLocation(am.panelx,am.panely);
				am.viewteacherpanel.setVisible(true);
				am.viewteacherpanel.setFocusable(true);
				am.contentPane.add(am.viewteacherpanel);
				
				}
				
			}
		});
	}
	public PenddingTeacherPanel(TeacherMain fm)
	{
		
		this();
		this.fm=fm;
		condition=" where departmentcode='"+fm.f.getDepartmentCode()+"' and semoryear="+fm.f.getSemorYear()+" and teacherid!="+fm.f.getTeacherId();
		this.createtablemodel();
		 table.addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
					{
					JTable t=(JTable) e.getSource();
					int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
					Teacher f=new TeacherData().getTeacherInfobyId(fid);
				
				fm.viewteacherpanel=new ViewTeacherPanel(f,fm,fm.teacherpanel);
				fm.viewteacherpanel.setVisible(true);
				fm.teacherpanel.setVisible(false);
				fm.viewteacherpanel.setLocation(fm.panelx,fm.panely);
				fm.viewteacherpanel.setVisible(true);
				fm.viewteacherpanel.setFocusable(true);
				fm.contentPane.add(fm.viewteacherpanel);
				
				}
				
			}
		});
	}
	/**
	 * @param sm
	 */
	public PenddingTeacherPanel(StudentMain sm)
	{
		this();
		this.sm=sm;
		headinglabel.setText("Faculties");
		condition=" where departmentcode='"+sm.s.getDepartmentCode()+"' and semoryear="+sm.s.getSemorYear()+" ";
		this.createtablemodel();
		table.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if(e.getClickCount()>1  && e.getButton()==MouseEvent.BUTTON1)
				{
					JTable t=(JTable) e.getSource();
					int fid=Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0)+"");
					Teacher f=new TeacherData().getPendingTeacherInfo(fid);
				
				sm.viewteacherpanel=new ViewTeacherPanel(f,sm,sm.teacherpanel);
				sm.viewteacherpanel.setVisible(true);
				sm.teacherpanel.setVisible(false);
				sm.viewteacherpanel.setLocation(sm.panelx,sm.panely);
				sm.viewteacherpanel.setVisible(true);
				sm.viewteacherpanel.setFocusable(true);
				sm.contentPane.add(sm.viewteacherpanel);
				
				}
				
			}
		});
	}
	
	private PenddingTeacherPanel() {
		this.setName("Pendding Teacher Panel");
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		headinglabel = new JLabel("All Pendding Teachers");
		headinglabel.setIcon(null);
		headinglabel.setBounds(10, 65, 411, 44);
		panel.add(headinglabel);
		headinglabel.setBackground(new Color(32, 178, 170));
		headinglabel.setHorizontalAlignment(SwingConstants.LEFT);
		headinglabel.setForeground(Color.WHITE);
		headinglabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headinglabel.setOpaque(true);
		  
		   maxphotospinner = new JSpinner();
		   maxphotospinner.setForeground(new Color(255, 255, 255));
		   maxphotospinner.setBackground(new Color(255, 255, 255));
		  
		   maxphotospinner.setVerifyInputWhenFocusTarget(false);
		  maxphotospinner.setModel(new SpinnerNumberModel(4, 1, 12, 1));
		  maxphotospinner.setFont(new Font("Tahoma", Font.PLAIN, 17));
		  JComponent comp = maxphotospinner.getEditor();
		    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		    field.setFocusable(false);
		    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    maxphotospinner.addChangeListener(new ChangeListener() {

		        @Override
		        public void stateChanged(ChangeEvent e) {
		        	maxphoto=(int) maxphotospinner.getValue();
		        	createphotoviewpanel();
		        }
		    });
		  maxphotospinner.setBounds(1000, 83, 85, 33);
		  maxphotospinner.setVisible(false);
		  maxphotospinner.setFocusable(false);
		  panel.add(maxphotospinner);
		  
		  maxphotolabel = new JLabel("Max Photos in Row  ");
		  maxphotolabel.setHorizontalAlignment(SwingConstants.RIGHT);
		  maxphotolabel.setForeground(Color.WHITE);
		  maxphotolabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		  maxphotolabel.setBounds(793, 83, 197, 33);
		  panel.add(maxphotolabel);
		  maxphotolabel.setVisible(false);
		  
		   tableviewpanel = new JPanel();
		  tableviewpanel.setBackground(Color.WHITE);
		  tableviewpanel.setBounds(0, 189, 1116, 528);
		  add(tableviewpanel);
		  tableviewpanel.setLayout(null);
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(10, 11, 1095, 483);
		  scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			for(Component c : scrollPane.getComponents())
			{
				c.setBackground(Color.white);
			}
		  tableviewpanel.add(scrollPane);
		  
		  table = new JTable();
		
			table = new JTable();
			table.setCursor(new Cursor(Cursor.HAND_CURSOR));
			table.setBorder(new LineBorder(Color.LIGHT_GRAY));
			table.getTableHeader().setBackground(new Color(32,178,170));
			table.getTableHeader().setForeground(Color.white);
			table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
			table.setFont(new Font("Segoe UI",Font.PLAIN,20));
			table.getTableHeader().setPreferredSize(new Dimension(50,40));
			table.setDragEnabled(false);
			table.setRowHeight(40);
			createtablemodel();
			table.setSelectionBackground(new Color(240, 255, 255));
			table.setFocusable(false);
			table.setDefaultEditor(Object.class,null);
			table.setGridColor(Color.LIGHT_GRAY);
			table.getTableHeader().setReorderingAllowed(false);	
			scrollPane.setViewportView(table);
		  
			
		  
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		 createtablemodel();
		tableviewpanel.setVisible(true);
			
		if(photoviewscrollpane!=null && photoviewscrollpane.isVisible()) {
			maxphotolabel.setVisible(true);
			maxphotospinner.setVisible(true);
		}
		else
		{
			maxphotolabel.setVisible(false);
			maxphotospinner.setVisible(false);
		}
	}
	public void createphotoviewpanel()
	{
		if(this.photoviewscrollpane!=null)
		{
			this.photoviewscrollpane.setVisible(false);
		}
		this.tableviewpanel.setVisible(false);
		this.photoviewscrollpane.getVerticalScrollBar().setUnitIncrement(16);
		this.photoviewscrollpane.setBounds(0, 189, 1105, 500);
		this.photoviewscrollpane.setVisible(true);
		this.add(photoviewscrollpane);
		this.photoviewscrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
	}
	public void createtablemodel()
	{
	
		ResultSet rs=new TeacherData().getPenddingTeacherInfo(condition);
		if(rs!=null)
		{
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		table.getColumnModel().getColumn(0).setMaxWidth(200);
	    table.getColumnModel().getColumn(0).setHeaderValue("Teacher Id");
		table.getColumnModel().getColumn(1).setMaxWidth(300);
	    table.getColumnModel().getColumn(1).setHeaderValue("Teacher Name");
		table.getColumnModel().getColumn(2).setMaxWidth(500);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		table.getColumnModel().getColumn(4).setMaxWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		DefaultTableCellRenderer cellrenderer=new DefaultTableCellRenderer();
		cellrenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
	}

}
