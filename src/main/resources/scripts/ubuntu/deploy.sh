#!/bin/bash
modulName=`echo $1`;
pid=$(ps -aef | grep java | grep apache | grep $modulName | awk '{print $2}');
echo ${pid};
if [ "${pid}" ]; then
  eval "kill -9 ${pid}"
fi
