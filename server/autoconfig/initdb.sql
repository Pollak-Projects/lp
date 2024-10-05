-- Create the keycloak database
CREATE DATABASE keycloak;

-- Connect to the keycloak database and create the schema
\connect keycloak;

CREATE SCHEMA keycloak;

-- Connect back to the default database to create the learning_pulse database
CREATE DATABASE learning_pulse;

-- Connect to the learning_pulse database and create the schemas
\connect learning_pulse;

CREATE SCHEMA learning_pulse;