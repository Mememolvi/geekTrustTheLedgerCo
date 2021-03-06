# Pre-requisites
* Java 1.8/1.11/1.15
* Maven

# Coding challange Link:
https://www.geektrust.com/coding/detailed/the-ledger-co

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows.  Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`. You are supposed to add the input commands in the file from the appropriate problem statement. 

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 We expect your program to take the location to the text file as parameter. Input needs to be read from a text file, and output should be printed to the console. The text file will contain only commands in the format prescribed by the respective problem.

 Use the pom.xml provided along with this project. Please change the main class entry (`<mainClass>com.example.geektrust.Main</mainClass>`) in the pom.xml if your main class has changed.

 # Running the code for multiple test cases

 Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar target/geektrust.jar sample_input/input1.txt` with `java -jar target/geektrust.jar sample_input/input2.txt` to run the test case from the second file. 

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

# Example Input Output

SAMPLE INPUT-OUTPUT 1 <br />
INPUT:<br />
LOAN IDIDI Dale 10000 5 4<br />
LOAN MBI Harry 2000 2 2<br />
BALANCE IDIDI Dale 5<br />
BALANCE IDIDI Dale 40<br />
BALANCE MBI Harry 12<br />
BALANCE MBI Harry 0<br />

OUTPUT:<br />
IDIDI Dale 1000 55<br />
IDIDI Dale 8000 20<br />
MBI Harry 1044 12<br />
MBI Harry 0 24<br />

SAMPLE INPUT-OUTPUT 2<br />
INPUT:<br />
LOAN IDIDI Dale 5000 1 6<br />
LOAN MBI Harry 10000 3 7<br />
LOAN UON Shelly 15000 2 9<br />
PAYMENT IDIDI Dale 1000 5<br />
PAYMENT MBI Harry 5000 10<br />
PAYMENT UON Shelly 7000 12<br />
BALANCE IDIDI Dale 3<br />
BALANCE IDIDI Dale 6<br />
BALANCE UON Shelly 12<br />
BALANCE MBI Harry 12<br />

OUTPUT:
IDIDI Dale 1326 9<br />
IDIDI Dale 3652 4<br />
UON Shelly 15856 3<br />
MBI Harry 9044 10<br />
