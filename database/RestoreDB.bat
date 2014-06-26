@echo on

echo.
echo Restoring database to initial configuration...

copy DatabaseBackups\MainDB.script MainDB.script
copy DatabaseBackups\CacheDB.script CacheDB.script
copy DatabaseBackups\MainDB.properties MainDB.properties
copy DatabaseBackups\CacheDB.properties CacheDB.properties
del CacheDB.data

echo.
echo Database restored.
pause
