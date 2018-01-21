<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<meta content="no-cache" http-equiv="pragma">
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/insurance.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap日期-->
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.js"></script>
<!-- bootstrap日期中文-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.zh-CN.js"></script>
<title>我要投保</title>
</head>
<script>
	var rates={"yangguang":{"jb":"5","xh":"7","ys":"5","zh":"7","lc":"","sz":"6"},"taipingyang":{"jb":"5","xh":"7","ys":"6","zh":"7","lc":"","sz":"7"},"renbao":{"jb":"5","xh":"7","ys":"5","zh":"7","lc":"","sz":"8"},"pingan":{"jb":"5","xh":"7","ys":"4","zh":"","lc":"","sz":"9"},"tstype":{"es":"","spc":"","xz":""}};
	var minmoney={"yangguang":{"jb":"30","xh":"0","ys":"30","zh":"30","lc":"0","sz":"0"},"taipingyang":{"jb":"30","xh":"80","ys":"40","zh":"40","lc":"50","sz":"60"},"renbao":{"jb":"30","xh":"80","ys":"0","zh":"30","lc":"0","sz":"0"},"pingan":{"jb":"60","xh":"0","ys":"60","zh":"60","lc":"0","sz":"0"}};
	var role=0;
	var isCopy=0;
	var isEdit=0;
	var myMoney='2000.00';
	var li=2;
	$("#li"+li).addClass('hover');
	function changeWH(){
		var lH=$("#left").height();
		var rH=$("#right").height()+63;
		var cH=0;
		if(lH<rH){
			$("#left").css('height',rH+80);
			cH=rH+186;
		}else{
			
			$("#left").css('height',rH+80);
			cH=rH+186;
		}
		$("#center").css("height",cH);
		//alert(cH);
	}
	changeWH(); 
	
	
	$(function(){
		
		check(); 
		
		$('#tb_tmenu a:first').addClass('hover');
		$("#bx_company").val($('#tb_tmenu a:first').attr('tag'));
		getTAndTs($('#tb_tmenu a:first').attr('tag'));
		
		
		
		//复制保险
		var insId = $("#ins_id_hid").val();
		if(insId&&insId!=null&&insId!=''){
			var obj = getInsInfo(insId);
			$("#tb_tmenu a").removeClass("hover");
			$("#tb_tmenu a").each(function(){
				if($(this).attr("tag")==obj.insComTag){
					$(this).addClass("hover");
					$("#bx_company").val($(this).attr("tag"));
				}
			})
			
			$("#bx_type_ul li").each(function(){
				if($(this).attr("rel")==obj.insType){
					$(this).find(".tb").addClass("hover");
					$("#bx_type").val($(this).attr("rel"));
				}
			})
			$("#ins_true_name").val(obj.insTrueName);
			$("#ins_tel").val(obj.insTel);
			$("#ins_card_num").val(obj.insCardNum);
			$("#ins_address").val(obj.insAddress);
			$("#ins_che_num").val(obj.insCheNum);
			$("#ins_he_tong").val(obj.insHeTongNum);
			
			$("#ins_start_time").val(formateDate(obj.insStartTime));
			$("#ins_trans_type option").each(function(){
				if($(this).val() == obj.insTransType){
					$(this).attr("selected","selected");
				}
			})
			$("#ins_load_type option").each(function(){
				if($(this).val() == obj.insLoadType){
					$(this).attr("selected","selected");
				}
			})
			$("#ins_goods_type option").each(function(){
				if($(this).val() == obj.insGoodsType){
					$(this).attr("selected","selected");
				}
			})
			$("#ins_bz option").each(function(){
				if($(this).val() == obj.insBaoZhuang){
					$(this).attr("selected","selected");
				}
			})
			$("#ins_huo_wu").val(obj.insHuoWu);
			$("#ins_hw_num").val(obj.insHuowuNum);
			$("#bx_jine").val(obj.insJine);
			$("#bx_ts_type_span span input").each(function(){
				if($(this).val() == obj.insTsType){
					$(this).attr("checked","checked");
				}
			})
			$("#ins_start_add").val(obj.insStartAdd);
			$("#ins_end_add").val(obj.insEndAdd);
			$("#ins_mid_add").val(obj.insMidAdd);
			renderInsRate()
			renderTotalFee();
		}
		
		//时间插件
		$("#ins_start_time").datetimepicker({
			format: 'yyyy-mm-dd hh:00:00',
			language: 'zh-CN', 
			minView:1,
			todayBtn: 'linked',
			autoclose:true ,//选择日期后自动关闭 
			
			
		});
		
		$('#ins_start_time').datetimepicker('setStartDate',getCurTime());
	})
	
	function renderInsRate(){
		/* $("#bx_img,#bx_type_,#bx_ts_type").click(function(){ */
		var insCompanyTag = $("#bx_company").val(),
			insTypeTag = $("#bx_type").val(),
			insTsTypeTag = $("#bx_ts_type_span span input[name='insTsType']:checked").val();
		
		if(insCompanyTag&&insCompanyTag!=null&&insCompanyTag!=''
				&&insTypeTag&&insTypeTag!=null&&insTypeTag!=''){
			data = {insCompanyTag:insCompanyTag,insTypeTag:insTypeTag,insTsTypeTag:insTsTypeTag}
			$.ajax({
				url:'<%=request.getContextPath()%>/insurance/getInsRate.shtml',
				type:'post',
				async:false,
				data:data,
				success:function(result){
					if(result.result){
						var data = result.data,
							typeRate = null,
							tsTypeRate = null,
							typeLowest = null,
							tsTypeLowest = null;
						if(data.type){
							typeRate = data.type.insRate,
							typeLowest = data.type.insLowestPrice;
						}
						if(data.tsType&&data.tsType!=null){
							tsTypeRate = data.tsType.insRate,
							tsTypeLowest = data.tsType.insLowestPrice;
						}
						if(tsTypeRate!=null){
							$("#ins_rate").val(typeRate>=tsTypeRate?typeRate:tsTypeRate);
							$("#lowest_price").val(typeRate>=tsTypeRate?typeLowest:tsTypeLowest);
						}else{
							$("#ins_rate").val(typeRate);
							$("#lowest_price").val(typeLowest);
						}
						
					}
				}
			})
			
		}
	}

	
	//获取当前时间后一小时
	function getCurTime(){
		var date = new Date(),
			year = date.getFullYear(),
			month = dateTransfer(date.getMonth()+1),
			day = dateTransfer(date.getDate()),

			hour = dateTransfer(date.getHours()+2),
			minute = dateTransfer(date.getMinutes()),
			second = dateTransfer(date.getSeconds());
		return year+"-"+month+"-"+day+" "+hour+":00:00";
		 
	}
	
	//格式化日期
	function formateDate(sdate){
		if(sdate){
			var date = new Date(sdate),
			
				year = date.getFullYear(),
				month = dateTransfer(date.getMonth()+1),
				day = dateTransfer(date.getDate()),
	
				hour = dateTransfer(date.getHours()),
				minute = dateTransfer(date.getMinutes()),
				second = dateTransfer(date.getSeconds());
			return year+"-"+month+"-"+day+" "+hour+":00:00";
		}else{
			return "";
		}
		
	}
	
	function dateTransfer(str){
		if((str+"").length<=1){
			return "0"+str;
		}else{
			return str;
		}
		
	}
	
	
	
	//获取保单信息
	function getInsInfo(insId){
		var obj;
		$.ajax({
			url:'<%=request.getContextPath()%>/insurance/getInsInfo.shtml',
			type:'post',
			async:false,
			data:{insId:insId},
			success:function(result){
				if(result.result){
					obj = result.data;	
				}
			}
		})
		return obj;
	}
	
	function closeSubAlert(){
		$("#alertBox").css('display','none');
		$("#bigbg").css('display','none');
	}
	
	
	
	function confirmSub(){
		var flag = false;
		var form = $("#toubaoform").serialize();
		$.ajax({
			url:'<%=request.getContextPath()%>/insurance/addInsurance.shtml',
			type:'post',
			data:form,
			async:false,
			success:function(result){
				if(result.result){
					$("#zhifuOrderNumber").val(result.data);
					flag = true;					
				}
			}
		})
		return flag;
	}
	
	function payIns(){
		if(confirmSub()){
			$("#zhifujine").val($("#alert_bx_money").val());
			$("#dingdanleixing").val("保险费");
			//
			$("#orderType").val(0);
			$("#yunChongzhi").modal({show : true});
		}
		
	}
	
	function checkBox(){
		
		var dowW=$(document).width();
		var dowH=$(document).height();
		var alertW=612;
		var mleft=(dowW-alertW)/2;
		var mtop=$(document).scrollTop();
		
		$("#bigbg").css('height',dowH);
		$("#bigbg").css('display','block');
		$("#alertBox").css('left',mleft);
		$("#alertBox").css('top',mtop);
		$("#alertBox").css('display','block');
		
		
		$("#alert_bx_logo").attr('src',$("#tb_tmenu").find('.hover').find('img').attr('src'));
		$("#alert_bx_type").html($("#bx_type_ul").find('.hover').parent().find('span').not('.tb').html());
		$("#alert_bx_truename").val($("#ins_true_name").val());
		$("#alert_bx_tel").val($("#ins_tel").val());
		$("#alert_bx_huowu").val($("#ins_huo_wu").val());
		$("#alert_bx_chenum").val($("#ins_che_num").val());
		$("#alert_bx_jine").val(Math.round($("#bx_jine").val()*100)/100);
		$("#alert_bx_cardnum").val($("#ins_card_num").val());
		$("#alert_bx_money").val($("#bx_money").val());
		$("#alert_bx_startadd").val($("#ins_start_add").val());
		$("#alert_bx_endadd").val($("#ins_end_add").val());
		
	}
	
	function subIns(){
		if($("#bx_type").val() == null || $("#bx_type").val() == ''){
			$("#type_des").html("请选择险种");
		}else{
			if(isValid()){
				var bxMoney = $("#bx_money").val(),
					lowestPrice = $("#lowest_price").val(); 
				if(parseFloat(bxMoney)<parseFloat(lowestPrice)){
					alert("此保险品牌的险种最低保费是"+$("#lowest_price").val()+"元，您的保单保费低于此费用!")			
					return false;
					//$("#bx_money").val($("#lowest_price").val());
				}
				checkBox();
				return false;
			}else{
				flag = true;
			}
		}
		
	}
	
	//计算总费用
	function renderTotalFee(){
		var insRate = $("#ins_rate").val();
		if(insRate!=null&&insRate!=''){
			if(!isNaN($("#bx_jine").val())){
				var jine = Math.round($("#bx_jine").val()*100)/100;
				$("#bx_money").val(Math.round(10000*insRate*jine*100)/100);
			}
		}
	}
	
	//保险险种点击事件
	function typeClick(obj){
		$("#type_des").html("");
		$("#bx_money").val("");
		$("#bx_jine").val("");
	}
	
	//保险品牌点击事件
	function comClick(obj){
		var str = $(obj).attr("tag");
		$("#bx_company").val(str);
		getTAndTs(str);
		getInsNote(str);
		$('#noteModal').modal('toggle');
	}
	
	//投保注意事项
	function getInsNote(itemAbbr){
		$.ajax({
			url:'<%=request.getContextPath()%>/sys/config/getInsNote.shtml',
			type:'post',
			data:{itemAbbr:itemAbbr},
			async:false,
			success:function(result){
				if(result.result){
					if(result.data){
						$("#insNoteCnt").html(result.data.itemContent);
					}
									
				}
			}
		})
	}
	
	//动态获取险种
	function getTAndTs(companyTag){
		$.ajax({
			url:'<%=request.getContextPath()%>/insurance/getTsAndT.shtml',
			type:'post',
			data:{companyTag:companyTag},
			success:function(result){
				var type_obj = $("#bx_type_ul"),
					ts_type_obj = $("#bx_ts_type_span"), 
					data = result.data;
				if(result.result){
					type_obj.find("li").remove();
					ts_type_obj.empty();
					for ( var i=0;i<data.typeList.length;i++) {
						var type_li = '<li id="bx_type_" rel="'+data.typeList[i].insTypeTag+'" onclick="typeClick(this);bxTypeClk(this);renderInsRate();"><span class="tb"></span><span>'+data.typeList[i].insTypeName+'</span></li>';
						type_obj.append(type_li);
					}
					ts_type_obj.append('<span><input type="radio" id="bx_ts_type" name="insTsType" class="bx_tstype" value="${insTsType.insTsTypeTag }" fl="" style=" background:none; border:none; width:auto; height:auto; position:relative; left:auto; top:auto;" checked="checked" onclick="renderInsRate()"/>无&nbsp;&nbsp;</span>');
					for ( var i=0;i<data.tsTypeList.length;i++) {
						ts_type_obj.append('<span><input type="radio" id="bx_ts_type" name="insTsType" class="bx_tstype" value="'+data.tsTypeList[i].insTsTypeTag+'" fl="" style=" background:none; border:none; width:auto; height:auto; position:relative; left:auto; top:auto;"  onclick="renderInsRate()"/>'+data.tsTypeList[i].insTsTypeName+'&nbsp;&nbsp;</span>')
					} 
				} 
			}
		})
	}
	//支付确认提示框
	function wancheng() {
		$("#yunChongzhi").modal('hide');
		$("#querenModal").modal('show');
	}
	//确认支付刷新跳转
	function shuaxin() {
		window.location.href="/logisticsc/orderCenter/toorderlistpage.shtml";
	}
	
	//表单绑定change事件验证
	function check(){
		$("input,select").bind("change",function(){
			verify(this);
		})
	}
	
	//保单验证
	var flag = true;
	function isValid(){
		if($("#bx_type").val() == 'sz'){
			$("input,select").each(function(){
				if($(this).hasClass("chk_empty")
						||$(this).hasClass("chk_phone")
						||$(this).hasClass("chk_car_num")){
					if(!verify(this)){
						flag = false;
					}
				}
			})
		}else{
			$("input,select").not("#div_sz_content input,select").each(function(){
				if($(this).hasClass("chk_empty")
						||$(this).hasClass("chk_phone")
						||$(this).hasClass("chk_car_num")){
					if(!verify(this)){
						flag = false;
					}
				}
			})
		}
		
		return flag;
	}
	
	function verify(obj){
		var node = $(obj).parent().parent().find(".des");
		if($(obj).hasClass("chk_empty")){
			if(!verifyEmpty($(obj).val())){
				var msg = "#不能为空";
				node.html(msg);
				node.css("color","red");
				return false;
			}else{
				node.empty();
			}
		}
		
		if($(obj).hasClass("chk_phone")){
			if(!verifyRegPhone($(obj).val())){
				var msg = "#手机号错误";
				node.html(msg);
				node.css("color","red");
				return false;
			}else{
				node.empty();
			}
		}
		if($(obj).hasClass("chk_car_num")){
			if(!verifyCarNum($(obj).val())){
				var msg = "#车牌号错误";
				node.html(msg);
				node.css("color","red");
				return false;
			}else{
				node.empty();
			}
		}
		if($(obj).hasClass("chk_idNum")){
			if(!verifyIDCard($(obj).val())){
				var msg = "#身份号或者组织代码不正确";
				node.html(msg);
				node.css("color","red");
				return false;
			}else{
				node.empty();
			}
		}
		
		if($(obj).hasClass("chk_vcard")){
			if(!verifyID($(obj).val())){
				var msg = "#身份号不正确";
				node.html(msg);
				node.css("color","red");
				return false;
			}else{
				node.empty();
			}
		}
		if($(obj).hasClass("chk_numb")){
			if(!verifyNum($(obj).val())){
				var msg = "#不是数字";
				node.html(msg);
				node.css("color","red");
				return false;
			}else{
				node.empty();
			}
		}
		
		return true;
	}
	function verifyEmpty(val){
		if(val == null || $.trim(val) == ''){
			return false;
		}else{
			return true;
		}
	}
	function verifyRegPhone(phone){
		phoneReg =  /^0?1[3|4|5|8][0-9]\d{8}$/;
		if(!phoneReg.test(phone)){
			return false;
		}else{
			return true;
		}
	}
	
	function verifyCarNum(carNum){
		var carNumReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
		if(!carNumReg.test(carNum)){
			return false;
		}else{
			return true;
		}
	}
	
	function verifyIDCard(cardNum){
		var cardNumReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
			orgReg = /^([0-9a-z]){8}-[0-9|x]$/;
		
		if(orgReg.test(cardNum)){
			return true;
		}
		if(cardNumReg.test(cardNum)){
			return true;
		}
		
		return false;
	}
	
	function verifyID(cardNum){
		var cardNumReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(cardNumReg.test(cardNum)){
			return true;
		}
		
		return false;
	}
	
	function verifyNum(val){
		 var numReg = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
		 if(numReg.test(val)){
			return true; 
		 }
		 return false;
	}
	
