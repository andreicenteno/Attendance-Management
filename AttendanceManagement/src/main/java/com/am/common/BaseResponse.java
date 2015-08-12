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

/**
 * @author andreicenteno
 *
 */
public class BaseResponse {
	private int responseCode;
	private String responseMessage;
	/**
	 * @return the responseCode
	 */
	public final int getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public final void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseMessage
	 */
	public final String getResponseMessage() {
		return responseMessage;
	}
	/**
	 * @param responseMessage the responseMessage to set
	 */
	public final void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseResponse [responseCode=");
		builder.append(responseCode);
		builder.append(", responseMessage=");
		builder.append(responseMessage);
		builder.append("]");
		return builder.toString();
	}
	
	
}
