all: unit integration

unit:
	lein midje stereotype.unit.*

integration:
	lein midje stereotype.integration.*

ci:
	lein2 with-profile dev,1.3:dev,1.4:dev,1.5 midje

ci-local:
	lein with-profile dev,1.3:dev,1.4:dev,1.5 midje
