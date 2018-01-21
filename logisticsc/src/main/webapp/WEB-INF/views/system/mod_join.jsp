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
	//获取云仓网点信息
	function getYcStorageBranch(){
		yc_public.ajax({"url":"getYcStorageBranch.yc","success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=branchNo]").append("<option value='"+item.branchNo+"'>"+item.branchName+"</option>");
				});
				getStorageZone($("select[name=branchNo]").val());
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	}
	function getStorageZone(storageNo){
		$("#select2-chosen-6").html("");//清空
		$("select[name=zoneNo]").empty();//清空 
		if(storageNo!=null){
			yc_public.ajax({"url":"getStorageZone.yc","data":{"storageNo":storageNo},"success":function(data){
				if(data.code==0){
					$.each(data.data, function(i, item){
						$("select[name=zoneNo]").append("<option value='"+item.zoneNo+"'>"+item.zoneNo+"</option>");
					});
				}else{
					yc_public.dialog(data.msg)
				}
			}});
		}
	}
	$(function(){
		getYcStorageBranch();
		var id = request.QueryString("id");
		yc_public.ajax({"url":"getYcJoinInfoSingle.yc","data":{"id":id},"success":function(data){
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
			  <label class="control-label" for="branchName"><s>*</s>申请人：</label>
			  <div class="controls">
			    <input type="hidden" group="val" name="joinerId" />
				<input type="text" group="val" name="joinName" readonly="readonly" valited="required" placeholder="申请人" class="col-xs-10 col-sm-5" />
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>加盟商类型：</label>
			  <div class="controls">
			   <select group="val" name="joinType" valited="required"  class="select2-container select2-allowclear typeSel tag-input-style">
					<option value="0">经销商</option>
					<option value="1">专线</option>
				</select>
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchType"><s>*</s>网点编号：</label>
			  <div class="controls">
			    <div class="col-sm-9">
					<select group="val" name="branchNo" valited="required"  class="select2-container select2-allowclear select2 tag-input-style" onchange="getStorageZone(this.value)">
						
					</select>
				</div>
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchType"><s>*</s>库区编号：</label>
			  <div class="controls">
			    <select group="val" name="zoneNo" valited="required"  class="select2-container select2-allowclear select2 tag-input-style">
					<option value="--请选择库区编号--"></option>
				</select>
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchType"><s>*</s>使用面积：</label>
			  <div class="controls">
			    <input type="text" group="val" name="apolyArea" valited="required,number" placeholder="使用面积" class="col-xs-10 col-sm-5" />
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchType"><s>*</s>审核结果：</label>
			  <div class="controls">
			    <select group="val" name="applyStatus" valited="required"  class="select2-container select2-allowclear typeSel tag-input-style">
					<option value="1">通过审核</option>
					<option value="0">审核中</option>
					<option value="2">拒绝</option>
				</select>
			  </div>
			</div>
			<div class="form-group">
				 <label class="control-label" for="branchType"><s>*</s>使用时间(月)：</label>
				<div class="col-sm-9">
					<input type="text" readonly="readonly" group="val" name="days" valited="required,number" placeholder="使用时间：月为单位" class="col-xs-10 col-sm-5" />
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
			    		url:"modYcJoinInfo.yc",
			    		data:obj,
			    		dataType:"json",
			    		async: false,
			    		error: function(request) {
			    			alert("Connection error");
			    		},
			    		success: function(data) {
			    			if(data.code==0){
				      			BUI.Message.Alert('修改成功！',function(){
				      				window.location="toJoinInfo.yc";
				      				
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