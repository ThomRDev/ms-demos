PIDS=$(ps aux | grep 'java' | awk '{print $1}')

if [ -n "$PIDS" ]; then
  for PID in $PIDS; do
    echo "Deteniendo el proceso con PID: $PID"
    kill $PID
  done
else
  echo "No se encontraron procesos java -jar en ejecución."
fi
