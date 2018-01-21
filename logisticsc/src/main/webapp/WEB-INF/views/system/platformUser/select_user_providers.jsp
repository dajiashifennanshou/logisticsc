<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<!-- bootstrap-->
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>

<title>查看企业会员信息</title>
		<style type="text/css">
			.f-cb:after,
			.f-cbli li:after {
				display: block;
				clear: both;
				visibility: hidden;
				height: 0;
				overflow: hidden;
				content: ".";
			}
			
			.f-cb,
			.f-cbli li {
				zoom: 1;
			}
			
			.fl {
				float: left;
			}
			
			.fr {
				float: right;
			}
			
			.new-con {
				width: 940px;
				padding: 20px 0px 20px 30px;
			}
			
			.new-con-list {
				width: 470px;
				margin: 0px 0px 20px 0px;
			}
			
			.new-lef {
				font-size: 14px;
				font-weight: bold;
				width: 104px;
				text-align: right;
				margin-right: 18px;
			}
			
			.new-con-rig {
				color: #000;
			}
			
			.new-con-rig img {
				width: 300px;
				height: 300px;
			}
			.mt20{
				margin-top: 30px;
			}
			.new-con-btn{
				width: 100%;
				text-align: center;
			}
		</style>
</head>
<body>
	<div class="new-con ">
		<input type="text" id="userId" style="display: none;" readonly="readonly" value="${data.userId}" />
	<input type="text" id="temCompanyId" style="display: none;" readonly="readonly" value="${data.temCompanyId}" />
		<div class="f-cb">
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司名称：</div>
				<div class="fl new-con-rig">${data.company_name}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">邮政编码：</div>
				<div class="fl new-con-rig">${data.post_code}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司地址：</div>
				<div class="fl new-con-rig">${data.company_address}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司电话：</div>
				<div class="fl new-con-rig">${data.company_phone}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">法定代表人：</div>
				<div class="fl new-con-rig">${data.legal_person}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司传真：</div>
				<div class="fl new-con-rig">${data.company_fax}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">法定人电话：</div>
				<div class="fl new-con-rig">${data.legal_mobile}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">税务登记号：</div>
				<div class="fl new-con-rig">${data.company_tax_no}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">联系人：</div>
				<div class="fl new-con-rig">${data.contacts}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">财务邮箱：</div>
				<div class="fl new-con-rig">${data.finance_email}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">联系人电话：</div>
				<div class="fl new-con-rig">${data.contacts_mobile}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司介绍：</div>
				<div class="fl new-con-rig">${data.company_info}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">组织代码：</div>
				<div class="fl new-con-rig">${data.company_code}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">QQ：</div>
				<div class="fl new-con-rig">${data.qq}</div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl"> &nbsp; </div>
				<div class="fl new-con-rig">  &nbsp; </div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">营业执照：</div>
				<div class="fl new-con-rig"><img src="/logisticsc/img/retrive.shtml?resource=${data.business_license}" /></div>
				
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司照片：</div>
				<div class="fl new-con-rig"><img src="/logisticsc/img/retrive.shtml?resource=${data.company_photo}" /></div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">法人身份证照：</div>
				<div class="fl new-con-rig"><img src="/logisticsc/img/retrive.shtml?resource=${data.legal_photo}" /></div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">名片照片：</div>
				<div class="fl new-con-rig"><img src="/logisticsc/img/retrive.shtml?resource=${data.card_photo}" /></div>
			</div>
			<div class="f-cb new-con-list fl">
				<div class="new-lef fl">公司logo：</div>
				<div class="fl new-con-rig"><img src="/logisticsc/img/retrive.shtml?resource=${data.logo}" /></div>
			</div>
			
		</div>
		<div class="mt20">
				<div class="form-actions new-con-btn">
					<button type="submit" onclick="verifyUser()" class="button button-primary">返回</button>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function verifyUser(){
				window.location="${configProps['project']}/system/pageJump.shtml?url=/system/platformUser/platform_user_provider_list";
			}
		</script>
</body>
</html>