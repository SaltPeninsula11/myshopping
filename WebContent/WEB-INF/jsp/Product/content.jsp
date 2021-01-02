<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="jp.ac.o_hara.site.user.UserBean" scope="session" />

<%
String[] information = new String[8];
information = product.getInformation(((Integer)(request.getAttribute("productId"))).intValue());
%>

<table border="1">
	<tr>
		<td rowspan="4">
			<!-- アイコン -->
			<img src="${pageContext.request.contextPath}/images/<%= information[0] %>.png" 
			width="125" height="165" alt="<%= information[1] %>">
		</td>
		<td>
			<table border="1">
			<tr>
			<!-- タグ -->
			<% for (int i = 3; i <= 7; i++){ %>
				<% if (information[i] != ""){ %>
				<td>
					<form action="search">
						<input type="submit" name="tag" value="<%= information[i] %>">
						<input type="hidden" name="page" value="1">
					</form>
				</td>
				<% } %>
			<% } %>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<!-- 商品名 -->
			<h2><%= information[1] %></h2>
		</td>
	</tr>
	<tr>
		<td>
			<!-- 価格 × 個数 -->
			<h4>
			<%= information[2] %>円
			 × 
			<select name="count">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			</select>個
			</h4>
		</td>
	</tr>
	<tr>
		<td>
			<input type="button" value="カートに入れる">
		</td>
	</tr>
</table>
