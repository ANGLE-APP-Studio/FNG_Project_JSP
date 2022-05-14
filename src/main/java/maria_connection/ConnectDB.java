package maria_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectDB {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static ConnectDB instance = new ConnectDB();

	public static ConnectDB getInstance() {
		return instance;
	}

	public ConnectDB() {

	}
	
	String jdbcUrl = "jdbc:mariadb://222.109.188.220:3306/"; // MySQL 계정
	String dbId = "Angle"; // MySQL 계정
	String dbPw = "1234"; // 비밀번호
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	String returns = "a";

	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	public String connectionDB(String UID, String PSW,String EML,String PNB,
			String NCK,String BTD,String GND,String RNK) {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// 디비 연결
			conn = DriverManager.getConnection("jdbc:mariadb://222.109.188.220:3306/example", "Angle", "1234");
			sql = "select UID from mmb where UID = ?";// 조회
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, UID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// 정보를 넣는데 이미 정보가 존재할 경우(회원가입이 불가능한 경우
				returns = "회원가입 불가";

			} else {
				// 넣고자 하는 정보가 없을 경우 (회원가입이 가능한 경우)
				sql2 = "insert into mmb values(?,?,?,?,?,?,?,?)"; // 삽입
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
				returns = "회원가입 가능";
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