<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />
<%
int allProducts = 0;
String[] information = new String[8];
%>
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="glyphicon glyphicon-certificate"></span>&nbsp;NEW ITEMS</h3>
	</div>
	
	<div class="panel-body">
		<% allProducts = product.getCount(); %>
		<table border="2">
		<% for (int i = 0; i < 54; i++){ %>
		<% information = product.getInformation(i); %>
		<tr>
			<td>
				<a href="product?productId=<% out.println(i); %>">
					<table border="1">
						<tr>
							<td rowspan="3">
								アイコン
							</td>
							<td>
								タグ名
							</td>
						</tr>
						<tr>
							<td><%= information[1] %></td>
						</tr>
						<tr>
							<td><%= information[2] %>円</td>
						</tr>
					</table>
				</a>
			</td>
		<tr>
		<% } %>
		</table>
	</div>
	
</div>
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="glyphicon glyphicon-arrow-up"></span>&nbsp;PICK UPS</h3>
	</div>
	<div class="panel-body">
		<table border="1">
			<tr>
				<td rowspan="3">
					アイコン
				</td>
				<td>
					タグ名
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
</div>