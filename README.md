# How to run everything

0. Use powershell for all the commands

1. Start minikube and then start the dashboard
```
minikube start
minikube dashboard
```

2. Open a new tab and run
```
# Powershell
& minikube -p minikube docker-env | Invoke-Expression

# Bash
eval $(minikube -p minikube docker-env)
```

3. Run the run all command
```
.\run-all.cmd
```
In case of the error "unable to forward port because pod is not running. Current status=Pending". Run the following command again.
```
kubectl port-forward svc/activemq 8161:8161 -n soa
```

4. Open a new tab and run minikube tunnel
```
minikube tunnel
```