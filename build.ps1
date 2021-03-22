minikube docker-env | Invoke-Expression
docker build -t emergency ./emergency-service
docker build -t schedule ./schedule-service
docker build -t ship ./ship-service
docker build -t headquarter ./headquarter-ui