</script>
<body style="background:#f5f5f5;">
<jsp:include page="../top.jsp"></jsp:include>
<div class="container-self" style="width:1200px;">
	<!--右侧开始-->
    	<form action="" method="post" id="toubaoform" name="toubaoform">
    		<input type="hidden" name="action" value="toubao" />
        	<input type="hidden" name="bx_area" value="0" />
   			<div class="tb_tmenu" id="tb_tmenu">
   				<c:forEach var="insBrand" items="${insCompanyList }" step="1" varStatus="st" begin="0" end="${fn:length(insCompanyList) }">
   					<a class="" href="#yangguang" tag="${insBrand.insComTag }" rel="${st.count}" onclick="comClick(this);">
	        			<img id="bx_img" src="<%=request.getContextPath()%>/img/retrive.shtml?resource=${insBrand.insComLogoUrl }" width="83" height="44" alt=""/>
	        			<span></span>
	        		</a>
   				</c:forEach>
	            <span><span><input type="hidden" class="chk_empty" name="insComTag" id="bx_company"/></span></span>
	            <span class="des"></span>
		        <script>
		        	$("#tb_tmenu a[rel=4]").addClass('last');
		        </script>
   		    </div>
   		    <!--国内保险险种-->
    		<div class="right" id="right" style="top:70px;color:#888888; background:#FFF">
    			<div style="width:100%; height:auto;" id="rightMain">
    				<p class="jg10"></p>
    				<div class="tb_xzbox">
	        			<p class="tb_xztxt" style="width:130px;">国内保险险种：</p>
	           		    <div class="tb_xzselect" id="tb_xzselect">
	            			<p class="x">※</p>
	            			<ul id="bx_type_ul">
	            				<%-- <c:forEach var="ins" items="${insurance.insTypeList }">
	            					<li id="bx_type_" rel="${ins.insTypeTag }" onclick="typeClick(this);"><span class="tb"></span><span>${ins.insTypeName }</span></li>
	                			</c:forEach> --%>
		                    
			                    <span><input type="hidden" name="insType" id="bx_type" value="" /></span>
		            			<span id="type_des" style="color:red"></span>
			                </ul>
	           			</div>
        			</div>
        			<div class="irt_tit tb_tit">被保险人信息</div>
			        <div class="tb_row">
			        	<ul>
			            	<li style="margin-left:0;">
			                	<p class="lab">被保险人/单位：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input">
			                        	<input id="ins_true_name" class="chk_empty" name="insTrueName" type="text"  maxlength="50"/>
			                        </span>
			                        <span class="des">#若为公司，请填写营业执照上的全称，司机不能为被保险人</span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">联系电话：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_tel" type="text" class="chk_empty chk_phone" name="insTel" id="bx_tel" value="" maxlength="15" /></span>
			                        <span class="des"># 用于保单生效后接收短信通知</span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">被保险人证件号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_card_num" type="text" name="insCardNum" class="chk_empty chk_idNum" id="bx_cardnum" maxlength="30" value="" /></span>
			                        <span class="des"># 个人投保必须填写身份证号，企业填写组织机构代码</span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">联系地址：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_address" type="text" class="chk_empty" name="insAddress" id="bx_address" maxlength="100" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
        			<div class="irt_tit tb_tit">车辆信息</div>
			        <div class="tb_row">
			        	<ul>
			            	<li style="margin-left:0;">
			                	<p class="lab">船/航/车牌号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_che_num" type="text" class="chk_empty chk_car_num" name="insCheNum" id="bx_chenum" maxlength="100" value="" /></span>
			                        <span class="des"># 如：川A12345，川B1234挂，请准确填写车牌号</span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">合同发票号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input id="ins_he_tong" type="text" name="insHeTongNum" id="bx_hetongnum" maxlength="50" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">起运时间：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_start_time" type="text" name="insStartTime" class="form-control chk_empty" id="ins_start_time"/></span>
			                        <span class="des"># 请选择至少一小时之后的时间</span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">运输方式：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="select" style="background:none">
			                        	<select id="ins_trans_type" name="insTransType" class="chk_empty" style="height:26px;width:100%">
			                        		<c:forEach var="transType" items="${insTransTypeList }">
			                        			<option value="${transType.name }">${transType.name }</option>
			                        		</c:forEach>
			                        	</select>
									</span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">装载方式：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="select" style="background:none">
			                        	<select id="ins_load_type" name="insLoadType" class="chk_empty" style="height:26px;width:100%">
			                        		<c:forEach var="loadType" items="${insLoadTypeList }">
			                        			<option value="${loadType.name }">${loadType.name }</option>
			                        		</c:forEach>
			                        	</select>
									</span>
			                    </p>
			                </li>
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
        			<div id="div_sz_label" class="irt_tit tb_tit szxinfobox" style="display:none">失踪险补充信息</div>
			        <div id="div_sz_content" class="tb_row szxinfobox" style="display:none">
			        	<ul>
			            	<li style="margin-left:0;">
			                	<p class="lab">驾驶员姓名：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_js" type="text" class="chk_empty" name="insJsy" id="bx_jsy" maxlength="20" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">驾驶员手机号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_js_tel" type="text" class="chk_empty chk_phone" rel="phone" name="insJsySj" id="bx_jsysj" maxlength="50" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">驾驶员身份证号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input type="text" rel="idNum" class="chk_empty chk_vcard" name="insJsySfz" id="bx_jsysfz" maxlength="30" value=""  /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">行驶证号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_js_num" type="text" name="insXsNum" id="bx_xsnum" class="chk_empty" maxlength="50" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">营运证号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_js_yyz" type="text" name="insYyNum" id="bx_yynum" class="chk_empty" maxlength="30" value=""  /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">发动机号：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_js_fdj" type="text" name="insFdjNum" id="bx_fdjnum" class="chk_empty" maxlength="50" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">车架号：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input"><input id="ins_js_chej" type="text" name="insCjNum" id="bx_cjnum" maxlength="30" class="chk_empty" value=""  /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
        			<div class="irt_tit tb_tit">货物信息</div>
			        <div class="tb_row">
			        	<ul>
			        		<li style="margin-left:0;">
			                	<p class="lab">货物分类：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="select" style="background:none">
			                        <select id="ins_goods_type" name="insGoodsType" class="chk_empty" id="ins_goods_type" style="height:26px;width:100%">
			                        	<c:forEach var="goodsType" items="${insGoodsTypeList }">
		                        			<option value="${goodsType.name }">${goodsType.name }</option>
		                        		</c:forEach>
			                        </select>
			                        </span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">包装方式：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="select" style="background:none">
			                        	<select id="ins_bz" name="insBaoZhuang" class="chk_empty" id="ins_goods_type" style="height:26px;width:100%">
			                        		<c:forEach var="baoZhuang" items="${insBaoZhuangList }">
			                        			<option value="${baoZhuang.name }">${baoZhuang.name }</option>
			                        		</c:forEach>
			                        	</select>
									</span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			            	<li style="margin-left:0;">
			                	<p class="lab">货物名称：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_huo_wu" type="text" class="chk_empty" name="insHuoWu" id="bx_huowu" maxlength="100" value="" /></span>
			                        <span class="des"># 如：“百货、设备、果蔬、酒类等”</span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">货物数量：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_hw_num" type="text" class="chk_empty" name="insHuowuNum" id="bx_baozhuang" maxlength="100" value="" /></span>
			                        <span class="des">吨位（件数）、包装 如：10吨，纸箱</span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">保险金额（万元）：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input type="text" name="insJine" class="chk_empty chk_numb" id="bx_jine" maxlength="12" onchange="renderTotalFee()"/></span>
			                        <span class="des"># 100万填写100即可（货物实际价值，务必足额投保）</span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">特殊类型：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span id="bx_ts_type_span" class="input" style="background:none; top:22px;">
			                        	<%-- <span><input type="radio" id="bx_ts_type" name="insTsType" class="bx_tstype" value="${insTsType.insTsTypeTag }" fl="" style=" background:none; border:none; width:auto; height:auto; position:relative; left:auto; top:auto;" checked="checked"  onchange="renderTotalFee()"/>无&nbsp;&nbsp;</span>
			                        	<c:forEach var="insTsType" items="${insurance.insTsTypeList }">
			                        		<span><input type="radio" id="bx_ts_type" name="insTsType" class="bx_tstype" value="${insTsType.insTsTypeTag }" fl="" style=" background:none; border:none; width:auto; height:auto; position:relative; left:auto; top:auto;"  onchange="renderTotalFee()"/>${insTsType.insTsTypeName }&nbsp;&nbsp;</span>
			                        	</c:forEach> --%>
			                        </span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0;">
			                	<p class="lab">起运地：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_start_add" type="text" name="insStartAdd" class="chk_empty" value="" maxlength="12"/></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li>
			                	<p class="lab">到达地：</p>
			                    <p class="box">
			                    	<span class="x">※</span>
			                        <span class="input"><input id="ins_end_add" type="text" class="chk_empty" name="insEndAdd" id="bx_baozhuang" maxlength="100" value="" /></span>
			                        <span class="des"></span>
			                    </p>
			                </li>
			                <li style="margin-left:0; width:100%; height:40px;">
			                	<p class="lab">中转地：</p>
			                    <p class="box">
			                    	<span class="x"></span>
			                        <span class="input">
			                        	<input type="text" id="ins_mid_add" name="insMidAdd" id="bx_midadd" maxlength="50"/>
			                        </span>
			                    </p>
			                </li>
			                <div style="clear:both;"></div>
			            </ul>
			        </div>
			        <div class="tb_bot">
			        	<p class="box">
			            	<span class="lab" style="font-size: 16px;">费率：</span>
			                <span class="input">
			                	<input id="ins_rate" type="text" name="insuranceRate" id="bx_rates" readonly/>
			                </span>
			            </p>
			            <p class="box" style=" margin-left: 140px;">
			            	<span class="lab" style="width:140px;font-size: 16px;">保险费用（元）：</span>
			                <span class="input"><input type="text" name="insuranceFee" id="bx_money" readonly="readonly" /></span>
			            </p>
			            <p class="box" style=" margin-left: 240px;">
			            	<a href="javascript:void(0)" class="subbtn" id="subbtn" onclick="subIns()">提交投保单</a>
			            </p>
			        </div>
        	</div>
   	    </div> 
    </form>
    <input id="lowest_price" type="hidden">
    <input id="ins_id_hid" type="hidden" value="${insId }"/>
