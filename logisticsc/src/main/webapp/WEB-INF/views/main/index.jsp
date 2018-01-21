<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" />
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<div class="header">
	<div class="dl-title">
		<a href="#"> <span class="lp-title-port"></span><span
			class="dl-title-text">后台管理系统</span>
		</a>
	</div>

	<div class="dl-log">
		<img class="toux" src="${configProps['resources']}/img/user-pic.jpg" /> <span
			class="dl-log-user">${user.username}</span> <img class="dl-down"
			src="${configProps['resources']}/newbui/img/down.png" />
	</div>
	<div class="nav-right">
		<!-- <a class="btn search"> <i class="fa fa-search"></i>
		</a>  -->
		<a class="btn arrows-alt"> <i class="fa fa-arrows-alt"></i>
		</a>
		<!--  <div class="dropdown">
			<a title="" href="#" class="header-btn"> <span
				class="small-badge bg-yellow"></span> <i class="fa fa-bullhorn"></i>
			</a>
		</div> -->
		<!-- <div class="dropdown">
			<a title="" href="#" class="header-btn"> <span
				class="small-badge bg-azure"></span> <i class="fa fa-cog"></i>
			</a>
		</div>  -->
		  <div style="text-align:center;" class="dropdown">
                <a href="javascript:void(0)" onclick="existSystem()" title="退出系统" class="logout dl-log-quit"><i class="fa fa-power-off fa-fontsize"></i>
                 </a> 
          </div>
	</div>

</div>


<div class="name-info">
	<div class="user-img">

		<img class="toux" width="80" height="80"
			src="${configProps['resources']}/img/user-pic.jpg" />
	</div>
	<div class="user-info">
		<span>${user.username}</span> <br /> <i></i>
	</div>
	<div class="divider"></div>
	<!-- <a href="javascript:void(0)" onclick="editFunction()" title="修改密码"
		class="pwd dl-log-quit">[修改密码]</a>  -->
		<a href="javascript:void(0)"
		onclick="existSystem()" title="退出系统" class="logout dl-log-quit">[退出系统]</a>
</div>




<div class="content">

	<div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title">贴心小秘书<s class="dl-inform-icon dl-up"></s></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear"> 
        
      </ul>
    </div> 
	<ul id="J_NavContent" class="dl-tab-conten">

	</ul>

</div>

<!-- 初始隐藏 dialog内容 -->
<div id="content" class="hide">
	<form class="form-horizontal" id="J_Form">
		<input id="id" type="hidden" value="9232dfe7d2f4483bafe852744239b981">
		<div class="row">
			<div class="control-group span8">
				<label class="control-label"><s>*</s>旧密码：</label>
				<div class="controls">
					<input name="oldpassword" id="oldpassword" type="password"
						data-rules="{required:true}" class="input-normal control-text">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="control-group span8">
				<label class="control-label"><s>*</s>新密码：</label>
				<div class="controls">
					<input name="stupassword" id="stupassword" type="password"
						data-rules="{required:true}" class="input-normal control-text">
				</div>
			</div>
			<div class="control-group span8">
				<label class="control-label"><s>*</s>确认新密码：</label>
				<div class="controls">
					<input id="newstupassword" data-rules="{equalTo:'#stupassword'}"
						type="password" class="input-normal control-text">
				</div>
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
	BUI.use('common/main', function() {
        
		var project="${configProps['project']}";
		var datas = [];
		var topmenu = {};
		var menuList = ${menuList};
		$.each(menuList, function(index, rowdata) {
			var main;
			if(index==0){
				main = '<li class="nav-item dl-selected"><div class="nav-item-inner nav-home">'+rowdata.menuname+'</div></li>';
			}else{
				main = '<li class="nav-item"><div class="nav-item-inner nav-inventory">'+rowdata.menuname+'</div></li>';
			}
			
			$("#J_Nav").append(main); 
			topmenu = {};
			
			topmenu.id=rowdata.id;
			topmenu.menu = getMenu(rowdata.subMenus);
			datas.push(topmenu);
			
			var tr="";

		});
		//console.log(JSON.stringify(datas));
		//alert();
		
		function getMenu(obj) {
			if (!obj)
				return [];
			var list = JSON.parse(JSON.stringify(obj));
			
			var data = [];
			var menu = {};
			
			var items = [];
			$.each(list, function(index, rowdata) {
				menu = {}; 
				menu.collapsed = true;
				menu.text=rowdata.menuname;
				menu.items = getItem(rowdata.subMenus); 
				 
				data.push(menu);
			});
			//menu.items = items;
			return data;
		}
		
		function getItem(obj) {

			if (!obj)
				return [];
			var list = JSON.parse(JSON.stringify(obj));
			
			var data = [];
			var menu = {};
		
			$.each(list, function(index, rowdata) {
				menu = {};
				menu.id = rowdata.id+'';
				menu.text = rowdata.menuname;
				menu.href = project + rowdata.menuUrl + '';
				data.push(menu);
			});
			//menu.items = items;
			return data;
		}

		new PageUtil.MainPage({
			modulesConfig : datas
		});
		
	});
</script>
<script type="text/javascript">
	/* 
	$(function(){
		
	  $(".dl-log").click(function(){
			$(".name-info").toggle();
		});
		 
	}); */

	var myDiv = $(".name-info");
	$(function() {
		$(".dl-log").click(function(event) {
			showDiv();//调用显示DIV方法
			$(document).one("click", function() {//对document绑定一个影藏Div方法
				$(myDiv).hide();
			});

			event.stopPropagation();//阻止事件向上冒泡
		});
		/* $(myDiv).click(function (event) {
		
		event.stopPropagation();//阻止事件向上冒泡
		});  */
		$(".arrows-alt").click(function() {
			System.fullScreen();
		});
	});
	function showDiv() {
		$(myDiv).toggle();
	}

	// 退出系统
	function existSystem() {
		location.href = "${configProps['project']}/exitsystem.shtml";
		
		
		/* BUI.Message.Confirm('确认要退出吗？', function() {
			$.ajax({
				type : 'get',
				url : "${configProps['project']}/exitsystem.shtml",
				cache : false,
				async : false,
				success : function() {
					location.href = "${configProps['project']}/manage/login.shtml";
				}
			});
		}, 'question'); */
	}

	//修改密码
	function editFunction() {
		var dialog = new BUI.Overlay.Dialog({
			title : '修改密码',
			width : 400,
			height : 240,
			closeAction : 'destroy', //每次关闭dialog释放
			contentId : 'content',
			success : function() {
				var id = $('#id').val();
				var oldpassword = $('#oldpassword').val();
				if (oldpassword == "") {
					return;
				}
				var stupassword = $('#stupassword').val();
				if (stupassword == "") {
					return;
				}
				var newstupassword = $('#newstupassword').val();
				if (newstupassword == "") {
					return;
				}

				$.post('/updateUserPassword.shtml', {
					id : id,
					oldpassword : oldpassword,
					stupassword : stupassword
				}, function(data) {
					if (data.result) {
						BUI.Message.Alert('修改成功！', 'success');
					} else {
						BUI.Message.Alert(data.msg, 'error');
					}
				}, 'json');
				this.close();
			}
		});
		dialog.show();
	}
</script>

<script type="text/javascript">
	BUI.use('bui/form', function(Form) {

		var form = new Form.HForm({
			srcNode : '#J_Form'
		}).render();

	});
</script>

</body>