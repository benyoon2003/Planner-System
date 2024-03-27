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
alter the model. These methods had to be package protected and not private for implementation.

    The PlannerView produces a graphical user interface of the ReadOnlyPlannerModel version
of the previously implemented mode. This means that the view is only able to utilize the following
methods: selectSchedule, eventsAtThisTime, scheduleOnDay, and getListOfUser, making the model
read only and not mutable. For the purposes of this project, the view can be used simply by
initializing the MainScheduleFrameView. The PlannerView interface provides a render method,
which exists for all types of PlannerView, but does not need to be called in order to produce a
MainScheduleFrameView due to the nature of JFrame. As a result of this, the MainScheduleFrameView
is the only public class within view. All other classes: EventFrameView, EventRedPanel,
MainBottomPanel, and WeekViewPanel are package protected.

    The MainScheduleFrameView is a custom JFrame that implements PlannerView and provides the
overarching frame for all subcomponents to overlay onto. One of the two main subcomponents on the
MainScheduleFrameView is the WeekViewPanel, which is a custom JPanel containing grid lines that
indicate days and hours of the week. TheWeekViewPanel is then overlaid with events represented
by EventRedPanels, which are clickable red panels that are resized on the WeekViewPanel
depending on the duration of the event. On click of the red panel, another custom interactive
JFrame, EventFrameView pops up. This frame contains multiple panels that allows the user to input
details for a new Event, modify the details of a pre-existing event, or remove the Event from the
user's schedule.

    Below the WeekViewPanel is the other main subcomponent of the MainScheduleFrameView, the
MainBottomPanel, which is another interactive panel containing a JComboBox, which allows the user
to select who's schedule to display on the WeekViewPanel, as well as two buttons that provide
options to create and schedule new events. These buttons also pop up an EventFrameView.

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

    In order to run the view, the user must simply initialize MainScheduleFrameView with
    the PlannerModel as the parameter:
        new MainScheduleFrameView(this.example);

    ***Notice that although this.example is a NuPlanner, when passed into the
    MainScheduleFrameView constructor, it automatically becomes a ReadOnlyPlannerModel


 Key Components:
    The main driver for this control flow is the model, the system and
 all its actions are contained in the model. The view is then driven to render
 the model and has no control over the system.

 Key Subcomponents:
    Key subcomponents would be Users and Events, both of these objects work in tandem to
 create the system. This is why the User has ties to its events and Events have ties to
 their users/attendees.

 Source Organization:
    All the subcomponents can be found inside the model package of this project as
 following suit the view class and interface can be found in the view package. We created a
 controller package that is empty as that was not a part of the assignment but will be in the
 future.

 Changes for part 2:
    To reflect on the comments made from the TA on our previous model implementation, we
 fixed memory leakage in the User class, documented the PlannerView better, and made the
 observation methods within Event public.
