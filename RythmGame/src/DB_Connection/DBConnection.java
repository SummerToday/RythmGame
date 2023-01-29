package DB_Connection;

//����Ŭ ���� �ڵ�

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBConnection 
{
        public static Connection getConnection()
        {
            Connection conn = null;
            try {
                String user = "kik"; // ������ ������ 
                String pw = "kom654";
                String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                /*����Ŭ�� �ڽ��� pc�� ��ġ�Ǿ� ������ localhost�� �Է��ϸ� �ǰ�,
                 * ���� ����Ŭ�� �ٸ� pc�� ��ġ�Ǿ� �ִٸ� ��ġ�� pc�� ip�ּҸ� ������ �ô�.*/
                
                Class.forName("oracle.jdbc.driver.OracleDriver");
                /*JDBC ����̹�(ojdbc6.jar)�� �ε��ϴ� �κ��̴�. �ε� ���н� ClassNotFoundExceptiom�� �߻�*/
                conn = DriverManager.getConnection(url, user, pw);
				/*
				 * �Էµ� ����Ŭ�� ����, ��й�ȣ, �ּҸ� �������� ����Ŭ�� ������ �Ѵ�. ���� ���н� SQLEXception�� �߻��Ѵ�.
				 * getConnection( ) �޼ҵ�� Connection�� ��ȯ�Ѵ�.
				 */
                System.out.println("Database�� ����Ǿ����ϴ�.\n");
                
            } catch (ClassNotFoundException cnfe) {
                System.out.println("DB ����̹� �ε� ���� :"+cnfe.toString());
            } catch (SQLException sqle) {
                System.out.println("DB ���ӽ��� : "+sqle.toString());
            } catch (Exception e) {
                System.out.println("Unkonwn error");
                e.printStackTrace();
            }
            return conn;     
        }
}