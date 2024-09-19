\c postgres;

-- Create the keycloak database
CREATE DATABASE keycloak;

-- Connect to the keycloak database and create the schema
\c keycloak;
CREATE SCHEMA keycloak;

-- Connect back to the default database to create the learning_pulse database
\c postgres;
CREATE DATABASE learning_pulse;

-- Connect to the learning_pulse database and create the schemas
\c learning_pulse;
CREATE SCHEMA IF NOT EXISTS learning_pulse
    AUTHORIZATION root;
CREATE SCHEMA IF NOT EXISTS user_schema
    AUTHORIZATION root;