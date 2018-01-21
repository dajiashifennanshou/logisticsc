<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>系统设置</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
        	<h1>${platformUserCompany.companyName }</h1>
        </div>
    </div>
  	<div class="panel">
       	<div class="panel-header">
           	<h2>网点介绍</h2>
        </div>
        <div class="panel-body">
	        <div id="outlets_render_grid"></div>
	       	<div id="outlets_pagingbar"></div>
        </div>  
    </div>
    <div class="panel">  
       	<div class="panel-header">
           	<h2>线路介绍</h2>
        </div>
        <div class="panel-body">
	     	<div id="line_render_grid"></div>
	       	<div id="line_pagingbar"></div>
       	</div>
    </div>
<form id="cmp_form" class="form-horizontal">
    <div class="panel"> 
    	<div class="panel-header">
           	<h2>基本信息</h2>
        </div>
        <div class="panel-body">
        	<div class="row">
		       	<div class="control-group span8">
		       		<label class="control-label">员工人数：</label>
		           	<div class="controls" style="position:relative">
	             		<input name="staffNumber" type="text" data-rules="{number:true}" value="${platformUserCompanyinfo.staffNumber }" disabled>
	             		<div style="position:absolute;left:140px;top:5px;color:gray">人</div>
	            	</div>
	          	</div>
	          	<div class="control-group span8">
		       		<label class="control-label">品牌名字：</label>
		           	<div class="controls">
	             		<input name="brandName" type="text" value="${platformUserCompanyinfo.brandName }" disabled>
	            	</div>
	          	</div>
	          	<div class="control-group span8">
		       		<label class="control-label">年营业额：</label>
		           	<div class="controls" style="position:relative">
	             		<input name="annualMoney" type="text" data-rules="{number:true,v_twoNum:true}" value="${platformUserCompanyinfo.annualMoney }" disabled placeholder="保留两位小数">
	             		<div style="position:absolute;left:130px;top:5px;color:gray">万元</div>
	            	</div>
	          	</div>
	        </div>
	        <div class="row">
		       	<div class="control-group span15">
		       		<label class="control-label">覆盖区域：</label>
		            <div class="controls control-row4">
		              <textarea name="region" class="input-large" style="text-indent:2em;" disabled>${platformUserCompanyinfo.region }</textarea>
		            </div>
	          	</div>
	        </div>
	        <div class="row">
		       	<div class="control-group span15">
		       		<label class="control-label">承运货物信息：</label>
		            <div class="controls control-row4">
		              <textarea name="carriageGoods" class="input-large" style="text-indent:2em;" disabled>${platformUserCompanyinfo.carriageGoods }</textarea>
		            </div>
	          	</div>
	        </div>
	        <div class="row">
		       	<div class="control-group span15">
		       		<label class="control-label">车辆信息：</label>
		            <div class="controls control-row4">
		              <textarea name="vehicleInfo" class="input-large" style="text-indent:2em;" disabled>${platformUserCompanyinfo.vehicleInfo }</textarea>
		            </div>
	          	</div>
	        </div>
	        <div class="row">
		       	<div class="control-group span15">
		       		<label class="control-label">仓库信息：</label>
		            <div class="controls control-row4">
		              <textarea name="warehouseInfo" class="input-large" style="text-indent:2em;" disabled>${platformUserCompanyinfo.warehouseInfo }</textarea>
		            </div>
	          	</div>
	        </div>
	        <div class="row">
		       	<div class="control-group span15">
		       		<label class="control-label">服务内容：</label>
		            <div class="controls control-row4">
		              <textarea name="serviceInfo" class="input-large" style="text-indent:2em;" disabled>${platformUserCompanyinfo.serviceInfo }</textarea>
		            </div>
	          	</div>
	        </div>
       		<div class="row">
		       	<div class="control-group span15">
		       		<label class="control-label">公司介绍：</label>
		            <div class="controls control-row4">
		              <textarea name="companyInfo" class="input-large" style="text-indent:2em;" disabled>${platformUserCompany.companyInfo }</textarea>
		            </div>
	          	</div>
	        </div>
       	</div>
    </div>
 	<div class="panel">
       	<div class="panel-header">
           	<h2>联系我们</h2>
        </div>
        <div class="panel-body">
		        <div class="row">
			      	<div class="control-group span12">
			         	<label class="control-label">联系人1：</label>
		            	<div class="controls">
		              		<input name="contacts1" type="text" value="${platformUserCompany.contacts1 }" disabled>
		            	</div>
			       	</div>
		          	<div class="control-group span12">
		            	<label class="control-label">联系人电话1：</label>
		            	<div class="controls">
		              		<input name="contacts1Mobile" type="text" data-rules="{v_phone:true}" value="${platformUserCompany.contacts1Mobile }" 
		              			disabled placeholder="输入11手机号">
		            	</div>
		            </div>
		       	</div>
		       	<div class="row">
			     	<div class="control-group span12">
			            <label class="control-label">联系人2：</label>
		            	<div class="controls">
		              		<input name="contacts2" type="text"  value="${platformUserCompany.contacts2 }" disabled>
		            	</div>
			        </div>
			        <div class="control-group span12">
			           	<label class="control-label">联系人电话2：</label>
		            	<div class="controls">
		              		<input name="contacts2Mobile" type="text" data-rules="{v_phone:true}" value="${platformUserCompany.contacts2Mobile }" 
		              			disabled placeholder="输入11手机号">
		            	</div>
			 		</div>
		      	</div>
		       	<div class="row">
			     	<div class="control-group span12">
		            	<label class="control-label">传真：</label>
		            	<div class="controls">
		              		<input name="companyFax" type="text" value="${platformUserCompany.companyFax }" data-rules="{v_mobile:true}" disabled>
		            	</div>
			        </div>
		        </div>
		        <div class="row">
			        <div class="control-group span12">
		            	<label class="control-label">地址：</label>
		            	<div class="controls">
		              		<input name="companyAddress" type="text" value="${platformUserCompany.companyAddress }" disabled>
		            	</div>
		          	</div>
		        </div>
		        <input type="hidden" name="companyId" value="${platformUserCompany.id }"/>
		        <input type="hidden" name="companyInfoId" value="${platformUserCompanyinfo.id }"/> 
		        <input type="hidden" id="outletsId" value="${outletsId }"/>
		</div>
	</div>
	</form>
	<div class="actions-bar">
	  	<div class="row ">
	        <div class="span13 offset3 ">
	         	<button class="button button-primary" onclick="editCmp()">编辑</button>
	           	<button onclick="subCompany()" type="submit" class="button button-primary">保存</button>
	      	</div>
	  	</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var Msg;
	    BUI.use('bui/overlay',function(overlay){
	        Msg = BUI.Message;
	    });
	
		function editCmp(){
			if($("#outletsId").val() != null && $("#outletsId").val() !=''){
				Msg.Alert("你没有权限修改","warning");
			}else{
				$("#cmp_form input,textarea").attr("disabled",false);
			}
		}
        
        var form;
		//表格渲染
		BUI.use(['bui/form','bui/grid','bui/data','bui/toolbar','bui/tooltip'],
					function(Form,Grid,Data,Toolbar,Tooltip){
			form = new Form.Form({
				srcNode : '#cmp_form',
				errorTpl : '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>'
			}).render();
			var Grid = Grid,
				Store = Data.Store,
				items = [];
				columns_outlets = [
					{title : '公司名称', dataIndex : 'companyName'},  
					{title : '网点名称', dataIndex : 'name'},
					{title : '联系人', dataIndex : 'contactPerson'},
					{title : '用户名', dataIndex : 'loginName'},
					{title : '姓名', dataIndex : 'trueName'},
					{title : '固定电话',dataIndex :'phone',},
					{title : '邮箱',dataIndex :'email'},
				];
				columns_line = [
					{title : '起始网点', dataIndex : 'startOutletsName'},
					{title : '到站网点', dataIndex : 'endOutletsName'},

				];
			store_outlets = new Store({
				url: '<%=request.getContextPath()%>/tms/shop/getoutlets.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			store_line = new Store({
				url: '<%=request.getContextPath()%>/tms/shop/getlines.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#outlets_render_grid',
			  	autoRender:true, 
				columns : columns_outlets,
				forceFit : true,
				store : store_outlets,
				loadMask: true,//加载数据时显示屏蔽层
			});
			grid = new Grid.Grid({
			   	render:'#line_render_grid',
			  	autoRender:true, 
				columns : columns_line,
				forceFit : true,
				store : store_line,
				loadMask: true,//加载数据时显示屏蔽层
			});
			
			//
			var mobReg = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/,//验证固定电话
        		phnReg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/,//验证手机号
        		numberTwo = /^[0-9]+(.[0-9]{2})?$/;
      		//保留两位小数
   	    	Form.Rules.add({
   	      		name : 'v_twoNum',  //规则名称
   	            msg : '保留两位小数',//默认显示的错误信息
   	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
   	              	if(value != null && value != ''){
   	              		if(!numberTwo.test(value)){
   		            		return formatMsg;
   		            	} 
   	              	}
   	            }
   	          }); 
       		//验证固定电话
   	    	Form.Rules.add({
   	      		name : 'v_mobile',  //规则名称
   	            msg : '传真格式错误',//默认显示的错误信息
   	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
   	              	if(value != null && value != ''){
   	              		if(!mobReg.test(value)){
   		            		return formatMsg;
   		            	} 
   	              	}
   	            }
   	          }); 
   	        	
   	    	//验证手机号
   	    	Form.Rules.add({
   	      		name : 'v_phone',  //规则名称
   	            msg : '手机号不正确',//默认显示的错误信息
   	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
   	              	if(value != null && value != ''){
   	              		if(!phnReg.test(value)){
   		            		return formatMsg;
   		            	} 
   	              	}
   	            }
   	          }); 

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
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#outlets_pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store_outlets
		    });
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#line_pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store_line
		    });
		});	
		
		function subCompany(){
			form.valid();
			if(form.isValid()){
				var data = $("#cmp_form").serialize();
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/shop/update.shtml',
					type:'post',
					data:data,
					success:function(result){
						if(result.result){
							Msg.Alert("更新成功",function(){
								window.location.href = "<%=request.getContextPath()%>/tms/shop/list.shtml";
							},"success");
						}
					},
					error:function(){
						
					}
				})
			}
		}
	</script>
</body>
</html>