var grid = null;
//instal类
var deliveryCharge_={
		newGrid:function(tabId,pagerId){
			grid = $(tabId).jqGrid({
				url:"getYcDeliveryOrderList.yc",
				datatype: "json",
				autowidth: true,
				height: '100%',
				colNames:['网点编号','配送单号','预约时间','创建时间','客户名称','客户电话','订单状态','签收时间'],
				colModel:[
					/*{name:'id',index:'id',align:'center',sorttype:"int"},*/
					{name:'branchNo',align:'center',width:70},
					{name:'deliveryNo',align:'center',width:150},
					{name:'subscribeTime',align:'center',formatter:function(val){
						return DateUtil.RemoveZero(val);
					}},
					{name:'createTime',align:'center',formatter:function(val){
						return DateUtil.RemoveZero(val);
					}},
					{name:'consigneeName',align:'center'},
					{name:'consigneePhone',align:'center'},
					{name:'orderStatus',align:'center',formatter:function(val,opt,row){
						return Constant.DeliveryOrderStatus[val];
					}},
					{name:'signTime',align:'center',formatter:function(val,opt,row){
						if(row.orderStatus<4) return "<span class='label arrowed'>未签收</span>";
					}}
				],
				viewrecords : true,
				page:1,
				rowNum:10,
				rowList:[10,20,30],
				pager : pagerId,
				altRows: true,
				multiselect: true,
				loadComplete : function() {
					var table = this;
					setTimeout(function(){
						pagerIcons(table);
					}, 0);
				}
			});

			$(tabId).jqGrid("navGrid",pagerId,{search: false,
				view: true,
				viewtitle:"详情",
				viewfunc:toDet,
				viewicon:"ace-icon fa fa-search-plus green",
				add: true,
				addtitle:"添加",
				addfunc:toAdd,
				addicon:"ace-icon fa fa-plus-circle purple",
				edit: true,
				edittitle:"修改",
				editfunc:toEdit,
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
		init:function(){
			//div的总数
			this.pageSum=$(".step-pane").length;
		},
		initJoiner:function(){
			//div的总数
			//this.pageSum=$(".step-pane").length;
		},
		initSelect:function(selector){
			$(selector).css('width','200px').select2({allowClear:true});
		},
		initList:function(){
			//列表初始化
			var demo1 = $('select[id="goodsId"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">检索完成</span>'});
			var container1 = demo1.bootstrapDualListbox('getContainer');
			container1.find('.btn').addClass('btn-white btn-info btn-bold');
		},
		initGoods:function(selector){
			yc_public.ajax({url:"",success:function(data){
				var goods=data.data;
				var html="";
				for(var i in goods){
					var good=goods[i];
					html+='<option value='+good.id+'>'+good.goodsName+"重量："+good.weight+"体积："+goods.volume+'</option>';
				}
				$(selector).html(html);
			}});
		},
		initDealer:function(selector){
			yc_public.ajax({url:'getAllDealer.yc',success:function(data){
				if(data.code==0){
					var html='';
					for(var i in data.data){
						var d=data.data[i];
						html+='<option value='+d.id+'>'+d.dealerName+'</option>';
					}
					$(selector).html(html);
				}
			}});
		},
		initDate:function(){
			$('[name=subscribeTime]').datetimepicker({
				 icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows ',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				 }
				}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				});
		},
		isDetail:false,
		goodsArray:null,
		getAppiontData:function(obj,id){
			for(var o in obj){
				if(obj[o].id==id){
					return obj[o];
				}
			}
		},gotoNext:function(){
			//如果只是详情翻页的话，当在最后一页时，就不做任何操作
			if(this.pageSum==this.pageNext)return;
			//+1
			this.pageNext+=1;
			//前往下一页
			$(".step-pane").removeClass("active");
			$("[data-step="+this.pageNext+"]").addClass("active");
			$(".btn-prev").removeAttr("disabled");
			//更改按钮信息
			if(this.pageSum==this.pageNext){
				if(this.isDetail){
					$(".btn-next").html('下页<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
					$(".btn-next").attr('disabled','disabled');
				}else{
					$(".btn-next").html('提交<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
				}
			}
		},
		selectNum_html:'<div class="col-xs-12 col-sm-4">{name}&nbsp;&nbsp;&nbsp;&nbsp;<select goodsNum="goodsNum" group="val" name="goods_{id}"  class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">{option}</select></div>',
		isSubmit:true,
		pageSum:1,
		pageNext:1,
		//是否修改过，默认false
		isMod:false,
		delivery_val:null,
		joiner_id:null,
		//用于存储改变后的id值
		goodsIdArray:new Array(),
		//获取此经销商拥有的货物，于库区储货表内查询
		getGoodsInfo:function(selector,options){
			var joinerId=options.joinerId;
			if(this.joiner_id==joinerId){
				return;
			}
			//data:{"joinerId":joinerId,"branchNo":'YC-59'}
			this.joiner_id=joinerId;
			yc_public.ajax({url:"getGoodsByDealerId.yc",data:options,success:function(data){
				var goods=data.data;
				if(!goods){
					yc_public.alert({msg:"此经销商未有货物.."});
					deliveryCharge_.initList();
					return;
				}
				deliveryCharge_.goodsArray=goods;
				//goods数据的html
				var html="";
				for(var i in goods){
					var good=goods[i];
					var gid=good.id+'_'+good.wayBillNo+'_'+good.goodsType;
					deliveryCharge_.goodsIdArray.push(gid);
					html+='<option value="'+gid+'">'+good.wayBillNo+":"+good.goodsBrand+'--'+good.goodsName+'</option>';
				}
				$(selector).html(html);
			}});
		},
		//计算安装总费用
		sumInstallCost:function(ele){
			var goods=$('#goodsId').val();
			var types=new Array();
			for(var i in goods){
				var tg=goods[i];
				//得到类型集合
				types.push(tg.split('_')[2]);
			}
			//从安装费用标准表里获取安装费用集合
			yc_public.ajax({url:'getYcInstallCostBy.yc',data:{type:types.toString()},success:function(data){
				if(data.code!=0) return;
				$(ele).val(data.data);
			}});
			
		},
		//计算安装总费用
		sumInstallCost_1:function(ele){
			var goods=_goodsObj.goodsIdList;
			
			var types=new Array();
			for(var i in goods){
				var tg=goods[i];
				//得到类型集合
				types.push(tg.split('_')[2]);
			}
			//从安装费用标准表里获取安装费用集合
			yc_public.ajax({url:'getYcInstallCostBy.yc',data:{type:types.toString()},success:function(data){
				if(data.code!=0) return;
				$(ele).val(data.data);
			}});
			
		},
		addSubmit:function(form){
			//验证重复提交
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				//_form.validate();
				var valids=_form.FengValid(this.pageNext);
				//return;
				//验证通过才能进行下一页或者提交
				if(valids){
					if(this.pageNext==1){
						this.sumInstallCost_1('#installCost');
					}
					//开始提交
					var data=new Object();
					data['goodsIds']=_goodsObj.goodsIdList.toString();
					var subData=$.extend(data,yc_public.getData(form));
					if(this.pageNext==this.pageSum){
						yc_public.confirm({msg:"请确认信息..",callback:function(){
							yc_public.ajax({"url":"addYcDeliveryOrder.yc",load:true,"data":subData,"result":"add"});
						}});
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
				//_form.validate();
				var valids=_form.FengValid(this.pageNext);
				//验证通过才能进行下一页或者提交
				if(valids){
					if(this.pageNext==1){
						this.sumInstallCost('#installCost');
					}
					if(this.pageNext==this.pageSum){
						yc_public.confirm({msg:"请确认信息..",callback:function(){
							yc_public.ajax({"url":"modYcDeliveryOrder.yc",load:true,"data":yc_public.getData(form),"result":"add"});
						}});
					}else{
						this.gotoNext();
					}
				}
				this.isSubmit=true;
			}
		},
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
				$(".btn-next").removeAttr('disabled');
			}
		},
		getModData:function(id){
			yc_public.ajax({"url":'getYcDeliveryOrderSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					var datas=data.data;
					yc_public.setData("mod_delivery_order_form",datas);
					//deliveryCharge_.getGoodsInfo("#goodsId",'getAllGoodsByDealerId.yc');
					//初始化货物信息
					//deliveryCharge_.getGoodsInfo("#goodsId",{"joinerId":joinerId,outStorageStatus:0});
					//----
					var joinerId=$("#dealerId").val();
					var dNo=datas.deliveryNo;
					//选中货物
					yc_public.ajax({url:'getYcOrderGoodsListByOrderNo.yc',data:{'deliveryNo':dNo},success:function(data){
						var d=data.data;
						if(data.code!=0)return;
						//用于赋值下拉框的货物id数组
						var ids=new Array();
						//用于判断哪些货物id被取消配送的数组
						var aids=new Array();
						for(var i in d){
							var temp=d[i].zoneGoodsId+'_'+d[i].wayBillNo;
							ids.push(temp);
							aids.push(d[i].zoneGoodsId);
						}
						//初始化货物信息 goodIds虽然取状态为0的货物，但是被选中的货物也要取出来
						deliveryCharge_.getGoodsInfo("#goodsId",{"joinerId":joinerId,outStorageStatus:0,goodIds:aids.toString()});
						//将取消被配送的订单id保存
						$('#afterGoodsId').val(aids.toString());
						var nIds=deliveryCharge_.getLikevalue(deliveryCharge_.goodsIdArray,ids);
						//设置货物id
						$('#goodsId').val(nIds);
						//-----
						//初始认为未作修改
						deliveryCharge_.isMod=false;
					}});
				}
			}});
		},
		getLikevalue:function(arr,ids){
			var res=new Array();
			for(var j in ids){
				var id=ids[j];
				for(var i in arr){
					if(arr[i].indexOf(id)!=-1){
						res.push(arr[i]);
					}
				}
			}
			return res;
		},
		setSelect:function(seid){
			yc_public.ajax({"url":'getYcInstallChargeSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					var cd=data.data;
					var html="";
					for(var i=0;i<cd.length;i++){
						html+='<option value='+i+'>'+i+'</option>';
					}
					$("#"+seid).html(html);
				}
			}});
		},
		findSelect:function(form){
			grid.setGridParam({"postData":yc_public.getData(form)}).trigger("reloadGrid");
		}
}
//前往添加页
function toAdd(){
	//window.parent.f_refreshTab("sd");
	window.parent.f_addTab("add_delivery_order","添加配送单","toAddYcDeliveryOrderPage.yc");
}
//前往修改页
function toEdit(){
	var rowData = grid.getGridParam('selarrrow');
	var data=grid.getRowData(rowData);
	if(data.orderStatus.indexOf('待分配')==-1){
		yc_public.alert({msg:'只有待分配的配送单才能修改...'});
		return;
	}
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("mod_delivery_order","修改配送单","toModYcDeliveryOrderPage.yc?rowId="+rowData[0]);
    }else{
    	yc_public.alert({"msg":"只能选择一项进行修改操作！"});
    }
}
//前往详情页
function toDet(){
	var rowData = grid.getGridParam('selarrrow');
	var data=grid.getRowData(rowData);
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("mod_delivery_order","配送单详情","toDetYcDeliveryOrderPage.yc?rowId="+rowData[0]);
	}else{
		yc_public.alert({"msg":"只能选择一项进行修改操作！"});
	}
}
//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length) {
    	yc_public.confirm({"color":"red","msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		//yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcStowage.yc","result":"del"});
    	}});
    }else{
    	yc_public.alert({"msg":"只能选择一项进行删除操作！"});
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
