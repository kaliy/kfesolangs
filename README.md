kfesolangs
==========

kfesolangs is a simple esoteric languages interpreter. Currently it supports only brainfuck but more languages will be added soon.


Usage
--------

    java -jar kfesolangs.jar <-l language> <source>
Where language is one of these languages:

  - brainfuck

As source you can enter filename or plain source code of your program:

    java -jar kfesolangs.jar -l brainfuck ++++++++++ ++++++++++ ++++++++++ ++++++++++ ++++++++++ ++++++++++ ++++++++++ .

    java -jar kfesolangs.jar -l brainfuck program.bf