package DB_Connection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
 
public class OracleTest 
{
    public static void main(String args[])
    {
        Connection conn = null; // DB����� ����(����)�� ���� ��ü
        PreparedStatement pstm = null;  // SQL ���� ��Ÿ���� ��ü
        ResultSet rs = null;  // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü
        
        try {
            // SQL ������ ����� ���� ������ ���Ǿ�(SELECT��)���
            // �� ����� ���� ResulSet ��ü�� �غ��� �� �����Ų��.
            String quary = "SELECT *FROM clown_info"; //������ ����.
            
            conn = DBConnection.getConnection();
            //DB�� ����� ���¸� con �� ��´�.
            pstm = conn.prepareStatement(quary);
            //����Ȼ���(con)�� ������ prepareStatement(query)�޼��带 ���� DB�� �������� ������.
            rs = pstm.executeQuery();
			/*
			 * PreparedStatement Ŭ������ �ִ� executeQuery( ) �޼���� �������� �����ϴ� ���� �ǹ��Ѵ�. �� �츮�� cmd
			 * â�� �������� ������� ���͸� ġ�� ����� ������ executeQuery( )�� �������� �Էµ� ���¿��� ����Ű�� ������ ������ �Ѵ�.
			 */
			/*
			 * �������� �Է��ϰ� ���͸� ������ �׿� �ش��ϴ� ����� ���� ���̴�. �� ����� rs(ResultSet)�� ��´�. executeQuery(
			 * ) �޼����� ��ȯ���� ResultSet �̴�.
			 */
            
            /*  clown_info ���̺��� ������ ����
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
INSERT INTO clown_info VALUES ('Babe', 'Earl��s Autos' ,'F, all pink and sparkly', 'balancing, little car');
INSERT INTO clown_info VALUES ('Bonzo' ,'','M, in drag, polka dotted dress', 'singing, dancing');
INSERT INTO clown_info VALUES ('Sniffles' ,'Tracy\'s', 'M, green and purple suit, pointy nose',''); 
            */
            
            System.out.println("\t clown_info TABLE \t");
            System.out.println("============================================");
            
            while(rs.next()){ // ����� �������� ������
                String name= rs.getString("name");
                String last_seen= rs.getString("last_seen");
                String appearance= rs.getString("appearance");
                String activities= rs.getString("activities");
                String result = name+"\t"+last_seen+"\t"+appearance+"\t"+activities;
                System.out.println(result);
            }
            
        } catch (SQLException sqle) {
            System.out.println("SELECT������ ���� �߻�");
            sqle.printStackTrace();
            
        }finally{
            // DB ������ �����Ѵ�.
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