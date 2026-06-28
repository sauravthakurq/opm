# Security Policy

At **OPM**, security and user trust are our highest priorities. We take vulnerabilities seriously and are committed to ensuring our open-source music player remains a safe environment for all users.

---

## Supported Versions

We actively provide security patches and updates for the following versions:

| Version | Supported          |
| ------- | ------------------ |
| 5.x.x   | :white_check_mark: |
| < 5.0   | :x:                |

*(Note: We highly recommend all users stay on the latest stable release to ensure they receive the most up-to-date security patches.)*

---

## Reporting a Vulnerability

If you discover a security vulnerability in OPM, please report it responsibly so we can address it before it affects users.

1. **Do NOT** create a public GitHub issue or discuss it in public community channels.
2. Email the developer directly at: **[sauravthakur6310@gmail.com](mailto:sauravthakur6310@gmail.com)**
3. Please include the following information in your report:
   - A detailed description of the vulnerability.
   - Step-by-step instructions to reproduce the issue.
   - The potential impact on users.
   - Any suggested mitigations or fixes.

We will acknowledge your email within 48 hours and work with you to resolve the issue as quickly as possible.

---

## Security Best Practices

### For Developers & Contributors
- **Never commit sensitive files**: API keys, tokens, and credentials (e.g., `google-services.json`, `local.properties`, `*.keystore`, `secrets.properties`) should never be committed to version control. They are explicitly ignored in our `.gitignore`.
- **Review Dependencies**: Keep dependencies updated to patch upstream security vulnerabilities.
- **Code Review**: All pull requests must be thoroughly reviewed before merging into the main branch.

### For Users
- **Download from Official Sources**: To avoid malware, only download the OPM APK from the official [GitHub Releases page](https://github.com/sauravthakurq/opm/releases).
- **Keep the App Updated**: Install updates promptly.
- **Review Permissions**: OPM only asks for the bare minimum permissions necessary to function (such as storage for local playback and notifications for media controls).

---

Thank you for helping keep OPM secure!
