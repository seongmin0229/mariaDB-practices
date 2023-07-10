package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class BookDao {
	public boolean insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.152:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 준비
			String sql = "insert into book values(null, ?, 'n', ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. Binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getAuthorNo());
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//5. 결과 처리
//			while(rs.next()) {
//				Long no = rs.getLong(1);
//				String firstName = rs.getString(2);
//				String lastName = rs.getString(3);
//				String email = rs.getString(4);
//				
//				EmaillistVo vo = new EmaillistVo();
//				vo.setNo(no);
//				vo.setFirstName(firstName);
//				vo.setLastName(lastName);
//				vo.setEmail(email);
//				result.add(vo);
//			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			//6. 자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}

	public boolean rent(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.152:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 준비
			String sql = "update book "
					+ "set rent = 'y' "
					+ "where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. Binding
			pstmt.setLong(1, num);
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			//5. 결과 처리
//			while(rs.next()) {
//				Long no = rs.getLong(1);
//				String firstName = rs.getString(2);
//				String lastName = rs.getString(3);
//				String email = rs.getString(4);
//				
//				EmaillistVo vo = new EmaillistVo();
//				vo.setNo(no);
//				vo.setFirstName(firstName);
//				vo.setLastName(lastName);
//				vo.setEmail(email);
//				result.add(vo);
//			}
			result = count == 1;
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			//6. 자원 정리
			try {
//				if(rs != null) {
//					rs.close();
//				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.152:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 준비
			String sql = "select b.no, b.title, b.rent, a.name "
					+ "from book b, author a "
					+ "where b.author_no = a.no "
					+ "order by b.no";
			pstmt = conn.prepareStatement(sql);
			
			//4. Binding
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//5. 결과 처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String rent = rs.getString(3);
				String name = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setRent(rent);
				vo.setAuthorName(name);
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			//6. 자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public BookVo findByNum(int num) {
		BookVo result = new BookVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.0.152:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 준비
			String sql = "select b.no, b.title, b.rent, a.name "
					+ "from book b, author a "
					+ "where b.author_no = a.no and b.no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. Binding
			pstmt.setLong(1, num);
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			//5. 결과 처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String rent = rs.getString(3);
				String name = rs.getString(4);
				
				result.setNo(no);
				result.setTitle(title);
				result.setRent(rent);
				result.setAuthorName(name);
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			//6. 자원 정리
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
