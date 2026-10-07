# Development Guide

Read `README.md`, `AADI_PROFILE.md`, `docs/ARCHITECTURE.md` and `docs/PERMISSIONS.md` before changing the app.

Rules:
- Keep core features offline.
- Do not add analytics without a product decision.
- Do not bypass Android security.
- Do not bypass another app's login, lock, DRM or authentication.
- Prefer Android public APIs.
- Keep alarms resilient to reboot and process death.
- Use explicit database migrations.
- Add tests for scheduling and compliance logic.

Product: MakeLifeGreat
Owner: Aadi
Package: com.aadi.makelifegreat
