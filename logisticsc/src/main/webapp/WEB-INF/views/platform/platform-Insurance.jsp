<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保险协议</title>
<script src="/logisticsc/resources/platform/jquery/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/logisticsc/resources/platform/css/index2.css" />
<link rel="stylesheet" href="/logisticsc/resources/platform/css/public.css" />
<!-- bootstrap-->
<script src="/logisticsc/resources/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

<style type="text/css">
body{
	background-color: #fafafa;
    font-size: 12px;
    height: 100%;
    margin: 0;
}
.contensa{
	width: 58%;
	/*height: 100%;*/
	margin: 0 auto;
	background: white none repeat scroll 0 0;
	margin-top: 50px;
	text-align: left;
	padding: 60px;
	line-height: 38px;
	box-shadow: 0 0 3px rgba(0, 0, 0, 0.1);
	position:relative;
}
h3{
	text-align:center;
}	
.bottom2{
	margin-top: 20px;
	background: #f8f8f8 none repeat scroll 0 0;
    color: #333333;
    height: 74px;
    line-height: 30px;
    padding-top: 8px;
    text-align: center;
    width: 100%;
}	

.top-content {
    height: 76px;
    margin: 0 auto;
    width: 1200px;
}

.top {
    height: 76px;
    width: 100%;
}
.logo {
    color: cornflowerblue;
    font-size: 38px;
    font-weight: bold;
    line-height: 76px;
    margin-top: 18px;
}
.dh {
    color: white;
    line-height: 76px;
	margin-left:22px;
}
.f {
    float: left;
}
</style>
</head>
<script type="text/javascript">
//页面加载
$(function(){
	$("#goumaibaoxian").click(function(){
		if($("#userNames").val() == null || $("#userNames").val() == ""){
			qingkomg();
			$("#login").modal({show : true});
			}else{
				window.location="<%=request.getContextPath()%>/insurance/toInsurance.shtml";	
			}
	});
});
function qingkomg() {
	$("#username").val("");
	$("#password").val("");
	$("#checkCode").val("");
}
//登陆
function login() {
	var username=$("#username").val();
    var password=$("#password").val();
    var reCode=$("#checkCode").val();
	if(null == username ||"" == username){
		$("#promptInfo").text("用户名不能为空！");
		myReload();
		return;
	}else if(null == password ||"" == password){
		$("#promptInfo").text("密码不能为空！");
		myReload();
		return;
	}else if(null == reCode ||"" == reCode){
		$("#promptInfo").text("验证码不能为空！");
		myReload();
		return;
	}
    $.ajax({
        url : "/logisticsc/user/loginUser.shtml",
        type : 'POST',
        data : {"loginName":username,"password":password,"reCode":reCode},
        dataType : 'json',
        success:function(data){
            if (data.result) {
            	$("#login").modal("hide");
            	window.location="<%=request.getContextPath()%>/insurance/toInsurance.shtml";
            } else {
               $("#promptInfo").text(data.msg);
           		myReload();
            }
        }
    });
}
//请求验证码
function myReload(){  
    document.getElementById("createCheckCode").src=document.getElementById("createCheckCode").src + "?nocache="+new Date().getTime();  
}
</script>
<body>
<div class="contents">
	<div class="index2_top">
		<div class="index2_con">
			<!--  
			<div class="index2_phone">
				<img src="/logisticsc/resources/platform/img/index2_top_phone.png" />
				<p>18000510004</p>
			</div>
			<c:if test="${user_session.loginName == null }">
				<a href="/logisticsc/user/jumpreGister.shtml" class="f">免费注册</a>
			</c:if>
			-->
			<c:if test="${user_session.loginName != null }">
				<a href="/logisticsc/user/outUser.shtml?" class="index_tuichu">退出</a>
			</c:if>
			<!--  
			<c:if test="${user_session.loginName == null }">
				<a href="/logisticsc/platform-login.jsp" class="f">请登陆</a>
			</c:if>
			-->
			<c:if test="${user_session.loginName != null }">
				<a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" class="f">${user_session.loginName}</a>
			</c:if>
			<input type="hidden" id="userNames" value="${user_session.loginName}"/>
		</div>
	</div>
	<div class="contensa">
		<h3>保险公司投保说明</h3>
		投保说明：<br>
		禁止投保的货物及特别约定：<br>
		（1）武器弹药、现金、支票、票据、单证、有价证券、信用证、护照、卫星运送过程中遭受的风险；<br>
		（2）货物运输过程中遭受的核子能、原子能风险，放射性沾染风险，政治、财政、信用风险；<br>
		（3）艺术品、金银、珠宝、钻石、玉器、文物古玩等贵重物品运送过程中遭受的风险；<br>
		（4）军用品<br />
		（5）鱼粉、菜籽饼、地瓜干、花生、动植物和血制品<br />
		（6）爆竹、烟花等易燃易爆品 <br />
		（7）在国内无维修能力的精密仪器进口（特别是芯片制造设备、液晶显示器制造设备）  一、特别约定：1、本保险不负责运输大件设备（如变压器等大件机械设备）的任何损失；2、本保险不负责货物超载运输的任何损失；3、本保险不负责易碎、易燃、易爆货物的任何损失。且本保险不负责鲜活动植物，瓜果蔬菜、核桃、红枣、瓜子等干果的任何损失。
		<br />
		免赔说明：<br />
			1.水路、陆路货物运输保险条款普通货物每次事故绝对免赔为RMB2000元，或损失金额的10%，高者为准；
			2.火灾事故每次事故绝对免赔率为损失金额的20%；
		<br />
			众安保险投保说明：
		<br />
			普通货物不包含以下物品禁止承保的货物及特别约定：
		<br />
			1.金银、金银制品、纪念币、珠宝、钻石、玉器、贵重金属、首饰、古币、古玩、古书、古画、邮票、艺术品、稀有金属、文物、古董、现金、有价证券、票据、文件、档案、账册、图纸、技术资料、玉雕工艺品、玉石制品、名画、古代化石、瓷器、旧地毯、各类工艺品等其他价值无法确定或者保险期间内价值变化较大的货物；
		<br />
		2.鱼粉、豆粕、易腐易蛀品、各类散装农产品（果仁、花生、大豆等谷物、粮食作物、菜籽饼、地瓜干、木薯干等）；
		<br />
			3.易变质品（包括海货、鲜活货等）、易腐坏及受温度影响的物品，如冷冻品、肉类、蛋类、水果类、蔬菜类等；
		<br />
		4.动植物及畜产品、血制品、疫苗；<br />
			5.爆炸品、放射性同位素、易燃、易爆的危险品（一般化工类产品不列入危险品，如桶装的油漆、燃料、农药、炭黑、清洁剂、机油、香精、香料、电池、电瓶、油墨、胶水、树脂等）；军用武器或装备、酒精、汽油、煤油、柴油、火柴；
		<br />
			6.汽车、摩托车，船舶；
		<br />
			7.原木、原棉、天然橡胶、木材（陆运木材除外）等；
		<br />
		8.单价超过RMB1万元的手表；<br />
		9.超宽或超高货物；<br />
		10.医疗设备（单价超过50万）；<br />
		11.有防震防倾斜要求的精密设备（如液晶、半导体、光伏生产设备等）。精密仪器或设备（任何仪器或设备如果满足以下条件之一，应视为精密仪器或设备：<br />
		a）运输过程中对该仪器或设备的平衡、稳定、防震、防尘、搬运倾斜角度等有特殊严格要求；<br />
		b)仪器或设备受损后无法找到配件，必须运回国外原厂修理或无法修复；<br />
		c)单件仪器或设备货值超过人民币200万元。<br />
		12.重大件机械设备（任何机机械设备如果满足以下条件之一，应视为重大件机械设备：<br />
		a）任一部件由于体积或重量的原因必须由特殊运输工具运输；<br />
		b)任一部件由于本身的特性必须特殊处理，如要求特殊的绑扎固定及保持重心的平衡）。<br />
		13.散装化工类货物，如煤炭；矿石/砂；石油及柴油、沥青、焦油等各类石油提炼品、饲料、化肥、水泥等；<br />
		14.太阳能组件、半导体、晶硅片（单晶硅、多晶硅）；<br />
		易碎品包含以下物品：各种玻璃、陶瓷、搪瓷制品，大理石等以及主要由上述材质制成的货物，包括液晶屏、瓶装货物。<br />
		15、药品类（疫苗除外）：货物发生损失时，本保单只负责赔偿受损货物及包装的直接损失（如外包装破损，但内包装完好，本保单仅就受损的外包装承担赔偿责任；如部分货物受损，本保单只负责受损货物的直接损失），不承担由此产生的整箱货物被拒收或无法销售带来的损失以及其它间接损失。<br />
		废旧品、旧设备、二手品、陈列品仅承保火灾爆炸、交通工具发生意外事故造成的损失。<br />
		裸装品不负责锈损和刮擦导致的损失。<br />
		在任何情况下，保险人不对承运的“不承保货物”的损失进行认定和赔付，也不对“不承
		保货物”造成的其他货物的损失进行认定和赔付<br />
		免赔说明：<br />
		1.普通货物：每次事故1000元或损失金额的10%，两者以高者为准;易碎品：每次事故1000元或保险金额的5%，两者以高者为准;冷藏货物：每次事故的绝对免赔为保险金额的10%。<br />
		2.本保单不承保整车货物被盗、抢掠或失踪之风险。<br />
		3.放弃对投保人的代位追偿权,但保留对其他实际承运人的代位追偿权。<br />					
		太平洋普通货物投保说明：<br />
		禁止承保的货物及特别约定：<br />
		（1）武器弹药、现金、支票、票据、单证、有价证券、信用证、护照、卫星运送过程中遭受的风险；<br />
		（2）货物运输过程中遭受的核子能、原子能风险，放射性沾染风险，政治、财政、信用风险；<br />
		（3）艺术品、金银、珠宝、钻石、玉器、文物古玩等贵重物品运送过程中遭受的风险；<br />
		（4）军用品<br />
		（5）鱼粉、菜籽饼、地瓜干、花生、动植物和血制品<br />
		（6）爆竹、烟花等易燃易爆品 <br />
		（7）在国内无维修能力的精密仪器进口（特别是芯片制造设备、液晶显示器制造设备）  <br />
		免赔说明：<br />
		普通货物：每次事故绝对免赔额为人民币1000元或损失金额的10%，两者以高者为准。<br />
		易碎品、装卸货或转载过程中造成损失每次事故绝对免赔额为5000元或损失金额的20%，两者以高者为准。<br />
		人保普货投保说明：<br />
		禁止承保的货物及特别约定：<br />
		1、金银、珠宝、钻石、玉器、首饰、古币、古玩、古书、古画、邮票、艺术品、稀有金属、文物、古董、现金、玉雕工艺品、玉石制品、名画、古代化石、瓷器、旧地毯、各类工艺品等其他价值无法确定或者保险期间内价值变化较大的货物；<br />
		2、体积小价值高的商品，如精密电子仪器、精密机械设备及器材、手机、笔记本电脑；<br />
		3、石膏制品、灯具、易碎工艺品、搪瓷等易碎容器装的货物、瓦等建筑装修材料中的易碎品等；易碎工艺品等；<br />
		4、爆炸品、放射性同位素、易燃、易爆的危险品、军用武器或装备、酒精、汽油、煤油、柴油、火柴；<br />
		5、体积庞大或各类无包装或包装不善的货物；<br />
		6、退货或二手货<br />
		免赔说明：<br />
		普通货物：每次事故绝对免赔额为人民币1000元或损失金额的10%，两者以高者为准。<br />
		易碎品、装卸货或转载过程中造成损失每次事故绝对免赔额为5000元或损失金额的20%，两者以高者为准。<br />
		人保鲜活投保说明：<br />
		基本险鲜活（蔬菜，水果）特别约定：<br />
		瓜果蔬菜只负责运输工具发生火灾、爆炸、碰撞、倾覆等交通事故导致的货损，以及因此进行施救或保护货物所支付的直接合理费用，其他任何原因所造成的货物损失均不属本保单项下保险责任，保险人不承担赔偿责任。<br />
		免赔说明：<br />
		水果蔬菜：<br />
		绝对免赔率：每次事故绝对免赔为损失金额的10%或5000元，以高为准。<br />
		平安保险投保说明：<br />
		禁止投保的货物及特别约定：<br />
		（1）武器弹药、现金、支票、票据、单证、有价证券、信用证、护照、卫星运送过程中遭受的风险；<br />
		（2）货物运输过程中遭受的核子能、原子能风险，放射性沾染风险，政治、财政、信用风险；<br />
		（3）艺术品、金银、珠宝、钻石、玉器、文物古玩等贵重物品运送过程中遭受的风险；<br />
		（4）军用品<br />
		（5）鱼粉、菜籽饼、地瓜干、花生、动植物和血制品<br />
		（6）爆竹、烟花等易燃易爆品 <br />
		（7）在国内无维修能力的精密仪器进口（特别是芯片制造设备、液晶显示器制造设备） <br />
		
		免赔说明：<br />
		1.裸装的钢材及制品，包括卷钢：本保单不负责货物的锈损和标的受到刮擦导致的损失<br />
		2.无包装或简易包装的纸张及制品，每次事故免赔为保额的1%。<br />
		3.玻璃，大理石，石料及制品，每次事故绝对免赔为易碎品保额的3%。<br />
		4.机器，设备电子产品，电器类，每次事故绝对免赔RMB2000或损失金额的5%，高者为准<br />
		<button class="btn btn-success" style="left: 50%;margin: 0 auto 0 -41px;position: absolute;" id="goumaibaoxian">购买保险</button>
	</div>
	<div style="cleat:both;"></div>
	
	</div>	
