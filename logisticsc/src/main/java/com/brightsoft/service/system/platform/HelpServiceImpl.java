package com.brightsoft.service.system.platform;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightsoft.dao.system.SysHelpMapper;
import com.brightsoft.model.SysHelp;

@Service
public class HelpServiceImpl {
	
	@Autowired
	private SysHelpMapper helpMapper;
	
	 public SysHelp getHelp(){
		   
		   List<SysHelp> helps = helpMapper.selectListSysyHelp();

		   SysHelp sysHelp = new SysHelp();
		   this.createTree(helps, sysHelp);
	    	
	    	return sysHelp;
	    }
	 
	 private void createTree(List<SysHelp> list,SysHelp treeSysMenu){
			
		for(int i = 0,j=list.size();i<j;i++){
			SysHelp sysHelp = list.get(i);

			if(sysHelp.getParentId() == treeSysMenu.getId()){
				
				treeSysMenu.getHelps().add(sysHelp);
				
				this.createTree(list, sysHelp);
				
			}
		}
	}
	 
	 public boolean insertHelp(SysHelp help){
		 help.setHeleTime(new Date());
		 help.setHelpState(1);
		 if(helpMapper.insertSelective(help) > 0){
			 return true;
		 }
		 return false;
	 }
	 
	 public boolean delete(Long id){
		 if(helpMapper.updateHelp(id)> 0){
			 List<SysHelp> helps = helpMapper.selectHelps(id);
			 if(helps.size() !=0){
				 for (int i = 0; i < helps.size(); i++) {
					if(helpMapper.updateHelp(helps.get(i).getId()) > 0){
						List<SysHelp> sysHelps =helpMapper.selectHelps(helps.get(i).getId());
						if(sysHelps.size() !=0){
							for (int j = 0; j < sysHelps.size(); j++) {
								if(helpMapper.updateHelp(helps.get(i).getId()) > 0){
								}else{
									 return false;
								}
							}
						}else{
							 return true;
						}
					}
					else{
						 return false;
					}
				}
			 }else{
				 return true;
			 }
		 }
		 return false;
	 }
}
