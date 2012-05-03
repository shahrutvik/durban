package com.carpool.db;

import com.carpool.db.retryable.TokenDB;
import com.carpool.vo.TokenVO;

public class TokenDAO {
	
	TokenDB tokenVirtual;
	
	public TokenDAO(){
		tokenVirtual = new TokenDB();
	}

	public void insertTokenRecord(TokenVO tokenVO) {
		tokenVirtual.insert(tokenVO);
		
	}

}
