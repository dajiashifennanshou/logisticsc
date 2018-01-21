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
           	<h2>司机管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	          	<input id="condition" name="condition" type="text" placeholder="司机姓名">
		        <button type="button" class="button button-normal" onclick="search()"><i class="icon-search"></i>查询</button>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="hidden_dia" class="hide">
	    <form id="add_edit_form" action="" class="form-horizontal">
     		<div class="control-group">
        		<label class="control-label"><s>*</s>司机姓名</label>
        		<div class="controls">
			         <input name="driverName" type="text" data-rules="{required:true}" class="input-normal"/>
		        </div>
      		</div>
      		<div class="control-group">
	        	<label class="control-label"><s>*</s>驾龄</label>
	        	<div class="controls" style="position:relative">
		          	<input name="driverAge" type="text" data-rules="{required:true,number:true}" class="input-normal">
	        		<div style="position:absolute;width:40px;height:27px;top:5px;left:130px;text-align:center;">年</div>
	        	</div>
      		</div>
	      	<div class="control-group">
		        <label class="control-label"><s>*</s>驾驶证类型</label>
		        <div class="controls">
		          	<select id="driver_license_type" name="driverLicenseType" data-rules="{required:true}" style="width:167px">
		       		
		          	</select>
	        	</div>
	      	</div>
       		<div class="control-group">
		        <label class="control-label"><s>*</s>驾驶证号</label>
		        <div class="controls">
			         <input name="driverLicenseNo" type="text" class="input-normal" data-rules="{required:true,regexp:/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/}" data-messages="{regexp:'驾驶证号不正确'}">
		        </div>
		   	</div>
	       	<div class="control-group">
		        <label class="control-label"><s>*</s>身份证</label>
		        <div class="controls">
			      	<input name="idCard" type="text" class="input-normal" data-rules="{required:true,regexp:/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/}" data-messages="{regexp:'身份证号不正确'}"/>
		        </div>
	      	</div>
	       	<div class="control-group">
	        	<label class="control-label"><s>*</s>手机号</label>
	        	<div class="controls">
	          		<input name="phoneNumber" type="text" class="input-normal" data-rules="{required:true,regexp:/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/}" data-messages="{regexp:'手机号不正确'}"/>
	        	</div>
	      	</div>
	      	<div class="control-group">
	        	<label class="control-label">开户行</label>
	        	<div class="controls">
	          		<input name="bankName" type="text" class="input-normal"/>
	        	</div>
	      	</div>
	       	<div class="control-group">
	        	<label class="control-label">银行卡号</label>
	        	<div class="controls">
	          		<input name="bankNumber" type="text" class="input-normal" data-rules="{v_bkc:true}"/>
	        	</div>
	      	</div>
	       	<div class="control-group">
	        	<label class="control-label">地址</label>
	        	<div class="controls">
	          		<input name="address" type="text" class="input-normal"/>
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
			var params={
				condition:$("#condition").val()
			}
			store.load(params);
		}
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/select','bui/form'],
					function(Grid,Data,Toolbar,Select,Form){
			var Grid = Grid,
				Store = Data.Store,
				columns = [
					{title : '所属网点', elCls:'center',renderer:function(val,obj){
						return obj.outletsInfo.name;
					}},
					{title : '姓名',elCls:'center', dataIndex : 'driverName'},
					{title : '驾龄', elCls:'center',dataIndex : 'driverAge'},
					{title : '手机号', elCls:'center',dataIndex : 'phoneNumber'},
					{title : '身份证',elCls:'center',dataIndex :'idCard'},
					{title : '驾驶证类型',elCls:'center',dataIndex :'driverLicenseTypeVal'},
					{title : '驾驶证号', elCls:'center',dataIndex : 'driverLicenseNo'},
				];
				editing = new Grid.Plugins.DialogEditing({
			          contentId : 'hidden_dia', //设置隐藏的Dialog内容
			          triggerCls : 'btn-edit', //触发显示Dialog的样式
			          editor:{
			        	  title:'司机管理',
			        	  width:450,
			        	  height:400,
			        	  success:function(){
			        			var editor = this,
			        				form = editor.get("form"),
			        		  		editType = editing.get("editType"),
			        		  		url = "";
			        		  	if(editType=="edit"){
			        		  		url = "<%=request.getContextPath()%>/tms/driver/update.shtml";
			        		  	}else if(editType == "add"){
			        		  		url = "<%=request.getContextPath()%>/tms/driver/insert.shtml";
			        		  	}
			        		  	form.valid();
			        		  	if(form.isValid()){
			        		  		form.ajaxSubmit({
				        			 	url:url,
				        			 	type:'post',
				        			  	success:function(data){
				        					if(data.result){
				        						editor.close();
				        						BUI.Message.Alert("信息提交成功",function(){
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
			editing.on('editorready',function(e){
				renderLicenseType()
			})
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/driver/search.shtml',
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
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler: addDriver
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler: editDriver
			          	},{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		handler: deleteDriver
			          	}
		          	],
				}
				
			});
			function addDriver(){
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/sys/isOutlets.shtml',
					type:'post',
					success:function(result){
						if(result.result){
							renderLicenseType();
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
			function editDriver(){
				$.ajax({
					url:"${configProps['project']}/tms/sys/isOutlets.shtml",
					type:'post',
					success:function(result){
						if(result.result){
							var selects = grid.getSelection();
							if(selects.length==1){
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
			function deleteDriver(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		data.push(selects[i].id);
		        	}
		        	BUI.Message.Confirm("确定要删除？",function(){
		        		$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/driver/delete.shtml',
			        		data:{driverId:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result==true){
			        				BUI.Message.Alert("该记录已成功被删除",function(){
			        					store.load();
			        				},"success");
			        			}
			        		}
			        	})
		        	})
		        }
			}
			
			/***********************/
			var bkcReg = /^(\d{16}|\d{19})$/;//验银行卡号
	         
	        //验证银行卡号
	    	Form.Rules.add({
	      		name : 'v_bkc',  //规则名称
	            msg : '银行卡号错误',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	            	if(value != null && value != ''){
	              		if(!bkcReg.test(value)){
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
			var form = new Form.Form({
				srcNode:'#add_edit_form'
			})
		});	
		
		//日期加载
		BUI.use('bui/calendar',function(Calendar){
	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	    });
		//渲染驾驶证类型
		function renderLicenseType(){
			$.ajax({
				url:'<%=request.getContextPath()%>/dic/licensetype.shtml',
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.result == true){
						var rows = data.rows,
							lcs_type = $("#driver_license_type");
						lcs_type.empty();
						for(var i=0;i<rows.length;i++){
							lcs_type.append("<option value='"+rows[i].id+"'>"+rows[i].name+"</option>");
						}
					}
				}
			})
		}
	</script>
</body>
</html>