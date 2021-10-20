#!/usr/bin/env bash
appiumPort=
wdaPort=

showUsage(){
  printf "%40s\n" "
      ---------------------------------------------------------------------------------------
                 iOS Appium Node Killer Tools
      ---------------------------------------------------------------------------------------
      -p <local appium port>      Appium port that used in local machine.
      -w <local wda port>         WDA port that used in local machine.

      NOTE: all of the options above are mandatory, make sure its all filled.

      Example usage:
        ./kill_node.sh -w 4455 -p 8100
      ---------------------------------------------------------------------------------------
  "
}

while getopts ":p:w:" opt; do
  case $opt in
  p)
    appiumPort="$OPTARG"
    ;;
  w)
    wdaPort="$OPTARG"
    ;;
  esac
done

if [[ "$1" == "" ]]; then
  showUsage
  exit 0
elif [[ "$1" == "help" ]]; then
  showUsage
  exit 0
elif [[ "$1" == "-p" ]] || [[ "$1" == "-w" ]]; then
  PID=`ps -eaf | grep "appium -p $appiumPort --webdriveragent-port $wdaPort" | grep -v grep | awk '{print $2}'`

  if [ "$PID" == "" ]; then
    echo "No such appium node process found. Please retry with valid port number!"
  else
    echo "Kill appium node with PID : $PID"
    kill -9 $PID
  fi
else
  echo "Please enter only the available options!"
  exit 0
fi

if [ -z "$appiumPort" ]; then
  echo ""
  echo ${red}"You have to specify the appium port in local machine!"${normal}
  exit 1
fi

if [ -z "$wdaPort" ]; then
  echo ""
  echo ${red}"You have to specify the WDA port in local machine!"${normal}
  exit 1
fi