package com.ktdsuniversity.edu.report.vo;

import com.ktdsuniversity.edu.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.generalpost.vo.GeneralCommentVO;
import com.ktdsuniversity.edu.generalpost.vo.GeneralPostVO;
import com.ktdsuniversity.edu.member.vo.MemberVO;

public class ReportVO {
	
	private String reportTypeId;
	private String reportReasonContent;
	private String reportReason;
	private String reportMember;
	private String reportId;
	private String reportDate;
	private String reportContentId;
	private String receivedReportMember;
	private String progressYn;
	private String attachedImg;
	
	private MemberVO memberVO;
	
	private CommonCodeVO commonCodeVO;
	
	private GeneralPostVO generalPostVO;
	
	private GeneralCommentVO generalCommentVO;
	
	
	public String getReportTypeId() {
		return reportTypeId;
	}
	public void setReportTypeId(String reportTypeId) {
		this.reportTypeId = reportTypeId;
	}
	public String getReportReasonContent() {
		return reportReasonContent;
	}
	public void setReportReasonContent(String reportReasonContent) {
		this.reportReasonContent = reportReasonContent;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public String getReportMember() {
		return reportMember;
	}
	public void setReportMember(String reportMember) {
		this.reportMember = reportMember;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportContentId() {
		return reportContentId;
	}
	public void setReportContentId(String reportContentId) {
		this.reportContentId = reportContentId;
	}
	public String getReceivedReportMember() {
		return receivedReportMember;
	}
	public void setReceivedReportMember(String receivedReportMember) {
		this.receivedReportMember = receivedReportMember;
	}
	public String getProgressYn() {
		return progressYn;
	}
	public void setProgressYn(String progressYn) {
		this.progressYn = progressYn;
	}
	public String getAttachedImg() {
		return attachedImg;
	}
	public void setAttachedImg(String attachedImg) {
		this.attachedImg = attachedImg;
	}
	public MemberVO getMemberVO() {
		return memberVO;
	}
	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	public CommonCodeVO getCommonCodeVO() {
		return commonCodeVO;
	}
	public void setCommonCodeVO(CommonCodeVO commonCodeVO) {
		this.commonCodeVO = commonCodeVO;
	}
	public GeneralPostVO getGeneralPostVO() {
		return generalPostVO;
	}
	public void setGeneralPostVO(GeneralPostVO generalPostVO) {
		this.generalPostVO = generalPostVO;
	}
	public GeneralCommentVO getGeneralCommentVO() {
		return generalCommentVO;
	}
	public void setGeneralCommentVO(GeneralCommentVO generalCommentVO) {
		this.generalCommentVO = generalCommentVO;
	}
	
}
