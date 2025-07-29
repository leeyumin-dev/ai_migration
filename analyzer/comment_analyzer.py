import ast
import re

class CommentAnalyzer(ast.NodeVisitor):
    def __init__(self, source_code: str):
        self.tree = ast.parse(source_code)
        self.comments = []
        self.source_lines = source_code.splitlines()

    def visit_FunctionDef(self, node):
        # 함수 정의 위의 인라인 주석 탐색
        start_line = node.lineno - 2  # 한 줄 위부터 위로 올라가며 주석 찾음
        while start_line >= 0 and self.source_lines[start_line].strip().startswith("#"):
            self.comments.append({
                "function": node.name,
                "comment": self.source_lines[start_line].strip("# ").strip()
            })
            start_line -= 1
        # 함수 내부 docstring도 함께 분석
        docstring = ast.get_docstring(node)
        if docstring:
            self.comments.append({
                "function": node.name,
                "comment": docstring.strip()
            })

    def detect(self):
        self.visit(self.tree)
        return self.comments
