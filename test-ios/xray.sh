#!/usr/bin/env bash

while getopts ":ix:ej:" option; do
    case "${option}" in
        i) IMPORT=true ;;
        e) EXPORT=true ;;
        x) XRAY_TAG=${OPTARG} ;;
        j) JSON_NAME=${OPTARG} ;;
    esac
done

function get_xray_token() {
    token=$(curl -s -H "Content-Type: application/json" -X POST --data @"xray_auth.json" https://xray.cloud.xpand-it.com/api/v1/authenticate| tr -d '"')
}

function export_cucumber_tests(){
    curl -s -o $1.zip -H "Content-Type: application/json" -X GET -H "Authorization: Bearer $token"  "https://xray.cloud.xpand-it.com/api/v1/export/cucumber?keys=$1"
    mkdir -p xray
    unzip -o $1.zip -d src/test/resources/features/
    mv $1.zip xray/
}

function import_execution_results(){
    curl -s -H "Content-Type: application/json" -X POST -H "Authorization: Bearer $token"  --data @"target/json-report/$JSON_NAME" https://xray.cloud.xpand-it.com/api/v1/import/execution/cucumber > /dev/null
}


if [[ $IMPORT == true ]]; then
    if [[ $JSON_NAME == "" ]]; then
        echo "You forgot to pass Cucumber Json Report name!"
        exit 1
    fi
    get_xray_token
    import_execution_results
    echo "Execution Results has been imported to Xray!"
elif [[ $EXPORT == true ]]; then
    if [[ $XRAY_TAG != "" ]]; then
        get_xray_token
        if [[ $XRAY_TAG == *","* ]]; then
            IFS=',' read -r -a array <<< "$XRAY_TAG"
            for element in "${array[@]}"
            do
                echo "$element"
                export_cucumber_tests $element
                echo "Cucumber tests with key $element has been exported!"
            done
        else
            echo "$XRAY_TAG"
            export_cucumber_tests $XRAY_TAG
            echo "Cucumber tests with key $XRAY_TAG has been exported!"
        fi
    else    
        echo "You forgot to pass XRAY tag!"
        exit 1
    fi
else
    echo "You forgot to pass import / export flag!"
    exit 1
fi
