var grid = null;
var gridCargo = null;
var inStorage_={
	newGird:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcInstorageList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['运单号','网点名称','库区编号','入库方式','入库类型','入库时间','备注'],
			colModel:[
				{name:'waybillNo',align:'center'},
				{name:'branchName',align:'center'},
				{name:'zoneNo',align:'center'},
				{name:'inType',align:'center',formatter:function(val,opt,row){
					return Constant.InStorageType[val];
				}},
				{name:'waybillSource',align:'center',formatter:function(val,opt,row){
					return Constant.InStorageSource[val];
				}},
				{name:'createTime',align:'center'},
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
		$(tabId).jqGrid("navGrid",pageId,{search: true,view: false,del: false,
//			add: true,
//			addtitle:"线上入库",
//			addfunc:toAdd,
			view: true,
			viewtitle:"详情",
			viewicon:"ace-icon fa fa-search-plus green",
			viewfunc:toDet,
//			addicon:"ace-icon fa fa-plus-circle purple",
			edit: true,
			edittitle:"修改",
			editfunc:toMod,
			editicon:"ace-icon fa fa-pencil blue",
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green",
//			searchtitle:"线下入库",
//			searchicon:"ace-icon fa fa-tags red",
//			searchfunc: toAddSelf,
		})/*.navButtonAdd(pagerId,{
			   caption:"",
			   title:'线下入库',
			   buttonicon:"ace-icon fa fa-tags red",   
			   onClickButton: function(){
				  toAddSelf();
			   },
			   position:"last"  

			});*/
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
					if(!$('#cId').val()){
						yc_public.alert({msg:'请至少选择一项货物..'});
						this.isSubmit=true;
						return;
					}
					yc_public.ajax({"url":"addYcInstorage.yc",load:true,"data":yc_public.getData(form),"result":"add"});
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
	addSubmitSelf:function(form){
		if(this.isSubmit){
			this.isSubmit=false;
			var _form=$("#"+form);
			//表单验证
			var valids=_form.FengValid(this.pageNext);
			if(valids){
				if(this.pageNext==this.pageSum){
					var data=yc_public.getData(form);
					//货物数据
					var goods=buildCargoInfoData();
					var temp=$.extend(data,{'goodsInfo':yc_public.decode(goods)});
					yc_public.ajax({"url":"addYcInstorageSelf.yc",load:true,"data":temp,"result":"add"});
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
					yc_public.ajax({"url":"modYcInstorage.yc","data":yc_public.getData(form),"result":"mod"});
				}else{
					this.gotoNext();
				}
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcInstorageSingle.yc","data":{"id":id},"success":function(data){
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
	getWayBillOrder:function(dealerId){
		yc_public.ajax({"url":"getWayBillOrder.yc","data":{"sign_status":1,'way_bill_order_user':dealerId},"success":function(data){
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
	window.parent.f_addTab("add_in_storage","线上入库","toAddInStorage.yc");
}
//前往线下添加页
function toAddSelf(){
	window.parent.f_addTab("add_in_storage_self","线下入库","toAddInStorageSelf.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_in_storage","修改入库信息","toModInStorage.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"请至少选择一行"});
    }
}
//删除
function del(){
    var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcInstorage.yc","result":"del"});
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
	$(function(){
		$("#add_in_storage_tab").hide();
		$("#search_in_storage_tab").hide();
	});
}
