<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" type="text/css" href="../include/css/reply.css"/>

<script type="text/javascript" src="../include/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var num = "<c:out value='${detail.num}' />";
		listAll(num)
		$("#replyInsert").click(function() {
			if( formCheck($("#r_name"), $("#check"), "이름을 ") ) return;
			else if( formCheck($("#r_pwd"), $("#check"), "비밀번호를 ") ) return; 
			else if( formCheck($("#r_content"), $("#check"), "내용을 ") ) return;
			else {
				var insertUrl = "/replies/replyInsert.re";
				
				$.ajax({
					url : insertUrl,
					type : "post",
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					dataType: "text",
					data : JSON.stringify({
						num : num,
						r_name : $("#r_name").val(),
						r_pwd : $("#r_pwd").val(),
						r_content : $("#r_content").val()
					}),
					error : function() {
						alert("시스템 오류입니다. 관리자에게 문의 하세요");
					},
					success : function(result) {
						if(result == "SUCCESS") {
							alert("댓글 등록이 완료되었습니다.");
							dataReset();
							listAll(num);
						}
					}
				});///////////end of ajax
			}
		});////////////////////end of click
	});
	
	//댓글 수정버튼 클릭시 수정폼 출력
	$(document).on("click", ".update_form", function() {
		$(".reset_btn").click();
		var conText = $(this).parents("li").children().eq(1).html();
		console.log(conText);
		
		$(this).parents("li").find("input[type='button']").hide();
		var conArea = $(this).parents("li").children().eq(1);
		
		conArea.html("");
		var data = "<textarea name='content' id='content' cols='10'>"+conText+"</textarea>";
		data += "<input type='button' class='update_btn' value='수정완료' />";
		data += "<input type='button' class='reset_btn' value='수정취소' />";
		conArea.html(data);		
	});
	
	//수정취소버튼 시 원래대로 수정
	$(document).on("click", ".reset_btn", function() {
		var conText = $(this).parents("li").find("textarea").html();
		$(this).parents("li").find("input[type='button']").show();
		var conArea = $(this).parents("li").children().eq(1);
		conArea.html(conText);
	});
	
	//새로운 글을 화면에 추가하기 위한 함수
	function addNewItem(r_num, r_name, r_content, r_date) {
		//새로운 글이 추가된 li태그 객체
		var new_li = $("<li>");
		new_li.attr("data-num", r_num);
		new_li.addClass("comment_item");
		
		//작성자 정보가 지정될 <p>태그
		var writer_p = $("<p>");
	}
	
	//댓글리스트 불러오는 함수
	function listAll(num) {
		$("#comment_list").html("");
		
		var url = "/replies/replyList.re?num="+num;
		$.getJSON(url, function(data) {
			console.log(data.length);
			
			$(data).each(function() {
				var r_num = this.r_num;
				var r_name = this.r_name;
				var r_content = this.r_content;
				var r_date = this.r_date;
				addNewItem(r_num, r_name, r_content, r_date);
			});
		}).fail(function() {
			alert("댓글 목록을 불러오는데 실패하였습니다.")
		})
	}
	
	//태그를 써서 가져온 댓글목록을 뿌려주기
	function addNewItem(r_num, r_name, r_content, r_date) {
		var new_li = $("<li>");
		new_li.addClass("comment_item");
		
		var writer_p = $("<p>");
		writer_p.addClass("writer");
		
		var name_span = $("<span>");
		name_span.addClass("name");
		name_span.html(r_name+"님");
		
		var date_span = $("<span>");
		date_span.html("/"+r_date+" ");
		
		var up_input = $("<input>");
		up_input.attr({"type" : "button", "value" : "수정하기"});
		up_input.addClass("update_form");
		
		var del_input = $("<input>");
		del_input.attr({"type" : "button", "value" : "삭제하기"});
		del_input.addClass("delete_btn");
		
		var content_p = $("<p>");
		content_p.addClass("con");
		content_p.html(r_content);
		
		//조립하기
		writer_p.append(name_span).append(date_span).append(up_input).append(del_input);
		new_li.append(writer_p).append(content_p);
		$("#comment_list").append(new_li);
	}
	
	function formCheck(mail, item, msg) {
		if(mail.val().replace(/\s/g, "")=="") {
			item.html(msg+" 입력해 주세요");
			mail.val("");
			return true;
		}else {
			return false;
		}
	}
	
	function dataReset() {
		$("#r_name", "#r_pwd", "#content").val("");
	}
</script>
</head>
<body>
<div id="replyContainer">
	<div id="comment_write">
		<form id="commnet_form">
			<div><div id="check"></div>
				<label for="r_name">작성자</label>
				<input type="text" name="r_name" id="r_name" />
				<label for="r_pwd">비밀번호</label>
				<input type="text" name="r_pwd" id="r_pwd" />
				<input type="button" id="replyInsert" value="저장하기" />
			</div>
			<div>
				<label for="r_content">댓글 내용</label>
				<textarea name="r_content" id="r_content"></textarea>
			</div>
		</form>
	</div>
	<ul id="comment_list">
		<!-- 여기에 동적 생성 요소가 들어가게 됩니다. -->
	</ul>
</div>
</body>
</html>