docker-compose build
kubectl apply -f .\kubernetes\soa-namespace.yml
.\run-deployments.cmd & .\run-services.cmd & TIMEOUT /T 5 & kubectl port-forward svc/activemq 8161:8161 -n soa