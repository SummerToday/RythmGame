package Rythm_Game;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Beat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon("src/images/exitButtonEnteredImage.png");
	private ImageIcon exitButtonBasicImage = new ImageIcon("src/images/exitButtonBasicImage.jpg");
	private ImageIcon startButtonBasicImage = new ImageIcon("src/images/startButtonBasic.png");
	private ImageIcon startButtonEnteredImage = new ImageIcon("src/images/startButtonEntered.png");
	private ImageIcon quitButtonBasicImage = new ImageIcon("src/images/quitButtonBasic.png");
	private ImageIcon quitButtonEnteredImage = new ImageIcon("src/images/quitButtonEntered.png");

	private ImageIcon leftButtonBasicImage = new ImageIcon("src/images/leftButtonBasic.png");
	private ImageIcon leftButtonEnteredImage = new ImageIcon("src/images/leftButtonEntered.png");
	private ImageIcon rightButtonBasicImage = new ImageIcon("src/images/rightButtonBasic.png");
	private ImageIcon rightButtonEnteredImage = new ImageIcon("src/images/rightButtonEntered.png");

	private ImageIcon easyButtonBasicImage = new ImageIcon("src/images/easyButtonBasic.png");
	private ImageIcon easyButtonEnteredImage = new ImageIcon("src/images/easyButtonEntered.png");
	private ImageIcon hardButtonBasicImage = new ImageIcon("src/images/hardButtonBasic.png");
	private ImageIcon hardButtonEnteredImage = new ImageIcon("src/images/hardButtonEntered.png");
	private ImageIcon backButtonBasicImage = new ImageIcon("src/images/backButtonBasic.png");
	private ImageIcon backButtonEnteredImage = new ImageIcon("src/images/backButtonEntered.png");
	
	private ImageIcon createUserButtonbBasicImage = new ImageIcon("src/images/CreateUser.png");
	private ImageIcon createUserButtonEnteredImage = new ImageIcon("src/images/CreateUser(entered).png");
	private ImageIcon createButtonBasicImage = new ImageIcon("src/images/create.png");
	private ImageIcon createButtonEnteredImage = new ImageIcon("src/images/create(entered).png");

	private Image backGround = new ImageIcon("src/images/tiesto_concert__musical_freedom-wallpaper-1280x800.jpg")
			.getImage();
	private JLabel menuBar = new JLabel(new ImageIcon("src/images/menuBar.png"));
	
	private JButton createButton= new JButton(createButtonBasicImage);
	private JButton createUserButton= new JButton(createUserButtonbBasicImage);
	private JButton exitButton = new JButton(exitButtonEnteredImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton selectedImageButton;
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage); // ��ư��, ���� �̹��� ����.
	private JButton loginbackButton = new JButton(backButtonBasicImage);
	private ImageIcon profile=new ImageIcon("src/images/profile.png");
	private JLabel profileLb=new JLabel(" ");
	private JLabel idLb = new JLabel("ID :");
	private JLabel pwLb = new JLabel("PW :");
	private JLabel nameLb = new JLabel("NAME :");
	private JLabel birthdateLb = new JLabel("BIRTHDATE :");
	private JLabel nickNameLb = new JLabel("NICKNAME :");
	private JLabel pwCheckLb = new JLabel("PW CHECK :");
	private JLabel showId;//�г������� �ٲٱ�.
	private JTextField idInput;
	private JTextField nameInput;
	private JTextField birthdateInput;
	private JTextField nickNameInput;
	private JPasswordField pwInput;
	private JPasswordField pwCheckInput;
	private boolean isMainScreen = false; // ���� ��ũ��(�� ���� ȭ��) ����
	private boolean isGameScreen = false; // ���� ȭ��(�÷��� ȭ��) ����
	private boolean isLoginScreen = true; // �α��� ȭ������ ����.
	private boolean isCreateUserScreen = false; // ȸ������ ȭ�� ��ȯ ����.
	/* ��ũ�� ���� ���� ���� ������
	 * �������� ��ũ�� ������ true ���� ������ ������ ��ũ���� �Ѿ�� ����.
	 * ���� ������ ȭ���� �ȳѾ�� ���� ��κ� ��ũ�� ������ �ߺ����� true�� ������ �Ǿ��ִ� ���. 
	 */
	
	
	
	ArrayList<Track> trackList = new ArrayList<>(); // ������ ���� ����Ʈ.

	private Image titleImage;
	private Music selectedMusic;
	private Music introMusic = new Music(
			"Dillon Francis_Sulta.. - 02 - When We Were Young (Vicetone Remix) (Feat...-201411.mp3", true); // ��Ʈ�� ���� �ݺ�
																											// ����.
	private int nowSelected = 0; // ���� ���õ� ������ ��ȣ.
	public static Game game; // ���� ���� ���� ����.
	
	//DB ���� �κ� 
	Connection conn = null; // DB����� ����(����)�� ���� ��ü
    PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
    ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü

	public Beat() {
		setUndecorated(true); // Ÿ��Ʋ �ٸ� ����. for. ���ο� Ÿ��Ʋ�ٸ� Ż���ֱ� ����
		setTitle("Rythm Game Beta");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGH);
		setResizable(false); // ������ ����� ����ڰ� ���� ���ϵ��� ��.
		setLocationRelativeTo(null); // â�� ȭ�� ����� �����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // ������ ���� ���� ����. 4��° �μ��� ���� ������.
		setLayout(null); // ������Ʈ ��ġ�� ����ڰ� ����.
		setVisible(true);
		addKeyListener(new KeyListener()); // Ű �̺�Ʈ ���� �޼ҵ� ����� ���� Ű������ ��ü�� �Ѱ���.

		introMusic.start(); // ��Ʈ�� ���� ����.

		trackList.add(new Track("Fitz & the Tantrums - HandClap(title).png",
				new ImageIcon("src/images/18. Fitz & the Tantrums - HandClap.png"),
				new ImageIcon("src/images/18. Fitz & the Tantrums - HandClap Entered.png"),
				"18. Fitz & the Tantrums - HandClap(���).png", "18. Fitz & the Tantrums - HandClap selected.mp3",
				"18. Fitz & the Tantrums - HandClap sample.mp3", "Fitz & the Tantrums - HandClap"));
		trackList.add(new Track("Anne-Marie - 2002(title).png",
				new ImageIcon("src/images/041 Anne-Marie - 2002.png"),
				new ImageIcon("src/images/041 Anne-Marie - 2002 Entered not yet.png"),
				"041 Anne-Marie - 2002(���).png", "041 Anne-Marie - 2002 selected.mp3", "041 Anne-Marie - 2002.mp3",
				"Anne-Marie - 2002"));
		trackList.add(new Track("���� - ���� �� ���� �� �� ���濡��(title).png",
				new ImageIcon("src/images/024 ���� - ���� �� ���� �� �� ���濡��.png"),
				new ImageIcon("src/images/024 ���� - ���� �� ���� �� �� ���濡��Entered not yet.png"),
				"024 ���� - ���� �� ���� �� �� ���濡��(���).png", "024 ���� - ���� �� ���� �� �� ���濡�� selected.mp3",
				"024 ���� - ���� �� ���� �� �� ���濡��.mp3", "024 ���� - ���� �� ���� �� �� ���濡��"));
		//�α��� ȭ�� ���� ����

		createUserButton.setBounds(625,600,100,25);
		createUserButton.setBorderPainted(false);
		createUserButton.setContentAreaFilled(false); // ��ư ���� ��� ����
		createUserButton.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		add(createUserButton);
		profileLb.setText(null);
		profileLb.setIcon(profile);
		profileLb.setBounds(0, 0, 30, 30);
		idInput = new JTextField(20);
		idInput.setBackground(Color.DARK_GRAY);
		idInput.setForeground(Color.YELLOW);
		idInput.setBounds(540, 430, 300, 50);
		add(idInput);
		idInput.requestFocus();
		pwInput = new JPasswordField(20);
		pwInput.setBackground(Color.DARK_GRAY);
		pwInput.setForeground(Color.YELLOW);
		pwInput.setBounds(540, 530, 300, 50);
		add(pwInput);
		pwInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passWord = new String(pwInput.getPassword());
				if (idInput.getText().equals("20195125") && passWord.equals("1234")) {
					JOptionPane.showMessageDialog(null, "WELCOME");
					add(profileLb);
					showId=new JLabel(idInput.getText()+" WELCOME BACK!!");
					showId.setForeground(Color.RED);
					showId.setFont(new Font("���� ���", Font.BOLD, 15)); // ��ġ+ũ�⼳��
					showId.setBounds(35, 0, 300, 40);
					add(showId);                  
					idInput.setVisible(false);   //ID �Է�â ����
					pwInput.setVisible(false);   //PW �Է�â ����
					idLb.setVisible(false);      //ID �� ����
					pwLb.setVisible(false);      //PW �� ����
					createUserButton.setVisible(false); //���� ��ư ����
					isLoginScreen=false;          // �α��� �Ϸ� �� �α��� ȭ�� ����.
					quitButton.setVisible(true);
					startButton.setVisible(true);		
					backGround=new ImageIcon(
							"src/images/tiesto_concert__musical_freedom-wallpaper-1280x800.jpg").getImage(); 
							//���� �ʱ� ������� �ٽ� ����.
				} else {
					JOptionPane.showMessageDialog(null, "Faild");
				}
			}
		});

		
		createUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createUserButton.setIcon(createUserButtonEnteredImage);
				createUserButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ��ư�� ������ �� ���콺 Ŀ�� ��� ����
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createUserButton.setIcon(createUserButtonbBasicImage);
				createUserButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ��ư���� ������ �� ���콺 Ŀ�� ��� �⺻ ����.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // ���콺�� ������ �� ȿ���� ����. �ݺ�x
				buttonEnteredMusic.start(); // ȿ���� ����.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // �� ����� ������ �Ҹ��� ���� ���� ������ â�� ������ �Ҹ��� �ȵ鸲 �׷��� 1000�и���=1�� ���� �����带 ������.->�Ҹ��� ����
				isLoginScreen=false;    //true�� ���� ��� ȭ�� �ȳѾ.
				isCreateUserScreen = true;
				idInput.setText("");
				pwInput.setText("");
				idInput.requestFocus();
			} // ���� Ŭ������ �̿��Ͽ� �ּ� ����.

		});
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createButton.setIcon(createButtonEnteredImage);
				createButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ��ư�� ������ �� ���콺 Ŀ�� ��� ����
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createButton.setIcon(createButtonBasicImage);
				createButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ��ư���� ������ �� ���콺 Ŀ�� ��� �⺻ ����.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // ���콺�� ������ �� ȿ���� ����. �ݺ�x
				buttonEnteredMusic.start(); // ȿ���� ����.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // �� ����� ������ �Ҹ��� ���� ���� ������ â�� ������ �Ҹ��� �ȵ鸲 �׷��� 1000�и���=1�� ���� �����带 ������.->�Ҹ��� ����
			} // ���� Ŭ������ �̿��Ͽ� �ּ� ����.

		});
		loginbackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginbackButton.setIcon(backButtonEnteredImage);
				loginbackButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ��ư�� ������ �� ���콺 Ŀ�� ��� ����
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginbackButton.setIcon(backButtonBasicImage);
				loginbackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ��ư���� ������ �� ���콺 Ŀ�� ��� �⺻ ����.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // ���콺�� ������ �� ȿ���� ����. �ݺ�x
				buttonEnteredMusic.start(); // ȿ���� ����.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // �� ����� ������ �Ҹ��� ���� ���� ������ â�� ������ �Ҹ��� �ȵ鸲 �׷��� 1000�и���=1�� ���� �����带 ������.->�Ҹ��� ����
				isCreateUserScreen = false;
				isLoginScreen = true;
				pwCheckInput.setVisible(false);
				nameInput.setVisible(false);
				birthdateInput.setVisible(false);
				nickNameInput.setVisible(false);
				idInput.setBounds(540, 430, 300, 50);
				pwInput.setBounds(540, 530, 300, 50);
				idInput.setText("");
				pwInput.setText("");
				idInput.requestFocus();
			} // ���� Ŭ������ �̿��Ͽ� �ּ� ����.

		});
		//�α��� ȭ�� ����.
		exitButton.setBounds(1250, 0, 30, 30); // setBounds(x,y,w,h) x,y:��ġ ��ǥ w,h: ũ�� ����
		exitButton.setBorderPainted(false); // ��ư �׵θ� ����
		exitButton.setContentAreaFilled(false); // ��ư ���� ��� ����
		exitButton.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ��ư�� ������ �� ���콺 Ŀ�� ��� ����
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ��ư���� ������ �� ���콺 Ŀ�� ��� �⺻ ����.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // ���콺�� ������ �� ȿ���� ����. �ݺ�x
				buttonEnteredMusic.start(); // ȿ���� ����.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // �� ����� ������ �Ҹ��� ���� ���� ������ â�� ������ �Ҹ��� �ȵ鸲 �׷��� 1000�и���=1�� ���� �����带 ������.->�Ҹ��� ����
					// exit �޼ҵ�
					// ���� �� ����.
				System.exit(0);
			} // ���� Ŭ������ �̿��Ͽ� �ּ� ����.

		});

		add(exitButton);

		startButton.setBounds(850, 600, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain(); // �� ���� ȭ������ ��ȯ.
			}

		});

		add(startButton);

		quitButton.setBounds(30, 600, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}

		});

		add(quitButton);

		// ��������� ���� �ʱ�ȭ�� UI����

		leftButton.setVisible(false); // ���� ���� ���� ��ư ���� �Ⱥ���.->���� ȭ������ �Ѿ�� ���̰�.
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addKeyListener(new KeyListener());
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft(); // �������� ��ư Ŭ���� �뷡 �Ѿ.
			}

		});

		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addKeyListener(new KeyListener());
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}

		});

		add(rightButton);

		menuBar.setBounds(0, 0, 1280, 30);
		add(menuBar); // �ϴܿ� �뷡 ���� ������ִ� �� �־��ֱ�.

		selectedImageButton = new JButton(trackList.get(nowSelected).getStartImage()); // �ش� Ʈ���� �ٹ� ��Ʈ ��ư
		selectedImageButton.setVisible(false);
		selectedImageButton.setBounds(415, 100, 450, 450);
		selectedImageButton.setBorderPainted(false);
		selectedImageButton.setContentAreaFilled(false);
		selectedImageButton.setFocusPainted(false);
		selectedImageButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				selectedImageButton.setIcon(trackList.get(nowSelected).getStartImageEntered());
				selectedImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				selectedImageButton.setIcon(trackList.get(nowSelected).getStartImage());
				selectedImageButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				leftButton.setVisible(false);
				rightButton.setVisible(false);
				selectedImageButton.setVisible(false);
				easyButton.setVisible(true);
				hardButton.setVisible(true);
				backGround = new ImageIcon("src/images/mainBackgroundBlacked.png").getImage();
				isMainScreen = false;
				requestFocus(); // Ű �̺�Ʈ�� ���� �� �ֵ��� ������ ��Ŀ�� ����.
				selectedMusic.close();
				Note.missNumber = 0; // �̽� Ƚ�� 0���� ����
				Note.gameOver = false; // ���ӿ��� ���� x
			}
		});
		add(selectedImageButton); // �ٹ� ǥ�� ��ư ����-> �ٹ� ǥ�� ��ư ������ ���ȭ�� �ٲ�� ���̵� ���� ��ư ����.

		easyButton.setVisible(false);
		easyButton.setBounds(250, 200, 300, 300);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}

		});
		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(750, 200, 300, 300);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(15, 40, 50, 50); // ��ư ��ġ�� ũ�� ����.
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("backPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}

		});
		add(backButton);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGH);
		screenGraphic = screenImage.getGraphics();
		// â�� �׷��ִ� ����.
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	} 

	public void screenDraw(Graphics2D g) { 
		g.drawImage(backGround, 0, 0, null);
		if (isLoginScreen) {
			loginScreen();

		}
		else if (isMainScreen) {
			g.drawImage(titleImage, 390, 600, null);
		} // �� ���� ȭ�� �ϴܿ� �� ������ �׷���. bc. Image���̱� ������ drawImage() �����
		else if (isGameScreen) {
			game.screenDraw(g); //���� �÷��� ���� ȭ���Ͻ� game Ŭ������ ���� ���� ȭ���� �׷��ִ� �޼ҵ� ȣ��. 
		}
		else if(isCreateUserScreen) {
			createUserScreen();
		}
		
		paintComponents(g); // ������Ʈ���� �׷���.
		this.repaint();     //����� ���׵��� ��Ȯ�� �ٽ� �׷���. �ȱ׷��� �׸� �߷��� ������ �̻�����.
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		this.nowSelected = nowSelected; // ������ Ʈ�� �ѹ��� ����� Ʈ���� �ѹ��� ����.
		titleImage = new ImageIcon("src/images/" + trackList.get(nowSelected).getTitleImage()).getImage(); // ǥ�õǴ� �ٹ���Ʈ��
																											// �̹����� �����
																											// Ʈ���� �ٹ���Ʈ��
																											// ����
		selectedImageButton.setIcon(trackList.get(nowSelected).getStartImage()); // ����Ʈ�� �ش����� �޼ҵ� ���
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start(); // �ش� Ʈ���� ���� �Ǿ��� ���� ���̶���Ʈ ���� ���.

	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // 0�� Ʈ���Ͻ� �ٽ� �ǳ� Ʈ������ �Ѿ.
		else
			nowSelected--; // ���� Ʈ������ �̵�
		selectTrack(nowSelected); // �ش� Ʈ���� ������ ����ȭ�� ����.
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void createUserScreen() {   //ȸ������ ȭ�� ����
		quitButton.setVisible(false);
		startButton.setVisible(false);
		backGround = new ImageIcon("src/images/loginScreen.png").getImage();
		createUserButton.setVisible(false);
		pwCheckLb.setForeground(Color.YELLOW);
		pwCheckLb.setFont(new Font("���� ���", Font.BOLD, 30)); // ��ġ+ũ�⼳��
		pwCheckLb.setBounds(330, 240, 400, 100);
		add(pwCheckLb);
		nameLb.setForeground(Color.YELLOW);
		nameLb.setFont(new Font("���� ���", Font.BOLD, 30)); // ��ġ+ũ�⼳��
		nameLb.setBounds(395, 310, 400, 100);
		add(nameLb);
		birthdateLb.setForeground(Color.YELLOW);
		birthdateLb.setFont(new Font("���� ���", Font.BOLD, 30)); // ��ġ+ũ�⼳��
		birthdateLb.setBounds(320, 380, 400, 100);
		add(birthdateLb);
		nickNameLb.setForeground(Color.YELLOW);
		nickNameLb.setFont(new Font("���� ���", Font.BOLD, 30)); // ��ġ+ũ�⼳��
		nickNameLb.setBounds(325, 450, 400, 100);
		add(nickNameLb);
		idLb.setFont(new Font("���� ���", Font.BOLD, 30));
		idLb.setBounds(450, 100, 100, 100); //id���̺� ��ġ ����
		pwLb.setFont(new Font("���� ���", Font.BOLD, 30));
		pwLb.setBounds(435, 170, 200, 100); //pw���̺� ��ġ ����
		
		idInput.setBounds(540, 130, 300, 40);
		pwInput.setBounds(540, 200, 300, 40);
		createButton.setBounds(625,570,100,40);
		createButton.setBorderPainted(false);
		createButton.setContentAreaFilled(false); // ��ư ���� ��� ����
		createButton.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		add(createButton);
		loginbackButton.setBounds(15,40,50,50);
		loginbackButton.setBorderPainted(false);
		loginbackButton.setContentAreaFilled(false); // ��ư ���� ��� ����
		loginbackButton.setFocusPainted(false); // ��Ŀ�� ǥ�� ����
		add(loginbackButton);
		pwCheckInput.setVisible(true);
		nameInput.setVisible(true);
		birthdateInput.setVisible(true);
		nickNameInput.setVisible(true);
		createButton.setVisible(true);
		createUserButton.setVisible(false);
		loginbackButton.setVisible(true);
		pwCheckLb.setVisible(true);
		nameLb.setVisible(true);
		birthdateLb.setVisible(true);
		nickNameLb.setVisible(true);
	}

	public void loginScreen() {

		pwCheckInput = new JPasswordField(20);
		pwCheckInput.setBackground(Color.DARK_GRAY);
		pwCheckInput.setForeground(Color.YELLOW);
		pwCheckInput.setBounds(540, 270, 300, 40);
		add(pwCheckInput);
		nameInput = new JTextField(20);
		nameInput.setBackground(Color.DARK_GRAY);
		nameInput.setForeground(Color.YELLOW);
		nameInput.setBounds(540, 340, 300, 40);
		add(nameInput);
		birthdateInput = new JTextField(20);
		birthdateInput.setBackground(Color.DARK_GRAY);
		birthdateInput.setForeground(Color.YELLOW);
		birthdateInput.setBounds(540, 410, 300, 40);
		add(birthdateInput);
		nickNameInput = new JTextField(20);
		nickNameInput.setBackground(Color.DARK_GRAY);
		nickNameInput.setForeground(Color.YELLOW);
		nickNameInput.setBounds(540, 480, 300, 40);
		add(nickNameInput);
		backGround = new ImageIcon("src/images/loginScreen.png").getImage();
		idLb.setForeground(Color.YELLOW);
		idLb.setFont(new Font("���� ���", Font.BOLD, 40)); // ��ġ+ũ�⼳��
		idLb.setBounds(450, 400, 100, 100);
		add(idLb);
		pwLb.setForeground(Color.YELLOW);
		pwLb.setFont(new Font("���� ���", Font.BOLD, 40)); // ��Ʈ�� �۾� ũ�� ����
		pwLb.setBounds(430, 500, 200, 100); // ��ġ�� �۾��� ǥ�õ� ���� ����
		add(pwLb);
		pwCheckInput.setVisible(false);
		nameInput.setVisible(false);
		birthdateInput.setVisible(false);
		nickNameInput.setVisible(false);
		quitButton.setVisible(false);
		startButton.setVisible(false);

		createButton.setVisible(false);
		createUserButton.setVisible(true);
		loginbackButton.setVisible(false);
		pwCheckLb.setVisible(false);
		nameLb.setVisible(false);
		birthdateLb.setVisible(false);
		nickNameLb.setVisible(false);
	} // �α��� ȭ�� ����


	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		selectedImageButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backGround = new ImageIcon("src/images/" + trackList.get(nowSelected).getGameImage()).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty,
				trackList.get(nowSelected).getGameMusic());
		game.start(); // Game Ŭ������ run() �޼ҵ� ����
	}

	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		selectedImageButton.setVisible(true);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		backButton.setVisible(false);
		backGround = new ImageIcon("src/images/mainBackground.jpg").getImage();
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close(); // ���� ���� ����+ ���� ������ ����.
	}

	public void enterMain() {
		backGround = new ImageIcon("src/images/mainBackground.jpg").getImage(); // �� ����ȭ�� ��� ����.
		startButton.setVisible(false); // start ��ư ����
		quitButton.setVisible(false); // quite ��ư ����
		leftButton.setVisible(true); // �� ���� ���� ��ư ����
		rightButton.setVisible(true); // '' ������ ��ư ����
		selectedImageButton.setVisible(true); // �ٹ�ǥ�� ��ư ����
		isMainScreen = true; // �� ����ȭ�� ����.
		introMusic.close(); // ��Ʈ�� ���� ����.
		selectTrack(0); // �� Ʈ�� 0������ ����.
	}

	public void gameOver() {
		backGround = new ImageIcon("src/images/gameOver.jpg").getImage();
		backButton.setVisible(true);
	}

}
