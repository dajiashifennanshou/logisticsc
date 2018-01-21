<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>险种管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body, #hidden_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>险种管理</h2>
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="险种，标签">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="dia_hide" class="hide">
    	<form id="ins_ts_type_form"  class="form-horizontal">
    		<div class="control-group">
    			<label class="control-label"><s>*</s>特殊类型名：</label>
    			<div class="controls">
    				 <input name="insTypeName" type="text" class="input-normal" data-rules="{required:true}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>特殊类型标签：</label>
    			<div class="controls">
    				<input id="ins_type_tag" name="insTypeTag" type="text" class="input-normal" data-rules="{required:true,v_typeTag:true}" />
    			</div>
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
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/overlay','bui/form'],
					function(Grid,Data,Toolbar,Format,Overlay,Form){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '险种名称',elCls:'center',dataIndex :'insTypeName'},
				{title : '险种标签',elCls:'center',dataIndex :'insTypeTag'},
				{title : '创建时间',elCls:'center',dataIndex :'createTime',renderer:Grid.Format.datetimeRenderer},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/system/ins/searchInsType.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'dia_hide', //设置隐藏的Dialog内容
		          triggerCls : 'btn-add', //触发显示Dialog的样式
		          editor:{
		        	  title:'特殊类型管理',
		        	  width:400,
		        	  height:250,
		        	  success:function(){
		        			var editor = this,
		        				form = editor.get("form"),
		        		  		editType = editing.get("editType"),
		        		  		data = $("#ins_ts_type_form").serialize(),
		        		  		record = editing.get("record"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/system/ins/addInsType.shtml";
		        		  		data += "&id="+record.id;
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/system/ins/addInsType.shtml";
		        		  	}
		        		  	form.valid();
		        		  	if(form.isValid()){
		        		  		$.ajax({
			        			 	url:url,
			        			 	data:data,
			        			 	type:'post',
			        			  	success:function(data){
			        					if(data.result){
			        						BUI.Message.Alert('信息已成功提交',function(){
			        							editor.close();
				        						store.load();
				        						editor.clearValue();
				        						$("#ins_type_tag").removeAttr("readonly");
			        						},'success');
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
			editing.on("cancel",function(){
				$("#ins_type_tag").removeAttr("readonly");
			})
			editing.on("editorshow",function(){
				var typeTag = $("#ins_type_tag");
				var editType = editing.get("editType");
				if(editType=='edit'){
					typeTag.attr("readonly",true);
				}
			})
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
			  	forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler:addInsType
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler:editInsType
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-del"></i>删除',
			           		handler:delInsType
			          	}
		          	],
				}
			});
			
			//验证标签是否存在
	    	Form.Rules.add({
	      		name : 'v_typeTag',  //规则名称
	            msg : '该标签已存在',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	            	if(editing.get("editType")=='add'){
	            		if(value != null && value != ''){
		              		if(typeTagExist(value)){
			            		return formatMsg;
			            	} 
		              	}
	            	}
	            }
	          });
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store,
					
		    });
		});	
		function typeTagExist(tag){
			var flag = false;
			$.ajax({
				url:'<%=request.getContextPath()%>/system/ins/existType.shtml',
				type:'post',
				data:{typeTag:tag},
				async:false,
				success:function(result){
					if(result.result){
						flag = result.data;
					}
				},
			})
			return flag;
		}
		function addInsType(){
			editing.add();
		}
		function editInsType(){
			var selects = grid.getSelection();
			if(selects.length==1){
				editing.edit(selects[0]);
			}else{
				BUI.Message.Alert("请选择一条记录","warning");
			}
		}
		function delInsType(){
			var selects = grid.getSelection();
			var length = selects.length;
			if(length>0){
				var array = [];
				for(var i=0;i<length;i++){
					array.push(selects[i].id);
				}
				$.ajax({
					url:'<%=request.getContextPath()%>/system/ins/delType.shtml',
					type:'post',
					data:{typeIds:array},
					dataType:'json',
					success:function(data){
						if(data.result == true){
							BUI.Message.Alert("操作成功",function(){
								store.load();	
							},"success");
						}
					}
				})
			}
		}
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
	</script>
</body>
</html>