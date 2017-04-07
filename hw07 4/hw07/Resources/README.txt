Music Editor
============
by Vincent Carlino and Thomas Katzman


Description
-----------
This is a music editor developed completely in Java.  

VERSION 1++++++++++++++++++++++++++++++++++++++++++++++
Design
------
Model ---------------------------------------------------- 
	The model is the same design as it was
in Vincent's HW5 with a minor change of having 
a map of what notes are played at certain beats.
In addition, all methods used for the textual
view were removed from the model and placed into the
implementation of the console view.  The model now 
contains a value for the tempo of the piece, a number
that was previously irrelevant without a MIDI playback.
	The model now allows public classes to retrieve
the length of the piece as well as the tempo and the notes
for each beat.  

View ------------------------------------------------------
	The views consist of 4 public methods: run, setModel,
moveLeft, and moveRight.  Run simply runs the view in which-
ever format is needed.  setModel takes in a ViewModel that is
used as a read-only model for the view to take data from.  
moveLeft and moveRight are used to move forward and back in
the song.  This is primarily used in the GUIView which must
show a keyboard with highlighted notes of what is playing. 
	Any additional methods contained in the views are
private and are mostly used to prepare for displaying the 
piece.

Controller -------------------------------------------------
	The controller currently has one method: run.  It 
will simply run the view it contains.  Upon construction, 
the controller will set the model of its given view based on 
its given model.  It will also initialize the keyboard controls
 
VERSION 2+++++++++++++++++++++++++++++++++++++++++++

Version 2 introduces some new features for the music editor.  Most notably,
we added a composite view which, for our purposes, is used to play the audio
of the given piece while also having a visual representation running at the same time.  

Changes to Views--------------------------------------------

We have added an additional interface, GUIViewOperations, which contains all operations
that any implemented GUIView should be able to do.  These operations are mostly just 
getters that get aspects of the view.  We also added methods to the general View interface including
methods that move to the start and end of the piece, a pause and play function, and various getters.

Changes/Addition of Controller---------------------------------

Version 2 sees that addition of more keyboard control than version 1.  Use the left and right arrow keys
to scroll through the piece and use the Home and End keys to move to the beginning and end of the piece.
In addition, Space can be used to pause and play the piece.  

We have two controllers in this implementation of the music editor.  The General controller has all of the above
functionality and can be used with anything that implements the View interface.  The GUIController contains 
private methods that can only function when paired with a GUIView (mainly the mouse functionality of being
able to click the piano and add notes).  