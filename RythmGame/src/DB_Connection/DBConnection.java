package DB_Connection;

//오라클 연동 코드

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection 
{
        public static Connection getConnection()
        {
            Connection conn = null;
            try {
                String user = "kik"; // 접속할 계정명 
                String pw = "kom654";
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                /*오라클이 자신의 pc에 설치되어 있으면 localhost를 입력하면 되고,
                 * 만약 오라클이 다른 pc에 설치되어 있다면 설치된 pc의 ip주소를 적으면 돤다.*/
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                /*JDBC 드라이버(ojdbc6.jar)를 로딩하는 부분이다. 로딩 실패시 ClassNotFoundExceptiom이 발생*/
                conn = DriverManager.getConnection(url, user, pw);
				/*
				 * 입력된 오라클의 계정, 비밀번호, 주소를 바탕으로 오라클에 접속을 한다. 접속 실패시 SQLEXception이 발생한다.
				 * getConnection( ) 메소드는 Connection을 반환한다.
				 */
                System.out.println("Database에 연결되었습니다.\n");
                
            } catch (ClassNotFoundException cnfe) {
                System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
            } catch (SQLException sqle) {
                System.out.println("DB 접속실패 : "+sqle.toString());
            } catch (Exception e) {
                System.out.println("Unkonwn error");
                e.printStackTrace();
            }
            return conn;     
        }
}