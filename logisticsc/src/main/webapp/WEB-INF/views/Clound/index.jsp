<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>云仓管理系统</title>
		<link rel="stylesheet" href="/logisticsc/Clound/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="/logisticsc/Clound/assets/css/font-awesome.css" />
		<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ace-fonts.css" />
		<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ace.css"/>
		<link href="/logisticsc/Clound/ligerui/skins/Aqua/css/ligerui-menu.css" rel="stylesheet" type="text/css" /> 
		<link href="/logisticsc/Clound/ligerui/skins/Aqua/css/ligerui-tab.css" rel="stylesheet" type="text/css" /> 
		<script src="/logisticsc/Clound/js/jquery-2.2.4.min.js"></script>
		<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
		<script src="/logisticsc/Clound/assets/js/bootstrap.js"></script>
		<script src="/logisticsc/Clound/assets/js/ace/ace.js"></script>
		<script src="/logisticsc/Clound/assets/js/ace/ace.sidebar.js"></script>
	     <script src="/logisticsc/Clound/ligerui/js/ligerui.all.js" type="text/javascript"></script> 
	    <script src="/logisticsc/Clound/js/index/index.js"></script>
	   <!--  <script src="/logisticsc/Clound/DateUtil.js" type="text/javascript"></script>  -->
	</head>
	<body class="no-skin">
		<div id="navbar" class="navbar navbar-default">
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a  href="#" class="navbar-brand">
						<small>
							<!--<i class="fa fa-leaf"></i>-->
							云仓管理系统  ：${branchName}
						</small>
					</a>
				</div>
				<div  class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="/logisticsc/Clound/assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									${user.realname }
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>
							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<!--<li>
									<a href="javascript:;">
										<i class="ace-icon fa fa-cog"></i>
										设置
									</a>
								</li>
								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										资料
									</a>
								</li>
								<li class="divider"></li>-->
								<li>
									<a href="exitLogin.yc">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="main-container" id="main-container">
			<div class="sidebar responsive">

				<ul class="nav nav-list" id="loadMeau_1">
					<li class="active">
						<a href="javascript:;" onclick="meauClick(this),f_addTab('home', '云仓管理系统', 'yc-main.yc');">
							<!--<i class="menu-icon fa fa-tachometer"></i>-->
							<span class="menu-text"> 云仓管理系统 </span>
						</a>
						<b class="arrow"></b>
					</li>
					<!-- 内容 -->
				</ul>
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
			</div>
			<div class="main-content">
				<div id="framecenter"> 
		            <div tabid="home" title="云仓管理系统">
		                <iframe frameborder="0" style="width: 100%;height: 100%" name="home" id="home" src="yc-main.yc"></iframe>
		            </div> 
		        </div> 
		        <div class="footer">
					<div class="footer-inner">
						<div class="footer-content">
							<span class="bigger-120">
								中工储运 版权所有 &copy; 2013-2017  粤ICP备15048227号-1
							</span>

						</div>
					</div>
				</div>
	        </div>
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>
	</body>
</html>
