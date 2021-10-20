#!/usr/bin/env bash
appiumPort=
wdaPort=
udid=
deviceName=
platformVersion=
hubAddress=
configFile=

bright=$(tput bold)
green=${bright}$(tput setaf 2)
greentea=$(tput setaf 156)
red=$(tput setaf 1)
bold=$(tput setaf 15)
normal=$(tput sgr0)

getDevice(){
    device=${deviceName// /_}
}

showUsage(){
  printf "%40s\n" "
    ---------------------------------------------------------------------------------------
               iOS Appium Node Registration Tools
    ---------------------------------------------------------------------------------------
    -h <hub ip address/domain>  The IP Address/Domain name where the hub server placed.
    -p <local appium port>      Appium port that used in local machine.
    -w <local wda port>         WDA port that used in local machine.
    -d <iOS device name>        Specific iOS device name as a node.
    -v <iOS version>            Specific iOS version of iOS device.
    -u <iOS udid>               Specific UDID of iOS device.

    NOTE: all of the options above are mandatory, make sure its all filled.

    Example usage:
      ./register_node.sh -h 192.168.1.2 \\
      -p 4455 \\
      -w 8100 \\
      -d \"iPhone X\" \\
      -v 12.2 \\
      -u 88B43690-A804-4B8D-8216-DA0C8C1CA497
    ---------------------------------------------------------------------------------------
  "
}

printNodeAttribute(){
	clear
	echo "========================================================="
	echo "    Node Attributes:"
	echo "========================================================="
	echo "- Hub address : $hubAddress"
	echo "- Appium port	: $appiumPort"
	echo "- WDA port	: $wdaPort"
	echo "- Device name	: $deviceName"
	echo "- Platform version: $platformVersion"
	echo "- Udid		: $udid"
  echo "========================================================="
}

setupConfigFile(){
	echo "Setting up the config file....."
    device=${deviceName// /_}
	configFile="config/node-"$device"-$platformVersion.json"

	if [[ -f $configFile ]]
	then
		echo "Override previous configuration which stored inside $configFile."
		rm -rf $configFile
	fi
    currentIp=$(ifconfig | awk '/net / { x = $2 } END { print x }')

	cp config/config_template.json $configFile

	sed -i -e 's/udid/'$udid'/g' $configFile
	sed -i -e 's/osVersion/'$platformVersion'/g' $configFile
	sed -i -e 's/defaultPort/'$appiumPort'/g' $configFile
	sed -i -e 's/defaultHubIpAddress/'$hubAddress'/g' $configFile
	sed -i -e 's/defaultHost/'$currentIp'/g' $configFile

	rm -rf config/*.json-e
}


registerNode(){
  device=${deviceName// /_}
	configFile="config/node-"$device"-$platformVersion.json"

	if [ ! -d "logs" ]; then
    mkdir -p logs
	else
    echo "logs folder is exist"
	fi

  nohup appium -p $appiumPort \
  --webdriveragent-port $wdaPort \
  --nodeconfig $configFile \
  --default-capabilities '{"udid":"'$udid'","deviceName":"'$device'","platformVersion":"'$platformVersion'"}' \
  --session-override >> "logs/node_ios_"$platformVersion"_"$device".log" &
}

doSetup(){
  printNodeAttribute
  setupConfigFile
  registerNode
}

while getopts ":h:p:w:d:v:u:" opt; do
  case $opt in
  h)
    hubAddress="$OPTARG"
    echo "hub address: $hubAddress"
    ;;
  p)
    appiumPort="$OPTARG"
    ;;
  w)
    wdaPort="$OPTARG"
    ;;
  d)
    deviceName="$OPTARG"
    echo "device name: $deviceName"
    ;;
  v)
    platformVersion="$OPTARG"
    ;;
  u)
    udid="$OPTARG"
    ;;
  esac
done

if [[ "$1" == "" ]] || [[ "$1" != "" ]]; then
  case $1 in
  help)
    showUsage
    exit 0
    ;;
  esac
else
  echo "Please enter only the available options!"
  exit 0
fi

if [ -z "$hubAddress" ]; then
  echo ""
  echo ${red}"You have to specify where the hub server is located!"${normal}
  exit 1
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

if [ -z "$deviceName" ]; then
  echo ""
  echo ${red}"You have to specify the device name to be registered as destination!"${normal}
  exit 1
fi

if [ -z "$platformVersion" ]; then
  echo ""
  echo ${red}"You have to specify the version of iOS device!"${normal}
  exit 1
fi

if [ -z "$udid" ]; then
  echo ""
  echo ${red}"You have to specify the iOS device udid!"${normal}
  exit 1
fi

if [[ "$hubAddress" != "" ]] && [[ "$appiumPort" != "" ]] && [[ "$wdaPort" != "" ]] && [[ "$deviceName" != "" ]] && [[ "$platformVersion" != "" ]] && [[ "$udid" != "" ]]
then
  doSetup
fi