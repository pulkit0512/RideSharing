# RideSharing

## Description: Implement a ride-sharing application with the below-expected features.

## Features:

The application allows users to share rides on a route.
Users can either offer a shared ride (Driver) or consume a shared ride (Passenger).
Users can search and select one from multiple available rides on a route with the same source and destination.

1) Application should allow user on-boarding.

    1.1) add_user(user_detail) Add basic user details

    1.2) add_vehicle(vehicle_detail) Add the user’s vehicle(s) details

2) User should be able to offer a shared ride on a route with details.

    2.1) offer_ride(ride_detail) Ride will have details like vehicle, origin, destination, available seats. (A ride will have no intermediate stops.)

3) Users can select a ride from multiple offered rides using a selection strategy. (A user can only request  a ride (only for 1 or 2 people))

    3.1) select_ride(source, destination, seats, selection_strategy)

    3.2) Preferred Vehicle
    
    a) (Activa/Polo/XUV)

    b) Most Vacant.
4) System should be able to end the ride. User can only offer a ride for a given vehicle, once there are no active offered rides for that vehicle. 

   end_ride(ride_details)

5) Find total rides offered/taken by all users.

    print_ride_stats()