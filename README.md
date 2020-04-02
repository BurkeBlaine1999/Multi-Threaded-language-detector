###MULTI THREADED LANGUAGE DETECTOR

#Description
My programes function is to identify different langauges that are inputted by the user by the use of sample language wili files
and a seperate query file. This is done by using ngrams to break down the wili files words
so we can compare it with those from the query file to decide which language is being used.

#Instructions
* You will be brought to the menu of the language detector.Select 1 to use the detector or 2 to exit.
* Input your file locations.
* Specify the amount of kmers you would like to use. (higher kmer count means more accurate results, at the loss of higher resource usage.)
* Input Query file location you wish to find out the language of.
* The Language inputted will display , if incorrect try changing the amount of kmers used!
* Press enter to return to the menu.

#Extra Features
* You can select the amount of kmers you would like to use.
* I added pressAnyKeyToContinue() method  to the parser class so that when you recieve your results you must press any key to continue to return to the menu!

#Acknowledgements
* Orcale.
* Java 8 doc's.
* Object Oriented Programming Lecture slides.
* StackOverflow when facing issues.
