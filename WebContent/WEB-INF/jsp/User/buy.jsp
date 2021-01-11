<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />
<jsp:useBean id="user" class="jp.ac.o_hara.site.user.UserBean" scope="session" />

<%
int allProducts = 0;
String[] information = new String[8];

String userId = request.getParameter("userId");
%>

<div class="panel-body">
ここのページはまだ作り途中です！
	<% allProducts = product.getCount(); %>
	<div id="product_list">
	<% for (int i = 0; i < allProducts; i++){ %>
	<% information = product.getInformation(i); %>
	<% if (information[0] == null){
		break;
	} %>
		<% if (user.getCartCount(i) > 0){ %>
		<div class="product_item">
			<table border="1">
				<tr>
					<td rowspan="3">
						<img src="${pageContext.request.contextPath}/images/<%= information[0] %>.png" 
						width="70" height="92" alt="<%= information[1] %>">
					</td>
					<td class="p-item">
						<input type="hidden" name="<%= i %>" value="<%= user.getCartCount(i) %>">
						× <%= user.getCartCount(i) %>
					</td>
				</tr>
				<tr>
					<td class="p-item"><%= information[1] %></td>
				</tr>
				<tr>
					<td class="p-item"><%= information[2] %>円</td>
				</tr>
			</table>
		</div>
		<% } %>
	<% } %>
	</div>
</div>