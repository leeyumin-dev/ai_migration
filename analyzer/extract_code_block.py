import re

def extract_code_block(text: str, language="java"):
    """
    GPT 응답에서 ```java ... ``` 코드 블록만 추출하는 함수.
    설명이나 사족 없이 Java 코드만 깔끔히 추출 가능.

    Args:
        text (str): GPT 응답 전체 텍스트
        language (str): 코드 블록 언어 지정 (기본값: java)

    Returns:
        str: 코드 블록 내부 내용만 반환. 없으면 원문 그대로 반환.
    """
    pattern = rf"```{language}\n(.*?)```"
    match = re.search(pattern, text, re.DOTALL)
    return match.group(1).strip() if match else text
