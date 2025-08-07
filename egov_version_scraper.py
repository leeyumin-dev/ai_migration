import os, requests, json
# 4.x 신버전 자바 파일들 싹 끌어모으기

# 📌 GitHub 저장소 정보
OWNER = "eGovFramework"
REPO = "egovframe-common-components"
BRANCH = "main"
TARGET_DIR = "src/main/java"
RAW_BASE = f"https://raw.githubusercontent.com/{OWNER}/{REPO}/{BRANCH}"

# 📁 저장 디렉토리
OUT_DIR = "examples/version/4.x"  # 최신 버전 기준
os.makedirs(OUT_DIR, exist_ok=True)

# ✅ 경로 해석 (Controller, Service, DAO 분류)
def classify_path(path):
    if "controller" in path.lower() or "web" in path.lower():
        return "Controller"
    elif "serviceimpl" in path.lower() or "impl" in path.lower():
        return "ServiceImpl"
    elif "service" in path.lower():
        return "Service"
    elif "dao" in path.lower():
        return "DAO"
    elif "vo" in path.lower():
        return "VO"
    elif "mapper" in path.lower():
        return "Mapper"
    else:
        return "Other"

# ✅ GitHub API로 전체 트리 가져오기
def fetch_java_files():
    url = f"https://api.github.com/repos/{OWNER}/{REPO}/git/trees/{BRANCH}?recursive=1"
    
    # GitHub Personal Access Token 사용 (환경 변수에서 로드)
    HEADERS = {"Authorization": f"token {os.getenv('GITHUB_TOKEN')}"}
    
    res = requests.get(url, headers=HEADERS)
    res.raise_for_status()
    files = res.json()["tree"]
    return [f["path"] for f in files if f["path"].startswith(TARGET_DIR) and f["path"].endswith(".java")]


# ✅ 자바 코드 다운로드 및 저장
def download_java_files():
    java_files = fetch_java_files()
    count = 0
    for path in java_files:
        file_type = classify_path(path)
        filename = os.path.basename(path)
        save_path = os.path.join(OUT_DIR, f"{file_type}__{filename}")

        raw_url = f"{RAW_BASE}/{path}"
        try:
            content = requests.get(raw_url).text.strip()
            if len(content) < 50:  # 너무 짧은 파일은 제외
                continue
            with open(save_path, "w", encoding="utf-8") as f:
                f.write(content)
            count += 1
        except Exception as e:
            print(f"⚠️ 실패: {raw_url} - {e}")

    print(f"✅ 다운로드 완료: {count}개 파일 저장됨 → {OUT_DIR}")

if __name__ == "__main__":
    download_java_files()