</div>
<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				 <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgs"></span></h5>
			</div>
		</div>
	</div>
</div>
<div id="errorModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: red !important;">操作失败！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: red !important;font-weight:bold">错误提示:<span id="errorModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
<div id="bigbg" style="width:100%; height:auto; position:fixed; z-index:1000; top:0; left:0; background:#474747;filter:alpha(opacity=70);-moz-opacity:0.7;-khtml-opacity: 0.7;opacity: 0.7; display:none;"></div>
	<div id="alertBox" style=" width:612px; height:auto; position:absolute; z-index:1010; top:0; left:0; display:none;">
	<p style="width:100%; height:51px; line-height:51px; text-align:center; font-weight:bold; font-size:18px; color:#FFF; background:url(/logisticsc/resources/platform/img/tb27.png) no-repeat;">投保单预览</p>
    <div style="width:100%; height:auto; background:#FFF;">
    	<p class="jg10"></p>
    	<p style="width:390px; height:54px; line-height:40px; margin:0 auto;">
        	<span style="float:left; display:block; width:95px; color:#474747;">保险品牌</span>
            <span style="float:left;"><img src="images/tb14.png" width="66" height="35" alt="" id="alert_bx_logo" /></span>
            <span style="float:left; color:#ef1b24; font-size:18px; font-weight:bold; ">&nbsp;&nbsp;——&nbsp;&nbsp;<span id="alert_bx_type"></span></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px; color:#474747;">被保险人/单位：</span>
            <span style="float:left;"><input type="text" id="alert_bx_truename" value=""  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px; color:#474747;">证件号：</span>
            <span style="float:left;"><input type="text" id="alert_bx_cardnum" value=""  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">联系电话：</span>
            <span style="float:left;"><input type="text" id="alert_bx_tel" value=""  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">货物名称：</span>
            <span style="float:left;"><input type="text" value=""  id="alert_bx_huowu" style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">船/航/车牌号：</span>
            <span style="float:left;"><input type="text" value="" id="alert_bx_chenum"  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">保险金额(万元)：</span>
            <span style="float:left;"><input type="text" value="" id="alert_bx_jine"  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">保费(元)：</span>
            <span style="float:left;"><input type="text" value="" id="alert_bx_money"  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">起运地：</span>
            <span style="float:left;"><input type="text" value="" id="alert_bx_startadd"  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<span style="float:left; display:block; width:95px;">到达地：</span>
            <span style="float:left;"><input type="text" value="" id="alert_bx_endadd"  style="width:250px; height:27px; padding:5px 10px 2px 10px; border:1px solid #cdcdcd;" disabled="disabled" readonly="readonly" /></span>
        </p>
        <p class="jg15"></p>
        <p style="border:1px dotted #d1d2da; padding:20px; width:350px; color:#fe9a9a; margin:0 auto;">温馨提示：<br />1.请认真核对您的投保单信息，以避免不必要的麻烦；<br />
				2.西藏地区，二手货物，商品车仅承保基本险（即交通事故，火灾和自然灾害）<br />
				3.其他特殊货物，如工艺品，化工等货物需提前申报，如按普通货物投保，出险后保险公司不予理赔
				</p>
        <p class="jg25"></p>
        <p style="width:390px; height:44px; line-height:30px; margin:0 auto; color:#474747;">
        	<a href="#" style="display:block; width:140px; float:left; margin-left:20px; height:36px; line-height:36px; text-align:center; color:#FFF; background:#0078ce; font-size:14px;" onclick="payIns();return false;">确认提交</a>
            <a href="#" style="display:block; float:left; margin-left:20px; width:140px; height:36px; line-height:36px; text-align:center; color:#FFF; background:#0078ce; font-size:14px;" onclick="closeSubAlert();return false;">返回修改</a>
        </p>
        <p class="jg35"></p>
    </div>
