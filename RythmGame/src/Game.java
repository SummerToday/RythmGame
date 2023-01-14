package Rythm_Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	int score = 0; // 0점 부터 시작
	int heart=3;
	Note note;

	private Image noteRouteLineImage = new ImageIcon("src/images/noteRouteLine.png").getImage(); 
	//ImageIcom 객체를 Image 형태로 반환해서 넘겨줌. + drawImage() 메소드는 Image형태로 넘겨줘야 되기 때문.
	private Image judgementLineImage = new ImageIcon("src/images/judgementLine.png").getImage();
	private Image gameInfoImage = new ImageIcon("src/images/gameInfo.png").getImage();
	private Image noteRouteSImage = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteDImage = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteFImage = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteSpace1Image = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteSpace2Image = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteJImage = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteKImage = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image noteRouteLImage = new ImageIcon("src/images/noteRoute.png").getImage();
	private Image heartImage = new ImageIcon("src/images/heart.png").getImage();
	private Image purpleFlareImage;
	private Image judgeImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);    // 해당 음악 제목의 음악을 Music 클래스에 
		                                                  // 담음
		gameMusic.start();  //게임 음악 재생.
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(heartImage,1000,673,null );         // 紐⑹��(����) �대�몄�
		g.drawImage(judgementLineImage, 0, 580, null); // �명�� ���� �쇱��
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon("src/images/Miss.png").getImage();
			}
			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} // 떨어지는 노트들 하나씩 제거해서 메모리 확보.
			else {
				note.screenDraw(g);   //노트 클래스에 있는 노트를 그려주는 메소드 호출
			}
		} // 떨어지는 노트를 그려주고 미스 판정 이미지 설정.
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    //글씨 외곽선을 부드럽게 해줌-> 디자인적 요소.  확대해서보면 없는 것과 차이가 큼.
		g.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(Integer.toString(score), 680, 702); // ��������
		g.setFont(new Font("맑은 고딕", Font.PLAIN, 30)); 
		g.drawString(Integer.toString(heart), 1050, 702); //紐⑹�� 媛��� ����
		g.drawImage(purpleFlareImage, 400, 310, null);
		g.drawImage(judgeImage, 460, 420, null);

	}
    //키 리스너로부터 이벤트가 발생하면 아래 메소드들 실행.
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon("src/images/noteRoutePressed.png").getImage();
		noteRouteSpace2Image = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon("src/images/noteRoute.png").getImage();
		noteRouteSpace2Image = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon("src/images/noteRoutePressed.png").getImage();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon("src/images/noteRoute.png").getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName, this.difficulty);    //해당 곡과 해당 난이도의 곡의 노트를 떨어트림.
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName, String difficulty) {
		Rythm[] rythm = null;
		if (titleName.equals("Fitz & the Tantrums - HandClap") && difficulty.equals("Easy")) {
			// 異�媛� �쇰━ �ㅼ�� 遺�遺�(����) ex.諛��� 怨��� �쇰━,�щ�� ����
			
            rythm=new Rythm[] {
			new Rythm(1000, "S"), new Rythm(2000, "D"), new Rythm(2800, "F"),
					new Rythm(3600, "F"), new Rythm(5800, "F"), new Rythm(6600, "F"), new Rythm(7000, "Space"),
					new Rythm(7900, "Space"), new Rythm(8900, "Space"), new Rythm(9500, "Space"),
					new Rythm(10500, "Space"), new Rythm(11300, "Space"), new Rythm(13000, "J"), new Rythm(14000, "K"),
					new Rythm(15000, "L"), new Rythm(16000, "S"), new Rythm(17000, "D"), new Rythm(18000, "F"),
					new Rythm(19000, "J"), new Rythm(20000, "K"), new Rythm(21000, "L"), new Rythm(22000, "S"),

					new Rythm(23000, "J"), new Rythm(24000, "K"), new Rythm(25000, "L"), new Rythm(26000, "S"),
					new Rythm(27000, "D"), new Rythm(28000, "F"), new Rythm(29000, "Space"), new Rythm(29500, "Space"),
					new Rythm(30000, "Space"), new Rythm(30500, "Space"), new Rythm(31500, "Space"),
					new Rythm(32000, "Space"), new Rythm(32500, "Space"),
					new Rythm(37000, "J"), new Rythm(40000, "K"), new Rythm(42000, "L"), new Rythm(46000, "S"),
					new Rythm(48000, "D"), new Rythm(50000, "F"), new Rythm(52000, "Space"), new Rythm(56000, "Space"),
					new Rythm(59000, "Space"), new Rythm(61000, "Space"), new Rythm(61500, "Space"),
					new Rythm(65000, "Space"), new Rythm(67500, "Space"), 
            };
		
		} 
		else if (titleName.equals("Fitz & the Tantrums - HandClap") && difficulty.equals("Hard")) {
			
			 rythm=new Rythm[] {
						new Rythm(1000, "S"), new Rythm(2000, "D"), new Rythm(2800, "F"),
								new Rythm(3600, "F"), new Rythm(5800, "F"), new Rythm(6600, "F"), new Rythm(7000, "Space"),
								new Rythm(7900, "Space"), new Rythm(8900, "Space"), new Rythm(9500, "Space"),
								new Rythm(10500, "Space"), new Rythm(11300, "Space"), new Rythm(13000, "J"), new Rythm(14000, "K"),
								new Rythm(15000, "L"), new Rythm(16000, "S"), new Rythm(17000, "D"), new Rythm(18000, "F"),
								new Rythm(19000, "J"), new Rythm(20000, "K"), new Rythm(21000, "L"), new Rythm(22000, "S"),

								new Rythm(23000, "J"), new Rythm(24000, "K"), new Rythm(25000, "L"), new Rythm(26000, "S"),
								new Rythm(27000, "D"), new Rythm(28000, "F"), new Rythm(29000, "Space"), new Rythm(29500, "Space"),
								new Rythm(30000, "Space"), new Rythm(30500, "Space"), new Rythm(31500, "Space"),
								new Rythm(32000, "Space"), new Rythm(32500, "Space"),new Rythm(37000, "J"), new Rythm(40000, "K"), new Rythm(42000, "L"), new Rythm(46000, "S"),
								new Rythm(48000, "D"), new Rythm(50000, "F"), new Rythm(52000, "Space"), new Rythm(56000, "Space"),
								new Rythm(59000, "Space"), new Rythm(61000, "Space"), new Rythm(61500, "Space"),
								new Rythm(65000, "Space"), new Rythm(67500, "Space"),
			            };
			//준비중
		} 
		else if (titleName.equals("Anne-Marie - 2002") && difficulty.equals("Easy")) {
			
			rythm = new Rythm[] { new Rythm(1000, "S")};
			//준비중
		} 
		else if (titleName.equals("Anne-Marie - 2002") && difficulty.equals("Hard")) {
			
			rythm = new Rythm[] { new Rythm(1000, "S")};
			//준비중
		} 
		else if (titleName.equals("024 노을 - 늦은 밤 너의 집 앞 골목길에서") && difficulty.equals("Easy")) {
			
			rythm = new Rythm[] { new Rythm(1000, "S")};
			//준비중
		} 
		else if (titleName.equals("024 노을 - 늦은 밤 너의 집 앞 골목길에서") && difficulty.equals("Hard")) {
			rythm = new Rythm[] { new Rythm(1000, "S")};
			//준비중
		} 

		int i = 0;
		while (true) {
			if (rythm[i].getTime() <= gameMusic.getTime()) {    //게임 음악의 시간보다 노트의 시간값이 작으면 노트를 떨어트림.
				Note note = new Note(rythm[i].getNoteName());
				note.start();      //노트를 떨어트림 노트 쓰레드 시작 Note 클래스의 run() 호출
				noteList.add(note);      //  노트들의 값을 판정하기위해 리스트에 넣어주어 하나씩 꺼내어 판정.
				i++;                     // 어차피 노트의 시간값이 음악보다 크면 i++이 안되서 노트가 떨어지지 않음.
			}
		} 
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	public void judgeEvent(String judge) {
		if (judge.equals("Miss")) {
			purpleFlareImage = new ImageIcon("src/images/purpleFlare.png").getImage();
			judgeImage = new ImageIcon("src/images/Miss.png").getImage();
		} else if (judge.equals("Late")) {
			purpleFlareImage = new ImageIcon("src/images/purpleFlare.png").getImage();
			judgeImage = new ImageIcon("src/images/Late.png").getImage();
		} else if (judge.equals("Good")) {
			score++; // 1�� 異�媛�
			purpleFlareImage = new ImageIcon("src/images/purpleFlare.png").getImage();
			judgeImage = new ImageIcon("src/images/Good.png").getImage();
		} else if (judge.equals("Great")) {
			score += 2; // 2�� 異�媛�
			purpleFlareImage = new ImageIcon("src/images/purpleFlare.png").getImage();
			judgeImage = new ImageIcon("src/images/Great.png").getImage();
		} else if (judge.equals("Perfect")) {
			score += 3; // 3�� 異�媛�
			purpleFlareImage = new ImageIcon("src/images/purpleFlare.png").getImage();
			judgeImage = new ImageIcon("src/images/Perfect.png").getImage();
		} else if (judge.equals("Early")) {
			purpleFlareImage = new ImageIcon("src/images/purpleFlare.png").getImage();
			judgeImage = new ImageIcon("src/images/Early.png").getImage();
		}else if (judge.equals("Null")) {
			purpleFlareImage = new ImageIcon("src/images/clean.png").getImage();
			judgeImage = new ImageIcon("src/images/clean.png").getImage();
		}  //null 값이 넘어올시 투명 이미지로 설정하여 플레어와 판정 이미지를 안보이게 해줌.
	}
}
