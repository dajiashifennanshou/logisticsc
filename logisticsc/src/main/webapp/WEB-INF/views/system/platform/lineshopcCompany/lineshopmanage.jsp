<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专线商铺管理</title>
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
<form id="cmp_form" class="form-horizontal">
	<div class="panel">
		<h1>${platformUserCompany.companyName }</h1>
        <div class="panel-header">
           	<h2>公司介绍</h2>
        </div>
        <div class="panel-body">
        		<div class="row">
			       <div class="control-group span15">
			            <div class="controls control-row4">
			            <input type="hidden" value="${platformUserCompany.id}" id="companyid"/>
			            <input type="hidden" value="${platformUserCompany.companyName}" id="companynameid"/>
			              <textarea name="companyInfo" class="input-large" style="text-indent:2em;" data-rules="{required:true}" disabled>${platformUserCompany.companyInfo }</textarea>
			            </div>
		          </div>
		        </div>
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
    <div class="panel">  
       	<div class="panel-header">
           	<h2>联系我们</h2>
        </div>
        <div class="panel-body">
		        <div class="row">
			      	<div class="control-group span12">
			         	<label class="control-label"><s>*</s>联系人1：</label>
		            	<div class="controls">
		              		<input name="contacts1" type="text" value="${platformUserCompany.contacts1 }" data-rules="{required:true}" disabled>
		            	</div>
			       	</div>
		          	<div class="control-group span12">
		            	<label class="control-label"><s>*</s>联系人电话1：</label>
		            	<div class="controls">
		              		<input name="contacts1Mobile" type="text" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'请输入11位有效手机号'}" value="${platformUserCompany.contacts1Mobile }" disabled>
		            	</div>
		            </div>
		       	</div>
		       	<div class="row">
			     	<div class="control-group span12">
			            <label class="control-label"><s>*</s>联系人2：</label>
		            	<div class="controls">
		              		<input name="contacts2" type="text"  value="${platformUserCompany.contacts2 }" data-rules="{required:true}" disabled>
		            	</div>
			        </div>
			        <div class="control-group span12">
			           	<label class="control-label"><s>*</s>联系人电话2：</label>
		            	<div class="controls">
		              		<input name="contacts2Mobile" type="text" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'请输入11位有效手机号'}" value="${platformUserCompany.contacts2Mobile }" disabled>
		            	</div>
			 		</div>
		      	</div>
		       	<div class="row">
			     	<div class="control-group span12">
		            	<label class="control-label"><s>*</s>传真：</label>
		            	<div class="controls">
		              		<input name="companyFax" type="text" value="${platformUserCompany.companyFax }" data-rules="{required:true}" disabled>
		            	</div>
			        </div>
		        </div>
		        <div class="row">
			        <div class="control-group span12">
		            	<label class="control-label"><s>*</s>地址：</label>
		            	<div class="controls">
		              		<input name="companyAddress" type="text" value="${platformUserCompany.companyAddress }" data-rules="{required:true}" disabled>
		            	</div>
		          	</div>
		        </div>
		        <input type="hidden" name="id" value="${platformUserCompany.id }"/>
		</div>
	</div>
	</form>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		function editCmp(){
			$("#cmp_form input,textarea").attr("disabled",false);
		}
		//表格渲染
		BUI.use(['bui/form','bui/grid','bui/data','bui/toolbar'],
					function(Form,Grid,Data,Toolbar){
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
				url: '<%=request.getContextPath()%>/system/yypt/info/getoutlets.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				params : {companyName :$("#companynameid").val(),
					companyId : $("#companyid").val()
					},
				 sortInfo : {
				     field : 'companyName',
				     direction : 'ASC' //ASC,DESC
				 },
				pageSize:10,
			});
			store_line = new Store({
				url: '<%=request.getContextPath()%>/system/yypt/info/getlines.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
			//	params : {"platformUserCompany" :platformUserCompany},
				params : {companyName :$("#companynameid").val(),
					companyId : $("#companyid").val()	
				},
				 sortInfo : {
				     field : 'companyName',
				     direction : 'ASC' //ASC,DESC
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
	        form = new Form.Form({
	          srcNode : '#cmp_form',
	        }).render();
		});	
		
		function subCompany(){
			var isValid = form.isValid();
			BUI.Message.Alert(isValid);
			if(isValid){
				var data = $("#cmp_form").serialize();
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/shop/update.shtml',
					type:'post',
					data:data,
					success:function(result){
						if(result.result){
							BUI.Message.Alert("更新成功");
							window.location.href = "<%=request.getContextPath()%>/tms/shop/list.shtml";
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