</div>
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #333333 !important;">温馨提示！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #333333!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
</div>

 <div id="yunChongzhi" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="yun_chongzhi" target="_blank" action="/logisticsc/orderCenter/doOrderMoney.shtml" method="post">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">支付</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group" style="margin-top: 28px;">
								<label class="control-label col-sm-4">订单类型：</label>
								<div class="col-sm-8">
									<label class="login-sjh" id=""></label>
									<input type="text" readonly="readonly" style="display:inline-block;width:250px" id="dingdanleixing" class="form-control required"/>
									<input type="text" id="orderType" name="orderType" style="display: none;">
								</div>
						</div>		
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">支付金额：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<input type="text" readonly="readonly" name="amount" id="zhifujine" style="display:inline-block;width:250px" class="form-control required"/>
								<input type="text" id="zhifuOrderNumber" name="orderNumber" style="display: none;">
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4"><input type="radio" style="width:10px;height:7px;" checked="checked">易宝支付：</label>
							<div class="col-sm-8"  style="padding-top: 8px;">
								<label class="login-sjh" id="yun_moneyMsg"></label>
								<span style="font-size: 14px;">(通过易宝支付完成网银支付)</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!-- <button type="button" class="btn btn-success btn-save" id="zhifu">确认支付</button> -->
						<input type="submit" class="btn-qr" value="确认支付" onclick="wancheng()"/>
					</div>
			</form>
		</div>
	</div>
