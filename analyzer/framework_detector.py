import ast

class FrameworkDetector(ast.NodeVisitor):
    def __init__(self, source_code: str):
        self.tree = ast.parse(source_code)
        self.imports = []

    def visit_Import(self, node):
        for alias in node.names:
            self.imports.append(alias.name.split('.')[0])

    def visit_ImportFrom(self, node):
        if node.module:
            self.imports.append(node.module.split('.')[0])

    def detect(self):
        self.visit(self.tree)
        return sorted(set(self.imports))  # 중복 제거 후 정렬
