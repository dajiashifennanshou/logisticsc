<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<title>帮助中心</title>
<style type="text/css">
body{font-size: 12px;font-family: "宋体","微软雅黑";}
ul,li{list-style: none;}
a:link,a:visited{text-decoration: none;}
.list{width: 210px;border-bottom:solid 1px #316a91;}
.list ul li{background-color:#467ca2; border:solid 1px #316a91; border-bottom:0;}
.list ul li a{padding-left: 10px;color: #fff; font-size:12px; display: block; font-weight:bold; height:36px;line-height: 36px;position: relative;
}
.list ul li .inactive{ background:url(/logisticsc/resources/platform/img/off.png) no-repeat 184px center;}
.list ul li .inactives{background:url(/logisticsc/resources/platform/img/on.png) no-repeat 184px center;} 
.list ul li ul{display: none;}
.list ul li ul li { border-left:0; border-right:0; background-color:#6196bb; border-color:#467ca2;}
.list ul li ul li ul{display: none;}
.list ul li ul li a{ padding-left:20px;}
.list ul li ul li ul li { background-color:#d6e6f1; border-color:#6196bb; }
.last{ background-color:#d6e6f1; border-color:#6196bb; }
.list ul li ul li ul li a{ color:#316a91; padding-left:30px;}
</style>
<script type="text/javascript">
$(function(){
	 getHelp();
	 if($("#helpId").val() != null && $("#helpId").val().length>0){
		 getHelpContent($("#helpId").val());
	 }
})	
	function getHelpContent(id) {
	$.ajax({
		url:"/logisticsc/helpCenter/getHelpContent.shtml",
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(dataResult){
			if(dataResult.result==true){
				$("#getHelpContent").html(dataResult.data.content);
			}else{
				
			}
		},
		error:function(data){
		}
	})
	}
    function getHelp(){
    	$.ajax({
    		url:"/logisticsc/helpCenter/getHelp.shtml",
    		type:'post',
    		dataType:'json',
    		success:function(dataResult){
    			if(dataResult.result==true){
    				var data = dataResult.data;
    				var body="<ul class='yiji'>";
    				for (var i = 0; i < data.length; i++) {
    					var dataHelps = data[i].helps;
    					if(dataHelps.length > 0 ){
    						body+="<li><a mode='a' href='#' class='inactive'>"+data[i].helpName+"</a>"
    							+"<ul style='display: block'>"
    					}else{
    						body+="<li onclick='getHelpContent("+data[i].id+")'><a href='#' mode='a'>"+data[i].helpName+"</a>"
    					} 
    					for(var j = 0; j < dataHelps.length; j++) {
    						var dataHelpss=dataHelps[j].helps;
    						if(dataHelpss.length > 0){
    							body+="<li><a href='#' class='inactive active'>"+dataHelps[j].helpName+"</a>"
    								+"<ul style='display: block'>"
    						}else{
    							body+="<li onclick='getHelpContent("+dataHelps[j].id+")'><a href='#'>"+dataHelps[j].helpName+"</a>"
    						}
    						for(var k = 0; k < dataHelpss.length; k++) {
    							body+="<li onclick='getHelpContent("+dataHelpss[k].id+")'><a href='#'>"+dataHelpss[k].helpName+"</a></li>"
    						}
    						if(dataHelpss.length > 0){
    							body+="</ul></li>";
    						}else{
    							body+="</li>";
    						}
    						
    					}
    					if(dataHelps.length > 0 ){
    						body+="</ul></li>";
    					}else{
    						body+="</li>";
    					}	
    				}
    				body+="</ul>";
    				$("#getHelp").html(body);
    				controlTree();
    			}else{
    			}
    		},
    		error:function(data){
    		}
    	})
    }
    function controlTree(){
    	$('.inactive').click(function(){
    		if($(this).siblings('ul').css('display')=='none'){
    			$(this).parent('li').siblings('li').removeClass('inactives');
    			$(this).addClass('inactives');
    			$(this).siblings('ul').slideDown(100).children('li');
    			if($(this).parents('li').siblings('li').children('ul').css('display')=='block'){
    				$(this).parents('li').siblings('li').children('ul').parent('li').children('a').removeClass('inactives');
    				$(this).parents('li').siblings('li').children('ul').slideUp(100);
    			}
    		}else{
    			//控制自身变成+号
    			$(this).removeClass('inactives');
    			//控制自身菜单下子菜单隐藏
    			$(this).siblings('ul').slideUp(100);
    			//控制自身子菜单变成+号
    			$(this).siblings('ul').children('li').children('ul').parent('li').children('a').addClass('inactives');
    			//控制自身菜单下子菜单隐藏
    			$(this).siblings('ul').children('li').children('ul').slideUp(100);
    			//控制同级菜单只保持一个是展开的（-号显示）
    			$(this).siblings('ul').children('li').children('a').removeClass('inactives');
    		}
    	})
    }
</script>
</head>
<body>
<div id="container">
	<jsp:include page="../top.jsp"></jsp:include>
	<input id="helpId" type="hidden" value='${helpId}'/>
		<div id="content">
			<div class="news-container" style="width: 1200px;margin:0px auto;margin-top:20px;">
				<div class="list f" id="getHelp" >
				</div>
				<div id="getHelpContent" style="float: left;margin-left: 20px;width: 900px;">
				</div>
			</div>
		</div>
</div>	
<div class="footer">
		<div style="clear: both;"></div>
		<jsp:include page="../bottom.jsp"></jsp:include>
	</div>		
</body>
</html>