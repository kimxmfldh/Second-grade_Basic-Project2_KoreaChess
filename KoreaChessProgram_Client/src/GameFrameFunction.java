import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameFrameFunction {
	KoreaChessFunction koreaChessFunction = new KoreaChessFunction();
	
	String movementKoreaChess(String string, int x, int y, KoreachessButton button, String move, String[][] location, OutputStream outStream, String selection) {
		String selectionTeam;
		if(selection.equals("one")) {
			selectionTeam = "han";
		} else {
			selectionTeam = "cho";
		}
		if(selectionTeam.equals("han"))
		{
			if(move.equals("")) {
				if(location[y][x].substring(0, 3).equals("han")) {
					move = move + location[y][x] + "/"+ x + "/" + y;
					System.out.println("첫번째 클릭 move 값 : " + move);
				} else {
					move = "";
				}
			}else if(move.contains("empty")) {
				move = "";
				move = move + location[y][x] + "/"+ x + "/" + y;
			} else {
				if(move.contains(location[y][x].substring(0, 3))) {
					move = "";
					System.out.println("같은 팀 또는 같은 말 선택! move초기화 -> move : " + move);
				} else {
					if(koreaChessFunction.checkChessName(move, location, x, y)) {
						move = move + "/" +location[y][x] + "/"+ x + "/" + y;
						System.out.println("서버로 송신 데이터 move 값" + move);
						changeImageKoreaChess(move, button, location, outStream);
						move = "";
						System.out.println("move 초기화 --> move 값:");						
					} else {
						move = "";
					}
				}
			}				
		}else if(selectionTeam.equals("cho")) {
			if(move.equals("")) {
				if(location[y][x].substring(0, 3).equals("cho")) {
					move = move + location[y][x] + "/"+ x + "/" + y;
					System.out.println("첫번째 클릭 move 값 : " + move);
				} else {
					move = "";
				}
			}else if(move.contains("empty")) {
				move = "";
				move = move + location[y][x] + "/"+ x + "/" + y;
			} else {
				if(move.contains(location[y][x].substring(0, 3))) {
					move = "";
					System.out.println("같은 팀 또는 같은 말 선택! move초기화 -> move : " + move);
				} else {
					if(koreaChessFunction.checkChessName(move, location, x, y)) {
						move = move + "/" + location[y][x] + "/"+ x + "/" + y;
						System.out.println("서버로 송신 데이터 move 값" + move);
						changeImageKoreaChess(move, button, location, outStream);
						move = "";
						System.out.println("move 초기화 --> move 값:");						
					} else {
						move = "";
					}
				}
			}	
		}
		return move;
	}

	void changeImageKoreaChess(String _move, KoreachessButton button, String[][] location, OutputStream outStream) {
		StringTokenizer stk = new StringTokenizer(_move, "/");
		String beforeKoreaChess = stk.nextToken().toString().trim();
		int before_X = Integer.parseInt(stk.nextToken().toString().trim());
		int before_Y = Integer.parseInt(stk.nextToken().toString().trim());
		String afterKoreaChess = stk.nextToken().toString().trim();
		int after_X = Integer.parseInt(stk.nextToken().toString().trim());
		int after_Y = Integer.parseInt(stk.nextToken().toString().trim());
		
		ImageContainer img = new ImageContainer();
		location[after_Y][after_X] = beforeKoreaChess;
		button.arr[after_Y][after_X].setIcon(img.findImage(beforeKoreaChess));

		location[before_Y][before_X] = "empty";
		button.arr[before_Y][before_X].setIcon(img.findImage("defaultImage"));
		
		if(afterKoreaChess.substring(3, afterKoreaChess.length()).equals("King")) {
			gameOver(_move, outStream);
		} else {			
			sendmoveKoreaChessLocation(_move, outStream);
		}
	}
	
	void sendmoveKoreaChessLocation(String _move, OutputStream outStream) {// 내가 움직인 위치를 서버에 전송.
		String gameTag = "GAME";
		String move = _move;
		try {
			StartGameFrame.turnFlag = false;
			DataOutputStream dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(gameTag + "/" + move);
		} catch(Exception e) {
			e.getMessage();
		}
	}

	void gameOver(String _move, OutputStream outStream) {// 왕이 잡혔을 경우 gameOver 서버에 전송.
		String gameOverTag = "GAMEOVER";
		String move = _move;
		try {
			DataOutputStream dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(gameOverTag + "/" + move);
			System.out.println("승!!!!");
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	void startButtonImageHan(JButton button, String number_x, String number_y, String location[][]) {
		ImageContainer img = new ImageContainer();
		
		String x = number_x;
		String y = number_y;

		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		
		if(x.equals("0") && y.equals("0")) {
			button.setIcon(img.hanCha);
			location[b][a] = "hanCha";
		}else if(x.equals("1") && y.equals("0")) {
			button.setIcon(img.hanSang);
			location[b][a] = "hanSang";
		}else if(x.equals("2") && y.equals("0")) {
			button.setIcon(img.hanMa);
			location[b][a] = "hanMa";
		}else if(x.equals("3") && y.equals("0")) {
			button.setIcon(img.hanSa);
			location[b][a] = "hanSa";
		}else if(x.equals("5") && y.equals("0")) {
			button.setIcon(img.hanSa);
			location[b][a] = "hanSa";
		}else if(x.equals("6") && y.equals("0")) {
			button.setIcon(img.hanMa);
			location[b][a] = "hanMa";
		}else if(x.equals("7") && y.equals("0")) {
			button.setIcon(img.hanSang);
			location[b][a] = "hanSang";
		}else if(x.equals("8") && y.equals("0")) {
			button.setIcon(img.hanCha);
			location[b][a] = "hanCha";
		}else if(x.equals("1") && y.equals("2")) {
			button.setIcon(img.hanPho);
			location[b][a] = "hanPho";
		}else if(x.equals("7") && y.equals("2")) {
			button.setIcon(img.hanPho);
			location[b][a] = "hanPho";
		}else if(x.equals("4") && y.equals("1")) {
			button.setIcon(img.hanKing);
			location[b][a] = "hanKing";
		}else if(x.equals("0") && y.equals("3")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("2") && y.equals("3")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("4") && y.equals("3")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("6") && y.equals("3")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("8") && y.equals("3")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("0") && y.equals("9")) {
			button.setIcon(img.choCha);
			location[b][a] = "choCha";
		}else if(x.equals("1") && y.equals("9")) {
			button.setIcon(img.choSang);
			location[b][a] = "choSang";
		}else if(x.equals("2") && y.equals("9")) {
			button.setIcon(img.choMa);
			location[b][a] = "choMa";
		}else if(x.equals("3") && y.equals("9")) {
			button.setIcon(img.choSa);
			location[b][a] = "choSa";
		}else if(x.equals("5") && y.equals("9")) {
			button.setIcon(img.choSa);
			location[b][a] = "choSa";
		}else if(x.equals("6") && y.equals("9")) {
			button.setIcon(img.choMa);
			location[b][a] = "choMa";
		}else if(x.equals("7") && y.equals("9")) {
			button.setIcon(img.choSang);
			location[b][a] = "choSang";
		}else if(x.equals("8") && y.equals("9")) {
			button.setIcon(img.choCha);
			location[b][a] = "choCha";
		}else if(x.equals("1") && y.equals("7")) {
			button.setIcon(img.choPho);
			location[b][a] = "choPho";
		}else if(x.equals("7") && y.equals("7")) {
			button.setIcon(img.choPho);
			location[b][a] = "choPho";
		}else if(x.equals("4") && y.equals("8")) {
			button.setIcon(img.choKing);
			location[b][a] = "choKing";
		}else if(x.equals("0") && y.equals("6")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("2") && y.equals("6")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("4") && y.equals("6")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("6") && y.equals("6")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("8") && y.equals("6")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		} else {
			location[b][a] = "empty";
		}
	}
	
	void startButtonImageCho(JButton button, String number_x, String number_y, String location[][]) {
		ImageContainer img = new ImageContainer();
		
		String x = number_x;
		String y = number_y;

		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		
		if(x.equals("0") && y.equals("0")) {
			button.setIcon(img.choCha);
			location[b][a] = "choCha";
		}else if(x.equals("1") && y.equals("0")) {
			button.setIcon(img.choSang);
			location[b][a] = "choSang";
		}else if(x.equals("2") && y.equals("0")) {
			button.setIcon(img.choMa);
			location[b][a] = "choMa";
		}else if(x.equals("3") && y.equals("0")) {
			button.setIcon(img.choSa);
			location[b][a] = "choSa";
		}else if(x.equals("5") && y.equals("0")) {
			button.setIcon(img.choSa);
			location[b][a] = "choSa";
		}else if(x.equals("6") && y.equals("0")) {
			button.setIcon(img.choMa);
			location[b][a] = "choMa";
		}else if(x.equals("7") && y.equals("0")) {
			button.setIcon(img.choSang);
			location[b][a] = "choSang";
		}else if(x.equals("8") && y.equals("0")) {
			button.setIcon(img.choCha);
			location[b][a] = "choCha";
		}else if(x.equals("1") && y.equals("2")) {
			button.setIcon(img.choPho);
			location[b][a] = "choPho";
		}else if(x.equals("7") && y.equals("2")) {
			button.setIcon(img.choPho);
			location[b][a] = "choPho";
		}else if(x.equals("4") && y.equals("1")) {
			button.setIcon(img.choKing);
			location[b][a] = "choKing";
		}else if(x.equals("0") && y.equals("3")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("2") && y.equals("3")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("4") && y.equals("3")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("6") && y.equals("3")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("8") && y.equals("3")) {
			button.setIcon(img.choJoll);
			location[b][a] = "choJoll";
		}else if(x.equals("0") && y.equals("9")) {
			button.setIcon(img.hanCha);
			location[b][a] = "hanCha";
		}else if(x.equals("1") && y.equals("9")) {
			button.setIcon(img.hanSang);
			location[b][a] = "hanSang";
		}else if(x.equals("2") && y.equals("9")) {
			button.setIcon(img.hanMa);
			location[b][a] = "hanMa";
		}else if(x.equals("3") && y.equals("9")) {
			button.setIcon(img.hanSa);
			location[b][a] = "hanSa";
		}else if(x.equals("5") && y.equals("9")) {
			button.setIcon(img.hanSa);
			location[b][a] = "hanSa";
		}else if(x.equals("6") && y.equals("9")) {
			button.setIcon(img.hanMa);
			location[b][a] = "hanMa";
		}else if(x.equals("7") && y.equals("9")) {
			button.setIcon(img.hanSang);
			location[b][a] = "hanSang";
		}else if(x.equals("8") && y.equals("9")) {
			button.setIcon(img.hanCha);
			location[b][a] = "hanCha";
		}else if(x.equals("1") && y.equals("7")) {
			button.setIcon(img.hanPho);
			location[b][a] = "hanPho";
		}else if(x.equals("7") && y.equals("7")) {
			button.setIcon(img.hanPho);
			location[b][a] = "hanPho";
		}else if(x.equals("4") && y.equals("8")) {
			button.setIcon(img.hanKing);
			location[b][a] = "hanKing";
		}else if(x.equals("0") && y.equals("6")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("2") && y.equals("6")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("4") && y.equals("6")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("6") && y.equals("6")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		}else if(x.equals("8") && y.equals("6")) {
			button.setIcon(img.hanJoll);
			location[b][a] = "hanJoll";
		} else {
			location[b][a] = "empty";
		}
	}
	
	void sendMassageToServer(OutputStream outStream, String _massage, JLabel myNickName, JTextArea chattingBar, JTextField inputBar) {
		String chattingTag = "CHATTING";
		String massage = myNickName.getText().toString().trim() + " : " + _massage;
		try {
			DataOutputStream dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(chattingTag + "/" + massage);
			chattingBar.append(massage + "\n");
			inputBar.setText("");
		} catch(Exception e) {
			e.getMessage();
		}
	}

	void giveUpsendUpdateInformation(OutputStream outStream, JLabel myNickName, JLabel otherNickName) {
		String updateTag = "UPDATE";
		String nickName = myNickName.getText().toString().trim();
		String othernickName = otherNickName.getText().toString().trim();
		try {
			DataOutputStream dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(updateTag
					+ "/" + nickName + "/defeat/" + othernickName + "/win");
			DefeatFrame defeat = new DefeatFrame();//항복버튼을 눌러서 뜨는 패배 창.
		} catch(Exception e) {
			e.getMessage();
		}
	}

}
