<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
           	<h2>提送货管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	<c:if test="${empty sessionScope.tms_user_session.outletsId }">
	        		网点：<input id="outletsName" name="outletsName" type="text">
	        	</c:if>
	          	区域：<input id="condition" name="area" type="text">
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
	        	<label class="control-label"><s>*</s>网点名称</label>
	        	<div class="controls">
		         	<input id="outlets_name" type="text" class="input-normal" disabled/>
	        	</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label"><s>*</s>区域</label>
        		<div class="controls">
	          		<select id="county" name="county"  data-rules="{required:true,isExistCounty:''}">
	          		</select>
       			</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label"><s>*</s>重量</label>
        		<div class="controls" style="position:relative">
	          		<input name="weight" type="text" class="input-normal"  data-rules="{required:true,number:true}">
        			<div style="position:absolute;width:30px;height:27px;top:5px;left:110px;text-align:center;">元/吨</div>
        		</div>
      		</div>
      		<div class="control-group">
        		<label class="control-label"><s>*</s>体积</label>
        		<div class="controls" style="position:relative">
	          		<input name="volume" type="text" class="input-normal"  data-rules="{required:true,number:true}">
        			<div style="position:absolute;width:40px;height:27px;top:5px;left:110px;text-align:center;">元/立方</div>
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
		$(document).ready(function(){
			renderCounty();
		})
		//查询
		function search(){
			var params = {
					outletsName:$("#outletsName").val(),
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select','bui/util','bui/form'],
					function(Grid,Data,Toolbar,Format,Select,Util,Form){
			var Grid = Grid,
				Store = Data.Store,
				columns = [
					{title : '所属网点',elCls:'center', renderer:function(val,obj){
						return obj.outletsInfo.name;
					}},
					{title : '提送货区域',elCls:'center', dataIndex : 'countyVal'},
					{title : '元/吨', elCls:'center', dataIndex : 'weight'},
					{title : '元/方',elCls:'center', dataIndex :'volume'},
				];
				editing = new Grid.Plugins.DialogEditing({
			          contentId : 'hidden_dia', //设置隐藏的Dialog内容
			          triggerCls : 'btn-edit', //触发显示Dialog的样式
			          editor:{
			        	  title:'提送货管理',
			        	  width:400,
			        	  height:300,
			        	  effect:{effect:'fade',duration:400},
			        	  success:function(){
			        			var editor = this,
			        				form = editor.get("form"),
			        		  		editType = editing.get("editType"),
			        		  		url = "";
			        		  	if(editType=="edit"){
			        		  		url = "<%=request.getContextPath()%>/tms/range/conf/update.shtml";
			        		  	}else if(editType == "add"){
			        		  		url = "<%=request.getContextPath()%>/tms/range/conf/insert.shtml";
			        		  	}
			        		  	form.valid();
			        		  	if(form.isValid()){
			        		  		form.ajaxSubmit({
				        			 	url:url,
				        			 	type:'post',
				        			  	success:function(data){
				        					if(data.result == true){
				        						if(data.result == true){
					        						BUI.Message.Alert("信息提交成功",function(){
					        							editor.close();
					        							store.load();
					        						},"success");
					        					}
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
				url: '<%=request.getContextPath()%>/tms/range/conf/search.shtml',
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
			           		handler: addConf
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler: editConf
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		handler : deleteConf
			          	}
		          	],
				}
				
			});
			Form.Rules.add({
				name : 'isExistCounty',
				msg : '区县已添加',
				validator : function(value, baseValue, formatMsg){
					if(value != null && value != ''){
						if(!validIsExistCounty(value)){
							return formatMsg;
						}
					}
				}
			});
			function addConf(){
				editing.get("editor").clearValue();
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
						BUI.Message.Alert("网络错误","error");
					}
				})
			}
			function editConf(){
				$.ajax({
					url:"${configProps['project']}/tms/sys/isOutlets.shtml",
					type:'post',
					success:function(result){
						if(result.result){
							var selects = grid.getSelection();
							if(selects.length == 1){
								editing.edit(selects[0]);
							}else{
								BUI.Message.Alert("请选择一条记录","warning");
							}
						}else{
							BUI.Message.Alert(result.msg,'warning');
						}
					},
					error:function(result){
						BUI.Message.Alert("网络异常",'error');
					}
				})
			}
			function deleteConf(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		data.push(selects[i].id);
		        	}
		        	BUI.Message.Confirm("确定要删除？",function(){
		        		$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/range/conf/delete.shtml',
			        		data:{confIds:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result){
			        				BUI.Message.Alert("删除成功",function(){
			        					store.load();
			        				},"success");
			        			}
			        		}
			        	})
		        	})
		        }
			}
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
		//渲染区域列表
		function renderCounty(){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/range/conf/render.shtml',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.result == true){
						var rows = data.rows,
							node = $("#county"),
							summary = data.summary;
						$("#outlets_name").val(summary.outletsInfo.name);
						node.empty();
						node.append("<option value=''>请选择</option>");
						for(var i=0;i<summary.xzqhs.length;i++){
							node.append("<option value='"+summary.xzqhs[i].id+"'>"+summary.xzqhs[i].name+"</option>");
						}
					}
				}
			})
		}
		
		// 验证 是否已存在 区县配置
		function validIsExistCounty(county){
			var flag = false;
			$.ajax({
				type : 'post',
				async : false,
				url : '<%=request.getContextPath()%>/tms/range/conf/validisexistcounty.shtml',
				data : { 'county' : county },
				success : function(result){
					flag = result;
				}
			});
			return flag;
		}
	</script>
</body>
</html>