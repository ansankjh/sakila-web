package dao;

import vo.*;
import db.*;
import java.util.*;
import java.sql.*;

public class CustomerDao {
	// cnt
	public int countList() {
		int count = 0;
		// 드라이버 초기화
		DBUtil dbUtil = null;
		// 연결 초기화
		Connection conn = null;
		// 객체 초기화
		PreparedStatement stmt = null;
		// ResultSet 초기화
		ResultSet rs = null;
		// 쿼리문
		
		try {
			// 드라이버 저장
			dbUtil = new DBUtil();
			// 연결
			conn = dbUtil.getConnection();
			String sql = "SELECT COUNT(*) FROM customer";
			// 객체 생성
			stmt = conn.prepareStatement(sql);
			// 쿼리 실행
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
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
		return count;
	}
	
	// Map 사용
	public ArrayList<HashMap<String, Object>> selectCustomerMapList(int beginRow, int rowPerPage) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		// CustomerAddressCityCountry[] list = new CustomerAddressCityCountry[];
		DBUtil dbUtil = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			// db연결
			dbUtil = new DBUtil();
			conn =  dbUtil.getConnection();
			// 쿼리문
			/*
			 SELECT cu.*, ad.*, ci.*, co.*
				FROM customer cu INNER JOIN
				address ad ON cu.address_id = ad.address_id
				INNER JOIN city ci ON ad.city_id = ci.city_id
				INNER JOIN country co ON ci.country_id = co.country_id
				ORDER BY cu.customer_id ASC
				LIMIT 0, 10;
			 */
			String sql = "SELECT "
					+ " cu.first_name firstName, cu.last_name lastName"
					+ ", ad.address address, ad.district district"
					+ ", ci.city city, co.country country"
					+ " FROM customer cu INNER JOIN"
					+ " address ad ON cu.address_id = ad.address_id"
					+ " INNER JOIN city ci ON ad.city_id = ci.city_id"
					+ " INNER JOIN country co ON ci.country_id = co.country_id"
					+ " ORDER BY cu.customer_id ASC"
					+ " LIMIT ?, ?";
			// 쿼리객체 생성
			stmt = conn.prepareStatement(sql);
			// 쿼리문 ?값 지정
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			// 쿼리 실행
			rs = stmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("firstName", rs.getString("firstName"));
				map.put("lastName", rs.getString("lastName"));
				map.put("address", rs.getString("address"));
				map.put("district", rs.getString("district"));
				map.put("city", rs.getString("city"));
				map.put("country", rs.getString("country"));
				list.add(map);				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return list;
	}
	
	// FM방법
	// Customer x Address x City x Country 4개의 테이블을 INNER JOIN하는 Dao
	// 자료구조의 기본은 배열인데 배열 잘안씀 그걸 위해 자바에선 기본 자료구조API제공(Collections 프레임웍) ex) List / Set / Map
	// public CustomerAddressCityCountry[] selectCustomerJoinList() {
	public ArrayList<CustomerAddressCityCountry> selectCustomerJoinList(int beginRow, int rowPerPage) {
		// CustomerAddressCityCountry[] list = new CustomerAddressCityCountry[];
		ArrayList<CustomerAddressCityCountry> list = new ArrayList<CustomerAddressCityCountry>();
		DBUtil dbUtil = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			// db연결
			dbUtil = new DBUtil();
			conn = dbUtil.getConnection();
			// 쿼리문
			/*
			 SELECT cu.*, ad.*, ci.*, co.*
				FROM customer cu INNER JOIN
				address ad ON cu.address_id = ad.address_id
				INNER JOIN city ci ON ad.city_id = ci.city_id
				INNER JOIN country co ON ci.country_id = co.country_id
				ORDER BY cu.customer_id ASC
				LIMIT 0, 10;
			 */
			String sql = "SELECT cu.*, ad.*, ci.*, co.*"
					+ " FROM customer cu INNER JOIN"
					+ " address ad ON cu.address_id = ad.address_id"
					+ " INNER JOIN city ci ON ad.city_id = ci.city_id"
					+ " INNER JOIN country co ON ci.country_id = co.country_id"
					+ " ORDER BY cu.customer_id ASC"
					+ " LIMIT ?, ?";
			// 쿼리객체 생성
			stmt = conn.prepareStatement(sql);
			// 쿼리문 ?값 지정
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			// 쿼리 실행
			rs = stmt.executeQuery();
			while(rs.next()) {
				CustomerAddressCityCountry cacc = new CustomerAddressCityCountry();
				
				// Customer table 결과셋
				Customer cu = new Customer();
				cu.setCustomerId(rs.getInt("cu.customer_id"));
				cu.setStoreId(rs.getInt("cu.store_id"));
				cu.setFisrtName(rs.getString("cu.first_name"));
				cu.setLastName(rs.getString("cu.last_name"));
				cu.setEmail(rs.getString("cu.email"));
				cu.setActive(rs.getInt("cu.active"));
				cacc.setCustomer(cu);
				
				// Address table의 결과셋
				Address ad = new Address();
				ad.setAddress(rs.getString("ad.address"));
				ad.setAddress2(rs.getString("ad.address2"));
				ad.setDistrict(rs.getString("ad.district"));
				ad.setPhone(rs.getString("ad.phone"));
				cacc.setAddress(ad);
				
				// City table의 결과셋
				City ci = new City();				
				ci.setCity(rs.getString("ci.city"));				
				cacc.setCity(ci);

				// Country table의 결과셋
				Country co = new Country();
				co.setCountry(rs.getString("co.country"));
				cacc.setCountry(co);
				
				list.add(cacc);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return list;
	}




















}
