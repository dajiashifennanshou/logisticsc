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
		yc_public.ajax({"url":"getYcStorageBranchSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData("J_Form", data.data);
				//获取
				getProvince();
				onProSelect();
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
			  <label class="control-label" for="branchName"><s>*</s>网点名称：</label>
			  <div class="controls">
			    <input name="branchName" group="val" type="text" class="input-large" data-rules="{required : true}">
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>合作形式：</label>
			  <div class="controls">
			    <select group="val" name="joinType" data-rules="{required : true}"  class="select2-container select2-allowclear select2 tag-input-style">
					<option value=""></option>
					<option value="0">直营</option>
					<option value="1">加盟</option>
				</select>
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchType"><s>*</s>仓库类型：</label>
			  <div class="controls">
			    <select group="val" name="branchType" data-rules="{required : true}"  class="select2-container select2-allowclear select2 tag-input-style">
					<option value=""></option>
					<option value="0">平面仓库</option>
					<option value="1">立体仓库</option>
				</select>
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label"><s>*</s>地址：</label>
			  <div class="controls">
			  		<input type="hidden" group="val" name="province" id="province"/>
			   		<select  id="pro_" onchange="onProSelect()" data-rules="{required : true}" class="select2-container select2-allowclear addSel tag-input-style">
						
					</select>
					<input type="hidden" group="val" name="city" id="city"/>
					<select   onchange="onCitySelect()" id="cit_" data-rules="{required : true}" class="select2-container select2-allowclear addSel tag-input-style">
						
					</select>
					<input type="hidden" group="val" name="county" id="county"/>
					<select onchange="onCountySelect()" id="cou_" data-rules="{required : true}" class="select2-container select2-allowclear addSel tag-input-style">
						
					</select>
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchName"><s>*</s>详细地址：</label>
			  <div class="controls">
					<input type="text" group="val" name="town" data-rules="{required : true}" placeholder="乡镇街道" class="col-xs-10 col-sm-5" />
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchName"><s>*</s>负责人：</label>
			  <div class="controls">
					<input type="text" group="val" name="masterName" data-rules="{required : true}" placeholder="负责人" class="col-xs-10 col-sm-5" />
			  </div>
			</div>
			<div class="control-group">
			  <label class="control-label" for="branchName"><s>*</s>负责人电话：</label>
			  <div class="controls">
					<input type="text" group="val" name="masterPhone" data-rules="{required : true}" placeholder="负责人电话" class="col-xs-10 col-sm-5" />
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
	
		//获取省市县
		function getxzqhInfo(pid,callback){
			yc_public.ajax({url:'xzqh/getxzqhinfo.shtml',data:{'pid':pid},success:function(data){
				callback(data);
			}})
		}
		//获取省
		function getProvince(){
			//显示省
			getxzqhInfo(100000,function(data){
				var html="";
				var ib=$('#province').val();
				for(var i in data){
					var t=data[i];
					if(ib===t.name){
						ib=t.id+'_'+t.name;
					}
					html+='<option value="'+t.id+'_'+t.name+'">'+t.name+'</option>';
				}
				$('#province').val(data[0].name);
				$('#pro_').html(html);
				$('#pro_').val(ib);
			});
		}
		//当省选择时
		function onProSelect(){
			var pid=$('#pro_').val();
			var spl=pid.split('_');
			$('#province').val(spl[1]);
			//显示市
			getxzqhInfo(spl[0],function(data){
				var html="";
				var ib=$('#city').val();
				for(var i in data){
					var t=data[i];
					if(ib===t.name){
						ib=t.id+'_'+t.name;
					}
					html+='<option value="'+t.id+'_'+t.name+'">'+t.name+'</option>';
				}
				if(ib==data[0].name){
					$('#city').val(data[0].name);
				}
				$('#cit_').html(html);
				$('#cit_').val(ib);
			});
			//让市也要走一遍
			onCitySelect();
		}
		//当市选择让时
		function onCitySelect(){
			var pid=$('#cit_').val();
			var spl=pid.split('_');
			//显示县
			getxzqhInfo(spl[0],function(data){
				var html="";
				var ib=$('#county').val();
				for(var i in data){
					var t=data[i];
					if(ib===t.name){
						ib=t.id+'_'+t.name;
					}
					html+='<option value="'+t.id+'_'+t.name+'">'+t.name+'</option>';
				}
				if(ib==data[0].name){
					$('#county').val(data[0].name);
				}
				$('#cou_').html(html);
				$('#cou_').val(ib);
			});
			$('#city').val(spl[1]);
			
		}
		//当县选择时
		function onCountySelect(){
			var pid=$('#cou_').val();
			var spl=pid.split('_');
			//存储名字
			$('#county').val(spl[1]);
		}
		
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
			     // var obj = jform.serialize();
			      var obj = yc_public.getData('J_Form');
			      //禁用按钮
			      $('button[type="submit"]').toggleClass('disabled');
			      $.ajax({
			    		cache: true,
			    		type: "POST",
			    		url:"modYcStorageBranch.yc",
			    		data:obj,
			    		dataType:"json",
			    		async: false,
			    		error: function(request) {
			    			alert("Connection error");
			    		},
			    		success: function(data) {
			    			if(data.code==0){
				      			BUI.Message.Alert('修改成功！',function(){
				      				window.location="toStorageBranch.yc";
				      				
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