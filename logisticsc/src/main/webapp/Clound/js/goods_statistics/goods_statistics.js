var grid = null;
//库存盘点
var gs_={
	newGird:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcGoodsList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['库区编号','运单号','入库状态','出库状态','货物名称','货物数量','包装信息','货物型号','生产厂家','重量(㎏)','体积(M³)'],
			colModel:[
				{name:'zoneNo',align:'center'},
				{name:'wayBillNo',width:170,align:'center'},
				{name:'inStorageStatus',align:'center',formatter:function(val,opt,row){
					return Constant.InZoneStatus[val];
				}},
				{name:'outStorageStatus',align:'center',formatter:function(val,opt,row){
					return Constant.OutZoneStatus[val];
				}},
				{name:'goodsName',align:'center'},
				{name:'goodsNum',width:30,align:'center'},
				{name:'park',align:'center'},
				{name:'goodsType',align:'center'},
				{name:'vender',align:'center'},
				{name:'weight',align:'center'},
				{name:'volume',align:'center'}
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
		$(tabId).jqGrid("navGrid",pageId,{search: false,view: false,add: false,del: false,
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green",
			edit: true,
			edittitle:"修改",
			editfunc:toMod,
			editicon:"ace-icon fa fa-pencil blue"
		});
	},//获取云仓网点信息
	getYcStorageBranch:function(){
		yc_public.ajax({"url":"getYcStorageBranch.yc","success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=branchNo]").append("<option value='"+item.branchNo+"'>"+item.branchName+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//获取库区信息
	getStorageZone:function(storageNo){
		$("#select2-chosen-4").html("");//清空
		$("select[name=zoneNo]").empty();//清空 
		yc_public.ajax({"url":"getStorageZone.yc","data":{"storageNo":storageNo},"success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=zoneNo]").append("<option value='"+item.zoneNo+"'>"+item.zoneNo+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//参数查询
	condSearch:function(){
		this.getGoodsStatisticsSumCount();
		grid.setGridParam({"postData":yc_public.getData("gs_from")}).trigger("reloadGrid");
	},// 条件查询获物数量
	getGoodsStatisticsSumCount:function(){
		yc_public.ajax({"url":"getGoodsStatisticsSumCount.yc","data":yc_public.getData("gs_from"),"success":function(data){
			if(data.code==0){
				$("#count span").html(data.data)
			}
		}});
	},//查询加盟商信息
	getYcJoin:function(){
		yc_public.ajax({"url":"getYcJoin.yc","success":function(data){
			if(data.code==0){
				$.each(data.data,function(i,item){
					$("select[name=dealerId]").append("<option value='"+item.joinerId+"'>"+item.joinerId+"</option>");
				})
			}
		}});
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
//前往修改页
function toMod(){
	
}
