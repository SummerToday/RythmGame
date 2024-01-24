# RythmGame
Java와 OracleDB를 활용하여 개발한 리듬 게임 
<br></br>

## 💻 프로젝트 소개 
본 프로젝트는 2022년 1학기 자바프로그래밍 수업과 2022년 2학기 데이터베이스 수업의 내용을 심화 학습하기 위해 진행된 프로젝트입니다. 
해당 수업들의 자바와 데이터베이스 관련 내용들을 참고하였고, 동빈나의 ' 자바 리듬게임 만들기 강좌'를 활용하여 프로젝트를 진행하였습니다.
<br></br>

## 📌 Stacks

### Environment
<img src="https://img.shields.io/badge/eclipseide-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">

### Devlopment
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/sql-8669AE?style=for-the-badge&logo=sql&8669AE=white">
<br></br>

## 📢 고객 요구사항
안녕하세요. 저희는 이번에 리듬게임을 출시 하려고 합니다. 현재로서는 너무 많은 기능 보다는 핵심 기능들만 포함시켜 개발한 후 이용자들의 의견을 반영하여 기능들을 점점 추가해 나아갈 예정입니다! <br>

우선 기본적으로 회원 가입을 하고 아이디와 비밀번호를 이용하여 로그인 할 수 있는 기능이 필요합니다. 회원가입 정보에는 아이디, 비밀번호, 이용자 이름, 생년월일, 게임 내에서 사용될 닉네임 정도로 구성되었으면 좋겠습니다. 로그인 한 후에는 상단에 작게 현재 로그인 한 이용자의 닉네임이 표시 되었으면 좋겠습니다. <br>

여러 곡들을 선택하여 플레이 할 수 있었으면 좋겠고, 해당 곡들을 선택 시 이용자들이 해당 곡이 어떤 곡인지 해당 곡의 하이라이트 부분이 반복 재생 되었으면 좋겠습니다. 그리고 해당 곡의 앨범 아트가 표시가 되었으면 좋겠습니다. 또한 난이도는 쉬움, 어려움으로 선택할 수 있었으면 좋겠습니다. <br>

게임 플레이에는 s, d, f, spacebar, j, k, l 키가 사용되고 3번을 초과하여 박자를 놓쳐 키를 누르지 못한다면 게임 오버가 되는 기능이 있었으면 좋겠습니다. 또한 점수 계산 기능도 있어서 박자에 맞춰 키를 누를 시 점수가 증가하는 것이 표시 되었으면 좋겠습니다. 게임 플레이 배경화면은 해당 곡의 앨범 아트로 하면 될 것 같습니다. 그리고 게임 플레이 화면에서 곡 선택창으로 갈 수 있는 뒤로가기 버튼도 있으면 좋겠습니다.
 감사합니다! <br></br>


## 🔎 고객 요구사항 분석

### · Usecase

   - 회원 가입 및 로그인
     - 아이디, 비밀번호, 이용자 이름, 생년월일, 게임 내에서 사용될 닉네임 정보를 입력하여 회원 가입할 수 있다.
     - 가입한 이용자는 해당 정보 중 아이디와 비밀번호를 사용하여 로그인 할 수 있다.
     - 로그인 후에는 화면 상단에 현재 로그인 한 이용자의 닉네임이 표시된다. 
     <br></br>
   
   - 곡 선택 및 정보 표시
     - 이용자는 여러 곡 중에서 선택하여 플레이할 수 있다.
     - 곡을 선택하면 해당 곡의 하이라이트 부분이 반복 재생되고, 앨범 아트가 표시된다.
     - 이용자는 선택한 곡의 난이도를 쉬움 또는 어려움으로 선택할 수 있다.
     <br></br>

   - 게임 플레이
     - 게임 플레이에는 키보드의 s, d, f, spacebar, j, k, l 키가 사용된다.
     - 이용자가 3번을 초과하여 박자를 놓치면 게임 오버된다.
     - 박자에 맞춰 키를 누를 시에는 점수가 증가하고 게임 플레이 화면에 표시된다.
     - 게임 플레이 배경화면은 선택한 곡의 앨범 아트로 설정된다.
     - 게임 플레이 화면에서는 곡 선택 창으로 갈 수 있는 뒤로가기 버튼이 존재한다.
      <br></br>

### · 주요 데이터
    
   - 회원 가입 및 로그인 
     - 이용자 정보
       - 아이디(String)
       - 비밀번호(String)
       - 이용자 이름(String)
       - 생년월일(String)
       - 닉네임(String)

   - 곡 선택 및 정보 표시
     - 선택한 곡 정보
       - 곡 타이틀(String)
       - 앨범 아트(Image)
       - 난이도(String)
   
   - 게임 플레이
     - 게임 진행 상태
       - 생명(Int)
       - 점수(Int)
   <br></br>
   
### · 주요 ERD 도출
![KakaoTalk_20240124_211359620](https://github.com/SummerToday/RythmGame-Java_OracleDB/assets/88650436/bdec8306-5d20-4f77-bab3-f544f411e1af)
