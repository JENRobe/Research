# Subalgebra Generator
This project was used for computing subalgebras of a given Algebra. This involved taking data from a text file that included a universe size and a number of operations, followed by the arity of an operation and its outputs.
## Text Files
This project has many test files that cover a wide variety of different algebras currently known in the field of logic.
* Binary Algbra: This algebra consists of just the values 0 and 1. It is an algebra of precisely two elements. 
* DeMorgan Algebra: Has 4 elements with 5 operations. 
* Double Stone Algebra: Has 4 elements with 6 operations. 
* Kleene Algebra: Has 3 elements with 5 operations. 
* Stone Algebra: This algebra also has 3 elements with 5 operations, the difference is the parity of the different operations. 
## Java Folder
The Java folder contains the different files necessary to properly calculate the different subalgebras, while this work is not entirely finished the main mechanics work properly.
* Test.java is a file where the user can play around with how to create their own algebra- one that is not already made like the those above, and from the program will output the product algebra, along with all proper sub algebras. Do note that this current program only works up to the specified size.
### Algebragen
This file folder contains the actual program given a proper text file such as those above. 
* A.java: Reads in a text file of an algebra and creates the actual Algebra that is needed based on the properties defined in the textfile.
* Pair.java: A class that create a coordinate pair object.
* Subalgebra.java: Creates an object called Subalgebra with all the necessary properties.
* Driver.java: Gives the text file to the A class to create the Algebra, which is then in turn used to calculate the subalgebras. 

## Work Going Forward
This class still needs a few properties to make it as efficient as possible such as a property to remove duplicate subalgebras, employ an entail property to get rid of the subalgebras that do not make logical sense, and compute the subsets of the product algebra to see what algebras are just contained in larger ones to reduce the amount of duplicate calls.
