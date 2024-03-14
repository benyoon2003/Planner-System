This project was designed by Senior Developers Ben Yoon and Nico Cruz.
All Rights Reserved.

Overview:
    This project and subsequent code are attempting to solve an organizational
problem by creating a planner system in which users can make events and invite
others to the event.The systems ensures that no events conflict with other events
in a user's schedule and that only valid events are added. We assumed that this
version of the model would not need any security measures so there are none but
this is not to say that there is no thought into information hiding. We designed
this model so that interacting with the model requires little understanding in how
the model works. All that is necessary is only the model's public method's to be used.
There are package protected methods that can be called when inside the package that can
alter the model. These methods had to package protected and not private for implementation.

Quick Start:
    To start the model first you must create an instance of the model:
        PlannerModel example = new NuPlannerModel(new ArrayList<>());
    The empty array list is representing the planner with no users in the system.

    To then add users simply write:
        this.example.addUser("Lucia");

    To create an event, you must be a user in the system to which the event
    you create you are the host:
        this.example.createEvent("Ben", "OOD", "Snell", true
            , Day.Monday, 1800, Day.Wednesday, 1800, List.of("Nico"));
    ***Notice that the host is not in the invitee list, this design choice
    was made because it doesn't make sense from the users perspective to invite yourself.
    But in the event object the event puts the host in the front of the list of people
    attending.
    ***Another thing to take note of is that createEvent will throw an exception if
    a user in the invitee list does not exist.

 Key Components:
    The main driver for this control flow is the model, the system and
 all its actions are contained in the model. The view is then driven to render
 the model and has no control over the system.

 Key Subcomponents:
    Key subcompnents would be Users and Events, both of these objects work in tandem to
 create the system. This is why the User has ties to its events and Events have ties to
 their users/attendees.

 Source Organization:
    All the subcomponents can be found inside the model package of this project as
 following suit the view class and interface cna be found in the view package. We created a
 controller package that is empty as that was not a part of the assignment but will be in the
 future.
