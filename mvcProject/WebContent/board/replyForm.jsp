<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
<title>답변작성</title>

<!-- 모바일 웹 페이지 설정 -->
<link rel="shortcut icon" href="../image/icon.png" />
<link rel="apple-touch-icon" href="../image/icon.png" />
<!-- 모바일 웹 페이지 설정 끝 -->
	
<!--[if lt IE 9]>
<script src="../include/js/html5shiv.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="../include/css/board.css"/>

<script type="text/javascript" src="../include/js/jquery-1.11.3.min.js"></script>
<script>
	function formCheck(mail, item, msg) {
		if(mail.val().replace(/\s/g, "")=="") {
			item.html(msg+" 입력해 주세요");
			mail.val("");
			return true;
		}else {
			return false;
		}
	}
		
	$(function() {
		$("#replyInsert").click(function() {
			if(formCheck($("#author"), $("#check"), "이름을 ")) return;
			else if(formCheck($("#title"), $("#check"), "비밀번호를 ")) return;
			else if(formCheck($("#content"), $("#check"), "비밀번호를 ")) return;
			else if(formCheck($("#passwd"), $("#check"), "비밀번호를 ")) return;
			else {
				$("#f_replyForm").attr({ 
					"method" : "post",
					"action" : "/board/reply.do"
				});
				$("#f_replyForm").submit();
			}
		})
		$("#title, #content, #passwd").focus(function() {
			$("#check").html("");
		})
		
		//목록 버튼 클릭 시 처리 이벤트
		$("#boardListBtn").click(function() {
			location.href="/board/list.do";
		});
	})
</script>
</head>
<body>
<div id="boardTit"><h3>글쓰기</h3></div>
<form id="f_replyForm" name="f_replyForm"	>
	<input type="hidden" id="num" name="num" value="${ detail.num }">
	<input type="hidden" id="repRoot" name="repRoot" value="${ detail.repRoot }">
	<input type="hidden" id="repIndent" name="repIndent" value="${ detail.repIndent }">
	<input type="hidden" id="repStep" name="repStep" value="${ detail.repStep }">
	<table id="boardWrite">
		<colgroup>
			<col width="20%">
			<col width="30%">
			<col width="20%">
			<col width="30%">
		</colgroup>
		<tr>
			<td class="ac">원글번호</td>
			<td>${ detail.num } &nbsp;&nbsp;
			<span>(조회수 : ${ detail.readcnt })</span></td>
			<td class="ac">원글작성일</td>
			<td align="center">${ detail.writeday }</td>
		</tr>
		<tr>
			<td class="ac">원글작성자</td>
			<td>${ detail.author }</td>
			<td class="ac">작성자</td>
			<td><input type="text" name="author" id="author" /></td>
		</tr>
		<tr>
			<td class="ac">제목</td>
			<td colspan="3"><input type="text" name="title" id="title" value="[답변] ${ detail.title }" /></td>
		</tr>
		<tr>
			<td class="ac">내용</td>
			<td colspan="3">
			<textarea name="content" id="content" rows="10" cols="70">============원글내용===============<c:out value="${ fn:replace(detail.content,'<br>','') }" /></textarea>
			</td>
		</tr>
		<tr>
			<td class="ac">비밀번호</td>
			<td colspan="3"><input type="password" name="passwd" id="passwd" /></td>
		</tr>
	</table>
	<div id="check"></div>
	<div id="boardBtn" align="right">
		<input type="button" value="답변달기" class="but" id="replyInsert" />
		<input type="button" value="목록으로" class="but" id="boardListBtn" />
	</div>
</form>
</body>