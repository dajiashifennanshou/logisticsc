var Constant={
		//订单状态：0:待分配1：已配载2：在途3：已配送4：待支付5：已完成
		DeliveryOrderStatus:{
				"0":"<span class='label arrowed arrowed'>待分配</span>",
				"1":"<span class='label label-yellow arrowed'>已配载</span>",
				"2":"<span class='label label-purple arrowed'>在    途</span>",
				"3":"<span class='label label-important arrowed'>已配送</span>",
				"4":"<span class='label label-info arrowed'>待支付</span>",
				"5":"<span class='label label-success arrowed'>已完成</span>"
		},
		tmsDeliveryOrderStatus:{
			"0":"待分配",
			"1":"已配载",
			"2":"在    途",
			"3":"已配送",
			"4":"待支付",
			"5":"已完成"
		},
		//安装评价状态
		InstallEvaluateStatus:{
			"0":"<span class='label label-info arrowed'>待评价</span>",
			"1":"<span class='label label-success arrowed'>已评价</span>"
		},
		//收款单
		FinanceStatus:{
			"0":"<span class='label  arrowed'>未收款</span>",
			"1":"<span class='label label-success arrowed'>已收款</span>",
			"2":"<span class='label  arrowed'>搁置</span>"
		},
		//收款单费用类型
		FinanceType:{
			"0":"<span class='label label-success'>安装费</span>",
			"1":"<span class='label label-success'>配送费</span>",
			"2":"<span class='label label-success'>安装配送费</span>",
			"3":"<span class='label label-success'>预付费</span>",
			"4":"<span class='label label-success'>代收费</span>"
		},
		//配载
		StowageStatus:{
			"0":"<span class='label arrowed'>待出库</span>",
			"1":"<span class='label label-yellow arrowed'>已出库</span>",
			"2":"<span class='label label-purple arrowed'>在途</span>",
			"3":"<span class='label label-success arrowed'>已完成</span>"
		},
		//安装满意度
		InstallEvaluateSatisfaction:{
			"3":"<span class='label label-info arrowed'>待评价</span>",
			"0":"<span class='label label-success arrowed'>不满意</span>",
			"1":"<span class='label label-success arrowed'>满意</span>",
			"2":"<span class='label label-success arrowed'>非常满意</span>"
		},
		//加盟标准状态
		JoinChargeStatus:{
			"0":"<span class='label label-success arrowed'>正在使用</span>",
			"1":"<span class='label label-info arrowed'>暂时搁置</span>"
		},//库区使用状态
		StorageZoneStatus:{
			"0":"<span class='label label-success arrowed'>启用中</span>",
			"1":"<span class='label label-info arrowed'>维护中</span>"
		},//云仓网点合作形式
		StorageBranchJoinType:{
			"0":"<span class='label label-success arrowed'>直营</span>",
			"1":"<span class='label label-info arrowed'>加盟</span>"
		},//云仓网点仓库类型
		StorageBranchType:{
			"0":"<span class='label label-success arrowed'>平面仓库</span>",
			"1":"<span class='label label-info arrowed'>立体仓库</span>"
		},//申请加盟类型
		JoinType:{
			"0":"<span class='label label-success arrowed'>经销商</span>",
			"1":"<span class='label label-info arrowed'>专线</span>"
		},//性别
		sex:{
			"0":"男",
			"1":"女"
		},//车辆类型
		carType:{
			"0":"<span class='label label-success arrowed'>面包车</span>",
			"1":"<span class='label label-success arrowed'>平板车</span>",
			"2":"<span class='label label-success arrowed'>高栏车</span>"
		},//入库类型
		InStorageType:{
			"0":"直接入库",
			"1":"扫描入库",
			"2":"手工入库"
		},//入库方式
		InStorageSource:{
			"0":"线上入库",
			"1":"线下入库"
		},//入库状态
		InZoneStatus:{
			"0":"<span class='label label-success arrowed'>已入库</span>",
			"1":"<span class='label label-info arrowed'>入库异常</span>"
		},//经销商审核状态
		OutZoneStatus:{
			"1":"<span class='label label-success arrowed'>已出库</span>",
			"0":"<span class='label label-info arrowed'>未出库</span>"
		},//经销商审核状态
		dealerStatus:{
			"0":"<span class='label label-info arrowed'>待审核</span>",
			"1":"<span class='label label-success arrowed'>已通过</span>",
			"2":"<span class='label label-info arrowed'>未通过</span>"
		},
		carStatus:{
			"0":"空闲",
			"1":"正在使用",
			"2":"故障"
		},
		useStatus:{
			0:'空闲',
			1:'工作中'
		}
		
}