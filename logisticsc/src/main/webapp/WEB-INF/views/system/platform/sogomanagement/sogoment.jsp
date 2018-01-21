<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商户管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body feedback_dia{
      overflow-x : auto;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>保险订单列表</h2>
        </div>
        <div class="panel-body">
	        <form id="search_form" class="well form-inline" enctype="multipart/form-data">
	         <label>投保单号：</label> 
	           <input type="text"  class="input-normal" name="insOrderNum" placeholder="投保单号">
	           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>投保单位/个人：</label> 
	           <input type="text"  class="input-normal" name="insTrueName" placeholder="投保单位/个人">
	        	<button type="button" class="button button-normal" onclick="search()">查询</button>
	        </form>
	     	<div id="render_grid">
	       	</div>
	       	<div id="pagingbar">
	       	</div>
       	</div>
    </div>
    
    <!-- 默认隐藏 -->
    <div id="add_edit_dia" class="hide">
    	<form id="ad_form" class="form-horizontal" enctype="multipart/form-data">
    			
    			<div class="span8">投保单位/个人：<span id="insTrueidid" > </span></div>
    			<div class="span8">投保单位/个人证件号：<span id="insCardNumid"> </span></div>
    			<div class="span8">保险品牌：<span id="insComTagid" > </span></div>
    			<div class="span8">保险险种：<span id="insTypeid" > </span></div>
    			<div class="span8">特殊类型：<span id="insTsTypeid"  > </span></div>
    			<div class="span8">联系方式：<span id="insTelid" name="insTelid" > </span></div>
    			<div class="span8">地址：<span id="insAddressid"  > </span></div>
    			<div class="span8">船/航/车牌号：<span id="insCheNumid"  > </span></div>
    			<div class="span8">合同号：<span id="insHeTongNumid"  > </span></div>
    			<div class="span8">货物名称：<span id="insHuoWuidid" > </span></div>
    			<div class="span8">数量/包装：<span id="insBaoZhuangid" > </span></div>
    			<div class="span8">保险金额：<span id="insJingeid"  > </span></div>
    			<div class="span8">投保失败说明：<span id="insDesid"   > </span></div>
    			<div class="span8">保单下载地址：<span id="insFileUrlid"  > </span></div>
    			<div class="span8">起始地详细地址：<span id="startAddressid" > </span></div>
    			<div class="span8">到达地详细地址：<span id="endAddressid"   > </span></div>
    			<div class="span8">保险费用：<span id="insFeeid"  > </span></div>
    			<div class="span8">保单生成日期：<span id="insCreateTimeid"  ></span></div>
    			<div class="span8">保单区域：<span id="insAreaid"  > </span></div>
    			<div class="span8">保单状态：<span id="insStatusid"  > </span></div>
    	
    	</form>
    	</div>
    			
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
	<script type="text/javascript">
	//查询
	function search(){
		var params={
				insOrderNum:$("#insOrderNum").val(),
				insTrueName:$("#insTrueName").val()
		}
		store.load(params);
	}
	        var Grid = BUI.Grid,
	        Toolbar = BUI.Toolbar,
	        Data = BUI.Data;
	        var Grid = Grid,
	        Store = Data.Store,
	        columns = [
		                {title : '投保单号',dataIndex :'insOrderNum'},
		                {title : '保险品牌',dataIndex :'insComTag'},
		                {title : '保险险种',dataIndex :'insType'},
		                {title : '特殊类型',dataIndex :'insTsType'},
		                {title : '投保单位/个人',dataIndex :'insTrueName'},
		                {title : '联系方式',dataIndex :'insTel'},
		                {title : '投保单位/个人证件号',dataIndex :'insCardNum'},
		                /* {title : '地址',dataIndex :'insAddress'},
		                {title : '船/航/车牌号',dataIndex :'insCheNum'},
		                {title : '合同号',dataIndex :'insHeTongNum'},
		                {title : '货物名称',dataIndex :'insHuoWu'},
		                {title : '数量/ 包装',dataIndex :'insBaoZhuang'},
		                {title : '保险金额',dataIndex :'insJinge'},
		                {title : '投保失败说明',dataIndex :'insDes'},
		                {title : '保单下载地址',dataIndex :'insFileUrl'},
		                {title : '起始地详细地址',dataIndex :'startAddress'},
		                {title : '到达地详细地址',dataIndex :'endAddress'},
		                {title : '保险费用',dataIndex :'insFee'},
		                {title : '保单生成日期',dataIndex :'insCreateTime', renderer:BUI.Grid.Format.dateRenderer},
		                {title : '保单区域',dataIndex :'insArea'},
		                {title : '保单状态',dataIndex :'insStatus'},*/
		                {title:'操作',dataIndex:'',width:100,renderer : function(value,obj){
							return '<button  class="button button-primary" onclick="insdetails('+obj.id+')">详情</button>';
					          }
						}
		              ];
	    var store = new Store({
	        url : '<%=request.getContextPath()%>/system/yypt/sogo/listdata.shtml',
	        autoLoad:true,
	        remoteSort : true,
	        pageSize:10   // 需要在store中 配置pageSize
	      }),
	      grid = new Grid.Grid({
	          render:'#render_grid',
	          columns : columns,
	          forceFit : true,
	          store: store,
	          loadMask: true,//加载数据时显示屏蔽层
	          plugins : [Grid.Plugins.AutoFit]	// 插件形式引入自适应宽度
	        });
	   
		//分页工具条
		var bar = new Toolbar.NumberPagingBar({
	          render : '#pagingbar',
	          autoRender: true,
	          elCls : 'pagination pull-right',
	          store : store
	    });
	    grid.render();
	    bar.render();
	    
		//通过id查询
		 function insdetails(id){
				var companyDetailDialog;
			 	BUI.use(['bui/overlay'],function(Overlay){
				 companyDetailDialog = new Overlay.Dialog({
				    	title:'专线商铺详情',
				        width:810,
				        height:360,
				        closeAction : 'destroy', //每次关闭dialog释放
				        contentId:'add_edit_dia',
				        success:function() {
				        	this.close();
				        }
				    });
				 companyDetailDialog.set('effect',{effect : 'fade', duration : 400});
				});
			 	companyDetailDialog.show();
			 	loadCompanyDetail(id);
		}
		
		
			function loadCompanyDetail(id) {
				$.ajax({
					type : 'post',
					url : '<%=request.getContextPath()%>/system/yypt/sogo/sogobyid.shtml',
					data : {'id' : id},
					success : function(result) {
						$('#insTrueidid').text(result.insTrueid == null ? "" :result.insTrueid);
						$('#insCardNumid').text(result.insCardNum== null ? "" :result.insCardNum);
						$('#insComTagid').text(result.insComTag== null ? "" :result.insComTag);
						$('#insTypeid').text(result.insType== null ? "" :result.insType);
						$('#insTsTypeid').text(result.insTsType== null ? "" :result.insTsType);
						$('#insTelid').text(result.insTel== null ? "" :result.insTel);
						$('#insAddressid').text(result.insAddress== null ? "" :result.insAddress);
						$('#insCheNumid').text(result.insCheNum== null ? "" :result.insCheNum);
						$('#insHeTongNumid').text(result.insHeTongNum== null ? "" :result.insHeTongNum);
						$('#insHuoWuid').text(result.insHuoWu== null ? "" :result.insHuoWu);
						$('#insJingeid').text(result.insJinge== null ? "" :result.insJinge);
						$('#insBaoZhuangid').text(result.insBaoZhuang== null ? "" :result.insBaoZhuang);
						$('#insDesid').text(result.insDes== null ? "" :result.insDes);
						$('#insFileUrlid').text(result.insFileUrl== null ? "" :result.insFileUrl);
						$('#startAddressid').text(result.startAddress== null ? "" :result.startAddress);
						$('#endAddressid').text(result.endAddress== null ? "" :result.endAddress);
						$('#insFeeid').text(result.insFee== null ? "" :result.insFee);
						$('#insCreateTimeid').text(result.insCreateTime== null ? "" :result.insCreateTime);
						$('#insAreaid').text(result.insArea== null ? "" :result.insArea);
						$('#insStatusid').text(result.insStatus== null ? "" :result.insStatus);
						
					}
				});
			}
			
			//日期加载
			BUI.use('bui/calendar',function(Calendar){
		        var datepicker = new Calendar.DatePicker({
		        	trigger:'.calendar',
		        	autoRender : true
		    	});
		    });
			
		      BUI.use('bui/form',function(Form){
		          
		          
		          new Form.Form({
		            srcNode : '#J_Form'
		          }).render();
		          
		      });
		</script>
</body>
</html>