class StructureMapper:
    def infer_role(self, func_info: dict):
        body = func_info["body"].lower()
        name = func_info["name"].lower()
        decorators = [d.lower() for d in func_info["decorators"]]

        # Controller 판단
        if any("route" in d or "router." in d for d in decorators):
            if "login" in name or "auth" in body:
                return "controller_login"
            return "controller"

        # DAO 판단 (SQL 처리)
        elif any(kw in body for kw in ["db.", "query", "read_sql", "read_sql_query", "execute", "session.query"]):
            return "dao"

        # 보안 관련 판단
        elif any(kw in body for kw in ["jwt", "session", "login_required"]):
            return "security"

        # 예외처리
        elif any(kw in body for kw in ["raise", "try:", "@app.errorhandler", "@exception_handler"]):
            return "exception_handler"

        # 파일 업로드
        elif any(kw in body for kw in ["request.files", "multipart", "file.stream", "file.read"]) or "upload" in name:
            return "file_upload"

        # 기본 서비스
        return "service"