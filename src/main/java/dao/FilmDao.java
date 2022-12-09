package dao;

import java.util.*;
import vo.*;
import db.*;
import java.sql.*;

public class FilmDao {
	
	// release_year의 최소값
	public int selectMinReleaseYear() {
		int minYear = 0;
		DBUtil dbUtil = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			String sql = "SELECT MIN(release_year) y FROM film";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				minYear = rs.getInt("y");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
		return minYear;
	}
	
	 // rating : String[] 여러개의 등급
	public ArrayList<Film> selectFilmList2(int releaseYear, String searchTitle, String[] rating, int fromMinute, int toMinute) {
		ArrayList<Film> list = new ArrayList<Film>();
		DBUtil dbUtil = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {		
			dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			String sql = "";
			// 쿼리객체 생성, 쿼리문?값 지정
			if(releaseYear != 0) { // 영화 개봉일 o
				if(toMinute > fromMinute) { // 영화 상영시간o
					if(rating == null || rating.length == 5) { // 영화등급o between and 
						// 쿼리문 작성
						sql = "SELECT * FROM film WHERE title LIKE ? AND length BETWEEN ? AND ? AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setInt(2, fromMinute);
						stmt.setInt(3, toMinute);
						stmt.setInt(4, releaseYear);
					} else if(rating.length == 4) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?, ?, ?) AND length BETWEEN ? AND ? AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setString(4, rating[2]);
						stmt.setString(5, rating[3]);
						stmt.setInt(6, fromMinute);
						stmt.setInt(7, toMinute);
						stmt.setInt(8, releaseYear);
					} else if(rating.length == 3) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?, ?) AND length BETWEEN ? AND ? AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setString(4, rating[2]);
						stmt.setInt(5, fromMinute);
						stmt.setInt(6, toMinute);
						stmt.setInt(7, releaseYear);
					} else if(rating.length == 2) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?) AND length BETWEEN ? AND ? AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setInt(4, fromMinute);
						stmt.setInt(5, toMinute);
						stmt.setInt(6, releaseYear);
					} else if(rating.length == 1) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?) AND length BETWEEN ? AND ? AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setInt(3, fromMinute);
						stmt.setInt(4, toMinute);
						stmt.setInt(5, releaseYear);
					}
				} else { // 상영시간x 개봉일o 등급o releaseYear, rating
					if(rating == null || rating.length == 5) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setInt(2, releaseYear);
					} else if(rating.length == 4) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?, ?, ?) AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setString(4, rating[2]);
						stmt.setString(5, rating[3]);
						stmt.setInt(6, releaseYear);
					} else if(rating.length == 3) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?, ?) AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setString(4, rating[2]);
						stmt.setInt(5, releaseYear);
					} else if(rating.length == 2) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?) AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setInt(4, releaseYear);
					} else if(rating.length == 1) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?) AND release_year = ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setInt(3, releaseYear);
					}
				}
			} else { // 개봉일x
				if(toMinute > fromMinute) { // 상영시간 o 
					if(rating == null || rating.length == 5) { 
						sql = "SELECT * FROM film WHERE title LIKE ? AND length BETWEEN ? AND ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setInt(2, fromMinute);
						stmt.setInt(3, toMinute);
					} else if(rating.length == 4) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?, ?, ?) AND length BETWEEN ? AND ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setString(4, rating[2]);
						stmt.setString(5, rating[3]);
						stmt.setInt(6, fromMinute);
						stmt.setInt(7, toMinute);
					} else if(rating.length == 3) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?, ?) AND length BETWEEN ? AND ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setString(4, rating[2]);
						stmt.setInt(5, fromMinute);
						stmt.setInt(6, toMinute);
					} else if(rating.length == 2) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?, ?) AND length BETWEEN ? AND ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setString(3, rating[1]);
						stmt.setInt(4, fromMinute);
						stmt.setInt(5, toMinute);
					} else if(rating.length == 1) {
						sql = "SELECT * FROM film WHERE title LIKE ? AND rating IN (?) AND length BETWEEN ? AND ?";
						stmt = conn.prepareStatement(sql);
						stmt.setString(1, "%"+searchTitle+"%");
						stmt.setString(2, rating[0]);
						stmt.setInt(3, fromMinute);
						stmt.setInt(4, toMinute);
					}
				}
			}
			// 쿼리 실행
			rs = stmt.executeQuery();
			while(rs.next()) {
				Film f = new Film();
				f.setFilmId(rs.getInt("film_id"));
				f.setTitle(rs.getString("title"));
				f.setRating(rs.getString("rating"));
				f.setLength(rs.getInt("length"));
				f.setReleaseYear(rs.getInt("release_year"));
				list.add(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	/*
	// SELECT * FROM film WHERE rating IN('G', 'PG') AND LENGTH between 50 AND 150;
	// rating : String[] 여러개의 등급
	public ArrayList<Film> selectFilmList2(String[] rating, int fromMinute, int toMinute) {
		ArrayList<Film> list = new ArrayList<Film>();		
		
		DBUtil dbUtil = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			String sql = "";
			
			 if(toMinute > fromMinute) {
				 if(rating == null || rating.length == 5) {
					sql = "SELECT * FROM film WHERE length BETWEEN ? AND ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, fromMinute);
					stmt.setInt(2, toMinute);
				} else if(rating.length == 4) {
					sql = "SELECT * FROM film WHERE rating in(?, ?, ?, ?) AND LENGTH between ? AND ?";
					stmt = conn.prepareStatement(sql);
					for(int i=1; i<=rating.length; i=i+1) {
						stmt.setString(i, rating[i-1]);					
					}
					stmt.setInt(5, fromMinute);
					stmt.setInt(6, toMinute);
				} else if(rating.length == 3) {
					sql = "SELECT * FROM film WHERE rating in(?, ?, ?) AND LENGTH between ? AND ?";
					stmt = conn.prepareStatement(sql);
					for(int i=1; i<=rating.length; i=i+1) {
						stmt.setString(i, rating[i-1]);					
					}
					stmt.setInt(4, fromMinute);
					stmt.setInt(5, toMinute);
				} else if(rating.length == 2) {
					sql = "SELECT * FROM film WHERE rating in(?, ?) AND LENGTH between ? AND ?";
					stmt = conn.prepareStatement(sql);
					for(int i=1; i<=rating.length; i=i+1) {
						stmt.setString(i, rating[i-1]);					
					}
					stmt.setInt(3, fromMinute);
					stmt.setInt(4, toMinute);
				} else if(rating.length == 1) {
					sql = "SELECT * FROM film WHERE rating in(?) AND LENGTH between ? AND ?";
					stmt = conn.prepareStatement(sql);
					for(int i=1; i<=rating.length; i=i+1) {
						stmt.setString(i, rating[i-1]);					
					}
					stmt.setInt(2, fromMinute);
					stmt.setInt(3, toMinute);
				}
			 } else {
				 if(rating == null || rating.length == 5) {
	               sql = "SELECT * FROM film";
	               stmt = conn.prepareStatement(sql);
	            } else if(rating.length == 4) {
	               sql = "SELECT * FROM film WHERE rating IN (?, ?, ?, ?)";
	               stmt = conn.prepareStatement(sql);
	               stmt.setString(1, rating[0]);
	               stmt.setString(2, rating[1]);
	               stmt.setString(3, rating[2]);
	               stmt.setString(4, rating[3]);
	            } else if(rating.length == 3) {
	               sql = "SELECT * FROM film WHERE rating IN (?, ?, ?)";
	               stmt = conn.prepareStatement(sql);
	               stmt.setString(1, rating[0]);
	               stmt.setString(2, rating[1]);
	               stmt.setString(3, rating[2]);
	            } else if(rating.length == 2) {
	               sql = "SELECT * FROM film WHERE rating IN (?, ?)";
	               stmt = conn.prepareStatement(sql);
	               stmt.setString(1, rating[0]);
	               stmt.setString(2, rating[1]);
	            } else if(rating.length == 1) {
	               sql = "SELECT * FROM film WHERE rating IN (?)";
	               stmt = conn.prepareStatement(sql);
	               stmt.setString(1, rating[0]);
	            } 
			 }
				
				rs = stmt.executeQuery();
				while(rs.next()) {
					Film f = new Film();
					f.setFilmId(rs.getInt("film_id"));
					f.setTitle(rs.getString("title"));
					f.setRating(rs.getString("rating"));
					f.setLength(rs.getInt("length"));
					list.add(f);
				}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return list;
	}
	*/
	
	// col : 컬럼명
	// sort : asc / desc
	public ArrayList<Film> selectFilmListBySearch(String col, String sort, String searchCol, String searchWord) {
		ArrayList<Film> list = new ArrayList<Film>();
		// 초기화
		DBUtil dbUtil = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// 드라이버 연결
			dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			// 쿼리문 작성
			String sql = "";
			// 검색할 컬럼이나 검색어가 null이면 film 테이블 모두 출력
			if(searchCol == null || searchWord == null) {
				System.out.println("검색어입력x");
				sql = "SELECT * FROM film";
				stmt = conn.prepareStatement(sql);
			} else if(searchCol.equals("titleAndDescription")) {
				sql = "SELECT * FROM film WHERE title LIKE ? AND description LIKE ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchWord+"%");
				stmt.setString(2, "%"+searchWord+"%");
			} else {
				String whereCol = searchCol;
				sql = "SELECT * FROM film WHERE "+whereCol+" LIKE ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+searchWord+"%");
			}
			
			
			// 쿼리 실행
			rs = stmt.executeQuery();
			while(rs.next()) {
				Film f = new Film();
				f.setFilmId(rs.getInt("film_id"));
				f.setTitle(rs.getString("title"));
				f.setDescription(rs.getString("description"));
				f.setReleaseYear(rs.getInt("release_year"));
				f.setLanguageId(rs.getInt("language_id"));
				f.setOriginalLanguageId(rs.getInt("original_language_id"));
				f.setRentalDuration(rs.getInt("rental_duration"));
				f.setRentalRate(rs.getDouble("rental_rate"));
				f.setLength(rs.getInt("length"));
				f.setReplacementCost(rs.getDouble("replacement_cost"));
				f.setRating(rs.getString("rating"));
				f.setSpecialFeatures(rs.getString("special_features"));
				f.setLastUpdate(rs.getString("last_update"));
				list.add(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.close(rs, stmt, conn);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return list;
	}
}
