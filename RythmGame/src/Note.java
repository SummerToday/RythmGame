package Rythm_Game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
    // ������ ���� ����: �츮�� ��Ƣ��� ���ӿ��� Ÿ�̸Ӹ� ���� �� ó�� �����̴� ȿ���� �ֱ� ����.
	// ������ ���� �� ��Ʈ�� �������� ȿ���� �� �� ����. ���� ���۽� ��Ʈ�� �ȶ������� �ٷ� ������.
	private Image noteBasicImage = new ImageIcon("src/images/noteBasic.png").getImage();
	private int x, y = -120; //y��= ��Ʈ�� �������� �����ϴ� ��ġ.-> �������� 580�� ��ġ
	                         //->1�ʿ� 700��ŭ ���������� �����س����� -120���� �����ϸ� 1�ʵ� 580�� ����.
	                         //-120+700=580.
	private String noteType;
	private boolean proceeded = true;     //��Ʈ ���࿩��
	public static int missNumber; // �̽� Ƚ�� 3ȸ �̻�� ���ӿ���
	public static boolean gameOver; // ���ӿ��� ����
	private Image gameOverImage = new ImageIcon("src/images/gameOver.png").getImage();

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;
	} //��Ʈ ����x.

	public Note(String noteType) {
		if (noteType.equals("S")) {
			x = 228;                         //��Ʈ�� �������� ��ġ
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
			g.drawImage(noteBasicImage, x, y, null); // �����̽��� �ƴҽ� ��Ʈ 1ĭ ����Ʈ��
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null); // �����̽��� ��� 2ĭ ����Ʈ��.
		}
		if (gameOver) {
			g.drawImage(gameOverImage, 0, 0, null); // ���ӿ��� ��� ���
			Beat.game.judgeEvent("Null");           // Null ���� �Ѱܼ� ������ ��Ʈ ���� ����Ʈ ����ȭ.
		}
                 // ��Ʈ�� �׷���.  y���� drop�Լ��� ���� ����
	}

	public void drop() {
		y += Main.NOTE_SPEED; // ��Ʈ�� 7�� �̵�.
		if (y > 620) {
			System.out.println("Miss");
			missNumber++;                //  �̽��Ͻ� �̽� Ƚ��+1
		    Beat.game.heart--;           //  �̽��Ͻ� ���-1
			close();                     // ��Ʈ�� �������� �Ʒ��� �������� ����. �����ٽ� �ٷ� �̽� Ƚ�� �ʰ�
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (missNumber == 3) {
					interrupt(); // �ش� ������ ����
					Beat.game.close(); // ���� ���� ����+ ���� ������ ����
					gameOver = true; // ���� ���� ȭ�� ��¿���->o
				}
				drop();
				if (proceeded) {
					Thread.sleep(Main.SLEEP_TIME);     // ������ �⺻ 0.001�ʸ��� �۵�
					                       //10�и��ʸ��� �۵�->0.01 ���� �۵�.->1�ʿ� 100�� �۵�->1�ʿ� 700��ŭ ������
					                       //bc. ��Ʈ ��ü �ӵ��� 7. 7*100 
				} else {
					interrupt(); //   �ش� ������ �����Ͽ� ��Ʈ ����Ʈ�� ����.
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
