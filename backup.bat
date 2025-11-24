@echo off
set DATE=%date:~-4,4%%date:~-10,2%%date:~-7,2%_%time:~0,2%%time:~3,2%%time:~6,2%
set BACKUP_DIR=backups
set BACKUP_FILE=artstore_backup_%DATE%.dump
set DB_URL=YOUR_SUPABASE_CONNECTION_STRING
if not exist %BACKUP_DIR% mkdir %BACKUP_DIR%
echo Starting backup...
pg_dump "%DB_URL%" -F c -b -v -f "%BACKUP_DIR%\%BACKUP_FILE%"
if %errorlevel% equ 0 (
  echo Backup successful: %BACKUP_FILE%
) else (
  echo Backup failed!
)
