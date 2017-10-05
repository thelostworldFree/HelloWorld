import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Delete implements ActionListener
{
	JFrame f2;
	Container ct;
	JPanel jp1,jp2,jp3,jp4;
	JLabel label;     
	JTextField text;
	JTable table;//接收数据库中返回的信息
	JButton btn1,btn2,btn3;
	Object columnName[]={"名称","杀伤力","枪械编号","枪械类别","持有者","何时拥有"};
	Object ar[][]=new Object[80][6];
	
	    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=GunManger";
	    String userName="sa";
	    String userPwd="666666";
	
	Delete(){
		f2=new JFrame();
		ct=f2.getContentPane();
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		label=new JLabel();
		text=new JTextField();
		btn1=new JButton("查询");
		btn2=new JButton("删除");
		btn3=new JButton("取消");
		
		label=new JLabel("<html><font color=#CC00FF size='4'>请输入要删除枪械名称:</font>",SwingConstants.CENTER);
		label.setBackground(Color.blue);
		
		table=new JTable(ar,columnName);
		JScrollPane scroll=new JScrollPane(table);
		
		text=new JTextField(20);
		
		
		jp2.add(btn1);
		jp2.add(btn2);
		jp2.add(btn3);
		
		jp1.add(label);
		jp1.add(text);
		ct.add(jp1,"North");
		jp3.setLayout(new BorderLayout());
		jp3.add(new JLabel("学生枪械如下"));
		jp3.add(scroll);
		
		ct.add(jp2,"South");
		ct.add(jp3,"Center");
		//设置不可以变大
		f2.setResizable(false);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;
		int y=screen.height;
		f2.setSize(450, 400);
		int xcenter=(x-450)/2;
		int ycenter=(y-400)/2;
		f2.setLocation(xcenter, ycenter);
		f2.setVisible(true);
		//f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
	}
	int i=0;
	public void show(String s){
		 while(i>=0)
		{	
		ar[i][0]="";
		ar[i][1]="";
		ar[i][2]="";
		ar[i][3]="";
		ar[i][4]="";
		ar[i][5]="";
		i--;
		}
		i=0; 
		
		try{
			 Class.forName(driverName);
		   }catch(ClassNotFoundException e){
			   		System.out.print("加载驱动程序失败");
		   }
		   String conURL="jdbc:sqlserver://localhost:1433; DatabaseName=GunManger";
		   try{
			   Connection ConnectiondbConn = DriverManager.getConnection(dbURL,userName,userPwd);
			    Statement st=ConnectiondbConn.createStatement();
			    
			    String sql="select * from GunManger where Gname='"+s+"'";
			    ResultSet res=st.executeQuery(sql);
			    /*if(!(res.next())){
			    	 JOptionPane.showMessageDialog(f,"所要查询的信息不存在"); 
			     } */
			    while(res.next()){
			    	String GName=res.getString(1);
			    	String Gpower=res.getString(2);
			    	String GNumber=res.getString(3);
			    	String GProfession=res.getString(4);
			    	String Ghold=res.getString(5);
			    	String GDate=res.getString(6);
			    	 
					ar[i][0]=GName;
					ar[i][1]=Gpower;
					ar[i][2]=GNumber;
					ar[i][3]=GProfession;
					ar[i][4]=Ghold;
					ar[i][5]=GDate;

			    	i++;
			    }
			      
			    
			    f2.repaint();
			    ConnectiondbConn.close();
			    
		}catch(SQLException e){
			System.out.println("错误代码："+e.getErrorCode());
			System.out.println("错误信息："+e.getMessage());
		}
	}
	
//	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//
//    String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=SIMS";
//
//    String userName="sa";
//
//    String userPwd="666666";
//
//    try
//
//    {
//
//     Class.forName(driverName);
//
//     Connection ConnectiondbConn = DriverManager.getConnection(dbURL,userName,userPwd);
//
//      System.out.println("连接数据库成功");
//
//    }
//
//    catch(Exception e)
//
//    {
//
//     e.printStackTrace();
//
//     System.out.print("连接失败");
//
//    }    
//    
//}
	public void delete(int line){
		try{
			Class.forName(driverName);
		   }catch(ClassNotFoundException e){
			   		System.out.print("加载驱动程序失败");
		   }
		   String conURL="jdbc:sqlserver://localhost:1433; DatabaseName=GunManger";
		   try{
			   Connection ConnectiondbConn = DriverManager.getConnection(dbURL,userName,userPwd);
			    Statement st=ConnectiondbConn.createStatement();
			    String number=(String)(ar[line][2]);
			    String sql="delete from GunManger  where Gnumber ='"+number+"'";
			    int del=st.executeUpdate(sql);
			    if(del==1){
			    	ar[line][0]="";
					ar[line][1]="";
					ar[line][2]="";
					ar[line][3]="";
					ar[line][4]="";
					ar[line][5]="";
			    	JOptionPane.showMessageDialog(null,"删除成功！",
							 "信息", JOptionPane.YES_NO_OPTION);
			    }
			    ConnectiondbConn.close();
			    f2.repaint();
		   }catch(SQLException e){
			    System.out.println("错误代码："+e.getErrorCode());
				System.out.println("错误信息："+e.getMessage());
		   }
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("查询")){
			String qu=text.getText().trim();
			show(qu);
		}
		if(cmd.equals("删除")){
			int de=table.getSelectedRow();
			
			
			if(de==-1){
				JOptionPane.showMessageDialog(null,"请选定要删除的行",
						 "错误信息", JOptionPane.YES_NO_OPTION);
			}
				else{
					delete(de);
				
			}
		}
		if(cmd.equals("取消")){
			f2.hide();
		}
	}
	 
}