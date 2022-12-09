<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	FilmDao filmDao = new FilmDao();
	int minYear = filmDao.selectMinReleaseYear(); // 2006
	Calendar today = Calendar.getInstance(); // new Calendar() --> Calendar는 추상클래스
	int todayYear = today.get(Calendar.YEAR); // 오늘 날짜의 년도 2022
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmListForm2</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/filmListAction2.jsp">
		<table border="1">			
			<tr>
				<td>출시년도</td>
				<td>
					<select name="releaseYear">
						<option value="">출시년도를 선택하세요</option>
						<%
							for(int i=minYear; i<=todayYear; i=i+1) { // i++, i+=1
						%>
								<option value="<%=i%>"><%=i%>년</option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>상영시간(분)</td>
				<td>
					<input type="number" name="fromMinute">
					~
					<input type="number" name="toMinute">
				</td>
			</tr>
			<tr>
				<td>등급</td>
				<td>
					<input type="checkbox" name="rating" value="G">G
					<input type="checkbox" name="rating" value="PG">PG
					<input type="checkbox" name="rating" value="PG-13">PG-13
					<input type="checkbox" name="rating" value="R">R
					<input type="checkbox" name="rating" value="NC-17">NC-17
				</td>
			</tr>
			<tr>
				<td>검색어(제목)</td>
				<td>
					<input type="text" name="searchTitle">
				</td>
			</tr>
		</table>
		<button type="submit">검색</button>
	 </form>	
</body>
</html>