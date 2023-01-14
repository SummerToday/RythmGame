package Rythm_Game;

public class Main {
	public static final int SCREEN_WIDTH=1280;
	public static final int SCREEN_HEIGH=720;
	public static final int NOTE_SPEED=7;  // 노트 자체 속도
	public static final int SLEEP_TIME=10; // 스레드를 중단하여 노트가 내려오는 속도 제어.
	
	public static void main(String[] args) {
		new Beat();
		
	}

}
  