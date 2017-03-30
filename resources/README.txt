Emily Daniel
HW5 description

1.  The model
    MusicModelOps - the interface for the model, contains all of the methods 
                    necessary for the model, addNote, deleteNote, changeNote,
                    combineSheets, consecSheets, and printSheet. Added getter
                    methods to interface.

    MusicModel - the class for the actual model, implements MusicModelOps. It
                 has four fields, a Map<NoteName, Set<Note>> sheet which holds
                 all of the notes anad names, an int tsNum which is the time
                 signature numerator, or the measure length, tsDen which is the 
                 time signature denominator, and bpm to hold the tempo, which was
                 later added. The sheet is a TreeMap, with the name of the notes 
                 as the key, and the set of notes as the data. The set is a TreeSet 
                 which orders the notes based on their starting beat. Has public 
                 methods to add a note, delete a note, change a note, combine two
                 sheets to play simultaneously, combine two sheets to play 
                 consecutively, and print the data in the sheet. Methods to get the 
                 data were added later, along with changing the access of the method
                 findMaxBeat from private to public. Has a private method that
                 makes sure when notes are added into the piece, if there is a gap
                 the in between notes are added. 

2.  The NoteName interface/class
    NoteNameOps - the interface for note names, contains all of the methods for 
                  a note name, getPitch, getOct, nextNote, prevNote.

    NoteName - Has two private fields for pitch and octave. The pitch is an enum,
               going from A - G#. The octave is a positive int. Has a method getPitch
               which returns the value of the pitch, getOct which returns the octave,
               printName which prints the string and centers it in a String with width
               5. Has a method that will return the next/previous note, making sure the
               octaves are inc/decremented correctly. Has a private helper method to
               center the name in a string. Another constructor was added so that a 
               NoteName can be created from a single value. 

    Pitch - an enum, with the values A AS B C CS D DS E F FS G GS. Has a method 
            getVal which returns the value of the enum, uses Java's default ordering.
            Has two methods nextPitch and prevPitch which return the next/prev pitch.
            Has a print method to print the pitch properly.

    NoteNameComp - a comparator to order the NoteName within the TreeMap in the Music 
                   model. If the octaves are different, uses that and the pitch to 
                   determine which is larger. If they are the same, just the pitch 
                   to compare.

3. The Note interface/class
    NoteOps - Interface for notes, contains all public methods for the note, getStart,
              getDuration, drawNote, and change. Added methods to get the instrument 
              and the dynamics, along with a method to determine if a note is playing
              at a certain beat.

    Note - Class for the notes in the music. Has four private fields start and duration,
           and instrument and dynamics which were added later. Has a copy constructor. 
           Start is the starting beat for the note, and duration 
           is how long the note is sustained for. The instrument is the instrument number
           that Midi associates with each kind of instrument. The higher the dynamics is, 
           the louder the note will sound via Midi. Has methods to return the starting 
           beat, the duration, draw the note, and change the note. Methods were added to
           get the dynamics and the instrument.

    NoteComp - Comparator for the notes, which is used to order the notes within the
               TreeSet of the MusicModel. Uses the starting beat to determine which 
               is larger. 

4. The View(s) interface/classes
    IMusicView - The interface for the views. Has methods to update the view, set the 
                 beat, set the KeyListener, and get the current beat.

    ConcreteGuiViewFrame - The Frame for the data to be rendered as a text. Has a private
                           field for the Panel. Has a different constructor to take in a
                           Panel and set that equal to its field. Can update the view, set
                           the beat, set the KeyListener, and ge the beat.

    ConcreteGuiViewPanel - The Panel for the data to be rendered as text. Has a private 
                           field sht which stores the data as a string. Paints the component
                           onto the panel. UpdateView updates the data and repaints, getSheet
                           returns the sheet.

    GUIViewFrame - The Frame for the gui view. Has two private fieds shtPanel and pianoPanel
                   that display the data. Updates the view by passing the new data to the 
                   Panels, sets the beat to be highlighted, sets the KeyListener, and gets
                   the current beat.

    SheetPanel - The Panel to show the music notes on the staff. Has four private fields
                 redLineBeat, data, panelWidth, and panelHeight. Can update the sheet to 
                 repaint the view, updates the beat, returns the beat, and paints the components.

    PianoPanel - The Panel to show the notes on a piano. Has two private fields, the data and
                 the current beat. Paints the components onto the screen, updates the data,
                 and updates the current beat.

    MidiViewImpl - Audio playback of the music. Has two private fields sequencer and track. 
                   Updates the view by looping through the music and playing the notes. 
    
    ViewFactory - Creates a different type of view depending on what String is passed. Creates
                  a ConcreteGuiViewFrame if "console" is passed in, a GUIViewFrame if "visual"
                  is passed in, and a MidiViewImpl if "midi" is passed in.

5. The Controller
    MusicController - The controller. Is passed a model and a view, converts the model's data 
                      to a ModelData object, and passes that to the view so the view can't
                      accidentally modify the model. Configures the keyboard listener so that
                      the views behave accordingly when certain keys are pressed.

    KeyboardListener - Has a private map keyPressedMap that keeps track of what keys should
                       do something when pressed, and runs that. 

