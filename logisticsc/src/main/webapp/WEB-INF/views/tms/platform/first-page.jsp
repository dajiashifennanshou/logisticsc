<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专线营运系统-首页</title>
<link href="${configProps['resources']}/tms/css/first-page.css" rel="stylesheet">
</head>
<body>
	<div class="flow-con">
		<div class="ds-dd" onclick="controlMenu('货运订单')">货运订单</div>
		<div class="xian-heng xian1"></div>
		<div id="triangle-right"  class="sjx1"></div>
		<div class="paiche-tihuo">
			<img src="${configProps['resources']}/tms/img/htai_tubiao1.png" class="img1" />
			<span class="span1" onclick="controlMenu('提货管理')">派车提货</span>
		</div>
		<div class="xian-heng xian2"></div>
		<div id="triangle-right"  class="sjx2"></div>
		<div class="kaidan-ruku">
			<img src="${configProps['resources']}/tms/img/htai_tubiao2.png" class="img1" />
			<span class="span1" onclick="controlMenu('开单入库')">开单入库</span>
		</div>
		<div class="xian-shu xians1"></div>
		<div id="triangle-down"  class="sjx3"></div>
		<div class="paiche-liebiao">
			<img src="${configProps['resources']}/tms/img/htai_tubiao3.png" class="img1" />
			<span class="span1" onclick="controlMenu('提货管理')">派车列表</span>
		</div>
		<div class="yundan-liebiao">
			<img src="${configProps['resources']}/tms/img/htai_tubiao3.png" class="img1" />
			<span class="span1" onclick="controlMenu('运单管理')">运单列表</span>
		</div>
		<div class="xian-shu xians2"></div>
		<div id="triangle-down"  class="sjx4"></div>
		<div class="xian-heng xian3"></div>
		<div class="xian-shu xians3"></div>
		<div class="xian-heng xian4"></div>
		<div id="triangle-right"  class="sjx5"></div>
		<div class="xian-heng xian5"></div>
		<div id="triangle-right"  class="sjx6"></div>
		<div class="fache-peizai">
			<img src="${configProps['resources']}/tms/img/htai_tubiao4.png" class="img1" />
			<span class="span1" onclick="controlMenu('发车配载')">发车配载</span>
		</div>
		<div class="waibao-peizai">
			<img src="${configProps['resources']}/tms/img/htai_tubiao4.png" class="img1" />
			<span class="span1" onclick="controlMenu('外包出库')">外包配载</span>
		</div>
		<div class="xian-shu xians4"></div>
		<div id="triangle-up"  class="sjx7"></div>
		<div class="fachedan-liebiao">
			<img src="${configProps['resources']}/tms/img/htai_tubiao3.png" class="img1" />
			<span class="span1" onclick="controlMenu('发车管理')">发车单列表</span>
		</div>
		<div class="xian-heng xian6"></div>
		<div class="xian-shu xians5"></div>
		<div id="triangle-down" class="sjx8"></div>
		<div class="clgz">
			<img src="${configProps['resources']}/tms/img/htai_tubiao5.png" class="img1" />
			<span class="span1" onclick="controlMenu('发车管理')">车辆跟踪</span>
		</div>
		<div class="xian-shu xians6"></div>
		<div class="xian-heng xian7"></div>
		<div class="qiandaork">
			<img src="${configProps['resources']}/tms/img/htai_tubiao6.png" class="img1" />
			<span class="span1" onclick="controlMenu('签到入库')">签到入库</span>
		</div>
		<div id="triangle-left" class="sjx9"></div>
		<div class="songhuosm">
			<img src="${configProps['resources']}/tms/img/htai_tubiao7.png" class="img1" />
			<span class="span1" onclick="controlMenu('送货上门')">送货上门</span>
		</div>
		<div class="ziti">
			<img src="${configProps['resources']}/tms/img/htai_tubiao8.png" class="img1" />
			<span class="span1" onclick="controlMenu('自提')">自提</span>
		</div>
		<div class="zhongzhuan">
			<img src="${configProps['resources']}/tms/img/htai_tubiao10.png" class="img1" />
			<span class="span1" onclick="controlMenu('中转')">中转</span>
		</div>
		<div class="xian-heng xian8"></div>
		<div id="triangle-left" class="sjx10"></div>
		<div class="xian-shu xians7"></div>
		<div class="xian-shu xians8"></div>
		<div class="xian-heng xian9"></div>
		<div class="xian-heng xian10"></div>
		<div id="triangle-left" class="sjx10 sjx11"></div>
		<div id="triangle-left" class="sjx10 sjx12"></div>
		<div class="qianshou">
			<img src="${configProps['resources']}/tms/img/htai_tubiao9.png" class="img1" />
			<span class="span1" onclick="controlMenu('签收列表')">签收</span>
		</div>
		<div class="xian-heng xian8 xian11"></div>
		<div id="triangle-left" class="sjx13 sjx10"></div>
		<div class="xian-shu xians1 xians9"></div>
		<div id="triangle-down"  class="sjx3 sjx14"></div>
		<div class="paiche-liebiao qianshou-lieb">
			<img src="${configProps['resources']}/tms/img/htai_tubiao3.png" class="img1" />
			<span class="span1" onclick="controlMenu('签收列表')">签收列表</span>
		</div>
		<div class="index-bot">
			<ul>
				<li>
					<img src="${configProps['resources']}/tms/img/htai_tubiao15.png" />
					<span class="span2" onclick="controlMenu('异常管理')">异常管理</span>
				</li>
				<li>
					<img src="${configProps['resources']}/tms/img/htai_tubiao11.png" />
					<span class="span2" onclick="controlMenu('回单管理')">回单管理</span>
				</li>
				<%-- <li>
					<img src="${configProps['resources']}/tms/img/htai_tubiao12.png" />
					<span class="span2">财务管理</span>
				</li>
				<li>
					<img src="${configProps['resources']}/tms/img/htai_tubiao13.png" />
					<span class="span2">客服中心</span>
				</li>
				<li>
					<img src="${configProps['resources']}/tms/img/htai_tubiao14.png" />
					<span class="span2">系统设置</span>
				</li> --%>
			</ul>
		</div>
	</div>

	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	function controlMenu(name){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/controlmenu.shtml',
			data : {'name' : name},
			success : function(result){
				if(result != null&&result != ""){
					window.location.href = '<%=request.getContextPath()%>' + result;
				}else{
					alert("您暂无权限使用此功能!");
				}
			}
		});
	}
	</script>
</body>
</html>