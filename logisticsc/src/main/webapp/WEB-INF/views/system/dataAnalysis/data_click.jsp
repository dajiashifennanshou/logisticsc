<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>访问量统计分析</title>
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
           	<h2>访问量统计分析</h2>
        </div>
        <div class="panel-body">
	       	<div>
	       		<label>今日访问量（次）：</label>
	       		<label>总访问量（次）：</label>
	       	</div>
       	</div>
    </div>
    <div class="panel">
        <div class="panel-header">
           	<h2>近7天访问量统计</h2>
        </div>
        <div class="panel-body">
	       <div id="canvas"></div>
       	</div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		BUI.use('bui/chart',function (Chart) {
		    
		      var chart = new Chart.Chart({
		        render : '#canvas',
		       
		        height : 500,
		        plotCfg : {
		          margin : [50,50,80] //画板的边距
		        },
		        title : {
		          text : '访问量统计'
		 
		        },
		        subTitle : {
		          text : '近7日访问量统计'
		        },
		        xAxis : {
		          categories : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
		        },
		        yAxis : {
		          title : {
		            text : '访问量',
		            rotate : 0
		          }
		        },  
		        tooltip : {
		          valueSuffix : '/次',
		          shared : true, //是否多个数据序列共同显示信息
		          crosshairs : true //是否出现基准线
		        },
		        series : [{
		              name: '访问量pv',
		              data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 10000]
		          }]
		      });
		 
		      chart.render();
		  });
		
	</script>
</body>
</html>