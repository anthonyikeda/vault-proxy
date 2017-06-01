#!/usr/bin/env bash
set -e -x

git clone git@github.com:anthonyikeda/vault-proxy.git

cd vault-proxy

mvn clean

mvn install


