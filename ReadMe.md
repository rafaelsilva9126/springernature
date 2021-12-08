TRUPHONE FRONT END

--SELECTING A BROWSER

- Change the browser tag, inside the file and environmentconfiiguration.yaml,
 and write one of the options chrome, firefox, edge or opera.
 *Make sure that if you choose any of the aforementioned browsers, you have them installed. It is not necessary to install the drivers, you just has to have the browser installed on the machine.
 
--RUNNING TEST MODES

1. Run  by command line using the mvn test command on the path of the project (Maven installed is required.)
2. Run by junit clicking on RunnerTest.Java and execute by junit
3. Run by cucumber clicking on Coverage.featurefile

--HEADLESS MODE

- Select chrome as browser and uncomment line 45 of BaseTest.java


