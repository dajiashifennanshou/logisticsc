<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>货运交易系统--我要发货</title>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<!-- jquery -->
<script
	src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link
	href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<script type="text/javascript"src="http://api.map.baidu.com/api?v=2.0&ak=KPYgYqgnu5GhpRDEd3hpSEuo"></script>
<script src="${configProps['resources']}/platform/js/deliverGoods.js"></script>
<link href="${configProps['resources']}/platform/css/woyaofahuo.css" rel="stylesheet">
<style type="text/css">
#allmap {
	width: 100%;
	height: 500px;
}

p {
	margin-left: 5px;
	font-size: 14px;
}

.county_box {
	position: absolute;
	width: 300px;
	top: 45px;
	top: 45px;
	border: 1px solid #ccc;
	/* filter: alpha(opacity=70); opacity: 0.7;} */
}

.county_box_nav {
	background-color: #fff;
}

.county_box_nav table {
	width: 100%;
	border-bottom: 1px solid #ccc;
}

.county_box_nav table td {
	text-align: center;
	font-size: 14px;
	background-color: #fff;
	cursor: pointer;
	height: 24px;
}

.county_box_nav .active {
	background-color: #39a64b;
	color: #fff;
}

.box-content {
	padding: 5px 9px;
}

.box-content li {
	display: inline-block;
}

.box-content li a {
	text-decoration: none;
	cursor: pointer;
	display: inline-block;
	margin: 5px;
	padding: 2px 3px;
}

