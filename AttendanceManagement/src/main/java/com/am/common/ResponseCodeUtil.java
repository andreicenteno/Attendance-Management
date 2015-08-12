/*******************************************************************************
 * Copyright (c) 2013 P3ople4u Inc.  All Rights Reserved.
 *        DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *        
 *        This code is NOT free software; you cannot redistribute, modify, 
 *        decompile, copy or publish without the prior written consent of 
 *        P3ople4u Inc.
 *        
 *        
 *        This software is the confidential and proprietary information
 *        of P3ople4u Inc. ("Confidential Information").  You shall 
 *        not disclose such Confidential Information and shall use it 
 *        only in accordance with the terms of the license agreement
 *        you entered into with P3ople4u.
 *        
 *        Please contact P3ople4u Inc. 17th Floor PhilamLife Tower, 
 *        8767 Paseo de Roxas Avenue,Makati City, Philippines 
 *        if you need additional information or have any questions.
 ******************************************************************************/
package com.am.common;

import com.am.common.BaseResponse;
import com.am.common.ResponseCode;



/**
 * @author emersonsalvador
 *
 */
public final class ResponseCodeUtil {

	/**
	 * 
	 */
	private ResponseCodeUtil() {
		
	}
	
	public static void setResponseCode(BaseResponse baseResponse, ResponseCode responseCode, Throwable throwable){
		baseResponse.setResponseCode(responseCode.getCode());
		
		if(throwable != null){
			baseResponse.setResponseMessage(responseCode.getMessage(throwable.getMessage()));
		}else{
			baseResponse.setResponseMessage(responseCode.getMessage());
		}
	}
	
	public static void setResponseCode(BaseResponse baseResponse, ResponseCode responseCode){
		setResponseCode(baseResponse, responseCode, null);
	}

}
