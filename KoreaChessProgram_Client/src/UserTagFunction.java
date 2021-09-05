import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

public class UserTagFunction {// Tag�� �޼ҵ�.
	
	void inputOtherMovedKoreaChessLocation(StartGameFrame startGame, String inputMassage) {// ��밡 �̵��� ���� ��ġ�� ������ �޾ƿͼ� �� ȭ�鿡 ����
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();
		String beforeKoreaChess = stk.nextToken().toString().trim();
		int before_X = Integer.parseInt(stk.nextToken().toString().trim());
		int before_Y = Integer.parseInt(stk.nextToken().toString().trim());
		String afterKoreaChess = stk.nextToken().toString().trim();
		int after_X = Integer.parseInt(stk.nextToken().toString().trim());
		int after_Y = Integer.parseInt(stk.nextToken().toString().trim());
		
		ImageContainer img = new ImageContainer();
		startGame.location[9-after_Y][8-after_X] = beforeKoreaChess;
		startGame.button.arr[9-after_Y][8-after_X].setIcon(img.findImage(beforeKoreaChess));

		startGame.location[9-before_Y][8-before_X] = "empty";
		startGame.button.arr[9-before_Y][8-before_X].setIcon(img.findImage("defaultImage"));
		
		StartGameFrame.turnFlag = true;
	}
	
	void inputChatting(StartGameFrame startGame, String inputMassage) {// ��밡 �Է��� ä���� ������ �޾ƿ� ä��â�� ����.
		System.out.println("�������� ���� ����//" + inputMassage);
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();
		String massage = stk.nextToken().toString().trim();
		
		startGame.chattingBar.append(massage + "\n");
	}

	void inputMyGameInfo(StartGameFrame startGame, String inputMassage) {// ù��°�� ���� �� �·��� �г����� ������ ���� �޾ƿ� ����â�� ����.
		System.out.println("�������� ���� ����//" + inputMassage);
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();
		String myNickName = stk.nextToken().toString().trim();
		String myWin = stk.nextToken().toString().trim();
		String myDefeat = stk.nextToken().toString().trim();
		
		int winPoint = Integer.parseInt(myWin);
		int defeatPoint = Integer.parseInt(myDefeat);
		int winningPoint = 0;
		if(defeatPoint == 0) {
			winningPoint = winPoint;
		}
		if(winPoint != 0) {
			winningPoint = ((winPoint) * 100 ) / (winPoint + defeatPoint);
		} 
		String winningRate = winningPoint + "%";
		startGame.myNickName.setText(myNickName);
		startGame.myWinningRate.setText(winningRate);
	}
	
	void inputOtherGameInfo(StartGameFrame startGame, String inputMassage) {// �ι�°�� ���� ������ �·��� �г����� ������ ���� �޾ƿ� ����â�� ����.
		System.out.println("�������� ���� ����//" + inputMassage);
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();
		String otherNickName = stk.nextToken().toString().trim();
		String otherWin = stk.nextToken().toString().trim();
		String otherDefeat = stk.nextToken().toString().trim();
		
		int winPoint = Integer.parseInt(otherWin);
		int defeatPoint = Integer.parseInt(otherDefeat);
		int winningPoint = 0;
		if(defeatPoint == 0) {
			winningPoint = winPoint;
		}
		if(winPoint != 0) {
			winningPoint = ((winPoint) * 100 ) / (winPoint + defeatPoint);
		} 
		String winningRate = winningPoint + "%";
		startGame.otherNickName.setText(otherNickName);
		startGame.otherWinningRate.setText(winningRate);
	}
	
	void inputMenuInfo(StartGameFrame startGame, String inputMassage) {// �ι�° ���� ����� ��� �̹� ���� �ִ� ����� �г��Ӱ� �·�, �� �г��Ӱ� �·��� �޾ƿͼ� ����â�� ����.
		System.out.println("�������� ���� ����//" + inputMassage);
		
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();;
		
		String myNickName = stk.nextToken().toString().trim();
		String myWin = stk.nextToken().toString().trim();
		String myDefeat = stk.nextToken().toString().trim();
		String myRank = stk.nextToken().toString().trim();
		String otherNickName = stk.nextToken().toString().trim();
		String otherWin = stk.nextToken().toString().trim();
		String otherDefeat = stk.nextToken().toString().trim();
		String otherRank = stk.nextToken().toString().trim();
				
		int winPoint = Integer.parseInt(otherWin);
		int defeatPoint = Integer.parseInt(otherDefeat);
		int winningPoint = 0;
		if(defeatPoint == 0) {
			winningPoint = winPoint;
		}
		if(winPoint != 0) {
			winningPoint = ((winPoint) * 100 ) / (winPoint + defeatPoint);
		} 
		String winningRate = winningPoint + "%";
		
		startGame.otherNickName.setText(otherNickName);
		startGame.otherWinningRate.setText(winningRate);
		
		winPoint = Integer.parseInt(myWin);
		defeatPoint = Integer.parseInt(myDefeat);
		winningPoint = 0;
		if(defeatPoint == 0) {
			winningPoint = winPoint;
		}
		if(winPoint != 0) {
			winningPoint = ((winPoint) * 100 ) / (winPoint + defeatPoint);
		} 
		winningRate = winningPoint + "%";
		
		startGame.myNickName.setText(myNickName);
		startGame.myWinningRate.setText(winningRate);
	}

	void inputGameOver(StartGameFrame startGame, String inputMassage) {// ��밡 �¸��ϰ� ���� �й� �ߴٴ� ������ ����. Ȯ�� ������ ���� ����.
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();
		String beforeKoreaChess = stk.nextToken().toString().trim();
		int before_X = Integer.parseInt(stk.nextToken().toString().trim());
		int before_Y = Integer.parseInt(stk.nextToken().toString().trim());
		String afterKoreaChess = stk.nextToken().toString().trim();
		int after_X = Integer.parseInt(stk.nextToken().toString().trim());
		int after_Y = Integer.parseInt(stk.nextToken().toString().trim());
		
		ImageContainer img = new ImageContainer();
		startGame.location[9-after_Y][8-after_X] = beforeKoreaChess;
		startGame.button.arr[9-after_Y][8-after_X].setIcon(img.findImage(beforeKoreaChess));

		startGame.location[9-before_Y][8-before_X] = "empty";
		startGame.button.arr[9-before_Y][8-before_X].setIcon(img.findImage("defaultImage"));
		
		System.out.println(startGame.myNickName.getText().toString() + "�й�!!!!!!!");
		DefeatFrame defeat = new DefeatFrame();
	}

	public void insertDBGameOver(StartGameFrame startGame, OutputStream outStream) {// DB�� ���� ���и� update���ֱ� ���Ѱ�.
		String updateTag = "UPDATE";
		String nickName = startGame.myNickName.getText().toString().trim();
		String othernickName = startGame.otherNickName.getText().toString().trim();
		try {
			DataOutputStream dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(updateTag
					+ "/" + nickName + "/defeat/" + othernickName + "/win");
		} catch(Exception e) {
			e.getMessage();
		}
		
	}
}
