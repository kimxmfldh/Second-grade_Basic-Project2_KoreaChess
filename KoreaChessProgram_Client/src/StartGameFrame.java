import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class StartGameFrame extends JFrame{
	JPanel basePanel = new JPanel(new BorderLayout());
	MyPanel westPanel = new MyPanel();//---------장기판
	JPanel centerPanel = new JPanel();//-------채팅
	JPanel eastPanel = new JPanel();//---------매뉴
	
	ImageIcon imageIcon = new ImageIcon("./image/koreachessplate.jpg");
	Image image = imageIcon.getImage();
	JTextArea chattingBar = new JTextArea();
	JScrollPane scroll = new JScrollPane(chattingBar);
	JTextField inputBar = new JTextField();
	JButton sendMassage = new JButton("보내기");
	ImageIcon imageMain = new ImageIcon("./image/maintitle.png");
	JLabel mainImage = new JLabel(imageMain);
	JLabel otherNickName = new JLabel("아무개");
	JLabel otherWinningRate = new JLabel("00%");
	JLabel VS = new JLabel("VS");
	JLabel myNickName = new JLabel("아무개");
	JLabel myWinningRate = new JLabel("00%");
	ImageIcon imageGiveUp = new ImageIcon("./image/giveup.png");
	JButton giveUpImage = new JButton(imageGiveUp);
	
	GameFrameFunction gameFunction = new GameFrameFunction();
	ImageContainer ic = new ImageContainer();
	String[][] location = new String[10][9];
	KoreachessButton button = new KoreachessButton();
	JButton jbutton;
	static String move = "";
	public static boolean turnFlag = false;
	
	StartGameFrame(OutputStream outStream, String selection){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("장기GO - 게임 화면");
		Font font = new Font("굵게", Font.BOLD, 18);
		Font font2 = new Font("굵게", Font.BOLD, 30);
		Font font3 = new Font("굵게", Font.BOLD, 22);
		LineBorder border = new LineBorder(Color.yellow, 5, true);
		LineBorder border2 = new LineBorder(Color.BLACK, 3, true);

		basePanel.add(westPanel, BorderLayout.WEST);
		basePanel.add(centerPanel, BorderLayout.CENTER);
		basePanel.add(eastPanel, BorderLayout.EAST);
		setContentPane(basePanel);
		westPanel.setPreferredSize(new Dimension(820, 700));
		westPanel.setLayout(new FlowLayout());
		for(int i = 9; i >= 0; i--) {
			for (int j = 0; j < 9; j++) {
				String number_x = Integer.toString(j);
				String number_y = Integer.toString(i);
				jbutton = button.arr[i][j];
				location[i][j] = "";
				westPanel.add(jbutton);
				if(selection.equals("one")) {
					turnFlag = true;
					gameFunction.startButtonImageHan(jbutton, number_x, number_y, location);					
				}else {
					turnFlag = false;
					gameFunction.startButtonImageCho(jbutton, number_x, number_y, location);
				}
				button.arr[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton)e.getSource();
						if(turnFlag) {					
							String coordinate = number_x + ", " + number_y;
							int x = Integer.parseInt(number_x);
							int y = Integer.parseInt(number_y);
							System.out.println(x +"  "+y);
							System.out.println(coordinate + "장기알 : " + location[y][x]);
							move = gameFunction.movementKoreaChess(location[y][x], x, y
									, button, move, location, outStream, selection);
						}
					}});
			}
		}
		centerPanel.setPreferredSize(new Dimension(350, 790));
		centerPanel.setOpaque(true);
		centerPanel.setBackground(Color.yellow);
		
		scroll.setPreferredSize(new Dimension(355, 695));
		chattingBar.setCaretPosition(chattingBar.getDocument().getLength());
		chattingBar.setFont(font);
		chattingBar.setBorder(border2);
		
		inputBar.setPreferredSize(new Dimension(270, 45));
		inputBar.setFont(font);
		inputBar.setBorder(border2);
		
		sendMassage.setPreferredSize(new Dimension(80, 45));
		sendMassage.setFont(font3);
		sendMassage.setBorder(border2);
		sendMassage.setOpaque(true);
		sendMassage.setBackground(Color.LIGHT_GRAY);
		sendMassage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String massage = inputBar.getText().toString().trim();
				gameFunction.sendMassageToServer(outStream, massage
						, myNickName, chattingBar, inputBar);
			}
		});
		centerPanel.add(scroll);
		centerPanel.add(inputBar);
		centerPanel.add(sendMassage);
		
		eastPanel.setPreferredSize(new Dimension(250, 650));
		eastPanel.setBorder(border);
		eastPanel.setOpaque(true);
		eastPanel.setBackground(Color.yellow);
		
		mainImage.setPreferredSize(new Dimension(250, 400));
		
		otherNickName.setPreferredSize(new Dimension(150, 40));
		otherNickName.setFont(font);
		otherNickName.setHorizontalAlignment(JLabel.CENTER);
		
		otherWinningRate.setPreferredSize(new Dimension(150, 40));
		otherWinningRate.setFont(font);
		otherWinningRate.setHorizontalAlignment(JLabel.CENTER);
		
		VS.setPreferredSize(new Dimension(150, 40));
		VS.setFont(font2);
		VS.setHorizontalAlignment(JLabel.CENTER);
		
		myNickName.setPreferredSize(new Dimension(150, 40));
		myNickName.setFont(font);
		myNickName.setHorizontalAlignment(JLabel.CENTER);
		
		myWinningRate.setPreferredSize(new Dimension(150, 40));
		myWinningRate.setFont(font);
		myWinningRate.setHorizontalAlignment(JLabel.CENTER);
		
		if(selection.equals("one")) {
			otherNickName.setOpaque(true);
			otherNickName.setBackground(Color.GREEN);
			otherNickName.setBorder(border2);
			otherWinningRate.setOpaque(true);
			otherWinningRate.setBackground(Color.GREEN);
			otherWinningRate.setBorder(border2);
			myNickName.setOpaque(true);
			myNickName.setBackground(Color.RED);
			myNickName.setBorder(border2);
			myWinningRate.setOpaque(true);
			myWinningRate.setBackground(Color.RED);
			myWinningRate.setBorder(border2);
		} else {
			otherNickName.setOpaque(true);
			otherNickName.setBackground(Color.RED);
			otherNickName.setBorder(border2);
			otherWinningRate.setOpaque(true);
			otherWinningRate.setBackground(Color.RED);
			otherWinningRate.setBorder(border2);
			myNickName.setOpaque(true);
			myNickName.setBackground(Color.GREEN);
			myNickName.setBorder(border2);
			myWinningRate.setOpaque(true);
			myWinningRate.setBackground(Color.GREEN);
			myWinningRate.setBorder(border2);
		}
		giveUpImage.setPreferredSize(new Dimension(150, 120));
		giveUpImage.setBorder(border2);
		giveUpImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameFunction.giveUpsendUpdateInformation(outStream, myNickName, otherNickName);// 항복버튼 누르면 발생하는 이벤트.
			}
		});
		eastPanel.add(mainImage);
		eastPanel.add(otherNickName);
		eastPanel.add(otherWinningRate);
		eastPanel.add(VS);
		eastPanel.add(myNickName);
		eastPanel.add(myWinningRate);
		eastPanel.add(giveUpImage);
		
		setSize(1450, 800);
		setVisible(true);
		setResizable(false);

		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, 
				(screenSize.height - frameSize.height) / 2);
		
	}

	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
