import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Vector;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;

class roomm 
{
   int att,quan,no;
   String name;
}
class playerlist extends JFrame
{
    int rank,popu[];     
	
	public playerlist()
        {
		popu=new int[5];
		
	       try
	       {
	    	   
	       }catch(IOException ee){ee.printStackTrace();}
        }
}

class connect                         //连接
{
	static Boolean goon=true;
	InetAddress ia;
    Socket so;
    String ip="203.208.39.99";
    static PrintWriter pw;
    static BufferedReader br;
    public connect()
    {
    	try
    	{
    	ia=InetAddress.getByName(ip);
    	so=new Socket(ia,3000);
    	pw=new PrintWriter(new OutputStreamWriter(so.getOutputStream()));
    	br=new BufferedReader(new InputStreamReader(so.getInputStream()));
        } catch(IOException ee){ee.printStackTrace();}
    	}
}

class an extends JButton               //游戏中的确定、取消按钮
{
	public an(String a)
	{
		super(a);
	Dimension dd = getPreferredSize();
    dd.width = dd.height = Math.max(dd.width,dd.height);
    setPreferredSize(dd);
    this.setContentAreaFilled(false);
	}	
	protected void paintComponent(Graphics g) 
	{
	    if (getModel().isArmed()) 
	    {	
	    	 g.setColor(Color.lightGray);
	      
	    } else 
	    {
	      g.setColor(getBackground());
	    }
	    g.fillOval(0, 0, getSize().width-1, getSize().height-1);
	    super.paintComponent(g);
	  }
	
	  protected void paintBorder(Graphics g) 
	  {
	    g.setColor(getForeground());
	    g.drawOval(0, 0, getSize().width-1,getSize().height-1);
	  }
	  
	
      Shape ss;
      public boolean contains(int x, int y) 
      {
    	    if (ss == null || 
    	      !ss.getBounds().equals(getBounds())) {
    	      ss = new Ellipse2D.Float(0,0,getWidth(),getHeight());
    	    }
    	    return ss.contains(x, y);
    	  }


	
}
class card extends JLabel                 //卡片
{ 
	Boolean clicked=false;
	int blood;
	String name;
	enum type{wujiang,jinlang,zhuangbei,qunxiong};
	enum nationality{wei,shu,wu,no};
	public card(int i)
	{
		
	}
	
}

class gametable extends JFrame implements MouseListener                       //游戏面板
{
	Connection con;
	Statement st;
	 PreparedStatement ps;
	 String url="jdbc:mysql://localhost:3306/football";
		String user="root";
		String code="69767425";
	card jl1;
	Boolean[] boo=new Boolean[3];
	an a1;
	Vector v;
	public gametable()

	{
		re.stage=2;
		
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,user,code);
		st=con.createStatement();
		}catch(SQLException ee){ee.printStackTrace();} catch(ClassNotFoundException ee){ee.printStackTrace();}
		this.setSize(400, 400);
		this.setTitle("the gametable");
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  v=new Vector();
	   jl1=new card(1);
	   jl1.addMouseListener(this);
	   jl1.setIcon(new ImageIcon("http://t1.gstatic.com/images?q=tbn:uznFpHcB9Sb9uM:http://newsimg.bbc.co.uk/media/images/42416000/jpg/_42416350_arsenal416.jpg"));
	   jl1.setBounds(200, 200, 70, 100);
	
	   a1=new an(" ENSURE ");a1.setBounds(300, 250, 30, 30);
	   a1.addActionListener(new ActionListener()
	   {
		 public void actionPerformed(ActionEvent ee)
		 {
			 
		 }
	   }
	   );
		Container cc=getContentPane();	
		cc.setLayout(null);
		cc.add(a1);cc.add(jl1);
		
		while(connect.goon && re.stage==2) 
		{
			try
			{
				String mes=connect.br.readLine();
				String[] m=mes.split(",");
				if(m[0]=="getc"){}
				else if(m[0]=="howtodo"){}
		    }catch(IOException ee){ee.printStackTrace();}
		}	
	}
	   public void mouseClicked(MouseEvent ee)
	   {
		   card c=(card)ee.getSource();
		   if(!c.clicked)
		   {
		   c.setLocation(c.getX(), c.getY()-15);
		   c.clicked=true;
		   }
		   else if(c.clicked)
		   {
			   c.setLocation(c.getX(), c.getY()+15);
			   c.clicked=false;
		   }
	   }
	   public void mouseReleased(MouseEvent ee)
	   {}
	   public void mouseEntered(MouseEvent ee)
	   {}
	   public void mouseExited(MouseEvent ee)
	   {}
	   public void mousePressed(MouseEvent ee)
	   {
	   }
		   
}

