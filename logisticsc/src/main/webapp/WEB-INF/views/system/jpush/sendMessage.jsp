<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>极光推送消息</title>
<link href="" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
	.jpush_content{border: 1px solid #ccc;border-radius:5px;width: 100%;}
	.jpush_content textarea{
       border-radius: 5px;
	   margin: 10px 10px 0px 10px;
	   height: 90px;
	   width: 96.5%;
	   resize: none;
	}
	.jpush_content_div{margin-right:10px;text-align: right;}
	.jpush{margin-top: 13px;width: 100%}
	/*tab*/
	.jpush_object_tab{margin: 10px 10px 10px 10px;width: 98%;}
	.platform{border-radius: 5px;width: 100px;height: 100px;margin-top: 10px;border: 1px solid #ccc;}
	.button-normal,.jpush_object_tips{margin-top: 10px;}
	.time_finish{margin-left: 20px;display: none;}
	#chk{display: inline-block;vertical-align: middle}
	#timing{display: none;}
	.send_time{margin-top: 10px;}
	#submitBtn{background-color: #428bca;}
</style>
</head>
<body>
<div class="panel">
        <div class="panel-header">
           	<h2>平台信息发布</h2>
        </div>
        <div class="panel-body">
        	<form id="sendMsg"  class="form-horizontal">
        		<div>
	        		<strong>推送内容</strong>
	        		<div class=jpush_content>
	        			<textarea id="content" name="content" onkeyup="inputSize();"></textarea>
	        			<div class="jpush_content_div">您还剩下<span id="jpush_content_span" style="font-size: 20px;">240</span>个汉字可以输入</div>
	        		</div>
        		</div>
        		<div class="jpush">
	        		<strong>推送对象</strong>
	        		<div class=jpush_content>
	        			<input type="hidden" id="platform" name="platform" value="all"/>
	        			<table class="jpush_object_tab">
	        				<tr><td colspan="3" style="border-bottom:1px dashed #d5d5d5;"><strong>目标平台</strong></td></tr>
	        				<tr>
	        					<td width="150px">
		        					<a href="javascript:void(0)" onclick='selPlatform("all");'>
		        						<img class="platform" src="/logisticsc/resources/img/all.png"/>
		        					</a>
	        					</td>
	        					<td width="150px">
		        					<a href="javascript:void(0)" onclick='selPlatform("ios");'>
		        						<img class="platform" src="/logisticsc/resources/img/ios.png"/>
		        					</a>
	        					</td>
	        					<td>
		        					<a href="javascript:void(0)" onclick='selPlatform("android");'>
		        						<img class="platform" src="/logisticsc/resources/img/android.png"/>
		        					</a>
	        					</td>
	        				</tr>
	        			</table>
	        			<input type="hidden" name="target" id="target" value="all">
	        			<table class="jpush_object_tab">
	        				<tr>
	        					<td colspan="4" style="border-bottom:1px dashed #d5d5d5;"><strong>目标人群</strong></td>
	        				</tr>
	        				<tr>
	        					<td width="150px">
		        					<button type="button" class="button button-normal" onclick="selectTarget('all');">平台所有用户</button>
	        					</td>
	        					<td width="150px">
		        					<button type="button" class="button button-normal" onclick="selectTarget('');">Registration ID</button>
	        					</td>
	        					<td width="150px">
		        					<button type="button" class="button button-normal" onclick="selectTarget('');">设备标签(tag)</button>
	        					</td>
	        					<td>
		        					<button type="button" class="button button-normal" onclick="selectTarget('');">设备别名(Alias)</button>
	        					</td>
	        				</tr>
	        				<tr>
	        					<td colspan="4">
	        						<div class="jpush_object_tips">将向应用所有的注册用户推送此消息</div>
	        					</td>
	        				</tr>
	        			</table>
	        		</div>
        		</div>
        		<div class="jpush">
	        		<strong>发送时间</strong>
	        		<div class=jpush_content>
	        			<input type="hidden" name="sendType" value="0"/><!-- 发送类型  0为立即发送，1定时发送-->
	        			<table class="jpush_object_tab">
	        				<tr>
	        					<td width="150px">
		        					<button type="button" class="button button-normal" onclick="immediatelySend();">立即发送</button>
	        					</td>
	        					<td>
		        					<button type="button" class="button button-normal" onclick="timingSend();">定时发送</button>
	        					</td>
	        				</tr>
	        				<tr id="timing">
	        					<td colspan="2">
	        						<div class="jpush_object_tips">
	        							<div class="send_time">发送时间：<input onchange="timeChange(this.value)" id="sendTime" name="sendTime" type="text" class="calendar calendar-time"/></div>
	        						</div>
	        					</td>
	        				</tr>
	        				<tr>
	        					<td colspan="2">
	        						<div class="jpush_object_tips">
	        							<div>是否启用定速推送：&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="chk" name="chk" onclick="isConstantSpeed();"></div>
	        							<div class="time_finish">消息将分布在<input id="timeFinish" name="timeFinish" type="text" value="0" placeholder="1~1440分钟">分钟内完成</div>
	        						</div>
	        					</td>
	        				</tr>
	        			</table>
	        		</div>
        		</div>
        		<div class="jpush">
        			<input type="button" class="button button-normal" id="submitBtn" onclick="sendMsg();" value="立即推送">
        			<!-- <button class="button button-normal" id="submitBtn" onclick="sendMsg();"></button> -->
        		</div>
        	</form>
       	</div>
    </div>
    <script type="text/javascript" src="/logisticsc/resources/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="/logisticsc/resources/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
	function sendMsg(){
		$("#sendMsg").ajaxSubmit({
			  	url:'<%=request.getContextPath()%>/system/jpush/web/sendMessage.shtml',
			  	type:'post',
			  	dataType:'json',
			  	headers : {"ClientCallMode" : "ajax"},
				success:function(data){
					if (data.code == "10000") {
						BUI.Message.Alert("发送成功！！！","success");
				}else{
					BUI.Message.Alert("发送失败！！！","error");
				}
     	},
     	error:function(){
     		BUI.Message.Alert("发送失败！！！","error");
     	}});
	}
	//输入大小
	function inputSize(){
		var max = 240;
		var content = document.getElementById("content");
		if(content.value.length>max){
			content.value = content.value.substring(0,max);
		}else{
			var size = max-content.value.length;
			document.getElementById('jpush_content_span').innerHTML=size;
		}
	}
	//选择推送平台
	function selPlatform(val){
		$("input[name=platform]").val(val);
	}
	//选择目标人群（目前只推送全部）
	function selectTarget(val){
		if(val=="all"){
			$("input[name=target]").val(val);
		}else{
			BUI.Message.Alert("该功能暂时不能 用","error");
		}
	}
	//是否定速发送
	function isConstantSpeed(){
		if(document.getElementById("chk").checked==true){
			$(".time_finish").show();
		}else{
			$(".time_finish").hide();
		}
	}
	//立即发送
	function immediatelySend(){
		$("input[name=sendType]").val(0);
		$("#submitBtn").attr("disabled",false);
		document.getElementById("submitBtn").innerHTML="立即推送"
		$("#timing").hide();
	}
	//定时发送
	function timingSend(){
		$("input[name=sendType]").val(1);
		$("#submitBtn").attr("disabled",true);
		document.getElementById("submitBtn").innerHTML="定时推送"
		$("#timing").show();
	}
	//日期加载
	BUI.use('bui/calendar',function(Calendar){
        var datepicker = new Calendar.DatePicker({
        	trigger:'#sendTime',
        	showTime : true,
        	minDate : new Date(),
            dateMask : 'yyyy-mm-dd HH:MM:ss',
        	autoRender : true
    	});
    });
	//时间验证
	function timeChange(time){
		var date = new Date().getTime();//当前时间
		var sendTime = new Date(time).getTime();//发送时间
		if(sendTime>date){
			$("#submitBtn").attr("disabled",false);
		}else{
			BUI.Message.Alert("时间必须大于当前时间","error");
		}
	}
	</script>
</body>
</html>