#!/bin/bash

# Verificar si se ha proporcionado un argumento
if [ $# -eq 0 ]; then
    echo "Uso: ./run.sh <clase_principal>"
    echo "Ejemplo: ./run.sh com.biblioteca.Main"
    exit 1
fi

# Clase principal proporcionada como argumento
MAIN_CLASS=$1

# Ruta del directorio del proyecto
PROJECT_DIR="$(pwd)" # Cambia esto si el script no está en la raíz del proyecto

# Paso 1: Navegar al directorio del proyecto
echo "Navegando al directorio del proyecto..."
cd "$PROJECT_DIR" || { echo "Error: No se pudo acceder al directorio $PROJECT_DIR"; exit 1; }

# Paso 2: Compilar el proyecto con Maven
echo "Compilando el proyecto con Maven..."
if ! mvn clean compile; then
  echo "Error durante la compilación."
  exit 1
fi

# Paso 3: Ejecutar la aplicación principal con la clase proporcionada
echo "Ejecutando la clase principal: $MAIN_CLASS"
mvn exec:java -Dexec.mainClass="$MAIN_CLASS"
if ! mvn exec:java -Dexec.mainClass="$MAIN_CLASS"; then
  echo "Error durante la ejecución de la aplicación."
  exit 1
fi
