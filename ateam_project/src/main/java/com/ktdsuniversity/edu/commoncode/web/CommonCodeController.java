/**
 * 작성자: 김태현
 * 수정자: 장보늬(2023-10-17)
 * 		 김태현(2023-10-17)
 * 작성일자: 2023-10-11
 * 내용: 공통 코드 테이블의 조회 화면을 보여주고 처리하는 클래스입니다.
 */

package com.ktdsuniversity.edu.commoncode.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.edu.commoncode.service.CommonCodeService;
import com.ktdsuniversity.edu.commoncode.vo.CommonCodeListVO;
import com.ktdsuniversity.edu.commoncode.vo.CommonCodeVO;

@Controller
public class CommonCodeController {
	@Autowired
	private CommonCodeService commonCodeService;
	
	@GetMapping("/code/{codeName}")
	@ResponseBody
	public List<CommonCodeListVO> searchCode(@PathVariable String codeName) {
		return commonCodeService.search(codeName);
	}
	
	@GetMapping("/home/hashtaglist")
	public ModelAndView viewHashtagList() {
		ModelAndView mav = new ModelAndView();
		CommonCodeListVO commonCodeListVO = commonCodeService.getAllCommonType("해시태그");
		mav.setViewName("temp/hashtagselect");
		mav.addObject("commonCodeListVO", commonCodeListVO);
		
		return mav;
	}
	
	@PostMapping("/home/hashtaglist")
	public ModelAndView createCommonCode(@ModelAttribute CommonCodeVO commonCodeVO) {
		ModelAndView mav = new ModelAndView();
		CommonCodeListVO commonCodeListVO = commonCodeService.getAllCommonType("해시태그");
		List<CommonCodeVO> originCommonType = commonCodeListVO.getCommonCodeList();
		
		String[] commonTypeArr = commonCodeVO.getCodeContent().split(" ");
		
		// 중복된 값이 제거된 타입 셋 생성
		Set<String> commonTypeSet = new HashSet<>();
		for (String str : commonTypeArr) {
			commonTypeSet.add(str);
		}
		
		int count = 0;
		boolean isExist = false;
		
		for (String item : commonTypeSet) {
			for (CommonCodeVO originhashtag : originCommonType) {
				if (item.equals(originhashtag.getCodeContent())) {
					count++;
					isExist = true;
					break;
				}
			}
			if (!isExist) {
				commonCodeVO.setCodeContent(item);
				boolean isSuccess = commonCodeService.createCommonCode(commonCodeVO);
				
				if (isSuccess) {
					count++;
				}
			}
			isExist = false;
		}
		
		if (count == commonTypeSet.size()) {
			mav.setViewName("redirect:/home/hashtaglist");
			return mav;
		}
		else {
			mav.setViewName("/temp/errorpage");
			return mav;
		}
		
	}
}
