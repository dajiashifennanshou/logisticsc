var grid = null;
var joinCharge_={
	newGrid:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcJoinChargeList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['收费标准(年)','状态','备注'],
			colModel:[
				{name:'chargeNorm',align:'center'},
				{name:'status',align:'center',formatter:function(val,opt,row){
					return Constant.JoinChargeStatus[val];
				}},
				{name:'remark',align:'center'},
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
		
		$(tabId).jqGrid("navGrid",pageId,{search: false,view: false,
			add: true,
			addtitle:"添加",
			addfunc:toAdd,
			addicon:"ace-icon fa fa-plus-circle purple",
			edit: true,
			edittitle:"修改",
			editfunc:toMod,
			editicon:"ace-icon fa fa-pencil blue",
			del: true,
			deltitle:"删除",
			delfunc:del,
			delicon:"ace-icon fa fa-trash-o red",
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green"
		});
	},
	isSubmit:true,
	addSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid();
			if(valids){
				yc_public.ajax({"url":"addYcJoinCharge.yc","data":yc_public.getData(form),"result":"add"});
			}
			this.isSubmit=true;
		}
	},
	modSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid();
			if(valids){
				yc_public.ajax({"url":"modYcJoinCharge.yc","data":yc_public.getData(form),"result":"mod"});
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcJoinChargeSingle.yc","data":{"id":id},"success":function(data){
			console.log(data);
			if(data.code==0){
				yc_public.setData(from, data.data);
			}
		}});
	}
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_join_charge","添加加盟收费标准","toAddJoinCharge.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_join_charge","修改仓库收费标准","toModJoinCharge.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}
//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcJoinCharge.yc","result":"del"});
    	}});
    }else{
    	yc_public.dialog({"msg":"至少选择一行进行删除"});
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
