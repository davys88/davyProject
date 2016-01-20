<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script src="../include/js/html5shiv.js"></script>
<![endif]-->

<link rel="stylesheet" type="text/css" href="../include/css/board.css"/>
<style type="text/css">
	#check {
		color: red;
		font-weight: bold;
		font-size: 15px;
	}
</style>
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
		$("#boardInsert").click(function() {
			if(formCheck($("#author"), $("#check"), "이름을")) return;
			else if(formCheck($("#title"), $("#check"), "제목을 ")) return;
			else if(formCheck($("#content"), $("#check"), "내용을 ")) return;
			else if(formCheck($("#passwd"), $("#check"), "비밀번호를 ")) return;
			else {
				$("#f_writeForm").attr({ 
					"method" : "post",
					"action" : "/board/write.do"
				});
				$("#f_writeForm").submit();
			}
			
		})
		$("#author, #title, #content, #passwd").focus(function() {
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
<div id="boardTit"><h2 align="center">글쓰기</h2></div>
<div id="check" align="center"></div>
<br>
<form id="f_writeForm" name="f_writeForm">
<table id="boardWrite">
	<colgroup>
		<col width="10%">
		<col width="90%">
	</colgroup>
	<tr>
		<td class="ac">작성자</td>
		<td><input type="text" name="author" id="author" /></td>
	</tr>
	<tr>
		<td class="ac">글제목</td>
		<td><input type="text" name="title" id="title"  /></td>
	</tr>
	<tr>
		<td class="ac">내용</td>
		<td><textarea name="content" id="content" rows="10" cols="70"></textarea></td>
	</tr>
	<tr>
		<td class="ac">비밀번호</td>
		<td><input type="password" name="passwd" id="passwd" /></td>
	</tr>
</table>
</form>
<div id="boardBtn" align="right">
	<input type="button" value="저장" class="but" id="boardInsert" />
	<input type="button" value="목록" class="but" id="boardListBtn" />
</div>
</body>
</html>