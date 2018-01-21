package com.brightsoft.service.system.platform;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.platform.PlatformInsuranceMapper;
import com.brightsoft.model.PlatformInsurance;
import com.brightsoft.utils.Page;
import com.brightsoft.utils.Result;

/**
 * 2016年3月21日 下午6:17:49
 * @author zhouna
 */
@Service
public class OperSogoManageServiceImpl implements OperSogoManageService {
	
	@Autowired 
	private PlatformInsuranceMapper  platformInsuranceMapper;

	/**查询保险所有信息*/
	public Result OperSogoListfindall(HttpSession session,Page<?> page, PlatformInsurance platformInsurance) {
		Result result = new Result();
		try {
			int results = platformInsuranceMapper.countplatformInsuranceRows(platformInsurance);
			List<PlatformInsurance> list = platformInsuranceMapper.OperSogoListfindall(page,platformInsurance);
			result.setResults(results);
			result.setRows(list);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(false);
		}
		return result;
	}

	/**通过id查看保险信息*/
	public PlatformInsurance OperSogoById(long id) {
		try {
			PlatformInsurance platformInsurance = platformInsuranceMapper.OperSogoById(id);
			if (platformInsurance != null) {
				return platformInsurance;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
