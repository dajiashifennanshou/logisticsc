<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>线路管理</title>
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
           	<h2>线路管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	<label>始发地：</label>
	        	<select id="search_start" name="searchStart">
	        		<option value="">请选择</option>
	        		<c:choose>
	          			<c:when test="${empty sessionScope.tms_user_session.outletsId}">
	          				<c:forEach var="startOutlets" items="${startOutlets }">
								<option value="${startOutlets.id }">${startOutlets.name }</option>		          		
			          		</c:forEach>
	          			</c:when>
	          			<c:otherwise>
	          				<option value="${startOutlets.id }">${startOutlets.name }</option>
	          			</c:otherwise>
	          		</c:choose>
	        	</select>
	        	<label>目的地：</label>
	        	<select id="search_end" name="searchEnd">
	        		<option value="">请选择</option>
	        		<c:forEach var="endOutlets" items="${endOutlets }">
						<option value="${endOutlets.id }">${endOutlets.name }</option>
					</c:forEach>
	        	</select>
	        	<label>服务类型：</label>
	        	<select id="condition" name="condition">
	        		<option value="">请选择</option>
	        		<c:forEach var="serverType" items="${serverTypes }">
						<option value="${serverType.id }">${serverType.name }</option>
					</c:forEach>
	        	</select>
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="hidden_dia" class="hide">
	    <form id="add_edit_form" class="form-horizontal">
			<div class="control-group">
		       	<label class="control-label"><s>*</s>发站网点</label>
		        <div class="controls">
		         	<select id="start_outlets" name="startOutlets" style="width:327px" data-rules="{required:true,outletsNotRep:true}" onchange="renderSelect(this,0)">
		         		<option value="">请选择</option>
		          		<%-- <c:choose>
		          			<c:when test="${empty outletsId }">
		          				<c:forEach var="startOutlets" items="${startOutlets }">
									<option value="${startOutlets.id }">${startOutlets.name }</option>	
				          		</c:forEach>
		          			</c:when>
		          			<c:otherwise>
		          				<option value="${startOutlets.id }">${startOutlets.name }</option>
		          			</c:otherwise>
		          		</c:choose> --%>
		          		<c:forEach var="endOutlets" items="${endOutlets }">
							<option value="${endOutlets.id }">${endOutlets.name }</option>
						</c:forEach>
		         	</select>
		         	<input id="outlets_id" type="hidden" name="outletsId"/>
		        </div>
		 	</div>
		   	<div class="control-group">
		      	<label class="control-label"><s>*</s>到站网点</label>
		        <div class="controls">
		          	<select id="end_outlets" style="width:327px" name="endOutlets" data-rules="{required:true,outletsNotRep:true}" onchange="renderSelect(this,1)">
		          		<option value="">请选择</option>
						<c:forEach var="endOutlets" items="${endOutlets }">
							<option value="${endOutlets.id }">${endOutlets.name }</option>
						</c:forEach>	
		          	</select>
		        </div>
			</div>
			<div class="control-group">
		        <label class="control-label"><s>*</s>服务类型</label>
		        <div class="controls">
		          <select id="server_type" style="width:327px" name="serverType" data-rules="{required:true}">
					<option value="">请选择</option>
					<c:forEach var="serverType" items="${serverTypes }">
						<option value="${serverType.id }">${serverType.name}</option>
					</c:forEach>
		          </select>
		        </div>
	      	</div>
	       	<div class="control-group">
		        <label class="control-label">起始地</label>
		        <div class="controls">
		          <select id="start_province" style="width:102px" class="input-small" disabled="disabled"></select>
		        </div>
		        <div class="controls">
		          <select id="start_city" style="width:102px" class="input-small" disabled="disabled"></select>
		        </div>
		        <div class="controls">
		          <select id="start_county" style="width:102px" class="input-small" disabled="disabled"></select>
		        </div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label">目的地</label>
		        <div class="controls">
		          	<select id="end_province" style="width:102px" class="input-small" disabled="disabled"></select>
		        </div>
		        <div class="controls">
		          	<select id="end_city" style="width:102px" class="input-small" disabled="disabled"></select>
		        </div>
		        <div class="controls">
		          	<select id="end_county" style="width:102px" class="input-small" disabled="disabled"></select>
		        </div>
	      	</div>
	   		<div class="control-group">
		        <label class="control-label"><s>*</s>运输方式</label>
		        <div class="controls">
		          <select id="trans_mode" style="width:327px" name="transportMode" data-rules="{required:true}">
					<option value="">请选择</option>
					<c:forEach var="transportMode" items="${transportModes }">
						<option value="${transportMode.id }">${transportMode.name}</option>
					</c:forEach>
		          </select>
		        </div>
	      	</div>
	      	<div class="control-group">
	        	<label class="control-label"><s>*</s>时效</label>
	        	<div class="controls" style="position:relative">
	          		<input id="time_out" name="timeLong" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-large">
	        		<div style="position:absolute;left:300px;top:5px;color:gray">时</div>
	        	</div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>重货价</label> 
		        <div class="controls">
		          	<input id="heavy_low_p" name="heavyCargoPriceLow" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-small" placeholder="小于1吨单价">-
		          	<input id="heavy_mid_p" name="heavyCargoPriceMid" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-small" placeholder="1吨到3吨单价">-
		          	<input id="heavy_high_p" name="heavyCargoPriceHigh" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-small" placeholder="大于3吨单价">
		        </div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>泡货价</label> 
		        <div class="controls">
		          	<input id="bulky_low_p" name="bulkyCargoPriceLow" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-small" placeholder="小于3立方单价">-
		          	<input id="bulky_mid_p" name="bulkyCargoPriceMid" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-small" placeholder="3立方到10立方单价">-
		          	<input id="bulky_high_p" name="bulkyCargoPriceHigh" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-small" placeholder="大于10立方单价">
		        </div>
	      	</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>最低运价</label>
		        <div class="controls" style="position:relative">
		          	<input id="lowest_p" name="lowestPrice" type="text" data-rules="{required:true,regexp:/^\d+(\.\d+)?$/}" data-messages="{regexp:'不是有效的数字'}" class="input-large">
		        	<div style="position:absolute;left:300px;top:5px;color:gray">元</div>
		        </div>
	      	</div>
	       	<div class="control-group">
	        	<label class="control-label">&nbsp;</label>
	        	<div class="controls">
	        		<label class="checkbox"><input id="release_state" type="checkbox" name="releaseState" value="1">是否发布</label>
	        	</div>
		  	</div>
	     </form>
	     <input id="outlets_id_hide" type="hidden" value="${sessionScope.tms_user_session.outletsId }"> 
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	
		$(function(){
			$("#start_outlets").bind("change",function(){
				$("#outlets_id").val($("#start_outlets").val());
			})
		})
		
		//查询
		function search(){
			var params = {
					searchStart:$("#search_start").val(),
					searchEnd:$("#search_end").val(),
					condition:$("#condition").val()
			};
			store.load(params);
		}
        
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/form','bui/tooltip','bui/overlay'],
					function(Grid,Data,Toolbar,Format,Form,Tooltip,Overlay){
			Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '所属网点',elCls:'center',renderer:function(val,obj){
					if(obj.outletsInfo){
						return obj.outletsInfo.name;
					}else{
						return "";
					}
					
				}},
				{title : '线路',elCls:'center',width:200,renderer:function(val,obj){
					return '<div  style="text-align:left;padding-left:20px"><div>从：'+obj.startOutletsObj.provinceValue+"&nbsp;&nbsp;"+obj.startOutletsObj.cityValue+"&nbsp;&nbsp;"+obj.startOutletsObj.countyValue+'</div><div>到：'
							+obj.endOutletsObj.provinceValue+"&nbsp;&nbsp;"+obj.endOutletsObj.cityValue+"&nbsp;&nbsp;"+obj.endOutletsObj.countyValue+"</div></div>"
				}},
				{title : '运输方式',elCls:'center',dataIndex :'transModeValue',},
				{title : '服务类型',elCls:'center',dataIndex :'serverTypeValue'},
				{title : '干线运价',elCls:'center',width:150,renderer:function(val,obj){
					var objStr = BUI.JSON.stringify(obj).replace(/\"/g,"'");
					return '<div style="text-align:left;padding-left:20px;"><div><div>重货价：<span class="grid-command heavy_cargo" data-title="'+objStr+'">￥'+obj.heavyCargoPriceLow+'/吨</span></div><div>泡货价：<span class="grid-command bulky_cargo" data-title="'+objStr+'">￥'
							+obj.bulkyCargoPriceLow+'/立方</span></div><div>最低价：￥'
							+obj.lowestPrice +"/票</div></div></div>";
				}},
			 	{title : '时效/时',elCls:'center',dataIndex : 'timeLong'},
			 	{title : '对外发布', elCls:'center',dataIndex : 'releaseState',renderer:function(val){
			 		if(val==0){
			 			return "否";
			 		}else if(val==1){
			 			return "是";
			 		}
			 	}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/lineinfo/search.shtml',
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
				plugins : [Grid.Plugins.CheckSelection],
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
				//$("#add_edit_form")[0].reset();
				if(!$("#outlets_id_hide").val()){
					$(':input','#add_edit_form').not(':button, :submit, :reset, :hidden') 
										.val('') 
										.removeAttr('checked') 
										.removeAttr('selected');
					var startOutletsSel = {value:$("select[name='startOutlets']").val()},
					endOutletsSel = {value:$("select[name='endOutlets']").val()};
					renderSelect(startOutletsSel,0);
					renderSelect(endOutletsSel,1);
					//editing.add();
					dialog.set("editType","add");
					dialog.show();
				}else{
					BUI.Message.Alert("你没有权限执行当前操作","warning");
				}
			}
			//编辑线路
			function editLine(){
				var selects = grid.getSelection();
				if(!$("#outlets_id_hide").val()){
					if(selects.length == 1){
						var startOutletsSel = {value:selects[0].startOutlets},
						endOutletsSel = {value:selects[0].endOutlets};
						$("#start_outlets option").each(function(){
							if($(this).val()==selects[0].startOutlets){
								$(this).attr("selected",true);
							}
						})
						$("#end_outlets option").each(function(){
							if($(this).val()==selects[0].endOutlets){
								$(this).attr("selected",true);
							}
						})
						$("#trans_mode option").each(function(){
							if($(this).val()==selects[0].transportMode){
								$(this).attr("selected",true);
							}
						})
						$("#server_type").val(selects[0].serverType);
						$("#time_out").val(selects[0].timeLong);
						$("#heavy_low_p").val(selects[0].heavyCargoPriceLow);
						$("#heavy_mid_p").val(selects[0].heavyCargoPriceMid);
						$("#heavy_high_p").val(selects[0].heavyCargoPriceHigh);
						$("#bulky_low_p").val(selects[0].bulkyCargoPriceLow);
						$("#bulky_mid_p").val(selects[0].bulkyCargoPriceMid);
						$("#bulky_high_p").val(selects[0].bulkyCargoPriceHigh);
						$("#lowest_p").val(selects[0].lowestPrice);
						renderSelect(startOutletsSel,0);
						renderSelect(endOutletsSel,1);
						if(selects[0].releaseState == 1){
							$("#release_state").prop("checked",true);
						}
						dialog.set("editType","edit");
						dialog.set("record",selects[0]);
						dialog.show();
						//editing.edit(selects[0]);
					}else{
						BUI.Message.Alert("请选择一条记录","warning");
					}
				}else{
					BUI.Message.Alert("你没有权限执行当前操作","warning");
				}
			}
			//删除线路
			function deleteLine(){
				if(!$("#outlets_id_hide").val()){
			        var selects = grid.getSelection();
			        var lng = selects.length;
			        if(lng == 1){
			        	BUI.Message.Confirm("确认删除",function(){
				        	$.ajax({
				        		url:'<%=request.getContextPath()%>/tms/lineinfo/delete.shtml',
				        		data:{lineId:selects[0].id},
				        		type:'post',
				        		dataType:'json',
				        		success:function(data){
				        			if(data.result==true){
				        				BUI.Message.Alert("线路删除成功",function(){
				        					store.load();
				        				},"warning");
				        				
				        			}else{
				        				BUI.Message.Alert(data.msg,"warning");
				        			}
				        		}
				        	})
			        	}, 'question')
			        }else{
			        	BUI.Message.Alert("请选择一条要删除的数据","warning");
			        }
				}else{
					BUI.Message.Alert("你没有权限执行当前操作","warning");
				}
			}
			//验证用户是否存在
			Form.Rules.add({
		      	name : 'outletsNotRep',  //规则名称
		        msg : '起始网点和到站网点不能相同',//默认显示的错误信息
		        validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息
		        	var startOut =  $("#start_outlets").val();
		        	var endOut =  $("#end_outlets").val();
		        	if(startOut!=null&&startOut!=''&&endOut!=null&&endOut!=''){
		          		if(startOut==endOut){
		          			return formatMsg;
		          		}
		          	}
		        	
		        }
		 	}); 
			var form = new Form.Form({
	        	srcNode:"#add_edit_form"
	        }).render();
			 var dialog = new Overlay.Dialog({
		            title:'线路管理',
		            width:600,
		            height:500,
		            //配置DOM容器的编号
		            contentId:'hidden_dia',
		            success:function () {
		            	var dia = this,
	        		  		editType = dia.get("editType"),
	        		  		data = $("#add_edit_form").serialize(),
	        		  		url = "";
	        		  	if(editType=="edit"){
	        		  		url = "<%=request.getContextPath()%>/tms/lineinfo/update.shtml";
	        		  		data += "&id="+dia.get("record").id+"&startOutletsName="+$("select[name='startOutlets']").find("option:selected").text()
	        		  			+"&endOutletsName="+$("select[name='endOutlets']").find("option:selected").text();
	        		  	}else if(editType == "add"){
	        		  		url = "<%=request.getContextPath()%>/tms/lineinfo/insert.shtml";
	        		  		data += "&startOutletsName="+$("select[name='startOutlets']").find("option:selected").text()
	        		  			+"&endOutletsName="+$("select[name='endOutlets']").find("option:selected").text();
	        		  	}
	        		  	form.valid();
	        		  	if(form.isValid()){
	        		  		$.ajax({
		        			 	url:url,
		        			 	data:data,
		        			 	type:'post',
		        			  	success:function(data){
		        					if(data.result){
		        						BUI.Message.Alert("信息提交成功",function(){
		        							dialog.close();
		        							store.load();
		        							$("#start_province").empty();
		        							$("#start_city").empty();
		        							$("#start_county").empty();
		        							$("#end_province").empty();
		        							$("#end_city").empty();
		        							$("#end_county").empty();
			        						
		        						},"success");
		        					}else{
		        						BUI.Message.Alert(data.msg,"warning");
		        					}
					        	},
					        	error:function(){
					        		  alert("系统错误");
					        		  editor.close();
					        	}
	        		  		})
	        		  	}
		            }
		        });
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
		//加载select
		function renderSelect(s_obj,type){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/lineinfo/rdxzqh.shtml',
				data:{outletsId:s_obj.value},
				type:'post',
				success:function(result){
					if(result.result){
						var data = result.data;
						if(type == 0){
							$("#start_province").html('<option>'+data.provinceValue+'</option>');
							$("#start_city").html('<option>'+data.cityValue+'</option>');
							$("#start_county").html('<option>'+data.countyValue+'</option>');
						}else if(type == 1){
							$("#end_province").html('<option>'+data.provinceValue+'</option>');
							$("#end_city").html('<option>'+data.cityValue+'</option>');
							$("#end_county").html('<option>'+data.countyValue+'</option>');
						}
					}
				}
			})
		}
	</script>
</body>
</html>