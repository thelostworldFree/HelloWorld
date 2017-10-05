

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Mstudent extends JFrame implements ActionListener
{
	 int a=1;
	 JButton liulan = new JButton("浏览枪械信息");
	JButton Query = new JButton("查询枪械信息");
	JButton add = new JButton("添加枪械信息");
	JButton delete = new JButton("删除枪械信息");
	
	JMenuBar mb = new JMenuBar();// 菜单栏
	JPanel jp = new JPanel();
	 Container cp = getContentPane();
	String userName;

	Mstudent()
	{
	}

	Mstudent(String userName)
	{
		
		this.userName = userName;
		mb.add(liulan); 
		mb.add(Query);
		mb.add(add);
		mb.add(delete);
		
        JPanel j=new JPanel();
        j.add(mb,"Center");
		cp.add(j, "North");
		
		ImageIcon icon = new ImageIcon("img/main.png");
    	JLabel bg = new JLabel(icon);
    	bg.setBounds(0, 0, 600, 500);
    	jp.add(bg);

		// 设置边框
		jp.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.blue, 2), null, TitledBorder.CENTER,
				TitledBorder.TOP));
		jp.setLayout(new BorderLayout());

		JLabel label1 = new JLabel("欢迎使用枪械管理系统",SwingConstants.CENTER);
		label1.setFont(new Font("微软雅黑", 1, 40));
		label1.setForeground(new Color(15,250,55));//new color（255，255，255） or Color.green颜色RGB的表示
		jp.add(label1, SwingConstants.CENTER);

		JScrollPane scrollpane = new JScrollPane(jp);
		 cp.add(scrollpane,"Center");
		setTitle("欢迎登陆--枪械基地:" + userName + "      ▄︻┻┳═一∞");
		//设置不可以变大
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width; /* 取得显示器窗口的宽度 */
		int y = screen.height; /* 取得显示器窗口的高度 */

		setSize(600, 500);
		int xcenter = (x - 600) / 2;
		int ycenter = (y - 500) / 2;
		setLocation(xcenter, ycenter);/* 显示在窗口中央 */
        
		setVisible(true);
		 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 注册临听器
		Query.addActionListener(this);
		add.addActionListener(this);
		delete.addActionListener(this);
		liulan.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if (cmd.equals("查询枪械信息"))
		{
			
			new Query(a);
		}
		if (cmd.equals("添加枪械信息"))
		{
			
			new Add();
		}
		if (cmd.equals("删除枪械信息"))
		{
		 
			new Delete();
		}if (cmd.equals("浏览枪械信息"))
		{
			 new Liulan();
		}
		
	}

 public static void main(String[] args){
	 new Mstudent("");
 }

}