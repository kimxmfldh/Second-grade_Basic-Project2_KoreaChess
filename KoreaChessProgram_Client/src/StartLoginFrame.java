import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class StartLoginFrame extends JFrame{
	JPanel mainPanel = new JPanel(new FlowLayout());
	ImageIcon imgTitle = new ImageIcon("./image/maintitle.png");
	ImageIcon imgMain = new ImageIcon("./image/mainimage.png");
	JLabel koreaChessImage = new JLabel(imgMain);	
	JLabel gameTitle = new JLabel(imgTitle); 
	JLabel id = new JLabel("아이디");
	JLabel password = new JLabel("비밀번호");
	JTextField typeId = new JTextField();
	JPasswordField typePassword = new JPasswordField();
	JButton loginButton = new JButton("로그인");
	JButton registerButton = new JButton("회원가입");
	DataOutputStream dataOutputStream = null;
	String userID;
	String userPW;
	
	public StartLoginFrame(OutputStream outStream) {
		setTitle("장기GO - 로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font = new Font("굵게", Font.BOLD, 20);
		Font font2 = new Font("굵게", Font.BOLD, 27);
		LineBorder border1 = new LineBorder(Color.orange, 3, true);
		LineBorder border2 = new LineBorder(Color.BLACK, 3, true);
		
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.yellow);
		
		gameTitle.setPreferredSize(new Dimension(540,150));
		
		koreaChessImage.setPreferredSize(new Dimension(540, 540));
		
		id.setPreferredSize(new Dimension(220, 45));
		id.setFont(font2);
		id.setOpaque(true);
		id.setBackground(Color.orange);
		id.setBorder(border1);
		id.setHorizontalAlignment(JLabel.CENTER);
		
		password.setPreferredSize(new Dimension(220, 45));
		password.setFont(font2);
		password.setOpaque(true);
		password.setBackground(Color.orange);
		password.setBorder(border1);
		password.setHorizontalAlignment(JLabel.CENTER);
		
		typeId.setPreferredSize(new Dimension(390, 45));
		typeId.setFont(font);
		typeId.setOpaque(true);
		typeId.setBackground(Color.white);
		typeId.setBorder(border2);
		
		typePassword.setPreferredSize(new Dimension(390, 45));
		typePassword.setFont(font);
		typePassword.setOpaque(true);
		typePassword.setBackground(Color.white);
		typePassword.setBorder(border2);
		
		loginButton.setPreferredSize(new Dimension(303, 45));
		loginButton.setFont(font2);
		loginButton.setOpaque(true);
		loginButton.setBackground(Color.lightGray);
		loginButton.setBorder(border2);
		loginButton.setHorizontalAlignment(JLabel.CENTER);
		
		registerButton.setPreferredSize(new Dimension(303, 45));
		registerButton.setFont(font2);
		registerButton.setOpaque(true);
		registerButton.setBackground(Color.lightGray);
		registerButton.setBorder(border2);
		registerButton.setHorizontalAlignment(JLabel.CENTER);
		
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = typeId.getText().toString().trim();
				String password = typePassword.getText().toString().trim();
				if(id.equals("") || password.equals("")) {					
					System.out.println("아이디 또는 비밀번호 입력하시오.");
				} else {					
					sendLoginInformationToServer(outStream, id, password);
				}
				typeId.setText("");
				typePassword.setText("");
			}
		});	
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StartRegisterFrame registerFrame = new StartRegisterFrame(outStream);
			}
		});
		
		setContentPane(mainPanel);
		
		mainPanel.setPreferredSize(new Dimension(550, 900));
		mainPanel.add(gameTitle);
		mainPanel.add(koreaChessImage);
		mainPanel.add(id);
		mainPanel.add(typeId);
		mainPanel.add(password);
		mainPanel.add(typePassword);
		mainPanel.add(loginButton);
		mainPanel.add(registerButton);

		setSize(700, 900);
		setVisible(true);
		setResizable(false);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, 
				(screenSize.height - frameSize.height) / 2);
		
	}
	
	void sendLoginInformationToServer(OutputStream outStream, String _id, String _password) {
		String loginTag = "LOGIN";
		dataOutputStream = new DataOutputStream(outStream);
		try {
			String goMassage = loginTag + "/" + _id + "/" + _password;
			dataOutputStream.writeUTF(goMassage);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
