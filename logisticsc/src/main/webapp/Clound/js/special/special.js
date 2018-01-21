var grid = null;
//经销商申请信息
var special_={
	newGrid:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcSpecialList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['公司代码','公司名称','网点代码','网点名称','联系人','手机号','座机号','地址','备注'],
			colModel:[
				{name:'companyCode',align:'center'},
				{name:'companyName',align:'center'},
				{name:'branchCode',align:'center'},
				{name:'branchName',align:'center'},
				{name:'linkman',align:'center'},
				{name:'phone',align:'center'},
				{name:'telephone',align:'center'},
				{name:'address',align:'center'},
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
	pageSum:1,
	pageNext:1,
	init:function(){
		//div的总数
		this.pageSum=$(".step-pane").length;
	},//下一页
	gotoNext:function(){
		this.pageNext += 1;
		$(".step-pane").removeClass("active");
		$("[data-step="+this.pageNext+"]").addClass("active");
		$(".btn-prev").removeAttr("disabled");
		if(this.pageSum==this.pageNext){
			$(".btn-next").html('提交<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
		}
	},//上一页
	prevSubmit:function(){
		//如果有上一页，才能前往
		if(this.pageNext-1>=1){
			this.pageNext-=1;
			$(".btn-prev").removeAttr("disabled");
			$(".step-pane").removeClass("active");
			$("[data-step="+this.pageNext+"]").addClass("active");
			$(".btn-next").html('下页<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
			//如果已经是第一页了，则将上页按钮不可用
			if(this.pageNext==1){
				$(".btn-prev").attr("disabled","disabled");
			}
		}
	},
	isSubmit:true,
	addSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid(this.pageNext);
			if(valids){
				if(this.pageNext==this.pageSum){
					yc_public.ajax({"url":"addYcSpecial.yc","data":yc_public.getData(form),"result":"add"});
				}else{
					this.gotoNext();
				}
			}
			this.isSubmit=true;
		}
	},
	modSubmit:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid(this.pageNext);
			if(valids){
				if(this.pageNext==this.pageSum){
					yc_public.ajax({"url":"modYcSpecial.yc","data":yc_public.getData(form),"result":"add"});
				}else{
					this.gotoNext();
				}
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcSpecialSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				special_.getStorageZone(data.data.branchNo);
				yc_public.setData(from, data.data);
			}
		}});
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
	}
	
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_special","添加专线信息","toAddSpecial.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_special","修改专线信息","toModSpecial.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"只能选择一行进行修改"});
    }
}

//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcSpecial.yc","result":"del"});
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
