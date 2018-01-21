var grid = null;
var goods_={
	newGird:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcZoneGoodsList.yc",
			datatype: "json",
			postData:{"dealerId":2},
			autowidth: true,
			height: '100%',
			colNames:['网点编号','库区编号','货物数量','入库状态','货物详细','备注'],
			colModel:[
			    {name:'branchNo',align:'center'},
				{name:'zoneNo',align:'center'},
				{name:'goodsNum',align:'center'},
				{name:'inZoneStatus',align:'center',formatter:function(val,opt,row){
					return Constant.InZoneStatus[val];
				}},
				{name:'goodsId',align:'center'},
				{name:'remark',align:'center'}
			], 
			viewrecords : true,
			page:1,
			rowNum:10,
			rowList:[10,20,30],
			pager : pageId,
			altRows: true,
			multiselect: true,
			loadComplete : function() {
				var table = this;
				setTimeout(function(){
					pagerIcons(table);
				}, 0);
			}
		});
		$(tabId).jqGrid("navGrid",pageId,{search: false,view: false,add: false,edit: false,del: false,
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green"
		});
	}
}
//分页样式
function pagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
		var icon = $(this);
		var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
		if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
	})
}
