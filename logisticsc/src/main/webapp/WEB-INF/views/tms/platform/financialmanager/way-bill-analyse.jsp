<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运单统计</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>运单统计</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form class="well form-inline">
				        	<label>网点：</label>
				        	<select>
				        		<option>成都</option>
				        		<option>重庆</option>
				        	</select>
				            <button type="button" class="button">统计</button>
				        </form>
					</div>
				</div>
				<div id="canvas"></div>
			</div>
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
	          text : '一年的运单统计'
	        },
	        /* subTitle : {
	          text : 'Source: WorldClimate.com'
	        }, */
	        xAxis : {
	          categories : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
	        },
	        yAxis : {
	          title : {
	            text : '运单数',
	            rotate : -90
	          }
	        },  
	        tooltip : {
	          valueSuffix : '',
	          shared : true, //是否多个数据序列共同显示信息
	          crosshairs : true //是否出现基准线
	        },
	        series : [{
	              name: '运单数',
	              data: [20, 30, 12, 7, 20, 11, 9, 8, 7, 4, 6, 29]
	          }]
	      });
	 
	      chart.render();
	  });
	</script>
</body>
</html>