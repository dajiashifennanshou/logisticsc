<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保险理赔</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
</head>
<style type="text/css">
.insurance-con {
	width: 1038px;
	line-height: 35px;
	font-size: 12px;
	color: #333;
}
.insurance-con strong {
	color: #0055bf;
}
</style>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="peronal_center_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle" style="float:left;">
		<div class="insurance-img11">
			<img src="/logisticsc/resources/platform/img/banner-insurance1.jpg" />
		</div>
		<div class="insurance-con">
			一、出险以后第一时间请客户先自己<strong>初步判断事故的责任；</strong><br />
			二、如果我方对该次事故应承担责任，不论责任大小只要有责任就请案发后第一时间、第一现场拨打保险公司的<strong>报案热线及时报案</strong>，如果我方没有责任，那么就无需报案，则直接找责任方进行赔偿；<br />
			三、在报案后接到保险公司工作人员联系的时候请把该次<strong>事故的详细情况</strong>及时告知给保险公司工作人员，以便保险公司人员好及时告知 客户接下来的工作；<br />
			四、<strong>出险以后请及时告知所承运车辆司机保管好该车货物所有的相关单据</strong>，以及运输车辆的行驶证、驾驶证、道路运输资格证、道路运输许可证、驾驶员年审资格证的体检表；<br />
			五、<strong>保险理赔详细资料如下：</strong><br />
			□ 出险通知书、损失清单、领取赔款授权书、赔偿确认书(均需被保险人签字/盖章）；<br />
			□ 保险单\ 投保单；<br />
			□ 货物运单、运输合同（运输协议）；<br />
			□ 货物装运清单（记载运输时货物的型号、规格及数量等）；<br />
			□ 货物发票及购销合同；<br />
			□ 设备交接单、货物签收单；<br />
			□ 承运人出具的货损/货差证明（如磅单、理货报告、商检报告等），损失清单；<br />
			□ 事故报告（如海事声明/海事调查报告、交警认定书、消防火灾原因认定书、气象证明等）______；<br />
			□ 出险货物货主索赔函、赔付凭证、货主及贵司权益转让书；<br />
			□ 承运车辆行驶证、道路运输证、驾驶员驾驶证、身体检查证明（注：照行驾证或复印的时候一定注意有效期，务必保证在有效期以内）；<br />
			□ 承运人的三证（组织机构代码证、税务登记证、营业许可证）；<br />
			□ 车辆所有人证件（营业执照、机构代码证/身份证）；<br />
			□ 受损货物的检测维修报告及维修发票 ；<br />
			□ 现场照片 ；<br />
			□ 如果被保险人为个人，需提供被保险人身份证及账号 ；<br />
			□ 如果被保险人是公司名义，需提供组织机构代码证、营业执照、税务登记证、公司账号及开户证明；如果赔款不是直接赔付给被保险人，需提供赔款证明及赔款委托书（需被保险人签章）；<br />
			敬请注意：如果事故案情特殊，为确保您（司）能够获得更加合理的保险赔偿，我司在理赔过程中，可能需要您（司）进一步提供上述所列单证以外的有关证明材料。届时，我司将及时通知您（司）。感谢您（司）对我们工作的理解和支持。<br />
		</div>
	</div>
</div>	
</div>
 <div style="clear: both;"></div>
 <div class="footer">
	<jsp:include page="../bottom.jsp"></jsp:include>
</div>	
</body>
</html>