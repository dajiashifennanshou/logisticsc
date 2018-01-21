<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" /> 
<title>专线营运系统</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
    *{
        margin: 0px;
        padding: 0px;
    }
    html, body{
    	height: 100%;
    }
    a{
    	text-decoration: none;
    }
    .top {
        height: 80px;
        background-color: #46b8da;
        position: relative;
    }
    .wrap-menu {margin:0 auto; overflow:auto; background:#F6F6F6; font:12px/1.5 Tahoma,Arial,sans-serif}
	.wrap-menu ul{ list-style:none; margin:0; padding:0;}
	.wrap-menu ul li{ text-indent:3em; white-space:nowrap; }
	.wrap-menu ul li h2{ cursor:pointer; height:100%; width:100%; margin:0 0 1px 0; font:12px/31px '宋体'; color:#fff; background:red;}
	.wrap-menu ul li a{ display:block; outline:none; height:25px; line-height:25px; margin:1px 0; color:#1A385C; text-decoration:none;}
	.wrap-menu ul li img{ margin-right:10px; margin-left:-17px; margin-top:9px; width:7px; height:7px; background:url(${configProps['resources']}/tms/images/arrow.gif) no-repeat; border:none;}
	.wrap-menu ul li img.unfold{ background-position:0 -9px;}
	.wrap-menu ul li a:hover{ background-color:#ccc; background-image:none;}
	


a {
	text-decoration: none
}
.clearfix {
	clear: both
}
body {
	margin: 0
}
ul, li {
	margin: 0;
	padding: 0;
	list-style: none
}
.banner_box {
	background-image: url('${configProps['resources']}/tms/images/banner.png');
	height: 106px
}
.logo {
	width: 350px;
	margin-left: 25px;
	margin-top: 30px;
	float: left;
}
.logo img {
	float: left;
	margin-right: 5px
}
.tubiao {
	float: right
}
.biaoti {
	font-size: 24px;
	font-family: "微软雅黑";
	color: white
}
.english {
	font-size: 12px;
	font-family: arial;
	color: white
}
.login {
	width: 300px;
	position: absolute;
	left: 27%;
	margin-top: 20px;
	border-radius: 5px;
	height: 65px;
	background-image: url('${configProps['resources']}/tms/images/login_box.png');
}
.login img {
	float: left;
	padding-left: 40px;
	margin-top: 8px
}
.welcome_box {
	float: left;
	font-size: 14px;
	font-family: "微软雅黑";
	margin-top: 12px;
	margin-left: 10px;
	color: white
}
.welcome {
	line-height: 20px
}
.link {
	float: right;
	position: absolute;
	margin-top: 28px;
	left: 50%
}
.link a {
	color: white;
	font-size: 18px;
	font-family: "微软雅黑";
}
.daohang_box {
	float: right;
	margin-top: 25px;
	margin-right: 10px
}
.daohang {
	float: left;
	background-image: url('${configProps['resources']}/tms/images/zhuye.png');
	margin-right: 15px
}
.daohang:hover {
	background-image: url('${configProps['resources']}/tms/images/zhuye01.png')
}
.daohang01 {
	float: left;
	background-image: url('${configProps['resources']}/tms/images/shezhi.png');
	margin-right: 15px
}
.daohang01:hover {
	background-image: url('${configProps['resources']}/tms/images/shezhi01.png')
}
.daohang02 {
	float: left;
	background-image: url('${configProps['resources']}/tms/images/fangda.png');
	margin-right: 15px
}
.daohang02:hover {
	background-image: url('${configProps['resources']}/tms/images/fangda01.png')
}
.daohang03 {
	float: left;
	background-image: url('${configProps['resources']}/tms/images/tuichu.png');
	margin-right: 15px
}
.daohang03:hover {
	background-image: url('${configProps['resources']}/tms/images/tuichu01.png')
}
.loginbox {
	width: 290px;
	height: 315px;
	border: 1px solid #ededed;
	position: absolute;
	left: 30%;
	top: 15%;
	background-color: white
}
.loginbox input {
	width: 258px;
	height: 40px;
	margin-top: 32px;
	border: 1px solid #eaeaea;
	color: #b5b6b8;
	font-size: 14px;
	font-family: "微软雅黑"
}
.login01 {
	width: 260px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 11px
}
.login01 .words01 {
	line-height: 50px;
	color: #b5b6b8;
	font-size: 12px;
	font-family: "微软雅黑";
	float: left;
	margin-top: 50px
}
.login01 .words02 {
	line-height: 50px;
	color: #b5b6b8;
	font-size: 12px;
	font-family: "微软雅黑";
	float: right;
	margin-top: 50px
}
.login01 .zhuce {
	font-size: 12px;
	font-family: "宋体";
	color: #b5b6b8;
	margin-top: 45px;
	text-align: center
}
.login01 .dianji {
	color: #0194D9
}
.denglu {
	width: 259px;
	height: 41px;
	background-color: #0194D9;
	line-height: 41px;
	text-align: center;
	color: white;
	margin-top: 20px
}
/*
.banner img{ position:relative; left:50%; margin-left:-720px}*/
.nav_box {
	margin-top: 10px
}
.nav_box li {
	border: #e1e1e1 1px solid;
	width: 200px;
	font-size: 16px;
	font-family: "微软雅黑";
	color: #323232;
	line-height: 50px;
	text-align: center;
	position: relative;
}
.nav_box li img {
	position: absolute;
	top: 15px;
	left: 35px;
	border: none;
}
/*
.nav_box .current li{ border:#e1e1e1 1px solid; width:200px;font-size:14px; font-family:"微软雅黑"; color:#4e4d4d; line-height:30px; text-align:center}
*/
.nav_box .current {
	background-color: #0194D9;
	color: #FFF
}
.nav_box .before {
	background-color: none;
	color: #FFF
}
.nav_box li:hover {
	cursor: pointer;
}
.nav_box li a:hover {
	color: white;
	background-image: url('${configProps['resources']}/tms/images/xiabian.png')
}
.nav_box li ul a:hover {
	color: #18b5fc;
	/*background-image: url('${configProps['resources']}/tms/images/xiala_lan.png')*/
}
.nav_box li ul {
	display: none;
	background-color: #fff;
}
.nav_box li ul li {
	border: 0;
	text-align: left;
	display: block;
	width: 108px;
	/*background-image: url(images/zuobian.png);*/
	background-repeat: no-repeat;
	background-position: right;
	color: #323232;
}
.nav_box li a {
	display: block;
	width: 108px;
	/*background-image: url('${configProps['resources']}/tms/images/zuobian.png');*/
	background-repeat: no-repeat;
	background-position: right;
	color: #323232;
	text-align: left;
	margin-left: 70px;
}
.nav_box .xiala a {
	display: block;
	width: 180px;
	/*background-image: url('${configProps['resources']}/tms/images/xiala_hui.png');*/
	background-repeat: no-repeat;
	color: #323232;
	background-position: left;
	background-position: 40px center;
	text-align: center;
	margin-left: 20px
}
.div_person{
	display:none;
}
.div_person li:hover{
	cursor:pointer;
	background:#4682B4
}


</style>
<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
<script type="text/javascript" src="${configProps['resources']}/tms/js/zzsc.js"></script>
</head>
<body>
	
    <table width="100%" height="100%">
    	<tr>
    		<td colspan="2">
    			<%-- <div class="top">
			        <h1>专线营运系统</h1>
			        <a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" style="position: absolute; right: 10px; top: 10px;">货运交易系统</a>
			        <div style="position: absolute; right: 20px; bottom: 20px;">
				    	<span>当前网点</span>
				    	${outletsInfo.name}
				    	<c:if test="${not empty outletsInfos}">
				    		<select onchange="setDefaultOutletsInfo(this)">
				    			<option value="">全部</option>
				    			<c:forEach items="${outletsInfos}" var="outletsInfo">
				    				<option value="${outletsInfo.id}">${outletsInfo.name}</option>
				    			</c:forEach>
					    	</select>
				    	</c:if>
				    </div>
			    </div> --%>
			    <div class="banner_box">
				   <div  class="logo">
				     <%--  <img src="${configProps['resources']}/tms/images/logo (2).png"> --%>

                         <div class="biaoti_box">
                              <div class="biaoti">云·专线营运系统</div>
                          <!--<div class="english">Logistics management system</div>-->
                          </div>
                       </div>

                         <div class="login" id="per_items">
                           <img src="${configProps['resources']}/tms/images/touxiang.png">
                           <div class="welcome_box">
                           <div class="welcome">你好,
                                <c:if test="${empty tms_user_session.trueName}">
				       			${tms_user_session.loginName}
				       		</c:if>
				       		<c:if test="${not empty tms_user_session.trueName}">
				       			${tms_user_session.trueName}
				       		</c:if>
				       </div>
				       <div class="welcome">欢迎使用专线营运系统</div>
				       </div>
				       <div class="clearfix"></div>
				       <!-- <div class="div_person" style="width:250px;height:100px;background:white;position:relative;left:35px;top:-15px;box-shadow:0px 0px 1px green;">
				       		<div style="margin:20px">
				       			<ul>
				       				<li><span style="line-height:30px;display:block" onclick="updatePwd()">密码修改</span></li>
				       				<li><span style="line-height:30px;display:block" onclick="exitLogin()">退出登录</span></li>
				       			</ul>
				       		</div>
				       </div> -->
				    </div>
				     
				    <div class="link">
				        <img src="${configProps['resources']}/tms/images/tubiao.png">

				       	<span style="color: #fff;">当前网点</span>
				       	<span style="color: #fff;">
				       		${outletsInfo.name}
				       		<c:if test="${sessionScope.tms_user_session.userType == 1 || sessionScope.tms_user_session.userType == 0 }">
						    	<c:if test="${not empty sessionScope.userType}">
						    		<select onchange="setDefaultOutletsInfo(this)" style="color:black">
						    			<option value="">全部</option>
						    			<c:forEach items="${outletsInfos}" var="outletsInfo">
						    				<c:choose>
							    				<c:when test="${not empty sessionScope.tms_user_session.outletsId }">
							    					<c:choose>
							    						<c:when test="${sessionScope.tms_user_session.outletsId == outletsInfo.id}">
							    							<option value="${outletsInfo.id}" selected>${outletsInfo.name}</option>
							    						</c:when>
							    						<c:otherwise>
							    							<option value="${outletsInfo.id}">${outletsInfo.name}</option>
							    						</c:otherwise>
							    					</c:choose>
							    				</c:when>
							    				<c:otherwise>
							    					<option value="${outletsInfo.id}">${outletsInfo.name}</option>
							    				</c:otherwise>
						    				</c:choose>
						    			</c:forEach>
							    	</select>
						    	</c:if>
					    	</c:if>
				       	</span>
				    </div>
				    
				    <div class="daohang_box">
						<%--<div class="daohang">
                            <a href="<%=request.getContextPath()%>/deliverGoods/deliverGoods.shtml" target="_self"><img src="${configProps['resources']}/tms/images/zhuye.png" title="前往货运交易系统"></a>
                        </div> --%>
				    <%-- <div class="daohang01"> 
				        <img src="${configProps['resources']}/tms/images/shezhi.png">
				    </div>
				    <div class="daohang02"> 
				        <img src="${configProps['resources']}/tms/images/fangda.png">
				    </div> --%>
				    <div class="daohang03"> 
				        <img onclick="exitLogin()" src="${configProps['resources']}/tms/images/tuichu.png" title="退出并返回到货运交易系统">
				    </div>
				   
				    </div>
				    <div class="clearfix"></div>
				 </div>
    		</td>
    	</tr>
    	<tr>
    		<td width="219px" valign="top" style="height: 100%;">
    			<!-- <div class="wrap-menu"></div> -->
    			<div style="height: 100%; overflow: auto;">
    				<ul class="nav_box" id="J_nav"></ul>
    			</div>
    		</td>
    		<td valign="top">
    			<iframe name="tmsContent" width="100%" height="100%" frameborder="0" src="<%=request.getContextPath()%>/tms/tofirstpage.shtml"></iframe>
    		</td>
    	</tr>
    </table>
    <div id="hidden_dia" class="hide">
	    <form id="update_pwd" class="form-horizontal">
	      	<div class="control-group">
	        	<label class="control-label"><s>*</s>账号</label>
				<div class="controls">
		          <input id="phone" name="phone" type="text" data-rules="{required:true,checkUser:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" 
		          		data-messages="{regexp:'手机号不正确'}" placeholder="输入11位手机号">
		        </div>
	     	</div>
	     	<div class="control-group">
	        	<label class="control-label"><s>*</s>旧密码</label>
				<div class="controls">
		          <input id="oldpassword" name="oldpassword" type="password" data-rules="{required:true}">
		        </div>
	     	</div>
	     	<div class="control-group">
	        	<label class="control-label"><s>*</s>新密码</label>
				<div class="controls">
		          <input id="newpassword" name="newpassword" type="password" data-rules="{required:true,verifyPwd:true}">
		        </div>
	     	</div>
	     	<div class="control-group">
	        	<label class="control-label"><s>*</s>确认新密码</label>
				<div class="controls">
		          <input id="repassword" name="repassword" type="password" data-rules="{required:true,verifyPwd:true}">
		        </div>
	     	</div>
	     </form>
	</div>
    <input id="baseUrl" type="hidden" value="<%=request.getContextPath() %>"/>
    <input id="imgBaseUrl" type="hidden" value="${configProps['resources']}"/>
    <script>
    	$(function(){
    		var menuList = ${menuList };
    		createMenu($("#J_nav"),menuList);
    	});
    	
		function modifyMenu(res,des){
			for(var i=0,j=res.length;i<j;i++){
				var resMenu = res[i];
				if(resMenu.parentId==des.id){
					var d = {}
					/* alert(JSON.stringify(resMenu)) */
					d.name=resMenu.menuName;
					d.url = resMenu.menuUrl;
					/* alert(JSON.stringify(d)) */
					des.subMenu.push(des)
					modifyMenu(resMenu,des);
				}
				
			}
		}
	
	// 专线公司账号  设置默认网点
	function setDefaultOutletsInfo(obj){
		window.location.href = '<%=request.getContextPath()%>/tms/setdefaultoutletsinfo.shtml?outletsId='+$(obj).val();
		<%-- $.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/setdefaultoutletsinfo.shtml',
			data : { 'outletsId' : obj.value },
			seccess : function(data){
				
			}
		}); --%>
	}
	//创建菜单
	function createMenu(nav,menuList){
		var baseUrl = $("#baseUrl").val();
		var imgBaseUrl = $('#imgBaseUrl').val();
		for(var i = 0;i<menuList.length;i++){
			var menu = menuList[i];
				if(menu.subMenus.length != 0){
					var leftNode = "<li><a href='javascript:void(0)'><img src='"+imgBaseUrl+menu.menuIcon+"'>"+menu.menuName+"</a><ul class='xiala'>";
					for(var j = 0;j<menu.subMenus.length;j++){
						var childNode = '<li><a href="'+baseUrl+menu.subMenus[j].menuUrl+'" target="tmsContent">'+menu.subMenus[j].menuName+'</a></li>';
						leftNode += childNode;
						//createMenu($("#ul"+time),menu.subMenus);
					}
					nav.append(leftNode + '</ul></li>');
				}else{
					var leftNode = "<li><a href='"+baseUrl+menu.menuUrl+"' target='tmsContent'><img src='"+imgBaseUrl+menu.menuIcon+"'>"+menu.menuName+"</a></li>"
					nav.append(leftNode);
				}
		}
	}
	
	window.onload=function(){
		/****************8
		二级菜单
		    ********************/
		  var navList=document.getElementById("J_nav").children;
		 // var navScend=nav.children;
		  //alert(navList[0].firstChild);
		    
		    for(var i=0;i<navList.length;i++){
		        navList[i].setAttribute("value","0");
		    }
		    for(var i=0;i<navList.length;i++){
		        navList[i].onmouseover=function(){
		            this.setAttribute("class","current")
		        };
		        navList[i].onmouseout=function(){
		            this.setAttribute("class","")
		        };
		        //给A标签添加单价事件
		       // alert(navList[i].firstChild.nodeName);
		        navList[i].firstChild.onclick= function(){
		        if(!this.parentNode.children[1].nodeName=="UL")return false;
		            if(this.parentNode.getAttribute("value")=="1"){		         
		                this.parentNode.onmouseout=function(){
		                    this.setAttribute("class","");		    
		                };
		                //  alert(this.getElementsByTagName("ul")[0].nodeName);
		               this.parentNode.children[1].style.display="none";
		               this.parentNode.setAttribute("value","0");
		            }
		            else{
		            	$(".xiala").css("display","none");	
		            	for(var i=0;i<navList.length;i++){
		            	 navList[i].onmouseout=function(){
		  		            this.setAttribute("class","")
		  		        	};
		  		        	navList[i].setAttribute("value","0");
		  		        }
		                this.parentNode.setAttribute("class","current");
		                this.parentNode.onmouseout=function(){
		                    this.setAttribute("class","current");
		                };
		                this.parentNode.children[1].style.display="block";
		                this.parentNode.setAttribute("value","1");
		                var oj = $(this).parent();		 
		                oj.siblings().removeClass("current");
		               // oj.siblings().get(0).children[1].style.display="none";
		                oj.siblings().val("0");
		                oj = null;
		            }
		        }
		    }
		};
		
		/* $(".login").mouseover(function(){
			$(".div_person").css("display","block");
			$(".div_person").focus();
		})
		
		$(".div_person").blur(function(){
			$(".div_person").css("display","none");
		}) */
		
		$("#per_items").mouseover(function(){
			$(".div_person").css("display","block");
		})
		
		$("#per_items").mouseout(function(){
			$(".div_person").css("display","none");
		})
		
		var message,
			dialog;
		BUI.use(['bui/form','bui/overlay'],function(Form,Overlay){
			message = BUI.Message;
			
			var form = new Form.Form({
	        	srcNode:"#update_pwd"
	        }).render();
			
			dialog = new Overlay.Dialog({
	            title:'密码修改 ',
	            width:450,
	            height:300,
	            //配置DOM容器的编号
	            contentId:'hidden_dia',
	            success:function () {
	    		  	form.valid();
	    		  	if(form.isValid()){
	    		  		var data = $("#update_pwd").serialize();
	    		  		$.ajax({
	    		  			url:'<%=request.getContextPath()%>/tms/user/updatePwd.shtml',
	    		  			data:data,
	    		  			type:'post',
	    		  			success:function(result){
	    		  				if(result.result){
	    		  					message.Alert("修改成功",function(){
	    		  						dialog.close();
	    		  					},"success")
	    		  				}else{
	    		  					message.Alert(result.msg,"warning");
	    		  				}
	    		  			},
	    		  			error:function(){
	    		  				message.Alert("网络错误");
	    		  			}
	    		  		})
	    		  	}
	            }
	        });
			
			dialog.on("closed",function(){
				$("#update_pwd input").val('');
			})
			//验证两次密码输入是否一致
			Form.Rules.add({
		      	name : 'verifyPwd',  //规则名称
		        msg : '两次密码不一致',//默认显示的错误信息
		        validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息
		          	var password = $("#newpassword").val();
		        	if(!verifyPwdEql(password,value)){
		            	return formatMsg;
		          	}
		        }
		 	});
			
        });
		
		//验证两次密码是否一致
		function verifyPwdEql(pwd,repwd){
			var flag = false;
			if(pwd != null 
					&& pwd != ''
					&& repwd != null 
					&& repwd != ''){
				if(pwd === repwd){
					flag = true;
				}
			}
			return flag;
		}
		function updatePwd(){
			dialog.show();
		}
		
		function exitLogin(){
			window.location.href = '/logisticsc/tms/logout.shtml';
			/* message.Alert("sadf","success")
			message.confirm("确定要退出？",function(){
				
			}) */
			
		}
		
		$(".loginbox").hide();
		$(".login").click(
		    function(){
			    $(".loginbox").toggle()
		    }
		);
		
		
		
		
		
		// 退出系统
		/* function exit(){
			window.location.href = '/logisticsc/user/outUser.shtml';
		} */
	</script>
</body>
</html>