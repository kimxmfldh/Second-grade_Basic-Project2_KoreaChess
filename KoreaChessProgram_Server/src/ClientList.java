import java.util.ArrayList;

public class ClientList {
	public static ArrayList<ServerThread> clients = new ArrayList<>();
	public static ArrayList<String> loginName = new ArrayList<>();
	
	ClientList(ServerThread _st) {
		clients.add(_st);
	}
	
	ClientList(String _loginName) {
		loginName.add(_loginName);
	}
}
