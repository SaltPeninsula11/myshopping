<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />
<jsp:useBean id="user" class="jp.ac.o_hara.site.user.UserBean" scope="session" />

<%
int allProducts = 0;
String[] information = new String[8];

String userId = request.getParameter("userId");
%>

<h1>${ user.getRealNameDAO(userId) }</h1>
<table>
	<tr>
		<th>
			<h4>残高</h4>
		</th>
		<td class="r-align">
			<h4>${ user.getMoneyDAO(userId) } 円</h4>
		</td>
	</tr>
	<tr>
		<th>
			<h4>ポイント</h4>
		</th>
		<td class="r-align">
			<h4>${ user.getPointDAO(userId) } pts</h4>
		</td>
	</tr>
</table>

<!-- カートの在庫がない場合、非表示する。 -->
<% if (user.getTotalCartPlusCheck(userId) > 0) { %>
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">
		<span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;MY CART
		<input type="button" value="購入する">
		</h3>
	</div>
	<div class="panel-body">
		<% allProducts = product.getCount(); %>
		<div id="product_list">
		<% for (int i = 0; i < allProducts; i++){ %>
		<% information = product.getInformation(i); %>
		<% if (information[0] == null){
			break;
		} %>
			<% if (user.getCartCount(i) > 0){ %>
			<div class="product_item">
				<a href="product?productId=<% out.println(i); %>">
					<table border="1">
						<tr>
							<td rowspan="3">
								<img src="${pageContext.request.contextPath}/images/<%= information[0] %>.png" 
								width="70" height="92" alt="<%= information[1] %>">
							</td>
							<td class="p-item">
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
				</a>
			</div>
			<% } %>
		<% } %>
		</div>
	</div>
</div>
<% } %>

<!-- <div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="glyphicon glyphicon-th-large"></span>&nbsp;MY ITEMS</h3>
	</div>
	<div class="panel-body">
		<table border="1">
			<tr>
				<td rowspan="3">
					アイコン
				</td>
				<td>
					×0
				</td>
			</tr>
			<tr>
				<td>商品名</td>
			</tr>
			<tr>
				<td>価格</td>
			</tr>
		</table>
	</div>
</div> -->
