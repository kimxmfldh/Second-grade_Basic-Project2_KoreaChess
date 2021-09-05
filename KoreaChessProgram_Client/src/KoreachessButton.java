import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KoreachessButton extends JPanel {
	JButton arr[][] = new JButton[10][9];
	
	KoreachessButton(){
		for(int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {				
				String number_x = Integer.toString(i);
				String number_y = Integer.toString(j);

				arr[i][j] = new JButton();
				if(j == 0) {
					arr[i][j].setPreferredSize(new Dimension(70, 70));
				} else if(j == 1){
					arr[i][j].setPreferredSize(new Dimension(80, 70));
				} else if(j > 1 && j < 4){
					arr[i][j].setPreferredSize(new Dimension(100, 70));
				} else if(j == 4) {
					arr[i][j].setPreferredSize(new Dimension(70, 70));
				} else if(j < 7) {
					arr[i][j].setPreferredSize(new Dimension(100, 70));
				} else if (j == 7) {	
					arr[i][j].setPreferredSize(new Dimension(80, 70));
				} else if(j == 8){
					arr[i][j].setPreferredSize(new Dimension(70, 70));
				}
				arr[i][j].setBorderPainted(false);
				arr[i][j].setContentAreaFilled(false);
				arr[i][j].setFocusPainted(false);
				arr[i][j].setOpaque(false);
			}
		}
	}
}
