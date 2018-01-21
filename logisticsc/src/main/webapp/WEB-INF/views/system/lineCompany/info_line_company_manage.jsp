<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>专线信息管理</title>
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
           	<h2>专线信息管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	<div style="display:inline-block">
	        	<label>起始地：</label>
		          <select id="s_start_province" name="startProvince" class="input-small start_province" onchange="renderSelect(this)"></select>
		          <select id="s_start_city" name="startCity" class="input-small start_city" onchange="renderSelect(this)"></select>
		          <select id="s_start_county" name="startCounty" class="input-small start_county"></select>&nbsp;&nbsp;&nbsp;&nbsp;
	        	</div>
	        	<div style="display:inline-block">
	        		<label>目的地：</label>
		          	<select id="s_end_province" name="endProvince" class="input-small end_province" onchange="renderSelect(this)"></select>
		          	<select id="s_end_city" name="endCity" class="input-small end_city" onchange="renderSelect(this)" ></select>
		          	<select id="s_end_county" name="endCounty end_county" class="input-small"></select>&nbsp;&nbsp;&nbsp;&nbsp;
        		</div>
        		<input id="condition" name="condition" type="text" placeholder="物流商，网点">
        		
        		
	        	<button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="render_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	</div>
       	</div>
    </div>
    <div id="hidden_dia" class="hide">
	    <form id="add_edit_form" class="form-horizontal">
	    	<label class="control-label">物流提供商</label>
	    	<div class="control-group">
		       	<div class="controls">
		          <input name="companyName" type="text" class="input-large" data-rules="{required:true}">
		        </div>
		 	</div>
		 	<div class="control-group">
				<label class="control-label">联系电话</label>
		       	<div class="controls">
		          <input name="phone" type="text" class="input-large" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号不正确'}" placeholder="输入11位手机号">
		        </div>
		 	</div>
		 	<div class="control-group">
				<label class="control-label">qq</label>
		       	<div class="controls">
		          <input name="qq" type="text" class="input-large" data-rules="{required:true}">
		        </div>
		 	</div>
			<div class="control-group">
				<label class="control-label">起始网点</label>
		       	<div class="controls">
		          <input name="startOutletsName" type="text" class="input-large" data-rules="{required:true}">
		        </div>
		 	</div>
		 	<div class="control-group">
				<label class="control-label">到站网点</label>
		       	<div class="controls">
		          <input name="endOutletsName" type="text" class="input-large" data-rules="{required:true}">
		        </div>
		 	</div>
		   	<div class="control-group">
		      	<label class="control-label">起始地</label>
		        <div class="controls">
		          <select id="start_province" name="startProvince" style="width:103px" data-rules="{required:true}" class="input-small start_province" onchange="renderSelect(this)"></select>
		          <select id="start_city" name="startCity" style="width:103px" data-rules="{required:true}" class="input-small start_city" onchange="renderSelect(this)"></select>
		          <select id="start_county" name="startCounty" style="width:103px" data-rules="{required:true}" class="input-small start_county"></select>
		        </div>
			</div>
			<div class="control-group">
		      	<label class="control-label">目的地</label>
		        <div class="controls">
		          	<select id="end_province" name="endProvince" style="width:103px" data-rules="{required:true}" class="input-small end_province" onchange="renderSelect(this)"></select>
		          	<select id="end_city" name="endCity" style="width:103px" data-rules="{required:true}" class="input-small end_city" onchange="renderSelect(this)" ></select>
		          	<select id="end_county" name="endCounty"  style="width:103px" data-rules="{required:true}" class="input-small"></select>
		        </div>
			</div>
	   		<div class="control-group">
		        <label class="control-label">运输方式</label>
		        <div class="controls">
		          <select id="trans_mode" name="transportMode" style="width:320px" data-rules="{required:true}" class="input-large">
					<option value="">请选择</option>
					<c:forEach var="transportMode" items="${transportModes }">
						<option value="${transportMode.id}">${transportMode.name}</option>
					</c:forEach>
		          </select>
		        </div>
	      	</div>
	      	<div class="control-group">
	        	<label class="control-label"><s>*</s>服务类型</label>
				<div class="controls">
		          	<input name="serverType" type="text" data-rules="{required:true}" class="input-large">
		        </div>
	     	</div>
	      	<div class="control-group">
	        	<label class="control-label"><s>*</s>时效</label>
	        	<div class="controls">
	          		<input name="timeLong" type="text" data-rules="{required:true}" class="input-large">
	        	</div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>重货价</label>
		        <div class="controls">
		          	<input name="heavyCargoPriceLow" type="text" data-rules="{required:true}" class="input-small" placeholder="小于1吨点单价">-
		          	<input name="heavyCargoPriceMid" type="text" data-rules="{required:true}" class="input-small" placeholder="1-3吨的单价">-
		          	<input name="heavyCargoPriceHigh" type="text" data-rules="{required:true}" class="input-small" placeholder="大于3吨的单价">
		        </div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>泡货价</label>
		        <div class="controls">
		          	<input name="bulkyCargoPriceLow" type="text" data-rules="{required:true}" class="input-small" placeholder="小于3立方的单价">-
		          	<input name="bulkyCargoPriceMid" type="text" data-rules="{required:true}" class="input-small" placeholder="3立方到10立方的单价">-
		          	<input name="bulkyCargoPriceHigh" type="text" data-rules="{required:true}" class="input-small" placeholder="大于10立方的单价">
		        </div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>起运价</label>
		        <div class="controls">
		          	<input name="lowestPrice" type="text" data-rules="{required:true}" class="input-large">
		        </div>
	      	</div>
	      	<div class="control-group">
	        	<label class="control-label">&nbsp;</label>
	        	<div class="controls">
	        		<label class="checkbox"><input id="release_state" type="checkbox" name="releaseState" value="1">对外发布</label>&nbsp;&nbsp;&nbsp;&nbsp;
	        		<!-- <label class="checkbox"><input id="is_take_cargo" type="checkbox" name="isTakeCargo" value="1">包提货</label>&nbsp;&nbsp;&nbsp;&nbsp;
	        		<label class="checkbox"><input id="is_give_cargo" type="checkbox" name="isGiveCargo" value="1">包送货</label> -->
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
					startProvince:$("#s_start_province").val(),
					startCity:$("#s_start_city").val(),
					startCounty:$("#s_start_county").val(),
					endProvince:$("#s_end_province").val(),
					endCity:$("#s_end_city").val(),
					endCounty:$("#s_end_county").val(),
					condition:$("#condition").val()
			};
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/overlay','bui/tooltip'],
					function(Grid,Data,Toolbar,Format,Overlay,Tooltip){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '公司名称',elCls:'center',width:150,dataIndex :'companyName'},
				{title : '联系电话',elCls:'center',width:120,dataIndex :'phone'},
				{title : 'qq',elCls:'center',width:120,dataIndex :'qq'},
				{title : '区域网点',elCls:'center',dataIndex :'startOutletsName'},
				{title : '线路',elCls:'center',width:250,renderer:function(val,obj){
					return '<div style="text-align:left;padding-left:20px"><div>从：'+obj.startProvince+'-'+obj.startCity+'-'+obj.startCounty+'</div><div>到：'
							+obj.endProvince+'-'+obj.endCity+'-'+obj.endCounty+'</div></div>';
				}},
				{title : '运输方式',elCls:'center',dataIndex :'transModeValue',},
				{title : '服务类型',elCls:'center',dataIndex :'serverType'},
				/* {title : '包提货',elCls:'center',dataIndex :'isTakeCargo',renderer:function(val){
					return val==0?"否":"是";
				}},
				{title : '包送货',elCls:'center',dataIndex :'isGiveCargo',renderer:function(val){
					return val==0?"否":"是";
				}}, */
				{title : '干线运价', elCls:'center',width:200,renderer:function(val,obj){
					var objStr = BUI.JSON.stringify(obj).replace(/\"/g,"'");
					return '<div style="text-align:left;padding-left:20px;"><div><div>重货价：<span class="grid-command heavy_cargo" data-title="'+objStr+'">￥'+obj.heavyCargoPriceLow+'/吨</span></div><div>泡货价：<span class="grid-command bulky_cargo" data-title="'+objStr+'">￥'
							+obj.bulkyCargoPriceLow+'/立方</span></div><div>最低价：￥'
							+obj.lowestPrice +"/票</div></div></div>";
				}},
			 	{title : '时效/时',elCls:'center',dataIndex : 'timeLong'},
			 	{title : '对外发布', elCls:'center',dataIndex : 'releaseState',renderer:function(val){
			 		if(val==1){
			 			return "是";
			 		}else{
			 			return "否";
			 		}
			 	}},
			];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'hidden_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit', //触发显示Dialog的样式
		          editor:{
		        	  title:'线路管理',
		        	  width:600,
		        	  height:500,
		        	  success:function(){
		        			var editor = this,
		        				form = editor.get("form"),
		        		  		editType = editing.get("editType"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/system/lineCmp/update.shtml";
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/system/lineCmp/insert.shtml";
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
				url: '<%=request.getContextPath()%>/system/lineCmp/search.shtml',
				autoLoad:true,
				proxy:{
					method:'post',
				},
				pageSize:10,
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
			  	//forceFit:true,
				columns : columns,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler : addLine
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler : editLine
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		handler : deleteLine
			          	}
		          	],
				}
			});
			//添加线路 
			function addLine(){
		        editing.add();
			}
			//编辑线路
			function editLine(){
				var selects = grid.getSelection();
				if(selects.length == 1){
					editSelect($("#start_city"), selects[0].startProvince);
					editSelect($("#start_county"), selects[0].startCity);
					editSelect($("#end_city"), selects[0].endProvince);
					editSelect($("#end_county"), selects[0].endCity);
					editing.edit(selects[0]);
				}else{
					BUI.Message.Alert("请选择一条记录","warning");
				}
			}
			//删除线路
			function deleteLine(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		data.push(selects[i].id);
		        	}
		        	$.ajax({
		        		url:'<%=request.getContextPath()%>/system/lineCmp/delete.shtml',
		        		data:{lineIds:data},
		        		type:'post',
		        		dataType:'json',
		        		success:function(data){
		        			if(data.result==true){
		        				alert("删除成功");
		        				store.load();
		        			}
		        		}
		        	})
		        }
			}
			//不使用模板的，左侧显示
			   var tips = new Tooltip.Tips({
			    	tip : {
				          	trigger : '.heavy_cargo', //出现此样式的元素显示tip
				          	alignType : 'right', //默认方向
				          	elCls : 'panel',
				          	width: 200,
				          	titleTpl : '<div class="panel-body">\
			                        <p><=1：￥{heavyCargoPriceLow}/吨\
			                        <p>1~3：￥{heavyCargoPriceMid}/吨\
			                        <p>>=3：￥{heavyCargoPriceHigh}/吨\
			                      </div>',
			          		offset : 10,
			        	}
			    }).render();
			   var tips = new Tooltip.Tips({
			    	tip : {
				          	trigger : '.bulky_cargo', //出现此样式的元素显示tip
				          	alignType : 'right', //默认方向
				          	elCls : 'panel',
				          	width: 200,
				          	titleTpl : '<div class="panel-body">\
					          		<p><=3：￥{bulkyCargoPriceLow}/立方\
			                        <p>3~10：￥{bulkyCargoPriceMid}/立方\
			                        <p>>=10：￥{bulkyCargoPriceHigh}/立方\
			                      </div>',
			          		offset : 10,
			        	}
			    }).render();
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
		$(function(){
			renderSelect();
		})	
		//加载select
		function renderSelect(obj){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/outlets/ajax/xzqh.shtml',
				data:{pid:$(obj).find("option:selected").attr("rel")},
				type:'post',
				success:function(result){
					if(result.result){
						var data = result.data;
						if(!obj){
							$(".start_province").append("<option value=''>请选择</option>");
							$(".end_province").append("<option value=''>请选择</option>");
							for(var i = 0;i<data.length;i++){
								$(".start_province").append('<option value="'+data[i].name+'" rel="'+data[i].id+'">'+data[i].name+'</option>');
								$(".end_province").append('<option value="'+data[i].name+'" rel="'+data[i].id+'">'+data[i].name+'</option>');
							}
						}else{
							if($(obj).val()!=null && $(obj).val()!=''){
								var nextEle = $(obj).next();						
								nextEle.empty();
								nextEle.next().empty();
								nextEle.append("<option value=''>请选择</option>");
								for(var i = 0;i<data.length;i++){
									nextEle.append('<option value="'+data[i].name+'" rel="'+data[i].id+'">'+data[i].name+'</option>');
								}	
							}else{
								var nextEle = $(obj).next();						
								nextEle.empty();
								nextEle.next().empty();
								nextEle.append("<option value='' rel=''></option>");
							}
						}
					}
				}
			})
		}
		
		function editSelect(obj,pid){
			if(pid){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/outlets/ajax/xzqh.shtml',
					data:{pid:pid},
					type:'post',
					success:function(result){
						if(result.result){
							var rows = result.data;
							for(var i = 0;i<rows.length;i++){
								$(obj).append("<option value='"+rows[i].name+"' rel='"+data[i].id+"'>"+rows[i].name+"</option>")
							}
						}
					}
				})
			}
		}
	</script>
</body>
</html>