<div style="clear: both;"></div>
<jsp:include page="bottom.jsp"></jsp:include>
<div id="login" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" id="theForm">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">登录</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group">
							<div class="tips-notice col-sm-5" style="margin-left: 212px;">
								<i class="icon-volume-up tubiao1 f"></i>
								<div class="tips-content f" style="line-height: 30px; margin-left: 9px;">
									<span id="promptInfo">请输入正确的用户名和密码。</span>
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">用户名</label>
							<div class="col-sm-8">
								<!-- <label class="login-sjh" id="promptInfo"></label> -->
								<input type="text" style="display:inline-block;width:250px" class="form-control required" id="username" class="username" placeholder="请输入用户名"/>
							</div>
						</div> 
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">密码</label>
							<div class="col-sm-8">
								<input type="password" style="display:inline-block;width:250px" class="form-control required"  id="password" class="username user" placeholder="请输入密码"/>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">验证码</label>
							<div class="col-sm-8">
								<input type="text" id="checkCode"  style="display:inline-block; float:left;width: 116px;" class="form-control required" placeholder="请输入验证码"/>
								<div><img id="createCheckCode" class="f" src="/logisticsc/user/getVerCode.shtml" onclick="myReload()" width="90" height="37" style="margin-top: 0px; margin-left: 27px;"/></div>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-top: 28px;">
						<button type="button" class="btn btn-success btn-save" onclick="login()">登录</button>
					</div> 
			</form>
		</div>
	</div>
</div>
</body>
</html>