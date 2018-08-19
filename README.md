# Video Tags Generator
Generate video tags based on IBM Watson Vision Recognition system 

This application is in Java:
- Vision Recognition for the tag generation
- Jcoder to extract image from video

## How it works

### Setup 
For this application you will need :
- IBM watson account for the vision you can create one from [here](https://www.ibm.com/watson/)
- Get your Gateway url and apiKey for the vision Recognition system [documentation here](https://console.bluemix.net/docs/services/visual-recognition/getting-started.html#getting-started-tutorial)

### Project setup
- ```maven install```
- In the ```TagGenerator.java ``` class change the ```VIDEO_PATH``` variable with your Video path (for now we support only local saved video)
- Change this part In the ```TagGenerator.java ``` with your Geteway url and apiKey
```
// Here you replace "your_api_key_here" by the API Key you created in "Creating
// a Watson Visual Recognition service instance and getting the API key"
service.setApiKey("acc4516893bf5b2fea7b0d4c4942fed4a167e1a1");```
