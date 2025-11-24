# ArtStore

This repository contains a minimal Spring Boot + Thymeleaf art store scaffold.

Quick start (dev):

```bash
# Build
mvn clean package

# Run
mvn spring-boot:run

# Visit
http://localhost:8080
http://localhost:8080/admin (login: admin / changeme)
```

Deployment notes:

- Add `system.properties` with `java.runtime.version=11` for some hosts such as Vercel.
- For production, set `DATABASE_URL` to your PostgreSQL (Supabase) connection and add `ADMIN_PASSWORD`.

Backup scripts: `backup.sh` and `backup.bat` are included (set DB URL before use).

See the included `src/main/resources/application-prod.properties` for production configuration.
