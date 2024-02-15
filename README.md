# Personal/Organization/Coordination number classifier
This project includes a simple program written in Java for classifying various numbers as either
"personal" (personnummer), "organization" (organisationsnummer) or "coordination" (samordningsnummer).

To run the program, make sure that you have Java installed on your computer and that you are running a UNIX-based operating system.

You now have the following choices:
1. Classify a single number
   - Run the `run.sh` script with a single argument, which is the number you want to classify. For example:
     ```bash
     ./run.sh 198001012381
     ```
2. Classify multiple numbers
- Run the `run.sh` script with a file as an argument using the `-f`option. The file must contain one number per line. For example:
  ```bash
  ./run.sh -f numbers.txt
  ```

The program will then classify the numbers and print the results to the terminal.