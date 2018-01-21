<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加银行卡</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<style type="text/css">
#add_card {
	width: 1038px;
}
#add_card_group {
	position: relative;
}
.icon-asterisk {
    font-size: 8px;
    color: #df4949;
    padding-top: 14px;
}
#add_card_group i{
	position: absolute;
	left: -20px;
}
.biaoqian {
	background:#eef4fa;
	display: block;
	height: 30px;
	line-height: 30px;
	color: rgb(51, 51, 51);
	padding-left: 20px;
	font-size: 12px;
	border-radius: 10px;
	margin-left: 15px;
}
.fl {
	float: left;
}
.uppic{
	margin-top: 20px;
    margin-left: 92px;
}
.uppic .upbtn{
	margin-right: 14px;
	cursor: pointer;
	width: 84px;
	height: 78px;
}
.uppic .upbtn img{
	width: 78px;
	height: 78px;
}
.upbtn input{
	display: none;
}
.uppic .upbtn span {
	font-size: 12px;
	text-align: center;
	display: block;
}
.dingwei {
	position: relative;
}
.dingwei .add-span1 {
    color: red;
    font-size: 10px;
    position: absolute;
    top: -20px;
    font-weight: 200;
    width: 100%;
    left: 42%;
}
.addbank-i {
	text-align:right;
}
.upbtn input[type="file"] {
    display: none;
}
</style>
<script type="text/javascript">
	function radio() {
		var radio =$('input:radio:checked').val();
		if(radio == 'PERSON'){
			$("#faren").hide();
			$("#yingyezhizhao").hide();
			$("#legalperson").val("");
			$("#businesslicence").val("");
			$("#shenfenzheng").show();
			  
		}else if(radio == 'ENTERPRISE'){
			$("#idcard").val("");
			$("#faren").show();
			$("#yingyezhizhao").show();
			$("#shenfenzheng").hide();
		}
	}
	//联系人
	function linkmanMsg() {
		var linkman = $('#linkman').val();
		if(linkman==null || linkman==''){
		$("#linkmanMsg").text("请输入联系人姓名！");
			return false;
		}else if(linkman.length < 2 || linkman.length > 10){
			$("#linkmanMsg").text("联系人姓名字符长度为2-10位！");
			return false;
		}
		return true;
	}
	//银行卡
	function bankaccountnumberMsg() {
		var bankaccountnumber = $('#bankaccountnumber').val();
		if(bankaccountnumber==null || bankaccountnumber==''){
			$("#bankaccountnumberMsg").text("请输入银行卡号！");
			return false;
		}else if(!/^(\d{16}|\d{19})$/.test(bankaccountnumber)){
			$("#bankaccountnumberMsg").text("请输入正确银行卡号！");
			return false;
		}
		return true;
	}
	//开户行省
	function provinceName() {
		var provinceName =$("#provinceName").val();
		if(provinceName == 0){
			$("#kaihuhangMsg").text("请选择开户行省！");
			return false;
		}
		return true;
	}
	//开户行市
	function cityName() {
		var cityName =$("#cityName").val();
		if(cityName == 0){
			$("#kaihuhangMsg").text("请选择开户行市！");
			return false;
		}
		return true;
	}
	//开户行
	function headName() {
		var headName =$("#headName").val();
		if(headName == 0){
			$("#kaihuhangMsg").text("请选择开户行！");
			return false;
		}
		return true;
	}
	//开户支行
	function bankname() {
		var bankname =$("#bankname").val();
		if(bankname == 0){
			$("#kaihuhangMsg").text("请选择开户支行！");
			return false;
		}
		return true;
	}
	//开户名
	function accountnameMsg() {
		var accountname = $('#accountname').val();
		if(accountname==null || accountname==''){
		$("#accountnameMsg").text("请输入开户名！");
			return false;
		}else if(accountname.length < 2 || accountname.length > 10){
			$("#accountnameMsg").text("开户名字符长度为2-10位！");
			return false;
		}
		return true;
	}
	//开户省
	function bankprovince() {
		var bankprovince =$("#bankprovince").val();
		if(bankprovince == 0){
			$("#kaihuMsg").text("请选择开户省！");
			return false;
		}
		return true;
	}
	//开户市
	function bankcity() {
		var bankcity =$("#bankcity").val();
		if(bankcity == 0){
			$("#kaihuMsg").text("请选择开户市！");
			return false;
		}
		return true;
	}
	//身份证号码
	function idcardMsg() {
		var idcard = $('#idcard').val();
		if(idcard==null || idcard==''){
		$("#idcardMsg").text("请输入身份证号码！");
			return false;
		}else if(!/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/.test(idcard)){
			$("#idcardMsg").text("请输入正确身份证号码！");
			return false;
		}
		return true;
	}
	//企业营业执照号
	function businesslicenceMsg() {
		var businesslicence = $("#businesslicence").val();
		if(businesslicence == null || businesslicence ==""){
			$("#businesslicenceMsg").text("请输入企业营业执照号！");
			return false;
		}else if(!/^\d{15}$/.test(businesslicence)){
			$("#businesslicenceMsg").text("请输入正确的企业营业执照号！");
			return false;
		}
		return true;
	}
	//法人姓名
	function legalpersonMsg() {
		var legalperson = $('#legalperson').val();
		if(legalperson==null || legalperson==''){
		$("#legalpersonMsg").text("请输入法人姓名！");
			return false;
		}else if(legalperson.length < 2 || legalperson.length > 10){
			$("#legalpersonMsg").text("法人名字符长度为2-10位！");
			return false;
		}
		return true;
	}
	$(document).ready(function(){
		getProvince();
		getProvinceName();
		$("#bangdingyinghangka").click(function() {
			var radio =$('input:radio:checked').val();
			if(radio == 'PERSON'){
				return linkmanMsg() && bankaccountnumberMsg() && provinceName() && cityName() && headName() && bankname() && accountnameMsg() && bankprovince() && bankcity() && idcardMsg() && addBank();
			}else if(radio == 'ENTERPRISE'){
				return linkmanMsg() && bankaccountnumberMsg() && provinceName() && cityName() && headName() && bankname() && accountnameMsg() && bankprovince() && bankcity() && businesslicenceMsg() && legalpersonMsg() && addBank();
			}
		});
		$("#linkman").focus(function() {
			$("#linkmanMsg").text("");
		});
		$("#bankaccountnumber").focus(function() {
			$("#bankaccountnumberMsg").text("");
		});
		$("#provinceName").focus(function() {
			$("#kaihuhangMsg").text("");
		});
		$("#cityName").focus(function() {
			$("#kaihuhangMsg").text("");
		});
		$("#headName").focus(function() {
			$("#kaihuhangMsg").text("");
		});
		$("#bankname").focus(function() {
			$("#kaihuhangMsg").text("");
		});
		$("#accountname").focus(function() {
			$("#accountnameMsg").text("");
		});
		$("#bankprovince").focus(function() {
			$("#kaihuMsg").text("");
		});
		$("#bankcity").focus(function() {
			$("#kaihuMsg").text("");
		});
		$("#idcard").focus(function() {
			$("#idcardMsg").text("");
		});
		$("#businesslicence").focus(function() {
			$("#businesslicenceMsg").text("");
		});
		$("#legalperson").focus(function() {
			$("#legalpersonMsg").text("");
		});
		
	});
	function addBank(){
		var option = {
	        url : '/logisticsc/personalCenter/doBank.shtml',
	        type : 'post',
	        dataType : 'json',
	        headers : {"ClientCallMode" : "ajax"},
	        success : function(data) {
	        	if(data.result==true){
	        		$("#successModal").modal('show');
					$("#successModalMsgs").html(data.msg);
				}else{
					$("#errorModal").modal('show');
					$("#errorModalMsgs").html(data.msg);
				}
	        },
	        error: function(data) {
	        	$("#errorModal").modal('show');
	        	$("#errorModalMsgs").html("系统错误");	
	        }
	     };
	    $("#bangding").ajaxSubmit(option);
	    return false;
	}
	function getProvince(){
		var getXzqhInfo="";
	 	 $.ajax({
	      url :"/logisticsc/personalCenter/getBankProvince.shtml",
	      type : 'POST',
	      dataType : 'json',
	      success:function(data){
	     	 if(data.result==true){
	     		 var data = data.data;
	     		$("#bankprovince").append("<option value='0'>请选择</option>");
	     		 for(var i=0; i<data.length; i++) {	
	     			getXzqhInfo += "<option value=\""+data[i].province+"\">"+data[i].province+"</option>"; 
	  			}
	  		$("#bankprovince").append(getXzqhInfo);
	        }
			}
	 	 })
	}
	function getCity(){
		var city=$("#bankprovince").val();
		var data={province:city};
		$("#bankcity").empty();
		$.ajax({
			url:"/logisticsc/personalCenter/getBankCity.shtml",
			data:data,
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result==true){
					$("#bankcity").append("<option value='0'>请选择</option>"); 
					$.each(data.data,function(i){
						$("#bankcity").append("<option value='"+data.data[i].city+"'>"+data.data[i].city+"</option>");
					})		
				}
			}
		})
	}
	function getProvinceName(){
		var getXzqhInfo="";
	 	 $.ajax({
	      url :"/logisticsc/personalCenter/getProvinceName.shtml",
	      type : 'POST',
	      dataType : 'json',
	      success:function(data){
	     	 if(data.result==true){
	     		 var data = data.data;
	     		$("#provinceName").append("<option value='0'>请选择</option>");
	     		 for(var i=0; i<data.length; i++) {	
	     			getXzqhInfo += "<option value=\""+data[i].provinceName+"\">"+data[i].provinceName+"</option>"; 
	  			}
	  		$("#provinceName").append(getXzqhInfo);
	        }
			}
	 	 })
	}
	function getCityName(){
		var province=$("#provinceName").val();
		var data={provinceName:province};
		$("#cityName").empty();
		$("#headName").empty();
		var getXzqhInfo="";
	 	 $.ajax({
	      url :"/logisticsc/personalCenter/getCityName.shtml",
	      data:data,
	      type : 'POST',
	      dataType : 'json',
	      success:function(data){
	     	 if(data.result==true){
	     		$("#cityName").append("<option value='0'>请选择</option>"); 
				$("#headName").append("<option value='0'>请选择</option>"); 
				$.each(data.data,function(i){
					$("#cityName").append("<option value='"+data.data[i].cityName+"'>"+data.data[i].cityName+"</option>");
				})
	  		$("#cityName").append(getXzqhInfo);
	        }
			}
	 	 })
	}
	function getHeadName(){
		var province=$("#provinceName").val();
		var cityName=$("#cityName").val();
		var getXzqhInfo="";
		var data={provinceName:province,cityName:cityName};
		$("#headName").empty();
		$("#bankname").empty();
		$.ajax({
			url:"/logisticsc/personalCenter/getHeadName.shtml",
			data:data,
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result==true){
					$("#headName").append("<option value='0'>请选择</option>"); 
					$("#bankname").append("<option value='0'>请选择</option>");
					$.each(data.data,function(i){
						$("#headName").append("<option value='"+data.data[i].headName+"'>"+data.data[i].headName+"</option>");
					})	
					$("#headName").append(getXzqhInfo);
				}
			}
		})
	}
	function getBranchName(){
		var province=$("#provinceName").val();
		var cityName=$("#cityName").val();
		var headName=$("#headName").val();
		var data={headName:headName,provinceName:province,cityName:cityName};
		$("#bankname").empty();
		$.ajax({
			url:"/logisticsc/personalCenter/getBranchName.shtml",
			data:data,
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.result==true){
					$("#bankname").append("<option value='0'>请选择</option>"); 
					$.each(data.data,function(i){
						$("#bankname").append("<option value='"+data.data[i].branchName+"'>"+data.data[i].branchName+"</option>");
					})	
				}
			}
		})
	}
	function tiaozhuan() {
		window.location="/logisticsc/personalCenter/toorderMyWallet.shtml";
	}
