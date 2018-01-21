<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台首页</title>
<link rel="stylesheet" href="/logisticsc/resources/platform/css/jquery.slideBox.css" />
<script src="/logisticsc/resources/platform/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/logisticsc/resources/platform/jquery/jquery.slideBox.js" ></script>
<!-- 全局样式 -->
<link href="/logisticsc/resources/platform/css/external-introduction.css" rel="stylesheet">
<link href="/logisticsc/resources/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="/logisticsc/resources/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

<link rel="stylesheet" href="/logisticsc/resources/platform/css/login/font-awesome-3.2.1/css/font-awesome.min.css" />
<link rel="stylesheet" href="/logisticsc/resources/platform/css/login/font-awesome-3.2.1/css/font-awesome-ie7.min.css" />
<script src="/logisticsc/resources/platform/js/platformIndex.js"></script>
<link rel="stylesheet" href="/logisticsc/resources/platform/css/index.css" />
</head> 
<body style="background:white;">
	<div class="xf">
			<ul>
				<li>
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=850226953&site=qq&menu=yes">
					<img src="/logisticsc/resources/platform/img/online-ask.png" border="0" src="http://wpa.qq.com/pa?p=2::53" alt="联系我们" title="联系我们"/>
					</a> 
				</li>
				<li id="zhiding"  style="margin-top:2px ;">
					<a href="#"><img src="/logisticsc/resources/platform/img/top.png" /></a>
				</li>
			</ul>
	</div>
		
		
		<div class="banner">
			<!-- <img src="/logisticsc/resources/platform/img/index_banner.jpg" /> -->
			<div class="logo-1"></div>
			<div class="gongshilan">
				<table>
					<tr>
						<td class="font-color">平台交易金额</td>
						<td><span>￥</span><span id="finalPriceCount"></span></td>
					</tr>
					<tr>
						<td class="font-color">物流订单数量</td>
						<td ><span id="orderCount"></span></td>
					</tr>
					<tr>
						<td class="font-color">物流提供商数量</td>
						<td><span id="userProviderCount"></span></td>
					</tr>
					<tr>
						<td class="font-color">专线数量</td>
						<td><span id="userCompanyCount"></span></td>
					</tr>
				</table>
			</div>
			<div class="head f">
			<!-- 
				<div class="head-phonimg f">
					<img src="/logisticsc/resources/platform/img/index_head_phone.png" />
					<div class="head-phonfon f">
						18000510004
					</div>
				</div>
				 -->
				<div class="head-denglu f">
				<!-- 
					<img src="/logisticsc/resources/platform/img/index_head_denglu.png" />
					<c:if test="${user_session.loginName == null }">
						<a href="/logisticsc/platform-login.jsp" class="f">请登陆</a>
					</c:if>
					 -->
					<c:if test="${user_session.loginName != null }">
						<a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" class="f">${user_session.loginName}</a>
					</c:if>
					<div class="head-zhuce f">
					<!--  
						<img src="/logisticsc/resources/platform/img/index_head_zhuce.png" />
						<c:if test="${user_session.loginName == null }">
							<a href="/logisticsc/user/jumpreGister.shtml" class="f">免费注册</a>
						</c:if>
						-->
						<c:if test="${user_session.loginName != null }">
							<a href="/logisticsc/user/outUser.shtml?" class="index_tuichu">退出</a>
						</c:if>
					</div>
				</div>
			</div>
			<div class="banner-con">
				<ul class="banner-top">
					<li>我要发货</li>
					<li>物流商查询</li>
					<li>订单跟踪</li>
				</ul>
				<ul class="banner-bot">
					<li class="bot1">
						<form id="deliver_goods" >
							<div class="shifadi f">
								<p class="f">始发地：</p>
								<select id="start_province_outlets" style="width: 144px;" class="form-control fcjselect f" name="startProvince" id="startProvince" onchange="startAjaxCity()">
						          </select>
						          <select id="start_city_outlets" style="width: 120px; margin-left:20px" class="form-control f fcjselect"name="startCity" id="startCity" onchange="startAjaxCounty()">
						          </select>
						          <select id="start_county_outlets" style="width: 120px;margin-left:20px" class="form-control f fcjselect"  name="startCounty" id="startCounty" >
						          </select>
							</div>
							<div class="shifadi destination f" style="margin-top:5px;">
								<p class="f">目的地：</p>
								<select id="end_province_outlets" style="width: 144px;" class="form-control f fcjselect" name="endProvince" id="endProvince" onchange="endAjaxCity()">
						         </select>
						         <select id="end_city_outlets" style="width: 120px;margin-left:20px" class="f form-control fcjselect" name="endCity" id="endCity" onchange="endAjaxCounty()">
						         </select>
						         <select id="end_county_outlets" style="width: 120px;margin-left:20px" class="f form-control fcjselect" name="endCounty" id="endCounty">
						         </select>
							</div>
							<input id="chaxun" type="button" class="cx" value="查询" onclick="deliverGoods()">
							<!-- <input type="button" class="cx" value="查询"> -->
						</form>
					</li>
					<li class="bot1 bot2">
						<form>
							<div class="shifadi f">
								<p class="f">物流商：</p>
								<input type="text" class="f wuliu-cx" id="wuliushangmingcheng"/>
							</div>
							<input type="button" class="cx" onclick="wuliushang()" value="查询">
						</form>
					</li>
					<li class="bot1 bot2">
						<form>
							<div class="shifadi f">
								<p class="f">订单号/运单号：</p>
								<input type="text" class="f wuliu-cx" id="dingdanhao"/>
							</div>
							<input type="button" class="cx" onclick="dingdangenzong()" value="查询">
						</form>
					</li>
				</ul>
			</div>
		</div>
		<div class="clr"></div>
		<!--*****************************part1************************************-->
		<div class="con1">
			<a href="<%=request.getContextPath()%>/helpCenter/toHelpCenter.shtml" class="con1-zxgl f">
				<div class="zxgl-top f">
					<img src="/logisticsc/resources/platform/img/index_par1_zxgl.png" />
				</div>
				<div class="zxgl-fon">
					专线管理<br />
					<p>物流管理平台</p>
				</div>
			</a>
			<a href="<%=request.getContextPath()%>/helpCenter/toHelpCenter.shtml" class="con1-bxyw con1-zxgl f">
				<div class="zxgl-top f">
					<img src="/logisticsc/resources/platform/img/index_par1_bx.png" />
				</div>
				<div class="zxgl-fon">
					保险业务<br />
					<p>诚信合作伙伴</p>
				</div>
			</a>
			<a class="con1-bxyw con1-zxgl f">
				<div class="zxgl-top f">
					<img src="/logisticsc/resources/platform/img/index_par1_flzx.png" />
				</div>
				<div class="zxgl-fon">
					法律咨询<br />
					<p>有效法律帮助</p>
				</div>
			</a>
			<a href="<%=request.getContextPath()%>/helpCenter/toHelpCenter.shtml" class="con1-bxyw con1-zxgl f">
				<div class="zxgl-top f">
					<img src="/logisticsc/resources/platform/img/index_par1_about.png" />
				</div>
				<div class="zxgl-fon">
					关于我们<br />
					<p>进一步了解我们</p>
				</div>
			</a>
			<a href="<%=request.getContextPath()%>/helpCenter/toHelpCenter.shtml" class="con1-bxyw con1-zxgl f">
				<div class="zxgl-top f">
					<img src="/logisticsc/resources/platform/img/index_par1_lxwm.png" />
				</div>
				<div class="zxgl-fon">
					联系我们<br />
					<p>时刻关注我们</p>
				</div>
			</a>
			<a href="<%=request.getContextPath()%>/helpCenter/toHelpCenter.shtml" class="con1-bxyw con1-zxgl f">
				<div class="zxgl-top f">
					<img src="/logisticsc/resources/platform/img/index_par1_sysc.png" />
				</div>
				<div class="zxgl-fon">
					使用手册<br />
					<p>快速掌握技能</p>
				</div>
			</a>
		</div>
		<div class="clr"></div>
		<!--*****************************part2************************************-->
		<div class="con2">
			<!--******************左***************************-->
			<div id="demo3" class="slideBox">
				  <ul class="items">
				    <li><a ><img src="/logisticsc/resources/platform/img/index-par2-banner6.jpg"></a></li>
				    <li><a ><img src="/logisticsc/resources/platform/img/index-par2-banner2.jpg"></a></li>
				    <li><a ><img src="/logisticsc/resources/platform/img/index-par2-banner3.jpg"></a></li>
				    <li><a ><img src="/logisticsc/resources/platform/img/index-par2-banner4.jpg"></a></li>
				    <li><a ><img src="/logisticsc/resources/platform/img/index-par2-banner5.jpg"></a></li>
				  </ul>
			</div>
		</div>
		<!--*****************************part3************************************-->		
		<div class="con3">
			<div class="con5-con2 f">
				<div class="con2-top1 f">
					<!--<img class="f" src="img/index_tit.png" />-->
					<p>推荐物流商</p>
				</div>
				<div class="lianjie-con f" id="getUserCompany">
				</div>	
			</div>
			<div class="con3-middle f">
				<div class="con2-top1 f">
					<!--<img src="img/index_tit.png" />-->
					<p>热门专线</p>
				</div>
				<table id="hot_line_s" class="hot-line" >
					<tr>
						<th>物流商 </th>
						<th>线路描述</th>						
						<th>收费信息</th>
						<th>评价星级  </th>
						<th>发货</th>
					</tr>
				</table>
			</div>
		</div>
		<div class="clr"></div>
		<!-- 内容热门城市/内容最新发货单 -->
		<div class="city-dingdan">
			<div class="con3-bot2 f">
				<div class="con2-top1 f" style="width: 100%;">
					<p>热门城市</p>
				</div>
				<div class="con3-bottom1 con3-bot3 f">
				<div class="con3-top1">
					<div class="ico_remen f">
						<ul>
							<li>
								<img src="/logisticsc/resources/platform/img/index_par3_city1.png" />
							</li>
							<li>
								<img src="/logisticsc/resources/platform/img/index_par3_city2.png" />
							</li>
							<li>
								<img src="/logisticsc/resources/platform/img/index_par3_city3.png" />
							</li>
						</ul>
					</div>
					<div class="con3-con1 f" id="getHomeOrderCity">
					</div>
				</div>
			</div>
			</div>
			<div class="con3-right f">
				<div class="con2-left con3-xx f">
					<div class="con2-top1 f">
						<p>最新发货单</p>
					</div>
					<div id="last_order" class="con3-bottom2 f">
						<ul>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="clr"></div>
		<!--*****************************part4************************************-->		
		<div class="news">
			<div class="news-gs f">
				<div class="con2-top1 f">
					<p>公司新闻</p>
					<a href="/logisticsc/homeCenter/toNewsCompanyIndex.shtml"><p class="more more1">更多>></p></a>
				</div>
				<table class="table1" id="companyNews">
				</table>
			</div>
			<div class="news-gs news-zixun f">
					<div class="con2-top1 f">
					<p>物流资讯</p>
					<a href="/logisticsc/homeCenter/toNewsinfoIndex.shtml"><p class="more">更多>></p></a>
				</div>
				<table id="infoNews">
				</table>
			</div>
		</div>
		<!--*****************************合作伙伴************************************-->	
		<div class="partner1">
			<div class="partner1-con" id="getPartner">
			</div>
		</div>
		<!--*****************************part5************************************-->				
		<div class="bottom">
			<div class="bot-con">
				<div class="bot-top">
					<div class="bot-left f">
						<ul>
							<li>
								<div class="bot-pic1 f">
									<img src="/logisticsc/resources/platform/img/index_bot_pic1.png">
								</div>
								<div class="bot-duanluo f">
									<i>新手指南</i><br/>
									下单指南<br/>
									常见问题<br/>
									服务协议<br/>
								</div>
							</li>
							<li>
								<div class="bot-pic1 f">
									<img src="/logisticsc/resources/platform/img/index_bot_pic1.png">
								</div>
								<div class="bot-duanluo f">
									<i>服务支持</i><br/>
									保障体系<br/>
									风险提示<br/>
									安全协议<br/>
								</div>
							</li>
							<li>
								<div class="bot-pic1 f">
									<img src="/logisticsc/resources/platform/img/index_bot_pic1.png">
								</div>
								<div class="bot-duanluo f">
									<i>商品配送</i><br/>
									配送方式<br/>
									验货签收<br/>
									会员福利<br/>
								</div>
							</li>
							<li>
								<div class="bot-pic1 f">
									<img src="/logisticsc/resources/platform/img/index_bot_pic1.png">
								</div>
								<!--  
								<div class="bot-duanluo f">
									<i>关于中工储运</i><br/>
									联系我们<br/>
									招贤纳士<br/>
									公司证件<br/>
								</div>
								-->
							</li>
							<li>
								<div class="bot-pic1 f">
									<img src="/logisticsc/resources/platform/img/index_bot_pic1.png">
								</div>
								<!--  
								<div class="bot-duanluo f bot_phone">
									<img src="/logisticsc/resources/platform/img/index_bot_pic2.png" class="f;">
									18000510004
								</div>
								-->
							</li>
						</ul>
					</div>
					<div class="bot-right f">
						<div class="botr-top">
							<img src="/logisticsc/resources/platform/img/index_bot_pic9.png" class="f;" />
						</div>
						<div class="botr-bot">
							<img src="/logisticsc/resources/platform/img/index_bot_pic3.png" class="f;" />
							<img src="/logisticsc/resources/platform/img/index_bot_pic4.png" class="f;" />
							<img src="/logisticsc/resources/platform/img/index_bot_pic5.png" class="f;" />
						</div>
					</div>
				</div>
				<div class="bot-haoma">
					<div class="bot-line"></div>
					<div class="bot-copy">
						<span>
							 友情链接： 中国石油     |	中国石化   | 	安邦保险	| 	天安财险	|	中国联通	 |中国石油  |	中国石化 |	安邦保险	 |天安财险		
    | 中国建设银行
						</span>
						<img src="/logisticsc/resources/platform/img/index_bot_pic6.png" class="f bot-pic3" />
						<img src="/logisticsc/resources/platform/img/index_bot_pic8.png" class="f" />
					</div>
				</div>
			</div>
			<div class="bot-youlian f">
				  © Copyright 2013-2017 粤ICP备15048227号-1 中工储运 版权所有
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
								<input type="text"  style="display: inline-block; width: 250px; height: 34px;" class="form-control required" id="username" class="username" placeholder="请输入用户名"/>
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
								<input type="text" id="checkCode"  style="display: inline-block; width: 116px; height: 34px;" class="form-control required f" placeholder="请输入验证码"/>
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
<input type="hidden" id="userNames" value="${user_session.loginName}"/>
</body>
</html>