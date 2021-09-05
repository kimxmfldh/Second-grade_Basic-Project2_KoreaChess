import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DefeatFrame extends JFrame{
	JPanel mainPanel = new JPanel(new FlowLayout());
	ImageIcon image = new ImageIcon("./image/defeatimage.png");
	JLabel defeatImage = new JLabel(image);
	JButton enterButton = new JButton("종료");
	
	public DefeatFrame() {
		setTitle("장기GO - 패배");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		defeatImage.setPreferredSize(new Dimension(630, 300));
		enterButton.setPreferredSize(new Dimension(300, 70));
		
		enterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mainPanel.add(defeatImage);
		mainPanel.add(enterButton);
		setContentPane(mainPanel);
		
		setSize(700, 450);
		setVisible(true);
		setResizable(false);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - frameSize.width) / 2, 
				(screenSize.height - frameSize.height) / 2);
		
	}
}
