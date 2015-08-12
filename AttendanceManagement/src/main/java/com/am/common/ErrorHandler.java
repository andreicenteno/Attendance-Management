
package com.am.common;


import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

public class ErrorHandler{
	
	public static void HandleErrorMessageRedirect(HttpServletResponse response, BaseResponse baseResponse,  ModelMap modelMap){
		if(baseResponse.getResponseCode() == ResponseCode.REQUIRED_FIELD.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseCode.REQUIRED_FIELD.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.INVALID_FORMAT.getCode()){
			modelMap.addAttribute(Constant.RESPONSE,  ResponseCode.INVALID_FORMAT.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.EMAIL_EXIST.getCode()){
			modelMap.addAttribute(Constant.RESPONSE,ResponseCode.EMAIL_EXIST.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.TECHNICAL_ERROR.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseCode.TECHNICAL_ERROR.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}
	}
	
	
	/*public static void HandleErrorMessage(HttpServletResponse response, BaseResponse baseResponse,  ModelMap modelMap){
		if(baseResponse.getResponseCode() == ResponseCode.REQUIRED_FIELD.getCode()){
			modelMap.addAttribute(Constant.ERROR, ResponseMessage.REQUIRED_FIELD);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.INVALID_FORMAT.getCode()){
			modelMap.addAttribute(Constant.ERROR, ResponseMessage.INVALID_FORMAT);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.EMAIL_EXIST.getCode()){
			modelMap.addAttribute(Constant.ERROR, ResponseMessage.EMAIL_EXIST);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.TECHNICAL_ERROR.getCode()){
			modelMap.addAttribute(Constant.ERROR, ResponseMessage.TECHNICAL_ERROR);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.MAINTENANCE_ERROR));
		}
	}*/
	
	
	public static void setupModelMapResponseMessage(ModelMap modelMap, HttpServletRequest request){
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String parameterName = (String) enumeration.nextElement();
			if(Constant.RESPONSE.equals(parameterName)){
				if(request.getParameter(Constant.RESPONSE).toString().equals(String.valueOf(ResponseCode.SUCCESSFUL.getCode()))){
					modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.SAVED);
				}else{
					if(request.getParameter(Constant.RESPONSE).toString().equals(String.valueOf(ResponseCode.REQUIRED_FIELD.getCode())))
						modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.REQUIRED_FIELD);
					else if(request.getParameter(Constant.RESPONSE).toString().equals(String.valueOf(ResponseCode.INVALID_FORMAT.getCode())))
						modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.INVALID_FORMAT);
					else if(request.getParameter(Constant.RESPONSE).toString().equals(String.valueOf(ResponseCode.EMAIL_EXIST.getCode())))
						modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.EMAIL_EXIST);
					else if(request.getParameter(Constant.RESPONSE).toString().equals(String.valueOf(ResponseCode.TECHNICAL_ERROR.getCode())))
						modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.TECHNICAL_ERROR);
				}
			}
			
		}
	}
	
	
}