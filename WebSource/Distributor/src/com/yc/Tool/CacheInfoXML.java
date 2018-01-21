package com.yc.Tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 读取config.xml中的信息到properties文件，让spring.xml能够直接注入
 * Author:FENG
 * 2016年5月11日
 */
public class CacheInfoXML extends PropertyPlaceholderConfigurer
{
	CacheInfoXML(){
		try {
			this.reader=new SAXReader();
			this.document=this.reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.xml"));
			//获取根节点元素对象  
			this.Config = document.getRootElement();   
			//获取根节点下的字节的：AppSeting
			this.App=Config.element("AppSettings");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	Document document;
	SAXReader reader;
	Element App;
	Element Config;
  protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
    throws BeansException
  {
    try
    {    
          //获取子节点列表
          @SuppressWarnings("unchecked")
          List<Element> lste= App.elements();
          //遍历节点，获取信息并设置
          for(Element e:lste){
        	  props.setProperty(e.getName(), e.getText());
          }
          super.processProperties(beanFactoryToProcess, props);
    }
    catch (Exception e) {
    	e.printStackTrace();
    }
  }
  public Map<String,String> getConfigInfo(){
	  Map<String,String> map=new HashMap<String,String>();
	  //获取子节点列表
      @SuppressWarnings("unchecked")
      List<Element> lste= App.elements();
      //遍历节点，获取信息并设置
      for(Element e:lste){
    	  map.put(e.getName(), e.getText());
      }
	  return map;
  }
}
