import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) {
		OutputStream outStream = null;
		DataOutputStream dataOutputStream = null;
		Scanner sc = new Scanner(System.in);
		try {
			Socket mySocket = new Socket("localhost", 7888);
			System.out.println("Client > 서버로 연결되었습니다.");
			UserThread userThread = new UserThread(mySocket);
			System.out.println("Server > " + mySocket  + "///" + "에서의 접속이 연결되었습니다.");
			
			userThread.start();
			
			UserThread.sleep(100);
			outStream = mySocket.getOutputStream();
			dataOutputStream = new DataOutputStream(outStream);
			dataOutputStream.writeUTF("CANLOGIN");
		}catch(Exception e) {
			e.getMessage();
		}
	}
}
