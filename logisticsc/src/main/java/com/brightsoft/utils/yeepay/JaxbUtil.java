package com.brightsoft.utils.yeepay;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

/**
 * 使用Jaxb2.0实现XML<->Java Object的Binder.
 * 
 * 特别支持Root对象是List的情形.
 * 
 * @author
 */
public class JaxbUtil {
	// 多线程安全的Context.
	private JAXBContext jaxbContext;

	/**
	 * @param types
	 *            所有需要序列化的Root对象的类型.
	 */
	public JaxbUtil(Class<?>... types) {
		try {
			jaxbContext = JAXBContext.newInstance(types);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Java Object->Xml.
	 */
	public String toXml(Object root, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			
			createMarshaller(encoding).marshal(root, writer);
			return writer.toString().replace("standalone=\"yes\"", "");
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Java Object->Xml, 特别支持对Root Element是Collection的情形.
	 */
	@SuppressWarnings("unchecked")
	public String toXml(Collection root, String rootName, String encoding) {
		try {
			CollectionWrapper wrapper = new CollectionWrapper();
			wrapper.collection = root;

			JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(
					new QName(rootName), CollectionWrapper.class, wrapper);

			StringWriter writer = new StringWriter();
			createMarshaller(encoding).marshal(wrapperElement, writer);

			return writer.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Xml->Java Object.
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromXml(String xml) {
		if(xml!=null && !xml.trim().equals("")){
			try {
				StringReader reader = new StringReader(xml);
				return (T) createUnmarshaller().unmarshal(reader);
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
		}else{
			return null;
		}
		
	}

	/**
	 * Xml->Java Object, 支持大小写敏感或不敏感.
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromXml(String xml, boolean caseSensitive) {
		try {
			String fromXml = xml;
			if (!caseSensitive)
				fromXml = xml.toLowerCase();
			StringReader reader = new StringReader(fromXml);
			return (T) createUnmarshaller().unmarshal(reader);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建Marshaller, 设定encoding(可为Null).
	 */
	public Marshaller createMarshaller(String encoding) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}
			return marshaller;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建UnMarshaller.
	 */
	public Unmarshaller createUnmarshaller() {
		try {
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 封装Root Element 是 Collection的情况.
	 */
	public static class CollectionWrapper {
		@SuppressWarnings("unchecked")
		@XmlAnyElement
		protected Collection collection;
	}
}


