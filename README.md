# Getting Started

### Requirements
For deploy this project you will need

* java 21
* gradle optional

### Guides

To build the project 

`./gradlew build`

To test the project you can execute 
`./gradlew bootRun` 

Or `java -jar build/libs/mowerbot-0.0.1-SNAPSHOT.jar`

Once the project is running you can test it with this: 

```console
curl --request POST \
  --url http://localhost:8080/v1/mower/commands \
  --header 'content-type: application/json' \
  --data '{
	"plateau": {
		"width": 5, 
		"height": 5
	},
	"mowers" : [
		{
			"location": {
				"x": 1,
				"y": 2,
				"direction": "N"
			},
			"commands": "LMLMLMLMM"
		},
		{
			"location": {
				"x": 3,
				"y": 3,
				"direction": "E"
			},
			"commands": "MMRMMRMRRM"
		}
	]
	
}'
```

You should have an answer like 

`[{"x":1,"y":3,"direction":"N"},{"x":5,"y":1,"direction":"E"}]`

Also, there is **swagger ui** at http://localhost:8080/swagger-ui/index.html available