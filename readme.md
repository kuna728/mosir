# Mosir
## Sports and recreation center management system
Mosir is a system built using *PostgreSQL*, *Spring* and *React* that supports a sports center.
The system was created for both employees and customers of the sports center.
The web application is currently only available in Polish.
The system is still in the development phase, however, some core functionalities have already been fully implemented:
- Public sites for unauthenticated users
- User token-based authentication
  - User profiles with roles (client, employee, manager etc.)
  - Clients registration with account activation
  - Password reset
- Email sending service
- PDF document generation service (tickets, invoices)
  - QR code generation service (tickets)
- User tickets site
  - Ticket list site with "download ticket" and "download invoice" buttons for each ticket
  - Buy ticket site (multistep, mocked payment)
- Mobile app for employees - [ticket scanner](https://github.com/kuna728/mosir-ticket-scanner)
  - Authentication required (employee account)
  - Ticket scanning based on the QR code shown by the customer
  - Ticket validation / marking ticket as used

In order to try out [Mosir](https://mosir.azurewebsites.net/), you can use the following user accounts:

| Username       | Email                         | Password | Role     | Destination |
|:---------------|-------------------------------|:---------|:---------|:------------|
| test           | test@gmail.com                | password | client   | web app     |
| marcin98       | marcin98@gmail.com            | password | client   | web app     |
| a.andrzejowicz | andrzej.andrzejowicz@mosir.pl | password | employee | mobile app  |

## Full-scale ticketing system
The functionality of buying tickets has been fully implemented. 
The Mosir has 2 types of tickets - a single-use ticket and a reusable ticket.
Use case tables for ticketing are presented below.

| UC01          | Purchase ticket                                                                                                                                                                                                                                                                                                                                                            |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Actors        | Client, Web app                                                                                                                                                                                                                                                                                                                                                            |
| Precondition  | Client is authenticated.                                                                                                                                                                                                                                                                                                                                                   |
| Postcondition | New ticket assigned to the customer's account is created. <br /> Client receives an email with the ticket and invoice.                                                                                                                                                                                                                                                     |
| Main scenario | 1. Client selects ticket type and order details via Web app.<br /> 2. *Mocked payment step* <br /> 3. System creates a new ticket and assigns it to the client. <br /> 4. System generates invoice and ticket document with QR code. <br/> 5. System sends email to client. <br /> 6. Web app displays ticket details and provides button to download generated documents. |

| UC02          | Use ticket                                                                                                                                                                                                     |
|---------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Actors        | Client, Employee, Mobile app                                                                                                                                                                                   |
| Precondition  | Employee is authenticated. <br /> Client has QR code of a ticket.                                                                                                                                              |
| Postcondition | Ticket status is updated                                                                                                                                                                                       |
| Main scenario | 1. Client shows QR code to employee.<br /> 2. Employee scans QR code via Mobile app. <br /> 3. System validates ticket.<br /> 4. Employee clicks the submit button. <br/> 5. System marks the ticket as *used* |
| Extensions    | 5a. Ticket is reusable. <br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 5a1. System updates value of the ticket usages counter.                                                                                          |
