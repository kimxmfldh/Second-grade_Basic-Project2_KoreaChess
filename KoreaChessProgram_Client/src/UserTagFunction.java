import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

public class UserTagFunction {// Tag별 메소드.
	
	void inputOtherMovedKoreaChessLocation(StartGameFrame startGame, String inputMassage) {// 상대가 이동한 말의 위치와 행위를 받아와서 내 화면에 띄운다
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
	
	void inputChatting(StartGameFrame startGame, String inputMassage) {// 상대가 입력한 채팅의 내용을 받아와 채팅창에 띄운다.
		System.out.println("서버에서 받은 정보//" + inputMassage);
		StringTokenizer stk = new StringTokenizer(inputMassage, "/");
		String tag = stk.nextToken().toString().trim();
		String massage = stk.nextToken().toString().trim();
		
		startGame.chattingBar.append(massage + "\n");
	}

	void inputMyGameInfo(StartGameFrame startGame, String inputMassage) {// 첫번째로 들어온 내 승률과 닉네임을 서버로 부터 받아와 정보창에 띄운다.
		System.out.println("서버에서 받은 정보//" + inputMassage);
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
	
	void inputOtherGameInfo(StartGameFrame startGame, String inputMassage) {// 두번째로 들어온 상대방의 승률과 닉네임을 서버로 부터 받아와 정보창에 띄운다.
		System.out.println("서버에서 받은 정보//" + inputMassage);
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
	
	void inputMenuInfo(StartGameFrame startGame, String inputMassage) {// 두번째 들어온 사람일 경우 이미 들어와 있는 사람의 닉네임과 승률, 내 닉네임과 승률을 받아와서 정보창에 띄운다.
		System.out.println("서버에서 받은 정보//" + inputMassage);
		
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

	void inputGameOver(StartGameFrame startGame, String inputMassage) {// 상대가 승리하고 내가 패배 했다는 프레임 띄우기. 확인 누르면 게임 종료.
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
		
		System.out.println(startGame.myNickName.getText().toString() + "패배!!!!!!!");
		DefeatFrame defeat = new DefeatFrame();
	}

	public void insertDBGameOver(StartGameFrame startGame, OutputStream outStream) {// DB에 게임 승패를 update해주기 위한것.
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
