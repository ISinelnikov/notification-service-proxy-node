#!/bin/sh

if [[ -z "$1" ]]; then
    echo "Command or version is empty. Use start/stop/restart for start application."
else
    SERVICE_NAME=notification-service-proxy-node.jar
    PATH_TO_JAR=/node/"$SERVICE_NAME"
    PATH_TO_CONFIG=/node/application.config.yaml
    PID_PATH_NAME=/tmp/"$SERVICE_NAME-pid"

    if [[ -f ${PATH_TO_JAR} ]]; then
        case $1 in
            start)
                echo "Starting $SERVICE_NAME..."
                if [[ ! -f ${PID_PATH_NAME} ]]; then
                    nohup java -jar -Dapplication.config.path=${PATH_TO_CONFIG} ${PATH_TO_JAR} >> "proxy-node-catalina.out" 2>&1&
                    echo $! > ${PID_PATH_NAME}
                    echo "$SERVICE_NAME started..."
                else
                    echo "$SERVICE_NAME is already running..."
                fi
            ;;
            stop)
                echo "Stopping $SERVICE_NAME..."
                if [[ -f ${PID_PATH_NAME} ]]; then
                    PID=$(cat ${PID_PATH_NAME});
                    echo "$SERVICE_NAME stopping..."
                    kill ${PID};
                    echo "$SERVICE_NAME stopped..."
                    rm ${PID_PATH_NAME}
                else
                    echo "$SERVICE_NAME is not running..."
                fi
            ;;
            restart)
                echo "Restarting $SERVICE_NAME..."
                if [[ -f ${PID_PATH_NAME} ]]; then
                    PID=$(cat ${PID_PATH_NAME});
                    echo "$SERVICE_NAME stopping...";
                    kill ${PID};
                    echo "$SERVICE_NAME stopped...";
                    rm ${PID_PATH_NAME}
                    echo "$SERVICE_NAME starting..."
                    nohup java -jar -Dapplication.config.path=${PATH_TO_CONFIG} ${PATH_TO_JAR} >> "proxy-node-catalina.out" 2>&1&
                    echo $! > ${PID_PATH_NAME}
                    echo "$SERVICE_NAME started..."
                else
                    echo "$SERVICE_NAME is not running..."
                fi
            ;;
        esac
    else
        echo "$PATH_TO_JAR not found..."
    fi
fi