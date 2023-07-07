# exe-springdata
Exercise

Created a controller implementing methods for create, update, delete and read bookings from in-memory volatile database (apache-ignite v2.15.0)
A booking is when a guest selects a start and end date and submits a reservation on a property.
A block is when the property owner or manager selects a range of days during which no guest can make a booking (e.g. the owner wants to use the
property for themselves, or the property manager needs to schedule the repainting of a few rooms).
Blocks can overlap with each other.
Blocks cannot overlap a booking.
Bookings cannot overlap a booking or a block.



The database is running on 127.0.0.1:10800
The server is configurated to work on 127.0.0.1:9000
Tested on Centos 7
Developed with Netbeans16

sql file to create a database is booking_ext.sql


To run the application:

download apache-ignite binaries and extract to eg:/usr/local
download maven and extract to eg: /usr/local
follow provider instructions to install both

open a terminal
Go to $IGNITE_HOME/bin
execute ./ignite.sh

open a new terminal
Go to $IGNITE_HOME/bin
execute ./ignite.sh

open a new terminal
Go to $IGNITE_HOME/bin
execute ./sqlline.sh -u jdbc:ignite:thin://127.0.0.1/
write a username eg: admin
write a password eg: admin
execute !run path_to_booking_ext.sql
execute !q

Go to the project directory
execute mvn spring-boot:run




example REST webservice client:

//read bookings of house with id 4
http://localhost:9000/api/house/bookings?idhouse=4

//trying to delete a non existent booking
http://localhost:9000/api/booking/delete?id=11

//create a booking
http://localhost:9000/api/booking/create?id=11&iduser=2&idhouse=4&datestart=2023-07-02%2013:00:00&dateend=2023-07-04%2013:00:00

//update a booking
http://localhost:9000/api/booking/update?id=11&iduser=2&idhouse=4&datestart=2023-07-03%2013:00:00&dateend=2023-07-05%2013:00:00

//trying to create a new booking overlapping another 
http://localhost:9000/api/booking/create?id=12&iduser=2&idhouse=4&datestart=2023-07-02%2013:00:00&dateend=2023-07-04%2013:00:00

//create a new booking
http://localhost:9000/api/booking/create?id=12&iduser=2&idhouse=4&datestart=2023-07-01%2013:00:00&dateend=2023-07-03%2013:00:00



//read bookings of user with id 4
http://localhost:9000/api/user/bookings?iduser=9

//read blocks of user with id 9
http://localhost:9000/api/user/blocks?iduser=9

//the same operations over the blocks
http://localhost:9000/api/house/blocks?idhouse=4

http://localhost:9000/api/block/delete?id=21

http://localhost:9000/api/block/create?id=21&idhouse=4&datestart=2023-08-02%2013:00:00&dateend=2023-08-04%2013:00:00

http://localhost:9000/api/block/update?id=21&idhouse=4&datestart=2023-08-03%2013:00:00&dateend=2023-08-05%2013:00:00

http://localhost:9000/api/block/create?id=22&idhouse=4&datestart=2023-08-02%2013:00:00&dateend=2023-08-04%2013:00:00

http://localhost:9000/api/block/create?id=23&idhouse=4&datestart=2023-08-01%2013:00:00&dateend=2023-08-03%2013:00:00








