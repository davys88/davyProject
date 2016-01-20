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
	
<title>Insert title here</title>

<!-- 모바일 웹 페이지 설정 -->
<link rel="shortcut icon" href="../image/icon.png" />
<link rel="apple-touch-icon" href="../image/icon.png" />
<!-- 모바일 웹 페이지 설정 끝 -->
	
<!--[if lt IE 9]>
<script src="../include./js/html5shiv.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="../include/css/board.css"/>
<script type="text/javascript" src="../include/js/jquery-1.11.3.min.js" ></script>
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
		$("#boardUpdate").click(function() {
			if(formCheck($("#title"), $("#check"), "제목을 ")) return;
			else if(formCheck($("#content"), $("#check"), "내용을 ")) return;
			else if(formCheck($("#passwd"), $("#check"), "비밀번호를 ")) return;
			else {
				$("#f_updateForm").attr({ 
					"method" : "post",
					"action" : "/board/update.do"
				});
				$("#f_updateForm").submit();
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
<div id="boardTit"><h3>글수정</h3></div>
<form id="f_updateForm" name="f_updateForm"	>
	<input type="hidden" id="num" name="num" value="${ detail.num }">
	<table id="boardWrite">
		<colgroup>
			<col width="20%">
			<col width="30%">
			<col width="20%">
			<col width="30%">
		</colgroup>
		<tr>
			<td class="ac">글번호</td>
			<td>${ detail.num } &nbsp;&nbsp;
			<span>(조회수 : ${ detail.readcnt })</span></td>
			<td class="ac">작성일</td>
			<td align="center">${ detail.writeday }</td>
		</tr>
		<tr>
			<td class="ac">작성자</td>
			<td colspan="3">${ detail.author }</td>
		</tr>
		<tr>
			<td class="ac">제목</td>
			<td colspan="3"><input type="text" name="title" id="title" value="${ detail.title }" /></td>
		</tr>
		<tr>
			<td class="ac">내용</td>
			<td colspan="3"><textarea name="content" id="content" rows="10" cols="70"><c:out value="${ fn:replace(detail.content,'<br>','') }" /></textarea></td>
		</tr>
		<tr>
			<td class="ac">비밀번호</td>
			<td colspan="3"><input type="password" name="passwd" id="passwd" /></td>
		</tr>
	</table>
	<div id="boardBtn" align="right">
		<input type="button" value="수정" class="but" id="boardUpdate" />
		<input type="button" value="목록" class="but" id="boardListBtn" />
	</div>
</form>
</body>
</html>