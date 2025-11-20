Commentary on Immutability in DOP
DOP relies heavily on immutable data structures like records. 
Once a record is created, it cannot be changed. 
This makes the code more predictable because data never mutates unexpectedly. 
Thread safety comes for free, and bugs caused by accidental modifications simply don't happen. 
Validation in the constructor guarantees the data stays valid throughout its lifetime.
The main drawback is that updating data requires creating entirely new objects. 
In this lab, adding a ticket to an attendee meant reconstructing the entire attendee record with a new ticket list. 
This feels repetitive and creates extra allocations. For deeply nested structures or frequent updates, the overhead can add up. 
But for domain models like concerts and tickets, the clarity and safety usually outweigh the performance cost.