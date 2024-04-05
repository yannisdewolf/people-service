#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER ueurope WITH ENCRYPTED PASSWORD 'europe';
	CREATE DATABASE europe;
	GRANT ALL PRIVILEGES ON DATABASE europe TO ueurope;
EOSQL