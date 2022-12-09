<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	// Controller
	// 현재페이지
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	// 페이지당 표시할 목록의 수
	int rowPerPage = 10;
	// 리스트 시작행의 숫자
	int beginRow = (currentPage - 1) * rowPerPage;
	// 메서드 호출
	CustomerDao customerDao = new CustomerDao();
	ArrayList<HashMap<String, Object>> list = customerDao.selectCustomerMapList(beginRow, rowPerPage);
	int cnt = customerDao.countList();
	// System.out.println(cnt);
	// 마지막 페이지
	int lastPage = cnt / rowPerPage;
	
	// System.out.println(lastPage);
%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="UTF-8">
		<title>customerMapList</title>
	</head>
	<body>
		<!-- Map 타입 사용 -->
		<h1>고객리스트</h1>
		<div>
			<table border="1">
				<tr>
					<th>firstName</th>
					<th>lastName</th>
					<th>address</th>
					<th>district</th>
					<th>city</th>
					<th>country</th>
				</tr>
				<%
					for(HashMap<String, Object> m : list) {
				%>
						<tr>
							<td><%=m.get("firstName")%></td>
							<td><%=m.get("lastName")%></td>
							<td><%=m.get("address")%></td>
							<td><%=m.get("district")%></td>
							<td><%=m.get("city")%></td>
							<td><%=m.get("country")%></td>	
						</tr>
				<%
					}
				%>
			</table>
		</div>
		<div>
		<!-- 페이징 -->
			<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=1">처음</a>
			<%
				if(currentPage > 1) {
			%>
					<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=currentPage-1%>">이전</a>
			<%=currentPage%>
			<%
				}
			
				if(currentPage < lastPage) {
			%>
					<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=currentPage+1%>">다음</a>
			<%
				}
			%>
			<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=lastPage%>">마지막</a>
		</div>
	</body>
</html>