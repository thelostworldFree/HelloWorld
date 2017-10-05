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

public class UpdateInfo implements ActionListener
{
	JFrame f5;
	Container ct;
	JPanel jp, jp1, jp2, jp3;
	JButton btn1, btn2;
	JLabel label;
	JTextField tf1, tf2, tf3, tf4, tf5;
	String userName;

	public UpdateInfo(String username)
	{
		this.userName = username;
		f5 = new JFrame();
		ct = f5.getContentPane();// ��ʼ�����
		jp = new JPanel();
		jp1 = new JPanel(new GridLayout(6, 1));
		jp2 = new JPanel(new GridLayout(6, 1));
		jp3 = new JPanel();
		btn1 = new JButton("ȷ��");
		btn2 = new JButton("ȡ��");
		label = new JLabel("<html><font color=#CC00FF size='4'>�޸�(����)��Ϣ</font>",
				SwingConstants.CENTER);
		label.setForeground(Color.gray);

		tf1 = new JTextField(20);
		tf2 = new JTextField(20);
		tf3 = new JTextField(20);
		tf4 = new JTextField(20);
		tf5 = new JTextField(20);
 
		tf5.setText("����2000-02-02");
		// �ı��������ʾ��
		tf5.addFocusListener(new FocusListener()
		{

			public void focusGained(FocusEvent e)
			{
				tf5.setText("");
			}

			public void focusLost(FocusEvent e)
			{
			}
		});

		jp.add(label);
		ct.add(jp, "North");

		jp1.add(new JLabel("����", SwingConstants.CENTER));
		jp2.add(tf1);
		jp1.add(new JLabel("�Ա�", SwingConstants.CENTER));
		jp2.add(tf2);
		jp1.add(new JLabel("רҵ", SwingConstants.CENTER));
		jp2.add(tf3);
		jp1.add(new JLabel("�༶", SwingConstants.CENTER));
		jp2.add(tf4);
		jp1.add(new JLabel("��ѧʱ��", SwingConstants.CENTER));
		jp2.add(tf5);

		jp3.add(btn1);
		jp3.add(btn2);

		ct.add(jp1, "West");
		ct.add(jp2, "East");
		ct.add(jp3, "South");

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int x = screen.width;
		int y = screen.height;
		f5.setSize(350, 330);
		int xcenter = (x - 350) / 2;
		int ycenter = (y - 330) / 2;
		f5.setLocation(xcenter, ycenter);
		f5.setVisible(true);
		f5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn1.addActionListener(this);
		btn2.addActionListener(this);
	}

	public void update()
	{
		if (tf1.getText().equals("") || tf2.getText().equals("")
				|| tf3.getText().equals("")
				|| tf4.getText().equals("") || tf5.getText().equals(""))
		{
			JOptionPane.showMessageDialog(f5, "����д������Ϣ");
			return;
		}
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e)
		{
			System.out.print("������������ʧ��");
		}
		String conURL = "jdbc:sqlserver://localhost:1433; DatabaseName=SIMS";
		try
		{
			Connection con = DriverManager.getConnection(conURL, "my", "123456");
			Statement st = con.createStatement();
			
			String query="select * from studentinfo where Snumber='"+userName+"'";
			String up = "update studentinfo set Sname='"+tf1.getText()+"',"
			+"Ssex='" + tf2.getText()+ "',Sprofession='"+ tf3.getText() + "',"+
			"Sclass='" + tf4.getText()+ "',Sdate='" + tf5.getText() + "' where Snumber='"+userName+"'";
			ResultSet res=st.executeQuery(query);
			if (tf5.getText().length() != 10)
			{
				JOptionPane.showMessageDialog(f5, "�޸���Ϣ�ɹ�");
				 con.close();
				tf5.setText("");

			}else if(res.next()){
		    	int update = st.executeUpdate(up);
				if (update == 1)
				{
					JOptionPane.showMessageDialog(f5, "�޸���Ϣ�ɹ���");
					tf1.setText("");
					tf2.setText("");		 
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
				}
	        }else{
	        	String insert="insert into studentinfo  values('"+tf1.getText()+"','"+tf2.getText()+"','"+userName+"','"+tf3.getText()+"','"+tf4.getText()+"','"+tf5.getText()+"');";
	        	int ins=st.executeUpdate(insert);
	        	if(ins==1){
	        		JOptionPane.showMessageDialog(f5, "������Ϣ�ɹ�");
	        		tf1.setText("");
					tf2.setText("");		 
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
	        	}
	        }
			con.close();
		} catch (SQLException e)
		{
			System.out.println("������룺" + e.getErrorCode());
			System.out.println("������Ϣ��" + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		if(cmd.equals("ȷ��"))
		{
		 
			update();
		} else if(cmd.equals("ȡ��"))
		{
			f5.hide();
		}
	}
	public static void main(String[] args){
		new UpdateInfo("");
	}
}