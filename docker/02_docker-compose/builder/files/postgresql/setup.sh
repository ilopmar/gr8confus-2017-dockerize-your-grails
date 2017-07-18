#!/bin/bash
echo "######### CREATING DATABASE ##########"

# Perform all actions as user 'postgres'
export PGUSER=postgres

# Create the reverse-app user and database
psql <<EOSQL
    CREATE DATABASE reverseapp;
    CREATE ROLE reverseuser WITH LOGIN PASSWORD 'reversepass';
    GRANT ALL PRIVILEGES ON DATABASE reverseapp TO reverseuser;
EOSQL

echo ""
echo "######### DATABASE CREATED ##########"
