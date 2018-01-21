<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>物流公司用户管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body feedback_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>物流公司用户管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
			        	<label>注册时间：</label><input id="startTime" name="startTime" type="text" class="calendar">&nbsp;&nbsp;&nbsp;&nbsp;
				       	<input id="condition" name="condition" type="text" class="input-large" placeholder="输入公司名称，组织代码，账号">
			        	<button type="button" class="button button-normal" onclick="search()">查询</button>
			        </li>
	      		</ul>
	        </form>
	     	<div id="render_grid">
	  
	       	</div>
	       	<div id="pagingbar">
	       	
	       	</div>
       	</div>
    </div>
   	<div id="hidden_dia" class="hide">
   		<form id="userDetail">
   			<div class="control-group">
	         	<label class="control-label">网点名称：</label>
	         	<div class="controls">
	           		<input id="outletsName" type="text" class="input-large" disabled/>
	         	</div>
	      	</div>
	      	<div class="control-group">
	         	<label class="control-label">网点编号：</label>
	         	<div class="controls">
	           		<input id="outletsCode" type="text" class="input-large" disabled/>
	         	</div>
	      	</div>
	      	<div class="control-group">
	         	<label class="control-label">角色：</label>
	         	<div class="controls">
	           		<input id="roleName" type="text" class="input-large" disabled/>
	         	</div>
	      	</div>
	      	<div class="control-group">
	         	<label class="control-label">账号：</label>
	         	<div class="controls">
	           		<input id="loginName" type="text" class="input-large" disabled/>
	         	</div>
	      	</div>
	      	<div class="control-group">
	         	<label class="control-label">姓名：</label>
	         	<div class="controls">
	           		<input id="trueName" type="text" class="input-large" disabled/>
	         	</div>
	      	</div>
	      	<div class="control-group">
	         	<label class="control-label">是否启用：</label>
	         	<div class="controls">
	           		<input id="userStatus" type="text" class="input-large" disabled/>
	         	</div>
	      	</div>
   		</form>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params={
				startTime:$("#startTime").val(),
				condition:$("#condition").val()
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '公司名称',elCls:'center',width:200,renderer:function(val,obj){
					return obj.platformUserCompany.companyName;
				}},
				{title : '组织代码',elCls:'center',renderer:function(val,obj){
					return obj.platformUserCompany.companyCode;
				}},
				{title : '账户',elCls:'center', dataIndex : 'loginName'},
				{title : '注册时间',elCls:'center',width:200,dataIndex:'createTime',renderer:Grid.Format.datetimeRenderer},
				{title : '法人',elCls:'center',renderer:function(val,obj){
					return obj.platformUserCompany.legalPerson;
				}},
				{title : '法人电话',elCls:'center',renderer:function(val,obj){
					return obj.platformUserCompany.contactsMobile;
				}},
				{title : '公司地址',elCls:'center',width:200,renderer:function(val,obj){
					return obj.platformUserCompany.companyAddress;
				}},
				{title : '状态',elCls:'center', dataIndex : 'userStatus',renderer:function(val){
					if(val == 0){
						return '已禁用';
					}else{
						return '已启用';
					}
				}},
				{title : '操作',elCls:'center', width:200,dataIndex : 'userStatus',renderer:function(val,obj){
					var ennable = '',
						disEnnable = '';
						getItems ='<a href="javascript:void(0)" onclick="getUserItem('+obj.loginName+')">查看详情</a>';
					if(val==0){
						ennable += '<a href="javascript:void(0)" onclick="enable('+obj.loginName+')">启用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					}else if(val==1){
						disEnnable += '<a href="javascript:void(0)" onclick="forbid('+obj.loginName+')">禁用</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					return ennable + disEnnable + getItems;
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/tms/member/getAllUserList.shtml',
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
		function forbid(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/sys/tms/member/disable.shtml',
				type:'post',
				data:{loginName:loginName},
				success:function(result){
					if(result.result){
						BUI.Message.Alert("用户已禁用",function(){
							store.load();
						},'success');
					}
				}
			})
		}
		
		function enable(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/sys/tms/member/enable.shtml',
				type:'post',
				data:{loginName:loginName},
				success:function(result){
					if(result.result){
						BUI.Message.Alert("用户已启用",function(){
							store.load();
						},'success');
					}
				}
			})
		}
		
		function getUserItem(loginName){
			$.ajax({
				url:'<%=request.getContextPath()%>/sys/tms/member/getUserDetail.shtml',
				data:{loginName:loginName},
				type:'post',
				success:function(result){
					if(result.result){
						var user = result.data;
						if(user.outletsInfo != null){
							$("#outletsName").val(user.outletsInfo.name);
							$("#outletsCode").val(user.outletsInfo.outletsNumber);
						}
						var roleNameLst ='';
						for(var i=0;i<user.roleList.length;i++){
							roleNameLst += user.roleList[i].roleName + "|";
						}
						$("#roleName").val(roleNameLst.substring(0,roleNameLst.length-2));
						$('#loginName').val(user.loginName);
						$("#trueName").val(user.trueName);
						if(user.userStatus == 1){
							$("#userStatus").val("已启用");
						}else{
							$("#userStatus").val("已禁用");
						}
						dialog.show();
					}
				},
				error:function(){
					BUI.Message.Alert("系统错误","error");
				}
			})
		}
		
		var dialog;
        BUI.use(['bui/overlay','bui/form'],function(Overlay,Form){
            
            var form = new Form.HForm({
              srcNode : '#userDetail'
            }).render();
       
            dialog = new Overlay.Dialog({
                  title:'用户详情',
                  width:500,
                  height:420,
                  //配置DOM容器的编号
                  contentId:'hidden_dia',
                  //mask:false,
                  buttons:[],
                });
            });
	</script>
</body>
</html>