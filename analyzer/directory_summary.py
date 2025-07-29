from analyzer.directory_analyzer import DirectoryStructureAnalyzer
import pandas as pd
import json

def summarize_structure(jsonl_path: str):
    entries = []
    with open(jsonl_path, "r", encoding="utf-8") as f:
        for line in f:
            item = json.loads(line)
            path = item["path"]
            analyzer = DirectoryStructureAnalyzer(path)
            result = analyzer.analyze()
            entries.append(result)

    df = pd.DataFrame(entries)
    print(df.head())  # 예시 출력

    # 계층별 분포 확인
    print("\n계층별 파일 수")
    print(df["layer"].value_counts())

    return df

# 실행 예시
if __name__ == "__main__":
    summarize_structure("data/egov_code_segments.jsonl")
