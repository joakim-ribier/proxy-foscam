# proxy-foscam
Http proxy for the FOSCAM Webcam API.

### Versions

|  ?     |  Play Framework  |
|--------|------------------|
| 0.0.1  |  2.4.3           |

### Configuration
Configure the connexion to the FOSCAM.
```
webcam {
  address : "http://localhost:88",
  user : "admin",
  password : ""
}
```

Allow only a few services in the application.conf.
```
actions : ["getMotionDetectConfig", "setMotionDetectConfig"]
```

Filter all parameters for each service in the webcam-api.conf.
```
setMotionDetectConfig {
  params : [
    "isEnable", "sensitivity", "linkage", "snapInterval", "triggerInterval",
    "schedule0", "schedule1", "schedule2", "schedule3", "schedule4", "schedule5", "schedule6",
    "area0", "area1", "area2", "area3", "area4", "area5", "area6", "area7", "area8", "area9"
  ]
},
```

### Authentication
Add a simple authentication by user/password for each request.
```
proxy {
  authentication: true,
  user : "user",
  password : "pwd"
}
```

### Build status

Thanks to [CodeShip.io](https://codeship.io/), we now have a build status for this repository:

[ ![Codeship Status for joakim-ribier/proxy-foscam](https://codeship.com/projects/9d8732c0-3d4c-0133-3eb0-1abe7f570a4c/status?branch=master)](https://codeship.com/projects/102480)

