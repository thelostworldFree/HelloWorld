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
		
		b1=new JButton("��ѯ������Ϣ");
		b2=new JButton("�޸�(����)��Ϣ");
		b3=new JButton("�޸ĸ�������");
		bar=new JMenuBar();
		
		this.userName=userName;
		bar.add(b1);
		bar.add(b2);
		bar.add(b3);
		
		JPanel j=new JPanel();
		j.add(bar,"Center");
		
		ct.add(j,"North");
		// ���ñ߿�
		jp1.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.blue, 2), null, TitledBorder.CENTER,
				TitledBorder.TOP));
		jp1.setLayout(new BorderLayout());

		JLabel label1 = new JLabel("��ӭʹ��ѧ����Ϣ����ϵͳ",SwingConstants.CENTER);
		jp1.add(label1);

		JScrollPane scrollpane = new JScrollPane(jp1);
		 ct.add(scrollpane,"Center");
		 setTitle("��ӭ��½" + "--" + "���: majie" + userName);
		 
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
		if(cmd.equals("��ѯ������Ϣ")){
			new Query(0);
		}else if(cmd.equals("�޸�(����)��Ϣ")){
			new UpdateInfo(userName);
		}else if(cmd.equals("�޸ĸ�������")){
			new UpdateCode();
		}
	}
	public static void main(String[] args){
		 new Ustudent("");
	 }
}