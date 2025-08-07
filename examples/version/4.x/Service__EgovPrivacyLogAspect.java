package egovframework.com.sym.log.plg.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovHttpRequestHelper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

/**
 * 개인정보 조회 이력 관리를 위한 Advice
 * 
 * @author Vincent Han
 * @since 2014.09.11
 * @version 3.5
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  홍길동          최초 생성
 *   2014.09.11  표준프레임워크     최초 생성
 *   2025.07.12  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-AssignmentInOperand(피연산자내에 할당문이 사용됨. 해당 코드를 복잡하고 가독성이 떨어지게 만듬)
 *   2025.07.12  이백행          2025년 컨트리뷰션 PMD로 소프트웨어 보안약점 진단하고 제거하기-UnnecessaryBoxing(불필요한 WrapperObject 생성)
 *
 *      </pre>
 */
public class EgovPrivacyLogAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovPrivacyLogAspect.class);

	/** List 기록 시 최대 기록 수 */
	private int maxListCount = 10; // defalut : 10

	/** 기록 대상 개인정보 항목 */
	private Map<String, String> target = null;

	public void setMaxListCount(int maxListCount) {
		this.maxListCount = maxListCount;
	}

	public void setTarget(Map<String, String> target) {
		this.target = target;
	}

	@Resource(name = "egovPrivacyLogService")
	private EgovPrivacyLogService privacyLogService;

	public void insertLog(JoinPoint joinPoint, Object returnVal) throws Throwable {

		String className = joinPoint.getTarget().getClass().getCanonicalName();
		String methodName = joinPoint.getSignature().getName();

		String serviceName = className + "." + methodName;

		if (!EgovHttpRequestHelper.isInHttpRequest()) {
			LOGGER.info("{} service called, but it isn't in HTTP request...", serviceName);
			return;
		}

		if (returnVal instanceof List) { // List이며 개별 기록
			int count = 0;

			for (Object item : (List<?>) returnVal) {
				List<String> list = null;
				if (item instanceof Map) {
					list = getItemValues((Map<?, ?>) item, serviceName);
				} else { // general VO
					list = getItemValues(item, serviceName);
				}

				if (list.size() > 0) {
					privacyLogService.innerInsertPrivacyLog(getPrivacyLogFromItemList(list, serviceName));

					++count;

					if (count >= maxListCount) { // 최대 기록 수 처리
						LOGGER.info("Max List count reached (skip next list) : maxListCount = {}, target = {}",
								maxListCount, serviceName);
						break;
					}
				}
			}
		} else if (returnVal instanceof Map) {
			List<String> list = getItemValues((Map<?, ?>) returnVal, serviceName);

			if (list.size() > 0) {
				privacyLogService.innerInsertPrivacyLog(getPrivacyLogFromItemList(list, serviceName));
			}
		} else { // general VO
			List<String> list = getItemValues(returnVal, serviceName);

			if (list.size() > 0) {
				privacyLogService.innerInsertPrivacyLog(getPrivacyLogFromItemList(list, serviceName));
			}
		}

	}

	protected List<String> getItemValues(Map<?, ?> data, String serviceName) {
		List<String> list = new ArrayList<String>();

		for (String key : target.keySet()) {
			if (data.containsKey(key) && data.get(key) != null && !data.get(key).toString().trim().equals("")) { // 조회된
																													// 데이터가
																													// 없으면
																													// 생략
				list.add(target.get(key));

				LOGGER.debug("Service ('{}') : inquired data = {}", serviceName, key);
			}
		}

		return list;
	}

	protected List<String> getItemValues(Object data, String serviceName) {
		List<String> list = new ArrayList<String>();

		for (String key : target.keySet()) {

			try {
				Method method = data.getClass().getMethod("get" + key.substring(0, 1).toUpperCase() + key.substring(1));

				Object returned = method.invoke(data);

				if (returned != null && !returned.toString().trim().equals("")) {
					list.add(target.get(key));
				}
			} catch (NoSuchMethodException ignore) {
				LOGGER.error("[" + ignore.getClass() + "] Try/Catch... : " + ignore.getMessage());
				continue;
			} catch (IllegalAccessException ignore) {
				LOGGER.error("[" + ignore.getClass() + "] Try/Catch... : " + ignore.getMessage());
				continue;
			} catch (IllegalArgumentException ignore) {
				LOGGER.error("[" + ignore.getClass() + "] Try/Catch... : " + ignore.getMessage());
				continue;
			} catch (InvocationTargetException ignore) {
				LOGGER.error("[" + ignore.getClass() + "] Try/Catch... : " + ignore.getMessage());
				continue;
			} catch (NullPointerException ignore) {
				LOGGER.error("[" + ignore.getClass() + "] Try/Catch... : " + ignore.getMessage());
				continue;
			}

			LOGGER.debug("Service ('{}') : inquired data = {}", serviceName, key);
		}

		return list;
	}

	private PrivacyLog getPrivacyLogFromItemList(List<String> list, String serviceName) {
		PrivacyLog log = new PrivacyLog();

		log.setServiceName(serviceName);
		log.setInquiryInfo(getStringFromItemList(list));

		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (loginVO != null) {
			log.setRequesterId(loginVO.getUniqId());
		}

		log.setRequesterIp(EgovHttpRequestHelper.getRequestIp());

		return log;
	}

	private String getStringFromItemList(List<String> list) {
		StringBuffer buffer = new StringBuffer();

		for (String item : list) {
			if (buffer.length() != 0) {
				buffer.append(",").append(item);
			} else {
				buffer.append(item);
			}
		}
		return buffer.toString();
	}
}