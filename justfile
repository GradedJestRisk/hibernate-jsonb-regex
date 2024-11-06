set dotenv-load

database-start:
  docker compose up --remove-orphans --force-recreate --renew-anon-volumes --detach

database-stop:
  docker compose down

database-load:
  psql --dbname "host=$DB_HOST port=$DB_PORT dbname=$DB_NAME user=$DB_USER password=$DB_PASSWORD" --file load-database.sql

database-console:
  psql --dbname "host=$DB_HOST port=$DB_PORT dbname=$DB_NAME user=$DB_USER password=$DB_PASSWORD"