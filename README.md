### QATracer
##TEST PROCEDURE FOR USING THE QA-Tracer PLUGIN
#Description 
The QA-Tracer plugin is used for tracing the Quality Attributes during the architecture design of the  System Development Life Cycle. The plugin works alongside the ArchTrace Studio 5; another plugin for eclipse used to design architectures.
#Installation and testing procedures
1)	Install the Java Development Kit (JDK) if this is not yet installed on your operating system.
2)	Install eclipse preferably eclipse for plugin development, this will come pre installed with the eclipse PDE, otherwise make sure you install or its installed.(The PDE is only required if you intend to compile from source). 
3)	Next install the ArchStudio plugin for designing architectures from
http://isr.uci.edu/projects/archstudio/setup-easy.html . This link provides the steps required to install ArchStudio and the support site also provides introduction videos on its use
4)	From the github repository https://github.com/munash48/QATracer you can clone or download the whole project to your local machine. For purposes of checking and improving the code you may clone the repository, but to use or test the plugin you may only download the repository.
5)	If you clone the project, then right click on the project and choose >run as> eclipse plugin. But if you download it, go to the folder Downloads in the plugin, extract the QATracerFinal plugins and copy them to the eclipse plugins installation folder folder and restart the IDE.
6)	After the restart, start the ArchStudio plugin by activating its perspective from the menu; >Window >Open Perspective >Other > then chose ArchStudio from the dialog box this will start the plugin.
7)	For testing purposes extract the contents of the sample zip file in the downloads folder to a specific location.
8)	Start the QA-Tracer Plugin using the menu; > Window >Show View> Other > Choose the QA-Tracer. Note that on opening, the plugin starts with the open dialog box where you should choose one of the file in the extracted sample folder.
9)	This will activate the tracking of changes in the csv file as well as the XML file. Please note that the csv file contains details of the requirements and the xml file is the architecture description.
10)	You should be able to show the requirements view, Components View and the Requirements Matrix view in the QA-Tracer main view of the plugin.
11)	Following the procedures on the Archstudio page you can load the architecture file (xml) into ArchStudio and modify it as you observe the changes in the Requirements Matrix that was produced earlier.

