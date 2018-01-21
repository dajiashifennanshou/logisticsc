<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>会员管理</title>
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
           	<h2>网点管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
			        	<label>起始地：</label>
				        <select id="start_province" name="startProvince" class="input-small start_province" onchange="renderSelect(this)"></select>
				        <select id="start_city" name="startCity" class="input-small start_city" onchange="renderSelect(this)"></select>
				        <select id="start_county" name="startCounty" class="input-small start_county"></select>&nbsp;&nbsp;&nbsp;&nbsp;
				       	<input id="condition" name="content" type="text" placeholder="专线,网点名称">
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
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params={
				startProvince:$("#start_province").val(),
				startCity:$("#start_city").val(),
				startCounty:$("#start_county").val(),
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
				{title : '所属专线', dataIndex : 'companyName'},
				{title : '网点名称', dataIndex : 'name'},
				{title : '编号', dataIndex : 'outletsNumber'},
				{title : '网点类型',dataIndex :'typeValue'},
				{title : '所属省',dataIndex :'provinceValue'},
				{title : '所属市', dataIndex : 'cityValue'},
			 	{title : '所属区',dataIndex : 'countyValue'},
			 	{title : '联系人', dataIndex : 'contactPerson'},
			 	{title : '固定电话', dataIndex : 'mobile'},
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/sys/tms/member/searchOutlets.shtml',
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
		
		function showOutletsItems(id){
			window.location.href="<%=request.getContextPath()%>/tms/sys/outlets/detail/list.shtml?outletsId="+id;
		}
		$(function(){
			renderSelect();
		})	
		//加载select
		function renderSelect(obj){
			$.ajax({
				url:'<%=request.getContextPath()%>/tms/outlets/ajax/xzqh.shtml',
				data:{pid:$(obj).val()},
				type:'post',
				success:function(result){
					if(result.result){
						var data = result.data;
						if(!obj){
							$(".start_province").append("<option value=''>请选择</option>");
							for(var i = 0;i<data.length;i++){
								$(".start_province").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
							}
						}else{
							if($(obj).val()!=null && $(obj).val()!=''){
								var nextEle = $(obj).next();						
								nextEle.empty();
								nextEle.next().empty();
								nextEle.append("<option value=''>请选择</option>");
								for(var i = 0;i<data.length;i++){
									nextEle.append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
								}	
							}else{
								var nextEle = $(obj).next();						
								nextEle.empty();
								nextEle.next().empty();
								nextEle.append("<option value=''></option>");
							}
						}
					}
				}
			})
		}
	</script>
</body>
</html>