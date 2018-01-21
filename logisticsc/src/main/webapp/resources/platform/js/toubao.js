// JavaScript Document



var bxKt=0;


/*if(isCopy>0){//浣跨敤浜嗗鍒跺姛鑳�
	$("#tb_tmenu a[tag="+$("#bx_company").val()+"]").addClass('hover');
	$("#bx_type_ul li[rel="+$("#bx_type").val()+"]").find('.tb').addClass('hover');
	if($("#bx_type").val()=='sz'){//澶辫釜闄�
		$(".szxinfobox").css('display','block');
	}
	changeWH();
}else{
	$("#tb_tmenu a[rel=1]").addClass('hover');
	$("#bx_company").val($("#tb_tmenu a.hover").attr('tag'));
}*/
$("#tb_tmenu a").click(function(){
	//$("#bx_tstype").val('');
	 $('.input input:radio:first').attr('checked', 'checked');
	//$("#bx_rates").val('');
	//$("#bx_money").val('');
	
	$("#tb_tmenu a").removeClass('hover');
	$(this).addClass('hover');
	$("#bx_company").val($(this).attr('tag'));
	
	
	/*if($("#bx_type").val()!=''){
		checkBxkt();
		if(!bxKt){
			alert('您未开通此类保险业务，请联系您的专属客服！');
			$("#bx_rates").val('');
			$("#bx_money").val('');
			return false;
		}else{
			$("#bx_rates").val(rates[$("#bx_company").val()][$("#bx_type").val()]);
		}
		
		
	}*/
	
	return false;
})

function bxTypeClk(obj){
	$("#bx_type_ul").find("li").find(".tb").removeClass('hover');
	$(obj).find(".tb").addClass('hover');
	$("#bx_type").val($(obj).attr('rel'));
	if($("#bx_type").val()=='sz'){//失踪险
		$(".szxinfobox").css('display','block');
		changeWH(); //在insurance_add.php页面底部JS代码中，仅针对insurance_add.php有效
	}else{//其他险种
		$(".szxinfobox").css('display','none');
		changeWH();
	}
}
/*$("#bx_type_ul li").click(function(){
	//$("#bx_tstype").val('');
	 //$('.input input:radio:first').attr('checked', 'checked');
	$("#bx_type_ul").find("li").find(".tb").removeClass('hover');
	$(this).find(".tb").addClass('hover');
	$("#bx_type").val($(this).attr('rel'));
	checkBxkt();
	
	if(!bxKt){
		alert('您未开通此类保险业务，请联系您的专属客服！');
		$("#bx_type").val('');
		$("#bx_type_ul li").find(".tb").removeClass('hover');
		$("#bx_rates").val('');
		$("#bx_money").val('');
		return false;
	}else{
		$("#bx_rates").val(rates[$("#bx_company").val()][$("#bx_type").val()]);
		
	}
	
	if($("#bx_type").val()=='sz'){//失踪险
		$(".szxinfobox").css('display','block');
		changeWH(); //在insurance_add.php页面底部JS代码中，仅针对insurance_add.php有效
	}else{//其他险种
		$(".szxinfobox").css('display','none');
		changeWH();
	}
	
})*/

/*function checkBxkt(){
	var bxFl=rates[$("#bx_company").val()][$("#bx_type").val()];
	if(!bxFl){
		bxKt=0;
	}else{
		bxKt=1;
	}
}*/

$("#tb_tmenu a").click(function(){
	//$("#bx_tstype").val('');
	 $('.input input:radio:first').attr('checked', 'checked');
	//$("#bx_rates").val('');
	//$("#bx_money").val('');
	
	$("#tb_tmenu a").removeClass('hover');
	$(this).addClass('hover');
	$("#bx_company").val($(this).attr('tag'));
	
	
	/*if($("#bx_type").val()!=''){
		checkBxkt();
		if(!bxKt){
			alert('鎮ㄦ湭寮€閫氭绫讳繚闄╀笟鍔★紝璇疯仈绯绘偍鐨勪笓灞炲鏈嶏紒');
			$("#bx_rates").val('');
			$("#bx_money").val('');
			return false;
		}else{
			$("#bx_rates").val(rates[$("#bx_company").val()][$("#bx_type").val()]);
		}
		
		
	}*/
	
	return false;
})

