
public class User {
	String userID;
	String userPassword;
	String userNickName;
	String userWinningGrade;
	String userDefeatGrade;
	String userRank;

	User(String _id, String _password, String _userNickName){
		userID = _id;
		userPassword = _password;
		userNickName = _userNickName;
	}
	
	User(String _userNickName, String _userWinningGrade, String _userDefeatGrade, String _userRank) {
		userNickName = _userNickName;
		userWinningGrade = _userWinningGrade;
		userDefeatGrade = _userDefeatGrade;
		userRank = _userRank;
	}
}
