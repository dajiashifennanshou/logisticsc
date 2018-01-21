var grid = null;
var outStorage_={
	newGird:function(tabId,pageId){
		grid = $(tabId).jqGrid({
			url:"getYcOutStorageList.yc",
			datatype: "json",
			autowidth: true,
			height: '100%',
			colNames:['配载单号','出库时间','操作人','网点编号','备注'],
			colModel:[
				{name:'stowageNo',align:'center'},
				{name:'createTime',align:'center',formatter:function(val){
					return DateUtil.RemoveZero(val);
				}},
				{name:'createUser',align:'center'},
				{name:'branchNo',align:'center'},
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
			edit: false,
			view: true,
			viewtitle:"详情",
			viewfunc:toDet,
			viewicon:"ace-icon fa fa-search-plus green",
		/*	edittitle:"修改",
			editfunc:toMod,
			editicon:"ace-icon fa fa-pencil blue",*/
			del: false,
			/*deltitle:"删除",
			delfunc:del,
			delicon:"ace-icon fa fa-trash-o red",*/
			refresh: true,
			refreshtitle:"刷新",
			refreshicon:"ace-icon fa fa-refresh green"
		});
	},
	initSelect:function(selector){
		$(selector).css('width','100%').select2({allowClear:false});
	},
	initStowage:function(selecteor){
		yc_public.ajax({url:'getAllStowageList.yc',data:{stowageStatus:0},success:function(data){
			var html="";
			if(data.code==0){
				var det=data.data;
				for(var i in det){
					var d=det[i];
					
					html+='<option value='+d.stowageNo+'>'+(d.stowageNo+"车辆："+d.carNo)+'</option>';
				}
			}
			$(selecteor).html(html);
		}})
	},
	getGoodsData:function(no,callback){
		yc_public.ajax({"url":"getGoodsByDeliveryNo.yc","data":{dNo:no},"success":function(data){
			callback(data);
		}});
	},
	selectInstallerHtml:'<div  class="col-xs-4">配送单：{psNo}&nbsp;&nbsp;&nbsp;货物信息:↓<select class="form-control" id="form-field-select-5" multiple="multiple">'
			+'{goodsOptions}'
			+'</select>'
			+'<label for="installer_{psNo}">安装人员</label>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
			+'<input id="installer_{psNo}" name="installer_{psNo}" vlaue="" />'
			+'</div>',
	selectInstaller:function(dNos){
		//selctNumHmtl
		var html="";
		//var hiddenHtml='<input type="hidden" group="val" name="deliveryNos" value="'+deliveryVal.toString()+'" />';
		
		for(var i in dNos){
			//id集合
			var t=dNos[i];
			var thisHtml=this.selectInstallerHtml.replace(/{psNo}/g,t);
			//获取指定id的货物对象select_num_
			this.getGoodsData(t,function(data){
				if(data.code==0){
					//获取货物集合
					var goodsArray=data.data;
					var goodsOptions="";
					for(var i in goodsArray){
						//当前货物
						var td=goodsArray[i];
						goodsOptions+='<option>'+td.goodsName+'</option>';
					}
					thisHtml=thisHtml.replace(/{goodsOptions}/,goodsOptions);
				}
			});
			html+=thisHtml;
		}
		//将生产好的html代码设置到页面中
		$("#selectInstaller").html(html);
		//$("#selectInstaller").append(hiddenHtml);
		//初始化选择框--已转移至提交方法里
	},
	getOrderDeatilByStowageNo:function(stowageNo){
		yc_public.ajax({url:'getYcStowageOrderAllList.yc',data:{stowageNo:stowageNo},success:function(data){
			 var delivers=data.data;
			 var dNos=new Array();
			 for(var i in delivers){
				 //添加到集合里，用于select框赋值
				 dNos.push(delivers[i].deliverNo);
			 }
			 //初始化订单
			 outStorage_.selectInstaller(dNos);
			 for(var i in dNos){
				 //获取安装人员的信息
				 yc_public.ajax({url:'getInstallerInfo.yc',data:{deliveryNo:dNos[i]},success:function(data){
					 if(data.code==0){
						 var det=data.data;
						 var ins="";
						 for(var i in det){
							 ins+=det[i].employeeName;
						 }
						 $('#installer_'+dNos[i]).val(ins);
					 }
				 }});
			 }
		 }});
	},
	pageNext:1,
	pageSum:1,
	init:function(){
		//div的总数
		this.pageSum=$(".step-pane").length;
	},//下一页
	gotoNext:function(){
		this.pageNext += 1;
		$(".step-pane").removeClass("active");
		$("[data-step="+this.pageNext+"]").addClass("active");
		$(".btn-prev").removeAttr("disabled");
		var jionType = $("select[name=jionType]").val();
//		this.getYcJoin(jionType);//查询加盟商
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
			//表单验证
			var _form=$('#'+form);
			var valids=_form.FengValid(this.pageNext);
			if(valids){
				if(this.pageNext==1){
					this.getOrderDeatilByStowageNo($('#stowage').val());
				}
				if(this.pageNext==this.pageSum){
					yc_public.confirm({msg:'请确认信息!',callback:function(){
						yc_public.ajax({"url":"addYcOutstorage.yc",load:true,"data":yc_public.getData(form),"result":"add"});
					}})
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
			var valids=_form.FengValid();
			if(valids){
				if(this.pageNext==this.pageSum){
					//yc_public.ajax({"url":"modYcOutstorage.yc","data":yc_public.getData(form),"result":"mod"});
				}else{
					this.gotoNext();
				}
			}
			this.isSubmit=true;
		}
	},
	getModData:function(id,from){
		yc_public.ajax({"url":"getYcOutstorageSingle.yc",load:true,"data":{"id":id},"success":function(data){
			if(data.code==0){
				yc_public.setData(from, data.data);
			}
		}});
	},
	//获取加盟商信息
	getYcJoin:function(jionType){
		//data:1:申请已通过的加盟商
		yc_public.ajax({"url":"getYcJoin.yc","data":{"applyStatus":1,"jionType":jionType},"success":function(data){
			if(data.code==0){
				$.each(data.data, function(i, item){
					$("select[name=dealerId]").append("<option value='"+item.dealerId+"'>"+item.dealerId+"</option>");
				});
			}else{
				yc_public.dialog({msg:data.msg})
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
				yc_public.dialog({msg:data.msg})
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
				yc_public.dialog({msg:data.msg})
			}
		}});
		//获取详情信息
	},getDetOutStorage:function(){
		
	},
	findSelect:function(form){
		grid.setGridParam({"postData":yc_public.getData(form)}).trigger("reloadGrid");

	}
}

//前往添加页
function toAdd(){
	window.parent.f_addTab("add_out_storage","添加出库信息","toAddOutStorage.yc");
}
//前往修改页
function toMod(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("mod_out_storage","修改出库信息","toModOutStorage.yc?id="+rowData[0]);
    }else{
    	yc_public.dialog({"msg":"请至少选择一行"});
    }
}
//前往详情页
function toDet(){
	var rowData = grid.getGridParam('selarrrow');
	if(rowData.length == 1){
		window.parent.f_addTab("det_out_storage","出库详情信息","toDetOutStorage.yc?id="+rowData[0]);
	}else{
		yc_public.dialog({"msg":"请至少选择一行"});
	}
}
//删除
function del(){
    var rowData = grid.getGridParam('selarrrow');
    if(rowData.length>0) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcOutStorage.yc","result":"del"});
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
