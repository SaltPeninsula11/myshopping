<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="product" class="jp.ac.o_hara.product.ProductBean" scope="request" />
<%String[] information = new String[8];%>
No. ${ productId }

<% information = product.getInformation(0); %>

<table border="1">
	<tr>
		<td rowspan="4">
			アイコン
		</td>
		<td>
			<h3>
				<table>
				<tr>
				<% for (int i = 3; i <= 7; i++){ %>
					<% if (information[i] != null){ %>
					<td>
						<%= information[i] %>
					</td>
					<% } %>
				<% } %>
				</tr>
				</table>
			</h3>
		</td>
	</tr>
	<tr>
		<td>
			<h3><%= information[1] %></h3>
		</td>
	</tr>
	<tr>
		<td>
			<h4>
			<%= information[2] %>円 × 
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
</table>
