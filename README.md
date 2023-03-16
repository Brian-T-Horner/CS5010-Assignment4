# Assignment 4- IME

There are two main packages: the model package and the control package.

The model package contains the interfaces Model and Image and the
classes AbstractImage and PPMModel.

Model contains all the logic and operations for loading, 
manipulating images, and saving images.

Image contains the data structure of a single image.

These are implemented for ASCII PPM files in PPMImage and PPMModel
where PPMImage is nested inside. 

The control package contains the Controller interface and its implementation
ImageController. This controller is how the user interfaces the program, through
the command line using the main method.

Inside control is the command package. Each command that the program supports has a class
that implements the Command interface for use inside ImageController.

## Usage

This program supports the following commands:

load image-path image-name: commands.Load an image from the specified path and refer it to henceforth in the program by the given image name.

save image-path image-name: commands.Save the image with the given name to the specified path which should include the name of the file.

greyscale red-component image-name dest-image-name: Create a greyscale image with the red-component of the image with the given name, and refer to it henceforth in the program by the given destination name. Similar commands for green, blue, value, luma, intensity components should be supported.

horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image, referred to henceforth by the given destination name.

vertical-flip image-name dest-image-name: Flip an image vertically to create a new image, referred to henceforth by the given destination name.

brighten increment image-name dest-image-name: brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening).

rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue: split the given image into three greyscale images containing its red, green and blue components respectively.

rgb-combine image-name red-image green-image blue-image: Combine the three greyscale images into a single image that gets its red, green and blue components from the three images respectively.

run script-file: commands.Load and run the script commands in the specified file

luma image-name dest-image-name: Create a greyscale image based on the luma component of an image.
This is essentially shorthand for the greyscale command.

value image-name dest-image-name: Create a greyscale image based on the value component of an image.
This is essentially shorthand for the greyscale command.

intensity image-name dest-image-name: Create a greyscale image based on the intensity component of an image.
This is essentially shorthand for the greyscale command.

The program will prompt the user with a "$" symbol which is used
as feedback to let the user know when the program is ready for another
command.

This program will accept two forms of input via the command line:
either line-by-line input of commands or via a .txt file 
that contains commands. Commented lines beginning with a "#"
character are supported.

**The provided script can be run by typing "run script.txt" into
the command line once the program is ran. The saved files should save to the top level directory**

Similarly, any text file with commands can be run with the "run" command,
followed by the relative or absolute path to the file.





## IME.model.Image Source

I, Ted Banken, own the image "donut.ppm" and authorize its use 
in this project.