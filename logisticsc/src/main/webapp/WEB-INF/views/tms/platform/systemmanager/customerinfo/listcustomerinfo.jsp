<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
           	<h2>客户管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="客户姓名">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid">
	       	</div>
	       	<div id="pagingbar">
	       	</div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="hidden_dia" class="hide">
	    <form id="add_edit_form" class="form-horizontal">
	    	<div class="control-group">
	        	<label class="control-label">公司名称</label>
	        	<div class="controls">
		         	<input name="companyName" type="text" class="input-normal"/>
	        	</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label"><s>*</s>姓名</label>
        		<div class="controls">
	          		<input name="customerName" type="text" data-rules="{required:true}" class="input-normal">
       			</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label"><s>*</s>手机号</label>
        		<div class="controls">
	          		<input name="phone" type="text" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号不正确'}" class="input-normal">
        		</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label">固定电话</label>
        		<div class="controls">
	          		<input name="mobile" type="text" class="input-normal" data-rules="{v_mobile:true}" 
			       		data-messages="{regexp:'号码不正确'}"/>
        		</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label">详细地址</label>
        		<div class="controls">
	          		<input name="address" type="text" class="input-normal">
        		</div>
      		</div>
      		<div>
      			<input type="hidden" name="id"/>
      		</div>
      	</form>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params = {
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/overlay','bui/form'],
					function(Grid,Data,Toolbar,Overlay,Form){
			var Grid = Grid,
				Store = Data.Store,
				columns = [
					{title : '所属网点',elCls:'center',renderer:function(val,obj){
						return obj.outletsInfo.name;
					}},
					{title : '姓名',elCls:'center', dataIndex : 'customerName'},
					{title : '手机号',elCls:'center', dataIndex : 'phone'},
					{title : '固定电话',elCls:'center',dataIndex :'mobile'},
					{title : '详细地址',elCls:'center',dataIndex :'address'},
					{title : '公司名称',elCls:'center', dataIndex : 'companyName'}
				],
				editing = new Grid.Plugins.DialogEditing({
			          contentId : 'hidden_dia', //设置隐藏的Dialog内容
			          triggerCls : 'btn-edit', //触发显示Dialog的样式
			          editor:{
			        	  title:'客户管理',
			        	  width:450,
			        	  height:350,
			        	  effect:{effect:'fade',duration:400},
			        	  success:function(){
			        			var editor = this,
			        				form = editor.get("form"),
			        		  		editType = editing.get("editType"),
			        		  		url = "";
			        		  	if(editType=="edit"){
			        		  		url = "<%=request.getContextPath()%>/tms/customer/update.shtml";
			        		  	}else if(editType == "add"){
			        		  		url = "<%=request.getContextPath()%>/tms/customer/insert.shtml";
			        		  	}
			        		  	form.valid();
			        		  	if(form.isValid()){
			        		  		form.ajaxSubmit({
				        			 	url:url,
				        			 	type:'post',
				        			  	success:function(data){
				        					if(data.result == true){
				        						BUI.Message.Alert("信息提交成功",function(){
				        							editor.close();
				        							store.load();
				        						},"success");
				        					}
							        	},
							        	error:function(){
							        		  alert("系统错误");
							        		  editor.close();
							        	}
			        		  		})
			        		  	}
			        			
			        	  }
			          }
			   	});
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/customer/search.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler: addCustomer
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler: editCustomer
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		handler: deleteCustomer
			          	}
		          	],
				}
			});
			function addCustomer(){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/sys/isOutlets.shtml',
					type:'post',
					success:function(result){
						if(result.result){
							editing.add();
						}else{
							BUI.Message.Alert(result.msg,"warning");
						}
					},
					error:function(){
						BUI.Message.Alert("网络异常","error");
					}
				})
			}
			function editCustomer(){
				var selects = grid.getSelection();
				if(selects.length==1){
					editing.edit(selects[0]);
				}else{
					BUI.Message.Alert("请选择一条记录");
				}
			}
			function deleteCustomer(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		data.push(selects[i].id);
		        	}
		        	BUI.Message.Confirm("确定要删除该记录？",function(){
		        		$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/customer/delete.shtml',
			        		data:{customerIds:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result==true){
			        				BUI.Message.Alert("删除成功",function(){
			        					store.load();
			        				},"success");
			        			}
			        		}
			        	})
		        	})
		        }
			}
			
			var mobReg = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//验证固定电话
	         
	        //验证固定电话
	    	Form.Rules.add({
	      		name : 'v_mobile',  //规则名称
	            msg : '固定电话格式错误',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!mobReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});	
		
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		//渲染起始网点
		function renderStartOutlets(startOutlets){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/lineinfo/startoutlets.shtml',
				type:'post',
				dataType:'json',
				async:false,
				success:function(data){
					if(data.result == true){
						var rows = data.rows,
							startNode = $("#line_start_outlets");
						startNode.empty();
						startNode.append("<option value=''>请选择</option>");
						for(var i=0;i<rows.length;i++){
							if(startOutlets==rows[i].id){
								startNode.append("<option value='"+rows[i].id+"&"+rows[i].name+"' selected>"+rows[i].name+"</option>");
							}else{
								startNode.append("<option value='"+rows[i].id+"&"+rows[i].name+"'>"+rows[i].name+"</option>");
							}
							
						}
					}
				}
			})
		}
		//渲染到站网点
		function renderEndOutlets(endOutlets){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/lineinfo/endoutlets.shtml',
				type:'post',
				dataType:'json',
				async:false,
				success:function(data){
					if(data.result == true){
						var rows = data.rows,
							endNode = $("#line_end_outlets");
						endNode.empty();
						endNode.append("<option value=''>请选择</option>");
						for(var i=0;i<rows.length;i++){
							if(endOutlets==rows[i].id){
								endNode.append("<option value='"+rows[i].id+"&"+rows[i].name+"' selected>"+rows[i].name+"</option>");	
							}else{
								endNode.append("<option value='"+rows[i].id+"&"+rows[i].name+"'>"+rows[i].name+"</option>");
							}
							
						}
					}
				}
			})
		}
		//渲染运输方式列表
		function renderTransMode(){
			$.ajax({
				url:'<%=request.getContextPath()%>/dic/transMode.shtml',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.result == true){
						var rows = data.rows,
							mode = $("#trans_mode");
						mode.empty();
						mode.append("<option value=''>请选择</option>");
						for(var i=0;i<rows.length;i++){
							mode.append("<option value='"+rows[i].id+"'>"+rows[i].name+"</option>");
						}
					}
				}
			})
		}
	</script>
</body>
</html>