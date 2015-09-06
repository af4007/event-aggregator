RESTful API written in Scala, using the Play Framework.

Please follow below steps to run the application,
	1. Please unzip/download the project in any directory
	2. Then go to project root directory, e.g. cd event-aggregator
	3. then run the command .\activator run to start the application

after successful message of activator our application is ready to take and serve REST requests.
For API related details please refer the attached document.

And then http://localhost:9000

To use the REST API:
1. To send/create event:
curl -X POST http://localhost:9000/sendEvent -H "Content-Type: application/json" -d "{\"eventType\": \"pageView\",\"timeStamp\":1433942949}"

2. To count events by event type and between to and from time specified, response would be a group by minute
http://localhost:9000/dispatcher?action=countEvents&eventType=pageView&startTime=1133942949&endTime=1435998988

expected response:
[{"timeStamp":23899080,"count":1},{"timeStamp":23899047,"count":3}]

3. To get events specified and between start time and end time
http://localhost:9000/dispatcher?action=getEvents&eventType=pageView&startTime=1133942949&endTime=1435998988

expected response:
[{"eventType":"pageView","timeStamp":1433942851},{"eventType":"pageView","timeStamp":1433942831},{"eventType":"pageView","timeStamp":1433942841}]

Please let me know your comments and suggestions. 

Thank you!
