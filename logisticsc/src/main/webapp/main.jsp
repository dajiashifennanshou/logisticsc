<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用云端供应链系统</title>
<script src="/logisticsc/resources/platform/jquery/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="/logisticsc/resources/platform/css/index2.css" />
<link rel="stylesheet" href="/logisticsc/resources/platform/css/public.css" />
<!-- bootstrap-->
<script src="/logisticsc/resources/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="/logisticsc/resources/platform/js/formSubmit.js"></script>
</head>
	<style>
		body {padding: 0; margin: 0; font-family: "Microsoft YaHei"; }
		a:link {text-decoration: none; color: #0055BF;}
		a:active {text-decoration:blink; }
		a:hover {text-decoration: none; color: red;} 
		a:visited {text-decoration: none; color: #000000;}
		.items {width: 380px; height: 120px; background: #FFFFFF; border-radius: 15px; margin: 20px; display:inline-block !important; *display:inline; }
		img {vertical-align: middle; margin: 4px 4px; float: left;}
		.world { font-size: 35px; vertical-align: middle; color: #0055BF; line-height: 110px;}
	</style>
	<body>
	<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #666 !important;">温馨提示！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #666!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
		<header style="text-align: center; ">
			<p style="color: #0055BF; font-size: 40px;margin-top:30px">欢迎使用云端供应链系统</p>
		</header>
		<div style="height: 700px; background: #0055BF url(/logisticsc/resources/img/bg1.jpg) no-repeat;margin-top:30px">
			<div style="text-align: center; padding-top: 160px;">
				<div class="items">
					<a href="/logisticsc/platform-login.jsp">
						<img src="/logisticsc/resources/img/index_par1_flzx_hover.png" alt="货运交易系统" title="货运交易系统"/>
						<span class="world">
							货运交易系统
						</span>
					</a>
				</div>
				<div class="items" >
				<a href="javascript:loginliner()">
						<img src="/logisticsc/resources/img/index_par1_sysc_hover.png" alt="专线营运系统" title="专线营运系统"/>
						<span class="world">
							专线营运系统
						</span>
				</a>
				</div>
				<div class="items">
				<a href="/logisticsc/yc-login.yc">
						<img src="/logisticsc/resources/img/index_par1_zxgl_hover.png" alt="云仓管理系统" title="云仓管理系统"/>
						<span class="world">
							云仓管理系统
						</span>
				</a>
				</div>
			</div>
		</div>
		<footer style="text-align: center; line-height: 30px; margin-top: 25px;">
			© Copyright 2013-2017    粤ICP备15048227号-1    中工储运    版权所有<br />
			<a href="http://www.china-esc.net" target="_blank">中工科技</a>&nbsp;&nbsp;独家技术支持
		</footer>
<script type="text/javascript">
	function loginliner(){
		$.ajax({
			url:'/logisticsc/tms/login.shtml',
			type:'post',
			success:function(data){
				if(data.result){
					window.location.href="/logisticsc/tms/index.shtml";
				}else{
					if(data.msg == '请先登录'){
						window.location.href="/logisticsc/liner-login.jsp";
					}else{
						$("#promptModal").modal('show');
						$("#promptModalMsgs").html(data.msg);
					}
				}
			},
		})
	}	
	</script>
	</body>
</html>
