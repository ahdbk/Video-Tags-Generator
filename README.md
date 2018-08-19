# Video Tags Generator
Generate video tags based on IBM Watson Vision Recognition system 

This application is in Java:
- Vision Recognition for the tag generation
- Jcoder to extract image from video

## How it works

### Setup 
For this application you will need :
- IBM watson account for the vision you can create one from [here](https://www.ibm.com/watson/)
- Get your Endpoint url and apiKey for the vision Recognition system [documentation here](https://console.bluemix.net/docs/services/visual-recognition/getting-started.html#getting-started-tutorial)

### Project setup
- ```maven install```
- In the ```TagGenerator.java ``` class change the ```VIDEO_PATH``` variable with your Video path (for now we support only local saved video)
- add In the ```TagGenerator.java ```  your IBM watson endpoint url and apiKey (change the existing ones)


### Program output
Console output

![Screen Image 1](https://github.com/ahdbk/Video-Tags-Generator/blob/master/screen/Screen%20Shot%202017-11-26%20at%2021.37.46.png?raw=true)

Images Output 

![Screen Image 1](https://github.com/ahdbk/Video-Tags-Generator/blob/master/screen/Screen%20Shot%202017-11-26%20at%2021.33.24.png?raw=true)
