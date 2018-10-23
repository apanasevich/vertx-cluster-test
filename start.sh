#!/usr/bin/env sh

export JAVA_OPTS="-Xms2g -Xmx4g -Xdebug -server -XX:+UseG1GC -XX:MaxGCPauseMillis=400 -XX:NewRatio=4 -XX:SurvivorRatio=4 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath="/jenkins/deploy/allinoneNode0/dump.hprof" -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:"/jenkins/deploy/allinoneNode0/gc.log" -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.port=7091 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Dlog4j.configurationFile=/jenkins/deploy/allinoneNode0/conf/log4j2.xml -Dvertx.hazelcast.async-api=true -Djava.awt.headless=true"
export JAVA_HOME=/jenkins/deploy/allinoneNode0/../jre

sh bin/launcher -c conf/application.conf &
exit 0
