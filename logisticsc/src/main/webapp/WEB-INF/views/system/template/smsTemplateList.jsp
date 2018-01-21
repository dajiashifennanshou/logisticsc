<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>短信模板设置</title>
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
           	<h2>短信模板设置</h2>
        </div>
        <div class="panel-body">
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    <!-- 默认隐藏 -->
    <div id="dia_hide" class="hide">
    	<form id="sms_template" class="form-horizontal">
    		<div class="control-group" style="height:135px;width:600px">
    			<label class="control-label"><s>*</s>模板内容：</label>
    			<div class="controls">
    				 <textarea rows="6" cols="30" id="tempContent" name="templateContent" data-rules="{required:true}" style="height:100px;width:400px"></textarea>
    			</div>
    		</div>
    		<div class="control-group" style="height:150px;width:600px">
    			<label class="control-label"><s>*</s>备注：</label>
    			<div class="controls">
    				 <textarea rows="6" cols="30" id="tempDesc" name="templateDesc" style="height:100px;width:400px"></textarea>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label">&nbsp;&nbsp;</label>
    			<div class="controls">
    				 <label><input id="sendConsignor" type="checkbox" value="1" name="sendConsignor"/>发货人</label>
    				 <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    				 <label><input id="sendConsignee" type="checkbox" value="1" name="sendConsignee"/>收货人</label>
    			</div>
    		</div>
    		<input id="templateId" type="hidden" name="id"/>
    	</form>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
		//表格渲染
		var dialog;
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Overlay){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '模板名称',elCls:'center',dataIndex :'templateName'},
				{title : '模板内容',elCls:'center',dataIndex :'templateContent',renderer:function(val){
					
				}},
				{title : '备注',elCls:'center',dataIndex:'templateDesc'},
				{title : '发货人',elCls:'center',dataIndex:'sendConsignor', renderer:function(val){
					if(val==1){
						return "是";
					}else{
						return "否";
					}
				}},
				{title : '收货人',elCls:'center',dataIndex:'sendConsignee', renderer:function(val){
					if(val==1){
						return "是";
					}else{
						return "否";
					}
				}},
				{title : '启用',elCls:'center',dataIndex:'isEnabled',renderer:function(val){
					if(val == 1){
						return "已启用";
					}else{
						return "已关闭";
					}
				}},
				{title : '操作',elCls:'center',dataIndex:'isEnabled',renderer:function(val,obj,index){
					var editTemp = "",
						open = "",
						close = "";
					editTemp = '<a href="javascript:void(0);" onclick="editTemp('+index+')" class="grid-command btn-edit"><i class="icon icon-yellow icon-pencil"></i> 编辑</a>';
					if(val == 0){
						open = "<a  href='javascript:void(0);' onclick='updateEnable("+obj.id+",1)' class='grid-command btn-del'><i class='icon icon-ok'></i> 启用</a>";
					}else{
						close = "<a  href='javascript:void(0);' onclick='updateEnable("+obj.id+",0)' class='grid-command btn-del'><i class='icon icon-remove'></i> 停用</a>";
					}
					return editTemp+open+close;
				}},
			];
			
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/template/getSmsTemplateList.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
			  	forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				
			});

	        var form = new Form.Form({
	        	srcNode:"#sms_template"
	        }).render();
	        
	        dialog = new Overlay.Dialog({
	            title:'短信模板设置',
	            width:600,
	            height:400,
	            //配置DOM容器的编号
	            contentId:'dia_hide',
	            success:function () {
    		  		var editType = this.get("editType"),
	    		  		url = "",
	    		  		data = {},
	    		  		sendConsignor = $("#sendConsignor").prop("checked")?1:0,
	    		  		sendConsignee = $("#sendConsignee").prop("checked")?1:0;
    		  		data.id = $("#templateId").val();
	    		  	data.templateContent = $("#tempContent").val();
	    		  	data.templateDesc = $("#tempDesc").val();
	    		  	data.sendConsignor = sendConsignor;
	    		  	data.sendConsignee = sendConsignee;
	    		  	if(editType=="edit"){
	    		  		url = "<%=request.getContextPath()%>/sys/template/updateSmsTemplate.shtml";
	    		  	}
	    		  	
	    		  	form.valid();
	    		  	if(form.isValid()){
	    		  		$.ajax({
	        			  	url:url,
	        			  	type:'post',
	        			  	dataType:'json',
	        			  	data:data,
	        			  	async:false,
	        				success:function(data){
	        					if(data.result){
	        						BUI.Message.Alert("模板更新成功",function(){
	        							dialog.close();
	        							store.load();
	        						},"success");
	        					}
				        	},
				        	error:function(){
				        		BUI.Message.Alert("网络异常","error");
				        	}
	          			});
	    		  	}
	            }
	        });
	        dialog.on("closed",function(){
	        	$("input").removeAttr("checked");
	        	$("input").val("");
	        })
	        
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store,
		    });
		});	
		

		function editTemp(index){
			var obj = grid.getItemAt(index);
			$("#templateId").val(obj.id);
			$("#tempContent").val(obj.templateContent);
			$("#tempDesc").val(obj.templateDesc);
			if(obj.sendConsignor == 1){
				$("#sendConsignor:first").prop("checked",true);
			}
			if(obj.sendConsignee == 1){
				$("#sendConsignee").prop("checked",true);
			}
			dialog.set("editType","edit");
			dialog.show();
		} 
		
		//启用/禁用模板
		function updateEnable(id,isEnabled){
			$.ajax({
			  	url:"<%=request.getContextPath()%>/sys/template/updateEnabled.shtml",
			  	type:'post',
			  	data:{templateId:id,isEnabled:isEnabled},
			  	dataType:'json',
			  	async:false,
				success:function(data){
					if(data.result){
						BUI.Message.Alert("操作成功",function(){
							store.load();
						},"success");
					}
	        	},
	        	error:function(){
	        		BUI.Message.Alert("网络异常","error");
	        	}
			})
		}
		
	</script>
</body>
</html>