import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.List;
import java.util.Map;

class user{
	String accno,rollno;
	int type;
	LocalDateTime startTime;
	user(String rollno,LocalDateTime strt) {
		this.rollno=rollno;
		this.startTime=strt;
	}
	void scanaccno(String accno) {
		this.accno=accno;
	}
}

public class LibraryAppWithBarcode extends JFrame {
	
	public JFrame frame;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTextField textField;
	private String s,name="NIL",bookno="NIL",bookname="NIL",Author="NIL";
	private int l=0,t1=0,t2=0;
	private List<String> scannedusers;
	private Map<String,Integer> users;
	private String temp,enteredPassword,Password="libdesk";
	private JLabel lblNewLabel_7,lblNewLabel_8,lblNewLabel_5,lblNewLabel_4;
	private Map<String,String> titles;
	private Map<String,LocalDateTime> starttime;
	private Map<String,Integer> res;
	private LocalDateTime endTime;
	private DefaultTableModel tableModel,tableModel1,tableModel5,tableModel4,tableModel2;
	Map<String,user> userscan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibraryAppWithBarcode frame = new LibraryAppWithBarcode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void updateStartTime(String user,LocalDateTime v){
		starttime.put(user, v);
    }
	
	public void IDscan(String s) {
		if(s.startsWith("1602") && l==0) {
			userscan.put(s,new user(s,LocalDateTime.now()));
			l=1;
			t1=t1+1;
            t2=t2+1;
            lblNewLabel_7.setText("  Total Logins:"+t1);
            lblNewLabel_8.setText("  Active Logins:"+t2);
            textField.setText("");
            lblNewLabel_5.setText("WELCOME:"+s+"  name:"+name+"  Department:"+getDepartmentFromUserId(s));
            lblNewLabel_4.setText(" ");
            temp=s;
		}
		else if(l==0 && !(s.startsWith("1602"))){
            JOptionPane.showMessageDialog(frame, "INVALID ID CARD", "Error", JOptionPane.ERROR_MESSAGE);
            textField.setText("");
            l=0;
        }
		
	}
	public void Finishread(String s) {
		endTime=LocalDateTime.now();
		textField.setText("");
		t2=t2-1;
		lblNewLabel_8.setText("  Active Logins:"+t2);
		lblNewLabel_5.setText("THANK YOU AND VISIT AGAIN:"+s);
		lblNewLabel_4.setText("");
		int i=userscan.get(s).type;
		System.out.println(i);
		if(i==0) {
			Object[] rowData = {userscan.get(s).accno, "Unknown Book", "Unknown Author", s, userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")), endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")), getDepartmentFromUserId(s),"NAME",Duration.between(userscan.get(s).startTime,endTime).toMinutes(),1};
            tableModel.addRow(rowData);
            table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
		}
		else if(i==1) {
			Object[] rowData = {userscan.get(s).accno, "Unknown Book", "Unknown Author", s, "dept", userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")), endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")), getDepartmentFromUserId(s),"NAME",Duration.between(userscan.get(s).startTime,endTime).toMinutes(),1};
            tableModel1.addRow(rowData);
            table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
		}
		else if(i==2) {
			Object[] rowData = {userscan.get(s).accno, "Unknown Book", "Unknown Author", s, userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")), endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),getDepartmentFromUserId(s),"NAME",Duration.between(userscan.get(s).startTime,endTime).toMinutes(),1};
			
            tableModel2.addRow(rowData);
            table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
		}
		else if(i==3) {
			Object[] rowData= {s,userscan.get(s).accno,userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),getDepartmentFromUserId(s),"NAME",Duration.between(userscan.get(s).startTime,endTime).toMinutes(),1};
			tableModel4.addRow(rowData);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
		}
		else if(i==4) {
			Object[] rowData= {userscan.get(s).accno,"NIL","NIL","NIL","NIL","NIL","NIL",s,userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),userscan.get(s).startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")),getDepartmentFromUserId(s),"NAME",Duration.between(userscan.get(s).startTime,endTime).toMinutes(),1};
			tableModel5.addRow(rowData);
			table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
		}
		System.out.println(userscan);
		userscan.remove(s);
		System.gc();
		System.out.println(userscan);
	}
	public void Bookscan(String s1) {
		if(s.startsWith("0") ) {
			l=0;
			userscan.get(s1).accno=s;
			textField.setText("");
			lblNewLabel_4.setText("Book no:"+bookno+"  Bookname:"+bookname+"  Author:"+Author);
			userscan.get(s1).type=0;
		}
		else if(s.startsWith("J")) {
			l=0;
			userscan.get(s1).accno=s;
			textField.setText("");
			lblNewLabel_4.setText("Journal no:"+bookno+"  Date Year:"+bookname+"  Publisher dept:"+Author);
			//updateStartTime(s1,LocalDateTime.now());
			userscan.get(s1).type=1;
		}
		else if(s.startsWith("BJ")) {
			l=0;
			userscan.get(s1).accno=s;
			textField.setText("");
			lblNewLabel_4.setText("Journal no:"+bookno+"  Date Year:"+bookname+"  Publisher :"+Author);
			//updateStartTime(s1,LocalDateTime.now());
			userscan.get(s1).type=2;
		}
		else if(s.equals("LAPTOP") || s.equals("DIGITAL LIBRARY") || s.equals("EXAM")) {
			//updateStartTime(s1,LocalDateTime.now());
			userscan.get(s1).accno=s;
			lblNewLabel_4.setText("Resource:"+s1);
			textField.setText("");
			userscan.get(s1).accno=s;
			l=0;
			userscan.get(s1).type=3;
		}
		else if(s.startsWith("P")) {
			//updateStartTime(s1,LocalDateTime.now());
			lblNewLabel_4.setText("Accession no:"+bookno+"  Project Name:"+bookname+"  Dept:"+Author);
			textField.setText("");
			userscan.get(s1).accno=s;
			l=0;
			userscan.get(s1).type=4;
		}
		else {
			JOptionPane.showMessageDialog(frame, "INVALID RESOURCE", "Error", JOptionPane.ERROR_MESSAGE);
			textField.setText("");
            l=1;
		}
		
	}
	public LibraryAppWithBarcode() {
		setTitle("Usage Mointoring System");
		setBackground(new Color(255, 255, 255));

		userscan=new WeakHashMap<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1297, 697);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Accession NUMBER");
        tableModel.addColumn("Book Name");
        tableModel.addColumn("Author");
        tableModel.addColumn("User ID");
        
        tableModel.addColumn("Date");
        tableModel.addColumn("Start Time");
        tableModel.addColumn("Last Read Time");
        tableModel.addColumn("Department");
        tableModel.addColumn("Name");
        tableModel.addColumn("Duration");
        tableModel.addColumn("Floor");
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(120); 
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(9,144,599,207);
		contentPane.add(scrollPane);
		
		tableModel1 = new DefaultTableModel();
		tableModel1.addColumn("Accession NUMBER");
        tableModel1.addColumn("Date year");
        tableModel1.addColumn("Published Dept");
        tableModel1.addColumn("User ID");
        
        tableModel1.addColumn("Date");
        tableModel1.addColumn("Start Time");
        tableModel1.addColumn("Last Read Time");
        tableModel1.addColumn("Department");
        tableModel1.addColumn("Name");
        tableModel1.addColumn("Duration");
        tableModel1.addColumn("Floor");
		table_1 = new JTable(tableModel1);
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            table_1.getColumnModel().getColumn(i).setPreferredWidth(120); 
        }
		JScrollPane scrollPane1 = new JScrollPane(table_1);
        scrollPane1.setBounds(644, 144, 613, 216);
		contentPane.add(scrollPane1);
		
		tableModel2 = new DefaultTableModel();
		tableModel2.addColumn("Accession NUMBER");
        tableModel2.addColumn("Date year");
        tableModel2.addColumn("Publisher");
        tableModel2.addColumn("User ID");
        
        tableModel2.addColumn("Start Time");
        tableModel2.addColumn("Last Read Time");
        tableModel2.addColumn("Department");
        tableModel2.addColumn("Name");
        tableModel2.addColumn("Duration");
        tableModel2.addColumn("Floor");
		table_2 = new JTable(tableModel2);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 10; i++) {
            table_2.getColumnModel().getColumn(i).setPreferredWidth(120); 
        }
		JScrollPane scrollPane2 = new JScrollPane(table_2);
        scrollPane2.setBounds(9, 402, 487, 140);
		contentPane.add(scrollPane2);
		
		tableModel5 = new DefaultTableModel();
		tableModel5.addColumn("Accession Number");
		tableModel5.addColumn("Name of the Project");
		tableModel5.addColumn("Name of Student");
		tableModel5.addColumn("Hall Ticket Number");
		tableModel5.addColumn("Guide Name");
        tableModel5.addColumn("Department");
        tableModel5.addColumn("Year");
        tableModel5.addColumn("User ID");
        tableModel5.addColumn("Date");
        tableModel5.addColumn("IN Time");
        tableModel5.addColumn("OUT Time");
        tableModel5.addColumn("Department");
        tableModel5.addColumn("Name");
        tableModel5.addColumn("Duration");
        tableModel5.addColumn("Floor");
		table_3 = new JTable(tableModel5);
		table_3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 7; i++) {
            table_3.getColumnModel().getColumn(i).setPreferredWidth(120); 
        }
		JScrollPane scrollPane5 = new JScrollPane(table_3);
        scrollPane5.setBounds(888, 402, 385, 140);
		contentPane.add(scrollPane5);
		
		tableModel4 = new DefaultTableModel();
        tableModel4.addColumn("User ID");
        tableModel4.addColumn("ReSource");
        
        tableModel4.addColumn("Date");
        tableModel4.addColumn("IN Time");
        tableModel4.addColumn("OUT Time");
        tableModel4.addColumn("Department");
        tableModel4.addColumn("Name");
        tableModel4.addColumn("Duration");
        tableModel4.addColumn("Floor");
		table_4 = new JTable(tableModel4);
		JScrollPane scrollPane4 = new JScrollPane(table_4);
		table_4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < 7; i++) {
            table_4.getColumnModel().getColumn(i).setPreferredWidth(120); 
        }
        scrollPane4.setBounds(506, 402, 372, 171);
		contentPane.add(scrollPane4);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s=textField.getText();
				if(l==0) {
					
						if(userscan.containsKey(s)) {
							Finishread(s);
						}
						else {
							IDscan(s);
						}
				}
				else if(l==1) {
					Bookscan(temp);
				}
			}
		});
		textField.setBounds(10, 553, 330, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("VASAVI COLLEGE OF ENGINEERING");
		 lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel_1.setBounds(359, -2, 561, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Dr.Sarvepalli Radhakrishnan Learning Resource Centre");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_2.setBounds(346, 33, 599, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Library Resources-Usage Monitoring System");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_3.setBounds(394, 72, 447, 36);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("Arial", Font.ITALIC, 12));
		lblNewLabel_4.setBounds(10, 622, 516, 27);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("WELCOME");
		lblNewLabel_5.setFont(new Font("Arial", Font.ITALIC, 12));
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(10, 584, 496, 27);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Logout All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enteredPassword=getPassword();
        		if(enteredPassword.equals(Password)) {
        			String[] keys=userscan.keySet().toArray(new String[0]);
        			for(String key: keys) {
        				
        					Finishread(key);
        				
        			}
        		}
			}
		});
		btnNewButton.setBounds(683, 626, 101, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Get Data");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showHistoryInTable1();
				
			}
		});
		btnNewButton_1.setBounds(683, 586, 101, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("Floor 1:");
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setFont(new Font("Arial", Font.ITALIC, 15));
		lblNewLabel_6.setBounds(516, 584, 187, 20);
		contentPane.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("  Total Logins");
		lblNewLabel_7.setForeground(new Color(255, 0, 0));
		lblNewLabel_7.setFont(new Font("Arial", Font.ITALIC, 15));
		lblNewLabel_7.setBounds(525, 604, 148, 20);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("  Active Logins");
		lblNewLabel_8.setForeground(new Color(255, 0, 0));
		lblNewLabel_8.setFont(new Font("Arial", Font.ITALIC, 15));
		lblNewLabel_8.setBounds(525, 628, 155, 21);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("DEVELOPED BY TECHNOSPHERE IT");
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 22));
		lblNewLabel_9.setBounds(852, 579, 421, 36);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("IT DEPARTMENT VASAVI COLLEGE OF ENGINEERING");
		lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_10.setBounds(852, 616, 421, 38);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("BOUND JOURNALS");
		lblNewLabel_11.setForeground(new Color(255, 0, 0));
		lblNewLabel_11.setBounds(9, 371, 296, 24);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("DIGITAL LIBRARY, LAPTOP & Examination preparation");
		lblNewLabel_12.setForeground(new Color(255, 0, 0));
		lblNewLabel_12.setBounds(506, 373, 314, 18);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("PROJECT WORK");
		lblNewLabel_13.setForeground(new Color(255, 0, 0));
		lblNewLabel_13.setBounds(888, 371, 259, 20);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("BOOKS");
		lblNewLabel_14.setForeground(new Color(255, 0, 0));
		lblNewLabel_14.setBounds(9, 113, 213, 20);
		contentPane.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("JOURNALS");
		lblNewLabel_15.setForeground(new Color(255, 0, 0));
		lblNewLabel_15.setBounds(660, 119, 251, 20);
		contentPane.add(lblNewLabel_15);
		
	}
	private void showHistoryInTable1() {
        
        JFrame historyFrame = new JFrame("Reading History");
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel label1=new JLabel("Floor 1:-");
        JLabel label2=new JLabel("  Total Logins:"+t1);
        JLabel label3=new JLabel("  Active Logins:"+t2);
        JLabel label4=new JLabel("Floor 2:-");
        JLabel label5=new JLabel("  Total Logins:");
        JLabel label6=new JLabel("  Active Logins:");
        JLabel label7=new JLabel("Total Library:-");
        JLabel label8=new JLabel("  Total Logins:"+t1);
        JLabel label9=new JLabel("  Active Logins:"+t2);
        label1.setFont(new Font("Arial", Font.ITALIC, 15));
        label2.setFont(new Font("Arial", Font.ITALIC, 15));
        label3.setFont(new Font("Arial", Font.ITALIC, 15));
        label4.setFont(new Font("Arial", Font.ITALIC, 15));
        label5.setFont(new Font("Arial", Font.ITALIC, 15));
        label6.setFont(new Font("Arial", Font.ITALIC, 15));
        label7.setFont(new Font("Arial", Font.ITALIC, 15));
        label8.setFont(new Font("Arial", Font.ITALIC, 15));
        label9.setFont(new Font("Arial", Font.ITALIC, 15));
        JPanel loginspanel=new JPanel(new GridLayout(9,1));
        loginspanel.add(label1);
        loginspanel.add(label2);
        loginspanel.add(label3);
        loginspanel.add(label4);
        loginspanel.add(label5);
        loginspanel.add(label6);
        loginspanel.add(label7);
        loginspanel.add(label8);
        loginspanel.add(label9);
        historyFrame.getContentPane().add(loginspanel);
        historyFrame.pack();
        historyFrame.setLocationRelativeTo(frame);
        historyFrame.setVisible(true);
	}
	private String getPassword() {
    	return JOptionPane.showInputDialog(frame,"Enter the password");
    }
	private String getDepartmentFromUserId(String userId) {
        String dept=userId.substring(8, 11);
        switch (dept) {
            case "732":
                return "Civil";
            case "733":
                return "CSE";
            case "734":
                return "EEE";
            case "735":
                return "ECE";
            case "736":
                return "Mech";
            case "737":
                return "IT";
            case "748":
                return "AI&ML";
            default:
                return "Unknown Department";
        }
    }
}
