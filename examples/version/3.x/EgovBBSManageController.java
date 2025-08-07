package egovframework.com.cop.bbs.web;

import java.util.List;
import javax.annotation.Resource;

import egovframework.com.cop.bbs.service.BoardVO;
import egovframework.com.cop.bbs.service.EgovBBSManageService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EgovBBSManageController {

    /** EgovBBSManageService */
    @Resource(name = "EgovBBSManageService")
    private EgovBBSManageService bbsManageService;

    Logger log = Logger.getLogger(this.getClass());

    /**
     * 게시물 목록을 조회한다.
     * @param boardVO 게시판 정보
     * @param model 모델
     * @return "/cop/bbs/EgovNoticeList"
     * @throws Exception
     */
    @RequestMapping("/cop/bbs/selectBoardList.do")
    public String selectBoardArticles(BoardVO boardVO, ModelMap model) throws Exception {

        log.debug("▶ 게시판 목록 요청");

        List<EgovMap> resultList = bbsManageService.selectBoardArticles(boardVO);

        model.addAttribute("resultList", resultList);
        model.addAttribute("boardVO", boardVO);

        return "/cop/bbs/EgovNoticeList";
    }
}