class vslist extends JFrame implements MouseListener                             //房间列表
{
     
     JList jlt;
     JScrollPane jsp;
     JLabel jl;
     Vector v;
     an create,exit;
	public vslist() throws UnknownHostException,IOException

     {
		
    	connect.goon=true;re.stage=1;
		v=new Vector();
    	v.add(vslist.adjust("1","ad","4/5"));
    	v.add(vslist.adjust("2","fdsfsdfdsfds","4/5"));
		jl=new JLabel("roomnum  | roomname            |   attendeces");
    	 jlt=new JList();
    	 jlt.setListData(v);
    	 jlt.addMouseListener(this);
    	 create=new an(" create ");exit=new an(" exit ");
    	 create.setBounds(280, 140, 40, 40); exit.setBounds(3400, 140, 40, 40);
    	 create.addActionListener(new ActionListener()
    	 {
    		 public void actionPerformed(ActionEvent ee)
    		 {
    			                                                                          //创建房间按钮
    		 }
    	 });
    	 jsp=new JScrollPane(jlt);
    	 jsp.setPreferredSize(new Dimension(100,100));
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 this.setSize(new Dimension(400,200));
    	 this.setTitle("对战表");
    	 Container cc=getContentPane();cc.setLayout(null);
    	 cc.add(jl,"North");cc.add(jsp,"Center");
    	 cc.add(create);cc.add(exit);
    	 while(connect.goon && re.stage==1)
    		{
    			String mes=connect.br.readLine();
    			String[] m=mes.split(",");
    			if(m[0]=="+"){v.add(vslist.adjust(m[1], m[2],m[3] ));}
    			else if(m[0]=="-") {v.remove(Integer.parseInt(m[1]));}
    			jlt.setListData(v);
    		}
     }
	
	 public void mouseExited(MouseEvent ee){}
	 public void mousePressed(MouseEvent ee){}
	 public void mouseClicked(MouseEvent ee)
	 {
		String space=" ";
		for(int c=0;c<22;c++)
		{
			space=space+" ";
		}
		 if(ee.getClickCount()==2)
		{
		    String a=(String)jlt.getSelectedValue();
		    String[] b=a.split(space);
		    
			 connect.pw.print(b[0]);                       //start from here
			this.setVisible(false);
		    new gametable().setVisible(true);	
		}
		
	}
	 public void mouseEntered(MouseEvent ee){}
	 public void mouseReleased(MouseEvent ee){}
	
	public static String adjust(String i,String a,String b)
	{
		String s=i+" ";
		for(int c=0;c<22;c++)
		{
			s=s+" ";
		}
		s=s+a;
		
		for(int c=s.indexOf(a)+a.length();c<60-a.length()+1;c++)
		{
			s=s+" ";
		}
		s=s+b;System.out.println(s.indexOf(b));
		return s;
	}
}


