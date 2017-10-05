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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
 

 public class Query implements ActionListener
{
	 
	String sql =null;
	int a;
	String info;
    JFrame f3;
   // Container cp;
    JPanel jp1,jp2,jp3,jp4,jp,jpwest;
    JButton btn1,btn2;//确定，取消
    JLabel label,label2;//标签，请输入姓名（label）
    JTextField tf,tf1,tf2,tf3,tf4,tf5,tf6;//文本框
    
    String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
     String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=GunManger";
   	    String userName="sa";
    	    String userPwd="666666";
     
    Query(int a){
    	 
		    this.a=a;
    	f3=new JFrame();
    	Container cp=f3.getContentPane();//初始化面板，按钮，标签，文本框
    	jp=new JPanel();
    	jp1=new JPanel();
    	jp2=new JPanel(new GridLayout(6,1));
    	jp3=new JPanel();
    	jp4=new JPanel(new GridLayout(6,1));
    	jpwest=new JPanel();
    	
    	btn1=new JButton("确定");
    	btn2=new JButton("取消");
    	
    	label=new JLabel("<html><font color=#CC00FF size='4'>请输入姓名：" +
    			"</font>",SwingConstants.CENTER);
    	label2=new JLabel("<html><font color=#CC00FF size='4'>枪械编号：" +
    			"</font>",SwingConstants.CENTER);
    	
    	label.setForeground(Color.gray);
    	tf=new JTextField(20);
        //------------------------------------------------
    	tf1=new JTextField(20);
    	tf2=new JTextField(20);
    	tf3=new JTextField(20);
    	tf4=new JTextField(20);
    	tf5=new JTextField(20);
        tf6=new JTextField(20);
        
        //布局，添加控件
        if(a==0){
        jp.add(label);
        jp.add(tf);
        }else if(a==1){
        	jp.add(label2);
            jp.add(tf);
        }
        cp.add(jp,"North");
        jp4.add(new JLabel("  名称",SwingConstants.CENTER));
        jp2.add(tf1);
        
        jp4.add(new JLabel("   杀伤力",SwingConstants.CENTER));
        jp2.add(tf2);
        
        jp4.add(new JLabel("   枪械编号",SwingConstants.CENTER));
        jp2.add(tf3);
        
        jp4.add(new JLabel("   枪械类别",SwingConstants.CENTER));
        jp2.add(tf4);
        
        jp4.add(new JLabel("   现持有者",SwingConstants.CENTER));
        jp2.add(tf5);
        
        jp4.add(new JLabel("   何时拥有",SwingConstants.CENTER));
        jp2.add(tf6);

        jp3.add(btn1);
        jp3.add(btn2);
        
        cp.add(jp4,"West");
        cp.add(jp2,"East");
        cp.add(jp3,"South");
        
        cp.add(jp1);
      //设置不可以变大
        f3.setResizable(false);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screen=kit.getScreenSize();
        int x=screen.width; //显示窗口宽度
        int y=screen.height;//窗口高度
        
        f3.setSize(350,330);
        int xcenter=(x-350)/2;
        int ycenter=(y-330)/2;
        
        f3.setLocation(xcenter,ycenter);
        f3.setVisible(true);
        //f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        
        /*f3.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	}
	);*/
        
        
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
    public void showRecord(){
    	try{
    		Class.forName(driverName);
		   }catch(ClassNotFoundException e){
			   		System.out.print("加载驱动程序失败");
		   }
		   String conURL="jdbc:sqlserver://localhost:1433; DatabaseName=GunManger";
		   try{
			   Connection ConnectiondbConn = DriverManager.getConnection(dbURL,userName,userPwd);
			    Statement st=ConnectiondbConn.createStatement();
			    
			    info=tf.getText().trim();
			    if(a==0){
			    	sql="select * from GunManger  where Gnumber ='"+info +"'";
			    }else if(a==1){
			    	sql="select * from GunManger  where Gnumber ='"+info +"'";
			    }
				ResultSet res=st.executeQuery(sql);
				if(res.next()){
					String sName=res.getString(1);
					String sSex=res.getString(2);
					String sNumber=res.getString(3);
					String sProfession=res.getString(4);
					String sClass=res.getString(5);
					String sDate=res.getString(6);
					
					tf1.setText(sName);
					tf2.setText(sSex);
					tf3.setText(sNumber);
					tf4.setText(sProfession);
					tf5.setText(sClass);
					tf6.setText(sDate);
				}
                else
	             {JOptionPane.showMessageDialog(null,"您输入的学生信息不存在，请重新输入",
					 "输入错误", JOptionPane.YES_NO_OPTION);
	             
				 }
				ConnectiondbConn.close();


		   }catch(SQLException e){
			   System.out.println("错误代码："+e.getErrorCode());
			   System.out.println("错误内容："+e.getMessage());
		   }
		      tf1.setEditable(false);
			  tf2.setEditable(false); 
			  tf3.setEditable(false);	
			  tf4.setEditable(false);
			  tf5.setEditable(false);
			  tf6.setEditable(false);
     }
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
    	String cmd=e.getActionCommand();
    	if(cmd.equals("确定")){
    		showRecord();
    		tf.setText("");
    	}else if(cmd.equals("取消")){
    		f3.hide();
    	}
    }
    
	 
}