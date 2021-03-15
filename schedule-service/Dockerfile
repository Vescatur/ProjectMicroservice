from amazoncorretto:11
COPY . /
RUN sed -i 's/\r$//' ./gradlew
RUN ls -l
RUN sh ./gradlew bootJar
CMD ./gradlew bootRun
