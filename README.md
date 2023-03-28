# Assignment 5- MIME

There are two main packages: the model package and the control package.

Model contains all the logic and operations for loading, 
manipulating images, and saving images.

Image contains the data structure of a single image.

The model package contains the interfaces Model and Image and the
classes AbstractImage and ImageModel(this has been refactored. It was called
"PPMModel" in the last assignment. PPMImage, a concrete class of AbstractImage, has also been refactored to "IMEImage" ).


A couple major changes have been made in this iteration of the IME application.
First, the controller has been abstracted into AbstractController,
which ImageController is extended from. A new controller has been created,
FileController, which operates in exactly the same way as the ImageController
but reads from a file instead of user input. This was done to make the user interaction 
cleaner and to separate the process of reading commands from a file from user input. Functionality
was also added to allow the program to exit through a script file(using the "quit" command).

The model, ImageModel, has added functionality as well, with methods to blur, sharpen, dither and sepia-scale
an image, with corresponding interface changes and new command classes.

Inside control is the command package. Each command that the program supports has a class
that implements the Command interface for use inside the controller.

ImageUtil is a static class that deals with loading and saving images.
BufferedImage and ImageIO are convenient JDK classes to do this. 
PPM files are read directly from the file, but all other file types supported
are read and written to a BufferedImage, which acts as an intermediary between
loading and saving our Image objects.

## Usage

The program will prompt the user with a "$" symbol which is used
as feedback to let the user know when the program is ready for another
command.

The supported image types that can be loaded and saved are .ppm, .png
.bmp, and .jpg.

This program will accept two forms of input via the command line:
either line-by-line input of commands or via a .txt file 
that contains commands. Commented lines beginning with a "#"
character are supported.

**The provided script can be run by typing "run script.txt" into
the command line once the program is run. 
Alternatively, the script can be run by adding "-file script.txt"
as a command line argument to this program.
The resulting files should save to the top level directory.**


Similarly, any text file with commands can be run with the "run" command 
or "-file" command line argument, followed by the relative or absolute path to the file.





## Image Source

I, Ted Banken, own the image "donut.png" and authorize its use 
in this project.