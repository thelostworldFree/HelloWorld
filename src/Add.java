import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Add implements ActionListener
{
	JFrame f1;
	Container ct;
	JPanel jp,jp1,jp2,jp3;
	JButton btn1,btn2;
	JLabel label;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";

	    String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=GunManger";

	    String userName="sa";

	    String userPwd="666666";
	Add(){
		f1=new JFrame();
		ct=f1.getContentPane();//��ʼ�����
		jp=new JPanel();
		jp1=new JPanel(new GridLayout(6,1));
		jp2=new JPanel(new GridLayout(6,1));
		jp3=new JPanel();
		btn1=new JButton("ȷ��");
		btn2=new JButton("ȡ��");
		label=new JLabel("<html><font color=#CC00FF size='4'>���ǹе��Ϣ</font>",SwingConstants.CENTER);
		label.setForeground(Color.gray);
		
		tf1=new JTextField(20);
		tf2=new JTextField(20);
		tf3=new JTextField(20);
		tf4=new JTextField(20);
		tf5=new JTextField(20);
		tf6=new JTextField(20);
		tf6.setText("����2000-02-02");
		//�ı��������ʾ��
		tf6.addFocusListener(new FocusListener(){

	    	public void focusGained(FocusEvent e) {
			tf6.setText("");
	    	}
	    	public void focusLost(FocusEvent e) {}
	    });
		
		jp.add(label);
		ct.add(jp,"North");
		
		jp1.add(new JLabel("   ����",SwingConstants.CENTER));
		jp2.add(tf1);
		jp1.add(new JLabel("   ɱ����",SwingConstants.CENTER));
		jp2.add(tf2);
		jp1.add(new JLabel("   ǹе���",SwingConstants.CENTER));
		jp2.add(tf3);
		jp1.add(new JLabel("   ǹе���",SwingConstants.CENTER));
		jp2.add(tf4);
		jp1.add(new JLabel("   �ֳ�����",SwingConstants.CENTER));
		jp2.add(tf5);
		jp1.add(new JLabel("   ��ʱӵ��",SwingConstants.CENTER));
		jp2.add(tf6);
		
		jp3.add(btn1);
		jp3.add(btn2);
		
		ct.add(jp1,"West");
		ct.add(jp2,"East");
		ct.add(jp3,"South");
		//���ò����Ա��
		f1.setResizable(false);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;
		int y=screen.height;
		f1.setSize(350,330);
		int xcenter=(x-350)/2;
		int ycenter=(y-330)/2;
		f1.setLocation(xcenter, ycenter);
		f1.setVisible(true);
		 //f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		
//		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//
//	    String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=SIMS";
//
//	    String userName="sa";
//
//	    String userPwd="666666";
//
//	    try
//
//	    {
//
//	     Class.forName(driverName);
//
//	     Connection ConnectiondbConn = DriverManager.getConnection(dbURL,userName,userPwd);
//
//	      System.out.println("�������ݿ�ɹ�");
//
//	    }
//
//	    catch(Exception e)
//
//	    {
//
//	     e.printStackTrace();
//
//	     System.out.print("����ʧ��");
//
//	    }    
//	    
// }
	}
	public void insert(){
			if(tf1.getText().equals("")||tf2.getText().equals("")||tf3.getText().equals("")||
				tf4.getText().equals("")||tf5.getText().equals("")||tf6.getText().equals("")){
				JOptionPane.showMessageDialog(f1,"����д������Ϣ");
				return;
			}
			try{
				Class.forName(driverName);
			   }catch(ClassNotFoundException e){
				   		System.out.print("������������ʧ��");
			   }
			
			String conURL="jdbc:sqlserver://localhost:1433; DatabaseName=GunManger";
			   try{
				   Connection ConnectiondbConn = DriverManager.getConnection(dbURL,userName,userPwd);
				    Statement st=ConnectiondbConn.createStatement();
				    
					String s="insert into GunManger  values('"+tf1.getText()+"','"+tf2.getText()+"','"+
					tf3.getText()+"','"+tf4.getText()+"','"+tf5.getText()+"','"+tf6.getText()+"');";

					String query="select * from GunManger where Gnumber='"+tf3.getText().trim()+"'";
					ResultSet res=st.executeQuery(query);
					if(res.next()){
						JOptionPane.showMessageDialog(f1,"ѧ����Ϣ�Ѿ����ڣ�");
						ConnectiondbConn.close(); 
						tf3.setText("");
						 
					}else if(tf6.getText().length()!=10){
						JOptionPane.showMessageDialog(f1,"��ѧʱ���ʽ����!");
						ConnectiondbConn.close();
						tf6.setText("");
						
					}
					int insert=st.executeUpdate(s);
					if(insert==1){
						JOptionPane.showMessageDialog(f1,"¼����Ϣ�ɹ���");
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
						tf4.setText("");
						tf5.setText("");
						tf6.setText("");
					}
			   }catch(SQLException e){
				   System.out.println("������룺"+e.getErrorCode());
				   System.out.println("������Ϣ��"+e.getMessage());
			   }
		}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("ȷ��")){
			//JOptionPane.showMessageDialog(null,"���û����ͻ");
			insert();
		}
		else if(cmd.equals("ȡ��")){
			f1.hide();
		}
	}
	 
	public Container getCt()
	{
		return ct;
	}
	public void setCt(Container ct)
	{
		this.ct = ct;
	}	
}