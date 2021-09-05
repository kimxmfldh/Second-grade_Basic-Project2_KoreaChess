import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class TagFunction {
	OutputStream outStream;
	DataOutputStream dataOutStream;
	static ClientList clientlist;
	final String loginOKTag = "LOGIN_OK";
	final String loginOKTagTWO = "LOGIN_OK_TWO";
	final String registerTag = "REGISTER";
	final String chattingTag = "CATTING";
	final String gameTag = "GAME";
	final String gameinfoTag = "GAMEINFO";
	
	void userLogin(String _inputMassage, Socket socket) {//-------OK LOGIN_TAG
		StringTokenizer stk = new StringTokenizer(_inputMassage, "/");
		String token = stk.nextToken();
		String id = stk.nextToken();
		String password = stk.nextToken();
		
		try {
			if (loginChecker(id, password)) {
				outStream = socket.getOutputStream();
				dataOutStream = new DataOutputStream(outStream);
				
				UserInformationManager uim = new UserInformationManager();
				String userNickName = getUserNickName(id, password);
				int location = uim.getUserWinningRateToDB(userNickName);
				System.out.println("check");
				String userWinningGrade = uim.users.get(location).userWinningGrade;
				System.out.println(loginOKTag + "/" + userNickName);
				String userDefeatGrade = uim.users.get(location).userDefeatGrade;
				String userRank = uim.users.get(location).userRank;
				Server.usersNickName.add(userNickName);
				System.out.println(Server.usersNickName.size());
				dataOutStream.writeUTF(loginOKTag + "/" + userNickName + "/" + userWinningGrade
						+ "/" + userDefeatGrade + "/" + userRank);
			} else {
				System.out.println("LOGIN_FAIL");
				dataOutStream.writeUTF("LOGIN_FAIL");
			}			
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	void userLogin(String _inputMassage, Socket socket, Socket ohterSocket) {
		StringTokenizer stk = new StringTokenizer(_inputMassage, "/");
		String token = stk.nextToken();
		String id = stk.nextToken();
		String password = stk.nextToken();
		
		try {
			if (loginChecker(id, password)) {
				outStream = socket.getOutputStream();
				dataOutStream = new DataOutputStream(outStream);
				
				String userNickName = getUserNickName(id, password);
				
				int location = Server.uim.getUserWinningRateToDB(userNickName);
				
				System.out.println("check");
				String userWinningGrade = Server.uim.users.get(location).userWinningGrade;
				System.out.println(loginOKTag + "/" + userNickName);
				String userDefeatGrade = Server.uim.users.get(location).userDefeatGrade;
				String userRank = Server.uim.users.get(location).userRank;
				
				String myGameInfo = userNickName + "/" + userWinningGrade
						+ "/" + userDefeatGrade + "/" + userRank;
				String otherNickName = null;
				for(int i = 0; i < Server.usersNickName.size(); i++) {
					if(Server.usersNickName.get(i).equals(userNickName) != true) {
						otherNickName = Server.usersNickName.get(i);
						System.out.println("userNickName"+userNickName+"otherNickName"+otherNickName);
					}
				}
				location = Server.uim.getUserWinningRateToDB(otherNickName);
				System.out.println("otherNickName" + otherNickName);
				String otherWinningGrade = Server.uim.users.get(location).userWinningGrade;
				System.out.println(loginOKTag + "/" + otherNickName);
				String otherDefeatGrade = Server.uim.users.get(location).userDefeatGrade;
				String otherRank = Server.uim.users.get(location).userRank;
				
				String otherGameInfo = otherNickName + "/" + otherWinningGrade
						+ "/" + otherDefeatGrade + "/" + otherRank;
				Server.usersNickName.add(userNickName);
				dataOutStream.writeUTF(loginOKTagTWO + "/" + myGameInfo + "/" + otherGameInfo);
			} else {
				System.out.println("LOGIN_FAIL");
				dataOutStream.writeUTF("LOGIN_FAIL");
			}			
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	void userRegister(String _inputMassage, Socket socket) {//----OK REGISTER_TAG
		StringTokenizer stk = new StringTokenizer(_inputMassage, "/");
		String token = stk.nextToken();
		String name = stk.nextToken();
		String nickName = stk.nextToken();
		String id = stk.nextToken();
		String password = stk.nextToken();
		
		try {
			outStream = socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			UserInformationManager uim = new UserInformationManager();
			uim.inSertInformationFromRegister(name, nickName, id, password);
			uim.inSertWinningRateFromRegister(nickName, "0", "0", "9급");
			dataOutStream.writeUTF("REGISTER_OK");
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	void userGamming(String _inputMassage, Socket socket) {//-----OK GAME_TAG 		
		try {
			outStream = socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(_inputMassage);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	void chatting(String _inputMassage, Socket socket) {//--------OK CHATTING_TAG
		StringTokenizer stk = new StringTokenizer(_inputMassage, "/");
		String token = stk.nextToken();
		String massage = stk.nextToken();
		
		try {
			outStream = socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF( _inputMassage);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	boolean loginChecker(String _id, String _password) {//--------OK LOGIN_TAG
		UserInformationManager uim = new UserInformationManager();
		uim.getUserLoginInformationToDB();
		boolean flag = false;
		
		for(int i = 0; i < uim.users.size(); i++) {
			if(uim.users.get(i).userID.equals(_id) && uim.users.get(i).userPassword.equals(_password)) {
				flag = true;
			}
		}
		return flag;
	}

	String getUserNickName(String _id, String _password) {//------OK LOGIN_TAG
		UserInformationManager uim = new UserInformationManager();
		uim.getUserLoginInformationToDB();
		String userNickName = null;
		
		for(int i = 0; i < uim.users.size(); i++) {
			if(uim.users.get(i).userID.equals(_id) && uim.users.get(i).userPassword.equals(_password)) {
				userNickName = uim.users.get(i).userNickName;
				Server.usersNickName.add(userNickName);
			}
		}		
		return userNickName;
	}

	void goMygameInformation(String _inputMassage, Socket otherSocket) {
		try {
			StringTokenizer stk = new StringTokenizer(_inputMassage, "/");
			String token = stk.nextToken();
			String id = stk.nextToken();
			String password = stk.nextToken();
			UserInformationManager uim = new UserInformationManager();
			String otherNickName = getUserNickName(id, password);
			
			int location = uim.getUserWinningRateToDB(otherNickName);
		
			String otherWinningGrade = uim.users.get(location).userWinningGrade;
			String otherDefeatGrade = uim.users.get(location).userDefeatGrade;
			String otherRank = uim.users.get(location).userRank;
			
			
			outStream = otherSocket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF(gameinfoTag + "/" + otherNickName + "/" + otherWinningGrade
					+ "/" + otherDefeatGrade + "/" + otherRank);
		}catch(Exception e) {
			e.getMessage();
		}
	}

	void updateUserWinningRate(String _inputMassage, Socket othersocket) {//---------- UPDATE_TAG
		StringTokenizer stk = new StringTokenizer(_inputMassage, "/");
		String token = stk.nextToken();
		String nickname1 = stk.nextToken();
		String win = stk.nextToken();
		String nickname2 = stk.nextToken();
		String defeat = stk.nextToken();
		
		UserInformationManager uim = new UserInformationManager();
		uim.updateUserWinningRate(nickname1, win, nickname2, defeat);
		System.out.println("Server.usersNickName.size : " + Server.usersNickName.size());
		Server.usersNickName.clear();;// 게임이 끝나면 접속한 유저 닉네임 정리.
		System.out.println("Server.usersNickName.size : " + Server.usersNickName.size());
		Server.uim.users.clear();// 게임이 끝나면 접속한 유저들 로그인 정보 정리.
		ClientList.clients.clear();// 게임이 끝나면 접속한 유저들 소켓정리.
		try {
			outStream = othersocket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			dataOutStream.writeUTF("GAMEFINISH");
		} catch(Exception e) {
			e.getMessage();
		}
	}
}
