package com.brightsoft.model;

import java.io.Serializable;
import java.util.Date;

import com.brightsoft.utils.Const;

public class SysRole implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String rolename;

    private Integer rolestate;

    private String pinyinname;

    private String backup1;

    private String backup2;

    private Date createtime;

    private Long effect;
    
    private String rolesStateStr;
    private String text;
    
    private int value;



	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRolesStateStr() {
		return rolesStateStr;
	}

	public void setRolesStateStr(String rolesStateStr) {
		this.rolesStateStr = rolesStateStr;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
    	
    	this.value = Integer.parseInt(id+"");
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
    	this.setText(rolename);
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getRolestate() {
        return rolestate;
    }

    public void setRolestate(Integer rolestate) {
    	if(rolestate!=null){
    		switch (rolestate) {
			case Const.STATE_YES:
				this.setRolesStateStr("已启用");
				break;
			case Const.STATE_NO:
				this.setRolesStateStr("已停用");
				break;

			default:
				break;
			}
    	}
        this.rolestate = rolestate;
    }

    public String getPinyinname() {
        return pinyinname;
    }

    public void setPinyinname(String pinyinname) {
        this.pinyinname = pinyinname == null ? null : pinyinname.trim();
    }

    public String getBackup1() {
        return backup1;
    }

    public void setBackup1(String backup1) {
        this.backup1 = backup1 == null ? null : backup1.trim();
    }

    public String getBackup2() {
        return backup2;
    }

    public void setBackup2(String backup2) {
        this.backup2 = backup2 == null ? null : backup2.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getEffect() {
        return effect;
    }

    public void setEffect(Long effect) {
        this.effect = effect;
    }
}