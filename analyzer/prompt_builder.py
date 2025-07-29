class PromptBuilder:
    def build_prompt(self, func_info: dict, framework_version: str = "egov3.10"):
        role = func_info["role"]
        code = func_info["body"]

        return f"""아래는 Python으로 작성된 {role.upper()} 역할의 함수입니다:

```python
{code}
```

이 코드를 Java {role.upper()} 역할의 코드로 변환해줘.
전자정부프레임워크 {framework_version} 구조를 반영해서 작성하되,
**절대 설명하지 말고, 코드만 출력해줘.**

**다른 말 없이 바로 아래처럼 Java 코드 블록으로 시작해줘.**

```java
"""