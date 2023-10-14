package com.ktdsuniversity.edu.common.vo;

import java.util.List;

public class CommonListVO {
	private int allCommonNumber;
	private List<CommonVO> commonList;
	private int hashtagCnt;
	private List<CommonVO> hashtagList;
	
	
	public int getAllCommonNumber() {
		return allCommonNumber;
	}
	public void setAllCommonNumber(int allCommonNumber) {
		this.allCommonNumber = allCommonNumber;
	}
	public List<CommonVO> getCommonList() {
		return commonList;
	}
	public void setCommonListVO(List<CommonVO> commonList) {
		this.commonList = commonList;
	}
	public int getHashtagCnt() {
		return hashtagCnt;
	}
	public void setHashtagCnt(int hashtagCnt) {
		this.hashtagCnt = hashtagCnt;
	}
	public List<CommonVO> getHashtagList() {
		return hashtagList;
	}
	public void setHashtagList(List<CommonVO> hashtagList) {
		this.hashtagList = hashtagList;
	}
	
}