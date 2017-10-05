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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
 
 

public class Liulan implements ActionListener
{
	JFrame f4;
	Container ct;
	JPanel jp1,jp2,jp3,jp4;
	JLabel label;
	JButton btn1,btn2;
	JTable table;
	String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=GunManger";
    String userPwd="666666";
	String userName="sa";
	Object[][] ar=new Object[80][6];
	Object[] columnName={"名称","杀伤力","枪械编号","枪械类别","持有者","何时拥有"};
	
	Liulan(){
		f4=new JFrame();
		ct=f4.getContentPane();
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		btn1=new JButton("点击浏览");
		btn2=new JButton(" 取消  ");
		label=new JLabel("<html><font color=#00FF22 size='4'>枪械信息浏览<font>",SwingConstants.CENTER);
		label.setBackground(Color.blue);
		table=new JTable(ar, columnName);
		JScrollPane scroll=new JScrollPane(table);
		
		jp1.add(label);
		jp2.add(btn1);
		jp2.add(btn2);
		jp3.add(scroll);
		
		ct.add(jp1,"North");
		ct.add(jp3,"Center");
		ct.add(jp2,"South");
		
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screen=kit.getScreenSize();
		int x=screen.width;
		int y=screen.height;
		f4.setSize(500,430);
		int xcen=(x-500)/2;
		int ycen=(y-430)/2;
		f4.setLocation(xcen,ycen);
		f4.setVisible(true);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}
	int i=0;
	public void select(){
		while(i>=0){
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
			    
			    String s="select * from GunManger";
			    ResultSet res=st.executeQuery(s);
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
			    f4.repaint();
			    ConnectiondbConn.close();
		   }catch(SQLException e){
				System.out.println("错误代码："+e.getErrorCode());
				System.out.println("错误信息："+e.getMessage());
			}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cmd=e.getActionCommand();
		if(cmd.equals("点击浏览")){
			select();
		}else if(cmd.equals(" 取消  ")){
			f4.hide();
		}
		
	}
	 
}