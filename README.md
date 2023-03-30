# sample export command
````shell
java -jar cli-0.1.jar export  --api-endpoint http://localhost:8080 employees
````
- this command calls the employees api on path `/api/employees` , fetchs a list of employees and exports them to a file on local storage
-  help command : 
````shell
java -jar cli-0.1.jar export --help
````
