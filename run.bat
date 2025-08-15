@echo off
echo Starting Student Management API...
echo.
echo Make sure you have:
echo 1. Java 17+ installed
echo 2. Maven installed
echo 3. MySQL running on port 3306
echo 4. Database 'studentdb' created
echo.
echo Press any key to continue...
pause >nul

echo Building project...
call mvn clean install

if %ERRORLEVEL% NEQ 0 (
    echo Build failed! Please check the errors above.
    pause
    exit /b 1
)

echo.
echo Starting application...
echo API will be available at: http://localhost:8080
echo.
call mvn spring-boot:run

pause