public class re extends JFrame implements ActionListener                        //主程序
{ 
	static int stage=0;
	Connection con;
	Statement st;
	 PreparedStatement ps;
	 JButton jb1,jb2,back;
	 JTextField jtf1,jtf2;
	 JLabel jl1,jl2;
     int page=1;
     static Boolean zzz=false;
	 public re()
     {
		 String url="jdbc:mysql://localhost:3306/football";
			String user="root";
			String code="69767425";
			
			try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,code);
			st=con.createStatement();
		}catch(Exception ee){ee.printStackTrace();}
		
    	this.setTitle("welcome to the game"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 jb1=new JButton("register ");jb2=new JButton("enter ");back=new JButton(" back ");
    	 jtf1=new JTextField(" enter your id ");jtf2=new JTextField(" enter your password ");
    	 jtf1.addMouseListener(new MouseListener()
    	 {
    		 public void mouseReleased(MouseEvent ee)
    		 {
    			 jtf1.setText("");
    		 }
    		 public void mouseExited(MouseEvent ee){}
    		 public void mousePressed(MouseEvent ee){}
    		 public void mouseClicked(MouseEvent ee){}
    		 public void mouseEntered(MouseEvent ee){}
    	 }
    	 );
    	 jtf2.addMouseListener(new MouseListener()
    	 {
    		 public void mouseReleased(MouseEvent ee)
    		 {
    			 jtf2.setText("");
    		 }
    		 public void mouseExited(MouseEvent ee){}
    		 public void mousePressed(MouseEvent ee){}
    		 public void mouseClicked(MouseEvent ee){}
    		 public void mouseEntered(MouseEvent ee){}
    	 }
    	 );
    	 jl1=new JLabel("id");jl2=new JLabel("password");
    	 jb1.addActionListener(this);jb2.addActionListener(this);back.addActionListener(this);
    	 this.setSize(300, 300);
    	 Container cc=getContentPane(); 
    	 cc.setLayout(null);
    	 cc.add(jtf1,"North");cc.add(back,"South");
    	 cc.add(jb1,"West");cc.add(jb2,"East");
    	 divi(page);
     }
 
	
    public Boolean search(int b,String n) throws SQLException
    {
    	ResultSet rs=st.executeQuery("select * from user");
    	while(rs.next())
    	{
    		if(rs.getString(b).equals(n)){ return true;	}
    	}
    	return false;
    }
 	
    public void actionPerformed(ActionEvent e)  
	{
 		
 		
		if(e.getSource()==jb1)
		{
			if(page==2 && jtf1.getText()!="" && jtf2.getText()!="")    
			{
				try
				{
					if(search(1,jtf1.getText())) {JOptionPane.showMessageDialog(null, " duplicate!!  enter again");jtf1.setText(" enter your id ");jtf2.setText(" enter your password ");}
					else 
					{
						ps=con.prepareStatement("insert into user values(?,?);");
						ps.setString(1, jtf1.getText());ps.setString(2, jtf2.getText());
						ps.execute();
					System.out.println("yfdsfdsf!!!");
					}
				}catch(SQLException ee){ee.printStackTrace();}
			}
			
			if(page==1) page=2;	
		}
		else if(e.getSource()==jb2) 
		{
			if(page==3 && jtf1.getText()!="" && jtf2.getText()!="")
			{
				try
				{
					if(search(1,jtf1.getText()) && search(2,jtf2.getText())) 
					{
						System.out.print(" now is loading!!");this.setVisible(false);
						try{new vslist().setVisible(true);}catch(IOException ee){}
						}
					else System.out.print(" there is some erroer on the id or the password!! ");
				}catch(SQLException ee){ee.printStackTrace();}
			}
			
			if(page==1) page=3;	
		}
		else if(e.getSource()==back) {page=1;jtf1.setText(" enter your id ");jtf2.setText(" enter your password ");}
		divi(page);
	}
	
    public void divi(int n)
	{
		if(n==1)
		{
			jb1.setVisible(true);
			jb2.setVisible(true);
			jtf1.setVisible(false);
			jtf2.setVisible(false);
			back.setVisible(false);
			jl1.setVisible(false);
			jl2.setVisible(false);
		}
		else if(n==2)
		{
			jb2.setVisible(false);	
			jb1.setVisible(true);
			jtf1.setVisible(true);
			jtf2.setVisible(true);
			back.setVisible(true);
			jl1.setVisible(true);
			jl1.setVisible(true);
		}
		else
		{
			jb1.setVisible(false);	
			jb2.setVisible(true);
			jtf1.setVisible(true);
			jtf2.setVisible(true);
			back.setVisible(true);
			jl1.setVisible(true);
			jl1.setVisible(true);
		}
	}
	
    
    public static void main(String[] args) throws UnknownHostException,IOException
	{
    	
    	
    	new connect();
    	new gametable().setVisible(true);
    		    
	}

}
