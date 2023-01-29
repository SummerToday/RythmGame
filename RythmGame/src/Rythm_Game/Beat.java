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
	private JButton backButton = new JButton(backButtonBasicImage); // 버튼들, 배경들 이미지 설정.
	private JButton loginbackButton = new JButton(backButtonBasicImage);
	private ImageIcon profile=new ImageIcon("src/images/profile.png");
	private JLabel profileLb=new JLabel(" ");
	private JLabel idLb = new JLabel("ID :");
	private JLabel pwLb = new JLabel("PW :");
	private JLabel nameLb = new JLabel("NAME :");
	private JLabel birthdateLb = new JLabel("BIRTHDATE :");
	private JLabel nickNameLb = new JLabel("NICKNAME :");
	private JLabel pwCheckLb = new JLabel("PW CHECK :");
	private JLabel showId;//닉네임으로 바꾸기.
	private JTextField idInput;
	private JTextField nameInput;
	private JTextField birthdateInput;
	private JTextField nickNameInput;
	private JPasswordField pwInput;
	private JPasswordField pwCheckInput;
	private boolean isMainScreen = false; // 메인 스크린(곡 선택 화면) 여부
	private boolean isGameScreen = false; // 게임 화면(플레이 화면) 여부
	private boolean isLoginScreen = true; // 로그인 화면으로 시작.
	private boolean isCreateUserScreen = false; // 회원가입 화면 전환 여부.
	/* 스크린 전역 변수 사용시 유의점
	 * 여러개의 스크린 변수가 true 값을 가지고 있을시 스크린이 넘어가지 않음.
	 * 논리는 맞지만 화면이 안넘어가는 경우는 대부분 스크린 변수가 중복으로 true로 설정이 되어있는 경우. 
	 */
	
	
	
	ArrayList<Track> trackList = new ArrayList<>(); // 음악이 들어가는 리스트.

	private Image titleImage;
	private Music selectedMusic;
	private Music introMusic = new Music(
			"Dillon Francis_Sulta.. - 02 - When We Were Young (Vicetone Remix) (Feat...-201411.mp3", true); // 인트로 음악 반복
																											// 설정.
	private int nowSelected = 0; // 현재 선택된 음악의 번호.
	public static Game game; // 게임 전역 변수 설정.
	
	//DB 연동 부분 
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
    ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체

	public Beat() {
		setUndecorated(true); // 타이틀 바를 없앰. for. 새로운 타이틀바를 탈아주기 위해
		setTitle("Rythm Game Beta");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGH);
		setResizable(false); // 프레임 사이즈를 사용자가 변경 못하도록 함.
		setLocationRelativeTo(null); // 창을 화면 가운데에 띄워줌.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0)); // 프레임 배경색 투명 설정. 4번째 인수가 투명도 설정값.
		setLayout(null); // 컴포넌트 배치는 사용자가 지정.
		setVisible(true);
		addKeyListener(new KeyListener()); // 키 이벤트 관련 메소드 사용을 위해 키리스너 객체를 넘겨줌.

		introMusic.start(); // 인트로 음악 시작.

		trackList.add(new Track("Fitz & the Tantrums - HandClap(title).png",
				new ImageIcon("src/images/18. Fitz & the Tantrums - HandClap.png"),
				new ImageIcon("src/images/18. Fitz & the Tantrums - HandClap Entered.png"),
				"18. Fitz & the Tantrums - HandClap(배경).png", "18. Fitz & the Tantrums - HandClap selected.mp3",
				"18. Fitz & the Tantrums - HandClap sample.mp3", "Fitz & the Tantrums - HandClap"));
		trackList.add(new Track("Anne-Marie - 2002(title).png",
				new ImageIcon("src/images/041 Anne-Marie - 2002.png"),
				new ImageIcon("src/images/041 Anne-Marie - 2002 Entered not yet.png"),
				"041 Anne-Marie - 2002(배경).png", "041 Anne-Marie - 2002 selected.mp3", "041 Anne-Marie - 2002.mp3",
				"Anne-Marie - 2002"));
		trackList.add(new Track("노을 - 늦은 밤 너의 집 앞 골목길에서(title).png",
				new ImageIcon("src/images/024 노을 - 늦은 밤 너의 집 앞 골목길에서.png"),
				new ImageIcon("src/images/024 노을 - 늦은 밤 너의 집 앞 골목길에서Entered not yet.png"),
				"024 노을 - 늦은 밤 너의 집 앞 골목길에서(배경).png", "024 노을 - 늦은 밤 너의 집 앞 골목길에서 selected.mp3",
				"024 노을 - 늦은 밤 너의 집 앞 골목길에서.mp3", "024 노을 - 늦은 밤 너의 집 앞 골목길에서"));
		//로그인 화면 구성 시작

		createUserButton.setBounds(625,600,100,25);
		createUserButton.setBorderPainted(false);
		createUserButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		createUserButton.setFocusPainted(false); // 포커스 표시 설정
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
					showId.setFont(new Font("맑은 고딕", Font.BOLD, 15)); // 위치+크기설정
					showId.setBounds(35, 0, 300, 40);
					add(showId);                  
					idInput.setVisible(false);   //ID 입력창 제거
					pwInput.setVisible(false);   //PW 입력창 제거
					idLb.setVisible(false);      //ID 라벨 제거
					pwLb.setVisible(false);      //PW 라벨 제거
					createUserButton.setVisible(false); //생성 버튼 제거
					isLoginScreen=false;          // 로그인 완료 후 로그인 화면 종료.
					quitButton.setVisible(true);
					startButton.setVisible(true);		
					backGround=new ImageIcon(
							"src/images/tiesto_concert__musical_freedom-wallpaper-1280x800.jpg").getImage(); 
							//원래 초기 배경으로 다시 설정.
				} else {
					JOptionPane.showMessageDialog(null, "Faild");
				}
			}
		});

		
		createUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createUserButton.setIcon(createUserButtonEnteredImage);
				createUserButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼에 들어왔을 때 마우스 커서 모양 설정
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createUserButton.setIcon(createUserButtonbBasicImage);
				createUserButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 버튼에서 나갔을 때 마우스 커서 모양 기본 설정.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // 마우스를 눌렀을 때 효과음 설정. 반복x
				buttonEnteredMusic.start(); // 효과음 시작.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // 이 블록이 없을시 소리가 나기 전에 윈도우 창이 닫혀서 소리가 안들림 그래서 1000밀리초=1초 동안 스레드를 멈춰줌.->소리가 나고
				isLoginScreen=false;    //true로 나둘 경우 화면 안넘어감.
				isCreateUserScreen = true;
				idInput.setText("");
				pwInput.setText("");
				idInput.requestFocus();
			} // 무명 클래스를 이용하여 주석 구현.

		});
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				createButton.setIcon(createButtonEnteredImage);
				createButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼에 들어왔을 때 마우스 커서 모양 설정
			}

			@Override
			public void mouseExited(MouseEvent e) {
				createButton.setIcon(createButtonBasicImage);
				createButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 버튼에서 나갔을 때 마우스 커서 모양 기본 설정.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // 마우스를 눌렀을 때 효과음 설정. 반복x
				buttonEnteredMusic.start(); // 효과음 시작.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // 이 블록이 없을시 소리가 나기 전에 윈도우 창이 닫혀서 소리가 안들림 그래서 1000밀리초=1초 동안 스레드를 멈춰줌.->소리가 나고
			} // 무명 클래스를 이용하여 주석 구현.

		});
		loginbackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginbackButton.setIcon(backButtonEnteredImage);
				loginbackButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼에 들어왔을 때 마우스 커서 모양 설정
			}

			@Override
			public void mouseExited(MouseEvent e) {
				loginbackButton.setIcon(backButtonBasicImage);
				loginbackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 버튼에서 나갔을 때 마우스 커서 모양 기본 설정.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // 마우스를 눌렀을 때 효과음 설정. 반복x
				buttonEnteredMusic.start(); // 효과음 시작.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // 이 블록이 없을시 소리가 나기 전에 윈도우 창이 닫혀서 소리가 안들림 그래서 1000밀리초=1초 동안 스레드를 멈춰줌.->소리가 나고
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
			} // 무명 클래스를 이용하여 주석 구현.

		});
		//로그인 화면 구성.
		exitButton.setBounds(1250, 0, 30, 30); // setBounds(x,y,w,h) x,y:위치 좌표 w,h: 크기 설정
		exitButton.setBorderPainted(false); // 버튼 테두리 설정
		exitButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		exitButton.setFocusPainted(false); // 포커스 표시 설정
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 버튼에 들어왔을 때 마우스 커서 모양 설정
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 버튼에서 나갔을 때 마우스 커서 모양 기본 설정.
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false); // 마우스를 눌렀을 때 효과음 설정. 반복x
				buttonEnteredMusic.start(); // 효과음 시작.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} // 이 블록이 없을시 소리가 나기 전에 윈도우 창이 닫혀서 소리가 안들림 그래서 1000밀리초=1초 동안 스레드를 멈춰줌.->소리가 나고
					// exit 메소드
					// 실행 후 종료.
				System.exit(0);
			} // 무명 클래스를 이용하여 주석 구현.

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
				enterMain(); // 곡 선택 화면으로 전환.
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

		// 여기까지가 게임 초기화면 UI구성

		leftButton.setVisible(false); // 음악 선택 왼쪽 버튼 아직 안보임.->게임 화면으로 넘어가면 보이게.
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
				selectLeft(); // 왼쪽으로 버튼 클릭시 노래 넘어감.
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
		add(menuBar); // 하단에 노래 제목 출력해주는 라벨 넣어주기.

		selectedImageButton = new JButton(trackList.get(nowSelected).getStartImage()); // 해당 트랙의 앨범 아트 버튼
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
				requestFocus(); // 키 이벤트를 받을 수 있도록 강제로 포커스 지정.
				selectedMusic.close();
				Note.missNumber = 0; // 미스 횟수 0부터 시작
				Note.gameOver = false; // 게임오버 여부 x
			}
		});
		add(selectedImageButton); // 앨범 표지 버튼 설정-> 앨범 표지 버튼 누를시 배경화면 바뀌고 난이도 선택 버튼 제시.

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
		backButton.setBounds(15, 40, 50, 50); // 버튼 위치와 크기 설정.
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
		// 창을 그려주는 역할.
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
		} // 곡 선택 화면 하단에 곡 제목을 그려줌. bc. Image형이기 때문에 drawImage() 해줘야
		else if (isGameScreen) {
			game.screenDraw(g); //게임 플레이 내부 화면일시 game 클래스의 게임 내부 화면을 그려주는 메소드 호출. 
		}
		else if(isCreateUserScreen) {
			createUserScreen();
		}
		
		paintComponents(g); // 컴포넌트들을 그려줌.
		this.repaint();     //변경된 사항들을 정확히 다시 그려줌. 안그러면 그림 잘려서 나오고 이상해짐.
	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null) {
			selectedMusic.close();
		}
		this.nowSelected = nowSelected; // 현재의 트랙 넘버를 변경된 트랙의 넘버로 설정.
		titleImage = new ImageIcon("src/images/" + trackList.get(nowSelected).getTitleImage()).getImage(); // 표시되는 앨범아트의
																											// 이미지를 변경된
																											// 트랙의 앨범아트로
																											// 변경
		selectedImageButton.setIcon(trackList.get(nowSelected).getStartImage()); // 리스트의 해당요소의 메소드 출력
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start(); // 해당 트랙이 선택 되었을 때의 하이라이트 음악 재생.

	}

	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // 0번 트랙일시 다시 맨끝 트랙으로 넘어감.
		else
			nowSelected--; // 이전 트랙으로 이동
		selectTrack(nowSelected); // 해당 트랙의 정보로 선택화면 설정.
	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void createUserScreen() {   //회원가입 화면 구성
		quitButton.setVisible(false);
		startButton.setVisible(false);
		backGround = new ImageIcon("src/images/loginScreen.png").getImage();
		createUserButton.setVisible(false);
		pwCheckLb.setForeground(Color.YELLOW);
		pwCheckLb.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 위치+크기설정
		pwCheckLb.setBounds(330, 240, 400, 100);
		add(pwCheckLb);
		nameLb.setForeground(Color.YELLOW);
		nameLb.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 위치+크기설정
		nameLb.setBounds(395, 310, 400, 100);
		add(nameLb);
		birthdateLb.setForeground(Color.YELLOW);
		birthdateLb.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 위치+크기설정
		birthdateLb.setBounds(320, 380, 400, 100);
		add(birthdateLb);
		nickNameLb.setForeground(Color.YELLOW);
		nickNameLb.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 위치+크기설정
		nickNameLb.setBounds(325, 450, 400, 100);
		add(nickNameLb);
		idLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		idLb.setBounds(450, 100, 100, 100); //id레이블 위치 변경
		pwLb.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		pwLb.setBounds(435, 170, 200, 100); //pw레이블 위치 변경
		
		idInput.setBounds(540, 130, 300, 40);
		pwInput.setBounds(540, 200, 300, 40);
		createButton.setBounds(625,570,100,40);
		createButton.setBorderPainted(false);
		createButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		createButton.setFocusPainted(false); // 포커스 표시 설정
		add(createButton);
		loginbackButton.setBounds(15,40,50,50);
		loginbackButton.setBorderPainted(false);
		loginbackButton.setContentAreaFilled(false); // 버튼 영역 배경 설정
		loginbackButton.setFocusPainted(false); // 포커스 표시 설정
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
		idLb.setFont(new Font("맑은 고딕", Font.BOLD, 40)); // 위치+크기설정
		idLb.setBounds(450, 400, 100, 100);
		add(idLb);
		pwLb.setForeground(Color.YELLOW);
		pwLb.setFont(new Font("맑은 고딕", Font.BOLD, 40)); // 폰트와 글씨 크기 설정
		pwLb.setBounds(430, 500, 200, 100); // 위치와 글씨가 표시될 범위 설정
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
	} // 로그인 화면 구성


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
		game.start(); // Game 클래스의 run() 메소드 실행
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
		game.close(); // 게임 음악 종료+ 게임 스레드 종료.
	}

	public void enterMain() {
		backGround = new ImageIcon("src/images/mainBackground.jpg").getImage(); // 곡 선택화면 배경 설정.
		startButton.setVisible(false); // start 버튼 감춤
		quitButton.setVisible(false); // quite 버튼 감춤
		leftButton.setVisible(true); // 곡 선택 왼쪽 버튼 제시
		rightButton.setVisible(true); // '' 오른쪽 버튼 제시
		selectedImageButton.setVisible(true); // 앨범표지 버튼 제시
		isMainScreen = true; // 곡 선택화면 설정.
		introMusic.close(); // 인트로 음악 정지.
		selectTrack(0); // 곡 트랙 0번부터 시작.
	}

	public void gameOver() {
		backGround = new ImageIcon("src/images/gameOver.jpg").getImage();
		backButton.setVisible(true);
	}

}
