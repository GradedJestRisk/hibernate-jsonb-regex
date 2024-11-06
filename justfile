set dotenv-load

database-start:
  docker compose up --remove-orphans --force-recreate --renew-anon-volumes --detach

database-show-queries:
  docker compose up --remove-orphans --force-recreate --renew-anon-volumes

database-stop:
  docker compose down

database-load:
  psql --dbname "host=$DB_HOST port=$DB_PORT dbname=$DB_NAME user=$DB_USER password=$DB_PASSWORD" --file load-database.sql

database-list:
  psql --dbname "host=$DB_HOST port=$DB_PORT dbname=$DB_NAME user=$DB_USER password=$DB_PASSWORD" --file list-database.sql

database-console:
  psql --dbname "host=$DB_HOST port=$DB_PORT dbname=$DB_NAME user=$DB_USER password=$DB_PASSWORD"