<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="-1"/>
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
           	<h2>网点管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="网点名称，联系人姓名">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
   
    <!-- 默认隐藏 -->
    <div id="outlets_hide" class="hide">
    	<form id="outlets_form"  class="form-horizontal">
    		<div class="control-group">
    			<label class="control-label">公司名称：</label>
    			<div class="controls">
    				 <span class="control-text">${company.companyName}</span>
    			</div>
    		</div>
    		<div class="control-group">
    			<label class="control-label"><s>*</s>网点名称：</label>
    			<div class="controls">
    				<input name="name" type="text" class="input-large" data-rules="{required:true}" 
    				 value="${company.companyName }"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label"><s>*</s>网点类型：</div>
    			<div class="controls">
    				<select id="type" name="type" style="width:325px" data-rules="{required:true}" 
    					data-messages="{required:'请选择网点类型'}">
			          	<option value="">请选择</option>
			          	<c:forEach	var="outletsType" items="${outletsTypes }"> 
			          		<option value="${outletsType.id }">${outletsType.name}</option>
			          	</c:forEach>
			       	</select>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label"><s>*</s>网点性质：</div>
    			<div class="controls">
    				<select id="nature" name="nature" style="width:325px" data-rules="{required:true}" 
    					data-messages="{required:'请选择网点性质'}">
			          	<option value="">请选择</option>
			          	<c:forEach var="outletsNature" items="${outletsNatures }">
			          		<option value="${outletsNature.id }">${outletsNature.name }</option>
			          	</c:forEach>
			       	</select>
    			</div>
    		</div>
	    	<div class="control-group">
	        	<label class="control-label"><s>*</s>网点区域：</label>
		        <div class="controls">
			      	<select id="province_outlets" style="width:102px" class="input-small" name="province" data-rules="{required:true}" data-messages="{required:'选项不能为空'}" onchange="ajaxCity()"><option value="">请选择</option></select>&nbsp;&nbsp;
			      	<select id="city_outlets" style="width:102px" class="input-small" name="city" data-rules="{required:true}" data-messages="{required:'选项不能为空'}" onchange="ajaxCounty()"><option value="">请选择</option></select>&nbsp;&nbsp;
			       	<select id="county_outlets" style="width:102px" class="input-small" name="county" data-rules="{required:true}" data-messages="{required:'选项不能为空'}"><option value="">请选择</option></select>
		        </div>
	      	</div>
    		<div class="control-group">
    			<div class="control-label"><s>*</s>联系人：</div>
    			<div class="controls">
			       	<input name="contactPerson" type="text" class="input-large" data-rules="{required:true}" 
			       		data-messages="{required:'请填写联系人姓名'}"/>
    			</div>
    		</div><div class="control-group">
    			<div class="control-label"><s>*</s>手机号：</div>
    			<div class="controls">
			       	<input name="phone" type="text" class="input-large" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" 
			       		data-messages="{regexp:'手机号不正确'}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">固定电话：</div>
    			<div class="controls">
			       	<input name="mobile" type="text" class="input-large"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label"><s>*</s>详细地址：</div>
    			<div class="controls">
			       	<input name="address" type="text" class="input-large" data-rules="{required:true}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">电子邮箱：</div>
    			<div class="controls">
			       	<input name="email" type="text" class="input-large" data-rules="{required:false,email:false}" data-messages="{email:'邮箱不正确'}"/>
    			</div>
    		</div>
    		<div class="control-group">
    			<div class="control-label">备注：</div>
    			<div class="controls control-row-auto">
          			<textarea name="remark" class="control-row4 input-large"></textarea>
    			</div>
    		</div>
    		<div class="control-group">
			    <input name="id" type="hidden"/>
    		</div>
    	</form>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		$(function(){
			initProvince();
		})
		//查询
		function search(){
			var params={
				condition:$("#condition").val()
			}
			store.load(params);
		}
		var message;
		//表格渲染
		BUI.use(['bui/form','bui/grid','bui/data','bui/toolbar','bui/format','overlay'],
					function(Form,Grid,Data,Toolbar,Format,Overlay){
			message = BUI.Message;
			var Grid = Grid,
				Store = Data.Store,
				columns = [
					{title : '专线/物流商',elCls:'center', width:180,dataIndex : 'companyName'},
					{title : '组织代码',elCls:'center', dataIndex : 'companyCode'},
					{title : '网点名称',elCls:'center',width:120,dataIndex :'name'},
					{title : '网点编号',elCls:'center',dataIndex :'outletsNumber'},
					{title : '创建日期',elCls:'center',width:180,dataIndex : 'createTime',renderer:Grid.Format.datetimeRenderer},
					{title : '地址', elCls:'center',width:200,dataIndex : 'address'},
					{title : '联系人',elCls:'center',dataIndex :'contactPerson'},
				 	{title : '联系人电话',elCls:'center',width:120,dataIndex : 'phone'},
				 	{title : '固定电话',elCls:'center',width:120,dataIndex : 'mobile'},
				 	{title : '邮箱', elCls:'center',width:120,dataIndex : 'email'},
				 	{title : '备注',width:250,elCls:'center',dataIndex : 'remark'},
				 	/* {title : '是否启用',elCls:'center',dataIndex : 'status',renderer:function(val){
				 		if(val==1){
							return '已启用';
						}else{
							return '已停用';
						}
				 	}}, */
				 	/* {title : '操作',elCls:'center',dataIndex : 'status',renderer:function(val,obj){
				 		var update;
							if(val==1){
								update  = "<a  href='javascript:void(0);' onclick='forbidOutlets("+obj.id+")' class='grid-command btn-del'><i class='icon icon-remove'></i> 停用</a>";
							}else{
								update  = "<a  href='javascript:void(0);' onclick='enableOutlets("+obj.id+")' class='grid-command btn-del'><i class='icon icon-ok'></i> 启用</a>";
							}
							return update;
				 	}}, */
				];
			editing = new Grid.Plugins.DialogEditing({
		          contentId : 'outlets_hide', //设置隐藏的Dialog内容
		          triggerCls : 'btn-add', //触发显示Dialog的样式
		          editor:{
		        	  title:'网点管理',
		        	  width:600,
		        	  height:500,
		        	  elCls:'center',
		        	  success:function(){
		        			var editor = this,
		        				form = editor.get("form"),
		        		  		editType = editing.get("editType"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/tms/outlets/update.shtml";
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/tms/outlets/insert.shtml";
		        		  	}
		        		  	form.valid();
		        		  	if(form.isValid()){
		        		  		form.ajaxSubmit({
			        			 	url:url,
			        			 	type:'post',
			        			  	success:function(data){
			        					if(data.result){
			        						BUI.Message.Alert('信息已成功提交',function(){
			        							editor.clearValue();
			        							editor.close();
				        						store.load();
			        						},'success');
			        					}
						        	},
						        	error:function(){
						        		BUI.Message.Alert('网络错误',function(){
						        			editor.clearValue();
							        		editor.close();
						        		},'warning');
						        	}
		        		  		})
		        		  	}
		        			
		        	  }
		          }
		   	});
			editing.on('editorshow',function(e){
				if(editing.get("editType")=="edit"){
					var pcc = editing.get("record");
					ajaxCity(pcc.province,pcc.city);
					ajaxCounty(pcc.city,pcc.county);
				}
			})
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/outlets/search.shtml',
				type:'post',
				autoLoad:true,
				pageSize:10,
				proxy:{
					method:'post',
				}
			});
			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				//forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler: addOutlets
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler: editOutlets
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		handler: deleteOutlets
			          	}
		          	],
				}
				
			});
			//新增
			function addOutlets(){
				<c:if test="${empty sessionScope.tms_user_session.outletsId}">
					editing.add(); 
					return ;
				</c:if>
				BUI.Message.Alert("你没有权限","warning");
				
			}
			//编辑
			function editOutlets(){
				<c:if test="${empty sessionScope.tms_user_session.outletsId}">
					var selects = grid.getSelection(),
						len = selects.length;
					if(len==1){
						editing.edit(selects[0]);
					}else{
				        BUI.Message.Alert('请选择一条记录','warning');
					}	
				
					return ;
				</c:if>
				BUI.Message.Alert("你没有权限","warning"); 
				
			}
			//删除
			function deleteOutlets(){
				<c:if test="${empty sessionScope.tms_user_session.outletsId}">
					var selects = grid.getSelection();
			        var lng = selects.length;
			        BUI.Message.Confirm("确定要删除？",function(){
				        if(lng>0){
				        	var data = [];
				        	for(var i=0;i<lng;i++){
				        		data.push(selects[i].id);
				        	}
			        		$.ajax({
				        		url:'<%=request.getContextPath()%>/tms/outlets/delete.shtml',
				        		data:{outletsId:data},
				        		type:'post',
				        		dataType:'json',
				        		success:function(data){
				        			if(data.result){
				        				BUI.Message.Alert('删除成功',function(){
				        					store.load();
				        				},'success');
				        			}else{
				        				BUI.Message.Alert(data.msg,"warning");
				        			}
				        		}
				        	})
				        }
			        })
					return ;
				</c:if>
				BUI.Message.Alert("你没有权限","warning"); 
		        
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
		
		function initProvince(){
			var province=$("#province_outlets").val();
			$("#province_outlets").empty();
			$.ajax({
				url:"${configProps['project']}/tms/outlets/ajax/xzqh.shtml",
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.result==true){
						$("#province_outlets").append("<option value=''>请选择</option>"); 
						$.each(data.data,function(i){
							$("#province_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
						})
					}
				},
				error:function(data){
					alert("系统错误");
				}	
			})
		}
		function ajaxCity(val,cityVal){
			var dta = {};
			if(val){
				data={pid:val};
			}else{
				var province=$("#province_outlets").val();
				data={pid:province};
			}
			$("#city_outlets").empty();
			$("#county_outlets").empty();
			$.ajax({
				url:"${configProps['project']}/tms/outlets/ajax/xzqh.shtml",
				data:data,
				type:"post",
				async:false,
				dataType:"json",
				success:function(data){
					if(data.result==true){
						$("#city_outlets").append("<option value=''>请选择</option>"); 
						$.each(data.data,function(i){
							if(data.data[i].id==cityVal){
								$("#city_outlets").append("<option value='"+data.data[i].id+"' selected>"+data.data[i].name+"</option>");
							}else{
								$("#city_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
							}
							
						})
					}
				},
				error:function(data){
					alert("系统错误");
				}	
			})
		}
		
		function ajaxCounty(val,countyVal){
			var data = {};
			if(val){
				data={pid:val};
			}else{
				city=$("#city_outlets").val();
				data={pid:city};
			}
			$("#county_outlets").empty();
			$.ajax({
				url:"${configProps['project']}/tms/outlets/ajax/xzqh.shtml",
				data:data,
				type:"post",
				dataType:"json",
				success:function(data){
					$("#county_outlets").append("<option value=''>请选择</option>"); 
					if(data.result==true){
						$.each(data.data,function(i){
							if(countyVal==data.data[i].id){
								$("#county_outlets").append("<option value='"+data.data[i].id+"' selected>"+data.data[i].name+"</option>");
							}else{
								$("#county_outlets").append("<option value='"+data.data[i].id+"'>"+data.data[i].name+"</option>");
							}
							
						})		
					}
				},
				error:function(data){
					alert("系统错误");
				}	
			})
		}
		
		function forbidOutlets(id){
			message.Confirm("确定要禁用？</br>禁用此网点将导致该网点下用户无法正常使用tms后台管理！",function(){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/outlets/forbid.shtml',
					type:'post',
					data:{outletsId:id},
					success:function(result){
						if(result.result){
							message.Alert("该网点已禁用",function(){
								store.load();
							},"success");
						}
					}
				})
			})
			
		}
		
		function enableOutlets(id){
			message.Confirm("确定要启用？",function(){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/outlets/enable.shtml',
					type:'post',
					data:{outletsId:id},
					success:function(result){
						if(result.result){
							message.Alert("该角色已启用",function(){
								store.load();
							},"success");
						}
					}
				})
			})
		}
	</script>
</body>
</html>