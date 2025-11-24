#!/bin/bash
DATE=$(date +%Y%m%d_%H%M%S)
BACKUP_DIR="./backups"
BACKUP_FILE="artstore_backup_$DATE.dump"
DB_URL="YOUR_SUPABASE_CONNECTION_STRING"
mkdir -p $BACKUP_DIR
echo "Starting backup..."
pg_dump "$DB_URL" -F c -b -v -f "$BACKUP_DIR/$BACKUP_FILE"
if [ $? -eq 0 ]; then
  echo "Backup successful: $BACKUP_FILE"
  cd $BACKUP_DIR
  ls -t | tail -n +31 | xargs rm -f
else
  echo "Backup failed!"
  exit 1
fi
