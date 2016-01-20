<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/tld/custom_tag.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>목록보기</title>

<!-- 모바일 웹 페이지 설정 -->
<link rel="shortcut icon" href="../image/icon.png" />
<link rel="apple-touch-icon" href="../image/icon.png" />
<!-- 모바일 웹 페이지 설정 끝 -->
	
<!--[if lt IE 9]>
<script src="../include/js/html5shiv.js"></script>
<![endif]-->
<style type="text/css">
	.goDetail {font-weight: bold; font-size: 14px; color: red;}
</style>
<script type="text/javascript" src="../include/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		/*검색 후 검색 대상과 검색 단어 출력*/
		if("<c:out value='${data.keyword}' />" != "") {
			$("#keyword").val("<c:out value='${data.keyword}' />")
			$("#search").val("<c:out value='${data.search}' />")
		}
		/*검색 대상이 변결될 때마다 처리 이벤트*/
		$("#search").change(function() {
			if($("#search").val() == "all") {
				$("#keyword").val("전체 데이터 조회합니다.");
			} else if($("#search").val() != "all") {
				$("#keyword").val("");
				$("#keyword").focus();
			}
		});
		$("#searchData").click(function() {
			if($("#search").val() != "all") {
				if(formCheck($("#keyword"), $("#keywordCheck"), "검색어를")) return;
			}
			goPage(1);
		});
		$("#keyword").focus(function() {
			$("#keywordCheck").html("");
		});
		$("#writeForm").click(function(){
			location.href="/board/writeForm.do"
		});
		if("<c:out value='${data.pageSize}' />"!="") {
			$("#pageSize").val("<c:out value='${data.pageSize}' />");
		}
		
		//한 페이지에 보여줄 레코드 수 변경될 때마다 처리 이벤트
		$("#pageSize").change(function() {
			goPage(1);
		});
		
		$(".goDetail").click(function() {
			var num = $(this).parent().parent().children().eq(0).html();
			$("#num").val(num);
			console.log(num);
			
 			$("#detailForm").attr({
				"method" : "post",
				"action" : "/board/detail.do"
			})
			$("#detailForm").submit(); 
		});
	});
	/*검색과 한 페이지에 보여줄 레코드 수 처리 및 페이징을 위한 실질적인 자리 함수*/
	function goPage(num) {
		if($("#search").val() == "all") {
			$("#keyword").val("");
		}
		$("#page").val(page);
		$("#f_search").attr({
			"method" : "post",
			"action" : "/board/list.do"
		});
		$("#f_search").submit();
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
</script>
<link rel="stylesheet" type="text/css" href="../include/css/board.css"/>
</head>
<body>
<div id="boardContainer">
<div id="boardTit"><h3 align="center">글목록</h3></div>
<form name="detailForm" id="detailForm">
	<input type="hidden" id="num"	 name="num" />
</form>
<%-- ================검색기능 시작================ --%>
<div id="boardSearch">
	<form id="f_search" name="f_search">
	<input type="hidden" id="page" name="page" value="${data.page}" />
		<input type="hidden" id="page" name="page" value="1" />
		<table summary="검색">
			<tr>
				<td id="btd1">
					<label>검색조건</label>
					<select id="search" name="search">
						<option value="all">전체</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="author">작성자</option>		
					</select>
					<input type="text" name="keyword" id="keyword" value="검색어를 입력하세요" />
					<input type="button" value="검색" id="searchData" />
					<span id="keywordCheck"></span>
				</td>
				<td id="btd2">한페이지에 
					<select id="pageSize" name="pageSize">
						<option value="2">2줄</option>
						<option value="3">3줄</option>
						<option value="4">2줄</option>
						<option value="5">5줄</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<%-- ================검색기능 종료================ --%>

<%-- ================리스트 시작================ --%>
<div id="boardList">
	<table summary="게시판 리스트" border="1">
			<colgroup>
				<col width="10%"/>
				<col width="50%"/>
				<col width="15%"/>
				<col width="13%"/>
				<col width="12%"/>
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty list }">
						<c:forEach var="dto" items="${list }"><!-- item의 컬랙션값을 가져온다 -->
								<tr align="center">
						       		<td>${dto.num }</td>
									<td align="left">
									<span class="goDetail">${dto.title }
									</span></td>
									<td>${dto.author }</td>
									<td>${dto.writeday }</td>
									<td>${dto.readcnt }</td>
								</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
					<tr>
						<td colspan="5" align="center">등록된 게시물이 존재하지 않습니다.</td>
					</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
	</table>
</div>
<%-- ================글쓰기 버튼 출력 시작================ --%>
<div id="boradBut" align="right">
	<input type="button" value="글쓰기"  id="writeForm">
</div>
	<div id="boardPage">
		<tag:paging page="${param.page}" total="${total}" list_size="${data.pageSize}" />
	</div>
</div>
</body>
</html>