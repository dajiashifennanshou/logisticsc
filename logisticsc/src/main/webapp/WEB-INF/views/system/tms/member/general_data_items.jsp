<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>基础数据管理</title>
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
           	<h2>基础数据管理</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="form-panel">
	        	<ul class="panel-content" >
			        <li>
				       	<input id="condition" type="text" placeholder="专线">
			        	<button type="button" class="button button-normal" onclick="search()">查询</button>
			        </li>
	      		</ul>
	        </form>
	     	<div id="render_grid"></div>
	       	<div id="pagingbar"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		//查询
		function search(){
			var params={
				'condition' : $("#condition").val()
			}
			store.load(params);
		}
		
		//表格渲染
		BUI.use(['bui/grid','bui/data','bui/toolbar','bui/format','bui/select'],
					function(Grid,Data,Toolbar,Format,Select){
			var Grid = Grid,
			Store = Data.Store,
			columns = [
				{title : '专线/物流商', dataIndex : 'companyName'},
				{title : '网点', dataIndex : 'outletsName'},
				{title : '现付', dataIndex : 'isImmediatePay',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
				{title : '到付',dataIndex :'isArrivePay',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
				{title : '预付',dataIndex :'isAdvancePay',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
				{title : '普通发票', dataIndex : 'isCommonReceipt',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
			 	{title : '收费点',dataIndex : 'commonReceiptRate',renderer:function(value){
					if(value!= null && value != ''){
						return value+'%';
					}
				}},
			 	{title : '增值税发票', dataIndex : 'isAddTaxReceipt',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
			 	{title : '收费点', dataIndex : 'addTaxReceiptRate',renderer:function(value){
					if(value!= null && value != ''){
						return value+'%';
					}
				}},
			 	{title : '无发票', dataIndex : 'isNoReceipt',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
			 	{title : '支持保险', dataIndex : 'isSupportInsurance',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
			 	{title : '强制购买保险', dataIndex : 'isForceInsurance',renderer:function(value){
					if(value == 1){
						return '是';
					}else{
						return '否';
					}
				}},
			 	{title : '保险费率', dataIndex : 'lineInsuranceRatio',renderer:function(value){
					if(value!= null && value != ''){
						return value+'%';
					}
				}}
			];
			store = new Store({
				url: '<%=request.getContextPath()%>/tms/additionalserver/getadditionalserverofplatform.shtml',
				autoLoad:true,
				pageSize:10,
				proxy:{ method:'post'}
			});

			grid = new Grid.Grid({
			   	render:'#render_grid',
			  	autoRender:true, 
				columns : columns,
				forceFit : true,
				store : store,
				loadMask: true,//加载数据时显示屏蔽层
				plugins : [Grid.Plugins.CheckSelection],
				
			});
			
			//分页工具条
			var bar = new Toolbar.NumberPagingBar({
		          render : '#pagingbar',
		          autoRender: true,
		          elCls : 'pagination pull-right',
		          store : store
		    });
		});	
		
	</script>
</body>
</html>