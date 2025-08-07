package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.egovframe.rte.fdl.cryptography.EgovEnvCryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovResourceCloseHelper;

/**
 * EgovImageProcessController.java
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 * 
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.02  이삼섭          최초생성
 *   2014.03.31  유지보수         fileSn 오류수정
 *   2018.08.31  이정은          MimeType 중복설정 제거
 *   2019.11.29  신용호          KISA 보안약점 조치 : HTTP응답분할(HTTP_Response_Splitting,CRLF)취약점 조치
 *   2025.06.03  이백행          PMD로 소프트웨어 보안약점 진단하고 제거하기-LocalVariableNamingConventions(지역 변수 명명 규칙), CloseResource(리소스 닫기), AssignmentInOperand(피연산자의 할당)
 *
 *      </pre>
 */
@SuppressWarnings("serial")
@Controller
public class EgovImageProcessController extends HttpServlet {

	/** 암호화서비스 */
	@Resource(name = "egovEnvCryptoService")
	EgovEnvCryptoService cryptoService;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovImageProcessController.class);

	/**
	 * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
	 *
	 * @param atchFileId
	 * @param fileSn
	 * @param sessionVO
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/cmm/fms/getImage.do")
	public void getImageInf(SessionVO sessionVO, ModelMap model, @RequestParam Map<String, Object> commandMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 암호화된 atchFileId 를 복호화하고 동일한 세션인 경우만 다운로드할 수 있다. (2022.12.06 추가) - 파일아이디가 유추
		// 불가능하도록 조치
		String param_atchFileId = (String) commandMap.get("atchFileId");
		param_atchFileId = param_atchFileId.replaceAll(" ", "+");
		byte[] decodedBytes = Base64.getDecoder().decode(param_atchFileId);
		String decodedString = cryptoService.decrypt(new String(decodedBytes));
		String decodedSessionId = StringUtils.substringBefore(decodedString, "|");
		String decodedFileId = StringUtils.substringAfter(decodedString, "|");

		String fileSn = (String) commandMap.get("fileSn");

		String sessionId = request.getSession().getId();

		boolean isSameSessionId = StringUtils.equals(decodedSessionId, sessionId);

		if (!isSameSessionId) {
			throw new Exception();
		}

		FileVO vo = new FileVO();

		vo.setAtchFileId(decodedFileId);
		vo.setFileSn(fileSn);

		// ------------------------------------------------------------
		// fileSn이 없는 경우 마지막 파일 참조
		// ------------------------------------------------------------
		if (fileSn == null || fileSn.equals("")) {
			int newMaxFileSN = fileService.getMaxFileSN(vo);
			vo.setFileSn(Integer.toString(newMaxFileSN - 1));
		}
		// ------------------------------------------------------------

		FileVO fvo = fileService.selectFileInf(vo);

		// String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

		String fileStreCours = EgovWebUtil.filePathBlackList(fvo.getFileStreCours());
		String streFileNm = EgovWebUtil.filePathBlackList(fvo.getStreFileNm());
		File file = new File(fileStreCours, streFileNm);

		ByteArrayOutputStream bStream = null;

		try (FileInputStream fis = new FileInputStream(file); BufferedInputStream in = new BufferedInputStream(fis);) {
			bStream = new ByteArrayOutputStream();

			FileCopyUtils.copy(in, bStream);

			String type = "";

			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
				if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
					type = "image/jpeg";
				} else {
					type = "image/" + fvo.getFileExtsn().toLowerCase();
				}
				/* type = "image/" + fvo.getFileExtsn().toLowerCase(); */

			} else {
				LOGGER.debug("Image fileType is null.");
			}

			response.setHeader("Content-Type", EgovWebUtil.removeCRLF(type));
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

		} finally {
			EgovResourceCloseHelper.close(bStream);
		}
	}
}