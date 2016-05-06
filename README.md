WriterDaemon
============

WriterDaemon is intended to be a tool to assist authors of creative works. The precise
requirements are a bit vague at the moment but some potential features include

+ Analysis of text to obtain statistics such as word count, sentence count, vocabulary lists,
  etc.

+ Spell checking with good support for proper nouns and created words. It would also be nice to
  have the check sensitive to dialog, perhaps using different or relaxed rules in dialog
  content.

+ Grammar checking?

The primary supported file format will be LaTeX to start but support for other formats such as
plain text and ODF would be nice. It is assumed the author is using a version control system so
features supported by such systems do not need to be a part of WriterDaemon. On the other hand
WriterDaemon should "play nicely" with version control and perhaps interact with it in some
useful way.

WriterDaemon is intended specifically to support creative writing. Thus features, such as
bibliography handling, are of low priority. On the other hand intelligent handling of dialog is
a high priority since dialog is a large component of creative works and often has a very
different tone than the surrounding narrative. See the WriterDaemon documentation in the `doc`
folder for more information about the requirements and design of the system.

Build Instructions
------------------

WriterDaemon is written in Scala. To build the system you will need the following software
installed. The version numbers given are for the specific versions we're using. In most cases
other closely related versions would probably also work, but have not been tested.

+ [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (1.8.0_31)

+ [Scala](http://www.scala-lang.org/) (2.11.6)

  It is important that you use a Scala version that matches the major release number mentioned
  here. For example, using a Scala version such as 2.10.x or 2.12.x will not work. However, any
  minor release within a major release (the 'x' in the version numbers above) should be fine.

+ [IntelliJ IDEA](http://www.jetbrains.com/idea/) (14.1.3)

  We use the Community Edition. You will also need to download and install the Scala plugin from
  the plugin control panel. You may have to configure IntelliJ to find your Java JDK.

  The WriterDaemon project assumes a suitable Scala SDK has been configured. This can be done
  from inside IntelliJ by pointing the IDE to the folder where Scala has been installed. The
  Scala SDK library should be named "scala-2.11." You also need to define a global library named
  `scalatest_2.11-2.1.5` that contains the ScalaTest jar of the same name. The actual location
  of the jar file is up to you.

  For the scaladoc build to work you need to define `SCALA_HOME` and `SCALATEST_JAR` environment
  variables at the operating system level. The first should point at the folder containing your
  Scala installation. The second should point at the ScalaTest jar file itself.

  Finally for the WriterDaemon.jar artifact to be correctly built you will need to configure the
  IntelliJ path variable `SCALA_HOME` to point at the folder containing your Scala installation.

Once the prerequisites listed above are installed and configured you should be able to open the
WriterDaemon project in IntelliJ and build it using the usual menu selections. A build artifact
is also defined that generates the WriterDaemon executable jar. Finally an `ant` build file is
provided that allows you to build the scaladoc associated with the project.

Peter C. Chapin  
PChapin@vtc.vsc.edu
