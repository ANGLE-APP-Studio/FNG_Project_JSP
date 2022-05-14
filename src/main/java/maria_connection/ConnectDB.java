package maria_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectDB {
	// �̱��� �������� ��� �ϱ��� �� �ڵ��
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {

	}
	
	String jdbcUrl = "jdbc:mariadb://222.109.188.220:3306/"; // MySQL ����
	String dbId = "Angle"; // MySQL ����
	String dbPw = "1234"; // ��й�ȣ
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	String returns = "a";

	// �����ͺ��̽��� ����ϱ� ���� �ڵ尡 ����ִ� �޼���
	public String connectionDB(String UID, String PSW,String EML,String PNB,
			String NCK,String BTD,String GND,String RNK) {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// ��� ����
			conn = DriverManager.getConnection("jdbc:mariadb://222.109.188.220:3306/example", "Angle", "1234");
			sql = "select UID from mmb where UID = ?";// ��ȸ
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, UID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// ������ �ִµ� �̹� ������ ������ ���(ȸ�������� �Ұ����� ���
				returns = "ȸ������ �Ұ�";

			} else {
				// �ְ��� �ϴ� ������ ���� ��� (ȸ�������� ������ ���)
				sql2 = "insert into mmb values(?,?,?,?,?,?,?,?)"; // ����
				pstmt2 = conn.prepareStatement(sql2);
				
				pstmt2.setString(1, UID);
				pstmt2.setString(2, PSW);
				pstmt2.setString(3, PNB);
				pstmt2.setString(4, EML);
				pstmt2.setString(5, NCK);
				pstmt2.setString(6, GND);
				pstmt2.setString(7, BTD);
				pstmt2.setString(8, RNK);
				
				pstmt2.executeUpdate();
				returns = "ȸ������ ����";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt2 != null)try {pstmt2.close();	} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
		}
		return returns;
	}

}