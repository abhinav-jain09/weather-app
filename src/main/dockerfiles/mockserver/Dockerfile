FROM ddavison/soapui
ADD tests-soapui-project.xml /opt/bin/tests-soapui-project.xml
CMD mockservicerunner.sh -m 'weatherMockService' 'tests-soapui-project.xml'
EXPOSE 8089