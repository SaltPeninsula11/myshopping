<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="user" class="jp.ac.o_hara.site.user.UserBean" scope="session" />

<h1>${ user.getRealName() }</h1>
<table>
	<tr>
		<th>
			<h4>残高</h4>
		</th>
		<td>
			<h4>0円</h4>
		</td>
	</tr>
	<tr>
		<th>
			<h4>ポイント</h4>
		</th>
		<td>
			<h4>0pts</h4>
		</td>
	</tr>
</table>
