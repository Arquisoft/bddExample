#!/bin/sh

set -eu

MIRROR=http://apache.rediris.es/kafka/1.0.0/kafka_2.11-1.0.0.tgz

test -f kafka.tgz || wget $MIRROR -O kafka.tgz
mkdir -p kafka && tar xzf kafka.tgz -C kafka --strip-components 1

kafka/bin/zookeeper-server-start.sh -daemon kafka/config/zookeeper.properties
echo "delete.topic.enable=true" >> kafka/config/server.properties
kafka/bin/kafka-server-start.sh -daemon kafka/config/server.properties