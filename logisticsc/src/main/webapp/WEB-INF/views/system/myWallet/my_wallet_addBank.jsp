<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定银行卡</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
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
#bangding {
	margin-left:50px;
}
.my-wallet_add1 {
	margin-bottom:12px;
}
.my-wallet_add1 .control-label{
	/* background: blue; */
    display: block;
    text-align: left;
    width: 86px;
}

</style>
<script type="text/javascript">

	$(document).ready(function(){
		getProvince();
		getProvinceName();
		
		BUI.use(['bui/form'],function(Form){
			form = new Form.Form({
		        srcNode : '#bangding'
		    }).render();
			//验证用户是否存在
			/* Form.Rules.add({
		      	name : 'checkP',  //规则名称
		        msg : '用户名已存在',//默认显示的错误信息
		        validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息
		        	if(value.length==11){
		          		if(checkUserExist(value)){
			            	return formatMsg;
			          	}
				})*/

		})
	});
	
	function subForm(){
		form.valid();
		if(form.isValid()){
			addBank();
		}
	}
	function addBank(){
		var date = $("#bangding").serialize();
		$.ajax({
			url:"/logisticsc/sys/account/doBank.shtml",
			data:date,
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.result==true){
					BUI.Message.Alert(data.msg,function(){
						window.location.href="/logisticsc/sys/account/toBangdingBank.shtml";
					},"success");
					
				}else{
					BUI.Message.Alert(data.msg,"error");
				}
			}
		});
	}
	function radio() {
		var radio =$('input:radio:checked').val();
		if(radio == 'PERSON'){
			$("#idcard").attr("data-rules","{required:true}");
			$("#legalperson").removeAttr("data-rules");
			$("#businesslicence").removeAttr("data-rules");
			$("#faren").hide();
			$("#yingyezhizhao").hide();
			$("#legalperson").val("");
			$("#businesslicence").val("");
			$("#shenfenzheng").show();
			  
		}else if(radio == 'ENTERPRISE'){
			$("#idcard").removeAttr("data-rules");
			$("#legalperson").attr("data-rules","{required:true}");
			$("#businesslicence").attr("data-rules","{required:true}");
			$("#idcard").val("");
			$("#faren").show();
			$("#yingyezhizhao").show();
			$("#shenfenzheng").hide();
		}
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
</script>
<body>
<div class="panel">
     <div class="panel-header">
        	<h2>银行卡绑定</h2>
     </div>
     <div class="panel-body">
     	<div id="container">
		<div class="container-self">
		<form id="bangding" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
			<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>绑定手机号码</label>
	          	<div class="controls">
	            	<input type="text" id="bindmobile" name="bindmobile" style="display:inline-block;width:250px" class="form-control"  placeholder="绑定手机号码" data-rules="{required:true}"/>
					<input type="text" id="id" style="display: none">
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>注册类型</label>
	          	<div class="controls">
	            	<input id="PERSON" type="radio" checked="" value="PERSON" name="customertype" onclick="radio()">
					<label for="PERSON">个人</label>
					<input id="ENTERPRISE" type="radio" value="ENTERPRISE" name="customertype" onclick="radio()">
					<label for="ENTERPRISE">企业</label>
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>联系人姓名</label>
	          	<div class="controls">
					<input type="text" id="linkman" name="linkman" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入联系人姓名" data-rules="{required:true}"/>
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>银行卡号</label>
	          	<div class="controls">
	          		<input type="text" id="bankaccountnumber" name="bankaccountnumber" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入银行卡号" data-rules="{required:true}"/>
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>开户行</label>
	          	<div class="controls">
	          		<select class="input-small" style="width: 100px;" id="provinceName" name="provincename" onchange="getCityName()">
				    </select>
				    <select class="input-small" style="width: 100px;" id="cityName" name="cityname" onchange="getHeadName()">
				    	<option value="0">请选择</option>
				    </select>
				    <select class="input-small" style="width: 150px;" id="headName"  name="bankheadname" onchange="getBranchName()">
				    	<option value="0">请选择</option>
				    </select>
				     <select class="input-small" style="width: 200px;" name="bankname" id="bankname">
				    	<option value="0">请选择</option>
				    </select>
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>开户名</label>
	          	<div class="controls">
	          		<input type="text" id="accountname" name="accountname" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入开户名"  data-rules="{required:true}"/>
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>银行类型</label>
	          	<div class="controls">
	          		<input id="PrivateCash" type="radio" checked="" value="PrivateCash" name="bankaccounttype">
					<label for="PrivateCash">对私</label>
					<input id="PublicCash" type="radio" value="PublicCash" name="bankaccounttype">
					<label for="PublicCash">对公</label>
	          	</div>
	       	</div>
	       	<div class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>开户省市</label>
	          	<div class="controls">
	          		<select class="input-small" name="bankprovince" id="bankprovince" onchange="getCity()">
				    </select>
				    <select class="input-small" name="bankcity" id="bankcity">
				    	<option value="0">请选择</option>
				    </select>
	          	</div>
	       	</div>
	       	<div id="shenfenzheng" class="control-group my-wallet_add1">
	          	<label class="control-label"><s>*</s>身份证号码</label>
	          	<div class="controls">
	          		<input type="text" id="idcard" name="idcard" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入身份证号码"/>
	          	</div>
	       	</div>
	       	<div id="yingyezhizhao" class="control-group my-wallet_add1" style="display: none">
	          	<label class="control-label"><s>*</s>企业营业执照</label>
	          	<div class="controls">
	          		<input type="text" id="businesslicence" name="businesslicence" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入企业营业执照号"/>
	          	</div>
	       	</div>
	       	<div  id="faren" class="control-group my-wallet_add1" style="display: none">
	          	<label class="control-label"><s>*</s>法人姓名</label>
	          	<div class="controls">
	          		<input type="text" id="legalperson" name="legalperson" style="display:inline-block;width:250px" class="form-control"  placeholder="请输入法人姓名"/>
	          	</div>
	       	</div>
       	</form>
       	<div class="row">       
          	<div class="form-actions span13 offset3">
            	<button type="submit" class="button button-primary" id="bangdingyinghangka" onclick="subForm()">确认</button>
          	</div>
	    </div>     
       </div>
      </div>
     </div>
   </div>
</body>
</html>