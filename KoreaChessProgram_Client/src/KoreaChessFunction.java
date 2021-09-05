import java.util.StringTokenizer;

public class KoreaChessFunction {
	boolean flagKoreaChess = false;
	
	boolean checkChessName(String input, String[][] location, int x, int y) {
		StringTokenizer stk = new StringTokenizer(input, "/");
		String beforeThing = stk.nextToken().toString().trim();
		int before_x = Integer.parseInt(stk.nextToken().toString().trim());
		int before_y = Integer.parseInt(stk.nextToken().toString().trim());
		String afterThing = location[y][x];
		int after_x = x;
		int after_y = y;
		
		if(beforeThing.substring(3, beforeThing.length()).equals("Joll")) {
			flagKoreaChess = false;
			flagKoreaChess = flagJoll(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else if(beforeThing.substring(3, beforeThing.length()).equals("Pho")) {
			flagKoreaChess = false;
			flagKoreaChess = flagPho(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else if(beforeThing.substring(3, beforeThing.length()).equals("Cha")) {
			flagKoreaChess = false;
			flagKoreaChess = flagCha(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else if(beforeThing.substring(3, beforeThing.length()).equals("Sang")) {
			flagKoreaChess = false;
			flagKoreaChess = flagSang(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else if(beforeThing.substring(3, beforeThing.length()).equals("Ma")) {
			flagKoreaChess = false;
			flagKoreaChess = flagMa(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else if(beforeThing.substring(3, beforeThing.length()).equals("Sa")) {
			flagKoreaChess = false;
			flagKoreaChess = flagSaAndKing(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else if(beforeThing.substring(3, beforeThing.length()).equals("King")) {
			flagKoreaChess = false;
			flagKoreaChess = flagSaAndKing(beforeThing, afterThing
					, before_x, before_y, after_x, after_y, location);
		} else {
			System.out.println("오류!");
		}
		
		return flagKoreaChess;
	}

	boolean flagJoll(String before, String after, int x, int y, int a, int b, String[][] location) {//----- 졸 움직임 OK
		String beforeThing = before;
		int before_x = x;
		int before_y = y;
		String afterThing = after;
		int after_x = a;
		int after_y = b;
		if( (after_x - before_x == 0) && (1 == after_y - before_y) ) {
			flagKoreaChess = true;
			System.out.println("졸"+beforeThing + before_x+before_y+afterThing+after_x+after_y);
		} else if( (before_y - after_y == 0) && (-1 == after_x - before_x) || (1 == after_x - before_x) ) {
			flagKoreaChess = true;
			System.out.println("졸"+beforeThing + before_x+before_y+afterThing+after_x+after_y);
		} else {
			if((before_y == 7 && before_x == 3) || (before_y == 7 && before_x == 5)) {
				if (after_y >= before_y) {
					flagKoreaChess = true;
				} else {
					flagKoreaChess = false;
				}
			} else if(before_y == 8 && before_x == 4){
				if (after_y >= before_y) {
					flagKoreaChess = true;
				} else {
					flagKoreaChess = false;
				}
			} else {					
				flagKoreaChess = false;
			}
		}
		
		return flagKoreaChess;
	}

	boolean flagPho(String before, String after, int x, int y, int a, int b, String[][] location) {//------ 포 움직임 OK
		String beforeThing = before;
		int before_x = x;
		int before_y = y;
		String afterThing = after;
		int after_x = a;
		int after_y = b;
		if(afterThing.contains("Pho")) {
			flagKoreaChess = false;
		} else {
			if(before_x == after_x) {// 포가 Y축 이동할 때
				int count = 0;
				if(before_y < after_y) {
					for(int i = before_y + 1; i < after_y; i++) {
						if(location[i][before_x].equals("empty")) {// 비어있을 때
						} else {// 아군이든 적군이든 포는 +2 나머지는 +1
							if(location[i][before_x].contains("Pho")) {
								count+=2;
							} else {								
								count++;
							}
						}
					}
					if(count == 1) {
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				} else if(before_y > after_y) {
					for(int i = after_y + 1; i < before_y; i++) {
						if(location[i][before_x].equals("empty")) {// 비어있을 때
						} else {// 아군이든 적군이든 포는 +2 나머지는 +1
							if(location[i][before_x].contains("Pho")) {
								count+=2;
							} else {								
								count++;
							}
						}
					}
					if(count == 1) {
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				}
			} else if(before_y == after_y) {// 포가 X축 이동 할 때
				int count = 0;
				if(before_x < after_x) {
					for(int i = before_x + 1; i < after_x; i++) {
						if(location[before_y][i].equals("empty")) {// 비어있을 때
						} else {// 아군이든 상대이든 포 와 원하는 위치에 하나만 있으면 넘을 수 있음.
							if(location[before_y][i].contains("Pho")) {// Pho는 카운터를 두 개를 준다.
								count+=2;
							} else {								
								count++;
							}
						}
					}
					if(count == 1) {
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				} else if(before_x > after_x) {
					for(int i = after_x + 1; i < before_x; i++) {
						if(location[before_y][i].equals("empty")) {// 비어있을 때
						} else {// 아군이든 상대이든 포 와 원하는 위치에 하나만 있으면 넘을 수 있음.
							if(location[before_y][i].contains("Pho")) {// Pho는 카운터를 두 개를 준다.
								count+=2;
							} else {								
								count++;
							}
						}
					}
					if(count == 1) {
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				}
			} else {
				if((3 == before_x) || (before_x == 5)) {
					if( (before_y == 0) || (before_y == 2) ) {
						if (location[1][4].equals("empty")) {
							flagKoreaChess = false;
						} else {
							flagKoreaChess = true;
						}
					} else if((before_y == 9) || (before_y == 7)) {
						if (location[8][4].equals("empty")) {
							flagKoreaChess = false;
						} else {
							flagKoreaChess = true;
						}
					}
				} else {					
					flagKoreaChess = false;
				}
			}
		}
		return flagKoreaChess;
	} 
	
	boolean flagCha(String before, String after, int x, int y, int a, int b, String[][] location) {//------ 차 움직임 OK
		String beforeThing = before;
		int before_x = x;
		int before_y = y;
		String afterThing = after;
		int after_x = a;
		int after_y = b;
		
		if(before_x == after_x) {
			int count = 0;
			if(before_y < after_y) {
				for(int i = before_y + 1; i < after_y; i++) {
					if(location[i][before_x].equals("empty")) {// 비어있을 때
					} else {// 아군이든 적군이든  +1
						count++;
					}
				}
				if(count == 0) {
					flagKoreaChess = true;
				} else {
					flagKoreaChess = false;
				}
			} else if(before_y > after_y) {
				for(int i = after_y + 1; i < before_y; i++) {
					if(location[i][before_x].equals("empty")) {// 비어있을 때
					}  else {// 아군이든 적군이든  +1
						count++;
					}
				}
				if(count == 0) {
					flagKoreaChess = true;
				} else {
					flagKoreaChess = false;
				}
			}
		} else if(before_y == after_y) {
			int count = 0;
			if(before_x < after_x) {
				for(int i = before_x + 1; i < after_x; i++) {
					if(location[before_y][i].equals("empty")) {// 비어있을 때
					} else {// 아군이든 적군이든  +1
						count++;
					}
				}
				if(count == 0) {
					flagKoreaChess = true;
				} else {
					flagKoreaChess = false;
				}
			} else if(before_x > after_x) {
				for(int i = after_x + 1; i < before_x; i++) {
					if(location[before_y][i].equals("empty")) {// 비어있을 때
					} else {// 아군이든 적군이든  +1
						count++;
					}
				}
				if(count == 0) {
					flagKoreaChess = true;
				} else {
					flagKoreaChess = false;
				}
			}
		} else {
			if( (after_x >= 3 && after_x <= 5) && ((after_y >= 0 && after_y <= 2) || (after_y >= 7 && after_y <= 9))) {
				if(3 <= before_x && before_x <= 5) {
					if( (before_y == 0) || (before_y == 2) ) {
						if(location[1][4].equals("empty")) {							
							flagKoreaChess = true;
						}
					} else if((before_y == 9) || (before_y == 7)) {
						if(after_x == 4 && after_y == 8) {
							flagKoreaChess = true;
						} else {							
							if(location[8][4].equals("empty")) {							
								flagKoreaChess = true;
							}
						}
					} else if ((before_y == 1) || (before_y == 8)){
						if(after_x == 4 && after_y == 1) {
							flagKoreaChess = true;
						} else {							
							if(location[1][5].equals("empty")) {							
								flagKoreaChess = true;
							}
						}
					} else {
						flagKoreaChess = true;
					}
				} else {					
					flagKoreaChess = false;
				}
			}
		}
		return flagKoreaChess;
	}
	
	boolean flagSang(String before, String after, int x, int y, int a, int b, String[][] location) {//----- 상 움직임  OK
		String beforeThing = before;
		int before_x = x;
		int before_y = y;
		String afterThing = after;
		int after_x = a;
		int after_y = b;
		if( ((after_x == before_x + 2) || (after_x == before_x - 2) || (after_x == before_x + 3) || (after_x == before_x - 3)) ) {
			if( ((after_y == before_y + 2) || (after_y == before_y - 2) || (after_y == before_y + 3) || (after_y == before_y - 3))) {
				
				if( (after_x < before_x) && (after_y > before_y) ) {
					if(location[after_y - 1][after_x + 1].equals("empty")) {
						System.out.println(location[after_y - 1][after_x + 1]);
						if(location[after_y - 2][after_x + 2].equals("empty")) {
							System.out.println(location[after_y - 2][after_x + 2]);
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else {
						flagKoreaChess = false;
					}
				} else if( (after_x > before_x) && (after_y > before_y) ) {
					if(location[after_y - 1][after_x - 1].equals("empty")) {
						System.out.println(location[after_y - 1][after_x - 1]);
						if(location[after_y - 2][after_x - 2].equals("empty")) {
							System.out.println(location[after_y -2][after_x - 2]);
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else {
						flagKoreaChess = false;
					}
				} else if( (after_x < before_x) && (after_y < before_y) ) {
					if(location[after_y + 1][after_x + 1].equals("empty")) {
						System.out.println(location[after_y + 1][after_x + 1]);
						if(location[after_y + 2][after_x + 2].equals("empty")) {
							System.out.println(location[after_y + 2][after_x +2]);
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else {
						flagKoreaChess = false;
					}
				} else if( (after_x > before_x) && (after_y < before_y) ) {
					if(location[after_y + 1][after_x - 1].equals("empty")) {
						System.out.println(location[after_y + 1][after_x - 1]);
						if(location[after_y + 2][after_x - 2].equals("empty")) {
							System.out.println(location[after_y +2][after_x -2]);
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else {
						flagKoreaChess = false;
					}
				} else {
					flagKoreaChess = false;
				}
			} else {
				flagKoreaChess = false;
			}
		} else {
			flagKoreaChess = false;
		}
		
		return flagKoreaChess;
	}
	
	boolean flagMa(String before, String after, int x, int y, int a, int b, String[][] location) {//------- 마 움직임 OK
		String beforeThing = before;
		int before_x = x;
		int before_y = y;
		String afterThing = after;
		int after_x = a;
		int after_y = b;
		if( ((after_x == before_x + 2) || (after_x == before_x - 2) || (after_x == before_x + 1) || (after_x == before_x - 1)) ) {
			if( ((after_y == before_y + 2) || (after_y == before_y - 2) || (after_y == before_y + 1) || (after_y == before_y - 1))) {
				
				if( (after_x < before_x) && (after_y > before_y) ) {
					if(location[after_y - 1][after_x + 1].equals("empty")) {
						System.out.println(location[after_y - 1][after_x + 1]);
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				} else if( (after_x > before_x) && (after_y > before_y) ) {
					if(location[after_y - 1][after_x - 1].equals("empty")) {
						System.out.println(location[after_y - 1][after_x - 1]);
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				} else if( (after_x < before_x) && (after_y < before_y) ) {
					if(location[after_y + 1][after_x + 1].equals("empty")) {
						System.out.println(location[after_y + 1][after_x + 1]);
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				} else if( (after_x > before_x) && (after_y < before_y) ) {
					if(location[after_y + 1][after_x - 1].equals("empty")) {
						System.out.println(location[after_y + 1][after_x - 1]);
						flagKoreaChess = true;
					} else {
						flagKoreaChess = false;
					}
				} else {
					flagKoreaChess = false;
				}
			} else {
				flagKoreaChess = false;
			}
		} else {
			flagKoreaChess = false;
		}
		return flagKoreaChess;
	}
	
	boolean flagSaAndKing(String before, String after, int x, int y, int a, int b, String[][] location) {// 왕, 사 움직임 OK
		String beforeThing = before;
		int before_x = x;
		int before_y = y;
		String afterThing = after;
		int after_x = a;
		int after_y = b;
		
		if( (3 <= after_x) && (after_x <= 5) ) {
			if( (0 <= after_y) && (after_y <= 2) ) {
				if(before_x == 4 && before_y == 1) {// 중앙 부분 --> 대각선 이동 가능
					flagKoreaChess = true;
				} else if(before_x == 3 && before_y == 0) {//  꼭지점 -->  대각선 이동 가능
					if( (after_x <= 4) && (after_y <= 1) ) {
						flagKoreaChess = true;
					}
				} else if(before_x == 5 && before_y == 0) {// 꼭지점 -->  대각선 이동 가능
					if( (4 <= after_x) && (after_y <= 1) ) {
						flagKoreaChess = true;
					}
				} else if(before_x == 3 && before_y == 2) {// 꼭지점 -->  대각선 이동 가능
					if( (after_x <= 4) && (1 <= after_y) ) {
						flagKoreaChess = true;
					}
				} else if(before_x == 5 && before_y == 2) {// 꼭지점 -->  대각선 이동 가능
					if( (4 <= after_x) && (1 <= after_y) ) {
						flagKoreaChess = true;
					}
				} else if(before_x == 4 && before_y == 2) { // 모서리 부분 --> 대각선 이동 불가능.
					if(after_y == 2) {
						if(after_x == 3 || after_x == 5) {							
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else if(after_y == 1 && after_x == 4) {
						flagKoreaChess = true;
						} else {
						flagKoreaChess = false;
					}
				} else if(before_x == 4 && before_y == 0) { // 모서리 부분 --> 대각선 이동 불가능.
					if(after_y == 0) {
						if(after_x == 3 || after_x == 5) {							
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else if(after_y == 1 && after_x == 4) {
						flagKoreaChess = true;
						} else {
						flagKoreaChess = false;
					}
				} else if(before_x == 3 && before_y == 1) { // 모서리 부분 --> 대각선 이동 불가능.
					if(after_y == 1 && after_x == 4) {
						flagKoreaChess = true;
					} else if(after_x == 3) {
						if(after_y == 2 || after_y == 0) {							
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else {
						flagKoreaChess = false;
					}
				} else if(before_x == 5 && before_y == 1) { // 모서리 부분 --> 대각선 이동 불가능.
					if(after_y == 1 && after_x == 4) {
						flagKoreaChess = true;
					} else if(after_x == 5) {
						if(after_y == 2 || after_y == 0) {							
							flagKoreaChess = true;
						} else {
							flagKoreaChess = false;
						}
					} else {
						flagKoreaChess = false;
					}
				} else {
					flagKoreaChess = false;
				}
			}
		} else {
			flagKoreaChess = false;
		}
		return flagKoreaChess;
	}
}