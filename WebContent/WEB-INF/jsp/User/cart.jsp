<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />

<%
String[] information = new String[8];
int count = Integer.parseInt(request.getParameter("count"));
int productId = Integer.parseInt(request.getParameter("cart"));
information = product.getInformation(productId);
%>

<form action="./user">
	<table>
		<tr>
			<td>
				<h3>カートに入れました。</h3>
				<input type="hidden" name="userId" value="hoge">
			</td>
		</tr>
		<tr>
			<td>
				<%= information[1] %> × <%= count %>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="ユーザー画面へ移動">
			</td>
		</tr>
		<tr>
			<td>
				<a href="./">
				<input type="button" value="買い物を続ける">
				</a>
			</td>
		</tr>
	</table>
</form>