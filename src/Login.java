import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Login extends JFrame{

	private static final long serialVersionUID = 2191465933249311362L;
	JTextField f1;
    JTextField f2;
	JButton b1;
	JButton b2;
	String power;//表示权限
    String imgePath = "";
    JPanel p5;
	public Window login;
    
	Login(){
				Container cp=getContentPane();
		    	ImageIcon icon = new ImageIcon("img/login.png");
		    	JLabel bg = new JLabel(icon);
		    	bg.setBounds(0, 0, 360, 250);
		    	cp.add(bg);
		  
				JLabel l1=new JLabel("用户：");
				JLabel l2=new JLabel("密码：");
				l1.setForeground(Color.WHITE);
				l2.setForeground(Color.WHITE);
		
				JPanel p2=new JPanel();
				JPanel p3=new JPanel();
				JPanel p4=new JPanel();
	p5=new JPanel(){
				private static final long serialVersionUID = 692263781688283292L;
				protected  void paintChildren(Graphics g) {
                super.paintChildren(g);
            }
        };
  
			   f1=new JTextField(15);
			      f2=new JPasswordField(15);
			   b1=new JButton("登录");
			   b2=new JButton("重置");

			  p2.add(l1);
			  p2.add(f1);
			  p2.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			  p2.setOpaque(false);
			  p3.add(l2);
			  p3.add(f2);
			  p3.setBorder(new MatteBorder(0,0,0,0,Color.BLACK));
			  p3.setOpaque(false);
			  p4.add(b1);
			  p4.add(b2);
			  p4.setBorder(new MatteBorder(-3,-3,-3,-3,Color.CYAN));
			  p4.setOpaque(false);
			  p5.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
			  p5.add(p2);
			  p5.add(p3);
			  p5.add(p4);
			  p5.setOpaque(false);
			  cp.add(p5, SwingConstants.CENTER);
			
			  b1.addActionListener(new Enter());
			  b2.addActionListener(new ReWrite());
			  addWindowListener(new winClose());
 }

 public static void main(String[] args) {
					  Login log=new Login();
					  log.setTitle("枪械管理    ▄︻┻┳═一∞  ");
					  //设置不可以变大
					  log.setResizable(false);
					  Toolkit kit=Toolkit.getDefaultToolkit();
					  Dimension screen=kit.getScreenSize();
					  int x=screen.width;
					  int y=screen.height;
					  log.setSize(360,250);
					  int xcenter=(x-360)/2;
					  int ycenter=(y-250)/2;
					  log.setLocation(xcenter,ycenter);
					  log.setVisible(true);
  

    }

 class Enter implements ActionListener{
         public void actionPerformed(ActionEvent e){  
              if((f1.getText()).equals("付伟")&&(f2.getText()).equals("123")){
            	    Mstudent frame = new Mstudent("");
            	    dispose();	
              }else if((f1.getText()).equals("1")&&(f2.getText()).equals("123456")){ 
            	    new Login();
            	    Mstudent frame = new Mstudent("");
              }else JOptionPane.showMessageDialog(null, "登录失败，请重新登录！");
      }
 }
 class ReWrite implements ActionListener{
  public void actionPerformed(ActionEvent e){
		    f1.setText("");
		    f2.setText("");
		    f1.requestFocus();
     }
 }
 class winClose extends WindowAdapter{
 public void windowClosing(WindowEvent e){
    (e.getWindow()).dispose();
     System.exit(0);
    }
  }
 
}
