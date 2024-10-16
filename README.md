## Playwright introduction

### SUT
You will be testing a web application for searching events and initiate a ticket purchase.
The web application is provided in the web directory.

```
cd web
docker build -t playwright-intro .
docker run -d --name playwright-intro -p 5001:80 playwright-intro
```

You can open the web browser and navigate to http://localhost:5001/app/catalog.html to see one of the web pages.

### Codegen
Playwright comes with the ability to generate tests out of the box and is a great way to quickly get started with testing. It will open two windows, a browser window where you interact with the website you wish to test and the Playwright Inspector window where you can record your tests, copy the tests, clear your tests as well as change the language of your tests.

To run codegen execute the following command:
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen http://localhost:5001/app/catalog.html"
```
