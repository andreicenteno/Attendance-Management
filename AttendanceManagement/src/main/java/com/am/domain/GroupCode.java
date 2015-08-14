package com.am.domain;

import com.am.model.Group;


public enum GroupCode {
	
	KKB(1L), YAM(2L), CHILDREN(3L), MEN(4L), WOMEN(5L);
	
	private Group group;
	
	private GroupCode(Long group_id){
		this.group = new Group();
		this.group.setId(group_id);
	}
	

	/**
	 * @return the status
	 */
	public Group getGroup() {
		return group;
	}
	
	
	
}
