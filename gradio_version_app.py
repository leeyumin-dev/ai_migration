#UI 띄우기 코드

import gradio as gr
from version_transform1 import convert_to_egov_4x

def handle_file_upload(file):
    with open(file.name, "r", encoding="utf-8") as f:
        input_code = f.read()

    output_code = convert_to_egov_4x(input_code)

    output_path = file.name.replace(".java", "_converted.java")
    with open(output_path, "w", encoding="utf-8") as f:
        f.write(output_code)

    return output_code, output_path

demo = gr.Interface(
    fn=handle_file_upload,
    inputs=gr.File(label="📁 3.x 자바 파일 업로드 (.java only)", file_types=[".java"]),
    outputs=[
        gr.Code(label="🔄 변환된 4.x 자바 코드"),
        gr.File(label="📥 변환 결과 파일 다운로드")
    ],
    title="eGovFrame 버전 변환기 (3.x → 4.x)",
    description="자바 파일을 업로드하면 전자정부프레임워크 4.x 스타일로 자동 변환합니다."
)

if __name__ == "__main__":
    demo.launch()
