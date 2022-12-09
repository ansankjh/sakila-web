<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	// Controller
	String searchTitle = request.getParameter("searchTitle");	

	// 1) rating
	// 여러개의 값을 받을땐 Values
	String[] rating = request.getParameterValues("rating");	
	
	// System.out.println(rating);
	if(rating != null) {
	   System.out.println(rating.length + " <-- rating.length");
	}
	// 2) fromMinute & toMinute
	// fromMinute & toMinute 초기화
	int fromMinute = 0;
	int toMinute = 0;
	// 상영시간이 공백이 아니면 int타입으로 바꿈
	if(!request.getParameter("fromMinute").equals("") 
			&& !request.getParameter("toMinute").equals("")) {
		fromMinute = Integer.parseInt(request.getParameter("fromMinute"));
		toMinute = Integer.parseInt(request.getParameter("toMinute"));
	}
	// System.out.println(fromMinute);
	// System.out.println(toMinute);
	
	// releaseYear 초기화
	int releaseYear = 0;
	
	if(!request.getParameter("releaseYear").equals("")) {
		releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
	}

	// 메서드 호출
	FilmDao filmDao = new FilmDao();
	ArrayList<Film> list = filmDao.selectFilmList2(releaseYear, searchTitle, rating, fromMinute, toMinute);
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>검색결과</h1>
		<table border="1">
			<tr>
				<th>필름 번호</th>
				<th>필름 제목</th>
				<th>필름 등급</th>
				<th>필름 상영시간</th>
				<th>필름 개봉일</th>
			</tr>
			<%
				for(Film f : list) {
			%>
					<tr>
						<td><%=f.getFilmId()%></td>
						<td><%=f.getTitle()%></td>
						<td><%=f.getRating()%></td>
						<td><%=f.getLength()%></td>
						<td><%=f.getReleaseYear()%></td>
					</tr>
			<%      
				}
			%>
		</table>
	</body>
</html>