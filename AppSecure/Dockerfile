FROM openjdk:17

WORKDIR /usrapp/bin

ENV PORT 5000
ENV URI "https://localhost:5001/hello"
ENV KEYSTORE_PASSWORD "123456"

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency
COPY /keystore /usrapp/bin/keystore/


CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arep.app.HelloWorld1"]