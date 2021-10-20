# bukalapak_ios_cucumber
Repository for iOS automation using Cucumber framework

Anything related to this project can be found in : https://bukalapak.atlassian.net/wiki/spaces/CQA/pages/1035185953/iOS+Automation+Framework+Refactor

## Run On Local iOS Simulator
- Command usage: 
```bash
./gradlew clean test -DappPath={path-to-app} -Ddevice={deviceName} -DplatformVersion={ios.version} -Dcucumber.options="--tags @tagname"
```
- Example usage: 
```bash
./gradlew clean test -DappPath=/Users/bukalapak/automation_app/bl_ios.app -Ddevice="iPhone 8" -DplatformVersion=12.2 -Dcucumber.options="--tags @important"
```

- Capabilities file for iOS simulator profiles :
    - inside 'src/test/resources/capabilities/simulator/'
    - copy 'simulator.capabilities.properties.sample' into 'capabilities.properties' and put it in the project root folder

## Run On Physical iOS Device
- Command usage: 
```bash
./gradlew clean test -DappPath={path-to-app} -Dcapability=device -Ddevice={deviceName} -DplatformVersion={ios.version} -Dudid={deviceUdid} -Dcucumber.options="--tags @tagName"
```
- Example usage: 
```bash
./gradlew clean test -DappPath=/Users/bukalapak/automation_app/bl_ios.app -Dcapability=device -Ddevice="bukalapak's iPhone X" -Dudid="cafdc5f678459de79ac56edf79b60e7e068e5fef" -DplatformVersion=12.3.1 -Dcucumber.options="--tags @important"
```

- Capabilities file for iOS physical device profiles :
    - inside 'src/test/resources/capabilities/device/'
    - copy 'device.capabilities.properties.sample' into 'capabilities.properties' and put it in the project root folder

## Display Detailed appium.log
- Add this to your gradle command :
```bash
-DlogLevel=DEBUG
```
## Export XRay Test Execution to Local Machine
```bash
./xray.sh -e -x XRAY_TAG
```

## Rerun On Local iOS Simulator
- Any failed scenario will be stored inside directory "rerun/failed_scenarios.txt"
- To perform rerun, simply change the "cucumber options" to point at that file
- Example command : 
```bash
./gradlew clean test -DappPath={path-to-app} -Dcucumber.options="@rerun/failed_scenarios.txt"
```
- Remember to copy out the previous 'cucumber.json' to another folder before performing rerun (this step is needed when you want to merge the report of both runs)
- How to merge the report : 
```bash
java -jar libs/malbec.jar merge [previous-cucumber.json] [rerun-cucumber.json]
```

## Run On Cloud
- Options : 'kobiton', 'bitbar' and 'saucelab'

- Example command : 
```bash
./gradlew clean test -Dcapability=kobiton -Dcucumber.options="--tags @important"
```

- Config and capabilities file for Cloud Profiles :
    - Bitbar : inside src/main/resources/bitbar
    - Kobiton : inside src/main/resources/kobiton
    - Saucelab : inside src/main/resources/saucelab

## Kill iOS Simulator After Execution Complete
- This task is optional, but can free up some memory space after the execution of automation has been completed.
    - **Command usage** : 
    ```bash
    java -jar libs/ios-simulator-killer-1.0.jar "OS_VERSION" "DEVICE_NAME"
    ```
    - **Example usage** : 
    ```bash
    java -jar libs/ios-simulator-killer-1.0.jar "12.1" "iPhone XS"
    ```
