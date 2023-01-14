package Rythm_Game;

import javax.swing.ImageIcon;

public class Track {
	
    private String titleImage;   
    private ImageIcon startImage;   
    private ImageIcon startImageEntered;  
    private String gameImage;    
    private String startMusic;  
    private String gameMusic;    
    private String titleName;
	
    
    public Track(String titleImage, ImageIcon startImage, ImageIcon startImageEntered, String gameImage, String startMusic, String gameMusic,String titleName) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.startImageEntered=startImageEntered;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName=titleName;
	}
	public ImageIcon getStartImageEntered() {
		return startImageEntered;
	}
	public void setStartImageEntered(ImageIcon startImageEntered) {
		this.startImageEntered = startImageEntered;
	}
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public ImageIcon getStartImage() {
		return startImage;
	}
	public void setStartImage(ImageIcon startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

}
