# create ms sql service

ref: https://learn.microsoft.com/en-us/sql/linux/quickstart-install-connect-docker?view=sql-server-ver16&pivots=cs1-bash

## Pulling image

image azure-sql-edge for apple m1 chip

```bash 
docker pull mcr.microsoft.com/mssql/server:2022-latest
```

## create container

The SA_PASSWORD environment variable is deprecated. Use MSSQL_SA_PASSWORD instead.

```bash
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=<YourStrong@Passw0rd>" \
-p 1433:1433 --name sql1 --hostname sql1 \
-d \
mcr.microsoft.com/mssql/server:2022-latest
```

| Parameter                                   | Description                                                                                                                                                                                                                                       |
| ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| -e "ACCEPT_EULA=Y"                          | Set the ACCEPT_EULA variable to any value to confirm your acceptance of the End-User Licensing Agreement. Required setting for the SQL Server image.                                                                                              |
| -e "MSSQL_SA_PASSWORD=<YourStrong@Passw0rd> | Specify your own strong password that is at least eight characters and meets the SQL Server password requirements. Required setting for the SQL Server image.                                                                                     |
| -e "MSSQL_COLLATION=<SQL_Server_collation>" | Specify a custom SQL Server collation, instead of the default SQL_Latin1_General_CP1_CI_AS.                                                                                                                                                       |
| -p 1433:1433                                | Map a TCP port on the host environment (first value) with a TCP port in the container (second value). In this example, SQL Server is listening on TCP 1433 in the container and this container port is then exposed to TCP port 1433 on the host. |
| --name sql1                                 | Specify a custom name for the container rather than a randomly generated one. If you run more than one container, you can't reuse this same name.                                                                                                 |
| --hostname sql1                             | Used to explicitly set the container hostname. If you don't specify the hostname, it defaults to the container ID, which is a randomly generated system GUID.                                                                                     |
| -d                                          | Run the container in the background (daemon).                                                                                                                                                                                                     |

## check service status

```bash
docker exec -t sql1 cat /var/opt/mssql/log/errorlog | grep connection
```

## change administrator password

```bash
sudo docker exec -it sql1 /opt/mssql-tools/bin/sqlcmd \
-S localhost -U SA \
 -P "$(read -sp "Enter current SA password: "; echo "${REPLY}")" \
 -Q "ALTER LOGIN SA WITH PASSWORD=\"$(read -sp "Enter new SA password: "; echo "${REPLY}")\""
 ```

 [EOF]
 