</div>	
<div id="querenModal" class="modal  fade" tabindex="-1" data-backdrop="static">
     <div class="modal-dialog">
         <div class="modal-content">
             <div class="modal-header modal-info modal-success">
                 <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                 <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">登录网上银行支付</h4>
           		<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="shuaxin()">×</span></a>
             </div>
             <div class="modal-body success-info">
                 <h5 style="color: #6f9fd9!important;font-weight:bold">温馨提示:<span id="querenMsg">请您在新打开的网上银行页面进行支付，支付完成前请不要关闭该窗口</span></h5>
             </div>
            <div class="modal-footer">
             	<button type='button' class='btn btn-success' style='margin-top:5px;' onclick="shuaxin()">完成支付</button>
             </div>
         </div>
     </div>
 </div>
 
 <div class="modal fade" id="noteModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   	<div class="modal-dialog">
      	<div class="modal-content">
      		<div class="modal-header">
	            <h4 class="modal-title" id="myModalLabel">
	               	投保注意事项
	            </h4>
         	</div>
         	<div class="modal-body" id="insNoteCnt">
         	</div>
         	<div class="modal-footer">
	            <button type="button" class="btn btn-primary" data-dismiss="modal">
	              	我已阅读
	            </button>
         	</div>
      </div>
 	</div>
</div>
<jsp:include page="../bottom.jsp"></jsp:include>
<script src="${configProps['resources']}/platform/js/toubao.js"></script>
</body>
</html>