<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />
<%
int allProducts = 0;
String[] information = new String[8];
%>
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="glyphicon glyphicon-arrow-up"></span>&nbsp;PICK UPS</h3>
	</div>
	
	<div class="panel-body">
		<% allProducts = product.getCount(); %>
		<div id="product_list">
		<% for (int i = 0; i < 25; i++){ %>
		<% information = product.getInformation(i); %>
		<% if (information[0] == null){
			break;
		} %>
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
		<% } %>
		</div>
	</div>
</div>