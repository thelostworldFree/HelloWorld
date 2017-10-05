import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
 

 
@SuppressWarnings("serial")
public class Ustudent extends JFrame implements ActionListener
{
	Container ct;
	JPanel jp1,jp2,jp3;
	JMenuBar bar;
	JButton b1,b2,b3;
	String userName;
	Ustudent(){}
	Ustudent(String userName){
		ct=getContentPane();
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		b1=new JButton("查询个人信息");
		b2=new JButton("修改(完善)信息");
		b3=new JButton("修改个人密码");
		bar=new JMenuBar();
		
		this.userName=userName;
		bar.add(b1);
		bar.add(b2);
		bar.add(b3);
		
		JPanel j=new JPanel();
		j.add(bar,"Center");
		
		ct.add(j,"North");
		// 设置边框
		jp1.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.blue, 2), null, TitledBorder.CENTER,
				TitledBorder.TOP));
		jp1.setLayout(new BorderLayout());

		JLabel label1 = new JLabel("欢迎使用学生信息管理系统",SwingConstants.CENTER);
		jp1.add(label1);

		JScrollPane scrollpane = new JScrollPane(jp1);
		 ct.add(scrollpane,"Center");
		 setTitle("欢迎登陆" + "--" + "你好: majie" + userName);
		 
		 Toolkit kit=Toolkit.getDefaultToolkit();
		 Dimension screen=kit.getScreenSize();
		 int x=screen.width;
		 int y=screen.height;
		 setSize(500,400);
		 int xcen=(x-500)/2;
		 int ycen=(y-400)/2;
		 setLocation(xcen,ycen);
		 setVisible(true);
		 b1.addActionListener(this);
		 b2.addActionListener(this);
		 b3.addActionListener(this);
		
	}
	public void actionPerformed(ActionEvent e){
		String cmd=e.getActionCommand();
		if(cmd.equals("查询个人信息")){
			new Query(0);
		}else if(cmd.equals("修改(完善)信息")){
			new UpdateInfo(userName);
		}else if(cmd.equals("修改个人密码")){
			new UpdateCode();
		}
	}
	public static void main(String[] args){
		 new Ustudent("");
	 }
}