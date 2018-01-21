package com.brightsoft.service.platform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brightsoft.dao.tms.MessageSubscriptionConfMapper;
import com.brightsoft.dao.tms.MessageSubscriptionMapper;
import com.brightsoft.model.MessageSubscription;
import com.brightsoft.model.MessageSubscriptionConf;
import com.brightsoft.utils.Const;

@Service("platformUserMessageSubscription")
public class PlatformUserMessageSubscriptionServiceImpl {
	
	@Autowired
	private MessageSubscriptionMapper messageSubscriptionMapper;
	
	@Autowired
	private MessageSubscriptionConfMapper messageSubscriptionConfMapper;
	
	/**
	 * 获取当前用户订阅信息
	 * @param userId
	 * @return
	 */
	public List<MessageSubscription> selectMessageSubscription(Long userId){
		List<MessageSubscription> subscriptions = messageSubscriptionMapper.selectMessageSubscription();
		List<MessageSubscriptionConf> subscriptionConfs = messageSubscriptionConfMapper.selectMessageSubscriptionConf(userId);
		for (int i = 0; i < subscriptions.size(); i++) {
			for (int j = 0; j < subscriptionConfs.size(); j++) {
				if(subscriptions.get(i).getId() == subscriptionConfs.get(j).getMessageId()){
					subscriptions.get(i).setChecked(Const.TMS_MESSAGE_CGECKED_1);
					break;
				}else{
					subscriptions.get(i).setChecked(Const.TMS_MESSAGE_CGECKED_0);
				}
			}
		}
		return subscriptions;
	}
	/**
	 * 增加当前用户订阅信息
	 */
	public boolean insertMessageSubscriptionConf(List<Long> list,Long userId){
		List<MessageSubscription> subscriptions = messageSubscriptionMapper.selectMessageSubscription();
		List<MessageSubscriptionConf> subscriptionConfs = new ArrayList<MessageSubscriptionConf>();
		for (int i = 0; i < subscriptions.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				MessageSubscriptionConf conf = new MessageSubscriptionConf();
				if(subscriptions.get(i).getId() == list.get(j)){
					conf.setMessageId(subscriptions.get(i).getId());
					conf.setNoticeType(subscriptions.get(i).getNoticeType());
					conf.setUserId(userId);
					subscriptionConfs.add(conf);
					break;
				}
			}
		}
		if(messageSubscriptionConfMapper.insertMessageSubscriptionConf(subscriptionConfs)>0){
			return true;
		}
		return false;
	}
	/**
	 * 删除我的消息订阅
	 * @param userId
	 * @return
	 */
	public boolean deleteMessageSubscriptionConf(Long userId){
		List<MessageSubscriptionConf> subscriptionConfs = messageSubscriptionConfMapper.selectMessageSubscriptionConf(userId);
		if(subscriptionConfs.size() > 0){
			if(messageSubscriptionConfMapper.deleteMessageSubscriptionConf(subscriptionConfs) > 0){
				return true;	
			}
		}else{
			return true;
		}
		return false;
	}
}
