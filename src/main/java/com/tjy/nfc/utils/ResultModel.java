package com.tjy.nfc.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultModel implements Serializable {
	private static final long serialVersionUID = -3948389268046368059L;

	private String retCode;

	private String retMsg;

	private Object body;

	public ResultModel() {}

	public ResultModel(String code, String msg) {
		this.retCode = code;
		this.retMsg = msg;
	}


	public static ResultModel success() {
		ResultModel Result = new ResultModel();
		Result.setRetCode(ResponseCode.SUCCESS);
		Result.setRetMsg("成功！");
		return Result;
	}

	public static ResultModel success(Object data) {
		ResultModel Result = new ResultModel();
		Result.setRetCode(ResponseCode.SUCCESS);
		Result.setRetMsg("成功！");
		Result.setBody(data);
		return Result;
	}

	public static ResultModel failure(String code, String msg) {
		ResultModel Result = new ResultModel();
		Result.setRetCode(code);
		Result.setRetMsg(msg);
		return Result;
	}

	public static ResultModel failure(String code, String msg, Object data) {
		ResultModel Result = new ResultModel();
		Result.setRetCode(code);
		Result.setRetMsg(msg);
		Result.setBody(data);
		return Result;
	}


}


