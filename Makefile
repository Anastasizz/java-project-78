.PHONY: build checkstyle test

run-dist:
	cd app && ./build/install/app/bin/app

build:
	cd app && ./gradlew build

checkstyle:
	cd app && ./gradlew checkstyleMain

test:
	cd app && ./gradlew test

report:
	cd app && ./gradlew jacocoTestReport