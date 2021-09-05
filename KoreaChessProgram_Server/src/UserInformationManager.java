import java.sql.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UserInformationManager {
	Connection connection = null;
	Statement statement = null;
	
	String url = "jdbc:mysql://localhost/koreachessuserinformation?serverTimezone=Asia/Seoul";
	String user = "root";
	String userPassword = "Daehong12!";
	static ArrayList<User> users = new ArrayList<User>();

	void getUserLoginInformationToDB(){//DB�� ����Ǿ� �ִ� ������ ������  �α��� �� �����´�.
		try {
			connection = DriverManager.getConnection(url,user, userPassword);
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM information");
			while(result.next()) {
				String userID = result.getString("id");
				String userPassword = result.getString("password");
				String userNickName = result.getString("nickname");
			
				User user = new User(userID, userPassword, userNickName);
				users.add(user);
			}
			statement.close();
			connection.close();
		}catch(Exception e) {
			System.out.println("�˻� ����");
		}
	}
	
	int getUserWinningRateToDB(String _nickName) {//�г����� ������ �г����� ��, ��, ����� DB���� �����´�. �׸��� �г����� �ִ� list�� �ε����� ��ȯ.
		String nickName = _nickName.toString().trim();
		int count = 0;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).userNickName.equals(nickName)) {
				count = i;
			}
		}
		try {
			connection = DriverManager.getConnection(url,user, userPassword);
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM usergrade WHERE nickname = '" + nickName + "'");
			while(result.next()) {
				String userWinningGrade = result.getString("win");
				String userDefeatGrade = result.getString("defeat");
				String userRank = result.getString("rank");
				
				users.get(count).userWinningGrade = userWinningGrade;
				users.get(count).userDefeatGrade = userDefeatGrade;
				users.get(count).userRank = userRank;
			}
			statement.close();
			connection.close();
			
		}catch(Exception e) {
			e.getMessage();
		}
		return count;
	}
	
	void inSertInformationFromRegister(String _name, String _nickName, String _id, String _password) {// ȸ�����Կ��� �Է¹��� ȸ�� ������ DB�� �����Ѵ�.
		String id = _id;
		String password = _password;
		String name = _name;
		String nickName = _nickName;
		
		String insertStr = "INSERT INTO information VALUES(";
		System.out.println("check\n");
		StringBuilder sb = new StringBuilder();
		String recordStr = sb.append(insertStr)   //recordStr �ۼ��� �� ���� ��α��Դϴ�. https://blog.naver.com/royjs7778/221742628657
				.append("'" + name + "',")
				.append("'" + nickName + "',")
				.append("'" + id + "',")
				.append("'" + password + "'")
				.append(");").toString();
		try {
			connection = DriverManager.getConnection(url, user, userPassword);
			statement = connection.createStatement();
			statement.executeUpdate(recordStr);			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				statement.close();
				connection.close();			
			}catch(Exception e) {
				
			}
		}
	}
	
	void inSertWinningRateFromRegister(String _nickName, String _win, String _defeat, String _rank) {// ȸ�����Կ��� �Է¹��� ȸ�� ������ DB�� �����Ѵ�.
		String nickName = _nickName;
		String win = _win;
		String defeat = _defeat;
		String rank = _rank;
		
		String insertStr = "INSERT INTO usergrade VALUES(";
		System.out.println("check\n");
		StringBuilder sb = new StringBuilder();
		String recordStr = sb.append(insertStr)   //recordStr �ۼ��� �� ���� ��α��Դϴ�. https://blog.naver.com/royjs7778/221742628657
				.append("'" + nickName + "',")
				.append("'" + win + "',")
				.append("'" + defeat + "',")
				.append("'" + rank + "'")
				.append(");").toString();
		try {
			connection = DriverManager.getConnection(url, user, userPassword);
			statement = connection.createStatement();
			statement.executeUpdate(recordStr);			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				statement.close();
				connection.close();			
			}catch(Exception e) {
				
			}
		}
	}
	
	void updateUserWinningRate(String _nickName1, String _defeat, String _nickName2, String _win) {// ���� ������ �� �� ��ũ�� DB�� �����Ѵ�.
		String nickName1 = _nickName1;
		String defeat = _defeat;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).userNickName.equals(nickName1)) {
				defeat = users.get(i).userDefeatGrade;
			}	
		}
		String nickName2 = _nickName2;
		String win = _win;
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).userNickName.equals(nickName2)) {
				win = users.get(i).userWinningGrade;
			}	
		}
		
		int winPoint = Integer.parseInt(win) + 1;
		win = "" + winPoint;
		int defeatPoint = Integer.parseInt(defeat) + 1;
		defeat = "" + defeatPoint;
		
		String insertStr1 = "UPDATE usergrade SET defeat = ";
		StringBuilder sb1 = new StringBuilder();
		String recordStr1 = sb1.append(insertStr1)
				.append("'" + defeat + "' where nickname = ")
				.append("'" + nickName1 + "'").toString();
		
		String insertStr2 = "UPDATE usergrade SET win = ";
		StringBuilder sb2 = new StringBuilder();
		String recordStr2 = sb2.append(insertStr2)
				.append("'" + win + "' where nickname = ")
				.append("'" + nickName2 + "'").toString();
		try {			
			connection = DriverManager.getConnection(url, user, userPassword);
			statement = connection.createStatement();
			statement.executeUpdate(recordStr1);
			statement = connection.createStatement();
			statement.executeUpdate(recordStr2);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				statement.close();
				connection.close();			
			}catch(Exception e) {
				
			}
		}
	}

}
