<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />

<%
String[] information = new String[8];
Boolean searchSwitch = (request.getAttribute("search") == null);
int count = product.getCount();
int pageCount = Integer.parseInt(request.getAttribute("page").toString());
%>
<!-- 「(キーワード)」の検索結果 -->
<% if (searchSwitch){ %>
	<!-- タグで絞り込み -->
	<!-- 
	SELECT * 
	FROM 商品テーブル 
	WHERE タグA = タグ 
	OR タグB = タグ 
	OR タグC = タグ 
	OR タグD = タグ 
	OR タグE = タグ 
	-->
	
	タグ「${ tag }」で検索　0件
<% } else { %>
	<!-- 通常検索 -->
	<!-- 
	SELECT * 
	FROM 商品テーブル 
	WHERE 商品名 LIKE "%キーワード%" 
	-->
	
	「${ search }」の検索結果 0件
<% } %>

<div class="panel-body">
	<div id="product_list">
	<% for (int i = (30 * (pageCount - 1)); i < (30 * pageCount); i++){ %>
		<%
		if (searchSwitch){
			information = product.searchTag(i, request.getAttribute("tag").toString());
		} else {
			information = product.search(i, request.getAttribute("search").toString());
		}
		%>
		<% if (information[0] != null){ %>
			<div class="product_item">
				<a href="product?productId=<% out.println(i); %>">
					<table border="1">
						<tr>
							<td rowspan="3">
								<img src="${pageContext.request.contextPath}/images/<%= information[0] %>.png" 
								width="70" height="92" alt="<%= information[1] %>">
							</td>
							<td class="p-item">
								タグ名
							</td>
						</tr>
						<tr>
							<td class="p-item"><%= information[1] %></td>
						</tr>
						<tr>
							<td class="p-item"><%= information[2] %>円</td>
						</tr>
					</table>
				</a>
			</div>
		<% } %>
	<% } %>
	</div>
</div>

<div class="page-divide">
	<form action="search">
		<% if (searchSwitch){ %>
		<input type="hidden" name="tag" value="<%= request.getAttribute("tag") %>">
		<%= request.getAttribute("tag") %>
		<% } else { %>
		<input type="hidden" name="search" value="<%= request.getAttribute("search") %>">
		<%= request.getAttribute("search") %>
		<% } %>
		<input type="button" name="page" value="2">
	</form>
</div>