.box-content li a:HOVER {
	background-color: #666;
	color: #fff;
}
</style>
</head>
<body style="background: #f5f5f5;">
	<div id="container">
		<jsp:include page="../top.jsp"></jsp:include>
		<div class="container-self">
			<div class="woyao-con">
				<div class="demo-content platformStyle">
					<div class="fahuo-guangg">
						<div class="fahuo-laba f"></div>
						<!-- <p>中工储运联合众芳，实现跨平台合作</p> -->
						<p id="pub_info"></p>
					</div>
					<div class="fahuo-sousuo f">
						<form class="form-inline f" id="listorderDeliver"
							style="margin-top: 15px; width: 822px;">
							<!-- <label style="color:white;">起始地：</label>
				          <select id="start_province_outlets" style="width: 144px;" class="form-control fcjselect" name="startProvince" id="startProvince" onchange="startAjaxCity()">
				          </select>
				          <select id="start_city_outlets" style="width: 120px;" class="form-control fcjselect"name="startCity" id="startCity" onchange="startAjaxCounty()">
				          </select>
				          <select id="start_county_outlets" style="width: 144px;" class="form-control fcjselect"  name="startCounty" id="startCounty" >
				          </select>
					<label style="color:white;">目的地：</label>
						 <select id="end_province_outlets" style="width: 144px;" class="form-control fcjselect" name="endProvince" id="endProvince" onchange="endAjaxCity()">
				         </select>
				         <select id="end_city_outlets" style="width: 120px;" class="form-control fcjselect" name="endCity" id="endCity" onchange="endAjaxCounty()">
				         </select>
				         <select id="end_county_outlets" style="width: 144px;" class="form-control fcjselect" name="endCounty" id="endCounty">
				         </select>
					<input id="chaxun" type="button" class="btn" style="background:#ff6600; color:white;width:120px" value="查询"> -->
							<input type="hidden" id="startProvince"> <input
								type="hidden" id="startCity"> <input type="hidden"
								id="startCounty"> <input type="hidden"
								id="targetProvince"> <input type="hidden"
								id="targetCity"> <input type="hidden" id="targetCounty">
							<div class="search_box" style="position: relative;">
								<div class="search_box_info">
									<div style="float: left; margin-right: 20px;">
										<label style="color: white;">始发地：</label> <input type="text"
											id="start_input" class="form-control choice-county"
											style="margin: 7px 0px; width: 270px;">
									</div>
									&nbsp;&nbsp;
									<div style="float: left; margin-right: 20px;">
										<label style="color: white;">目的地：</label> <input type="text"
											id="target_input" class="form-control choice-county"
											style="margin: 7px 0px; width: 270px;">
									</div>
								</div>
								<div class="search_box_button"
									style="float: left; margin-top: -11px;">
									<input id="chaxun" type="button" class="btn"
										style="background: #ff6600; color: white; width: 120px"
										value="查询">
								</div>
								<div class="county_box" style="display: none; z-index: 999;">
									<div class="county_box_nav">
										<table>
											<tr>
												<td class="active">省</td>
												<td>市</td>
												<td>县</td>
											</tr>
										</table>
										<div class="box-content" id="content-province"></div>
										<div class="box-content" id="content-city"
											style="display: none;"></div>
										<div class="box-content" id="content-county"
											style="display: none;"></div>
									</div>
								</div>
							</div>
							<div
								style="margin-top: 0px; color: white; margin-left: 0px; float: left; font-weight: bold;">
								<input type="checkbox" id="isStartCounty" value="1" class="f"><lable style="display: block; float: left; padding-top: 2px; padding-left: 5px;">始发地区域线路</lable>
								<input type="checkbox" id="isEndCounty" value="1" class="f"
									style="margin-left: 242px"><lable style="display: block; float: left; padding-top: 2px; padding-left: 5px;">目的地区域线路</lable>
							</div>
						</form>

					</div>
					<!-- <div class="fauo-shaixuan f">
					<div class="huowuxinx f">
						货物信息
						<input type="text" id="zhonghuo"/>
						吨
						<input type="text" id="paohuo"/>
						立方米
					</div>
					<div class="tipaisong f">
						提派送服务
						<label>
							<input type="checkbox" value="" name="1"/>上门取货
						</label>
						<label>
							<input type="checkbox" value="" name="2"/>送货上门
						</label>
					</div>
				</div> -->
					<div class="paixu f">
						<div class="xuanzepx f">选择排序方式</div>
						<div class="pttj f">
							<ul>
								<li><button class="btn btn-success" id="anniu"
										onclick="pingtaituijian()">系统推荐</button></li>
								<li><button class="btn btn-success" id="anniu"
										onclick="xingji()">星级</button></li>
								<li><button class="btn btn-success" id="anniu"
										onclick="shixiao()">时效</button></li>
								<li><button class="btn btn-success" id="anniu"
										onclick="chengyunpiaoshu()">承运票数</button></li>
								<li><button class="btn btn-success" id="anniu"
										onclick="zhonghuojia()">重货价</button></li>
								<li><button class="btn btn-success" id="anniu"
										onclick="paohuojia()">泡货价</button></li>
								<li><button class="btn btn-success" id="anniu"
										onclick="qiyunjia()">起运价</button></li>
							</ul>
						</div>
					</div>
				</div>
				<div>
					<table class="table" id="table-xx" style="background: white;">
						<thead>
							<tr>
								<th>物流商</th>
								<th>线路</th>
								<th>线路描述</th>
								<th>收费情况</th>
								<th>交易评价</th>
								<th>线路评分</th>
								<!-- <th>总费用</th> -->
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="getListorderDeliver" style="color: #696969;">
						</tbody>
					</table>
					<div id="pageList"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div style="clear: both;"></div>
	</div>
	<jsp:include page="../bottom.jsp"></jsp:include>
	<input id="param_form" type="hidden" value='${paramForm}' />
	<input id="xzqh" type="hidden" value='${xqzh}' />
	<input id="companyName" type="hidden" value='${companyName}' />
	<input type="hidden" id="userNames" value="${user_session.loginName}" />
	<div id="successModal" class="modal fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header modal-info modal-success">
					<h4 class="modal-title" id="myModalLabel">页面提示</h4>
					<a class="close" data-dismiss="modal" style="margin-top: -23px;">×</a>
				</div>
				<div class="modal-body success-info">
					<h5 style="color: #6f9fd9 !important; font-weight: bold">
						成功提示:<span id="successModalMsgs"></span>
					</h5>
				</div>
			</div>
		</div>
	</div>
	<div id="promptModal" class="modal  fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header modal-info modal-success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title" style="color: #333 !important;"
						id="myModalLabel">温馨提示！</h4>
					<a class="close" data-dismiss="modal" style="margin-top: -23px;">×</a>
				</div>
				<div class="modal-body success-info">
					<h5 style="font-weight: bold">
						温馨提示:<span id="promptModalMsgs"></span>
					</h5>
				</div>
			</div>
		</div>
	</div>
	<div id="errorModal" class="modal  fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header modal-info modal-success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="modal-title" id="myModalLabel"
						style="color: red !important;">操作失败！</h4>
					<a class="close" data-dismiss="modal" style="margin-top: -23px;">×</a>
				</div>
				<div class="modal-body success-info">
					<h5 style="color: red !important; font-weight: bold">
						错误提示:<span id="errorModalMsgs"></span>
					</h5>
				</div>
			</div>
		</div>
	</div>
	<div id="wangdianditu" class="modal  fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog" style="width: 70%">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">网点信息</h4>
						<a class="close" data-dismiss="modal" style="margin-top: -23px;">×</a>
					</div>
					<div class="modal-body add-role-body">
						<ul id="myTab" class="nav nav-tabs">
							<li id="qiishdi"><a href="javascript:void(0)"
								data-toggle="tab" onclick="qishidi()">起始地</a></li>
							<li id="mudiddi"><a href="javascript:void(0)"
								data-toggle="tab" onclick="mididi()">目的地</a></li>
						</ul>
						<div>
							<div id="allmap"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="login" class="modal  fade" tabindex="-1"
		data-backdrop="static">
		<div class="modal-dialog" id="theForm">
			<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">登录</h4>
						<a class="close" data-dismiss="modal" style="margin-top: -23px;">×</a>
					</div>
					<div class="modal-body add-role-body">
						<div class="form-group">
							<div class="tips-notice col-sm-5" style="margin-left: 212px;">
								<i class="icon-volume-up tubiao1 f"></i>
								<div class="tips-content f"
									style="line-height: 30px; margin-left: 9px;">
									<span id="promptInfo">请输入正确的用户名和密码。</span>
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">用户名</label>
							<div class="col-sm-8">
								<!-- <label class="login-sjh" id="promptInfo"></label> -->
								<input type="text" style="display: inline-block; width: 250px"
									class="form-control required" id="username" class="username"
									placeholder="请输入用户名" />
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">密码</label>
							<div class="col-sm-8">
								<input type="password"
									style="display: inline-block; width: 250px"
									class="form-control required" id="password"
									class="username user" placeholder="请输入密码" />
							</div>
						</div>
						<div class="form-group" style="margin-top: 28px;">
							<label class="control-label col-sm-4">验证码</label>
							<div class="col-sm-8">
								<input type="text" id="checkCode"
									style="display: inline-block; float: left; width: 116px;"
									class="form-control required" placeholder="请输入验证码" />
								<div>
									<img id="createCheckCode" class="f"
										src="/logisticsc/user/getVerCode.shtml" onclick="myReload()"
										width="90" height="37"
										style="margin-top: 0px; margin-left: 27px;" />
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-top: 28px;">
						<button type="button" class="btn btn-success btn-save"
							onclick="login()">登录</button>
					</div>
				</form>
			</div>
		</div>
		<form action="/logisticsc/deliverGoods/placeOrder.shtml"
			id="toPlaceOrderForm" method="post">
			<input type="hidden" name="lineId" id="order_lineId"> <input
				type="hidden" name="startProvince" id="order_startProvince">
			<input type="hidden" name="startCity" id="order_startCity"> <input
				type="hidden" name="startCounty" id="order_startCounty"> <input
				type="hidden" name="endProvince" id="order_endProvince"> <input
				type="hidden" name="endCity" id="order_endCity"> <input
				type="hidden" name="endCounty" id="order_endCounty">
		</form>
	</div>
</body>
</html>