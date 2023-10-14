package com.ktdsuniversity.edu.generalpost.service;

import java.util.List;

import com.ktdsuniversity.edu.generalpost.vo.GeneralCommentVO;

public interface GeneralCommentService {

	public List<GeneralCommentVO> getAllComments(String generalPostId);
	public boolean createNewComment(GeneralCommentVO generalCommentVO);
	public boolean deleteOneComment(String generalCommentId,String commentWriterId);
	public boolean modifyOneComment(GeneralCommentVO generalCommentVO);
	public boolean recommendOneComment(String generalCommentId, String commentWriterId);
	public boolean reportOneComment(String generalCommentId, String commentWriterId);
}