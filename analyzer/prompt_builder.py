class PromptBuilder:
    def build_prompt(self, func_info: dict):
        role = func_info["role"]
        code = func_info["body"]
        return f"""
다음 Python 코드를 Java {role.upper()}로 변환해줘.
Spring Boot + eGovFrame 구조로 생성해줘.


--- Python ---
{code}
"""


