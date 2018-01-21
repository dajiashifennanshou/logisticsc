var grid = null;
//配载单类
var stowage_={
		newGrid:function(tabId,pagerId){
			grid = $(tabId).jqGrid({
				url:"getYcStowageList.yc",
				datatype: "json",
				autowidth: true,
				height: '100%',
				colNames:['配载单号','配载状态','配载车辆','司机信息','生成时间','创建人'],
				colModel:[
					/*{name:'id',index:'id',align:'center',sorttype:"int"},*/
					{name:'stowageNo',align:'center'},
					{name:'stowageStatus',align:'center',formatter:function(val){
						return Constant.StowageStatus[val];
					}},
					{name:'carNo',align:'center'},
					{name:'driverInfo',align:'center'},
					{name:'createTime',align:'center',formatter:function(val){
						return DateUtil.RemoveZero(val);
					}},
					{name:'createUser',align:'center'},
				], 
				viewrecords : true,
				page:1,
				rowNum:10,
				rowList:[10,20,30],
				pager : pagerId,
				altRows: true,
				multiselect: true,
				loadComplete : function(xhr) {
					yc_public.ValitedLoadData(xhr);
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
				del: false,
				deltitle:"删除",
				delfunc:del,
				delicon:"ace-icon fa fa-trash-o red",
				refresh: true,
				refreshtitle:"刷新",
				refreshicon:"ace-icon fa fa-refresh green"
			}).navButtonAdd(pagerId,{
				   caption:"",
				   title:'配送出库',
				   buttonicon:"ace-icon fa fa-tags red",   
				   onClickButton: function(){
					   var selected = grid.getGridParam('selarrrow');
					   if(selected.length<=0){
						   yc_public.alert({msg:'请至少选择一行进行操作..'});
						   return;
					   }
					   //var row=grid.getRowData(id);
					   var stowageNos=new Array();
					   for(var i in selected){
						   //如果这个订单不是代配送状态,则提示不能配送
						   var the=grid.getRowData(selected[i]);
						   if(the.stowageStatus.indexOf("待出库")!=-1){
							   stowageNos.push(the.stowageNo);
						   }else{
							   yc_public.alert({msg:'只有待出库的配载单才能出库..'});
							   return;
						   }
						   
					   };
					   yc_public.confirm({"msg":"确定出库这"+selected.length+"项么？","callback":function(){
				    		yc_public.ajax({url:'addYcOutstorage.yc',load:true,data:{stowage:stowageNos.toString()},result:'del'})
				    	}});

				   },
				   position:"last"  

				}).navButtonAdd(pagerId,{
						   caption:"",
						   title:'更改状态为在途',
						   buttonicon:"ace-icon fa fa-tags blue",   
						   onClickButton: function(){
							   var selected = grid.getGridParam('selarrrow');
								var data=grid.getRowData(selected);
							   if(selected.length!=1){
								   yc_public.alert({msg:'只能选择一行进行操作..'});
								   return;
							   }
							   //如果是未完成，就可以前去完成
							   if(data.stowageStatus.indexOf('已出库')==-1  ){
								   yc_public.alert({msg:'只有已出库的的配载单才能操作...'});
								}else{
									yc_public.confirm({msg:'确定更改['+data.stowageNo+']状态为出库?',color:'red',callback:function(){

										 yc_public.ajax({url:'modStatusByNo.yc',load:true,data:{stowageNo:data.stowageNo,stowageStatus:2},success:function(data){
											 if(data.code==0){
												 yc_public.dialog({msg:'状态已更改..',color:'green'});
												 grid.trigger("reloadGrid");
											 }else{
												 yc_public.dialog({msg:'状态更新失败..',color:'red'});
											 }
										 }})
									}})
								}
						   },
						   position:"last"  
						}).navButtonAdd(pagerId,{
							   caption:"",
							   title:'配载单完成',
							   buttonicon:"ace-icon fa fa-tags green",   
							   onClickButton: function(){
								   var selected = grid.getGridParam('selarrrow');
									var data=grid.getRowData(selected);
								   if(selected.length!=1){
									   yc_public.alert({msg:'只能选择一行进行操作..'});
									   return;
								   }
								   //如果是未完成，就可以前去完成
								   if(data.stowageStatus.indexOf('在途')==-1  ){
									  
									   yc_public.alert({msg:'只有在途的的配载单才能操作...'});
									}else{
										 stowage_.stowageOver(data.stowageNo);
									}

							   },
							   position:"last"  

							});
		},
		/**
		 * 为选择配送单填充数据
		 * @param selector
		 */
		initStowage:function(selector,options){
//			var param;
//			if(status){
//				param={orderStatus:status};
//			}else{
//				param={};
//			}
			//deliveryOrderController,获取未配载的配送单
			yc_public.ajax({"url":"getYcDeliveryOrderAllList.yc",data:options,"success":function(data){
				var delivers=data.data;
				var html="";
				for(var i in delivers){
					var deliver=delivers[i];
					//html+='<option value='+deliver.deliveryNo+'_'+deliver.dealerId+'>'+deliver.consigneeName+"  "+deliver.consigneeAddr+'</option>';
					html+='<option value='+deliver.deliveryNo+'_'+deliver.dealerId+'>'+deliver.consigneeName+"  "+deliver.consigneeAddr+'</option>';
				}
				$(selector).html(html);
			}});
		},init:function(){
			//div的总数
			this.pageSum=$(".step-pane").length;
		},
		initCar:function(selector,options){
			//carController,只获取状态为空闲的车
			yc_public.ajax({"url":"getYcCarAllList.yc",data:options,"success":function(data){
				var html="";
				var cars=data.data;
				for(var i in cars){
					var car=cars[i];
					html+='<option value='+car.carNo+'>'+car.carNo+"  载重质量："+car.weight+"Kg 载重体积："+car.volume+'m³   司机：'+car.dreverName+'</option>';
				}
				$(selector).html(html);
			}});
		},
		initSelect:function(selector){
			$(selector).css('width','100%').select2({allowClear:false});
		},
		initList:function(){
			//列表初始化
			var demo1 = $('select[id="deliveryNo"]').bootstrapDualListbox({infoTextFiltered: '<span class="label label-purple label-lg">检索完成</span>'});
			var container1 = demo1.bootstrapDualListbox('getContainer');
			container1.find('.btn').addClass('btn-white btn-info btn-bold');
		},
		/**
		 * 判断重复提交
		 */
		isDetail:false,
		isSubmit:true,
		pageSum:1,
		pageNext:1,
		getGoodsData:function(no,callback){
			yc_public.ajax({"url":"getGoodsByDeliveryNo.yc","data":{dNo:no},"success":function(data){
				callback(data);
			}});
		},
		getInstallerData:function(callback,options){
			//employeeController
//			var param;
//			if(status){
//				param={useStatus:status,position:3};
//			}else{
//				param={position:3};
//			}
			yc_public.ajax({"url":"getEmployee.yc",data:options,"success":function(data){
				callback(data);
			}});
		},
		selectInstallerHtml:'<div  class="col-xs-4">配送单：{psNo}&nbsp;&nbsp;&nbsp;货物信息:↓<select class="form-control" id="form-field-select-5" multiple="multiple">'
				+'{goodsOptions}'
				+'</select>'
				+'选择<label for="installer_{psNo}">安装人员</label>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
				+'<select group="val" installer="installer" valited="required" name="installer_{psNo}" multiple="multiple"   class="select2-container select2-allowclear select2 tag-input-style" data-placeholder="点击选择...">'
				+'{installOptions}'
				+'</select>'
				+'</div>',
		deliver_val:null,
		selectInstaller:function(options){
			//获取货物
			var deliveryVal=$("#deliveryNo").val();
			//如果值未改变，那么就不用重新赋值。
			//alert(this.deliver_val+":"+deliveryVal);
			if(yc_public.EqualsObjec(this.deliver_val,deliveryVal)){
				return;
			}
			this.deliver_val=deliveryVal;
			//selctNumHmtl
			var html="";
			//var hiddenHtml='<input type="hidden" group="val" name="deliveryNos" value="'+deliveryVal.toString()+'" />';
			for(var i in deliveryVal){
				//id集合
				var t=deliveryVal[i].split('_')[0];
				var thisHtml=this.selectInstallerHtml.replace(/{psNo}/g,t);
				//获取指定id的货物对象select_num_
				this.getGoodsData(t,function(data){
					//获取货物集合
					var goodsArray=data.data;
					var goodsOptions="";
					for(var i in goodsArray){
						//当前货物
						var td=goodsArray[i];
						goodsOptions+='<option>'+td.goodsName+'</option>';
					}
					//获取安装工信息
					stowage_.getInstallerData(function(data){
						var inArray=data.data;
						var inOptions="";
						for(var i in inArray){
							var ti=inArray[i];
								//inOptions+='<option selected="selected" value='+ti.id+'>'+ti.employeeName+'</option>';
								inOptions+='<option value='+ti.id+'>'+ti.employeeName+'</option>';
						}
						//拼接
						thisHtml=thisHtml.replace(/{installOptions}/,inOptions).replace(/{goodsOptions}/,goodsOptions);
					},options);
				});
				html+=thisHtml;
			}
			//将生产好的html代码设置到页面中
			$("#selectInstaller").html(html);
			//$("#selectInstaller").append(hiddenHtml);
			//初始化选择框--已转移至提交方法里
		},
		gotoNext:function(){
			//如果只是详情翻页的话，当在最后一页时，就不做任何操作
			if(this.pageSum==this.pageNext)return;
			//+1
			this.pageNext+=1;
			//前往下一页
			$(".step-pane").removeClass("active");
			$("[data-step="+this.pageNext+"]").addClass("active");
			$(".btn-prev").removeAttr("disabled");
			//更改按钮信息
			if(this.isDetail){
				$(".btn-next").html('下页<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
				$(".btn-next").attr('disabled','disabled');
			}else{
				$(".btn-next").html('提交<i class="ace-icon fa fa-arrow-right icon-on-right"></i>');
			}
		},
		/**
		 * 添加的提交方法 
		 * @param form 表单id eg:xxform
		 */
		addSubmit:function(form){
			//验证重复提交
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				//_form.validate();
				//验证表单
				var valids=_form.FengValid(this.pageNext);
				//验证通过才能进行下一页或者提交
				if(valids){
					
					//选择配送员面板
					if(this.pageNext==1){
						this.selectInstaller({useStatus:0,position:3});
						//初始化选择框
						stowage_.initSelect('[installer=installer]');
						stowage_.initList();
					}
					if(this.pageNext==this.pageSum){
						yc_public.confirm({msg:"请确认信息..",callback:function(){
							yc_public.ajax({"url":"addYcStowage.yc",load:true,"data":yc_public.getData(form),"result":"add"});
						}});
					}else{
						this.gotoNext();
					}
				}
				this.isSubmit=true;
			}
		},
		/**
		 * 修改的提交事件
		 * @param form 表单的ideg：xxxfrom
		 */
		modSubmit:function(form){
			if(this.isSubmit){
				this.isSubmit=false;
				var _form=$("#"+form);
				//_form.validate();
				var valids=_form.FengValid(this.pageNext);
				//验证通过才能进行下一页或者提交
				if(valids){
					//选择配送员面板
					if(this.pageNext==1){
						this.selectInstaller({useStatus:0,position:3});
						//初始化选择框
						stowage_.initSelect('[installer=installer]');
						stowage_.initList();
					}
					if(this.pageNext==this.pageSum){
						yc_public.confirm({msg:"请确认信息..",callback:function(){
								yc_public.ajax({"url":"modYcStowage.yc",load:true,"data":yc_public.getData(form),"result":"add"});
							}
						});
					}else{
						this.gotoNext();
					}
				}
				this.isSubmit=true;
			}
		},
		/**
		 * 上一页的事件
		 */
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
			yc_public.ajax({"url":'getYcStowageSingle.yc',data:{"id":id},success:function(data){
				if(data.code==0){
					var det=data.data;
					var stowageNo=det.stowageNo;
					//填充车辆信息
					stowage_.initCar("#carNo",{carStatus:0,carNo:det.carNo});
					//表单赋值
					yc_public.setData("mod_stowage_form",det);
					yc_public.ajax({url:'getYcStowageOrderAllList.yc',data:{stowageNo:stowageNo},success:function(data){
						//获取的数据的集合
						 var delivers=data.data;
						 var dNos=new Array();
						 var dNoV=new Array();
						 for(var i in delivers){
							 //添加到集合里，用于select框赋值
							 dNos.push(delivers[i].deliverNo);
							 dNoV.push(delivers[i].deliverNo+'_'+delivers[i].dealerId);
						 }
						 //初始化订单选择框
						 stowage_.initStowage('#deliveryNo',{orderStatus:0,deliveryNo:dNos.toString()});
						 //赋值
						 //初始化订单赋值，将已经选择的货物赋予初始值
						 //保存之前的订单号值，用于后台使用
						 $('#afterDNo').val(dNos);
						 //选中之前选择的订单
						 $("#deliveryNo").val(dNoV);
						 
						 //初始化安装工赋值
						 for(var i in delivers){
							 var d=delivers[i];
							 //初始化安装工
							 stowage_.selectInstaller({useStatus:0,position:3,ins:d.employeeId});
							 $('[name=installer_'+d.deliverNo+']').val(d.employeeId.split(','));
							
						 }
						//表示已经选择了这几条
						 stowage_.deliver_val=dNoV;
						 //初始化选择框样式
						 stowage_.initSelect('[installer=installer]');
					 }});
				}
			}});
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

		},stowageOver:function(stowageNo){
			yc_public.ajax({"url":'YcStowageOver.yc',load:true,data:{"stowageNo":stowageNo},success:function(data){
				if(data.code==0){
					yc_public.dialog({msg:"配载单已完成",color:'green'});
					grid.trigger("reloadGrid");
				}else{
					yc_public.alert({msg:data.msg});
				}
			}});
		}
}
//前往添加页
function toAdd(){
	//window.parent.f_refreshTab("sd");
	window.parent.f_addTab("add_stowage","添加配载单","toAddYcStowagePage.yc");
}
//前往修改页
function toEdit(){
	var rowData = grid.getGridParam('selarrrow');
	var data=grid.getRowData(rowData);
	if(data.stowageStatus.indexOf('待配送')==-1){
		yc_public.alert({msg:'只有待配送的配载单才能修改...'});
		return;
	}
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("mod_stowage","修改配载单","toModYcStowagePage.yc?rowId="+rowData[0]);
    }else{
    	yc_public.alert({"msg":"只能选择一项进行修改操作！"});
    }
}
//前往详情页
function toDet(){
	var rowData = grid.getGridParam('selarrrow');
	
	if(rowData.length == 1){
		//var Data = grid.getRowData(rowData);
		//获取id
		window.parent.f_addTab("det_stowage","配载单详情","toDetYcStowagePage.yc?rowId="+rowData[0]);
	}else{
		yc_public.alert({"msg":"只能选择一项进行修改操作！"});
	}
}
//删除
function del(){
	var rowData = grid.getGridParam('selarrrow');
    if(rowData.length) {
    	yc_public.confirm({"msg":"确定删除这"+rowData.length+"项么？","callback":function(){
    		yc_public.ajax({"data":{"ids":rowData.toString()},"url":"delYcStowage.yc","result":"del"});
    	}});
    }else{
    	yc_public.alert({"msg":"请选择至少一项进行删除操作！"});
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
