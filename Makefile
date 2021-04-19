
gradlew-build: gradlew-clean
	./gradlew build

gradlew-clean:
	./gradlew clean

run:
	java -jar ./build/libs/java-spring-skeleton-1.0.0.jar

run-call:
	@(java -jar ./build/libs/java-spring-skeleton-1.0.0.jar) & \
	export PID=$$! && \
	echo Started $$PID && \
	sleep 10 && \
	curl http://localhost:8090/v1/messages && \
	kill $$PID

kill-procs:
	ps -ef | grep 'java-spring-skeleton' | grep -v grep | awk '{print $2}' | xargs kill

bench:
	ab -n 10000 -c 100 http://localhost:8090/v1/messages
