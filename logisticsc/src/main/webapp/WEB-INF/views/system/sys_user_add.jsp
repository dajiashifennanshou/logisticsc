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
			<div class="control-group">
			  <label class="control-label"><s>*</s>用户登录名：</label>
			  <div class="controls">
			    <input name="username" type="text" class="input-large" data-rules="{required : true}">
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>真实姓名：</label>
			  <div class="controls">
			    <input name="realname" type="text" class="input-large" >
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>密码：</label>
			  <div class="controls">
			    <input name="password" type="text" class="input-large" >
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>角色：</label>
			  <div id="s1" class="controls">
			    <input id="roleId" name="roleId" type="hidden" class="input-large" > 
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>云仓人员：</label>
			  <div id="s1" class="controls">
			    <select id="isBranch" name="isBranch" class="edit-table-select">
			    	<option value="1">否</option>
					<option value="0">是</option>
				</select>
			  </div>
			</div>
			<div class="control-group" style="display:none;" id="show_branch">
			  <label class="control-label"><s>*</s>选择所属云仓：</label>
			  <div id="s1" class="controls">
			    <select id="branchNo" name="branchNo" class="edit-table-select">
			    	<option value="0">--请选择云仓--</option>
					<c:forEach items="${branchNos }" var="bn">
						<option value="${bn.branchNo}">${bn.branchName}</option>
					</c:forEach>
				</select>
			  </div>
			</div>
			 <div class="row actions-bar">       
		          <div class="form-actions span13 offset3" >
		          	<button  type="submit" class="button button-primary">保存</button>
		           </div>
		      </div>
		</form>
    </div>
	<script type="text/javascript">
	$(function(){
		$("#isBranch").change(function(){
			var v=$('#isBranch').val();
				if(v==0){
		  			$('#show_branch').show();
		  		}else{
		  			$('#show_branch').hide();
		  		}
		})
	})
	BUI.use(['bui/select','bui/data'],function(Select,Data){
		  
	    var store = new Data.Store({
	      url : "${configProps['project']}/sysRole/sys_role_select.shtml",
	     // root: 'list',
	      autoLoad : true
	    }); 
	    
/* 	    var suggest = new Select.Suggest({
	        render:'#s1',
	        valueField:'#hide',
	        name:'suggest',
	        store: store,
	        list: {
	          itemTpl : '<li>{value}</li>' // 设置列表模板
	        },
	        cacheable:true
	      });
	      suggest.render(); */
	    select = new Select.Select({  
	        render:'#s1',
	        valueField:'#hide',
	        /* list : {
	            itemTpl : '<li>123123</li>',
	            idField : 'id',
	            elCls:'bui-select-list'
	          }, */
	        store : store
	      });
	      select.render();
	      select.on('change', function(ev){
	    	  var v=ev.item.id;
		  		
	    	  $("#roleId").val(v);
	      }); 
	    
	  });
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
			      var branchNo=$('#branchNo').val();
			      var roleId=$('#roleId').val();
			      var isB=$('#isBranch').val();
			      if(isB==="0"){
			    	  if(branchNo===0 || branchNo==="0"){
			    	 	 alert("请选择云仓..");
				    	  return false;
			    	  }
			      }else{
			    	  $('#branchNo').val(0);
			      }
			      var obj = jform.serialize();
			      //禁用按钮
			      $('button[type="submit"]').toggleClass('disabled');
			      
			      $.ajax({
			    		cache: true,
			    		type: "POST",
			    		url:"${configProps['project']}/system/addUser.shtml",
			    		data:obj,
			    		async: false,
			    		error: function(request) {
			    			alert("Connection error");
			    			//禁用按钮
			    			$('button[type="submit"]').toggleClass('disabled');
			    		},
			    		success: function(data) {
			    			//禁用按钮
						    $('button[type="submit"]').toggleClass('disabled');
			    			if(data.result){
				      			BUI.Message.Alert(data.msg,function(){
				      				window.location="${configProps['project']}/system/pageJump.shtml?url=/system/sys_user_list";
				      				
				      			},'success'); 
				      		}else{
				      			BUI.Message.Alert(data.msg,'error');
				      		}
			    			
			    		}
			    	});
			      
			      /* $.post("${configProps['project']}/sysRole/addSysRole.shtml",obj,function(data){
			      		$('button[type="submit"]').toggleClass('disabled');
			      		
		      			//window.location="${configProps['project']}/system/pageJump.shtml?url=/system/sys_role_list";
		      			//alert("123123");
			      		if(data.result){
			      			BUI.Message.Alert('操作成功！请继续下一步填写！',function(){
			      				window.location="${configProps['project']}/system/pageJump.shtml?url=/system/sys_role_list";
			      				
			      			},'success'); 
			      		}else{
			      			BUI.Message.Alert(data.msg,'error');
			      		}
			      },'json'); */
			  }
		      return false;
		    });

		});
    </script>
</body>