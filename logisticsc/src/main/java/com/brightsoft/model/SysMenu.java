package com.brightsoft.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.brightsoft.utils.Const;

public class SysMenu implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Long id;

    private String menuname;

    private Long parentId;

    private String menuUrl;

    private String menuIcon;

    private Integer sortIndex;

    private Integer menuState;

    private String menuRemark;

    private Integer parentSort;

    private Long effect;
    
    private int level;

    private String menuStateStr;
    
	public String getMenuStateStr() {
		return menuStateStr;
	}

	public void setMenuStateStr(String menuStateStr) {
		this.menuStateStr = menuStateStr;
	}

	private boolean checked = false;



    /**
   	 * 子项
   	 */
   	private List<SysMenu> subMenus = new ArrayList<SysMenu>();
   	
   	/*
   	 * 展开
   	 */
    private boolean	expanded = true;
    
    public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public List<SysMenu> getSubMenus() {
   		return subMenus;
   	}

   	public void setSubMenus(List<SysMenu> subMenus) {
   		this.subMenus = subMenus;
   	}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Integer getMenuState() {
        return menuState;
    }

    public void setMenuState(Integer menuState) {
    	if(menuState!=null){
    		switch (menuState) {
			case Const.STATE_YES:
				this.setMenuStateStr("可用");
				break;
			case Const.STATE_NO:
				this.setMenuStateStr("停用");
				break;

			default:
				break;
			}
    	}
        this.menuState = menuState;
    }

    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark == null ? null : menuRemark.trim();
    }

    public Integer getParentSort() {
        return parentSort;
    }

    public void setParentSort(Integer parentSort) {
        this.parentSort = parentSort;
    }

    public Long getEffect() {
        return effect;
    }

    public void setEffect(Long effect) {
        this.effect = effect;
    }
    
    public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}