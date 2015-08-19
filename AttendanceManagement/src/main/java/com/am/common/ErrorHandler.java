
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
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.INVALID_FORMAT.getCode()){
			modelMap.addAttribute(Constant.RESPONSE,  ResponseCode.INVALID_FORMAT.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.EMAIL_EXIST.getCode()){
			modelMap.addAttribute(Constant.RESPONSE,ResponseCode.EMAIL_EXIST.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.TECHNICAL_ERROR.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseCode.TECHNICAL_ERROR.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.RECORD_NOT_EXIST.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseCode.RECORD_NOT_EXIST.getCode());
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}
		
	}
	
	
/*	public static void HandleErrorMessage(HttpServletResponse response, BaseResponse baseResponse,  ModelMap modelMap){
		if(baseResponse.getResponseCode() == ResponseCode.REQUIRED_FIELD.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.REQUIRED_FIELD);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.INVALID_FORMAT.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.INVALID_FORMAT);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.EMAIL_EXIST.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.EMAIL_EXIST);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.TECHNICAL_ERROR.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.TECHNICAL_ERROR);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
		}else if(baseResponse.getResponseCode() == ResponseCode.SEARCH_RESULT.getCode()){
			modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.SEARCH_RESULT);
			response.addCookie(new Cookie(Constant.NOTIFICATION,Constant.DIV_ERROR));
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
					else if(request.getParameter(Constant.RESPONSE).toString().equals(String.valueOf(ResponseCode.RECORD_NOT_EXIST.getCode())))
						modelMap.addAttribute(Constant.RESPONSE, ResponseMessage.RECORD_NOT_EXIST);
					else if(request.getParameter("HIDE_IMPORT").toString().equals("YES")){
						modelMap.addAttribute(Constant.RESPONSE, "SUCCESSFULLY UPLOADED FILE IN OUR DATABASE! You can now check attendees information on Attendees tab.");
					}
				}
			}
			
		}
	}
	
	
}