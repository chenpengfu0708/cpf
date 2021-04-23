#!/bin/sh
PORT=8888
HOME='/root/led'

pid=$(ps -ef | grep led-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}')

pid_file=/root/led/led.pid
start(){
   echo "start..."
   if [ -n "$pid" ];then
	   echo "service alread start,pid:$pid"
	   return 0
   fi
   cd $HOME
   nohup java -jar -Dspring.config.location=application.yml led-0.0.1-SNAPSHOT.jar >led.log 2>&1 &   
   echo "running success test port:$PORT"
   echo "test port:$PORT 服务启动成功 ..... "
   echo  $! > $pid_file
}

stop(){
   echo "stopping running cloud-core ............... "
   if [ -z "$pid" ];then
      echo "test port:$PORT 服务已经被关闭了请执行 sh run.sh start "
      return 0
   fi
   kill -9 $pid
   rm -rf $pid
   echo "kill program use signal 2,pid:$pid"   
}

status(){
   if [ -z "$pid" ]; then
      echo "not find program on port:$PORT"
   else
     echo "program is running,pid:$pid"
   fi     
}

case $1 in
  start)
     start
  ;;
  stop)
     stop
  ;;
  restart)
     $0 stop
     sleep 2
     $0 start
   ;;
  status)
     status
  ;;
  *)
      echo $"Usage: $0 {start|stop|status}" 
      exit 0
esac      

