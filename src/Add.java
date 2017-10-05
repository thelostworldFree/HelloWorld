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
		ct=f1.getContentPane();//初始化面板
		jp=new JPanel();
		jp1=new JPanel(new GridLayout(6,1));
		jp2=new JPanel(new GridLayout(6,1));
		jp3=new JPanel();
		btn1=new JButton("确定");
		btn2=new JButton("取消");
		label=new JLabel("<html><font color=#CC00FF size='4'>添加枪械信息</font>",SwingConstants.CENTER);
		label.setForeground(Color.gray);
		
		tf1=new JTextField(20);
		tf2=new JTextField(20);
		tf3=new JTextField(20);
		tf4=new JTextField(20);
		tf5=new JTextField(20);
		tf6=new JTextField(20);
		tf6.setText("例：2000-02-02");
		//文本框加入提示语
		tf6.addFocusListener(new FocusListener(){

	    	public void focusGained(FocusEvent e) {
			tf6.setText("");
	    	}
	    	public void focusLost(FocusEvent e) {}
	    });
		
		jp.add(label);
		ct.add(jp,"North");
		
		jp1.add(new JLabel("   名称",SwingConstants.CENTER));
		jp2.add(tf1);
		jp1.add(new JLabel("   杀伤力",SwingConstants.CENTER));
		jp2.add(tf2);
		jp1.add(new JLabel("   枪械编号",SwingConstants.CENTER));
		jp2.add(tf3);
		jp1.add(new JLabel("   枪械类别",SwingConstants.CENTER));
		jp2.add(tf4);
		jp1.add(new JLabel("   现持有者",SwingConstants.CENTER));
		jp2.add(tf5);
		jp1.add(new JLabel("   何时拥有",SwingConstants.CENTER));
		jp2.add(tf6);
		
		jp3.add(btn1);
		jp3.add(btn2);
		
		ct.add(jp1,"West");
		ct.add(jp2,"East");
		ct.add(jp3,"South");
		//设置不可以变大
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
//	      System.out.println("连接数据库成功");
//
//	    }
//
//	    catch(Exception e)
//
//	    {
//
//	     e.printStackTrace();
//
//	     System.out.print("连接失败");
//
//	    }    
//	    
// }
	}
	public void insert(){
			if(tf1.getText().equals("")||tf2.getText().equals("")||tf3.getText().equals("")||
				tf4.getText().equals("")||tf5.getText().equals("")||tf6.getText().equals("")){
				JOptionPane.showMessageDialog(f1,"请填写完整信息");
				return;
			}
			try{
				Class.forName(driverName);
			   }catch(ClassNotFoundException e){
				   		System.out.print("加载驱动程序失败");
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
						JOptionPane.showMessageDialog(f1,"学号信息已经存在！");
						ConnectiondbConn.close(); 
						tf3.setText("");
						 
					}else if(tf6.getText().length()!=10){
						JOptionPane.showMessageDialog(f1,"入学时间格式有误!");
						ConnectiondbConn.close();
						tf6.setText("");
						
					}
					int insert=st.executeUpdate(s);
					if(insert==1){
						JOptionPane.showMessageDialog(f1,"录入信息成功！");
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
						tf4.setText("");
						tf5.setText("");
						tf6.setText("");
					}
			   }catch(SQLException e){
				   System.out.println("错误代码："+e.getErrorCode());
				   System.out.println("错误信息："+e.getMessage());
			   }
		}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("确定")){
			//JOptionPane.showMessageDialog(null,"与用户表冲突");
			insert();
		}
		else if(cmd.equals("取消")){
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