<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 「(キーワード)」の検索結果 -->
<% if (request.getAttribute("search") == null){ %>
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
