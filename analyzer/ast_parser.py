import ast

class ASTAnalyzer:
    def __init__(self, source_code: str):
        self.tree = ast.parse(source_code)

    def extract_functions(self):
        results = []
        for node in ast.walk(self.tree):
            if isinstance(node, ast.FunctionDef):
                result = {
                    "name": node.name,
                    "args": [arg.arg for arg in node.args.args],
                    "decorators": [ast.unparse(d) for d in node.decorator_list],
                    "docstring": ast.get_docstring(node),
                    "body": ast.unparse(node),
                }
                results.append(result)
        return results
