import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread extends Thread{
	Socket socket;
	Server server;
	TagFunction tagFunction = new TagFunction();
	
	OutputStream outStream;
	DataOutputStream dataOutStream;
	InputStream inStream;
	DataInputStream dataInStream;
	
	final String loginTag = "LOGIN";
	final String register = "REGISTER";
	final String chattingTag = "CHATTING";
	final String gameTag = "GAME";
	final String gameOverTag = "GAMEOVER";
	final String updateTag = "UPDATE";
	
	static ClientList clientlist;
	
	ServerThread(Socket _socket, Server _server) {
		this.socket = _socket;
		this.server = _server;
	}
	
	public void run () {
		System.out.println("Server > " + this.socket.toString()  + "///" + this.server.toString() + "에서의 접속이 연결되었습니다.");
		String inputMassage;
		try {
			outStream = this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream = this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			while(true) {				
				inputMassage = dataInStream.readUTF();
				StringTokenizer stk = new StringTokenizer(inputMassage, "/");
				String tag = stk.nextToken();
				
				if(tag.equals(loginTag)) {
					Socket otherSocket = null;
					for(int i = 0; i < ClientList.clients.size(); i++) {
						otherSocket = ClientList.clients.get(i).socket;
						if (Server.usersNickName.size() > 0) {							
							if(otherSocket.toString().equals(socket.toString()) == false) {							
								tagFunction.goMygameInformation(inputMassage, otherSocket);
							}
						}
					}
					System.out.println("ClientList.loginName.size() : " + ClientList.loginName.size());
					if(Server.usersNickName.size() < 1) {
						System.out.println("첫 번째Server.usersNickName.size() : " + Server.usersNickName.size());
						tagFunction.userLogin(inputMassage, socket);
					} else {
						System.out.println("두 번째Server.usersNickName.size() : " + Server.usersNickName.size());
						tagFunction.userLogin(inputMassage, socket, otherSocket);
					}
				} else if(tag.equals(register)) {
					tagFunction.userRegister(inputMassage, socket);
				} else if(tag.equals(chattingTag)) {
					for(int i = 0; i < ClientList.clients.size(); i++) {
						Socket otherSocket = ClientList.clients.get(i).socket;
						if(otherSocket.toString().equals(socket.toString()) == false) {							
							tagFunction.chatting(inputMassage, otherSocket);
						}
					}
				} else if(tag.equals(gameTag)) {
					for(int i = 0; i < ClientList.clients.size(); i++) {
						Socket otherSocket = ClientList.clients.get(i).socket;
						if(otherSocket.toString().equals(socket.toString()) == false) {							
							tagFunction.userGamming(inputMassage, otherSocket);
						}
					}
				} else if(tag.equals(updateTag)) {
					for(int i = 0; i < ClientList.clients.size(); i++) {
						Socket otherSocket = ClientList.clients.get(i).socket;
						if(otherSocket.toString().equals(socket.toString()) == false) {
							tagFunction.updateUserWinningRate(inputMassage, otherSocket);
						}
					}
				} else if(tag.equals(gameOverTag)){
					for(int i = 0; i < ClientList.clients.size(); i++) {
						Socket otherSocket = ClientList.clients.get(i).socket;
						if(otherSocket.toString().equals(socket.toString()) == false) {
							tagFunction.userGamming(inputMassage, otherSocket);
						}
					}
				}else if(tag.equals("CANLOGIN")){
					try {
						outStream = socket.getOutputStream();
						dataOutStream = new DataOutputStream(outStream);
						dataOutStream.writeUTF("CANLOGIN");
					} catch(Exception e) {
						e.getMessage();
					}
				} else {
					System.out.println("TAG에 오류가 있습니다.");
				}
			}
			
		} catch(Exception e) {
			e.getMessage();
		}
	}
}
