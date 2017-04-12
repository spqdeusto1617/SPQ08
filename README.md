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

Read carefully installation guide. In order to run the projects on your computer you should first of all install the following tools:
- [Intellij IDEA](https://www.jetbrains.com/idea/)
- [Git](https://www.atlassian.com/git/tutorials/install-git)
- [Mongodb](https://docs.mongodb.com/manual/installation/)
- [JDK 8 and JRE 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)


#### Clone repo

- Clone repository which contains both applications: server and client by running: <br />
`git clone https://github.com/spqdeusto1617/SPQ08.git`

#### Run rmiregistry

- Open your command line, navigate to root of mailServer and run: <br />
`rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false`

#### Run Server


- Download all dependencies of the project. In the root of mailServer project: <br />
 `mvn install`
- Open **mailServer** folder in your Intellij IDEA in order to run the server. 
- Edit configurations and add codebase in VM option. You should specify path to classes which will be in `/target/classes/`. Example: <br />`-Djava.rmi.server.codebase=file:/home/inigo/workspace/Java/SPQ08/mailServer/target/classes/`
- Run **mailServer** from IDE.

You should see message like: 
`* Server '//127.0.0.1:1099/EmailServer' active and waiting...`

#### Run Client

When server is ready you can run client application:
- Open client folder in NEW window of your IDE.
- Edit configurations of build and specify that you want to run an Application with `Window` as main class
- Run client application from IDE by clicking `run` button.

Client and Server is working...

