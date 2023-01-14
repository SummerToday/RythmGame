package Rythm_Game;

public class Rythm {
	private int time;
	private String noteName;
	
	public int getTime() {
		return time;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Rythm(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
