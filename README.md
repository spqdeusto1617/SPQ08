# SPQ08

## Mail Application

### Features:
- Send mail
- Read mail
- View list of recieved mails
- Delete mail

### Technologies 

- [Intellij IDEA](https://www.jetbrains.com/idea/)
- [MongoDB](https://www.mongodb.com/)
- [RMI](http://docs.oracle.com/javase/7/docs/technotes/guides/rmi/hello/hello-world.html)
- [Maven](https://maven.apache.org/)
- [YouTrack](https://www.jetbrains.com/youtrack/)


### Installation

In order to run the projects on your computer you should first of all install the following tools by visiting links:
- [Intellij IDEA](https://www.jetbrains.com/idea/)
- [Git](https://www.atlassian.com/git/tutorials/install-git)
- [Mongodb](https://docs.mongodb.com/manual/installation/)
- [JDK 8 and JRE 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)

#### Run Server

When everything is set up you are ready to run the project. Perform the following steps:
- Clone repository which contains both applications: server and client by running: `git clone https://github.com/spqdeusto1617/SPQ08.git`
- Navigate to mailServer project`cd spqdeusto1617/mailServer`
- Run `mvn install` to download all dependencies of the project 
- Open **mailServer** folder your Intellij IDEA in order to run the server. 
- Edit configurations and add codebase in VM option. You should specify path to classes which will be in `/target/classes/`. Example: `-Djava.rmi.server.codebase=file:/home/inigo/workspace/Java/SPQ08/mailServer/target/classes/`
- Run rmiregistry `rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false`
- Run **mailServer** from IDE.

You should see message like: 
`* Server '//127.0.0.1:1099/EmailServer' active and waiting...`

#### Run Client

When server is ready you can run client application:
- Open client folder in NEW window of your IDE.
- Edit configurations of build and specify that you want to run an Application with `Window` as main class
- Run client application from IDE by clicking `run` button.

Client and Server is working...

