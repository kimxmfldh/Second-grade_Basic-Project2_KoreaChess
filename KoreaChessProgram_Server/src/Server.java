import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {
	ServerSocket ss = null;
	UserInformationManager userManager;
	static ClientList clientlist;
	static UserInformationManager uim = new UserInformationManager();
	static ArrayList<String> usersNickName = new ArrayList<>();
	
	public static void main(String[] args) {
		Server server = new Server();
		server.userManager = new UserInformationManager();
		
		try {
			server.ss = new ServerSocket(7888);
			System.out.println("Server > Server Socket is Created...");
			
			while(true) {
				Socket socket = server.ss.accept();
				ServerThread serverThread = new ServerThread(socket, server);
				clientlist = new ClientList(serverThread);
				serverThread.start();
			}
		}catch(SocketException e) {
			System.out.println("Server > 소켓 관련 예외 발생, 서버 종료");
		}catch(IOException ioe) {
			System.out.println("Server > 입출력 예외 발생");
		}
	}
}
