if [ $# -eq 0 ]
  then
    echo "No arguments supplied. Possible aurgment are local,test,acc,prod.Example- sh startapp.sh local"
    exit 1
fi
echo
mvn clean install -Denv=docker
denv="$1" docker-compose up
