<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />

<%
String[] information = new String[8];
Boolean searchSwitch = (request.getAttribute("search") == null);
int count = product.getCount();
int searchCount = 0;
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
	
	タグ「${ tag }」で絞り込み
<% } else { %>
	<!-- 通常検索 -->
	<!-- 
	SELECT * 
	FROM 商品テーブル 
	WHERE 商品名 LIKE "%キーワード%" 
	-->
	
	「${ search }」の検索結果
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
							<td rowspan="2">
								<img src="${pageContext.request.contextPath}/images/<%= information[0] %>.png" 
								width="70" height="92" alt="<%= information[1] %>">
							</td>
							<td class="p-item">
								<%= information[1] %>
							</td>
						</tr>
						<tr>
							<td class="p-item"><%= information[2] %>円</td>
						</tr>
					</table>
				</a>
			</div>
			<% searchCount += 1; %>
		<% } %>
	<% } %>
	</div>
</div>

<!--
if (ページ数 >= (n - 5) && 総ページ数 > 10){
	[1]・・・[n-9][n-8][n-7][n-6][n-5][n-4][n-3][n-2][n-1][n]の形式にする
} else if (ページ数 >= 6 && 総ページ数 > 10){
	[1]・・・[2][3][4][5][6][7][8][9][10][11]の形式にする
} else {
	[1][2][3][4][5][6][7][8][9][10]の形式にする
}
 -->
<%
int lastPage = (int)Math.ceil(searchCount / 30);
%>
 
<div class="page-divide">
	<table>
		<tr>
		<% for (int i = 1; i <= lastPage; i++) { %>
			<td>
				<form action="search">
					<% if (searchSwitch){ %>
					<input type="hidden" name="tag" value="<%= request.getAttribute("tag") %>">
					<% } else { %>
					<input type="hidden" name="search" value="<%= request.getAttribute("search") %>">
					<% } %>
					<input type="submit" name="page" value="<%= i %>">
				</form>
			</td>
		<% } %>
		</tr>
	</table>
</div>