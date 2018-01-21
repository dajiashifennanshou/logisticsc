<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据统计</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
 <style>
    /**内容超出 出现滚动条 **/
    .bui-stdmod-body feedback_dia{
      overflow-x : hidden;
      overflow-y : auto;
    }
  </style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>数据统计</h2>
        </div>
        <div class="panel-body">
	       	<div>
	       		<div style="border:1px solid gray;width:120px;height:50px;background:rgb(0,0,255);line-height:50px;text-align:center;display: inline-block;" onclick="getOrderView()">交易量（笔）：<span id="order_amount"></span></div>
	       		<div style="border:1px solid gray;width:120px;height:50px;background:rgb(0,0,255);line-height:50px;text-align:center;display: inline-block;" onclick="getPvView()">浏览量（次）：<span  id="pv_"></span></div>
	       		<div id="canvas"></div>
	       	</div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	  var chart = {};
	  BUI.use('bui/chart',function (Chart) {
	      chart = new Chart.Chart({
		        render : '#canvas',
		       
		        height : 500,
		        plotCfg : {
		          margin : [50,50,80] //画板的边距
		        },
		         title : {
		          text : ''
		 
		        },
		        subTitle : {
		          text : ''
		        }, 
		        xAxis : {
		          categories : []
		        }, 
		        yAxis : {
		          title : {
		            text : '',
		            rotate : -90
		          }
		        },  
		        tooltip : {
		          valueSuffix : '',
		          shared : true, //是否多个数据序列共同显示信息
		          crosshairs : true //是否出现基准线
		        },
		        series : [{
		              name: '',
		              data: []
		        
		         }]
	      	});
	      getMonthPv();
	      chart.render();
	  })
	  
	  BUI.use('bui/chart',function (Chart) {
	      chart = new Chart.Chart({
		        render : '#canvas',
		       
		        height : 500,
		        plotCfg : {
		          margin : [50,50,80] //画板的边距
		        },
		         title : {
		          text : ''
		 
		        },
		        subTitle : {
		          text : ''
		        }, 
		        xAxis : {
		          categories : []
		        }, 
		        yAxis : {
		          title : {
		            text : '',
		            rotate : -90
		          }
		        },  
		        tooltip : {
		          valueSuffix : '',
		          shared : true, //是否多个数据序列共同显示信息
		          crosshairs : true //是否出现基准线
		        },
		        series : [{
		              name: '',
		              data: []
		        
		         }]
	      	});
	      getMonthOrder();
	      chart.render();
	  })
	  
	  $(function(){
		  getData();
	  })
	  
	  function getPvView(){
	  }
	  
	  function getOrderView(){
		  getMonthOrder();
	      chart.render();
	  }
	  
	  function getData(){
		  	$.ajax({
			  	url:'<%=request.getContextPath()%>/system/dataAnalysis/getData.shtml',
		  		type:'post',
		  		success:function(result){
		  			if(result.result){
		  				var map = result.data;
		  				$("#order_amount").html(map.orderAmount);
		  				$("#pv_").html(map.pv);
		  			}
		  		}
		  	})
	  	}
	  
	  function getMonthPv(){
		  $.ajax({
			  url:'<%=request.getContextPath()%>/system/dataAnalysis/getMonthPv.shtml',
		  	  type:'post',
		  	  async:false,
		  	  success:function(result){
		  		  if(result.result){
		  			  var ax = chart.get("xAxis"),
		  			  	yx = chart.get("yAxis"),
		  			  	series = chart.get("series")[0],
		  			  	pvData = result.data;
		  			  
		  			  chart.get("title").text="当月pv量";
		  			  chart.get("tooltip").text="（次）";
		  			  /* chart.get("subTitle").text="当前pv量"; */
		  			  series.name= 'pv量';
		  			  series.title = 'pv';
		  			  yx.title.text = 'pv(次)';
		  			  for(var i=1;i<=30;i++){
		  				var isEq = false;
		  				for(var j = 0;j<pvData.length;j++){
		  					if(pvData[j].day == i){
		  						ax.categories.push(i+"日");
		  						series.data.push(pvData[j].total);
		  						isEq = true;
		  						break;
		  					}
			  			  }
		  				if(!isEq){
		  					ax.categories.push(i+"日");
	  						series.data.push(0);
		  				}
		  				
		  			  }
		  			  
		  		  }
		  	  }
		  })
	  }
	  
	  function getMonthOrder(){
		  $.ajax({
			  url:'<%=request.getContextPath()%>/system/dataAnalysis/getMonthOrder.shtm',
		  	  type:'post',
		  	  success:function(result){
		  		  if(result.result){
		  			var ax = chart.get("xAxis"),
	  			  	yx = chart.get("yAxis"),
	  			  	series = chart.get("series")[0],
	  			  	pvData = result.data;
	  			  
		  			chart.get("title").text="当月交易量";
		  			chart.get("tooltip").text="（笔）";
		  			 /*  chart.get("subTitle").text="当前pv量"; */
		  			  series.name= '订单量';
		  			  series.title = '订单量';
		  			  yx.title.text = '订单量(笔)';
		  			  for(var i=1;i<=30;i++){
		  				var isEq = false;
		  				for(var j = 0;j<pvData.length;j++){
		  					if(pvData[j].day == i){
		  						ax.categories.push(i+"日");
		  						series.data.push(pvData[j].total);
		  						isEq = true;
		  						break;
		  					}
			  			  }
		  				if(!isEq){
		  					ax.categories.push(i+"日");
	  						series.data.push(0);
		  				}
		  				
		  			  }
		  		  }
		  	  },
		  })
	  }
	</script>
</body>
</html>