<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
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
    .col-sm-2 {
	    width: 16.66666667%;
	  }
	  .btn {
  display: inline-block;
  padding: 6px 12px;
  margin-bottom: 0;
  font-size: 14px;
  font-weight: normal;
  line-height: 1.42857143;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
      touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
}
.btn:focus,
.btn:active:focus,
.btn.active:focus,
.btn.focus,
.btn:active.focus,
.btn.active.focus {
  outline: thin dotted;
  outline: 5px auto -webkit-focus-ring-color;
  outline-offset: -2px;
}
.btn:hover,
.btn:focus,
.btn.focus {
  color: #333;
  text-decoration: none;
}
.btn:active,
.btn.active {
  background-image: none;
  outline: 0;
  -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
          box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
}
.btn.disabled,
.btn[disabled],
fieldset[disabled] .btn {
  cursor: not-allowed;
  filter: alpha(opacity=65);
  -webkit-box-shadow: none;
          box-shadow: none;
  opacity: .65;
}
a.btn.disabled,
fieldset[disabled] a.btn {
  pointer-events: none;
}
.btn-default {
  color: #333;
  background-color: #fff;
  border-color: #ccc;
}
.btn-default:focus,
.btn-default.focus {
  color: #333;
  background-color: #e6e6e6;
  border-color: #8c8c8c;
}
.btn-default:hover {
  color: #333;
  background-color: #e6e6e6;
  border-color: #adadad;
}
.btn-default:active,
.btn-default.active,
.open > .dropdown-toggle.btn-default {
  color: #333;
  background-color: #e6e6e6;
  border-color: #adadad;
}
.btn-default:active:hover,
.btn-default.active:hover,
.open > .dropdown-toggle.btn-default:hover,
.btn-default:active:focus,
.btn-default.active:focus,
.open > .dropdown-toggle.btn-default:focus,
.btn-default:active.focus,
.btn-default.active.focus,
.open > .dropdown-toggle.btn-default.focus {
  color: #333;
  background-color: #d4d4d4;
  border-color: #8c8c8c;
}
.btn-default:active,
.btn-default.active,
.open > .dropdown-toggle.btn-default {
  background-image: none;
}
.btn-default.disabled,
.btn-default[disabled],
fieldset[disabled] .btn-default,
.btn-default.disabled:hover,
.btn-default[disabled]:hover,
fieldset[disabled] .btn-default:hover,
.btn-default.disabled:focus,
.btn-default[disabled]:focus,
fieldset[disabled] .btn-default:focus,
.btn-default.disabled.focus,
.btn-default[disabled].focus,
fieldset[disabled] .btn-default.focus,
.btn-default.disabled:active,
.btn-default[disabled]:active,
fieldset[disabled] .btn-default:active,
.btn-default.disabled.active,
.btn-default[disabled].active,
fieldset[disabled] .btn-default.active {
  background-color: #fff;
  border-color: #ccc;
}
.btn-default .badge {
  color: #fff;
  background-color: #333;
}
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>用户管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline">
	        	<c:if test="${empty sessionScope.tms_user_session.outletsId }">
	        		网点：<input id="outletsName" name="outletsName" type="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        	</c:if>
	          	<input id="condition" name="condition" type="text" placeholder="用户名，姓名">
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
            	<label class="control-label">公司名称：</label>
            	<div class="controls">
              		<input id="company_name" name="companyName" value="${company.companyName }" type="text" class="input-large" disabled/>
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label">公司代码：</label>
            	<div class="controls">
              		<input id="company_code" name="companyCode" value="${company.companyCode }" type="text" class="input-large" disabled/>
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label"><s>*</s>网点：</label>
		        <div class="controls">
	        		<select id="outlets_info" name="outletsId" data-rules="{required:true}" class="input-large" onchange="loadRoleList(this.value)" style="width:327px">
	        			<option value="">请选择</option> 
	        			<c:forEach var="outletsInf" items="${outletsInfoList }">
	        				<option value="${outletsInf.id }">${outletsInf.name }</option>
	        			</c:forEach>
	             	</select>
            	</div>
         	</div>
         	<div class="control-group">
		        <label class="control-label"><s>*</s>角色：</label>
		        <div id="select_role" class="controls" style="width:327px">
					 <input type="hidden" id="hide" class="input-large" name="roleIds" data-rules="{required:true}" style="width:327px">
		        </div>
      		</div>
      		<div class="control-group">
            	<label class="control-label"><s>*</s>真实姓名：</label>
            	<div class="controls">
              		<input id="true_name" name="trueName" type="text" data-rules="{required:true}" class="input-large control-text">
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label"><s>*</s>账号：</label>
            	<div class="controls">
              		<input id="login_name" name="loginName" type="text" data-rules="{checkUser:true}" 
              			class="input-large control-text" placeholder="输入11位手机号">
            	</div>
         	</div>
         	<div class="control-group" id="check_code">
            	<label class="control-label"><s>*</s>验证码：</label>
            	<div class="controls">
              		<input id="checkCode" name="checkCode" type="text" data-rules="{required:true}" class="control-text">
              		<!-- <a onclick="getCheckCode()" href="javascript:void(0)" style="display:inline-block;width:80px;height:26px;background:blue;text-align:center;line-height:26px;color:white;text-decoration:none">获取验证码</a> -->
            		<!-- <div class="btn btn-default" id="sendcode" onclick="getCheckCode()">获取验证码</div>	 -->
            		<div class="col-sm-2" style="display:inline-block">
						<div class="btn btn-default" id="sendcode">获取验证码</div>	
					</div>
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label"><s>*</s>密码：</label>
            	<div class="controls">
              		<input id="password" name="password" type="password" data-rules="{required:true}" class="input-large control-text">
            	</div>
         	</div>
         	<div class="control-group" id="re_password">
            	<label class="control-label"><s>*</s>确认密码：</label>
            	<div class="controls">
              		<input id="repassword" name="repassword" type="password" data-rules="{required:true,verifyPwd:''}" class="input-large control-text">
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label">固定电话：</label>
            	<div class="controls">
              		<input id="phone" name="phone" type="text" data-rules="{v_mobile:true}" class="input-large control-text">
            	</div>
         	</div>
         	<div class="control-group">
            	<label class="control-label">qq：</label>
            	<div class="controls">
              		<input id="qq" name="qq" type="text" class="input-large control-text">
            	</div>
         	</div>
          	<div class="control-group">
            	<label class="control-label">电子邮箱：</label>
            	<div class="controls">
              		<input id="email" name="email" type="text" data-rules="{email:true}" class="input-large control-text">
            	</div>
         	</div>
          		<div class="control-group">
            	<label class="control-label">详细地址：</label>
            	<div class="controls">
              		<input id="address" name="address" type="text" class="input-large control-text">
            	</div>
         	</div>
		</form>
		<input id="tms_login_name" type="hidden" value="${sessionScope.tms_user_session.loginName }"/>
	</div>
	<div id="hidden_dia_char" class="hide">
	    <form id="changeRole" class="form-horizontal">
	    	<div class="control-group">
            	<label class="control-label">当前角色：</label>
            	<div class="controls" id="curRole">
             
            	</div>
         	</div>
         	<div class="control-group">
		        <label class="control-label">角色变更：</label>
		        <div id="changeRoles" class="controls bui-select">
					 <input type="hidden" id="roleVal" name="roleIds" data-rules="{required:true}">
		        </div>
      		</div>
         	<input type="hidden" name="userId" id="userId"/>
	    </form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var roleItems = [], 
			roleSelect, 
			roleItems1=[],
			isSubmit = false,
			isAutoRegister= false,
			isExist = false;
		//查询
		function search(){
			var params = {
					condition:$("#condition").val().trim(),
					outletsName:$("#outletsName").val().trim(),
			};
			store.load(params);
		}
		
		var message;
        BUI.use('bui/overlay',function(overlay){
        	message = BUI.Message;
        });
        
        var message;
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/select','bui/form'],
					function(Grid,Data,Toolbar,Select,Form){
			message = BUI.Message;
			var Grid = Grid,
				Store = Data.Store;
				columns = [
					{title : '组织代码', elCls:'center',renderer:function(val,obj){
						if(obj.platformUserCompany){
							return obj.platformUserCompany.companyCode;
						}else{
							return "";
						}
					}},
					{title : '账号', elCls:'center',width:100,dataIndex : 'loginName'},
					{title : '姓名', elCls:'center',dataIndex : 'trueName'},
					{title : '角色', elCls:'center',width:100,renderer:function(value,obj){
						var role = '';
						if(obj.roleList){
							for(var i=0;i<obj.roleList.length;i++){
								role += obj.roleList[i].roleName + "|";
							}
						}
						return role.substring(0,role.length-1);
					}},
					{title : '所属网点', elCls:'center',width:120,renderer:function(val,obj){
						if(obj.outletsInfo){
							return obj.outletsInfo.name;
						}else{
							return "";
						}
					}},
					{title : '固定电话',elCls:'center',width:100,dataIndex :'phone'},
					{title : 'qq',elCls:'center',width:100,dataIndex :'qq'},
					{title : '邮箱',elCls:'center',width:120,dataIndex :'email'},
					{title : '状态',elCls:'center',dataIndex :'userStatus',renderer:function(val){
						if(val==0){
							return '已禁用';
						}else if(val==1){
							return '已启用';
						}
					}},
					{title : '操作',elCls:'center',dataIndex :'userStatus', width:150,renderer : function(value,obj,index){
						var changeRole = "",
							forbid = "",
							enable = "",
							isOutletsMng = 0;
						for(var i=0;i<obj.roleList.length;i++){
							if(obj.roleList[i].ownerType == '2' || obj.roleList[i].ownerType == '6'){
								isOutletsMng = 1;
								break;
							}
						}
						
						if(isOutletsMng == 0){
							changeRole = "<a href=\"javascript:void(0)\" onclick=\"changeRole("+index+")\"><i class=\"icon-tag\"></i>角色变更</a>";
						}
						if($("#tms_login_name").val() === obj.loginName){
							forbid = "<i class='icon icon-remove'></i> 停用";
						}else{
							if(value==1){
								forbid = "<a  href='javascript:void(0);' onclick='forbid("+obj.loginName+")' class='grid-command btn-del'><i class='icon icon-remove'></i> 停用</a>";
							}else{
								enable = "<a  href='javascript:void(0);' onclick='enable("+obj.loginName+")' class='grid-command btn-del'><i class='icon icon-ok'></i> 启用</a>";
							}
						}
						return forbid + enable + changeRole;
					}}
				];
			
	        roleSelect = new Select.Select({
	          	render:'#select_role',
	          	valueField:'#hide',
	          	multipleSelect:true,
	          	items:roleItems
	        }).render();
	        roleSelect1 = new Select.Select({
	          	render:'#changeRoles',
	          	valueField:'#roleVal',
	          	multipleSelect:true,
	          	items:roleItems1
	        }).render();
	        
			var editing = new Grid.Plugins.DialogEditing({
		          contentId : 'hidden_dia', //设置隐藏的Dialog内容
		          triggerCls : 'btn-edit', //触发显示Dialog的样式
		          editor:{
		        	  title:'用户管理',
		        	  width:600,
		        	  height:500,
		        	  elCls:'center',
		        	  success:function(){
		        			var editor = this,
		        		  		editType = editing.get("editType"),
		        		  		form = editor.get("form"),
		        		  		url = "";
		        		  	if(editType=="edit"){
		        		  		url = "<%=request.getContextPath()%>/tms/user/update.shtml";
		        		  	}else if(editType == "add"){
		        		  		url = "<%=request.getContextPath()%>/tms/user/insert.shtml";
		        		  	}
		        		  	if(isAutoRegister){
		        		  		//form.getField("checkCode").removeRule("required");
		        		  		form.getField("trueName").removeRule("required");
		        		  		form.getField("repassword").removeRule("required");
		        		  		form.getField("repassword").removeRule("verifyPwd");
		        		  	}
		        		  	isSubmit = true;
		        		  	form.valid();
		        		  	if(form.isValid()){
		        		  		var data = $("#add_edit_form").serialize()+"&isAutoRegister="+isAutoRegister;
		        		  		$.ajax({
			        			 	url:url,
			        			 	type:'post',
			        			 	data:data,
			        			  	success:function(data){
			        					if(data.result){
			        						message.Alert("用户添加成功",function(){
			        							//editor.close();
			        							editing.cancel();
			        							editor.clearValue();
					        					store.load();
					        					
					        					form.getField("trueName").addRule("required");
					        					form.getField("repassword").addRule("required");
					            		  		form.getField("repassword").addRule("verifyPwd");
					        					
					        					//发送验证码重置
												clearInterval(interval);
												$("#sendcode").html("获取验证码");
												$("#sendcode").css("background-color","#dadada");
												$('#sendcode').bind('click', function(){
													getCheckCode();
												});
					        				},"success");
			        					}else{
			        						message.Alert(data.msg,"warning");
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
				if(isAutoRegister){
					form = editing.get("editor").get("form");
					//form.getField("checkCode").addRule("required");
					form.getField("trueName").addRule("required");
    		  		form.getField("repassword").addRule("required");
    		  		form.getField("repassword").addRule("verifyPwd");
    		  	}
				isSubmit = false;
				isAutoRegister = false;
				$("#true_name").attr("readonly",false);
      			//$("#check_code").css("display","block");
      			$("#password").attr("readonly",false);
      			$("#re_password").css("display","block");
      			$("#password").attr("readonly",false);
      			$("#phone").attr("readonly",false);
      			$("#qq").attr("readonly",false);
      			$("#email").attr("readonly",false);
      			$("#address").attr("readonly",false);
      			$("#login_name").attr("readonly",false);
				editing.get("editor").clearValue();
			})
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/user/search.shtml',
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
				//forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection,editing],
				tbar:{ //添加、删除
		         	items : [
		         		{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-plus"></i>新增',
			           		handler: addUser
			          	}/* ,{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-edit"></i>编辑',
			           		handler: editUser
			          	} */,{
			          		btnCls : 'button button-normal',
			         		text : '<i class="icon-remove"></i>删除',
			           		handler: deleteUser
			          	}
		          	],
				}
				
			});
			function addUser(){
		        editing.add();
			}
			function editUser(){
				var selects = grid.getSelection();
				if(selects.length==1){
					editing.edit(selects[0]);
				}else{
					BUI.Message.Alert("请选择一条记录","warning");
				}
			}
			function deleteUser(){
		        var selects = grid.getSelection();
		        var lng = selects.length;
		        if(lng>0){
		        	var data = [];
		        	for(var i=0;i<lng;i++){
		        		data.push(selects[i].loginName);
		        	}
		        	BUI.Message.Confirm("确定要删除该记录？",function(){
		        		$.ajax({
			        		url:'<%=request.getContextPath()%>/tms/user/delete.shtml',
			        		data:{loginNames:data},
			        		type:'post',
			        		dataType:'json',
			        		success:function(data){
			        			if(data.result){
			        				BUI.Message.Alert("删除成功",function(){
			        					store.load();
			        				},"success");
			        			}else{
			        				BUI.Message.Alert("操作失败！"+data.msg,"warning");
			        			}
			        		}
			        	})
		        	})
		        	
		        }
			}
			
			//验证用户是否存在
		Form.Rules.add({
		      	name : 'checkUser',  //规则名称
		        msg : '用户名已存在',//默认显示的错误信息
		        validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息
		        	var phone = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
		        	if(value==null || value==''){
		        		return "不能为空";
		        	}else if(!phone.test(value)){
		        		return "手机号不正确";
		        	}else{
		        		if(value.length==11){
			        		
			        		var result = checkUserExist(value);
			        		
			          		if(result.result){
			          			if(result.msg != null){
			          				return result.msg;
			          			}else{
			          				return formatMsg;
			          			}
				          	}else{
				          		if(!isSubmit){
				          			var data = result.data;
					          		if(data != null){
					          			isAutoRegister = true;
					          			$("#true_name").val(data.trueName);
					          			$("#true_name").attr("readonly",true);
					          			//$("#check_code").css("display","none");
					          			$("#password").val(data.password);
					          			$("#password").attr("readonly",true);
					          			$("#re_password").css("display","none");
					          			$("#password").attr("readonly",true);
					          			$("#phone").val(data.mobile);
					          			$("#phone").attr("readonly",true);
					          			$("#qq").val(data.qq);
					          			$("#qq").attr("readonly",true);
					          			$("#email").val(data.email);
					          			$("#email").attr("readonly",true);
					          			$("#address").val(data.address);
					          			$("#address").attr("readonly",true);
					          			//BUI.Message.Alert("该账号已在货运交易系统平台注册，用户信息已自动添加");
					          			
					          			//$("#checkCode").removeAttr("data-rules");
				        		  		//$("#repassword").removeAttr("data-rules");
					          		}else{
					          			isAutoRegister = false;
					          			$("#true_name").attr("readonly",false);
					          			$("#check_code").css("display","block");
					          			$("#password").attr("readonly",false);
					          			$("#re_password").css("display","block");
					          			$("#password").attr("readonly",false);
					          			$("#phone").attr("readonly",false);
					          			$("#qq").attr("readonly",false);
					          			$("#email").attr("readonly",false);
					          			$("#address").attr("readonly",false);
					          			
					          			//$("#checkCode").attr("data-rules","{required:true}");
					    		  		//$("#repassword").attr("data-rules","{required:true,verifyPwd:''}");
					          		}
				          		}
				          		
				          	}
			          	}
		        	}
		        	
		        }
		 	}); 
			//验证两次密码输入是否一致
			Form.Rules.add({
		      	name : 'verifyPwd',  //规则名称
		        msg : '两次密码不一致',//默认显示的错误信息
		        validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息
		          	var password = $("#password").val();
		        	if(!verifyPwdEql(password,value)){
		            	return formatMsg;
		          	}
		        }
		 	});
			
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
		
		// 加载 角色列表
		function loadRoleList(outletsId){
			roleItems = [];
			$.ajax({
				url: '<%=request.getContextPath()%>/tms/role/ajax/getrole.shtml',
				type:'post',
				async:false,
				data : { 'outletsId' : outletsId },
				dataType:'json',
				success:function(result){
					if(result.result==true){
						for(var i= 0;i<result.rows.length;i++){
							data = {};
							data.text = result.rows[i].roleName;
							data.value = result.rows[i].id;
							roleItems.push(data);
						}
						roleSelect.set('items',roleItems);
						roleSelect.render();
					}
				},
			})
		}
		
		function loadRoleList1(outletsId){
			roleItems1 = [];
			$.ajax({
				url: '<%=request.getContextPath()%>/tms/role/ajax/getrole.shtml',
				type:'post',
				async:false,
				data : { 'outletsId' : outletsId },
				dataType:'json',
				success:function(result){
					if(result.result==true){
						for(var i= 0;i<result.rows.length;i++){
							data = {};
							data.text = result.rows[i].roleName;
							data.value = result.rows[i].id;
							roleItems1.push(data);
						}
						roleSelect1.set('items',roleItems1);
						roleSelect1.render();
					}
				},
			})
		}
		
		//禁用用户
		function forbid(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/user/forbid.shtml',
				type:'post',
				data:{loginName:loginName},
				success:function(result){
					if(result.result){
						message.Alert("状态修改成功",function(){
							store.load();
						},'success');
					}
				}
			})
		}
		//启用用户
		function enable(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/user/enable.shtml',
				type:'post',
				data:{loginName:loginName},
				success:function(result){
					if(result.result){
						message.Alert("状态修改成功",function(){
							store.load();
						},'success');
					}
				}
			})
		}
		//验证账号是否存在
		function checkUserExist(loginName){
			var rst = {};
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/user/checkUserExist.shtml',
				type:'post',
				data:{'loginName':loginName},
				async:false,
				success:function(result){
					rst = result;
				},error:function(){
					
				}
			})
			return rst;
		}
		//验证两次密码是否一致
		function verifyPwdEql(pwd,repwd){
			var flag = false;
			if(pwd != null 
					&& pwd != ''
					&& repwd != null 
					&& repwd != ''){
				if(pwd === repwd){
					flag = true;
				}
			}
			return flag;
		}
		
		<%-- function getCheckCode(){
			var phone = $("#login_name").val(),
				phoneReg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
			if(!phone || phone == null || phone == '' || !phoneReg.test(phone)){
				BUI.Message.Alert("请正确填写手机号！");
				return false;
			}else{
				$.ajax({
					url:'<%=request.getContextPath()%>/tms/user/getMsgCode.shtml',
					type:'post',
					data:{phoneNum:phone},
					async:false,
					success:function(result){
						if(result.result){
							message.Alert("短信已成功发送","success");
						}else{
							message.Alert("短信发送失败","warning");
						}
					},error:function(){
						
					}
				})
			}
		} --%>
		
		var dialog;
        BUI.use(['bui/overlay'],function(Overlay){
            dialog = new Overlay.Dialog({
                  title:'角色变更',
                  width:500,
                  height:200,
                  //配置DOM容器的编号
                  contentId:'hidden_dia_char',
                  success:function () {
                	  var data = {},
                	  	obj = this;
	          			$.ajax({
	          				url:'<%=request.getContextPath()%>/tms/user/updateUserRole.shtml',
	          				data:$("#changeRole").serialize(),
	          				type:'post',
	          				success:function(result){
	          					if(result.result){
	          						BUI.Message.Alert("操作成功",function(){
	          							store.load();
	          							obj.close();
	          						},"success");
	          					}
	          				}
	          			})
                  }
                });
        });
		
		function changeRole(index){
			var obj = grid.getItemAt(index),
			    role = obj.roleList,
			    roleName="";
			for(var i=0;i<role.length;i++){
				roleName += role[i].roleName+"|";
			}
			$("#curRole").html("<input type='text' value=\""+roleName.substring(0,roleName.length-1)+"\" readonly/>");
			$("#userId").val(obj.id);
			loadRoleList1(obj.outletsInfo.id);
			dialog.show();
		}
		
		$(function(){
			$("#sendcode").bind("click",function(){
				getCheckCode();
			});
		})
		
		//发送验证码	
		var interval = null;
		function getCheckCode(){
			var login_name = $('#login_name').val();
			
			if(login_name==null || login_name==''){
				BUI.Message.Alert("请输入手机号！");
				return false;
			}else{
				if(login_name.length != 11 || !/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(login_name)){
					BUI.Message.Alert('请输入正确的手机号码!');
					return false;
				}else{
					var result = checkUserExist(login_name);
					if(result.result){
						BUI.Message.Alert('该手机号已注册!');
						return ;
					}
				}
			}
			//方式验证码
			$.ajax({
				type : "POST",
				url:'<%=request.getContextPath()%>/tms/user/getMsgCode.shtml',
				data : {phoneNum:login_name},
				success : function(data) {
					if(data.result){
						BUI.Message.Alert("短信已成功发送！","success");
						$("#login_name").attr("readonly",true);
						$('#sendcode').unbind('click');
		    				$("#sendcode").css("background-color","#666666");
		    				var time = 60;
		    				interval = setInterval(function(){
								time--;
								$("#sendcode").html(time + "秒后可再次获取");
								if(time == 0){
									time = 60;
									$("#sendcode").html("获取验证码");
									clearInterval(interval);
									$("#sendcode").css("background-color","#dadada");
									$('#sendcode').bind('click', function(){
										getCheckCode();
									});
								}
							},1000);
					}else{
						BUI.Message.Alert("发送失败");
					}
				}
			});
		} 
	</script>
</body>
</html>