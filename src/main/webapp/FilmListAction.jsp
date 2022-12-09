<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	// 1) Controller(요청처리 + 모델호출(dao))	
	String col = "film_id";
	String sort = "asc";	
	
	if(request.getParameter("col") != null) {
		col = request.getParameter("col");
	}
	if(request.getParameter("sort") != null) {
		sort = request.getParameter("sort");
	}
	
	String paramSort = "asc";
	if(sort.equals("asc")) { // 제목 클릭시 넘겨질 sort값
		paramSort = "desc";
	}
	
	String searchCol = request.getParameter("searchCol"); // title, description , title AND description
	String searchWord = request.getParameter("searchWord");
	
	FilmDao filmDao = new FilmDao();
	ArrayList<Film> list = filmDao.selectFilmListBySearch(col, sort, searchCol, searchWord);
	
	
	
	// 2) View
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>FilmListAction</title>
	</head>
	<body>
		<h1>필름 목록(검색 : 동적쿼리)</h1>
		<form action="<%=request.getContextPath()%>/FilmListAction.jsp" method="get">
			<select name="searchCol">
				<option value="title">title</option>
				<option value="description">description</option>
				<option value="titleAndDescription">title + description</option>
			</select>
			<input type="text" name="searchWord">
			<button type="submit">검색</button>
		</form>
		<table border="1">
			<tr>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=film_id&sort=<%=paramSort%>">
						filmId
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=title&sort=<%=paramSort%>">
						title
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=description&sort=<%=paramSort%>">
						description
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=release_year&sort=<%=paramSort%>">
						releaseYear
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=language_id&sort=<%=paramSort%>">
						languageId
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=original_language_id&sort=<%=paramSort%>">
						originalLanguageId
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=rental_duration&sort=<%=paramSort%>">
						rentalDuration
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=rental_rate&sort=<%=paramSort%>">
						rentalRate
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=length&sort=<%=paramSort%>">
						length
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=replacement_cost&sort=<%=paramSort%>">
						replacementCost
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=rating&sort=<%=paramSort%>">
						rating
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=special_features&sort=<%=paramSort%>">
						specialFeatures
					</a>
				</th>
				<th>
					<a href="<%=request.getContextPath()%>/FilmListAction.jsp?col=last_update&sort=<%=paramSort%>">
						lastUpdate
					</a>
				</th>
			
			</tr>
			<%
				for(Film f : list) {
			%>
					<tr>
						<td><%=f.getFilmId()%></td>
						<td><%=f.getTitle()%></td>
						<td><%=f.getDescription()%></td>
						<td><%=f.getReleaseYear()%></td>
						<td><%=f.getLanguageId()%></td>
						<td><%=f.getOriginalLanguageId()%></td>
						<td><%=f.getRentalDuration()%></td>
						<td><%=f.getRentalRate()%></td>
						<td><%=f.getLength()%></td>
						<td><%=f.getReplacementCost()%></td>
						<td><%=f.getRating()%></td>
						<td><%=f.getSpecialFeatures()%></td>
						<td><%=f.getLastUpdate()%></td>
					</tr>
			<%
				}
			%>
		</table>
	</body>
</html>