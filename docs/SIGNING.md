# Android Release Signing

For production releases, configure these GitHub Actions secrets:

- `ANDROID_KEYSTORE_BASE64`
- `KEYSTORE_PASSWORD`
- `KEY_ALIAS`
- `KEY_PASSWORD`

Never commit a keystore or password to Git. The workflow decodes the keystore only on the temporary GitHub runner.
