# How to run everything

1. Start minikube and then start the dashboard
```
minikube start
minikube dashboard
```

2. Open a new tab and run
```
& minikube -p minikube docker-env | Invoke-Expression
```

3. Run the run all command
```
.\run-all.cmd
```

4. Open a new tab and run minikube tunnel
```
minikube tunnel
```