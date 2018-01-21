<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body,#role_menu_itmes{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>角色管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
				       	<input id="condition" name="condition" type="text" class="input-large" placeholder="输入公司名称，组织代码，角色名称">
			        	<button type="button" class="button button-normal" onclick="search()">查询</button>
			        </li>
	      		</ul>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
    <div id="view_perms" class="hide">
	    <div id="role_menu_itmes" style="width:100%;height:240px">
	    
	    </div>
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
				{title : '角色名称',elCls:'center',dataIndex:'roleName',renderer:function(val,obj){
					return obj.roleList[0].roleName;
				}},
				{title : '操作',elCls:'center',renderer:function(val,obj){
					return "<a href=\"javascript:void(0)\" onclick=\"viewPerms("+obj.roleList[0].id+")\">查看权限</a>";
				}},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/tms/member/getRoleList.shtml',
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
		
		var treeStorePerms;
		BUI.use(['bui/data','bui/tree','bui/overlay'],
				function(Data,Tree,Overlay){
		
			treeStorePerms = new Data.TreeStore({ 
		    	map : {
					menuName : 'text',
		            id : 'id',
		            subMenus : 'children',
		            checked:'checked'
		        },
		        autoLoad : true,
				url : "${configProps['project']}/sys/tms/member/searchRoleMenu.shtml"
		    });
			treePerms = new Tree.TreeList({
	            render : '#role_menu_itmes',
	            store : treeStorePerms,
	            showLine : true //显示连接线
	          });
	        treePerms.render();
		})
		
		var dialog;
        BUI.use('bui/overlay',function(Overlay){
            dialog = new Overlay.Dialog({
              title:'角色权限',
              width:500,
              height:400,
              contentId:'view_perms',
           	  buttons:[],
              /* mask:false,
              buttons:[],
              bodyContent:'<p>这是一个非模态窗口,并且不带按钮</p>' */
            });
         
          });
		
		function viewPerms(roleId){
			treeStorePerms.load({"roleId":roleId},function(){
				dialog.show();
			});
		}
	</script>
</body>
</html>