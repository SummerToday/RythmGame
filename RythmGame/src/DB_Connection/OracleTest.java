package DB_Connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
 
public class OracleTest 
{
    public static void main(String args[])
    {
        Connection conn = null; // DB연결된 상태(세션)을 담은 객체
        PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
        ResultSet rs = null;  // 쿼리문을 날린것에 대한 반환값을 담을 객체
        
        try {
            // SQL 문장을 만들고 만약 문장이 질의어(SELECT문)라면
            // 그 결과를 담을 ResulSet 객체를 준비한 후 실행시킨다.
            String quary = "SELECT *FROM clown_info"; //쿼리문 저장.
            
            conn = DBConnection.getConnection();
            //DB에 연결된 상태를 con 에 담는다.
            pstm = conn.prepareStatement(quary);
            //연결된상태(con)를 가지고 prepareStatement(query)메서드를 통해 DB에 쿼리문을 보낸다.
            rs = pstm.executeQuery();
			/*
			 * PreparedStatement 클래스에 있는 executeQuery( ) 메서드는 쿼리문을 실행하는 것을 의미한다. 즉 우리가 cmd
			 * 창에 쿼리문을 적어놓고 엔터를 치면 결과가 나오듯 executeQuery( )는 쿼리문이 입력된 상태에서 엔터키를 누르는 역할을 한다.
			 */
			/*
			 * 쿼리문을 입력하고 엔터를 누르면 그에 해댕하는 결과가 나올 것이다. 그 결과를 rs(ResultSet)에 담는다. executeQuery(
			 * ) 메서드의 반환값은 ResultSet 이다.
			 */
            
            /*  clown_info 테이블의 데이터 정보
             * 
                 CREATE TABLE `clown_info` (
 `name` varchar(50) default NULL,
 `last_seen` varchar(50) default NULL,
 `appearance` varchar(50) default NULL,
 `activities` varchar(50) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO clown_info VALUES ('Elsie', 'Cherry Hill Senior Center', 'F, red hair, green dress, huge feet', 'balloons, little car');
INSERT INTO clown_info VALUES ('Pickles', 'Jack Green\'s party', 'M, orange hair, blue suit, huge feet','mime');
INSERT INTO clown_info VALUES ('Snuggles' ,'Ball?Mart' ,'F, yellow shirt, baggy red pants', 'horn, umbrella');
INSERT INTO clown_info VALUES ('Mr. Hobo' ,'Party for Eric Gray' ,'M, cigar, black hair, tiny hat violin', '');
INSERT INTO clown_info VALUES ('Clarabelle' ,'Belmont Senior Center' ,'F, pink hair, huge flower, blue dress', 'yelling, dancing');
INSERT INTO clown_info VALUES ('Scooter', 'Oakland Hospital' ,'M, blue hair, red suit, huge nose', 'balloons'); 
INSERT INTO clown_info VALUES ('Zippo' ,'Millstone Mall', 'F, orange suit, baggy pants', 'dancing'); 
INSERT INTO clown_info VALUES ('Babe', 'Earl’s Autos' ,'F, all pink and sparkly', 'balancing, little car');
INSERT INTO clown_info VALUES ('Bonzo' ,'','M, in drag, polka dotted dress', 'singing, dancing');
INSERT INTO clown_info VALUES ('Sniffles' ,'Tracy\'s', 'M, green and purple suit, pointy nose',''); 
            */
            
            System.out.println("\t clown_info TABLE \t");
            System.out.println("============================================");
            
            while(rs.next()){ // 저장된 데이터의 끝까지
                String name= rs.getString("name");
                String last_seen= rs.getString("last_seen");
                String appearance= rs.getString("appearance");
                String activities= rs.getString("activities");
                String result = name+"\t"+last_seen+"\t"+appearance+"\t"+activities;
                System.out.println(result);
            }
            
        } catch (SQLException sqle) {
            System.out.println("SELECT문에서 예외 발생");
            sqle.printStackTrace();
            
        }finally{
            // DB 연결을 종료한다.
            try{
                if ( rs != null ){rs.close();}   
                if ( pstm != null ){pstm.close();}   
                if ( conn != null ){conn.close(); }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
            
        }
    }
}