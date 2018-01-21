<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>险种费用管理</title>
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
           	<h2>险种费用管理</h2>
        </div>
        <div class="panel-body">
        	<form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="保险公司，险种">
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
    			<label class="control-label"><s>*</s>保险公司：</label>
    			<div class="controls">
    				 <select id="ins_com" name="insCompanyId" data-rules="{required:true,v_insRate:true}" onchange="getTsT(this);">
    				 	<option value="">请选择</option>
    				 	<c:forEach var="insCompany" items="${insCom }">
    				 		<option value="${insCompany.id }" rel="${insCompany.insComTag }">${insCompany.insComName }</option>
    				 	</c:forEach>
    				 </select>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>费率类型：</label>
    			<div class="controls">
    				<select id="fee_type" onchange="getTsT(this);">
    					<option value="">请选择</option>
    					<option value="0">按险种计费</option>
    					<option value="1">按特殊类型计费</option>
    				</select>
    			</div>
    		</div>
    		<div id="type_div" class="control-group hide">
    			<label class="control-label"><s>*</s>保险险种：</label>
    			<div class="controls">
    				<select id="ins_type" data-rules="{v_insRate:true}" name="insTypeId">
    					
    				</select>
    			</div>
    		</div>
    		<div id="ts_type_div" class="control-group hide">
    			<label class="control-label"><s>*</s>特殊类型：</label>
    			<div class="controls">
    				<select id="ins_ts_type" data-rules="{v_insRate:true}" name="insTsTypeId">
    				</select>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>费率：</label>
    			<div class="controls">
    				<input name="insRate" type="text" class="input-normal" data-rules="{required:true}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>最低消费：</label>
    			<div class="controls">
    				<input name="insLowestPrice" type="text" class="input-normal" data-rules="{required:true}"/>
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
				{title : '保险公司',elCls:'center',renderer:function(val,obj){
					if(obj.insuranceCompany){
						return obj.insuranceCompany.insComName;	
					}else{
						return '';
					}
					
				}},
				{title : '险种',elCls:'center',renderer:function(val,obj){
					if(obj.insType){
						return obj.insType.insTypeName;
					}else{
						return '';
					}
				}},
				{title : '特殊类型',elCls:'center',renderer:function(val,obj){
					if(obj.insTsType){
						return obj.insTsType.insTsTypeName; 
					}else{
						return '';
					}
				}}, 
				{title : '费率',elCls:'center',dataIndex :'insRate'},
				{title : '最低消费',elCls:'center',dataIndex :'insLowestPrice'},
				{title : '创建日期',elCls:'center',dataIndex :'createTime',renderer:Grid.Format.datetimeRenderer},
			];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'dia_hide', //设置隐藏的Dialog内容
		          triggerCls : 'btn-add', //触发显示Dialog的样式
		          editor:{
		        	  title:'特殊类型管理',
		        	  width:400,
		        	  height:350,
		        	  success:function(){
		        			var editor = this,
		        				form = editor.get("form"),
		        		  		editType = editing.get("editType"),
		        		  		data = $("#ins_ts_type_form").serialize(),
		        		  		record = editing.get("record"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/system/ins/addInsFee.shtml";
		        		  		data += "&id="+record.id;
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/system/ins/addInsFee.shtml";
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
			editing.on("editorshow",function(){
				var record = editing.get("record");
				if(record.insType){
					$("#fee_type option").each(function(){
						if($(this).val() == 0){
							$(this).attr("selected",true);
						}
					})
					getTsT();
 					$("#ins_type option").each(function(){
						if($(this).val() == record.insTypeId){
							$(this).attr("selected",true);
						}
					})
					$("#ins_type").css("display","block");
				}
				if(record.insTsType){
					$("#fee_type option").each(function(){
						if($(this).val() == 1){
							$(this).attr("selected",true);
						}
					})
					getTsT();
					$("#ins_ts_type option").each(function(){
						if($(this).val() == record.insTsTypeId){
							$(this).attr("selected",true);
						}
					})
					$("#ins_ts_type").css("display","block");
				}
			})
			editing.on("cancel",function(){
				$("#ins_ts_type_form input","#ins_ts_type_form select option")
					.val("")
					.removeAttr("selected");
				//$("#ins_ts_type_form");
			})
			store = new Store({
				url: '<%=request.getContextPath()%>/system/ins/searchInsFee.shtml',
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
				plugins : [Grid.Plugins.CheckSelection,editing],
			      tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler:addInsRate
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler:editInsRate
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-del"></i>删除',
			           		handler:delInsRate
			          	}
		          	],
				}
			});
			
			//验证标签是否存在
	    	Form.Rules.add({
	      		name : 'v_insRate',  //规则名称
	            msg : '该费率已存在',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	            	if(editing.get("editType")=='add'){
	            		var t = $("#fee_type").val(),
	            			comTag = $("#ins_com :selected").attr("rel"),
	            			typeTag = $("#ins_type :selected").attr("rel"),
	            			tsTypeTag = $("#ins_ts_type :selected").attr("rel");
	            		if(t == 0){
	            			if(comTag != null && comTag !=''
	            					&&typeTag != null && typeTag != ''){
	            				var flag = false;
			              		$.ajax({
			              			url:'<%=request.getContextPath()%>/system/ins/existInsRate.shtml',
			              			type:'post',
			              			async:false,
			              			data:{comTag:comTag,typeTag:typeTag},
			              			success:function(result){
			              				if(result.result){
			              					flag = result.data;
			              				}
			              			}
			              		})
	            				if(flag){
	            					return formatMsg;
	            				}
			              	}
	            		}else if(t == 1){
	            			if(comTag != null && comTag !=''
            					&&tsTypeTag != null && tsTypeTag != ''){
	            				var flag = false;
			              		$.ajax({
			              			url:'<%=request.getContextPath()%>/system/ins/existInsRate.shtml',
			              			type:'post',
			              			async:false,
			              			data:{comTag:comTag,tsTypeTag:tsTypeTag},
			              			success:function(result){
			              				if(result.result){
			              					if(result.data){
			              						flag = result.data;
			              					}
			              				}
			              			}
			              		})
			              		if(flag){
			              			return formatMsg; 
			              		}
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
		
		function addInsRate(){
			editing.add();
		}
		
		function editInsRate(){
			var selects = grid.getSelection();
			if(selects.length==1){
				getTsT();
				editing.edit(selects[0]);
			}else{
				BUI.Message.Alert("请选择一条记录","warning");
			}
		}
		
		function delInsRate(){
			var selects = grid.getSelection();
			var length = selects.length;
			if(length>0){
				var array = [];
				for(var i=0;i<length;i++){
					array.push(selects[i].id);
				}
				$.ajax({
					url:'<%=request.getContextPath()%>/system/ins/delInsRate.shtml',
					type:'post',
					data:{rateIds:array},
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
		
		function getTsT(){
			var insCom = $("#ins_com").val(),
				feeType = $("#fee_type").val();
			if(insCom!=null&&insCom!=''
					&&feeType!=null&&feeType!=''){
				$.ajax({
					url:"<%=request.getContextPath()%>/system/ins/getInsTsAndT.shtml",
					type:'post',
					data:{companyId:insCom,feeType:feeType},
					async:false,
					success:function(result){
						if(result.result){
							var typeList = result.data.typeList,
								tsTypeList = result.data.tsTypeList,
								instype = '',
								intstype = '';
							if(feeType  == 0){
								$("#ts_type_div").addClass("hide");
								$("#ts_type_div select").attr("disabled",true);
								$("#type_div").removeClass("hide");
								$("#type_div select").attr("disabled",false);
								$("#type_div select").empty();
								$("#ins_type").append("<option value='' rel=''>请选择</option>");
								for (var i = 0; i < typeList.length; i++) {
									$("#ins_type").append("<option value='"+typeList[i].id+"' rel='"+typeList[i].insTypeTag+"'>"+typeList[i].insTypeName+"</option>");
									
								}
							}else{
								$("#type_div").addClass("hide");
								$("#type_div select").attr("disabled",true);
								$("#ts_type_div").removeClass("hide");
								$("#ts_type_div select").attr("disabled",false);
								$("#ts_type_div select").empty();
								$("#ins_ts_type").append("<option value='' rel=''>请选择</option>");
								for (var i = 0; i < tsTypeList.length; i++) {
									$("#ins_ts_type").append("<option value='"+tsTypeList[i].id+"' rel='"+tsTypeList[i].insTsTypeTag+"'>"+tsTypeList[i].insTsTypeName+"</option>");
								}
							}
						}
					}
				})
			}
		}
	</script>
</body>
</html>