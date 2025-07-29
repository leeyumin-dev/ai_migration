class DependencyFilter:
    def __init__(self, whitelist=None):
        # 기본 허용된 전자정부/표준 라이브러리 목록
        self.whitelist = set(whitelist or [
            "os", "sys", "re", "datetime", "json", "math",
            "collections", "logging", "itertools", "typing",
            "flask", "django", "fastapi", "sqlalchemy"
        ])

    def filter_external(self, detected_imports):
        return sorted(set(detected_imports) - self.whitelist)