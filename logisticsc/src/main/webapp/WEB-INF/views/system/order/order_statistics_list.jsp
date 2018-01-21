<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<style type="text/css">

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- <script src="http://g.tbcdn.cn/bui/acharts/1.0.32/acharts-min.js"></script>
 --><script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
 <script type="text/javascript" src="${configProps['resources']}/newbui/js/acharts-min.js"></script>
</head>
<body>
	<!-- grid -->
	<div>
		<div class="detail-section">
			<div id="canvas">
			
			</div>
		</div>
    </div>
	<script type="text/javascript">
    
    function buildChart(x, y){
    	var chart = new AChart({
    	      theme : AChart.Theme.Smooth4,
    	      id : 'canvas',
    	      width : 950,
    	      height : 500,
    	      plotCfg : {
    	        margin : [50,50,80] //画板的边距
    	      },
    	      xAxis : {
    	        categories: x,
    	        labels : {
    	          label : {
    	            rotate : -45,
    	            'text-anchor' : 'end'
    	          }
    	        }
    	      },
    	      yAxis : {
    	        min : 0
    	      },
    	      seriesOptions : { //设置多个序列共同的属性
    	        /*columnCfg : { //公共的样式在此配置

    	        }*/
    	      },
    	      tooltip : {
    	        valueSuffix : '单'
    	      },
    	      series : [ {
    	        name: '订单统计（按月）',
    	        type : 'column',
    	        data: y,
    	        labels : { //显示的文本信息
    	          label : {
    	            rotate : -90,
    	            y : 10,
    	            'fill' : '#fff',
    	            'text-anchor' : 'end',
    	            textShadow: '0 0 3px black',
    	            'font-size' : '14px'
    	          }
    	        }

    	      }]

    	    });

    	    chart.render();
    }
    
    function loadChart(){
    	$.ajax({
    		type : 'post',
    		url : "${configProps['project']}/systemOrder/getSysOrderCount.shtml",
    		success : function(result){
    			var x= new Array("1","2","3","4","5","6","7","8","9","10","11","12");
    			var y= result.data.y;
    			buildChart(x,y);
    		}
    	});
    }
    $(document).ready(function(){
    	loadChart();
    });
    </script>
</body>