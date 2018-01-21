package com.brightsoft.model;

import java.io.Serializable;

public class SmsTemplate implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String templateName;
    
    private String templateAbbr;

    private String templateContent;

    private String templateDesc;

    private Integer isEnabled;
    
    private Integer sendConsignor;
    
    private Integer sendConsignee;

    public Integer getSendConsignor() {
		return sendConsignor;
	}

	public void setSendConsignor(Integer sendConsignor) {
		this.sendConsignor = sendConsignor;
	}

	public Integer getSendConsignee() {
		return sendConsignee;
	}

	public void setSendConsignee(Integer sendConsignee) {
		this.sendConsignee = sendConsignee;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getTemplateAbbr() {
		return templateAbbr;
	}

	public void setTemplateAbbr(String templateAbbr) {
		this.templateAbbr = templateAbbr;
	}

	public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent == null ? null : templateContent.trim();
    }

    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc == null ? null : templateDesc.trim();
    }

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
}