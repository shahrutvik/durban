package com.carpool.db;

import com.carpool.db.retryable.GroupDB;
import com.carpool.vo.GroupVO;

public class GroupDAO {
	
	GroupDB groupVirtual;

	public GroupDAO(){
		groupVirtual = new GroupDB();
	}
	
	public void insertGroupRecord(GroupVO groupVO){
		if(!isGroupNickNameInDB(groupVO)){
			groupVirtual.insert(groupVO);
		}
		else
		{
			System.out.println(groupVO.getNickname()+" already exists.Please use another nickname for your group");
		}
	}
	
	private boolean isGroupNickNameInDB(GroupVO groupVO) {
		return groupVirtual.isGroupNickNameInDB(groupVO);
	}

	/*protected boolean groupExistInDB(int groupId){
		return groupVirtual.isGroupInDB(groupId);
	}*/
	
	public int getGroupId(String nickname){
		return groupVirtual.getIdForNickname(nickname);
	}
}
