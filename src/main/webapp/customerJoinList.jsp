<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "java.util.*" %>
<%
	// Controller
	// 현재 페이지
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	// 페이지당 표시할 목록의 수
	int rowPerPage = 10;
	// 리스트 시작할 행의 숫자
	int beginRow = (currentPage - 1) * rowPerPage;
	
	// 메서드 호출
	CustomerDao customerDao = new CustomerDao();
	ArrayList<CustomerAddressCityCountry> list = customerDao.selectCustomerJoinList(beginRow, rowPerPage);
	// 총 행의수
	int cnt = customerDao.countList();
	// System.out.println(cnt);
	//마지막 페이지
	int lastPage = cnt / rowPerPage;
	// System.out.println(lastPage);

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>customerJoinList</title>
	</head>
	<body>
		<!-- Domain 타입 사용 -->
		<h1>고객리스트</h1>
		<div>
			<table border="1">
				<tr>
					<th>customerId</th>
					<th>storeId</th>
					<th>firstName</th>
					<th>lastName</th>
					<th>email</th>
					<th>active</th>
					<th>address</th>
					<th>address2</th>
					<th>district</th>
					<th>phone</th>
					<th>city</th>
					<th>country</th>
				</tr>
				<%
					for(CustomerAddressCityCountry c : list) {
				%>
						<tr>
							<td><%=c.getCustomer().getCustomerId()%></td>
							<td><%=c.getCustomer().getStoreId()%></td>
							<td><%=c.getCustomer().getFisrtName()%></td>
							<td><%=c.getCustomer().getLastName()%></td>
							<td><%=c.getCustomer().getEmail()%></td>
							<td><%=c.getCustomer().getActive()%></td>
							<td><%=c.getAddress().getAddress()%></td>
							<td><%=c.getAddress().getAddress2()%></td>
							<td><%=c.getAddress().getDistrict()%></td>
							<td><%=c.getAddress().getPhone()%></td>
							<td><%=c.getCity().getCity()%></td>
							<td><%=c.getCountry().getCountry()%></td>
						</tr>
				<%
					}
				%>
				
			</table>
		</div>
		<div>
		<!-- 페이징 -->
			<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=1">처음</a>
			<%
				if(currentPage > 1) {
			%>
					<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=<%=currentPage-1%>">이전</a>
			<%=currentPage%>
			<%
				}
			
				if(currentPage < lastPage) {
			%>
					<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=<%=currentPage+1%>">다음</a>
			<%					
				}
			%>
			<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=<%=lastPage%>">마지막</a>
		</div>
	</body>
</html>