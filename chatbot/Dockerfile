FROM python:latest


COPY requirements.txt .
RUN pip install -r requirements.txt
# RUN pip install --no-cache-dir -r requirements.txt

COPY . .
WORKDIR /app

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8003"]
