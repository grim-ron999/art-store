#!/bin/bash
DATE=$(date +%Y%m%d_%H%M%S)
BACKUP_DIR="./backups"
BACKUP_FILE="artstore_backup_$DATE.dump"
# Prefer DATABASE_URL env var (as used on many hosts). Override by exporting DB_URL if needed.
DB_URL="${DATABASE_URL:-${DB_URL:-}}"
mkdir -p "$BACKUP_DIR"
echo "Starting backup..."
if [ -z "$DB_URL" ]; then
  echo "ERROR: No database connection string found. Set DATABASE_URL or DB_URL environment variable."
  exit 2
fi

# Use pg_dump with a connection string
pg_dump --dbname="$DB_URL" -F c -b -v -f "$BACKUP_DIR/$BACKUP_FILE"
if [ $? -eq 0 ]; then
  echo "Backup successful: $BACKUP_FILE"
  cd $BACKUP_DIR
  ls -t | tail -n +31 | xargs rm -f
else
  echo "Backup failed!"
  exit 1
fi
