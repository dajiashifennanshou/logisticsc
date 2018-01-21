var grid = null;
var gridCargo = null;
var goodsType_={
	newGird:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcGoodsTypeList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['类型名称','创建时间','修改时间','备注'],
			colModel:[
				{name:'softName',align:'center'},
				{name:'createTime',align:'center'},
				{name:'updateTime',align:'center'},
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
		$(tabId).jqGrid("navGrid",pageId,{search: true,
			add: true,
			addtitle:"线上入库",
			addfunc:toAdd,
			addicon:"ace-icon fa fa-plus-circle purple",
			edit: true,
			edittitle:"修改",
			editfunc:toMod,
			del: true,
			deltitle:"删除",
			delfunc:del,
			delicon:"ace-icon fa fa-trash-o red",
			editicon:"ace-icon fa fa-pencil blue",
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green",
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
					yc_public.ajax({"url":"addYcGoodsType.yc","data":yc_public.getData(form),"result":"add"});
				}else{
					//得到不入库的货物
					var rowData = gridCargo.getGridParam('selarrrow');
					if(rowData.length>0){
						$("input[name=cId]").val(rowData.toString());
					}
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
					yc_public.ajax({"url":"modYcGoodsType.yc","data":yc_public.getData(form),"result":"mod"});
				}else{
					this.gotoNext();
				}
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcGoodsTypeSingle.yc","data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData(from, data.data);
			}
		}});
	},//获取云仓网点信息
	getYcStorageBranch:function(){
		yc_public.ajax({"url":"getYcStorageBranch.yc","success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=branchNo]").append("<option value='"+item.branchNo+"'>"+item.branchName+"&nbsp;&nbsp;"+item.province+item.city+item.county+item.town+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//获取库区信息
	getStorageZone:function(storageNo){
		$("#select2-chosen-8").html("");//清空
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
	}/*,
	findSelect:function(form){
		grid.setGridParam({"postData":yc_public.getData(form)}).trigger("reloadGrid");

	}*/,//查询运单
	getWayBillOrder:function(){
		yc_public.ajax({"url":"getWayBillOrder.yc","data":{"signStatus":6},"success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=waybillNo]").append("<option value='"+item.id+"'>"+item.way_bill_number+"</option>");
				});
			}else{
				yc_public.dialog(data.msg)
			}
		}});
	},//查询运单下的货物信息
	getWayBillOrderCargoInfo:function(way_bill_order_id){
		gridCargo.setGridParam({"datatype":"json","postData":{"way_bill_order_id":way_bill_order_id}}).trigger("reloadGrid");
	},
	loadCargoTab:function(){
		gridCargo=$("#cargo_tab").jqGrid({
			url:"getWayBillOrderCargoInfoList.yc",
			datatype:"local",
			autowidth: true,
			height: '100%',
			colNames:['货物名称','货物品牌','货物型号','件数','套数','重量(T)','体积(m³)','价格','包装信息'],
			colModel:[
				{name:'name',align:'center'},
				{name:'brand',align:'center'},
				{name:'model',align:'center'},
				{name:'number',align:'center'},
				{name:'set_number',align:'center'},
				{name:'total_weight',align:'center'},
				{name:'total_volume',align:'center'},
				{name:'price',align:'center'},
				{name:'package_type',align:'center'}
			],
	        altRows: true,
			multiselect: true,
	        caption : "运单货物信息"
	    });
	}
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_goods_type","添加类型","toAddYcGoodsType.yc");
}

//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_goods_type","修改类型","toModYcGoodsType.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"请至少选择一行"});
    }
}
//删除
function del(){
    var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcGoodsType.yc","result":"del"});
    	}});
    }else{
    	yc_public.dialog({"msg":"至少选择一行进行删除"});
    }
}
//前往详情页
function toDet(){
	var rowData = grid.getGridParam('selarrrow');
	var data=grid.getRowData(rowData);
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("det_in_storage","入库记录详情","toDetYcInGoods.yc?rowId="+rowData[0]);
	}else{
		yc_public.alert({"msg":"只能选择一项进行修改操作！"});
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
