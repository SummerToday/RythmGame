package Rythm_Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	                      //키어댑터->리스너로 구현할 시 추상메소드들을 모두 구현해야되는 부담이 있음
	                      //이 부담을 줄이기 위해 각 리스너들은 어댑터 클래스들 존재. but 추상메소드 1개 있는 애들은
	                      //어댑터 클래스 존재x.
	@Override
	public void keyPressed(KeyEvent e) {
		if (Beat.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Beat.game.pressS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Beat.game.pressD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Beat.game.pressF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Beat.game.pressSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			Beat.game.pressJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			Beat.game.pressK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			Beat.game.pressL();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Beat.game == null) {
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Beat.game.releaseS();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			Beat.game.releaseD();
		} else if (e.getKeyCode() == KeyEvent.VK_F) {
			Beat.game.releaseF();
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Beat.game.releaseSpace();
		} else if (e.getKeyCode() == KeyEvent.VK_J) {
			Beat.game.releaseJ();
		} else if (e.getKeyCode() == KeyEvent.VK_K) {
			Beat.game.releaseK();
		} else if (e.getKeyCode() == KeyEvent.VK_L) {
			Beat.game.releaseL();
		}

	}

}
