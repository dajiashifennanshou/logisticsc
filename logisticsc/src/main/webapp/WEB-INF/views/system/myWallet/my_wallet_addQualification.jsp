<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资质上传</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
<script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
<script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
<script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
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
    width:400px;
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
$(document).ready(function(){
	selectBank();
	$("#shengfenzheng_zhengmian").click(function() {
		var name ="shengfenzheng_zhengmianMsg";
		return addzhizhi(name);
	});
	$("#shengfenzheng_fanmian").click(function() {
		var name ="shengfenzheng_fanmianMsg";
		return addzhizhi(name);
	});
	$("#shouchishengfenzheng_zhengmian").click(function() {
		var name ="shouchishengfenzheng_zhengmianMsg";
		return addzhizhi(name);
	});
	$("#yinhangka_zhengmian").click(function() {
		var name ="yinhangka_zhengmianMsg";
		return addzhizhi(name);
	});
	$("#yingyezhizhao").click(function() {
		var name ="yingyezhizhaoMsg";
		return addzhizhi(name);
	});
	$("#faren_zhengmian").click(function() {
		var name ="faren_zhengmianMsg";
		return addzhizhi(name);
	});
	$("#fanren_fanmian").click(function() {
		var name ="fanren_fanmianMsg";
		return addzhizhi(name);
	});
	$("#yinhangkahuxukezheng").click(function() {
		var name ="yinhangkahuxukezhengMsg";
		return addzhizhi(name);
	});
	$("#zhuzhijigoudaimazheng").click(function() {
		var name ="zhuzhijigoudaimazhengMsg";
		return addzhizhi(name);
	});
	$("#suiwudengjihao").click(function() {
		var name ="suiwudengjihaoMsg";
		return addzhizhi(name);
	});
});
function addzhizhi(name){
	var option = {
        url : '/logisticsc/sys/account/dozhizhi.shtml',
        type : 'post',
        dataType : 'json',
        headers : {"ClientCallMode" : "ajax"},
        success : function(data) {
        	if(data.result==true){
        		BUI.Message.Alert(data.msg,function(){
					window.location.href="/logisticsc/sys/account/toAddCredit.shtml";
				},"success");
			}else{
				BUI.Message.Alert(data.msg,"error");
			}
        },
        error: function(data) {
        	BUI.Message.Alert("系统异常","error");	
        }
     };
	  $("#"+name).ajaxSubmit(option);
    return false;
}
//查询账户信息
function selectBank() {
	$.ajax({
		url:"/logisticsc/sys/account/getBankUserId.shtml",
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				 radio =data.data.customertype;
				 date = data.data;
				 comanyId = date.id;
				if(radio == 'PERSON'){
					$("#qiyezizhi").hide();
					$("#grzizhi").show();
					if(null == date.idCardFront || '' == date.idCardFront || 'undefined' == date.idCardFront){
					}else{
						var pic0 = document.getElementById("preview0");
						pic0.src = "/logisticsc/img/retrive.shtml?resource="+date.idCardFront;
					}
					if(null == date.idCcardBack || '' == date.idCcardBack || 'undefined' == date.idCcardBack){
					}else{
						var pic1 = document.getElementById("preview1");
						pic1.src = "/logisticsc/img/retrive.shtml?resource="+date.idCcardBack;
					}
					if(null == date.persouPhoto || '' == date.persouPhoto || 'undefined' == date.persouPhoto){
					}else{
						var pic2 = document.getElementById("preview2");
						pic2.src = "/logisticsc/img/retrive.shtml?resource="+date.persouPhoto;
					}
					if(null == date.bankCardFront || '' == date.bankCardFront || 'undefined' == date.bankCardFront){
					}else{
						var pic3 = document.getElementById("preview3");
						pic3.src = "/logisticsc/img/retrive.shtml?resource="+date.bankCardFront;
					}
				}else if(radio == 'ENTERPRISE'){
					$("#grzizhi").hide();
					$("#qiyezizhi").show();
					if(null == date.businesslicence || '' == date.businesslicence || 'undefined' == date.businesslicence){
					}else{
						var pic4 = document.getElementById("preview4");
						pic4.src = "/logisticsc/img/retrive.shtml?resource="+date.businesslicence;
					}
					if(null == date.idCardFront || '' == date.idCardFront || 'undefined' == date.idCardFront){
					}else{
						var pic5 = document.getElementById("preview5");
						pic5.src = "/logisticsc/img/retrive.shtml?resource="+date.idCardFront;
					}
					if(null == date.idCcardBack || '' == date.idCcardBack || 'undefined' == date.idCcardBack){
					}else{
						var pic6 = document.getElementById("preview6");
						pic6.src = "/logisticsc/img/retrive.shtml?resource="+date.idCcardBack;
					}
					if(null == date.bankAccountLicence || '' == date.bankAccountLicence || 'undefined' == date.bankAccountLicence){
					}else{
						var pic7 = document.getElementById("preview7");
						pic7.src = "/logisticsc/img/retrive.shtml?resource="+date.bankAccountLicence;
					}
					if(null == date.organizationCode || '' == date.organizationCode || 'undefined' == date.organizationCode){
					}else{
						var pic8 = document.getElementById("preview8");
						pic8.src = "/logisticsc/img/retrive.shtml?resource="+date.organizationCode;
					}
					if(null == date.taxRegistron || '' == date.taxRegistron || 'undefined' == date.taxRegistron){
					}else{
						var pic9 = document.getElementById("preview9");
						pic9.src = "/logisticsc/img/retrive.shtml?resource="+date.taxRegistron;
					}
				}
			}
		},
		error:function(data){
			BUI.Message.Alert("系统异常","error");
		}
	})
}
function updateimg(obj,index){
	var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
	if(ie){ 
	document.getElementById('fileName'+index).click(); 
	}else{
	var a=document.createEvent("MouseEvents");//FF的处理 
	a.initEvent("click", true, true);  
	document.getElementById('fileName'+index).dispatchEvent(a);
	}
}
function fileNmae(index){
var docObj = document.getElementById("fileName"+index); 
var imgObjPreview = document.getElementById("preview"+index); 
if (docObj.files && docObj.files[0]) { 
	/*火狐下，直接设img属性*/
	imgObjPreview.style.display = 'block'; 
	imgObjPreview.style.width = '78px'; 
	imgObjPreview.style.height = '78px'; 
	/*imgObjPreview.src = docObj.files[0].getAsDataURL();*/ 
	/*火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式*/ 
	imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]); 
} else { 
	/*IE下，使用滤镜*/ 
	docObj.select(); 
	var imgSrc = document.selection.createRange().text; 
	var localImagId = document.getElementById("localImag"); 
	/*必须设置初始大小*/ 
	localImagId.style.width = "78px"; 
	localImagId.style.height = "78px"; 
	/*图片异常的捕捉，防止用户修改后缀来伪造图片*/ 
try { 
	localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)"; localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc; 
} catch (e) { 
	alert("您上传的图片格式不正确，请重新选择!"); 
return false; 
} 
	imgObjPreview.style.display = 'none'; 
	document.selection.empty(); 
} 
	return true; 
}
</script>
<body>
<div class="panel">
 	<div class="panel-header">
     	<h2>上传资质</h2>
  	</div>
  	<div class="panel-body">
  		<div id="container">
			<div class="container-self">
	  				<div class="grzizhi" id="grzizhi" style="display: none;">
					<div class="form-group"  style="margin-top: 28px;clear: both;display: block;width: 100%;">
						<div class="col-sm-3"></div>
						<i class="icon-asterisk col-sm-1 addbank-i fl" style=" margin-top: 8px;width: 32px;"></i>
						<label class="control-label pull-left" style="width: 160px;text-align: left;">个人资质上传</label>
					   	<div class="biaoqian col-sm-2" style="color: red; display: block;float: left; margin-left: -38px;width: 202px;" id="tupianMsg">
							资质图片不能大于512KB！
						</div>
						<div class="col-sm-3"></div>
					</div>
					<div class="form-group"  style="margin-top: 14px; clear: both; float: left;">
						<div class="col-sm-3"></div>
						<div class="uppic f-cb fl col-sm-6" style="margin-top:2px;margin-left: 48px;">
							<form id="shengfenzheng_zhengmianMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="upbtn fl" title="上传图片">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'0')" id="preview0"  />
									<span>身份证正面</span>
									<input type="file" name="fileName0" id="fileName0" onchange="javascript:fileNmae(0);" />
									<button class="button button-success btn-update" id="shengfenzheng_zhengmian">上传</button>
								</div>
							</form>
							<form id="shengfenzheng_fanmianMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="upbtn fl" title="上传图片">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'1')" id="preview1"/>
									<span>身份证反面</span>
									<input type="file" name="fileName1" id="fileName1" onchange="javascript:fileNmae(1);" />
									<button class="button button-success btn-update" id="shengfenzheng_fanmian">上传</button>
								</div>
							</form>	
							<form id="shouchishengfenzheng_zhengmianMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
							<div class="upbtn fl" title="上传图片">
								<img src="/logisticsc/resources/platform/img/shangchuan.png"  onclick="updateimg(this,'2')" id="preview2"/>
								<span>手持身份证正面</span>
								<input type="file" name="fileName2" id="fileName2" onchange="javascript:fileNmae(2);" />
								<button class="button button-success btn-update" id="shouchishengfenzheng_zhengmian">上传</button>
							</div>
							</form>
							<form id="yinhangka_zhengmianMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
							<div class="upbtn fl" title="上传图片">
								<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'3')" id="preview3"/>
								<span>银行卡正面</span>
								<input type="file" name="fileName3" id="fileName3" onchange="javascript:fileNmae(3);"/>
								<button class="button button-success btn-update" id="yinhangka_zhengmian">上传</button>
							</div>
							</form>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div  id="qiyezizhi" class="qiyezizhi" style="display: none;float: left;text-align: center; margin-left:210px; ">
					<div class="form-group"  style="margin-top: 32px;">
						<div class="col-sm-3"></div>
						<i class="icon-asterisk col-sm-1 addbank-i pull-left"></i>
						<label class="control-label pull-left" style="width: 72px;text-align: left;">企业资质上传</label>
					   <div class="biaoqian col-sm-2" id="qiyuetupianMsg" style="color: red;width: 222px;margin-top:-5px;float: left;">
							资质图片不能大于512KB！
						</div>
						<div class="col-sm-4"></div>
					</div>
					<div style="clear:both;"></div>
					<div class="form-group"  style="margin-top: 14px;">
						<div class="col-sm-3"></div>
						<div class="uppic f-cb fl col-sm-4" style="margin-top:-30px;margin-left: 2px; width:600px;">
							<form id="yingyezhizhaoMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="upbtn fl" title="上传图片" style="margin-top: 30px;">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'4')" id="preview4"/>
									<span>营业执照</span>
									<input type="file" name="fileName4" id="fileName4" onchange="javascript:fileNmae(4);" />
									<button class="button button-success button-update" id="yingyezhizhao">上传</button>
								</div>
							</form>
							<form id="faren_zhengmianMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="upbtn fl" title="上传图片" style="margin-top: 30px;">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'5')" id="preview5" />
									<span>法人身份证正面</span>
									<input type="file" name="fileName5" id="fileName5" onchange="javascript:fileNmae(5);" />
									<button class="button button-success button-update" id="faren_zhengmian">上传</button>
								</div>
							</form>
							<form id="fanren_fanmianMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="upbtn fl" title="上传图片" style="margin-top: 30px;">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'6')" id="preview6" />
									<span>法人身份证反面</span>
									<input type="file" name="fileName6" id="fileName6" onchange="javascript:fileNmae(6);" />
									<button class="button button-success button-update" id="fanren_fanmian">上传</button>
								</div>
							</form>	
							<form id="yinhangkahuxukezhengMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="upbtn fl" title="上传图片" style="margin-top: 30px;">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'7')" id="preview7" />
									<span>银行开户许可证</span>
									<input type="file" name="fileName7" id="fileName7" onchange="javascript:fileNmae(7);" />
									<button class="button button-success button-update" id="yinhangkahuxukezheng">上传</button>
								</div>
							</form>
							<form id="zhuzhijigoudaimazhengMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">	
								<div class="upbtn fl" title="上传图片" style="margin-top: 30px;">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'8')" id="preview8" />
									<span>组织机构代码证</span>
									<input type="file" name="fileName8" id="fileName8" onchange="javascript:fileNmae(8);" />
									<button class="button button-success button-update" id="zhuzhijigoudaimazheng">上传</button>
								</div>
							</form>
							<form id="suiwudengjihaoMsg" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">	
								<div class="upbtn fl" title="上传图片" style="margin-top: 30px;">
									<img src="/logisticsc/resources/platform/img/shangchuan.png" onclick="updateimg(this,'9')" id="preview9" />
									<span>税务登记证</span>
									<input type="file" name="fileName9" id="fileName9" onchange="javascript:fileNmae(9);" />
									<button class="button button-success button-update" id="suiwudengjihao">上传</button>
								</div>
							</form>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
	  			<div style="clear: both;"></div>
	  				 <div class="row" style="margin-top: 52px;">       
			        <!--   <div class="form-actions span13 offset10">
			            <button type="button" class="button button-primary" id="shangchuanzizhi">确认</button>
			          </div> -->
			    </div>   
	  		</div>
  		</div>
  	</div>
</div>
</body>
</html>