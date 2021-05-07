
gradlew-build: gradlew-clean
	./gradlew build

gradlew-clean:
	./gradlew clean

run:
	java -jar ./build/libs/java-spring-boilerplate-1.0.0.jar

.ONESHELL:
run-call: gradlew-build
	@(java -jar ./build/libs/java-spring-boilerplate-1.0.0.jar) &
	export PID=$$! &&
	echo Started $$PID &&
	sleep 20 &&
	curl http://localhost:8090/customers &&
	kill $$PID
	rm customers.db

kill-procs:
	ps -ef | grep 'java-spring-boilerplate' | grep -v grep | awk '{print $2}' | xargs kill

bench:
	ab -n 10000 -c 100 http://localhost:8090/customers
