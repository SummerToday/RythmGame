package Rythm_Game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;



public class Music extends Thread {
	
	private Player player;     // mp3를 재생해주는 클래스
	private boolean isLoop;    // 음악 반복 재생 여부
	private File file;         // 음악파일을 가져오는 클래스
	private FileInputStream fis;   // 음악파일을 바이트 단위로 읽어옴. 
	
	public Music(String name,boolean isLoop) {
		try {
			this.isLoop=isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis=new FileInputStream(file); // 음악 파일을 바이트 단위로 읽어옴.
			player=new Player(fis);  // 바이트 단위로 읽은 음악파일을 플레이어 객체에 삽입. for 음악재생.
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if(player==null)
			return 0;
		return player.getPosition();       
	}       //현재 음악의 시간을 밀리초 단위(ex.1분=60000밀리초)로 반환해줌.
	public void close() {
		isLoop= false;
		player.close();
		this.interrupt();
	}
	@Override   //스레드를 상속할시 run() 메소드는 구현을 해줘야함
	public void run() {
		try {
			do {
				player.play();
				fis=new FileInputStream(file);
				player=new Player(fis);
			} while(isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
