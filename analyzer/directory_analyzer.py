class DirectoryStructureAnalyzer:
    def __init__(self, file_path: str):
        self.file_path = file_path.replace("\\", "/")
        self.segments = self.file_path.split("/")

    def analyze(self):
        result = {
            "domain": self._extract_domain(),
            "feature": self._extract_feature(),
            "layer": self._extract_layer(),
            "file": self.segments[-1]
        }
        result["type"] = self._infer_type(result)
        return result

    def _extract_domain(self):
        try:
            return self.segments[5]  # 예: cop, sym, uss
        except IndexError:
            return "unknown"

    def _extract_feature(self):
        try:
            if self.segments[6].endswith(".java"):
                return "common"
            return self.segments[6]  # 예: bbs, login, etc
        except IndexError:
            return "common"

    def _extract_layer(self):
        path = self.file_path.lower()
        if "controller" in path or "web" in path:
            return "Controller"
        elif "serviceimpl" in path or "impl" in path:
            return "ServiceImpl"
        elif "service" in path:
            return "Service"
        elif "dao" in path:
            return "DAO"
        elif "vo" in path:
            return "VO"
        elif "mapper" in path:
            return "Mapper"
        elif "handler" in path or "hndlr" in path:
            return "Handler"
        else:
            return "Other"

    def _infer_type(self, info):
        return f"{info['domain']}.{info['feature']}.{info['layer']}"
