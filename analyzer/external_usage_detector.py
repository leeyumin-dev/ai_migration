import ast

class ExternalUsageDetector(ast.NodeVisitor):
    def __init__(self, source_code: str):
        self.tree = ast.parse(source_code)
        self.external_calls = set()

    def visit_Call(self, node):
        func_name = self.get_full_name(node.func)
        if func_name:
            self.check_external(func_name)
        self.generic_visit(node)

    def get_full_name(self, node):
        if isinstance(node, ast.Name):
            return node.id
        elif isinstance(node, ast.Attribute):
            value = self.get_full_name(node.value)
            return f"{value}.{node.attr}" if value else None
        return None

    def check_external(self, name):
        external_keywords = [
            "requests.", "httpx.", "openai.", "socket.",
            "urllib.", "http.", "smtplib.", "boto3.", "session.",
            "read_sql", "query", "fetch", "connect", "cursor", "engine"
        ]
        if any(keyword in name.lower() for keyword in external_keywords):
            self.external_calls.add(name)

    def detect(self):
        self.visit(self.tree)
        return sorted(self.external_calls)
