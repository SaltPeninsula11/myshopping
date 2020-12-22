<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="user" class="jp.ac.o_hara.site.user.UserBean" scope="session" />

<h1>${ user.getRealName() }</h1>
<table>
	<tr>
		<th>
			<h4>残高</h4>
		</th>
		<td>
			<h4>${ user.getMoneyWithComma() } 円</h4>
		</td>
	</tr>
	<tr>
		<th>
			<h4>ポイント</h4>
		</th>
		<td>
			<h4>${ user.getPointWithComma() } pts</h4>
		</td>
	</tr>
</table>

<!-- カートの在庫がない場合、非表示する。 -->
<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;MY CART</h3>
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

<div class="panel panel-info">
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
