#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER uamerica WITH ENCRYPTED PASSWORD 'america';
	CREATE DATABASE america;
	GRANT ALL PRIVILEGES ON DATABASE america TO uamerica;
EOSQL