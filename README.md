# Currency exchanger

This project is split between subprojects:

* exchanger-bom
* exchanger-core
* exchanger-eureka-server
* exchanger-liquibase
* currency-provider
* dataservice
* exchange-rate-provicer
* transaction-manager

#### Exchanger Bom

Subproject used as a platform for distributing dependencies

#### Exchanger Core

Classes common to all submodules, such as loggers.

#### Exchanger Eureka Server

Service which contains eureka server responsible for loadbalancing and discovery between services.

#### Exchanger Liquibase

Module containing sql scripts defining database and changes made in it.

#### Currency Provider

Main service which enables user creation and manipulation

#### Dataservice

Service responsible for proper setup of database. In the future it should serve as a midpoint between services and
database. Each service should have its own database or database scheme, but in case when they are using one database
there should be a service used as a broker between them.

### Exchange rate provider

This service provides current exchange range accurate to the information provided by NBP. For reduction of REST request
cache was used. In the future information about the exchange rate can be saved to the database for gathering data and
processing transactions happening in different timestamps.

#### Transaction manager

Transaction manager is responsible for processing transactions. Usually bank transactions require complicated handling,
so that's why I decided to move it to the different microservice. Transactions in this system are simplified, they do
not require any acknowledgement or do not check if the wallet have the value to support this transaction.

## Main logic

Account describes person with 11-digit social number as an owner of bank account. Each account can have multiple
wallets (currency subaccounts) which are uniquely identified. User can have multiple wallets connected to its account,
but for each currency can be only one wallet connected.

Transactions are instantaneous, there is no downtime for approval from user, it can lead to many anomalies, but for sake
of simplicity it is enough. In more real world scenario there should be some kind of verification for user (session)
and asynchronous processing which can be achieved using events with Kafka or Rabbitmq. To reduce time for wallet balance
database views can be used which would make balance lookup much quicker.

### General Comments

This whole project could be done within one service, but in my opinion splitting it grants greater clarification and
single responsible principle. Moreover, I wanted to learn more about spring cloud. Some of its functionalities are taken
over by kubernetes, such as loadbalancing and service discovery, but nevertheless it was a nice experience to use it in
project.

All the services can be built as a docker container and then run within docker compose.

Split between `api` and `service` modules was dictated by the fact that I prefer to import only necessary dependencies.
It can be avoided but then namespaces will be polluted with unnecessary classes.

I prefer usage of functions rather than wrapping REST calls inside RestTemplates. Usage of spring <5 rest template was
intentional. In this version of project there is no need for async processing.

### What about tests?

Test are very important part of the code. In my case lack of time made me abandon them (to my disappointment). For the
future I plan to create unit tests for services and some tests using Wiremock and testcontainers for integration tests
wrapping every service.

### Other remarks

Validation from spring does not work despite various tries `@field:Min(2)` refuses to work correctly, that's why regular
validation was used.

Feign client requires extra touches for `POST` requests.