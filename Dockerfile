FROM openjdk

WORKDIR /application

COPY mohmed.java .

RUN javac mohmed.java

CMD java mohmed 
