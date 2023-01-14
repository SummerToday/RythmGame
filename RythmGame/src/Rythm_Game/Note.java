package Rythm_Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
    // 스레드 구현 이유: 우리가 공튀기기 게임에서 타이머를 쓰는 것 처럼 움직이는 효과를 주기 위해.
	// 스레드 없을 시 노트가 떨어지는 효과를 줄 수 없음. 게임 시작시 노트가 안떨어지고 바로 판정됨.
	private Image noteBasicImage = new ImageIcon("src/images/noteBasic.png").getImage();
	private int x, y = -120; //y값= 노트가 떨어지기 시작하는 위치.-> 판정라인 580에 위치
	                         //->1초에 700만큼 떨어지도록 설정해놨으니 -120으로 설정하면 1초뒤 580에 도달.
	                         //-120+700=580.
	private String noteType;
	private boolean proceeded = true;     //노트 진행여부
	public static int missNumber; // 미스 횟수 3회 이상시 게임오버
	public static boolean gameOver; // 게임오버 여부
	private Image gameOverImage = new ImageIcon("src/images/gameOver.png").getImage();

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	} //노트 진행x.

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;                         //노트가 떨어지는 위치
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null); // 스페이스가 아닐시 노트 1칸 떨어트림
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null); // 스페이스일 경우 2칸 떨어트림.
		}
		if (gameOver) {
			g.drawImage(gameOverImage, 0, 0, null); // 게임오버 배경 출력
			Beat.game.judgeEvent("Null");           // Null 값을 넘겨서 마지막 노트 판정 이펙트 투명화.
		}
                 // 노트를 그려줌.  y값은 drop함수를 통해 조정
	}

	public void drop() {
		y += Main.NOTE_SPEED; // 노트가 7씩 이동.
		if (y > 620) {
			System.out.println("Miss");
			missNumber++;                //  미스일시 미스 횟수+1
		    Beat.game.heart--;           //  미스일시 목숨-1
			close();                     // 노트가 판정라인 아래로 떨어질때 정지. 안해줄시 바로 미스 횟수 초과
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (missNumber == 3) {
					interrupt(); // 해당 스레드 종료
					Beat.game.close(); // 게임 음악 종료+ 게임 스레드 종료
					gameOver = true; // 게임 오버 화면 출력여부->o
				}
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);     // 쓰레드 기본 0.001초마다 작동
					                       //10밀리초마다 작동->0.01 마다 작동.->1초에 100번 작동->1초에 700만큼 떨어짐
					                       //bc. 노트 자체 속도가 7. 7*100 
				} else {
					interrupt(); //   해당 스레드 종료하여 노트 떨어트림 중지.
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	public String judge() {
		if (y >= 613) {
			System.out.println("Late");
			close();
			return "Late";
		} else if (y >= 600) {
			System.out.println("Good");
			close();
			return "Good";
		} else if (y >= 587) {
			System.out.println("Great");
			close();
			return "Great";
		} else if (y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		} else if (y >= 565) {
			System.out.println("Great");
			close();
			return "Great";
		} else if (y >= 550) {
			System.out.println("Good");
			close();
			return "Good";
		} else if (y >= 535) {
			System.out.println("Early");
			close();
			return "Early";
		} else
			return "None";
	}

	public int getY() {
		return y;
	}

}
