package Rythm_Game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;



public class Music extends Thread {
	
	private Player player;     // mp3�� ������ִ� Ŭ����
	private boolean isLoop;    // ���� �ݺ� ��� ����
	private File file;         // ���������� �������� Ŭ����
	private FileInputStream fis;   // ���������� ����Ʈ ������ �о��. 
	
	public Music(String name,boolean isLoop) {
		try {
			this.isLoop=isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis=new FileInputStream(file); // ���� ������ ����Ʈ ������ �о��.
			player=new Player(fis);  // ����Ʈ ������ ���� ���������� �÷��̾� ��ü�� ����. for �������.
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if(player==null)
			return 0;
		return player.getPosition();       
	}       //���� ������ �ð��� �и��� ����(ex.1��=60000�и���)�� ��ȯ����.
	public void close() {
		isLoop= false;
		player.close();
		this.interrupt();
	}
	@Override   //�����带 ����ҽ� run() �޼ҵ�� ������ �������
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