</script>
<body>
<div id="container">
	<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="peronal_center_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle pull-left">
			<form id="bangding" class="form-horizontal form-bordered" id="add_card" enctype="multipart/form-data" method="post">
				<div class="form-group  dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1"></span>
					<i class="icon-asterisk col-sm-1 addbank-i"></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;" >绑定手机号码</label>
					<div class="col-sm-4">
						<input type="text" id="bindmobile" name="bindmobile" readonly="readonly" value="${user_session.loginName}"  style="display:inline-block;width:250px" class="form-control"  placeholder="绑定手机号码"/>
						<input type="text" id="id" style="display: none">
					</div>
					<div class="col-sm-3"></div>
				</div>
				
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">注册类型</label>
					<div class="col-sm-4">
						<input id="PERSON" type="radio" checked="" value="PERSON" name="customertype" onclick="radio()">
						<label for="PERSON">个人</label>
						<input id="ENTERPRISE" type="radio" value="ENTERPRISE" name="customertype" onclick="radio()">
						<label for="ENTERPRISE">企业</label>
					</div>
					<div class="col-sm-3"></div>
				</div>
				
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="linkmanMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">联系人姓名</label>
					<div class="col-sm-4">
						<input type="text" id="linkman" name="linkman" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入联系人姓名" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="bankaccountnumberMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">银行卡号</label>
					<div class="col-sm-4">
						<input type="text" id="bankaccountnumber" name="bankaccountnumber" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入银行卡号" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="kaihuhangMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">开户行</label>
					<div class="col-sm-4">
						<select class="form-control" style="width: 125px;float: left;" id="provinceName" name="provincename" onchange="getCityName()">
					    </select>
					    <select class="form-control" style="width: 125px;float: left;" id="cityName" name="cityname" onchange="getHeadName()">
					    	<option value="0">请选择</option>
					    </select>
					    <select class="form-control" style="width: 250px; float: left;margin-top:10px;" id="headName"  name="bankheadname" onchange="getBranchName()">
					    	<option value="0">请选择</option>
					    </select>
					     <select class="form-control" style="width: 250px; float: left;margin-top:10px;" name="bankname" id="bankname">
					    	<option value="0">请选择</option>
					    </select>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="accountnameMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">开户名</label>
					<div class="col-sm-4">
						<input type="text" id="accountname" name="accountname" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入开户名"  />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">银行类别</label>
					<div class="col-sm-4">
						<input id="PrivateCash" type="radio" checked="" value="PrivateCash" name="bankaccounttype">
						<label for="PrivateCash">对私</label>
						<input id="PublicCash" type="radio" value="PublicCash" name="bankaccounttype">
						<label for="PublicCash">对公</label>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="kaihuMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">开户省市</label>
					<div class="col-sm-4">
						<select class="form-control" style="width: 125px;float:left;" name="bankprovince" id="bankprovince" onchange="getCity()">
					    </select>
					    <select class="form-control" style="width: 125px; float:left;" name="bankcity" id="bankcity">
					    	<option value="0">请选择</option>
					    </select>
					    
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div id="shenfenzheng" class="form-group dingwei"  style="margin-top: 28px;">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="idcardMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">身份证号码</label>
					<div class="col-sm-4">
						<input type="text" id="idcard" name="idcard" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入身份证号码" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<div id="yingyezhizhao" class="form-group dingwei"  style="margin-top: 28px;display: none">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="businesslicenceMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">企业营业执照号</label>
					<div class="col-sm-4">
						<input type="text" id="businesslicence" name="businesslicence" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入企业营业执照号" />
					</div>
					<div class="col-sm-3"></div>
				</div> 
				<div id="faren" class="form-group dingwei"  style="margin-top: 28px;display: none">
					<div class="col-sm-3"></div>
					<span class="add-span1" id="legalpersonMsg"></span>
					<i class="icon-asterisk col-sm-1 addbank-i "></i>
					<label class="control-label pull-left" style="width: 72px;text-align: left;">法人姓名</label>
					<div class="col-sm-4">
						<input type="text" id="legalperson" name="legalperson" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入法人姓名" />
					</div>
					<div class="col-sm-3"></div>
				</div> 
			</form>
	    </div>
	    <div class="btn btn-success pull-right" id="bangdingyinghangka">确认</div>
   </div>
   </div>  
   <div style="clear: both;"></div>
   <div class="footer">	
	<jsp:include page="../bottom.jsp"></jsp:include>
</div>
<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;"><span onclick="tiaozhuan()">×</span></a>
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
</body>
</html>