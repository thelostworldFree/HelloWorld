import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UpdateCode extends JFrame implements ActionListener
{
	 
	JFrame f4;
	Container ct;
	JPanel jp1,jp2,jp3,jp4;
	JLabel label,label2,label3;
	JTextField name;
	JPasswordField tf1,tf2,tf3;
	JButton btn1,btn2;
    String Sname;
    
	 
	
	UpdateCode(){
		 
		f4=new JFrame();
		ct=f4.getContentPane();
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel(new GridLayout(6,1));
		jp4=new JPanel(new GridLayout(6,1));
		btn1=new JButton("ȷ��");
		btn2=new JButton("ȡ��");
		label=new JLabel("<html><font color=#CC00FF size='4'>�޸�����</font>",SwingConstants.CENTER);
		label.setForeground(Color.blue);
		//label.setFont(new Font("BOLD",Font.BOLD,15));
		name=new JTextField(20);
		tf1=new JPasswordField(20);
		tf2=new JPasswordField(20);
		tf3=new JPasswordField(20);
		jp1.add(btn1);
		jp1.add(btn2);
		jp2.add(label);
		
		ct.add(jp2,"North");
		jp3.add(new JLabel("�û���",SwingConstants.RIGHT));
		jp4.add(name);
		
		jp3.add(new JLabel("ԭ����",SwingConstants.RIGHT));
		jp4.add(tf1);
		
		jp3.add(new JLabel("������",SwingConstants.RIGHT));
		jp4.add(tf2);
		
		jp3.add(new JLabel("ȷ������",SwingConstants.RIGHT));
		jp4.add(tf3);
		jp4.add(new JLabel());
		
		ct.add(jp3,"West");
		ct.add(jp4,"East");
		ct.add(jp1,"South");
		
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;
		int y=screen.height;
		f4.setSize(350,330);
		int xcen=(x-350)/2;
		int ycen=(y-330)/2;
		f4.setLocation(xcen, ycen);
		f4.setVisible(true);
		//f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}
	@SuppressWarnings("deprecation")
	public void Update(){
		try{
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   }catch(ClassNotFoundException e){
			   		System.out.print("������������ʧ��");
		   }
		   String conURL="jdbc:sqlserver://localhost:1433; DatabaseName=SIMS";
		   try{
			   Connection con=DriverManager.getConnection(conURL,"my","123456");
			    Statement st=con.createStatement();
			    
			    String username=name.getText().trim();
			    String Mima=tf1.getText().trim();
			    String query="select * from userinfo where name='"+username+"' and mima='"+Mima+"'";
			    ResultSet res=st.executeQuery(query);
			    if(res.next()){
			    	String newmima=tf2.getText().trim();
			    	String s=null;
			    		s="update userinfo set mima='"+newmima+"' where name='"+username +"'";
			    	 
			    	int updatemima=st.executeUpdate(s);
			    	if(updatemima==1){
			    		JOptionPane.showMessageDialog(f4, "�޸�����ɹ�");
			    	}
			    	con.close();
			    	f4.repaint();
			    }else{
			    	JOptionPane.showMessageDialog(f4,"���û�������");
			    }
			    name.setText("");
			    tf1.setText("");
			    tf2.setText("");
			    tf3.setText("");
		   }catch(SQLException e){
			   System.out.println("�������:"+e.getErrorCode());
			   System.out.println("������Ϣ:"+e.getMessage());
		   }
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("ȷ��")){
			if(name.getText().equals("")||tf1.getText().equals("")||tf2.getText().equals("")||tf3.getText().equals(""))
	          {
					JOptionPane.showMessageDialog(null,"����д�û���������Ϣ","��ʾ", 
				          JOptionPane.YES_NO_OPTION);
	           return;
	          }
			if(tf2.getText().trim().equals(tf3.getText().trim())){
					Update();
			}else{
				JOptionPane.showMessageDialog(f4,"ȷ�������������벻ƥ��");
			}
		}else if(cmd.equals("ȡ��")){
			f4.hide();
		}
	}
 
}