#! /bin/bash

# Not finished at all

echo "Setting up Kubernetes"

echo `winget install -e --id Kubernetes.kubectl`
echo `kubectl version --client`

echo `winget install Kubernetes.kind`

echo `kind create cluster --name learningpulse --wait 30s`

echo `kubectl cluster-info --context kind-learningpulse`




