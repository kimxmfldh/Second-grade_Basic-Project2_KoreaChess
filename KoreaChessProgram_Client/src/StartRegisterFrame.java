import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class StartRegisterFrame extends JFrame{
	JPanel mainPanel = new JPanel(new FlowLayout());
	JLabel id = new JLabel("아이디");
	JLabel password = new JLabel("비밀번호");
	JLabel name = new JLabel("이름");
	JLabel nickName = new JLabel("닉네임");
	JTextField typeId = new JTextField();
	JTextField typeNickName = new JTextField();
	JTextField typeName = new JTextField();
	JPasswordField typePassword = new JPasswordField();
	JButton enterButton = new JButton("확인");
	JButton cancleButton = new JButton("취소");
	JLabel empty = new JLabel();
	DataOutputStream dataOutputStream = null;
	
	public StartRegisterFrame(OutputStream outStream) {
		setTitle("장기GO - 회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font font = new Font("굵게", Font.BOLD, 20);
		Font font2 = new Font("굵게", Font.BOLD, 27);
		LineBorder border1 = new LineBorder(Color.orange, 3, true);
		LineBorder border2 = new LineBorder(Color.BLACK, 3, true);
		empty.setPreferredSize(new Dimension(440, 7));
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.yellow);
		
		id.setPreferredSize(new Dimension(130, 50));
		id.setFont(font2);
		id.setOpaque(true);
		id.setBackground(Color.orange);
		id.setBorder(border1);
		id.setHorizontalAlignment(JLabel.CENTER);
		typeId.setPreferredSize(new Dimension(330, 50));
		typeId.setFont(font);
		typeId.setOpaque(true);
		typeId.setBackground(Color.white);
		typeId.setBorder(border2);
		
		name.setPreferredSize(new Dimension(130, 50));
		name.setFont(font2);
		name.setOpaque(true);
		name.setBackground(Color.orange);
		name.setBorder(border1);
		name.setHorizontalAlignment(JLabel.CENTER);
		typeName.setPreferredSize(new Dimension(330, 50));
		typeName.setFont(font);
		typeName.setOpaque(true);
		typeName.setBackground(Color.white);
		typeName.setBorder(border2);
		
		password.setPreferredSize(new Dimension(130, 50));
		password.setFont(font2);
		password.setOpaque(true);
		password.setBackground(Color.orange);
		password.setBorder(border1);
		password.setHorizontalAlignment(JLabel.CENTER);
		typePassword.setPreferredSize(new Dimension(330, 50));
		typePassword.setFont(font);
		typePassword.setOpaque(true);
		typePassword.setBackground(Color.white);
		typePassword.setBorder(border2);
		
		nickName.setPreferredSize(new Dimension(130, 50));
		nickName.setFont(font2);
		nickName.setOpaque(true);
		nickName.setBackground(Color.orange);
		nickName.setBorder(border1);
		nickName.setHorizontalAlignment(JLabel.CENTER);
		typeNickName.setPreferredSize(new Dimension(330, 50));
		typeNickName.setFont(font);
		typeNickName.setOpaque(true);
		typeNickName.setBackground(Color.white);
		typeNickName.setBorder(border2);
		
		enterButton.setPreferredSize(new Dimension(230, 50));
		enterButton.setFont(font2);
		enterButton.setOpaque(true);
		enterButton.setBackground(Color.lightGray);
		enterButton.setBorder(border2);
		enterButton.setHorizontalAlignment(JLabel.CENTER);
		cancleButton.setPreferredSize(new Dimension(230, 50));
		cancleButton.setFont(font2);
		cancleButton.setOpaque(true);
		cancleButton.setBackground(Color.lightGray);
		cancleButton.setBorder(border2);
		cancleButton.setHorizontalAlignment(JLabel.CENTER);
		
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = typeName.getText().toString().trim();
				String nickName = typeNickName.getText().toString().trim();
				String id = typeId.getText().toString().trim();
				String password = typePassword.getText().toString().trim();
				if(name.equals("")) {
					System.out.println("회원가입 정보를 다시 입력하세요");
				} else {
					sendRegisterInformationToServer(outStream, name, nickName, id, password);
					dispose();
				}
			}
		});
		cancleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setContentPane(mainPanel);
		mainPanel.add(empty);
		mainPanel.add(name);
		mainPanel.add(typeName);
		mainPanel.add(nickName);
		mainPanel.add(typeNickName);
		
		mainPanel.add(id);
		mainPanel.add(typeId);
		mainPanel.add(password);
		mainPanel.add(typePassword);
		
		mainPanel.add(enterButton);
		mainPanel.add(cancleButton);		

		setSize(500, 340);
		setVisible(true);
		setResizable(false);
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, 
				(screenSize.height - frameSize.height) / 2);
		
	}
	
	void sendRegisterInformationToServer(OutputStream outStream, String _name, String _nickName
			, String _id, String _password) {
		String registerTag = "REGISTER";
		dataOutputStream = new DataOutputStream(outStream);
		try {
			String goMassage = registerTag + "/" + _name + "/" + _nickName + "/" + _id + "/" + _password;
			System.out.println(goMassage);
			dataOutputStream.writeUTF(goMassage);		
		} catch (IOException e) {
			e.printStackTrace();
		}	
	} 
}
