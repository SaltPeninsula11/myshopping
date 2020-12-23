<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="module" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="content" class="jp.ac.o_hara.site.ContentBean" scope="request" />
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>MyShop</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/jquery-ui.min.css">
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(function() {
		$("strong").css("color", "red");
	});
	</script>
</head>
<body>
<div class="container">
	<header class="well">
		<!-- メインロゴ（クリックしたときに、トップページへ飛ぶ設定） -->
		<h1>
			<a href="./"><i>MyShop</i></a>
		</h1>
		
		<!-- 検索欄 -->
		<form action="search">
			<!-- キーワードを送信 -->
			<input type="search" name="search" required />
			<!-- ページ数を送信（1に設定） -->
			<input type="hidden" name="page" value="1">
			<!-- 検索ボタン -->
			<input type="submit" value="検索" />
		</form>
	</header>
	<div class="row">
		<div class="col-sm-3">
			<!-- ユーザー欄 -->
			<module:User />
			
			<!-- カレンダー欄（いらないかも） -->
			<module:Calendar />
			
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"><span class="glyphicon glyphicon-tags"></span>&nbsp;タグで検索</h3>
				</div>
				<ul class="nav nav-pills nav-stacked">
				<!-- タグをループで表示 -->
				<%
				for (int i = 0; i < 5; i++){
				%>
					<li>
						<a href="${ pageContext.request.contextPath }/search?tag=<% out.println(i); %>&page=1">
							<% out.println("タグ" + i); %>
						</a>
					</li>
				<%
				}
				%>
				</ul>
			</div>
		</div>
		<div class="col-sm-9">
		
			<!-- 動的部分 -->
			<jsp:include page="${ content.getContent() }" />
			
		</div>
	</div>
	<footer class="well">
		Copyright &copy;塩﨑 航太
	</footer>
</div>

</body>
</html>