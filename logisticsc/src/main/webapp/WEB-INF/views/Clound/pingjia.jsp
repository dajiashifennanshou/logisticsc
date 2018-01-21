<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<title>云仓管理系统-发表评价</title>
		<script type="text/javascript" src="/logisticsc/Clound/js/jquery-2.2.4.min.js"></script>
    	<script type="text/javascript" src="/logisticsc/Clound/js/startScore.js"></script>
    	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
    	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
		<style>
			body,li,p,ul { 
			    margin: 0;
			    padding: 0;
			    background-color:white;
			    font: 12px/1 Tahoma, Helvetica, Arial, "\5b8b\4f53", sans-serif;
			}
			ul, li, ol { list-style: none; }
			/* 重置文本格式元素 */
			a { text-decoration: none; cursor: pointer; color:#333333; font-size:14px;}
			a:hover { text-decoration: none; }
			.clearfix::after{ display:block; content:''; height:0; overflow:hidden; clear:both;} 
			/*星星样式*/
			.content{ margin:0 auto;width:95%; padding-top:20px;margin-left:2%;}
			.title{ font-size:14px; background:#dfdfdf; width:100%;padding:10px; margin-bottom:10px;}
			.block{ width:100%; margin:0 0 20px 0; padding-top:10px; padding-left:20%; line-height:21px;}
			.area{border:1px solid #ccc;margin-left:2%;width:95%;height:260px;font-size: 14px;margin-top:6px;}
			.block .star_score{ float:left;}
			.star_list{height:21px;margin:50px; line-height:21px;}
			.block p,.block .attitude{ padding-left:20px; line-height:21px; display:inline-block;}
			.block p span{ color:#C00; font-size:16px; font-family:Georgia, "Times New Roman", Times, serif;}
			.star_score { background:url(Clound/img/stark2.png); width:160px; height:21px;  position:relative; }
			.star_score a{ height:21px; display:block; text-indent:-999em; position:absolute;left:0;}
			.star_score a:hover{ background:url(Clound/img/stars2.png);left:0;}
			.star_score a.clibg{ background:url(Clound/img/stars2.png);left:0;}
			#starttwo .star_score { background:url(Clound/img/starky.png);}
			#starttwo .star_score a:hover{ background:url(Clound/img/starsy.png);left:0;}
			#starttwo .star_score a.clibg{ background:url(Clound/img/starsy.png);left:0;}
			#startthree .star_score { background:url(Clound/img/starky.png);}
			#startthree .star_score a:hover{ background:url(Clound/img/starsy.png);left:0;}
			#startthree .star_score a.clibg{ background:url(Clound/img/starsy.png);left:0;}
			#startfor .star_score { background:url(Clound/img/starky.png);}
			#startfor .star_score a:hover{ background:url(Clound/img/starsy.png);left:0;}
			#startfor .star_score a.clibg{ background:url(Clound/img/starsy.png);left:0;}
			/*星星样式*/
			.show_number{ padding-left:50px; padding-top:20px;}
			.show_number li{ width:240px; border:1px solid #ccc; padding:10px; margin-right:5px; margin-bottom:20px;}
			.atar_Show{background:url(Clound/img/stark2.png); width:160px; height:21px;  position:relative; float:left; }
			.atar_Show p{ background:url(Clound/img/stars2.png);left:0; height:21px; width:134px;}
			.show_number li span{ display:inline-block; line-height:21px;}
			.label_ {float:left;display:inline-block;}
			</style>
			<script type="text/javascript">
				var no=request.QueryString('orderNo');
				if(no==="已评价"){
					yc_public.alert({msg:'此订单已做过评价..'});
					window.location='www.baidu.com';
				}
				function submit(){
					if(no==="已评价"){
						return;
					}
					var orderNo=$('#orderNo').val();
					if(/^1\d{10}$/.test( orderNo )){
						var pj=$("#pj_").val();
						var pf1=$("#pf_1").val();
						var pf2=$("#pf_2").val();
						var pf3=$("#pf_3").val();
						yc_public.confirm({msg:'请确认信息..',callback:function(){
							yc_public.ajax({url:'modYcDeliveryOrderEvaluate.yc',load:true,data:{type:'mod',consigneePhone:orderNo,satisfaction:pf1,customerSug:pj},success:function(data){
								yc_public.dialog({msg:data.msg});
							}})
						}})
					}else{
						alert("请输入正确的手机号..");
					}
					
					//alert(pj+""+pf1+"-"+pf2+"-"+pf3+"-");
				}
			</script>
	</head>
	<body>
		<br/>
		<div style="width:95%;height:30px;border:0px;margin-left:2%;"><label >手机号：</label><input id="orderNo" type="text" /></div>
		<textarea id="pj_" placeholder="这里填写评价.." class="area"></textarea>	
		
		<br/>
		<div class="content">
		    
		    <p class="title">给予星级评价</p>
		    <div id="starttwo" class="block clearfix">
		       <div class="label_">货物完好&nbsp;&nbsp;&nbsp;&nbsp;</div><div  class="star_score"></div> <div class="attitude"></div>
		        <input id="pf_1" type="hidden" name="fens" />
		    </div>
		    <div id="startthree" class="block clearfix">
		       <div class="label_">服务态度&nbsp;&nbsp;&nbsp;&nbsp;</div><div  class="star_score"></div> <div class="attitude"></div>
		        <input id="pf_2" type="hidden" name="fens" />
		    </div>
		    <div id="startfor" class="block clearfix">
		       <div class="label_">准时送货&nbsp;&nbsp;&nbsp;&nbsp;</div><div class="star_score"></div> <div class="attitude"></div>
		        <input id="pf_3" type="hidden" name="fens" />
		    </div>
		    <script>
		     scoreFun($("#starttwo"),{
		           fen_d:22,//每一个a的宽度
		           ScoreGrade:5//a的个数 10或者
		         })
		     scoreFun($("#startthree"),{
		           fen_d:22,//每一个a的宽度
		           ScoreGrade:5//a的个数 10或者
		         })
		     scoreFun($("#startfor"),{
		           fen_d:22,//每一个a的宽度
		           ScoreGrade:5//a的个数 10或者
		         })
		    </script>
		  </div>
		  	<div onclick="submit();" style="margin-right:4px;background-color:#33CC00;float:right;width:100px;text-align:center;height:50px;"><a style="height:50px;line-height:50px;color:white;width:100px;" href="javascript:;" ><b>发表评价</b></a></div>
	</body>
</html>
