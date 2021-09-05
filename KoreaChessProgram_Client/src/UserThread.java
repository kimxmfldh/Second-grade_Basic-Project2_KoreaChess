import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class UserThread extends Thread{
	Socket mySocket = null;
	
	OutputStream outStream = null;
	InputStream inputStream = null;
	DataOutputStream dataOutputStream = null;
	DataInputStream dataInputStream = null;
	
	final String loginTag = "LOGIN_OK";
	final String loginTagTWO = "LOGIN_OK_TWO";
	final String registerTag = "REGISTER_OK";
	final String chattingTag = "CHATTING";
	final String gameTag = "GAME";
	final String gameOverTag = "GAMEOVER";
	final String gameinfoTag = "GAMEINFO";
	
	String inputMassage = "";
	StartGameFrame startGame;
	StartLoginFrame loginFrame;
	UserTagFunction tagFunction = new UserTagFunction();
	
	UserThread(Socket _mySocket) {
		mySocket = _mySocket;
	}
	
	public void run() {
		try {
			int count = 0;
			while(true) {
				outStream = mySocket.getOutputStream();
				inputStream = mySocket.getInputStream();
				dataInputStream = new DataInputStream(inputStream);
				inputMassage = dataInputStream.readUTF();
				
				StringTokenizer stk = new StringTokenizer(inputMassage, "/");
				String tag = stk.nextToken().toString().trim();
				System.out.println(tag);
				if(tag.equals(loginTag)) {
					loginFrame.dispose();
					startGame = new StartGameFrame(outStream, "one");
					tagFunction.inputMyGameInfo(startGame, inputMassage);
				} else if(tag.equals(loginTagTWO)) {
					loginFrame.dispose();
					startGame = new StartGameFrame(outStream, "two");
					tagFunction.inputMenuInfo(startGame, inputMassage);
				} else if(tag.equals(registerTag)) {
					System.out.println("회원가입 완료");
				} else if(tag.equals(chattingTag)) {
					tagFunction.inputChatting(startGame, inputMassage);
				} else if(tag.equals(gameTag)) {	
					tagFunction.inputOtherMovedKoreaChessLocation(startGame, inputMassage);
				} else if(tag.equals(gameinfoTag)) {
					tagFunction.inputOtherGameInfo(startGame, inputMassage);
				} else if(tag.equals("GAMEFINISH")) {
					VictoryFrame victory = new VictoryFrame();// 상대가 항복하면 뜨는 승리 창.
				} else if(tag.equals(gameOverTag)) {
					tagFunction.inputGameOver(startGame, inputMassage);
					tagFunction.insertDBGameOver(startGame, outStream);
				} else if(tag.equals("CANLOGIN")){
					loginFrame = new StartLoginFrame(outStream);
				} else {
					System.out.println("TAG 오류" + inputMassage);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
