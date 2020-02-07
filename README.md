# codeTestKofax

This is my solution for the Kafax coding Test.

Author: Rick Price<rprice@pricemail.ca>

I used the Java8 Stream API's because they make the code shorter and more readable.
I placed the logic to crack a string into a SchoolGradeRecord into the class to keep things encapsulated, and to make it easier to find the logic later.
I used Java's native error checking where possible (array out of bounds), in a more production implementation, I would probably setup a proper hierarchy of errors that could be thrown depending on what was wrong with the data.
I would not normally print things out using System.out, but I didn't want to introduce any external dependencies for this project. I normally use Log4J.
Normally I would use a GNU Readline like library to read the program parameters and handle the command line interface, but I didn't want to introduce external dependencies.