package com.wrt.xinsilu.citywhell.wheel.widget.cascade.activity;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wrt.xinsilu.bean.ProvinceBean;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.model.CityModel;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.model.DistrictModel;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.model.ProvinceModel;
import com.wrt.xinsilu.citywhell.wheel.widget.cascade.service.XmlParserHandler;
import com.wrt.xinsilu.data.LocalData;
import com.xsl.lerist.llibrarys.utils.FileUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class BaseActivity {
	public static final String TAG = "BaseActivity";

	/**
	 * 所有省
	 */
	public String[] mProvinceDatas;
	/**
	 * key - 省 value - 市
	 */
	public Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
	/**
	 * key - 市 values - 区
	 */
	public Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

	/**
	 * key - 区 values - 邮编
	 */
	public Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

	/**
	 * 当前省的名称
	 */
	public String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	public String mCurrentCityName;
	/**
	 * 当前区的名称
	 */
	public String mCurrentDistrictName ="";

	/**
	 * 当前区的邮政编码
	 */
	public String mCurrentZipCode ="";

	/**
	 * 解析省市区的XML数据
	 */

	public void initProvinceDatas()
	{
		List<ProvinceModel> provinceList = null;
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("assets/"+"province_data.xml");
			// 创建一个解析xml的工厂对象
			SAXParserFactory spf = SAXParserFactory.newInstance();
			// 解析xml
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			parser.parse(input, handler);
			input.close();
			// 获取解析出来的数据
			provinceList = handler.getDataList();
			//*/ 初始化默认选中的省、市、区
			if (provinceList!= null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<CityModel> cityList = provinceList.get(0).getCityList();
				if (cityList!= null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<DistrictModel> districtList = cityList.get(0).getDistrictList();
					mCurrentDistrictName = districtList.get(0).getName();
					mCurrentZipCode = districtList.get(0).getZipcode();
				}
			}
			//*/
			mProvinceDatas = new String[provinceList.size()];
			for (int i=0; i< provinceList.size(); i++) {
				// 遍历所有省的数据
				mProvinceDatas[i] = provinceList.get(i).getName();
				List<CityModel> cityList = provinceList.get(i).getCityList();
				String[] cityNames = new String[cityList.size()];
				for (int j=0; j< cityList.size(); j++) {
					// 遍历省下面的所有市的数据
					cityNames[j] = cityList.get(j).getName();
					List<DistrictModel> districtList = cityList.get(j).getDistrictList();
					String[] distrinctNameArray = new String[districtList.size()];
					DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
					for (int k=0; k<districtList.size(); k++) {
						// 遍历市下面所有区/县的数据
						DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
						// 区/县对于的邮编，保存到mZipcodeDatasMap
						mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
						distrinctArray[k] = districtModel;
						distrinctNameArray[k] = districtModel.getName();
					}
					// 市-区/县的数据，保存到mDistrictDatasMap
					mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
				}
				// 省-市的数据，保存到mCitisDatasMap
				mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			for (int i = 0; i < mProvinceDatas.length; i++){
				Log.i(TAG,mProvinceDatas[i]);
			}
			Log.i(TAG,mCurrentProviceName);
			Log.i(TAG,mCurrentCityName);
			Log.i(TAG,mCurrentDistrictName);

		}
	}
	/**Json处理数据城市*/
	public void initProvincedata(LocalData data, Context context) {
		String aa = FileUtils.readFileContent(Environment.getExternalStorageDirectory() + "/city.json");
		List<ProvinceBean> provinceList = JSON.parseArray(aa, ProvinceBean.class);
		try {
			if (provinceList != null && !provinceList.isEmpty()) {
				mCurrentProviceName = provinceList.get(0).getName();
				List<ProvinceBean.CityBean> cityList = provinceList.get(0).getList();
				if (cityList != null && !cityList.isEmpty()) {
					mCurrentCityName = cityList.get(0).getName();
					List<ProvinceBean.CityBean.CountyBean> list = cityList.get(0).getList();
					mCurrentDistrictName = list.get(0).getName();
				}
			}
			mProvinceDatas = new String[provinceList.size()];
			for (int i = 0; i < provinceList.size(); i++) {
				mProvinceDatas[i] = provinceList.get(i).getName();
				List<ProvinceBean.CityBean> cityList = provinceList.get(i).getList();
				String[] cityNames = new String[cityList.size()];
				for (int j = 0; j < cityList.size(); j++) {
					cityNames[j] = cityList.get(j).getName();
					List<ProvinceBean.CityBean.CountyBean> districtList = cityList.get(j).getList();
					String[] distrinctNameArray = new String[districtList.size()];
					DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
					for (int k = 0; k < districtList.size(); k++) {
						// 遍历市下面所有区/县的数据
						DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getPid());
						// 区/县对于的邮编，保存到mZipcodeDatasMap
						mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getPid());
						distrinctArray[k] = districtModel;
						distrinctNameArray[k] = districtModel.getName();
					}
					// 市-区/县的数据，保存到mDistrictDatasMap
					mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
				}
				// 省-市的数据，保存到mCitisDatasMap
				mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
			}
		}catch (Exception e){
				e.printStackTrace();
		}finally {
//			for (int i = 0; i < mProvinceDatas.length; i++){
//				Log.i(TAG,mProvinceDatas[i]);
//			}
//			Log.i(TAG,mCurrentProviceName);
//			Log.i(TAG,mCurrentCityName);
//			Log.i(TAG,mCurrentDistrictName);
		}
	}

}
