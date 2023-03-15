# Assignment 4- IME

Model

Controller

Commands




## Usage

This program supports the following commands:

load image-path image-name: Load an image from the specified path and refer it to henceforth in the program by the given image name.

save image-path image-name: Save the image with the given name to the specified path which should include the name of the file.

greyscale red-component image-name dest-image-name: Create a greyscale image with the red-component of the image with the given name, and refer to it henceforth in the program by the given destination name. Similar commands for green, blue, value, luma, intensity components should be supported.

horizontal-flip image-name dest-image-name: Flip an image horizontally to create a new image, referred to henceforth by the given destination name.

vertical-flip image-name dest-image-name: Flip an image vertically to create a new image, referred to henceforth by the given destination name.

brighten increment image-name dest-image-name: brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening).

rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue: split the given image into three greyscale images containing its red, green and blue components respectively.

rgb-combine image-name red-image green-image blue-image: Combine the three greyscale images into a single image that gets its red, green and blue components from the three images respectively.

run script-file: Load and run the script commands in the specified file

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
the command line once the program is ran.**

Similarly, any text file with commands can be run with the "run" command,
followed by the relative or absolute path to the file.





## Image Source

I, Ted Banken, own the image "donut.ppm" and authorize its use 
in this project.