<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>异常管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>异常处理</h2>
        </div>
        <div class="panel-body">
	        <form class="form-horizontal" id="form_abnormal_handle" onSubmit="return false;">
		        <div class="row">
		          	<div class="control-group span10">
		            	<label class="control-label"><s>*</s>处理日期</label>
		            	<div class="controls">
		              		<input name="handleDateStr" type="text" class="calendar calendar-time" data-rules="{required:true}" style="width: 126px;">
		            	</div>
		          	</div>
		          	<div class="control-group span12">
		            	<label class="control-label">理赔金额</label>
		            	<div class="controls">
		              		<input name="compensationMoney" type="text" class="input-normal control-text" data-rules="{number:true}" style="width: 80px;">
		            	</div>
		          	</div>
		        </div>
	        	<div class="control-group">
	        		<label class="control-label">客户意见</label>
					<div class="controls control-row-auto">
		              	<textarea name="customerOpinion" class="control-row4 input-large"></textarea>
		            </div>
	        	</div>
	        	<div class="control-group">
	        		<label class="control-label ">回复意见</label>
					<div class="controls control-row-auto">
		              	<textarea name="replyOpinion" class="control-row4 input-large"></textarea>
		            </div>
	        	</div>
		       
		        <div class="control-group">
		        	<label class="control-label">处理结果</label>
						<div class="controls control-row-auto">
			              	<textarea name="handleResult" class="control-row4 input-large"></textarea>
			            </div>
		        </div>
		        <div class="control-group">
		        	<label class="control-label">预防措施</label>
						<div class="controls control-row-auto">
			              	<textarea name="preventionMeasures" class="control-row4 input-large"></textarea>
			            </div>
		        </div>
				<div class="row form-actions actions-bar">
	            	<div class="span13 offset3 ">
		              	<button type="submit" class="button button-primary" onclick="sumbitAbHandle()">保存</button>
		              	<button type="reset" class="button">重置</button>
		            </div>
		        </div>
		        <input type="hidden" name="abnormalId" value="${abnormalId }"/>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	
		var message, abnormalHandleForm;
	    BUI.use('bui/overlay',function(overlay){
	        message = BUI.Message;
	        loadForm();
	    });
        
		function sumbitAbHandle(){
			if(!abnormalHandleForm.isValid()){
				return;
			}
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/abnormal/insrthandlret.shtml',
				type:'post',
				dataType:'json',
				data:$("#form_abnormal_handle").serialize(),
				success:function(data){
					if(data.result){
						message.Alert("处理成功",function(){
							window.location.href="<%=request.getContextPath()%>/tms/abnormal/list.shtml";
						},'success');
					}
				},error:function(){
					alert("系统错误")
				}
			})
		}
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	showTime:true,
	        	autoRender : true
	        });
	    });
		
		function loadForm(){
			BUI.use('bui/form',function(Form){
				abnormalHandleForm = new Form.Form({
					srcNode : '#form_abnormal_handle'
				}).render();
			})
		}
	</script>
</body>
</html>