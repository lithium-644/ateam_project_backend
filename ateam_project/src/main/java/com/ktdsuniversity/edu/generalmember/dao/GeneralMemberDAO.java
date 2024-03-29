/**
 * 작성자 : 김광원
 * 작성일자 : 2023-10-24
 * 수정일자 : 2023-10-25 수정자(김광원)
 * 내용 : 일반회원을 위한 DAO입니다.
 */
package com.ktdsuniversity.edu.generalmember.dao;

import java.util.List;
import java.util.Map;

import com.ktdsuniversity.edu.career.vo.CareerVO;
import com.ktdsuniversity.edu.commoncode.vo.CommonCodeVO;
import com.ktdsuniversity.edu.education.vo.EducationVO;
import com.ktdsuniversity.edu.generalmember.vo.GeneralMemberListVO;
import com.ktdsuniversity.edu.generalmember.vo.GeneralMemberVO;
import com.ktdsuniversity.edu.member.vo.MemberVO;

public interface GeneralMemberDAO {
	
	public int createNewGeneralMember(GeneralMemberVO generalMemberVO);

	/**
	 * 경력 전체 조회
	 */
	public List<CareerVO> getAllCareerListByMemberEmail(String generalMemberEmail);

	/**
	 * 일반회원 전체 조회
	 */
	public List<GeneralMemberVO> getAllGeeralMemberList(String generalMemberEmail);

	/**
	 * 학력 전체 조회
	 */
	public List<EducationVO> getAllEducationList(String generalMemberEmail);

	/**
	 * 일반회원 조회
	 */
	public MemberVO getSelectNickname(String generalMemberEmail);
	/**
	 * 공통코드 조회
	 */
	public List<CommonCodeVO> getSelectCommonCode(String generalMemberEmail);
	/**
	 * 일반회원 컬럼 조회
	 */
	public GeneralMemberVO getSelectGeneralMember(String generalMemberEmail);
	
	/**
	 * 주소 수정
	 */
	public int updateOneAddress(GeneralMemberVO generalMemberVO);

	/**
	 * 주소 삭제
	 */
	public int deleteOneAddress(String generalMemberEmail);

	/**
	 * SNS 수정
	 */
	public int updateSns(GeneralMemberVO generalMemberVO);

	/**
	 * github 삭제
	 */
	public int deleteGithub(String generalMemberEmail);

	/**
	 * email 삭제
	 */
	public int deleteEmail(String generalMemberEmail);

	/**
	 * blog 삭제
	 */
	public int deleteBlog(String generalMemberEmail);

	/**
	 * 소개글 수정
	 */
	public int updateselfIntro(GeneralMemberVO generalMemberVO);

	/**
	 * blog 삭제
	 */
	public int deleteSelfIntro(String generalMemberEmail);
	
	/**
	 * 티어 승급한 회원의 TierId 변경
	 * @param generalMemberEmail 티어 승급한 회원 email
	 * @return
	 */
	public int updateTierId(GeneralMemberVO generalMemberVO);
	
	/**
	 * 티어 승급한 회원의 TierDate 변경
	 * @param generalMemberEmail 티어 승급한 회원 email
	 * @return
	 */
	public int updateTierDate(GeneralMemberVO generalMemberVO);
	
	/**
	 * 회원이 작성한 글과 댓글 조회
	 * @param generalMemberEmail 활동 내역을 조회할 회원 email
	 * @return
	 */
	public List<Map<String, Object>> selectMemberActivityLog(String generalMemberEmail);
	
	/**
	 * 스코어 상위 10위까지의 회원 목록 조회
	 * @return
	 */
	public List<GeneralMemberVO> selectTopTenScoreMemberList();
}
