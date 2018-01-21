<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>

	<div class="demo-content">		
		<form id="J_Form" class="form-horizontal">
		<input style="display:none;" name="id" value="${sysRole.id}" type="text"  >
			<div class="control-group">
			  <label class="control-label"><s>*</s>角色名称：</label>
			  <div class="controls">
			    <input name="rolename" value="${sysRole.rolename}" type="text" class="input-large" data-rules="{required : true}">
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>拼音：</label>
			  <div class="controls">
			    <input name="pinyinname" value="${sysRole.pinyinname}" type="text" class="input-large" >
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
			    		url:"${configProps['project']}/sysRole/updateSysRole.shtml",
			    		data:obj,
			    		async: false,
			    		error: function(request) {
			    			alert("Connection error");
			    		},
			    		success: function(data) {
			    			if(data.result){
				      			BUI.Message.Alert('修改成功！',function(){
				      				window.location="${configProps['project']}/system/pageJump.shtml?url=/system/sys_role_list";
				      				
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