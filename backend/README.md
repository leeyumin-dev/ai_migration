# 

## Model
www.msaez.io/#/97872005/storming/c24f2265785538c49a590f2385c32326

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- user
-  noticeboard
- agent
- admin
- platform


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- user
```
 http :8088/users id="id"email="email"password="password"nickname="nickname"
```
-  noticeboard
```
 http :8088/posts postId="postId"title="title"content="content"createdAt="createdAt"updatedAt="updatedAt"createdBy="createdBy"
 http :8088/postQueries postId="postId"title="title"viewCount="viewCount"createdAt="createdAt"
```
- agent
```
 http :8088/agents id="id"userId="userId"filePath="filePath"inputeGovFrameVer="inputeGovFrameVer"outputeGovFrameVer="outputeGovFrameVer"isTestCode="isTestCode"conversionType="conversionType"query="query"
```
- admin
```
 http :8088/tokens id="id"userId="userId"apiKey="apiKey"active="active"createdAt="createdAt"
```
- platform
```
 http :8088/conversions id="id"
 http :8088/testLogs id="id"
 http :8088/conversionLogs id="id"
 http :8088/securities id="id"
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
