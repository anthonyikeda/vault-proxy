#!/usr/bin/env bash
set -e -x

git clone vault-proxy-code vault-proxy

cd vault-proxy

mvn clean

mvn install


