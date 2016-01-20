function chkSubmit(v_item,v_msg){
	if(v_item.val().replace(/\s/g,"")==""){
		alert(v_msg+" 입력해 주세요");
		v_item.val("");
		v_item.focus();
		return false;
	}else {
		return true;
	}
}

function formCheck(main,item,msg){//유효성 체크대상 , 출력 영역, 출력될 메시지
	if(main.val().replace(/\s/g,"")==""){
		item.html(msg+" 입력해 주세요");
		main.val("");
		return false;
	}else {
		return true;
	}
}