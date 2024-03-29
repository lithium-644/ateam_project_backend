/**
 * 작성자: 김광원
 * 수정자: 김광원(2023-10-20)
 * 수정자: 장보늬(2023-10-23)
 * 수정자: 김태현(2023-10-27)
 * 수정자: 김태현(2023-10-31)
 * 작성일자: 2023-10-19
 * 내용: 일반회원 비밀번호 수정 및 닉네임 수정
 */
package com.ktdsuniversity.edu.member.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.common.vo.AbstractSearchVO;
import com.ktdsuniversity.edu.member.vo.MemberVO;
import com.ktdsuniversity.edu.member.vo.SocialVO;

@Repository
public class MemberDAOImpl extends SqlSessionDaoSupport implements MemberDAO{
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	/**
	 * 이메일 중복
	 */
	@Override
	public int getEmailCount(String email) {
		return getSqlSession().selectOne("getEmailCount",email);
	}
	/**
	 * 닉네임 중복
	 */
	@Override
	public int getNicknameCount(String nickname) {
		return getSqlSession().selectOne("getNicknameCount",nickname);
	}
	/**
	 * 회원생성
	 */
	@Override
	public int createNewMember(MemberVO memberVO) {
		return getSqlSession().insert("createNewMember",memberVO);
	}
	/**
	 * salt키
	 */
	@Override
	public String getSalt(String email) {
		return getSqlSession().selectOne("getSalt",email);
	}
	/**
	 *  일반 회원 조회
	 */
	@Override
	public MemberVO getGeneralMember(MemberVO memberVO) {
		return getSqlSession().selectOne("getGeneralMember",memberVO);
	}
	
	/**
	 *  기업 회원 조회
	 */
	@Override
	public MemberVO getCompanyMember(MemberVO memberVO) {
		return getSqlSession().selectOne("getCompanyMember",memberVO);
	}
	
	/**
	 * 프로필사진 조회
	 */
	@Override
	public MemberVO getOneFile(String email) {
		return getSqlSession().selectOne("getOneFile",email);
	}
	/**
	 * 파일수정
	 */
	@Override
	public int updateOneFile(MemberVO memberVO) {
		return getSqlSession().update("updateOneFile",memberVO );
	}
	
	/**
	 * 회원탈퇴
	 */
	@Override
	public int updateWithdrawMember(MemberVO memberVO) {
		return getSqlSession().update("updateWithdrawMember", memberVO);
	}
	
	/**
	 * 기업회원 비밀번호 수정
	 */
	@Override
	public int updateCompanyPassword(MemberVO memberVO) {
		return getSqlSession().update("updateCompanyPassword", memberVO);
	}
	
	/**
	 * 기업회원가입
	 */
//	@Override
//	public int createNewCompanyMember(MemberVO memberVO) {
//		return getSqlSession().insert("createNewCompanyMember", memberVO);
//	}
	
	/**
	 * 회원 조회
	 */
	@Override
	public MemberVO selectMemberinfo(String email) {
		return getSqlSession().selectOne("selectMemberinfo",email);
	}
	/**
	 * 회원 닉네임 수정
	 */
	@Override
	public int updateMemberNickname(MemberVO memberVO) {
		return getSqlSession().update("updateMemberNickname",memberVO);
	}
	/**
	 * 회원 비밀번호 수정
	 */
	@Override
	public int updateMemberPW(MemberVO memberVO) {
		return getSqlSession().update("updateMemberPW", memberVO);
	}
	/**
	 * 프로필사진 수정
	 */
	@Override
	public int createNewFile(MemberVO memberVO) {
		return getSqlSession().update("createNewFile", memberVO);
	}
	/**
	 * 프로필사진 삭제
	 */
	@Override
	public int deleteProfile(String email) {
		return getSqlSession().update("deleteProfile",email);
	}
	
	/**
	 * 관리를 위한 모든 일반 회원 목록 조회
	 */
	@Override
	public List<MemberVO> searchGemeralTypeMemberList() {
		return getSqlSession().selectList("searchGemeralTypeMemberList");
	}
	
	@Override
	public List<MemberVO> searchCompanyTypeMemberList() {
		return getSqlSession().selectList("searchCompanyTypeMemberList");
	}
	
	// 회원유형구분해서 회원찾기
	@Override
	public List<MemberVO> searchMember(String memberType) {
		return getSqlSession().selectList("searchMember", memberType);
	}
	
	// 통합검색
	@Override
	public List<MemberVO> searchAllMemberByKeyword(AbstractSearchVO abstractSearchVO) {
		return getSqlSession().selectList("searchAllMemberByKeyword", abstractSearchVO);
	}
	/**
	 * 카카오 회원가입
	 */
	@Override
	public int kakaoinsert(HashMap<String, Object> userInfo) {
		return getSqlSession().insert("kakaoinsert",userInfo);
	}
	
	@Override
	public int naverinsert(HashMap<String, Object> userInfo) {
		return getSqlSession().insert("naverinsert",userInfo);
	}
	@Override
	public int googleinsert(HashMap<String, Object> userInfo) {
		return getSqlSession().insert("googleinsert",userInfo);
	}
	@Override
	public SocialVO findSocial(HashMap<String, Object> userInfo) {
		return getSqlSession().selectOne("findSocial",userInfo);
	}
}
