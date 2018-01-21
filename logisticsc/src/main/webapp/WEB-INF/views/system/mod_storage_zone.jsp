<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改云仓网点</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
<script type="text/javascript">
	$(function(){
		var id = request.QueryString("id");
		yc_public.ajax({"url":"getYcStorageZoneSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData("J_Form", data.data);
			}
		}});
	});
</script>
</head>
<body>

	<div class="demo-content">		
		<form id="J_Form" class="form-horizontal">
		<input name="id"  type="hidden" group="val" >
			<div class="control-group">
			  <label class="control-label" for="branchName"><s>*</s>库区名称：</label>
			  <div class="controls">
			    <input name="zoneName" group="val" data-rules="{required : true}"  type="text" class="input-large" >
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>库区面积：</label>
			  <div class="controls">
			   <input type="text" group="val" name="zoneArea" data-rules="{required : true}" placeholder="仓库面积" class="col-xs-10 col-sm-5" />
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchType"><s>*</s>库区状态：</label>
			  <div class="controls">
			    <select group="val" name="zoneStatus" data-rules="{required : true}"  class="select2-container select2-allowclear statusSel tag-input-style">
					<option value="0" selected="selected">启用</option>
					<option value="1">维修</option>
				</select>
			  </div>
			</div>
		
			<div class="control-group">
			  <label class="control-label" for="branchName">备注：</label>
			  <div class="controls">
					<textarea class="col-xs-10 col-sm-5" group="val" name="remark"></textarea>
			  </div>
			</div>
			<div class="row actions-bar">       
		          <div class="form-actions span13 offset3" >
		          	<button  type="submit" class="button button-primary">修改</button>
		           </div>
		      </div>
		</form>
    </div>
	<script type="text/javascript">
	
/* 		$('#J_Form').submit(function() {
		  //alert("123");
			$.ajax({
	          type: "POST",
	          dataType : 'json',
	          url: "${configProps['project']}/sysRole/addSysRole.shtml",
	          //data: $('#J_Form').serialize(),
	          success: function (data) {
	              
	          },
	          error: function(data) {
	              
	           }
	      });
			
			return false;
		}); */
		
	 	BUI.use(['bui/form','bui/tooltip'],function(Form,Tooltip){
	 	//	$(document).ready(function(){
 
			var form = new Form.Form({
			  srcNode : '#J_Form',
			  errorTpl : '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>'
			}).render();
	 
			//不使用模板的，左侧显示
			var tips = new Tooltip.Tips({
			  tip : {
			    trigger : '.x-icon-error', //出现此样式的元素显示tip
			    alignType : 'top', //默认方向
			    elCls : 'tips tips-warning tips-no-icon tip1',
			    offset : 10 //距离左边的距离
			  }
			});
	      	tips.render();
	      	
	      	
	      	var jform = $('#J_Form');
		    jform.on('submit',function(ev) {
				
		    	
		      if(form.isValid()){
			      //序列化成对象
			      var obj = jform.serialize();
			      //禁用按钮
			      $('button[type="submit"]').toggleClass('disabled');
			      
			      $.ajax({
			    		cache: true,
			    		type: "POST",
			    		url:"modYcStorageZone.yc",
			    		data:obj,
			    		dataType:"json",
			    		async: false,
			    		error: function(request) {
			    			alert("Connection error");
			    		},
			    		success: function(data) {
			    			if(data.code==0){
				      			BUI.Message.Alert('修改成功！',function(){
				      				window.location="toStorageZone.yc";
				      				
				      			},'success'); 
				      		}else{
				      			BUI.Message.Alert(data.msg,'error');
				      		}
			    			
			    		}
			    	});
			  }
		      return false;
		    });

		});
    </script>
</body>