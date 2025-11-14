#!/bin/bash

cd "$(dirname "$0")"

echo "Starting CoWorking Platform..."

/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/java \
  --module-path "/Users/wangsiting/Downloads/javafx-sdk-21.0.5 2/lib" \
  --add-modules javafx.controls,javafx.fxml \
  -cp "bin:/Users/wangsiting/Downloads/javafx-sdk-21.0.5 2/lib/*" \
  application.Main

echo "Application closed."

