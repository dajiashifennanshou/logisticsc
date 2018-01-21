<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>货运交易系统底部</title>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/bottom.css" /> 
 <script src="/logisticsc/resources/platform/js/formSubmit.js"></script>

<script type="text/javascript">
	function helpjiaruwm() {
		var name=new Array(); 
		var value=new Array();
		name[0]="name";
		value[0]="contactUs";
		buildForm("/logisticsc/helpCenter/toHelpCenter.shtml",name,value);
	}
	function helpguanyu() {
		var name=new Array(); 
		var value=new Array();
		name[0]="name";
		value[0]="aboutUs";
		buildForm("/logisticsc/helpCenter/toHelpCenter.shtml",name,value);
	}
</script> 
</head>
<body>
		<div class="index2_bot">
			<div class="bot-con">
				<center>© Copyright 2013-2017 中工储运   版权所有 |&nbsp; 粤ICP备15048227号-1</center>
				<div class="bot_left f">
					<ul>
						<!--  
						<li>
							<a href="javascript:javascript:void(0);" onclick="helpguanyu()">关于中工&nbsp;&nbsp;&nbsp;&nbsp;|</a>
							<a href="javascript:javascript:void(0);" onclick="helpjiaruwm()">&nbsp;&nbsp;&nbsp;&nbsp;加入我们</a><br />
						</li>
						<li>
							全国服务热线：18000510004
						</li>
						<li>
							  © Copyright 2013-2017 中工储运   版权所有 |&nbsp; 粤ICP备15048227号-1
						</li>
						-->
					</ul>
				</div>
				<!--  
				<div class="bot_right r">
					<div class="r-left f">
						<span class="rl">
							扫一扫<br />
							访问官网
						</span>
					</div>
					<img src="/logisticsc/resources/platform/img/icon-weixin.png" />
				</div>
				-->
			</div>
		</div>
</body>
</html>