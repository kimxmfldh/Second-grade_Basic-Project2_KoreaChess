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
			System.out.println("Client > ������ ����Ǿ����ϴ�.");
			UserThread userThread = new UserThread(mySocket);
			System.out.println("Server > " + mySocket  + "///" + "������ ������ ����Ǿ����ϴ�.");
			
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
