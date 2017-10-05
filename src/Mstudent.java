

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
	 JButton liulan = new JButton("���ǹе��Ϣ");
	JButton Query = new JButton("��ѯǹе��Ϣ");
	JButton add = new JButton("���ǹе��Ϣ");
	JButton delete = new JButton("ɾ��ǹе��Ϣ");
	
	JMenuBar mb = new JMenuBar();// �˵���
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

		// ���ñ߿�
		jp.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createLineBorder(Color.blue, 2), null, TitledBorder.CENTER,
				TitledBorder.TOP));
		jp.setLayout(new BorderLayout());

		JLabel label1 = new JLabel("��ӭʹ��ǹе����ϵͳ",SwingConstants.CENTER);
		label1.setFont(new Font("΢���ź�", 1, 40));
		label1.setForeground(new Color(15,250,55));//new color��255��255��255�� or Color.green��ɫRGB�ı�ʾ
		jp.add(label1, SwingConstants.CENTER);

		JScrollPane scrollpane = new JScrollPane(jp);
		 cp.add(scrollpane,"Center");
		setTitle("��ӭ��½--ǹе����:" + userName + "      �{��ߩרTһ��");
		//���ò����Ա��
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width; /* ȡ����ʾ�����ڵĿ�� */
		int y = screen.height; /* ȡ����ʾ�����ڵĸ߶� */

		setSize(600, 500);
		int xcenter = (x - 600) / 2;
		int ycenter = (y - 500) / 2;
		setLocation(xcenter, ycenter);/* ��ʾ�ڴ������� */
        
		setVisible(true);
		 
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ע��������
		Query.addActionListener(this);
		add.addActionListener(this);
		delete.addActionListener(this);
		liulan.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if (cmd.equals("��ѯǹе��Ϣ"))
		{
			
			new Query(a);
		}
		if (cmd.equals("���ǹе��Ϣ"))
		{
			
			new Add();
		}
		if (cmd.equals("ɾ��ǹе��Ϣ"))
		{
		 
			new Delete();
		}if (cmd.equals("���ǹе��Ϣ"))
		{
			 new Liulan();
		}
		
	}

 public static void main(String[] args){
	 new Mstudent("");
 }

}