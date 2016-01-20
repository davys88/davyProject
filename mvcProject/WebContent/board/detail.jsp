<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
<title>상세보기</title>

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
	var butChk=0; //수정버튼과 삭제버튼을 구현하기 위한 변수
	$(function() {
		$("#pwdChk").hide();
		//수정 버튼 클릭 시 처리 이벤트
		$("#updateForm").click(function() {
			$("#pwdChk").show();
			$("#msg").text("작성시 입력한 비밀번호를 입력해 주세요").css("color", "#000099")
			butChk=1;
		});
		//삭제 버튼 클릭 시 처리 이벤트
		$("#boardDelete").click(function() {
			$("#pwdChk").show();
			$("#msg").text("작성시 입력한 비밀번호를 입력해 주세요").css("color", "#000099")
			butChk=2;
		});
		//목록 버튼 클릭 시 처리 이벤트
		$("#boardListBtn").click(function() {
			location.href="/board/list.do";
		});
		//비밀번호 확인 버튼 클릭 시 처리 이벤트
		$("#pwdBut").click(function() {
			pwdConfirm(butChk);
		});
        $("#passwd").bind("keydown", function(e) {
            if (e.keyCode == 13) { // enter key
				pwdConfirm(butChk);
                return false
            }
        });
		//답변 버튼 클릭 시 처리 이벤트
		$("#boardReply").click(function() {
			$("#f_data").attr({
				"method" : "post",
				"action" : "/board/replyForm.do"
			});
			$("#f_data").submit();
		});
	});
	
	function pwdConfirm(butChk) {
		if(formCheck($("#passwd"), $("#msg"), "비밀번호를")) return;
		else {
			$.ajax({
				url : "/board/pwdCheck.do",  //전송 url
				type : "POST",					//전송 시 메소드
				data : $("#f_pwd").serialize(),	//폼 전체 데이터 전송
				dataType : "text",			//받아 올 데이터 타입
				error : function(){
					alert("시스템오류입니다");
				},
				success : function(resultData) {
					var goUrl="";
					if(resultData==0) {
						$("#msg").text("작성시 입력한 비밀번호가 일치하지 않습니다").css("color", "red");
						$("#passwd").select();
					} else if(resultData==1) {
						$("#msg").text("");
						if(butChk==1) {
							goUrl = "/board/updateForm.do";
						} else if(butChk==2) {
							goUrl = "/board/delete.do";
						}
						$("#f_data").attr("action", goUrl);
						$("#f_data").submit();
					}
				}////////////////////////////end of success
			})///////////////////////////////end of ajax
		}
	}////////////////////////////////////////end of function
	
	function formCheck(mail, item, msg) {
		if(mail.val().replace(/\s/g, "")=="") {
			item.html(msg+" 입력해 주세요");
			mail.val("");
			return true;
		}else {
			return false;
		}
	}
</script>
</head>
<body>
<div id="boardContainer">
<div id="boardTit"><h2 align="center">상세보기</h2></div>
<form name="f_date" id="f_data" method="post">
	<input type="hidden" name="num" id="num" value="${ detail.num }" />
</form>
<table id="boardPwdBut">
	<tr>
		<td id="btd1">
			<div id="pwdChk">
				<form name="f_pwd" id="f_pwd">
					<input type="hidden" name="num" id="num" value="${ detail.num }" />
					<label for="b_pwd" id="l_pwd">비밀번호 : </label>
					<input type="password" name="passwd" id="passwd" />
					<input type="button" value="확인" id="pwdBut" />
					<span id="msg"></span>
				</form>
			</div>
		</td>
		<td id="btd2">
			<input type="button" value="수정" class="but" id="updateForm" />
			<input type="button" value="삭제" class="but" id="boardDelete" />
			<input type="button" value="답변" class="but" id="boardReply" />
			<input type="button" value="목록" class="but" id="boardListBtn" />
		</td>
	</tr>
</table>
<%-- =============상세 정보 보여주기 시작============== --%>
<div id="boardDetail">
<table>
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
		<td colspan="3">${ detail.title }</td>
	</tr>
	<tr class="ctr">
	<td class="ac">내용</td>
	<td colspan="3">${ detail.content }</td>
	</tr>
</table>
</div>
</div>
<%-- =============상세 정보 보여주기 종료============== --%>
<jsp:include page="reply.jsp"></jsp:include>
</body>
</html>