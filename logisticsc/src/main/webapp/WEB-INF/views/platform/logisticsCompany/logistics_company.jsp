<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物流商店铺</title>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico"/> 
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- 分页 -->
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />

<link href="${configProps['resources']}/platform/css/company-in.css" rel="stylesheet">
<script src="${configProps['resources']}/platform/js/logisticsCompany.js"></script> 
<style type="text/css">
	#allmap{width:100%;height:500px;}
	p{margin-left:5px; font-size:14px;}
	body{ background:#f5f5f5;} 
	.county_box{
	position: absolute;
	width: 300px;
	top: 45px;
	top:45px;
	border: 1px solid #ccc;
	/* filter: alpha(opacity=70); opacity: 0.7;} */
}
.county_box_nav{
	background-color: #fff;
}
.county_box_nav table{
	width: 100%;
	border-bottom: 1px solid #ccc;
}
.county_box_nav table td{
	text-align: center;
	font-size: 14px;
	background-color: #fff;
	cursor: pointer;
	height: 24px;
}
.county_box_nav .active{
	background-color: #39a64b;
	color: #fff;
}
.box-content{
	padding: 5px 9px;
}
.box-content li{
	display: inline-block;
}
.box-content li a{
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
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=KPYgYqgnu5GhpRDEd3hpSEuo"></script>
</head>
<body>
<div id="container">
<div id="content">
<div class="dd-nav">
			<div class="nav-con">
				<div class="logo f">
					<div class="logo-1 f"></div>
					<a href="<%=request.getContextPath()%>/platform-index.jsp" style="font-size:12px;">首页</a>
				</div>
				<div class="top-con">
				<div class="top-btn">
					<a href="javascript:void(0)" id="go_logisticsc" style="color: rgb(255, 102, 0);display:block">专线管理</a>
				</div>
				<div class="top-tu">
					<ul>
						<li class="li4">
							<div class="top-denglu"></div>
							<c:if test="${user_session.loginName == null }">
								<a href="/logisticsc/platform-login.jsp">请登录</a>
							</c:if>
							<c:if test="${user_session.loginName != null }">
								<a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" class="f">${user_session.loginName}</a>
							</c:if>
						</li>
						<li class="li5">
						<div class="top-zhuce"></div>
						<!--  
							<c:if test="${user_session.loginName == null }">
							
								<a href="${configProps['project']}/user/jumpreGister.shtml?">免费注册</a>
							</c:if>
							-->
							<c:if test="${user_session.loginName != null }">
							<a href="${configProps['project']}/user/outUser.shtml?">退出</a>
							</c:if>
						</li>
					</ul>
				</div>
			</div>
			</div>
		</div>
		<div class="company-name">
			<span class="f">
				<div class="shangpu-img1">
					<img id="gongsitupian"/>
				</div>	
				<span id="company_name">暂无信息</span><br />
				<p class="span1s f">服务电话：<span id="company_phone">暂无信息</span></p>
			</span>
		</div>
		<div class="nav-con2">
			<ul class="nav-con3">
				<li>
					公司介绍 
				</li>
				<li>
					网点介绍							
				</li>
				<li>
					服务线路
				</li>
			    <li>
			    	店铺评分
			    </li>	           
				<li>
					联系我们
				</li>
			</ul>
			<ul class="nav-con4">
				<li>
					<div class="company-descripe">
						<div class="title1">
							<span class="f span1">1.</span>
							<p>公司概况</p>
							<img src="/logisticsc/resources/platform/img/shangpu_img2.png" />
						</div>
						<div class="con1">
							<p id="company_info">
								暂无信息
							</p>
						</div>
					</div>
					<div class="company-descripe xx">
						<div class="title1">
							<span class="f span1">2.</span>
							<p>详细信息</p>
							<img src="/logisticsc/resources/platform/img/shangpu_img2.png" />
						</div>
						<table>
							<tr>
								<td>员工人数</td>
								<td><span id="staff_number">暂无信息</span></td>
								<td>品牌名称</td>
								<td><span id="brand_name">暂无信息</span></td>
							</tr>
							<tr>
								<td>覆盖地域</td>
								<td><span id="region">暂无信息</span></td>
								<td>车辆信息</td>
								<td><span id="vehicle_info">暂无信息</span></td>
							</tr>
							<tr>
								<td>仓库信息</td>
								<td><span id="warehouse_info">暂无信息</span></td>
								<td>年营业额</td>
								<td><span id="annual_money">暂无信息</span><span id="noney_danwei"></span></td>
							</tr>
							<tr>
								<td>承运货物信息</td>
								<td><span id="carriage_goods">暂无信息</span></td>
								<td>服务内容</td>
								<td><span id="service_info">暂无信息</span></td>
							</tr>
						</table>
					</div>
				</li>
				<li>
					<div style="margin-top:20px;padding-left:20px;">
						<form class="form-inline">
						  <label>省份：</label>
					          <select id="province" style="width: 144px;" class="form-control fcjselect" name="province" onchange="contact()">
					          </select>
					       <label>市份：</label>
					          <select id="countys" style="width: 144px;" class="form-control fcjselect" name="countys">
					          	<option value="">请选择</option>
					          </select>   
					      <label >网点名称：</label>
			  	  			<input type="text" id="name" class="form-control" name="name" placeholder="网点名称">   
							<button type="button" onclick="wangdianchaxun()" class="btn btn-info">查询</button>
						</form>
					</div>
					<div style="margin-top:20px;" id="biao-1">
						<table class="table">
							<thead>
								<tr>
								<th>网点名称</th>
								<th>地址</th>
								<th>联系电话</th>
								<th>联系人</th>
								<th>操作</th>
								</tr>
							</thead>
							<tbody id="wangdian">
							</tbody>
						</table>
						<div id="wangdianPageList">
						</div>
					</div>
				</li>
				<li>
					<div style="margin-top:20px;padding-left:20px;">
						<form class="form-inline">
						  		<input type="hidden" id="startProvince">
								<input type="hidden" id="startCity">
								<input type="hidden" id="startCounty">
								<input type="hidden" id="targetProvince">
								<input type="hidden" id="targetCity">
								<input type="hidden" id="targetCounty">
								<div class="search_box row" style="position: relative;">
									<div class="search_box_info">
										<div style="float: left;margin-right:20px;margin-left: 15px;">
											<label>始发地：</label>
											<input type="text" id="start_input" class="form-control choice-county" style="margin: 7px 0px;width: 270px;">   
										</div>&nbsp;&nbsp;
										<div style="float: left;margin-right:20px;">
											<label>目的地：</label>
											<input type="text" id="target_input" class="form-control choice-county" style="margin: 7px 0px;width: 270px;"> 
										</div>
									</div>
									<div class="search_box_button" style="float: left;margin-top:-11px;">
										<button type="button" onclick="chaxun()" class="btn btn-info">查询</button>
									</div>
									<div class="county_box" style="display: none;z-index: 999;">
										<div class="county_box_nav">
											<table>
												<tr>
													<td class="active">省</td>
													<td>市</td>
													<td>县</td>
												</tr>
											</table>
											<div class="box-content" id="content-province">
											</div>
											<div class="box-content" id="content-city" style="display: none;">
											</div>
											<div class="box-content" id="content-county" style="display: none;">
											</div>
										</div>
									</div>
								</div>
								 <div
								style="margin:0px 0px 10px  0px; color: white; float: left; font-weight: bold;">
								<input type="checkbox" id="isStartCounty" value="1" class="f"><lable style="display: block; float: left; padding-top: 2px; padding-left: 5px;">始发地区域线路</lable>
								<input type="checkbox" id="isEndCounty" value="1" class="f"
									style="margin-left: 242px"><lable style="display: block; float: left; padding-top: 2px; padding-left: 5px;">目的地区域线路</lable>
							</div>
						</form>
					</div>
					<div style="margin-top:20px;" id="biao-1">
						<table class="table">
							<thead>
								<tr>
								<th>线路</th>
								<th>线路描述</th>
								<th>收费信息</th>
								<th>交易评价</th>
								<!-- <th>线路评分</th> -->
								<th>操作</th>
								</tr>
							</thead>
							<tbody id="fuwuxianlu">
							</tbody>
						</table>
						<div id="pageList">
						</div>
					</div>
				</li>
				<li>
					<div class="pingfen company-descripe">
						<div class="title1">
							<span class="f span1">1.</span>
							<p>承运商店铺评分展示</p>
							<img src="/logisticsc/resources/platform/img/shangpu_img2.png" />
						</div>
						<div class="pf-xx">
							<div class="pf-left f">
								<div class="hpd f">
									<div class="hpd-con" >
										<span style="style="color: rgb(255, 102, 0); font-size: 20px;"><span style="font-size: 32px;color: rgb(255, 102, 0)" id="haopingdu"></span>%</span><br/>
										好评度
									</div>
								</div>
								<div class="pingjia f">
									<div class="haoping">
										<span class="f">好评</span>
										<div class="fk1 f">
											<div class="fk-haoping" id="haoping"></div>
										</div>
										<span id="haopingrenshu"></span>
										<span>个人评价了好评</span>
									</div>
									<div class="haoping" style="margin-top: 12px;">
										<span class="f">中评</span>
										<div class="fk1 f">
											<div class="fk-haoping" id="zhongping"></div>
										</div>
										<span id="zhongpingrenshu"></span>
										<span>个人评价了好评</span>
									</div>
									<div class="haoping" style="margin-top: 12px;">
										<span class="f">差评</span>
										<div class="fk1 f">
											<div class="fk-haoping" id="chaping"></div>
										</div>
										<span id="chapingrenshu"></span>
										<span>个人评价了好评</span>
									</div>
								</div>
							</div>
							<div class="pf-right f">
								<div class="tihuo1">
									<span>货物完好：平均分</span>
									<span>3</span>
								</div>
								<div class="tihuo2 tihuo1">
									<span>准时提货：平均分</span>
									<span>4</span>
								</div>
								<div class="tihuo3 tihuo1">
									<span>准时送货：平均分</span>
									<span>3.2</span>
								</div>
								<div class="tihuo4 tihuo1">
									<span>服务态度：平均分</span>
									<span>5.2</span>
								</div>
							</div>
							<div class="dianping">
								<table class="table">
									<thead>
									<tr>
									    <th style="width:170px">用户</th>
									    <th style="width:204px">线路</th>
										<th style="width:496px">评价内容</th>
										<th style="width:170px">评价时间</th>
										<th>评价星级</th>
									</tr>	
									</thead>
									<tbody id="pingjia">
									</tbody>
								</table>
								<div id="pingjiaPageList">
								</div>
							</div> 
						</div>
					</div>
				</li>
				<li>
					<div class="contact company-descripe">
						<div class="title1">
							<span class="f span1">1.</span>
							<p>联系方式</p>
							<img src="/logisticsc/resources/platform/img/shangpu_img2.png" />
						</div>
						<table>
							<tr>
								<td>公司名称</td>
								<td><span id="companyName">暂无信息</span></td>
							</tr>
							<tr>
								<td>联系人</td>
								<td><span id=contacts1>暂无信息</span></td>
							</tr>
							<tr>
								<td>电话</td>
								<td><span id="contacts1Mobile">暂无信息</span></td>
							</tr>
							<tr>
								<td>联系人</td>
								<td><span id="contacts2">暂无信息</span></td>
							</tr>
							<tr>
								<td>电话</td>
								<td><span id="contacts2Mobile">暂无信息</span></td>
							</tr>
							<tr>
								<td>传真</td>
								<td><span id="company_fax">暂无信息</span></td>
							</tr>
							<tr>
								<td>地址</td>
								<td><span id="company_address">暂无信息</span></td>
							</tr>
						</table>
					</div>
				</li>
			</ul>
		</div>
		</div>
		</div>
		<input id="companyId" type="hidden" value='${companyId}'/>
		<input type="hidden" id="userNames" value="${user_session.loginName}"/>
<div id="wangdianditu" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<!-- BEGIN form-->
			<form class="form-horizontal form-bordered" id="">
				<div class="modal-header modal-info modal-success">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title" id="myModalLabel">网点信息</h4>
					<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
				</div>
				<div class="modal-body add-role-body">
					<div id="allmap"></div> 
				</div>
		</form>
		</div>
	</div>
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
<div id="zhuanxian" class="modal fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="modalTitle"></h4>
                    <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="font-weight:bold"><span id="modalContent"></span></h5>
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
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
                     <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #cccccc!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
</div>
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
<div style="clear: both;"></div>
<div id="#footer">
<jsp:include page="../bottom.jsp"></jsp:include>
</div>	
</body>
</html>